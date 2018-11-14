package frc.team4585.robot;

public class PivotArmControl {
	private static final double ARM_ANGLE_PID_PFAC = 0.0065;
	private static final double ARM_ANGLE_PID_IFAC = 0.00015;
	private static final double ARM_ANGLE_PID_DFAC = 0.0700;
	private static final double ARM_ANGLE_MAX_VALUE = 0.55;

	private double _MinAngle = 0.0;
	private double _MaxAngle = 0.0;
	private double _CurrentAngle = 0.0;
	
	private PID _ArmPID = new PID(ARM_ANGLE_PID_PFAC, ARM_ANGLE_PID_IFAC, ARM_ANGLE_PID_DFAC, ARM_ANGLE_MAX_VALUE);

	private double _UseMotorValue = 0.0;
	
	
	public PivotArmControl(double MinAngle, double MaxAngle)
	{
		_MinAngle = MinAngle;
		_MaxAngle = MaxAngle;

		ResetPID();
	}

	
	public double GetTargetAngle()
	{
		return _ArmPID.GetTarget();
	}
	
	public void SetCurrentAngle(double CurAngle)
	{
		_CurrentAngle = CurAngle;
	}
	
	public double GetCurrentAngle()
	{
		return _CurrentAngle;
	}
	
	public void ResetPID()
	{
		_ArmPID.PID_Reset();
	}
	
	public void SetTargetAngle(double TargAngle)
	{
		if(TargAngle > _MaxAngle)
		{
			_ArmPID.SetTarget(_MaxAngle);
		}
		else
		{
			if(TargAngle < _MinAngle)
			{
				_ArmPID.SetTarget(_MinAngle);
			}
			else
			{
				_ArmPID.SetTarget(TargAngle);
			}			
		}
	}
	
	public void MoveUp()
	{
		SetTargetAngle(_MaxAngle);
	}
	
	
	public void MoveDown()
	{
		SetTargetAngle(_MinAngle);
	}
	
	public void Stop()
	{
		SetTargetAngle(_CurrentAngle);
		_ArmPID.PID_Reset();
	}
	
	
	public void DoActionMove()
	{
		_UseMotorValue = _ArmPID.GetControlValue(_CurrentAngle);
		
		/***
		SmartDashboard.putNumber("Piv PID P", _ArmPID.TraceP());
		SmartDashboard.putNumber("Piv PID I", _ArmPID.TraceI());
		SmartDashboard.putNumber("Piv PID D", _ArmPID.TraceD());
		SmartDashboard.putNumber("Piv PID Error", _ArmPID.TraceError());
		SmartDashboard.putNumber("Piv Int Sum", _ArmPID.TraceISum());
		SmartDashboard.putNumber("Piv Delta Error", _ArmPID.TraceDeltaError());
		***/

	}
	
	public double GetArmMotorValue()
	{
		return _UseMotorValue;
	}
	
}
