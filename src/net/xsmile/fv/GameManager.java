package net.xsmile.fv;

public class  GameManager 
{
	final static int  PlayerDrawCards=0;
	final static int PlayerPlay=1;
	final static int PlayerRespond=2;
	final static int PUpdateState=3;
	final static int PlayerDiscard=4;
	final static int  CpuDrawCards=5;
	final static int CpuPlay=6;
	final static int CpuRespond=7;
	final static int CUpdateState=8;
	final static int CpuDiscard=9;
	final static int GamePause=10;
	
	final static int antiPower=4;
	
	public static int CheckWhoWin(Person human,Person computer)
	{
		if	(human.getMass()<=0)
		{
			return 1;//cpu wins
		}
		else if(computer.getMass()<=0)
		{
			return 2;//human wins
			
		}
		else
		{
			return 0;// No body wins
			
			
		}
	}
	
	public	static void kill(Person killer,Person killed,Card KillCard,Card DefendCard)
	{
		
		/////////////////////
		////////Should
		//////// add detection to judge if the kill card is a antipower which is not allowed
		////////////////////////
		if (KillCard==null)
		{
			
		}
		else if (KillCard.getType()!=antiPower&&(DefendCard==null))//The killed's mass is reduced by one
		{
			killed.setMass(killed.getMass()-1);
		}
		else if (KillCard.getType()!=antiPower&&DefendCard.getType()==antiPower)//One is power the other is anti
		{
		
		}
		else if(KillCard.getType()!=antiPower&&DefendCard.getType()!=antiPower)
		{
			killed.setMass(killed.getMass()-1);
			
		}
		
		
	} 
	public static int GameStateUpdate(int CurrentState) 
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
