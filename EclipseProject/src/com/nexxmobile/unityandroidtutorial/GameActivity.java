package com.nexxmobile.unityandroidtutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import com.google.example.games.basegameutils.GameHelper.GameHelperListener;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

public class GameActivity extends UnityPlayerActivity {
	private Activity _activity;
	/** Since we are inherited from UnityPlayerActivity we can't inherit BaseGameActivity class we will use GameHelper Class for gpgs 
	 * Check out the documentation for further reading @ https://developers.google.com/games/services/android/init
	 */
	GameHelper mHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		_activity = UnityPlayer.currentActivity;

		try {
			setUpGameHelper();
		} catch (Exception e) {
			Log.e("Unity", "Error: " + e.toString());
		}
	}

	/** Your game should then call the GameHelper lifecycle methods from the corresponding activity lifecycle methods */
	@Override
	protected void onStart() {
		super.onStart();
		mHelper.onStart(_activity);
	}

	@Override
	protected void onStop() {
		super.onStop();
		mHelper.onStop();
	}

	@Override
	protected void onActivityResult(int request, int response, Intent data) {
		super.onActivityResult(request, response, data);
		mHelper.onActivityResult(request, response, data);
	}
	/****** ***** ******/
	/** GPGS SETUP */
	private void setUpGameHelper() {

		mHelper = new GameHelper(this, GameHelper.CLIENT_ALL);
		GameHelperListener listener = new GameHelper.GameHelperListener() {
			@Override
			public void onSignInSucceeded() {
				// handle sign-in succeess
				logAndroid("Unity", "Singed in");
			}

			@Override
			public void onSignInFailed() {
			}
		};
		mHelper.setup(listener);
	}

	public void signIn() {
		if (!mHelper.isSignedIn()) {
			// mClient.connect();
			mHelper.beginUserInitiatedSignIn();
		}
	}

	public void signOut() {
		mHelper.signOut();
	}

	public void submitScore(final int score) {
		// if we are signed in submit score to leaderboard 
		if (mHelper.isSignedIn()) {
			Games.Leaderboards.submitScore(mHelper.getApiClient(),
					Constants.GPGS_LEADERBOARD_ID, score);
		}
	}

	public void showScoreboard() {
		// if we are signed in show leaderboard 
		if (mHelper.isSignedIn()) {
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(
					mHelper.getApiClient(), Constants.GPGS_LEADERBOARD_ID),
					9000);
		// else sign in 
		} else {
			signIn();
			// TODO save show leaderboard action, call this method from onSignInSuccess
		}
	}

	public void showAchievements() {
		// if we are signed in show achievements 
		if (mHelper.isSignedIn()) {
			startActivityForResult(
					Games.Achievements.getAchievementsIntent(mHelper
							.getApiClient()), 9001);
		} else {
			signIn();
			// TODO save show achievements action, call this method from onSignInSuccess
		}
	}

	public void unlockAchievement(int id) {
		// if we are signed in unlock achievement
		if (mHelper.isSignedIn()) {
			Games.Achievements.unlock(mHelper.getApiClient(),
					Constants.ACHIVEMENTS[id]);
		}
	}

	/** HELPERS */

	public void makeToast(final String message) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(_activity, message, Toast.LENGTH_SHORT).show();
			}
		});
	}

	public void logAndroid(String tag, String message) {
		Log.d(tag, message);
	}
}
