package net.xsmile.fv;

public class Pascal extends Person{
	
	boolean usePressure=true;
	public Pascal(String name, int health) 
	{
		
		super(name, health);
		Name="Pascal";
		MainSkill="Pressure";
		MainSkillisLockSkill=false;
		SkillDescription="ѹǿ:�����������ƽ׶���������һ�����ƣ�ʹ���ֳ������˺�+1.";
		// TODO Auto-generated constructor stub
	}
	public boolean isUsePressure() {
		return usePressure;
	}
	public void setUsePressure(boolean usePressure) {
		this.usePressure = usePressure;
	}

}
