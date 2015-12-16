package com.eldad.memorygame;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_main);
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
		if (id == R.id.action_new_game) {
			StartNewGame();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void StartNewGame() {
		// TODO Auto-generated method stub
		MainFragment mainFragmentInstance = (MainFragment)getFragmentManager().findFragmentById(R.id.mainFragmentPlaceHolder);
		if (mainFragmentInstance instanceof IHaveSetup){
			IHaveSetup setupObject = (IHaveSetup)mainFragmentInstance;
			setupObject.Setup();
		}
	}
}
