using UnityEngine;
using System.Collections;
using System.Collections.Generic;
using HutongGames.PlayMaker.Actions;
using HutongGames.PlayMaker;

/// <summary>
/// Level (Stage) Manager.
/// Controls things like playing status, level time, etc...
/// </summary>
public class LevelManagerScript : MonoBehaviour 
{	
	#region "Configurable Variables"
	
	/// <summary>
	/// Player's character ID for this level.
	/// Determines which character Diary is used.
	/// </summary>
	public string PlayerCharacterID = "DEFAULT";
			
	#endregion
	
	#region "Private variables"		
	
	//other game objects		
	private MessageGenericScript popupGUI;		

	#endregion
	
	#region "Initialization"
	
	void Awake()
	{							
		
		//Initialize phrases/localization dictionary
		this.loadPhraseTexts();
		
		//initialize player
		this.intializePlayer();								
		
		
		//initialize player
		this.intializePlayer();		
								
		//Register at GameManager
		GameManagerScript.Instance.RegisterLevelManager(this);
		
		//pause game flow
		GameManagerScript.Instance.PauseGameForced(true); 
	}
	
	
	/// <summary>
	/// Late initialization
	/// </summary>
	void Start() 
	{							
		//find dialog controls
		this.initializeDialogControls();
		
		//popup GUI
		this.popupGUI = GameObject.FindObjectOfType(typeof(MessageGenericScript)) as MessageGenericScript;
		if (this.popupGUI!=null)
		{
			this.popupGUI.Done += new GameManagerScript.GameEventDelegate(this.popupGUI_Done);
		}								
		
		//initialize diary
		this.initializeDiary();	
				
		this.initializeFsm();		
	}
	
	/// <summary>
	/// Initialization of dialog controls
	/// </summary>
	private void initializeDialogControls()
	{		
		DialogWindow = GameObject.Find("dialog_window");		
		if(DialogWindow != null)
		{
			dialogBehaviour = this.DialogWindow.GetComponent<DialogBehaviour>();
			dialogBehaviour.DialogEnds += dialogBehaviour_DialogEnd;
		}
	}
	
	/// <summary>
	/// Begin level playing flow.
	/// </summary>
	private void startLevel()
	{
		GameManagerScript.Instance.PauseGameForced(false);
		this.PilgrimController.Enabled = true;
		
		this.levelFsm.Fsm.Event("BEGIN GAME");	
	}		
	
	#endregion					
		
	#region "Utils"
			
	//flag that indicates if the debug labels of POIS should show 
	[HideInInspector]
	[System.NonSerialized]
	public bool ShowDebugPOILabels = false;
	
	[HideInInspector]
	[System.NonSerialized]
	/// <summary>
	/// flag that indicates if the POI buttons should show 
	/// </summary>	
	public bool ShowPOIButtons = true;
	
	#endregion	
	
	#region "Generic Info PopUp"
	
	private GameManagerScript.GameEventDelegate _popupDoneEvent = null;

	/// <summary>
	/// Shows a generic popup message
	/// </summary>
	public void ShowGenericMessage (string title, string message, GameManagerScript.GameEventDelegate popUpDoneEvent)
	{				
		this._popupDoneEvent = popUpDoneEvent;
		if (this.popupGUI!=null)
		{
			this.popupGUI.Show(title,message);
		}
	}

	/// <summary>
	/// Notifies the popup is done to the client code
	/// </summary>
	private void popupGUI_Done()
	{
		if (this._popupDoneEvent!=null) 
		{
			this._popupDoneEvent();
		}
	}
					
	#endregion		
	
	#region "Level Flags"

	/// <summary>
	/// Dictionary to store level flags
	/// </summary>
	private Dictionary<string, bool> levelFlags = new Dictionary<string, bool>();

	/// <summary>
	/// Sets the value of a level flag
	/// </summary>
	public void SetLevelFlag(string FlagId, bool flagValue)
	{
		this.levelFlags[FlagId.ToUpper()] = flagValue;
		
		//Debug
		Debug.Log("Level Variable: " + FlagId + " set to " + flagValue );
	}

	/// <summary>
	/// Gets the value of a level flag. If the flag hasn't been set to any value, returns False
	/// </summary>
	public bool GetLevelFlag(string FlagId)
	{
		bool retVal = false;
		this.levelFlags.TryGetValue(FlagId.ToUpper(), out retVal);
		return retVal;
	}

	#endregion
	
	#region "Diary"

	private GameManagerScript.GameEventDelegate diaryDone = null;
	private DiaryManager diaryManager = null;

	/// <summary>
	/// Initializes diary functionality
	/// </summary>
	private void initializeDiary()
	{	
		//get reference to Diary Manager
		this.diaryManager = this.GetComponent<DiaryManager>() as DiaryManager;
		if (diaryManager != null) this.diaryManager.InitializeDiary( this.PlayerCharacterID /*, this.DiaryContent*/ );
	}
	
	/// <summary>
	/// Adds a diary content block to the diary
	/// </summary>
	public void UnlockDiaryContent(string package, int pageindex)
	{
		this.diaryManager.UnlockDiaryContent(package, pageindex);
	}	
	
	/// <summary>
	/// Adds a diary content block to the diary
	/// </summary>
	public void UnlockDiaryContent(string package, string pageid)
	{
		this.diaryManager.UnlockDiaryContent(package, pageid);
	}

	/// <summary>
	/// Shows up the diary in the game
	/// </summary>
	public void ShowDiary( GameManagerScript.GameEventDelegate diaryDone )
	{
		//save for later notification
		this.diaryDone = diaryDone;
		
		//pause the game
		//this.beforeDiaryPauseState = GameManagerScript.Instance.GamePaused;
		GameManagerScript.Instance.PauseGame(true);

		//display the diary
		this.diaryManager.ShowDiary( this.diaryDone );
	}
	
	/// <summary>
	/// Shows up the diary in the game
	/// </summary>
	public void ShowDiary( GameManagerScript.GameEventDelegate diaryDone, int pageIndex )
	{
		//sets the page before showing the diary
		this.diaryManager.SetDiaryPage( pageIndex );
		this.ShowDiary (diaryDone);		                
	}
	
	/// <summary>
	/// Returns if the diary is currently visible
	/// </summary>
	public bool IsDiaryVisible
	{
		get { return this.diaryManager.IsDiaryVisible; }
	}

	#endregion
	
	#region "Dialogs"
	
	/// <summary>
    /// Object of the level that handles the Play Dialog GUI
    /// </summary>
	public GameObject DialogWindow;
	
	/// <summary>
	/// Behaviour script attached to Dialog Window
	/// </summary>
	private DialogBehaviour dialogBehaviour;
	
	GameManagerScript.GameEventObjDelegate dialogDone;
	
	/// <summary>
    /// Executes a Dialog.
    /// Called from a FSM action
    /// </summary>
    public bool ExecuteDialog(GameManagerScript.GameEventObjDelegate dialogDone, string dialogName, DialogsPackage package)
	{									
		//Store caller action
        //this.playDialogAction = action;
		
		this.dialogDone = dialogDone;
		
		//Start dialog
		dialogBehaviour.CurrentDialog = package.GetDialogByName(dialogName);        
        dialogBehaviour.StartDialog();
        return true;
	}		
	
	#endregion
	
	#region "Phrases/Localization"
	
	/// <summary>
	/// Packages used in the current scene
	/// </summary>
	[SerializeField]
	//private string[] phrasePackages;
	private PhrasesPackage[] phrasePackages;
	
	private Dictionary<string,string> phraseTexts = new Dictionary<string, string>();
	
	private void loadPhraseTexts()
	{
		this.phraseTexts.Clear();
		
		for(int i=0; i < phrasePackages.Length; i++)
		{			
			this.loadPackage(phrasePackages[i]);
			
		}
	}
	
	/// <summary>
	/// Loads the given localization package to he localized texts
	/// </summary>
	private void loadPackage(PhrasesPackage package)
	{
		foreach (var item in package.Phrases) 
		{
			phraseTexts.Add(item.ID, item.Text);
		}
	}
	
	public bool GetString(string id, out string locString)
	{
		return this.phraseTexts.TryGetValue(id, out locString);
	}
	
	/// <summary>
	/// Notify to play dialog action to finish
	/// </summary>	
	private void dialogBehaviour_DialogEnd(object param)
	{
		if (dialogDone != null) dialogDone(param);
	}	
	
	#endregion
	
	#region "Pilgrim Player"
	
	/// <summary>
	/// Prebaf for pilgrim player
	/// </summary>
	//public GameObject PlayerPrefab;
	
	/// <summary>
	/// PilgrimScript attached to player
	/// </summary>
	private PilgrimScript pilgrimPlayer;
	
	/// <summary>
	/// PilgrimController attached to player
	/// </summary>
	private PilgrimController pilgrimController;
	
	/// <summary>
	/// Public accessor for pilgrimPlayer
	/// </summary>
	public PilgrimScript Player
	{
		get {return this.pilgrimPlayer;}
	}
	
	/// <summary>
	/// Public accessor for pilgrimController
	/// </summary>
	public PilgrimController PilgrimController
	{
		get {return this.pilgrimController;}	
	}
	
	/// <summary>
	/// Instantiation and configuration of the player
	/// </summary>
	private void intializePlayer()
	{		
		GameObject player = GameObject.FindWithTag("Player");						
			
		//Find PilgrimScript
		this.pilgrimPlayer = player.GetComponent(typeof(PilgrimScript)) as PilgrimScript;	
		
		//Find PilgrimController
		this.pilgrimController = player.GetComponent(typeof(PilgrimController)) as PilgrimController;		
	}		
	
	#endregion
	
	#region "FSM Integration"
	
	/// <summary>
	/// Finite state machine object 
	/// </summary>
	private PlayMakerFSM levelFsm = null;
	
	/// <summary>
	/// FSM Initialization 
	/// </summary>
	private void initializeFsm()
	{
		//get the fsm
		this.levelFsm = this.GetComponent<PlayMakerFSM>();
	}		
	
	
	#endregion
	
	/// <summary>
	/// Update this instance
	/// </summary>	
	public void Update()
	{
		if (this.levelFsm!=null)
			if(this.levelFsm.ActiveStateName == "Initialization")
			{
				this.startLevel();
			}
	}
	
	

}
