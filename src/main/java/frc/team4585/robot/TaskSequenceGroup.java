package frc.team4585.robot;
import java.util.ArrayList;
import java.util.List;


public class TaskSequenceGroup {
	private List<TaskSequence> _MySequenceList = new ArrayList<TaskSequence>();
	boolean _AllSequencesCompleted = false;
	String _GroupName = "";
	
	public TaskSequenceGroup(String SequenceGroupName)
	{
		_GroupName = SequenceGroupName;
	}
	
	public void AddSequence(TaskSequence NewSequence)
	{
		_MySequenceList.add(NewSequence);
	}
	
	public String GetGroupName()
	{
		return _GroupName;
	}
	
	public boolean DoTasks()
	{
		if(!_AllSequencesCompleted)
		{
			_AllSequencesCompleted = true;
			for(TaskSequence CurSeq : _MySequenceList)
			{
				_AllSequencesCompleted &= CurSeq.DoCurrentTask();
			}
		}
		
		return _AllSequencesCompleted;
	}
	
	public boolean AllSequencesCompleted()
	{
		return _AllSequencesCompleted;
	}
}
