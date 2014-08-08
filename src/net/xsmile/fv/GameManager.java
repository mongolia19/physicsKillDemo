package net.xsmile.fv;

public static class  GameManager 
{
	final int  PlayerDrawCards=0;
	final int PlayerPlay=1;
	final int PlayerRespond=2;
	final int PUpdateState=3;
	final int PlayerDiscard=4;
	final int  CpuDrawCards=5;
	final int CpuPlay=6;
	final int CpuRespond=7;
	final int CUpdateState=8;
	final int CpuDiscard=9;
	final int GamePause=10;
	public	static void kill(Person killer,Person killed,Card KillCard,Card DefendCard)
	{
		if	(KillCard.getgetType()!=4&&DefendCard.getgetType()==4)//One is power the other is anti
		{
		
		}
		else if(KillCard.getgetType()!=4&&DefendCard.getgetType()!=4)//The killed's mass is reduced by one
		{
			killed.setMass(killed.getMass()-1);
		}
		
		
		
	} 
	public int GameStateUpdate(int CurrentState) 
	{
		int NextState;
		if (CurrentState==PlayerDrawCards) {
			NextState=PlayerPlay;
		}else if (CurrentState==PlayerPlay) {
			NextState=CpuRespond;
		}else if (CurrentState==CpuRespond) {
			NextState=PUpdateState;
		}else if (CurrentState==PUpdateState) {
			NextState=PlayerDiscard;
		}
		else if (CurrentState==PlayerDiscard) {
			NextState=CpuDrawCards;
		}
		else if (CurrentState==CpuDrawCards) {
			NextState=CpuPlay;
		}
		else if (CurrentState==CpuPlay) {
			NextState=PlayerRespond;
		}
		else if (CurrentState==PlayerRespond) {
			NextState=CUpdateState;
		}
		else if (CurrentState==CUpdateState) {
			NextState=CpuDiscard;
		}
		else if (CurrentState==CpuDiscard) {
			NextState=PlayerDrawCards;
		}else {
			return GamePause;
		}
		return NextState;
	}
}
