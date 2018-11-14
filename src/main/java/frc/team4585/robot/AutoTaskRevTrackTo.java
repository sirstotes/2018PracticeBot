package frc.team4585.robot;

public class AutoTaskRevTrackTo  extends AutoTask {

	private static final double CLOSE_ENOUGH_TO_TARG = 1.2;
	private HuskyVector2D _TargetLoc;
	private ChassisControl _TheChassisControl;
	
	public AutoTaskRevTrackTo(double XTarg, double YTarg, ChassisControl TheChassisControl)
	{
		_TheChassisControl = TheChassisControl;
		_TargetLoc = new HuskyVector2D(XTarg, YTarg);
	}
	
	public void StartTask()
	{
		_TheChassisControl.SetTrackToTarget(_TargetLoc);
	}
	
	public boolean DoTask()
	{
		boolean TaskFinished = false;
		HuskyVector2D CurLoc = _TheChassisControl.GetCurrentLocation();
		if (CurLoc.DistanceTo(_TargetLoc) > CLOSE_ENOUGH_TO_TARG)
		{
			_TheChassisControl.DoActionTrackToTargetRev();
		}
		else
		{
			TaskFinished = true;
		}
		
		return TaskFinished;
	}

}
