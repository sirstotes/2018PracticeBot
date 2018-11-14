package frc.team4585.robot;


public class HuskyVector2DPID extends PID{

	private HuskyVector2D _VecTarg = new HuskyVector2D();
	
	public HuskyVector2DPID(double PFac, double IFac, double DFac, double MaxVal)
	{
		super(PFac, IFac, DFac, MaxVal);
	}
	
	public void SetTargetVec(HuskyVector2D NewTarg) {
		_VecTarg.SetVals(NewTarg);
	}
	
	public HuskyVector2D GetTargetVec()
	{
		return _VecTarg;
	}
	
	public double CalculateError(HuskyVector2D CurrentReading)
	{
		return CurrentReading.DistanceTo(_VecTarg);
	}
	
	public double GetControlValue(HuskyVector2D CurrentReading)
	{
		return super.GetControlValue(CalculateError(CurrentReading));
	}
	
}
