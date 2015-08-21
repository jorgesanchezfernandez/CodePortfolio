using UnityEngine;
using System.Collections;

/// <summary>
/// Camera Controller Script
/// </summary>
public class CameraController : MonoBehaviour 
{
	
	#region 'Configurable properties'
	
	/// <summary>
	/// camera Direction on initialize 
	/// </summary>
	public Transform PointAt;
		
	/// <summary>
	///Distance to PointAt gameObject 
	/// </summary>
	public float DistanceToPointAt = 500.0f;
	
	
	/// <summary>
	///Maximun Speed 
	/// </summary>
	public float MovementSpeedMax = 300.0f; 
	
	/// <summary>
	///Acceleration of the camera 
	/// </summary>	
	public float MovementAcceleration = 50.0f;	
	
	/// <summary>
	///Camera deceleration 
	/// </summary>
	public float MovementDeceleration = 100.0f;
	
	public float HorizontalScrollSize = 500.0f;
	public float VerticalScrollSize = 500.0f;
	
	//Max and Min Camera Position
	public Vector3 ZoomMaxPosition = new Vector3(0.0f, 175.0f, 0.0f);	
	public Vector3 ZoomMinPosition = new Vector3(0.0f, 710.0f, 0.0f);
	
	//Max and Min Camera rotation
	public Vector3 ZoomMaxRotation = new Vector3 (30.0f, 0.0f, 0.0f);	
	public Vector3 ZoomMinRotation = new Vector3 (45.0f, 0.0f, 0.0f);
	
	//Distance to Pilgrim
	public Vector3 DistanceToLookAt = new Vector3(0.0f, 100.0f, -200.0f);
	
	//Zoom movement
	public float ZoomSpeedMax = 0.8f;
	public float ZoomAcceleration = 0.4f;
	public float ZoomDeceleration= 0.6f;
	
	/// <summary>
	///Camera States 
	/// </summary>
	public enum cameraMode{
		Lock,
		Free,
		Follow	
	}
	#endregion
	
	#region 'Private Variables'

	
	/// <summary>
	///Current Camera State 
	/// </summary>
	private cameraMode currentMode;
		
	/// <summary>
	///Camera's current speed 
	/// </summary>
	private float currentSpeedX = 0;	
	private float currentSpeedZ = 0;
	
	//current camera position
	private Vector3 currentMovementPosition; 	
	private Vector3 currentZoomPosition;
	
	//Camera Rotation
	private Quaternion currentZoomRotation;
	private Quaternion _zoomRotationMax;
	private Quaternion _zoomRotationMin;
	
	private float currentZoomSpeed = 0;	
	private float currentZoomValue = 0.5f;
	
	//Is moving?		
	private bool isMovingForward = false; 	
	private bool isMovingBack = false;	
	private bool isMovingRight = false;	
	private bool isMovingLeft = false;
	private bool isZoomingIn = false;
	private bool isZoomingOut = false;
	
	//MovementArea
	private Vector3 areaCenter;
	private Vector3 areaSize;
			
 	private Transform cameraLookAt;
	
	private GameObject TerrainCube;

	#endregion
		
	
	/// <summary>
	/// Start this instance.
	/// </summary>
	void Start () 
	{
		//Gets the cube terrain
		TerrainCube = GameObject.FindGameObjectWithTag("TerrainCube");
		
		//Render everything *except* Hidden POIS layer 
		this.camera.cullingMask = ~(1 << LayerMask.NameToLayer("Hidden POIS"));

		//Initialize camera's values
		currentZoomValue = 0.5f;		
	    currentMovementPosition = new Vector3( 0.0f,0.0f,0.0f);
		
		this._zoomRotationMax = Quaternion.Euler(this.ZoomMaxRotation);
		this._zoomRotationMin = Quaternion.Euler(this.ZoomMinRotation);
		
		//Initialize movement area
		this.areaSize = new Vector3(HorizontalScrollSize, 
		                            this.ZoomMinPosition.y - this.ZoomMaxPosition.y,
		                            VerticalScrollSize);
		
		this.areaCenter = new Vector3(this.transform.position.x,
		                              this.areaSize.y/2 +ZoomMaxPosition.y + TerrainCube.transform.position.y		                              ,
		                              this.transform.position.z);
		
		if (this.PointAt != null)
			currentMovementPosition = new Vector3(this.PointAt.position.x,  areaCenter.y, this.PointAt.transform.position.z - DistanceToPointAt);
		else
			currentMovementPosition = areaCenter;
		
		//Suscribe to events
		
		//briefing GUIS				
		this.StartFreeMode();		
	}
	
	/// <summary>
	/// Main Update
	/// </summary>
	void Update () 
	{
		
		//Get input
		this.checkMovementKeys();
		
		switch (this.currentMode) 
		{
			case CameraController.cameraMode.Free:
			{
				freeCamera();
				break;
			}
			case CameraController.cameraMode.Follow:
			{
				followCamera();
			    break;
			}		
		}		
	
	}
	
	#region Camera Modes
	
	/// <summary>
	///Update free camera mode 
	/// </summary>
	private void freeCamera()
	{					
		//Vector3 currentPosition = this.transform.localPosition;
		float deltaTime = Time.deltaTime;

		
		//Calculate current movement speed		
		calculateCurrentSpeed(deltaTime);
		
		//Calculate current Zoom speed
		calculateZoomSpeed(deltaTime);
		
			
		//Animate movement
		currentMovementPosition = new Vector3(currentMovementPosition.x + currentSpeedX * deltaTime,
		                                      currentMovementPosition.y, 
		                                      currentMovementPosition.z +  currentSpeedZ * deltaTime);
			
		
		//animate zoom		
		this.currentZoomValue += currentZoomSpeed * deltaTime;		
		if (currentZoomValue <0.0f) this.currentZoomValue = 0.0f;
		if (currentZoomValue >1.0f) this.currentZoomValue = 1.0f;
		
		this.currentZoomPosition = Vector3.Lerp(this.ZoomMinPosition, this.ZoomMaxPosition, this.currentZoomValue);
		this.currentZoomRotation =  Quaternion.Lerp(this._zoomRotationMin, this._zoomRotationMax, this.currentZoomValue);
		
		
		//Apply movement + zoom
		
		this.transform.localPosition = new Vector3(currentMovementPosition.x + currentZoomPosition.x, 
		                                      currentMovementPosition.y + currentZoomPosition.y,
		                                      currentMovementPosition.z+ currentZoomPosition.z);
	
		
		//Update position
		this.transform.localRotation = currentZoomRotation;
		
	}
	
	/// <summary>
	/// Update follow camera mode 
	/// </summary>
	private void followCamera()
	{
		//Vector3 currentPosition = this.transform.position; 
		
		if (isMovingBack || isMovingForward || isMovingLeft || isMovingRight)
			setFreeCamera();
		
		this.transform.localPosition = cameraLookAt.position + DistanceToLookAt;
		
	}
	
	/// <summary>
	/// lock camera
	/// </summary>
	private void setLockCamera()
	{
		this.currentMode = CameraController.cameraMode.Lock;		
	}
	
	/// <summary>
	/// lock camera
	/// Free camera
	/// </summary>
	private void setFreeCamera()
	{
		this.currentMode = CameraController.cameraMode.Free;		
	}
	
	/// <summary>
	/// Change camera mode
	/// </summary>
	private void changeMode (cameraMode newMode)
	{
	
		this.currentMode = newMode;
	}
	
	
	/// <summary>
	///Start following the pilgrim 
	/// </summary>
	/// <param name="pilgrim">
	/// A <see cref="Transform"/>
	/// </param>
	public void StartFollingMode(Transform LookAt)
	{
		cameraLookAt = LookAt;
		
		if (currentMode != CameraController.cameraMode.Follow) 
		{
			currentMode = CameraController.cameraMode.Follow;
			
			//Rotate the camera		
			this.transform.rotation = this.currentZoomRotation =  Quaternion.Lerp(this._zoomRotationMin, this._zoomRotationMax, 1);
		}
	}
	
	public void StartFreeMode()
	{
		this.changeMode(cameraMode.Free);
	}
	
	#endregion
	
	/// <summary>
	/// Ises the in camera space.
	/// </summary>
	private bool isInCameraSpace()
	{
		//float currentPosX = this.transform.localPosition.x;
		//float currentPosZ = this.transform.localPosition.z;
		bool canMove = true;
		
		if (this.transform.localPosition.x<= this.areaCenter.x - this.areaSize.x/2) 
		{
			currentSpeedX = 0.0f; 
			canMove =  false;
		}
		if (this.transform.localPosition.z<= this.areaCenter.z - this.areaSize.z/2) 
		{
			currentSpeedZ = 0;
			canMove =  false;
		}
		if (this.transform.localPosition.x >= this.areaCenter.x + this.areaSize.x/2) 
		{
			currentSpeedX = 0;
			canMove =  false;
		}
		if (this.transform.localPosition.z >= this.areaCenter.z + this.areaSize.z/2) 
		{
			currentSpeedZ = 0;
			canMove =  false;
		}
		
		return canMove;
	}
	
	/// <summary>
	///Check Input 
	/// </summary>
	private void checkMovementKeys()
	{	
		this.isMovingForward = Input.GetKey(KeyCode.W);		
		this.isMovingBack = Input.GetKey(KeyCode.S);
		this.isMovingLeft = Input.GetKey(KeyCode.A);
		this.isMovingRight = Input.GetKey(KeyCode.D);
		this.isZoomingIn = Input.GetKey(KeyCode.U);
		this.isZoomingOut = Input.GetKey(KeyCode.J);
	}
	
	/// <summary>
	/// Calculate Zoom Speed
	/// </summary>
	private void calculateZoomSpeed(float deltaTime)
	{
		//Is Zoomming in/out?
		if (!isZoomingIn && !isZoomingOut)
		{
			if (this.currentZoomSpeed >0.0f)
			{
				this.currentZoomSpeed -= this.ZoomDeceleration * deltaTime;
				if (this.currentZoomSpeed < 0.0f) this.currentZoomSpeed = 0.0f;
			}
			if (this.currentZoomSpeed <0.0f)
			{
				this.currentZoomSpeed += this.ZoomDeceleration * deltaTime;
				if (this.currentZoomSpeed > 0.0f) this.currentZoomSpeed = 0.0f;
			}
		}
		else
		{
			if (isZoomingIn) this.currentZoomSpeed += this.ZoomAcceleration * deltaTime;
			if (isZoomingOut) this.currentZoomSpeed -= this.ZoomAcceleration * deltaTime;			
		}
		
		//Clamp Zoom Speed
		if (this.currentZoomSpeed > this.ZoomSpeedMax) this.currentZoomSpeed = this.ZoomSpeedMax;
		if (this.currentZoomSpeed < -this.ZoomSpeedMax) this.currentZoomSpeed = -this.ZoomSpeedMax;
		
	}
	
	/// <summary>
	/// calculate current Speed
	/// </summary>
	private void calculateCurrentSpeed(float deltaTime)
	{
		//Vertical movement
		if (!isMovingForward && !isMovingBack)
		{
			if (this.currentSpeedZ > 0.0f)
			{
				this.currentSpeedZ -= this.MovementDeceleration * deltaTime;
				if (this.currentSpeedZ < 0.0f) this.currentSpeedZ = 0.0f;
			}
			if (this.currentSpeedZ < 0.0f)
			{
				this.currentSpeedZ += this.MovementDeceleration * deltaTime;	
				if (this.currentSpeedZ > 0.0f) this.currentSpeedZ = 0.0f;
			}
		}
		else
		{		
			if (isMovingForward)
			{
				if (this.currentSpeedZ < 0.0f) this.currentSpeedZ = 0.0f;
				this.currentSpeedZ += this.MovementAcceleration * deltaTime;
			}
			if (isMovingBack)
			{
				if (this.currentSpeedZ > 0.0f) this.currentSpeedZ = 0.0f;
				this.currentSpeedZ -= this.MovementAcceleration * deltaTime;
			}
		}
		
		//Horizontal movement
		if (!isMovingLeft && !isMovingRight)
		{
			if (this.currentSpeedX >0.0f)
			{
				this.currentSpeedX -= this.MovementDeceleration * deltaTime;
				if (this.currentSpeedX < 0.0f) this.currentSpeedX = 0.0f;				
			}
			if (this.currentSpeedX <0.0f)
			{
				this.currentSpeedX += this.MovementDeceleration * deltaTime;		
				if (this.currentSpeedX > 0.0f) this.currentSpeedX = 0.0f;
			}
		}
		else
		{		
			if (isMovingLeft)
			{
				if (this.currentSpeedX > 0.0f) this.currentSpeedX = 0.0f;
				this.currentSpeedX -= this.MovementAcceleration * deltaTime;
			}
			if (isMovingRight)
			{
				if (this.currentSpeedX < 0.0f) this.currentSpeedX = 0.0f;
				this.currentSpeedX += this.MovementAcceleration * deltaTime;
			}
		}
		
		//clamp Speed 
		if (currentSpeedX < -MovementSpeedMax) currentSpeedX = -MovementSpeedMax;
		if (currentSpeedX > MovementSpeedMax) currentSpeedX = MovementSpeedMax;
		if (currentSpeedZ < -MovementSpeedMax) currentSpeedZ = -MovementSpeedMax;
		if (currentSpeedZ > MovementSpeedMax) currentSpeedZ = MovementSpeedMax;	

	}
	
  	public cameraMode GetCurrentMode ()
	{
		return currentMode;
	}
	
}