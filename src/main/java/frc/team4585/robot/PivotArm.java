package frc.team4585.robot;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;

public class PivotArm extends RobotSensor implements HuskySubsystem  {

	private int _SensorPort = 0;
	private int _MotorPort = 0;
	
	private Spark _Motor;
	private double _MotorValue = 0.0;
	
	private AnalogPotentiometer _ArmSensor; 
	
	private double _CurrentAngle = 0.0;

	private double _PreviousUseMotor = 0.0;
	private static final double MAX_MOTOR_CHANGE = 0.1;

	public PivotArm(String Name, int SensorPort, int MotorPort)
	{
		super(Name);
		
		_SensorPort = SensorPort;
		_MotorPort = MotorPort;
		
		_CurrentAngle = 0.0;
		
		_ArmSensor = new AnalogPotentiometer(SensorPort);
		
		_Motor = new Spark(_MotorPort);
	}
	
	public double GetCurrentAngle()
	{
		return _CurrentAngle;
	}
	

	public void SetMotorValue(double MotorValue)
	{
		_MotorValue = MotorValue;
	}
	
	public void UpdateReadings()
	{
		double PotReading = _ArmSensor.get();
		_CurrentAngle += (_MotorValue * 0.5);	// dummy temp junk  read this from the sensor
		SmartDashboard.putNumber("Piv Angle", _CurrentAngle);
		SmartDashboard.putNumber("PotReading", PotReading);
	}

	
	public void autoInit()
	{
		_CurrentAngle = 20.0;	// temp init until we have a sensor
	}
	
	public void doAuto()
	{
		DoAction();
	}
	
	public void teleopInit()
	{
	}
	
	public void doTeleop()
	{
		DoAction();
	}
	
	private void DoAction()
	{
		SmartDashboard.putNumber("Pivot Control", _MotorValue);
		
		double SafeMotor = _MotorValue;
		double DesiredMotorDiff = _PreviousUseMotor - _MotorValue;
		if(Math.abs(DesiredMotorDiff) > MAX_MOTOR_CHANGE)
		{
			if(DesiredMotorDiff > 0.0)
			{
				SafeMotor = _PreviousUseMotor - MAX_MOTOR_CHANGE;
			}
			else
			{
				SafeMotor = _PreviousUseMotor + MAX_MOTOR_CHANGE;
			}
		}

		_Motor.set(_MotorValue);
	}
	
}
