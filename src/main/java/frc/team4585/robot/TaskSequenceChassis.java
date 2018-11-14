package frc.team4585.robot;

public class TaskSequenceChassis extends TaskSequence {

	private ChassisControl _TheChassisControl;

	public TaskSequenceChassis(String SeqName, ChassisControl TheChassisControl)
	{
		super(SeqName);
		_TheChassisControl = TheChassisControl;
	}

	////////////////////////////////////////////////////////////////////
	
	public void AddTaskTrackTo(double TargX, double TargY)
	{
		AddTask(new AutoTaskTrackTo(TargX, TargY, _TheChassisControl));
	}
	
	public void AddTaskRotateTo(double TargHeading)
	{
		AddTask(new AutoTaskRotateTo(TargHeading, _TheChassisControl));
	}
	
	public void AddTaskRevTrackTo(double TargX, double TargY)
	{
		AddTask(new AutoTaskRevTrackTo(TargX, TargY, _TheChassisControl));
	}
	
	public void AddTaskTrackToward(double TargX, double TargY, double Cutoff)
	{
		AddTask(new AutoTaskTrackToward(TargX, TargY, Cutoff, _TheChassisControl));
	}
	
	public void AddTaskForwardUntilTrigger(double Speed, int TriggerNum)
	{
		AddTask(new AutoTaskForwardUntilTrigger(Speed, TriggerNum, _TheChassisControl));
	}
	

	////////////////////////////////////////////////////////////////////


}
