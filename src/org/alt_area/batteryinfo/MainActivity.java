package org.alt_area.batteryinfo;

import android.os.BatteryManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button button = (Button)findViewById(R.id.bt_getinfo);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				displayInfo();
			}

		});

		displayInfo();
	}

	private void displayInfo() {
		IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		Intent batteryStatus = registerReceiver(null, ifilter);
		int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
		String statusStr;
		switch (status) {
			case BatteryManager.BATTERY_STATUS_CHARGING:
				statusStr = "BATTERY_STATUS_CHARGING";
				break;
			case BatteryManager.BATTERY_STATUS_DISCHARGING:
				statusStr = "BATTERY_STATUS_DISCHARGING";
				break;
			case BatteryManager.BATTERY_STATUS_FULL:
				statusStr = "BATTERY_STATUS_FULL";
				break;
			case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
				statusStr = "BATTERY_STATUS_NOT_CHARGING";
				break;
			case BatteryManager.BATTERY_STATUS_UNKNOWN:
				statusStr = "BATTERY_STATUS_UNKNOWN";
				break;
			default:
				statusStr = "UNDEFINED";
		}
		TextView view = (TextView)findViewById(R.id.info);
		String msgStr = "Status:" + status + "=" + statusStr;
		view.setText(msgStr);
	}
}
