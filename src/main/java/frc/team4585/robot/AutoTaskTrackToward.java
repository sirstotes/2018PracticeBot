package frc.team4585.robot;
import frc.team4585.robot.AutoTask;
import frc.team4585.robot.ChassisControl;
import frc.team4585.robot.HuskyVector2D;

public class AutoTaskTrackToward  extends AutoTask {

	private HuskyVector2D _TargetLoc;
	private ChassisControl _TheChassisControl;
	private double _CutoffDistance;
	
	public AutoTaskTrackToward(double XTarg, double YTarg, double CutoffDistance, ChassisControl TheChassisControl)
	{
		_TheChassisControl = TheChassisControl;
		_TargetLoc = new HuskyVector2D(XTarg, YTarg);
		_CutoffDistance = CutoffDistance;
	}
	
	public void StartTask()
	{
		_TheChassisControl.SetTrackToTarget(_TargetLoc);
	}
	
	public boolean DoTask()
	{
		boolean TaskFinished = false;
		HuskyVector2D CurLoc = _TheChassisControl.GetCurrentLocation();
		if (CurLoc.DistanceTo(_TargetLoc) > _CutoffDistance)
		{
			_TheChassisControl.DoActionTrackToTarget();
		}
		else
		{
			TaskFinished = true;
		}
		
		return TaskFinished;
	}

}
