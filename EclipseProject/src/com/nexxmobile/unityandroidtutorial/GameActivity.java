package com.nexxmobile.unityandroidtutorial;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

public class GameActivity extends UnityPlayerActivity {
	private Activity _activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		_activity = UnityPlayer.currentActivity;
	}

	public void makeToast(final String message) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(_activity, message, Toast.LENGTH_SHORT).show();
			}
		});
	}
	public  void logAndroid(String tag,String message)
	{
		Log.d(tag, message);
	}
}
