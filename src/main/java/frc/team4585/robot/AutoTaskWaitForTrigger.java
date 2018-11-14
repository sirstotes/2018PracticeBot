package frc.team4585.robot;

public class AutoTaskWaitForTrigger extends AutoTask implements TriggerTask {

	private int _TargTriggerNumber;
	private boolean _HaveTriggered = false;
	
	public AutoTaskWaitForTrigger(int TriggerNum)
	{
		_TargTriggerNumber = TriggerNum;
		TriggerTask.AddTriggerTask(this);
	}
	
	public void StartTask()
	{
	}
	
	public boolean DoTask()	// return 'true' if finished
	{
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
