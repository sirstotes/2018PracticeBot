package frc.team4585.robot;

public abstract class DistanceSensor extends RobotSensor {

	public DistanceSensor(String Name)
	{
		super(Name);
	}
	
	public abstract double GetDistanceFeet();
}
