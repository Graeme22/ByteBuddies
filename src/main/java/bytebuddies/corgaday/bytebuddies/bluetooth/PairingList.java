package bytebuddies.corgaday.bytebuddies.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import bytebuddies.corgaday.bytebuddies.R;

public class PairingList extends Activity {

	private ListView listView;
	private String[] pairs;
	private ArrayAdapter<String> adapter;
	private BluetoothAdapter ba;
	public static String EXTRA_DEVICE_ADDRESS = "device_address";

	@Override
	protected void onNewIntent (Intent intent) {
		super.onNewIntent(intent);
	}

	@Override
	protected void onCreate (@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pairing_list_layout);
		listView = (ListView)findViewById(R.id.listViewId);
		Bundle bn = getIntent().getExtras();
		pairs = bn.getStringArray("pairs");

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

}
