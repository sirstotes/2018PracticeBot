package frc.team4585.robot;

public class AutoTaskForwardUntilTrigger extends AutoTask implements TriggerTask {

	private int _TargTriggerNumber;
	private boolean _HaveTriggered = false;
	private double _TargetSpeed;
	ChassisControl _TheChassisControl;

	
	public AutoTaskForwardUntilTrigger(double Speed, int TriggerNum, ChassisControl TheChassisControl)
	{
		_TargTriggerNumber = TriggerNum;
		_TargetSpeed = Speed;
		_TheChassisControl = TheChassisControl;
	}
	
	public void StartTask()
	{
		_TheChassisControl.SetMoveAtSpeed(_TargetSpeed);
	}
	
	public boolean DoTask()	// return 'true' if finished
	{
		_TheChassisControl.DoActionMoveAtSpeed();
		return _HaveTriggered;
	}
	
	public void CheckTrigger(int TriggeredNumber)
	{
		if(_TargTriggerNumber == TriggeredNumber)
		{
			_HaveTriggered = true;
		}
	}
}
