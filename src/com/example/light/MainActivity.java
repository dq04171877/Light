package com.example.light;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private Button torchOnOrOff;
	private EditText mEditText = null;
	private Button send;
	private Camera camera = null;
	private Parameters parameters = null;
	private int flagFlashMode = 0;
	private String textsended;
	private OnClickListener mClickListener = new OnClickListener() {
		public void onClick(View v) {
			parameters = camera.getParameters();
			switch (v.getId()) {
			case R.id.torchOnButton:
				if (flagFlashMode == 0) {
					parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
					camera.setParameters(parameters);
					flagFlashMode = 1;
				} else {
					parameters.setFlashMode(Parameters.FLASH_MODE_OFF);// ¿ªÆô
					camera.setParameters(parameters);
					flagFlashMode = 0;
				}
			case R.id.SendButton:
				char[] b = textsended.toCharArray();
				String c = null;
				char[] d = null;
				for (int i = 0; i < b.length; i++) {
					c = Integer.toBinaryString((int) b[i]);
					d = c.toCharArray();
					for (int j = 0; j < d.length; j++) {
						if (d[j] == '0') {
							try {
								parameters
										.setFlashMode(Parameters.FLASH_MODE_OFF);
								camera.setParameters(parameters);
								Thread.currentThread();
								Thread.sleep(500);// ×è¶Ï0.5Ãë
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} else {
							try {
								parameters
										.setFlashMode(Parameters.FLASH_MODE_TORCH);
								camera.setParameters(parameters);
								Thread.currentThread();
								Thread.sleep(500);// ×è¶Ï0.5Ãë
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

						}
					}

				}

				break;
			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		torchOnOrOff = (Button) findViewById(R.id.torchOnButton);
		torchOnOrOff.setOnClickListener(mClickListener);
		send = (Button) findViewById(R.id.torchOnButton);
		send.setOnClickListener(mClickListener);
		camera = Camera.open();
		mEditText = (EditText) findViewById(R.id.mEditText);
		mEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				textsended = arg0.toString();

			}
		});
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
