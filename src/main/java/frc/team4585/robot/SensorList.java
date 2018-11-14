package frc.team4585.robot;

import java.util.HashMap;
import java.util.Map;


public class SensorList {

	private static Map<String, RobotSensor> _SensorMap = new HashMap<String, RobotSensor>();
	public static void ReadSensors()
	{
		for(Object SensorKey:  _SensorMap.keySet())
		{
			RobotSensor CurSensor = _SensorMap.get(SensorKey);
			CurSensor.UpdateReadings();
		}
	}
	
	public static void AddSensor(RobotSensor NewSensor)
	{
		_SensorMap.put(NewSensor.GetName(), NewSensor);
	}
	
	public static RobotSensor GetSensor(String SensorName)
	{
		return _SensorMap.containsKey(SensorName) ? _SensorMap.get(SensorName) : null;
	}
}
