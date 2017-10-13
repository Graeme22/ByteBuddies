package etbcor.bluetoothgame.util;

import android.content.Context;
import android.content.res.Resources;
import android.hardware.SensorManager;

import java.util.UUID;

import etbcor.bluetoothgame.engine.Dimension;

public class Constants {
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    public static Context CURRENT_CONTEXT;
    public static Context APPLICATION_CONTEXT;
	public static String MAC;

	public static String _UUID = "fc3805e5-bff6-4963-bc8b-3d57efe29c81";

    public static Resources RESOURCES;
    public static SensorManager SENSOR_SERVICE;

    public static Dimension FRAME;

	public static final int MESSAGE_STATE_CHANGE = 1;
	public static final int MESSAGE_READ = 2;
	public static final int MESSAGE_WRITE = 3;
	public static final int MESSAGE_DEVICE_NAME = 4;
	public static final int MESSAGE_TOAST = 5;

	// Key names received from the BluetoothChatService Handler
	public static final String DEVICE_NAME = "device_name";
	public static final String TOAST = "toast";


    public static long INIT_TIME;
}
