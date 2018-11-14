package frc.team4585.robot;

import edu.wpi.first.wpilibj.Timer;

public class AutoTaskWaitForTime extends AutoTask {

	private Timer _UseTimer;
	private double _WaitSeconds;
	private double _StartTime;
	
	public AutoTaskWaitForTime(double WaitSeconds, Timer UseTimer)
	{
		_UseTimer = UseTimer;
	}
	
	public void StartTask()
	{
		_StartTime = _UseTimer.get();
	}
	
	public boolean DoTask()	// return 'true' if finished
	{
		return ((_UseTimer.get() - _StartTime) >= _WaitSeconds);
	}
	
}
