package bytebuddies.corgaday.bytebuddies.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import bytebuddies.corgaday.bytebuddies.R;
import bytebuddies.corgaday.bytebuddies.scenes.SceneManager;
import bytebuddies.corgaday.bytebuddies.util.Constants;

public class PairingList extends Activity {

	private ListView listView;
	private String[] pairs;
	private ArrayAdapter<String> adapter;
	private BluetoothServices mChatService = null;
	private BluetoothAdapter ba;
	public static String EXTRA_DEVICE_ADDRESS = "device_address";
	private String mConnectedDeviceName = null;

	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Activity activity = getParent();
			switch (msg.what) {
				case Constants.MESSAGE_STATE_CHANGE:
					break;
				case Constants.MESSAGE_WRITE:
					byte[] writeBuf = (byte[]) msg.obj;
					// construct a string from the buffer
					String writeMessage = new String(writeBuf);
					break;
				case Constants.MESSAGE_READ:
					byte[] readBuf = (byte[]) msg.obj;
					// construct a string from the valid bytes in the buffer
					String readMessage = new String(readBuf, 0, msg.arg1);
					break;
				case Constants.MESSAGE_DEVICE_NAME:
					// save the connected device's name
					mConnectedDeviceName = msg.getData().getString(Constants.DEVICE_NAME);
					if (null != activity) {
						Toast.makeText(activity, "Connected to "
								+ mConnectedDeviceName, Toast.LENGTH_SHORT).show();
					}
					break;
				case Constants.MESSAGE_TOAST:
					if (null != activity) {
						Toast.makeText(activity, msg.getData().getString(Constants.TOAST),
								Toast.LENGTH_SHORT).show();
					}
					break;
			}
		}
	};

	@Override
	protected void onCreate (@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pairing_list_layout);
		listView = (ListView)findViewById(R.id.listViewId);
		Bundle bn = getIntent().getExtras();
		pairs = bn.getStringArray("pairs");
		mChatService = new BluetoothServices(getParent(), mHandler);

		if (pairs != null) {
			adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>(Arrays.asList(pairs)));
			listView.setAdapter(adapter);
		} else {
			setResult(RESULT_CANCELED);
			finish();
		}

		ba = BluetoothAdapter.getDefaultAdapter();
		Set<BluetoothDevice> pairedDevices = ba.getBondedDevices();
		if(pairedDevices.size() > 0) {
			for(BluetoothDevice device : pairedDevices)
				adapter.add(device.getName() + " @" + device.getAddress());
		}

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
				ba.cancelDiscovery();

				//Get MAC address, which is last 17 characters.
				String info = ((TextView)view).getText().toString();
				String address = info.substring(info.length() - 17);

				Intent intent = new Intent();
				intent.putExtra(EXTRA_DEVICE_ADDRESS, address);
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}

	private void connectDevice(Intent data, boolean secure) {
		String address = data.getExtras().getString(PairingList.EXTRA_DEVICE_ADDRESS);
		BluetoothDevice device = ba.getRemoteDevice(address);
		mChatService.connect(device, secure);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		if(resultCode == Activity.RESULT_OK)
			connectDevice(data, false);

		SceneManager.setScene(SceneManager.GAME_PLAY_SCENE);

	}

}
