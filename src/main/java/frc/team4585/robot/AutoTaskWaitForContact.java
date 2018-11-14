package frc.team4585.robot;

public class AutoTaskWaitForContact extends AutoTask {
	
	private ContactSensor _CheckSensor;
	private boolean _OpenTrigger = false;
	
	public AutoTaskWaitForContact(ContactSensor CheckSensor, boolean OpenTrigger)
	{
		_CheckSensor = CheckSensor;
		_OpenTrigger = OpenTrigger;
	}

	public AutoTaskWaitForContact(ContactSensor CheckSensor)
	{
		_CheckSensor = CheckSensor;
		_OpenTrigger = false;
	}
	
	public void StartTask()
	{
	}
	
	public boolean DoTask()	// return 'true' if finished
	{
		return (_CheckSensor.IsClosed() ^ _OpenTrigger);
	}

}
