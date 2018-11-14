package frc.team4585.robot;

public abstract class AutoTask {
	
	public AutoTask()
	{
	}
	
	public abstract void StartTask();
	public abstract boolean DoTask();	// return 'true' if finished
}
