package com.example.light;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button torchOn;
	private Button torchOff;
	private Camera camera = null;
	private Parameters parameters = null;
	private OnClickListener mClickListener = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.torchOnButton:
				parameters = camera.getParameters();
				parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);// 开启
				camera.setParameters(parameters);
				break;
			case R.id.torchOffButton:
				parameters.setFlashMode(Parameters.FLASH_MODE_OFF);
				camera.setParameters(parameters);

			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		torchOn = (Button) findViewById(R.id.torchOnButton);
		torchOn.setOnClickListener(mClickListener);
		torchOff = (Button) findViewById(R.id.torchOffButton);
		torchOff.setOnClickListener(mClickListener);
		camera = Camera.open();
		// 直接开启
		// try {
		// Thread.currentThread();
		// Thread.sleep(500);// 阻断0.5秒
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	protected void onDestroy() {
		super.onDestroy();
		camera.release();
	}
}
