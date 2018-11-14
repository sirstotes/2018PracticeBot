package frc.team4585.robot;

public class TaskSequenceArm extends TaskSequence {

	private PivotArmControl _TheArmControl;

	public TaskSequenceArm(String SeqName, PivotArmControl TheArmControl)
	{
		super(SeqName);
		_TheArmControl = TheArmControl;
	}

	////////////////////////////////////////////////////////////////////
	
	public void AddTaskRotateArm(double TargAngle)
	{
		AddTask(new AutoTaskArmRotate(TargAngle, _TheArmControl));
	}

	////////////////////////////////////////////////////////////////////

}
