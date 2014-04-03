using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using UnityEngine;

public class AndroidClasses : MonoBehaviour 
{
#if UNITY_ANDROID
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

#endif
}
