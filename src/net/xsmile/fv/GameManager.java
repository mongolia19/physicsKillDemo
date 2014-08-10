package net.xsmile.fv;

import java.util.ArrayList;

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
	private static final String Bernuli = "Bernuli";
	private static final String Joule = "Joule";
	
	
	public static void giveCardsTo(Person p,ArrayList<Card> d)
	{
		
		if(d.size()>0)
		{
			Card drawnCard;
			drawnCard=d .get(0);
			d.remove(0);
			p.getOneCard(drawnCard);
			
			drawnCard=d .get(0);
			d.remove(0);
			p.getOneCard(drawnCard);
		}
		
		
	}
	
	public static void reduceHandCardsToMass(Person p,ArrayList<Card> d) 
	{
		while(p.getMass()<p.getAllHandCards().size())
		{
			Card 	tempCard;
			
			tempCard= (p.removeOneCardAt(0));
		
			d.add(tempCard);
			
		}
		
	}
	public static boolean CheckIfIsBernuli(Person p)
	{
		if(p.getName().equals("Bernuli"))
		{
			
			return true;
		}else
		{
			return false;
		}
		
	}
	public static boolean CheckBernuliDiscard(Bernuli b )
	{
		return (b.IfHasKilled);
	
		
	}
	
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
		boolean playedAForce=false;
		if (KillCard==null)
		{
			
		}
		else if (KillCard.getType()!=antiPower&&(DefendCard==null))//The killed's mass is reduced by one
		{
			playedAForce=true;
			killed.setMass(killed.getMass()-1);
		}
		else if (KillCard.getType()!=antiPower&&DefendCard.getType()==antiPower)//One is power the other is anti
		{
			playedAForce=true;
		}
		else if(KillCard.getType()!=antiPower&&DefendCard.getType()!=antiPower)
		{
			playedAForce=true;
			killed.setMass(killed.getMass()-1);
			killed.getOneCard(DefendCard);
			
			
		}
		else if(KillCard.getType()==antiPower)
		{
			
			killer.getOneCard(KillCard);
			
			
		}
		
		if(killer.getName().equals("Bernuli"))
		{
				((Bernuli)killer).setIfHasKilled(playedAForce);
		}
		
		
	} 
	public static boolean takeAwayCardsFromPlayerReturnWaitFlag(Person p,ArrayList<Card> d,int index)
	{
		if(index<0||index>d.size()-1)
		{
			
			index=0;
		}
		if (p.getName().equals(Bernuli)) 
		{
			if(((Bernuli)p).IfHasKilled==true)
			{
				reduceHandCardsToMass(p, d);
				
			}
			else
			{
				
				//neednot discard any cards
				
			}
		}
		else if(p.getName().equals(Joule))
		{
			reduceHandCardsToMass(p, d);
			/////the skill of conservation
			while(p.getMass()>p.getAllHandCards().size())
			{
				giveOneCardFromDeck(p, d, index);
			}
			
		}
		else
		{
			
			while(p.getMass()<p.getAllHandCards().size())
			{
				Card 	tempCard;
				
				tempCard= (p.removeOneCardAt(index));
			
				d.add(tempCard);
				
			}
			
		}
		return false;
		
	}
	public static void giveOneCardFromDeck(Person p,ArrayList<Card> d,int index) 
	{
		Card tempCard=d.get(index);
		d.remove(index);
		p.getOneCard(tempCard);
		
	}
	public static boolean takeAwayCardsFromCpuReturnWaitFlag(Person p,ArrayList<Card> d,int index)
	{
		if (p.getName().equals(Bernuli)) 
		{
			if(((Bernuli)p).IfHasKilled==true)
			{
				while(p.getMass()<p.getAllHandCards().size())
				{
					Card 	tempCard;
					
					tempCard= (p.removeOneCardAt(index));
				
					d.add(tempCard);
					
				}
				
			}
			else
			{
				
				//neednot discard any cards
				
			}
		}
		else if(p.getName().equals(Joule))
		{
			
			reduceHandCardsToMass(p, d);
			/////the skill of conservation
			while(p.getMass()>p.getAllHandCards().size())
			{
				giveOneCardFromDeck(p, d, index);
			}
			
		}
		else
		{
			
			while(p.getMass()<p.getAllHandCards().size())
			{
				Card 	tempCard;
				
				tempCard= (p.removeOneCardAt(index));
			
				d.add(tempCard);
				
			}
			
		}
		
		return true;
		
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
