package frc.team4585.robot;

public class HuskyVector2D {
	
	private double _XVal;
	private double _YVal;
	private double _EqualThreshold = 0.0;
	
	public HuskyVector2D(double InitX, double InitY)
	{
		_XVal = InitX;
		_YVal = InitY;
	}
	
	public HuskyVector2D()
	{
		this(0.0, 0.0);
	}
	
	public HuskyVector2D(HuskyVector2D Orig)
	{
		_XVal = Orig.GetX();
		_YVal = Orig.GetY();
	}
	
	
	public double GetX() { return _XVal; }
	public void SetX(double NewX) { _XVal = NewX; }
	public double GetY() { return _YVal; }
	public void SetY(double NewY) { _YVal = NewY; }
	public double GetEqualThreshold() { return _EqualThreshold; }
	public void SetEqualThreshold(double NewThresh) { _EqualThreshold = NewThresh; }
	
	public void SetVals(double NewX, double NewY)
	{
		SetX(NewX);
		SetY(NewY);
	}
	
	public void SetVals(HuskyVector2D OtherVec)
	{
		SetX(OtherVec.GetX());
		SetY(OtherVec.GetY());
	}
	
	public double GetMagnitude()
	{
		return Math.sqrt((_XVal * _XVal) + (_YVal * _YVal));
	}

	public void AddVec(HuskyVector2D OtherVec)
	{
		SetX(GetX() + OtherVec.GetX());
		SetY(GetY() + OtherVec.GetY());
	}

	public void SubVec(HuskyVector2D OtherVec)
	{
		SetX(GetX() - OtherVec.GetX());
		SetY(GetY() - OtherVec.GetY());
	}
	
	public double DistanceTo(HuskyVector2D OtherVec)
	{
		double DeltaX = OtherVec.GetX() - GetX();
		double DeltaY = OtherVec.GetY() - GetY();
		double RawDist = Math.sqrt((DeltaX * DeltaX) + (DeltaY * DeltaY));
		return (RawDist <= _EqualThreshold) ? 0.0 : RawDist;
	}
	
	public HuskyVector2D VectorTo(HuskyVector2D OtherVec)
	{
		HuskyVector2D WorkVec = new HuskyVector2D(OtherVec);
		WorkVec.SubVec(this);
		return WorkVec;
	}
	
	
	// return the angle from the head of the vector to the head of the other vector
	public double AngleTo(HuskyVector2D OtherVec)
	{
		HuskyVector2D WorkVec = VectorTo(OtherVec);
		return Math.toDegrees(Math.atan2(WorkVec.GetX(), WorkVec.GetY()));		
	}
	

	
}
