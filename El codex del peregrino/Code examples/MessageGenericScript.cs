using UnityEngine;
using System.Collections;

public class MessageGenericScript : MonoBehaviour {
		
	#region 'Controls'
	private LevelManagerScript levelManager;
	private BitWindow window;
	private BitButton btnOK;
	private BitLabel lblTitle;
	private BitLabel lblMessage;
	#endregion
	
	public event GameManagerScript.GameEventDelegate Done;
		
	/// <summary>
	/// Initialization
	/// </summary>
	void Start () 
	{			
		//Find Controls
		window = this.transform.GetComponent(typeof(BitWindow)) as BitWindow;	
		btnOK = this.transform.FindChild("btnOk").GetComponent<BitButton>();
		levelManager =  GameObject.FindObjectOfType(typeof(LevelManagerScript)) as LevelManagerScript;
		
		lblTitle = this.transform.FindChild("lblTitle").GetComponent<BitLabel>();
		lblMessage = this.transform.FindChild("lblMessage").GetComponent<BitLabel>();
		
		//Subscribe to events
		btnOK.MouseClick += new Bitverse.Unity.Gui.MouseClickEventHandler(btnOK_MouseClick);	
		
		//Don't show the windows
		this.window.Enabled = false;
		this.window.Visible = false;
		
	}	
	
	/// <summary>
	/// Show the windows
	/// </summary>
	public void Show(string title, string message)
	{
		//Results
		lblTitle.Text = title;
		lblMessage.Text = message;
		
		//Hide the pois labels 
		levelManager.ShowDebugPOILabels = false;
		
		this.window.Enabled = true;
		this.window.Visible = true;
	}
	
	/// <summary>
	///Hide the windows
	/// </summary>
	public void Hide()
	{
		//Show the pois labels 
		levelManager.ShowDebugPOILabels = true;
		
		this.window.Enabled = false;
		this.window.Visible = false;
	}
		
	#region "Button Handlers"		   
	
	/// <summary>
    /// Button Ok
    /// </summary>
    void btnOK_MouseClick(object sender, Bitverse.Unity.Gui.MouseEventArgs e)
    {
		this.Hide();

		//Restart Scene
		if (Done!= null)
		{
			Done();
		}
    }
			
	#endregion
	
}
