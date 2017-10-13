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
import android.widget.ImageView;

import bytebuddies.corgaday.bytebuddies.resources.EHero;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sm;
    private Sensor accelerometer;
    private ImageView iv;
    private Intent intent;
    private DisplayMetrics dm;
    private BluetoothAdapter ba;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accelerometer, sm.SENSOR_DELAY_GAME);
        iv = (ImageView)findViewById(R.id.imageView);
        dm = new DisplayMetrics();
        ba = BluetoothAdapter.getDefaultAdapter();
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
        rotateImage(x, y);
        moveChampion(se.values[0], se.values[1], 4f, 3.08f);
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

    public void rotateImage(float pitch, float roll) {
        float theta = (float)Math.atan2(-pitch, -roll);
        iv.setRotation(270-(int)Math.toDegrees(theta));
    }

    public void moveChampion(/*Player player, */float pitch, float roll, float speed, float sensitivity) {
        if(Math.abs(pitch) + Math.abs(roll) > sensitivity) {
            iv.setTranslationX(iv.getTranslationX() + speed * (pitch > 0 ? -1 : 1) * (Math.abs(pitch) > sensitivity ? 1 : (Math.abs(pitch) / sensitivity)));
            iv.setTranslationY(iv.getTranslationY() + speed * (roll > 0 ? 1 : -1) * (Math.abs(roll) > sensitivity ? 1 : (Math.abs(roll) / sensitivity)));
        }
    }

}