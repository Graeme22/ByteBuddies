package etbcor.bluetoothgame.engine;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;
import android.os.Handler;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

import etbcor.bluetoothgame.R;
import etbcor.bluetoothgame.bluetooth.BluetoothServices;
import etbcor.bluetoothgame.util.Constants;
import etbcor.bluetoothgame.util.Utility;

public class MainActivity extends Activity {
	// set script tag
	private static final String TAG = "MainActivity";

	private GamePanel gamePanel;
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
	};;

	// make bt variables
	BluetoothAdapter bluetoothAdapter;
	Intent i;
	final int CODE_BT = 1;
	final int CODE_BT_DESC = 2;
	final int LIST_CODE = 3;
	Set<BluetoothDevice> pairedDevices;
	BluetoothDevice bdl[];
	String devices[];
	String otherAddress;

	@Override
	public Context getApplicationContext () {
		return super.getApplicationContext();
	}

	private void connectDevice(Intent data, boolean secure) {
		BluetoothDevice device = bluetoothAdapter.getRemoteDevice(otherAddress);
		// Attempt to connect to the device
		mChatService.connect(device, secure);
	}

	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		BluetoothSocket tmp = null;

	    // make sure we start w/out nav bars
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

	    // set Constants.WIDTH & Constants.HEIGHT
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Constants.SCREEN_WIDTH = displayMetrics.widthPixels;
	    Constants.SCREEN_HEIGHT = displayMetrics.heightPixels;
	    // make sure that if we have a nav bar that it's added to the width
	    int resourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
	    if (resourceId > 0)
	    	Constants.SCREEN_WIDTH += getResources().getDimensionPixelSize(resourceId);

	    // set up frame
	    Constants.FRAME = Utility.getScaledDimension(new Dimension(2, 1), new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
	    Constants.FRAME.top = 0;
	    Constants.FRAME.left = (Constants.SCREEN_WIDTH / 2) - (Constants.FRAME.width / 2);
	    Constants.FRAME.bottom = Constants.FRAME.height;
	    Constants.FRAME.right = Constants.SCREEN_WIDTH / 2 + (Constants.FRAME.width / 2);

		Constants.APPLICATION_CONTEXT = getApplicationContext();
		Constants.MAC = Utility.getMacAddress();

	    // set up sensor
	    Constants.SENSOR_SERVICE = (SensorManager)getSystemService(SENSOR_SERVICE);

	    //set content view (canvas)
		gamePanel = new GamePanel(this, this);
	    setContentView(gamePanel);

//	    setContentView(R.layout.testing);

	    // bt setup
	    bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (bluetoothAdapter == null) {
			Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
			this.finish();
		}

		i = new Intent();


	    BluetoothSocket bs;

    }

    @Override
    public void onDestroy() {
	    super.onDestroy();
	    if(mChatService != null) mChatService.stop();
    }

    @Override
    public void onResume() {
	    super.onResume();
	    if(mChatService != null && mChatService.getState() == BluetoothServices.STATE_NONE) mChatService.start();
    }

    @Override
    public void onActivityResult (int reqCode, int resCode, Intent data) {
	    if (reqCode == CODE_BT && resCode != RESULT_CANCELED) {
		    Toast.makeText(getBaseContext(), "Bluetooth turned on", Toast.LENGTH_LONG).show();
		    openList();
	    } else if (reqCode == LIST_CODE && resCode != RESULT_CANCELED) {
		    gamePanel.getMainThread().setRunning(true);
		    int pos = data.getIntExtra("pos", -1);
		    if (pos != -1) {
			    otherAddress = bdl[pos].getAddress();
			    BluetoothDevice device = bluetoothAdapter.getRemoteDevice(bdl[pos].getAddress());
			    boolean goFirst = Utility.compareMacs(Constants.MAC, bdl[pos].getAddress());

			    if (goFirst) {
				    try {
					    BluetoothServerSocket bss = bluetoothAdapter.listenUsingRfcommWithServiceRecord("ByteBuddies", UUID.fromString(Constants._UUID));
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
					    //BluetoothSocket bs = new BluetoothSocket();
					    //bs.connect();
				    } catch (IOException e) {
					    e.printStackTrace();
				    }
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
	    i.setAction(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
	    startActivityForResult(i, CODE_BT);
	    mChatService = new BluetoothServices(this, mHandler);
    }


    private void openList () {
	    pairedDevices = bluetoothAdapter.getBondedDevices();
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

	    gamePanel.getMainThread().setRunning(false);

	    startActivityForResult(in, LIST_CODE);
    }
}