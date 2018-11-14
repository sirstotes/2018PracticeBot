package frc.team4585.robot;
import edu.wpi.first.wpilibj.DigitalInput;


public class ContactSensor extends RobotSensor {

	private boolean _IsClosed = false;
	private int _PortNum;
	DigitalInput _Sensor;
	
	
	public ContactSensor(String ContactName, int PortNum)
	{
		super(ContactName);
		_PortNum = PortNum;
		_Sensor = new DigitalInput(_PortNum);
	}
	
	public void UpdateReadings()
	{
		_IsClosed = !(_Sensor.get());
	}
	
	public boolean IsClosed()
	{
		return _IsClosed;
	}
	
}
