package frc.team4585.robot;

public class AutoTaskRotateTo extends AutoTask {

	private CompassHeading _TargetHeading;
	private ChassisControl _TheChassisControl;
	
	public AutoTaskRotateTo(double TargHeading, ChassisControl TheChassisControl)
	{
		_TheChassisControl = TheChassisControl;
		_TargetHeading = new CompassHeading(TargHeading);
	}

	public void StartTask()
	{
		_TheChassisControl.SetRotateToHeading(_TargetHeading);
	}
	
	public boolean DoTask()
	{
		boolean TaskFinished = false;
		CompassHeading CurHead = new CompassHeading(_TheChassisControl.GetCurrentHeading());

		if(!_TargetHeading.EssentiallyEquals(CurHead))
		{
			_TheChassisControl.DoActionRotateToHeading();
		}
		else
		{
			TaskFinished = true;
		}
	
		return TaskFinished;
	}

}
