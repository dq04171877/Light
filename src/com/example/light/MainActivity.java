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
	private Camera camera = null;
	private Parameters parameters = null;
	private int flagFlashMode=0;
	private EditText mEditText=null;
	private OnClickListener mClickListener = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.torchOnButton:
				parameters = camera.getParameters();
				if (flagFlashMode==0) {
					parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
					camera.setParameters(parameters);
					flagFlashMode=1;
				} else {
					parameters.setFlashMode(Parameters.FLASH_MODE_OFF);// 开启
					camera.setParameters(parameters);
					flagFlashMode=0;
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
		camera = Camera.open();
		mEditText=(EditText)findViewById(R.id.mEditText);
		// 直接开启
		// try {
		// Thread.currentThread();
		// Thread.sleep(500);// 阻断0.5秒
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		mEditText.addTextChangedListener(new TextWatcher(){

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
				// TODO Auto-generated method stub
				
			}});
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
