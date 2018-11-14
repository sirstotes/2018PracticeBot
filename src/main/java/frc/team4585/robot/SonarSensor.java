package frc.team4585.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class SonarSensor extends DistanceSensor{

	private static final double CORRECTION_COEFF = 1.125;
	private static final int DEFAULT_SAMPLE_BUFFER_SIZE = 20;

	private static final double INCHES_PER_VOLT = 38.4252176;
	private static final double INCHES_PER_FOOT = 12.0;
	
	private AnalogInput _AnaInput;
	private int _SampleBufferSize;
	
	private double _VoltageReading = 0.0;
	
	
	public SonarSensor(String Name, int PortNum, int SampleBufferSize)
	{
		super(Name);
		_AnaInput = new AnalogInput(PortNum);
		
		SetSampleBufferSize(SampleBufferSize);
	}
	
	
	public SonarSensor(String Name, int PortNum)
	{
		this(Name, PortNum, DEFAULT_SAMPLE_BUFFER_SIZE);
	}

	
	public void SetSampleBufferSize(int BufferSize)
	{
		_SampleBufferSize = BufferSize;
		
		_AnaInput.setAverageBits(_SampleBufferSize);
		_AnaInput.setOversampleBits(_SampleBufferSize);
	}
	
	
	public void UpdateReadings()
	{
		_VoltageReading = _AnaInput.getAverageVoltage() * CORRECTION_COEFF;		
	}
	

	public double GetInches() 
	{
		return _VoltageReading * INCHES_PER_VOLT;
	}

	
	public double GetDistanceFeet()
	{
		return (GetInches() / INCHES_PER_FOOT);
	}
	
}
