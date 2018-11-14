package frc.team4585.robot;

public abstract class RobotSensor {
	
	private String _SensorName;
	
	public RobotSensor(String SensorName)
	{
		_SensorName = SensorName;
		
		SensorList.AddSensor(this);
	}
	
	public abstract void UpdateReadings();
	
	public String GetName()
	{
		return _SensorName;
	}
}
