package frc.team4585.robot;

public class HeadingPID extends PID{

	public HeadingPID(double PFac, double IFac, double DFac, double MaxVal)
	{
		super(PFac, IFac, DFac, MaxVal);
	}
	
	@Override
	public double CalculateError(double CurrentReading){
		return CompassHeading.AngleDifference(GetTarget(), CurrentReading);

	}

}