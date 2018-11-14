package frc.team4585.robot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface TriggerTask {
	static List<TriggerTask> _TriggerTaskList = new ArrayList<TriggerTask>();

	public static void AddTriggerTask(TriggerTask NewTask)
	{
		_TriggerTaskList.add(NewTask);		
	}
	
	public static void DoTrigger(int TrigNum)
	{
		for(Iterator<TriggerTask> TaskIter = _TriggerTaskList.iterator(); TaskIter.hasNext(); )
		{
			TriggerTask CurTask = TaskIter.next();
			CurTask.CheckTrigger(TrigNum);
		}
	}
	
	public abstract void CheckTrigger(int TriggerNum);
	
}
