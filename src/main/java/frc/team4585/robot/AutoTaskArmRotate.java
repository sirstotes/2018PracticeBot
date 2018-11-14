package frc.team4585.robot;

public class AutoTaskArmRotate  extends AutoTask {

	private static final double PIVOT_ANGLE_CLOSE_ENOUGH = 1.5;
	private PivotArmControl _TheArmControl;
	private double _TargAngle;
	
	public AutoTaskArmRotate(double TargAngle, PivotArmControl TheArmControl)
	{
		_TheArmControl = TheArmControl;
		_TargAngle = TargAngle;
	}
	
	public void StartTask()
	{
		_TheArmControl.SetTargetAngle(_TargAngle);
	}
	
	public boolean DoTask()	// return 'true' if finished
	{
		boolean TaskFinished = false;
		double CurrentAngle = _TheArmControl.GetCurrentAngle();
		
		if (Math.abs(_TargAngle - CurrentAngle) <= PIVOT_ANGLE_CLOSE_ENOUGH)
		{
			TaskFinished = true;
		}
		
		return TaskFinished;
	}
}
