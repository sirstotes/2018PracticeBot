package frc.team4585.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ChassisControl {

	private static final double CHASSIS_DISTANCE_PID_PFAC = 0.0065;
	private static final double CHASSIS_DISTANCE_PID_IFAC = 0.0035;
	private static final double CHASSIS_DISTANCE_PID_DFAC = 0.0700;
	private static final double CHASSIS_DISTANCE_MAX_VALUE = 0.70;

	private static final double CHASSIS_DIRECTION_PID_PFAC = 0.0032;
	private static final double CHASSIS_DIRECTION_PID_IFAC = 0.0011;
	private static final double CHASSIS_DIRECTION_PID_DFAC = 0.0285;
	private static final double CHASSIS_DIRECTION_MAX_VALUE = 0.60;

	private static final double TRACK_TURN_THRESHOLD = 1.8;

	private static final double CHASSIS_VELOCITY_PID_PFAC = 0.0065;
	private static final double CHASSIS_VELOCITY_PID_IFAC = 0.0035;
	private static final double CHASSIS_VELOCITY_PID_DFAC = 0.0700;
	private static final double CHASSIS_VELOCITY_MAX_VALUE = 0.70;

	private HuskyVector2D _CurrentLocation = new HuskyVector2D();
//	private HuskyVector2D _TargLocation = new HuskyVector2D();

	private CompassHeading _CurHeading = new CompassHeading();
	private CompassHeading _TargHeading = new CompassHeading();
	
	private HuskyVector2DPID _DistancePID = new HuskyVector2DPID(CHASSIS_DISTANCE_PID_PFAC, CHASSIS_DISTANCE_PID_IFAC, CHASSIS_DISTANCE_PID_DFAC, CHASSIS_DISTANCE_MAX_VALUE);
	private HeadingPID _DirectionPID = new HeadingPID(CHASSIS_DIRECTION_PID_PFAC, CHASSIS_DIRECTION_PID_IFAC, CHASSIS_DIRECTION_PID_DFAC, CHASSIS_DIRECTION_MAX_VALUE);

	private PID _SpeedPID = new PID(CHASSIS_VELOCITY_PID_PFAC, CHASSIS_VELOCITY_PID_IFAC, CHASSIS_VELOCITY_PID_DFAC, CHASSIS_VELOCITY_MAX_VALUE);
	private double _CurSpeed = 0.0;
	
	private double _UseTwistVal = 0.0;
	private double _UseSpeedVal = 0.0;
	
	public ChassisControl()
	{
		
	}
	
	public void SetLocationTarget(HuskyVector2D LocTarg)
	{
		_DistancePID.SetTargetVec(LocTarg);
	}
	
	public HuskyVector2D GetLocationTarget()
	{
		return _DistancePID.GetTargetVec();
	}

	public void SetCurrentLocation(double XVal, double YVal)
	{
		_CurrentLocation.SetVals(XVal, YVal);
	}
	
	public void SetCurrentLocation(HuskyVector2D TargLoc)
	{
		_CurrentLocation.SetVals(TargLoc);
	}
	
	public HuskyVector2D GetCurrentLocation()
	{
		return _CurrentLocation;
	}
	
	public void SetCurrentHeading(double HeadingVal)
	{
		_CurHeading.SetValue(HeadingVal);
	}
	
	public void SetCurrentHeading(CompassHeading NewHeading)
	{
		_CurHeading.SetValue(NewHeading);
	}
	
	public double GetCurrentHeading()
	{
		return _CurHeading.GetValue();
	}
	
	public void SetCurrentSpeed(double CurrentSpeed)
	{
		_CurSpeed = CurrentSpeed;
	}

	public double GetUseSpeed()
	{
		return _UseSpeedVal;
	}
	
	public double GetUseTwist()
	{
		return _UseTwistVal;
	}
	
	private void ResetPIDs()
	{
		_DirectionPID.PID_Reset();
		_DistancePID.PID_Reset();
		_SpeedPID.PID_Reset();
	}
	
	
	
	public void SetTrackToTarget(HuskyVector2D LocTarg) 
	{
		SetLocationTarget(LocTarg);
		ResetPIDs();
	}
	
	public void SetTrackToTargetContinue(HuskyVector2D LocTarg) 
	{
		SetLocationTarget(LocTarg);
	}
	
	public void DoActionTrackToTarget()
	{	
		SmartDashboard.putNumber("Targ X", GetLocationTarget().GetX());
		SmartDashboard.putNumber("Targ Y", GetLocationTarget().GetY());

		CalculateDistanceControl();
		
		if(_CurrentLocation.DistanceTo(GetLocationTarget()) > TRACK_TURN_THRESHOLD)
		{
			_TargHeading.SetValue(_CurrentLocation.AngleTo(GetLocationTarget()));
			CalculateHeadingControl();
		}
		else
		{
			_UseTwistVal = 0.0;
		}
	}
	
	private void CalculateDistanceControl()
	{
		_UseSpeedVal = _DistancePID.GetControlValue(_CurrentLocation);

		/***
		SmartDashboard.putNumber("Dist PID P", _DistancePID.TraceP());
		SmartDashboard.putNumber("Dist PID I", _DistancePID.TraceI());
		SmartDashboard.putNumber("Dist PID D", _DistancePID.TraceD());
		SmartDashboard.putNumber("Dist PID Error", _DistancePID.TraceError());
		SmartDashboard.putNumber("Dist Int Sum", _DistancePID.TraceISum());
		SmartDashboard.putNumber("Dist Delta Error", _DistancePID.TraceDeltaError());
		***/
	}
	
	
	public void DoActionTrackToTargetRev()
	{	
		SmartDashboard.putNumber("Targ X", GetLocationTarget().GetX());
		SmartDashboard.putNumber("Targ Y", GetLocationTarget().GetY());

		CalculateDistanceControl();
		_UseSpeedVal = -_UseSpeedVal;
		
		if(_CurrentLocation.DistanceTo(GetLocationTarget()) > TRACK_TURN_THRESHOLD)
		{
			_TargHeading.SetValue(_CurrentLocation.AngleTo(GetLocationTarget()) + 180.0);
			CalculateHeadingControl();
		}
		else
		{
			_UseTwistVal = 0.0;
		}
	}
	
	
	
	
	public void SetRotateToHeading(CompassHeading DesiredHeading)
	{
		_TargHeading = DesiredHeading;		
		ResetPIDs();
	}
	
	public void DoActionRotateToHeading()
	{	
		SmartDashboard.putNumber("Targ X", 0.0);
		SmartDashboard.putNumber("Targ Y", 0.0);

		_UseSpeedVal = 0.0;
		CalculateHeadingControl();
	}

	private void CalculateHeadingControl()
	{
		SmartDashboard.putNumber("Target Heading", _TargHeading.GetValue());
		if(_CurHeading.EssentiallyEquals(_TargHeading))
		{
			_UseTwistVal = 0.0;
			_DirectionPID.PID_Reset();
		}
		else
		{
			_DirectionPID.SetTarget(_TargHeading.GetValue());
			_UseTwistVal = _DirectionPID.GetControlValue(_CurHeading.GetValue());
		}
		
		/***
		SmartDashboard.putNumber("Twist PID P", _DirectionPID.TraceP());
		SmartDashboard.putNumber("Twist PID I", _DirectionPID.TraceI());
		SmartDashboard.putNumber("Twist PID D", _DirectionPID.TraceD());
		SmartDashboard.putNumber("Twist PID Error", _DirectionPID.TraceError());
		SmartDashboard.putNumber("Twist Int Sum", _DirectionPID.TraceISum());
		SmartDashboard.putNumber("Twist Delta Error", _DirectionPID.TraceDeltaError());
		***/
	}

	public void SetMoveAtSpeed(double DesiredSpeed)
	{
		_SpeedPID.SetTarget(DesiredSpeed);
		_TargHeading = _CurHeading;
		ResetPIDs();
	}
	
	public void DoActionMoveAtSpeed()
	{	
		CalculateHeadingControl();
		_UseSpeedVal = _SpeedPID.GetControlValue(_CurSpeed);
	}


	public void SlowToStop()
	{
		_SpeedPID.SetTarget(0.0);	
	}
	
	//===========================================================
	
}
