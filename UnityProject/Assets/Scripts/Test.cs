using UnityEngine;
using System.Collections;

public class Test : MonoBehaviour {
	public int achievementId = 0;
	public int score = 10;
	void OnGUI () 
	{
		if(GUI.Button(new Rect(10,10,100,50),"Make Toast"))
		{
			AndroidClasses.logAndroid("Unity","Logging in please wait!");
			AndroidClasses.makeToast("Logging in please wait!");
		}
		else if(GUI.Button(new Rect(10,70,125,50),"Show Leaderboard"))
		{
			AndroidClasses.showScoreboard();
		}
		else if(GUI.Button(new Rect(10,130,125,50),"Show Achievements"))
		{
			AndroidClasses.showAchievements();
		}
		else if(GUI.Button(new Rect(10,190,125,50),"Unlock achievement"))
		{
			AndroidClasses.unlockAchievement(achievementId);
		}
		else if(GUI.Button(new Rect(10,250,125,50),"Submit score"))
		{
			AndroidClasses.submitScore(score);
		}

		GUI.Label(new Rect(200,10,100,50),"Score: "+score);
		if(GUI.Button(new Rect(200,70,25,50),"+"))
		{
			score+=10;
		}
		else if(GUI.Button(new Rect(255,70,25,50),"-"))
		{
			score-=10;
		}

		GUI.Label(new Rect(310,10,100,50),"Achievement id: "+achievementId);
		if(GUI.Button(new Rect(310,70,25,50),"+"))
		{
			achievementId += 1;
		}
		else if(GUI.Button(new Rect(365,70,25,50),"-"))
		{
			achievementId -= 1;
		}
	}
	
	
}
