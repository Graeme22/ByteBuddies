package bytebuddies.corgaday.bytebuddies.bluetooth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import bytebuddies.corgaday.bytebuddies.R;

public class PairingList extends Activity {

	ListView listView;
	String[] pairs;
	ArrayAdapter<String> adapter;

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
			adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pairs);
			listView.setAdapter(adapter);
		} else {
			setResult(RESULT_CANCELED);
			finish();
		}

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent();
				intent.putExtra("id", (int)id);
				intent.putExtra("pos", position);
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}
}
