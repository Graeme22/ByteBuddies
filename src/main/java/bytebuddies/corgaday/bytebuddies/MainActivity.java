package bytebuddies.corgaday.bytebuddies;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import bytebuddies.corgaday.bytebuddies.engine.GameThread;
import bytebuddies.corgaday.bytebuddies.player.Player;
import bytebuddies.corgaday.bytebuddies.util.Settings;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Intent intent;
    private DisplayMetrics dm;
    private BluetoothAdapter ba;
    public static Player player;
    private SensorManager sm;
    private Sensor accelerometer;
    private GameThread gt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        player = new Player();
        sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accelerometer, sm.SENSOR_DELAY_GAME);
        dm = new DisplayMetrics();
        ba = BluetoothAdapter.getDefaultAdapter();
        if(ba == null) {
            Toast.makeText(this, "Bluetooth not supported.", Toast.LENGTH_LONG);
            this.finish();
        }
        gt = new GameThread(true);
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        if(ba.isEnabled()) {
            intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, 0);
        }
        intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(intent, 0);
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

    protected void onResume() {
        super.onResume();
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

}