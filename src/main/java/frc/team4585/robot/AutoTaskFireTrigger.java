package frc.team4585.robot;

public class AutoTaskFireTrigger extends AutoTask {
	
	private int _TriggerNumber;
	
	public AutoTaskFireTrigger(int TriggerNum)
	{
		_TriggerNumber = TriggerNum;
	}
	
	public void StartTask()
	{
		// fire trigger here
		TriggerTask.DoTrigger(_TriggerNumber);
	}
	
	public boolean DoTask()
	{
		return true;
	}
}
