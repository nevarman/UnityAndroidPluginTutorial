using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using UnityEngine;

public class AndroidClasses : MonoBehaviour 
{
	private static AndroidJavaClass jc = new AndroidJavaClass("com.unity3d.player.UnityPlayer"); 
	private static AndroidJavaObject jo = jc.GetStatic<AndroidJavaObject>("currentActivity"); 

	public static void logAndroid(string tag,string message)
	{
		jo.Call("logAndroid",tag,message);
	}
	public static void makeToast(string message)
	{
		jo.Call("makeToast",message);
	}
#region GPGS
	public static void submitScore(int score)
	{
		jo.Call("submitScore", score);
	}
	public static void showScoreboard()
	{
		jo.Call("showScoreboard");
	}
	public static void showAchievements()
	{
		jo.Call("showAchievements");
	}
	public static void unlockAchievement(int id)
	{
		jo.Call("unlockAchievement",id);
	}
#endregion
}
