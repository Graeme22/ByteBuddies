package bytebuddies.corgaday.bytebuddies.engine;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Message;
import android.view.View;
import android.os.Handler;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

import bytebuddies.corgaday.bytebuddies.R;
import bytebuddies.corgaday.bytebuddies.bluetooth.PairingList;
import bytebuddies.corgaday.bytebuddies.player.Player;
import bytebuddies.corgaday.bytebuddies.util.Constants;
import bytebuddies.corgaday.bytebuddies.util.Settings;
import bytebuddies.corgaday.bytebuddies.bluetooth.BluetoothServices;
import bytebuddies.corgaday.bytebuddies.util.Utilities;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Intent intent, i;
    private DisplayMetrics dm;
    private BluetoothAdapter ba;
    public static Player player;
    private SensorManager sm;
    private Sensor accelerometer;
    private GameThread gt;

    private static final String TAG = "MainActivity";

    public GamePanel gamePanel;
    private String mConnectedDeviceName = null;
    private ArrayAdapter<String> mConversationArrayAdapter;
    private StringBuffer mOutStringBuffer;
    private BluetoothServices mChatService = null;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.MESSAGE_WRITE:
                    byte[] writeBuf = (byte[]) msg.obj;
                    // construct a string from the buffer
                    String writeMessage = new String(writeBuf);
                    mConversationArrayAdapter.add("Me:  " + writeMessage);
                    break;
                case Constants.MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    // construct a string from the valid bytes in the buffer
                    String readMessage = new String(readBuf, 0, msg.arg1);
                    mConversationArrayAdapter.add(mConnectedDeviceName + ":  " + readMessage);
                    break;
                case Constants.MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    mConnectedDeviceName = msg.getData().getString(Constants.DEVICE_NAME);
                    if (null != getApplicationContext()) {
                        Toast.makeText(getApplicationContext(), "Connected to "
                                + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case Constants.MESSAGE_TOAST:
                    if (null != getApplicationContext()) {
                        Toast.makeText(getApplicationContext(), msg.getData().getString(Constants.TOAST),
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    final int CODE_BT = 1;
    final int CODE_BT_DESC = 2;
    final int LIST_CODE = 3;
    Set<BluetoothDevice> pairedDevices;
    BluetoothDevice bdl[];
    String devices[];
    String otherAddress;

    private void connectDevice(Intent data, boolean secure) {
        BluetoothDevice device = ba.getRemoteDevice(otherAddress);
        mChatService.connect(device, secure);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BluetoothSocket tmp = null;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        //player = new Player(HeroX);
        sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accelerometer, sm.SENSOR_DELAY_GAME);

        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        int resourceID = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if(resourceID > 0) Constants.SCREEN_WIDTH += getResources().getDimensionPixelSize(resourceID);
        Constants.FRAME = Utilities.getScaledDimension(new Dimension(2, 1), new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        Constants.FRAME.top = 0;
        Constants.FRAME.left = (Constants.SCREEN_WIDTH / 2) - (Constants.FRAME.width / 2);
        Constants.FRAME.bottom = Constants.FRAME.height;
        Constants.FRAME.right = Constants.SCREEN_WIDTH / 2 + (Constants.FRAME.width / 2);
        Constants.APPLICATION_CONTEXT = getApplicationContext();
        Constants.MAC = Utilities.getMacAddress();
        Constants.SENSOR_SERVICE = (SensorManager)getSystemService(SENSOR_SERVICE);

        gamePanel = new GamePanel(this, this);
        setContentView(gamePanel);

        ba = BluetoothAdapter.getDefaultAdapter();
        if(ba == null) {
            Toast.makeText(this, "Bluetooth not supported.", Toast.LENGTH_LONG);
            this.finish();
        }
        gt = new GameThread(gamePanel.getHolder(), gamePanel);
        if(ba.isEnabled()) {
            intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, 0);
        }
        intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mChatService != null) mChatService.stop();
    }

    @Override
    public void onResume() {
        super.onResume();
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        if(mChatService != null && mChatService.getState() == BluetoothServices.STATE_NONE) mChatService.start();
    }

    @Override
    public void onSensorChanged(SensorEvent se) {
        float x = se.values[1];
        float y = se.values[0];

        player.moveChampion(se.values[0], se.values[1], 4f, Settings.SENSITIVITY);
        player.rotateChampion(x, y, Settings.SENSITIVITY);
    }

    @Override
    public void onAccuracyChanged(Sensor s, int i) {}

    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }


    @Override
    public void onActivityResult (int reqCode, int resCode, Intent data) {
        if (reqCode == CODE_BT && resCode != RESULT_CANCELED) {
            Toast.makeText(getBaseContext(), "Bluetooth turned on", Toast.LENGTH_LONG).show();
            openList();
        } else if (reqCode == LIST_CODE && resCode != RESULT_CANCELED) {
            gamePanel.getGameThread().setRunning(true);
            BluetoothDevice device = ba.getRemoteDevice(PairingList.EXTRA_DEVICE_ADDRESS);
            boolean goFirst = Utilities.compareMacs(Constants.MAC, PairingList.EXTRA_DEVICE_ADDRESS);

            if (goFirst) {
                try {
                    BluetoothServerSocket bss = ba.listenUsingRfcommWithServiceRecord("ByteBuddies", UUID.fromString(Constants._UUID));
                    boolean notConnected = true;
                    while (notConnected) {
                        BluetoothSocket bs = bss.accept();
                        bss.close();
                        if (bs.getRemoteDevice().getAddress() != device.getAddress()) {
                            bs.close();
                        } else {
                            notConnected = false;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    device.createRfcommSocketToServiceRecord(UUID.fromString(Constants._UUID));
                    //connect here
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        }
    }

    public void activateBT () {
        i = new Intent();
        i.setAction(ba.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(i, CODE_BT);
        mChatService = new BluetoothServices(this, mHandler);
    }

    private void openList () {
        pairedDevices = ba.getBondedDevices();
        devices = new String[pairedDevices.size()];
        int j = 0;
        bdl = new BluetoothDevice[pairedDevices.size()];
        for (BluetoothDevice device : pairedDevices) {
            bdl[j] = device;
            devices[j] = device.getName();
            j++;
        }
        Bundle bn = new Bundle();
        bn.putStringArray("pairs", devices);

        Intent in = new Intent("pair_filter");
        in.putExtras(bn);

        gamePanel.getGameThread().setRunning(false);

        startActivityForResult(in, LIST_CODE);
    }

}