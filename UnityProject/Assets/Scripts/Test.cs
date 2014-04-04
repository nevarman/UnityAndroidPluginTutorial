using UnityEngine;
using System.Collections;

public class Test : MonoBehaviour {

	// Use this for initialization
	void OnGUI () 
	{
		if(GUI.Button(new Rect(10,10,100,50),"Make Toast"))
		{
			AndroidClasses.logAndroid("Unity","Logging in please wait!");
			AndroidClasses.makeToast("Logging in please wait!");
		}
	}
	
	
}
