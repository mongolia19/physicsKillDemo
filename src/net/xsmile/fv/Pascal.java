package net.xsmile.fv;

public class Pascal extends Person{
	
	boolean usePressure=true;
	public Pascal(String name, int health) 
	{
		
		super(name, health);
		Name="Pascal";
		MainSkill="Pressure";
		MainSkillisLockSkill=false;
		SkillDescription="压强:主动技。出牌阶段主动弃掉一张手牌，使本轮出的力伤害+1.";
		// TODO Auto-generated constructor stub
	}
	public boolean isUsePressure() {
		return usePressure;
	}
	public void setUsePressure(boolean usePressure) {
		this.usePressure = usePressure;
	}

}
