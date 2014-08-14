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
	public final static int GamePause=10;
	public static final int PlayerSkillLaunch = 11;
	
	
	private static final String Bernuli = "Bernuli";
	private static final String Joule = "Joule";
	private static final String Pascal = "Pascal";
	///////
	//this method does not return the cards to deck ,please put them back;mannually
	//////
	public static void DealPlayCards(Person killer,Person killed,Card killCard,Card DefendCard,ArrayList<Card> d) 
	{
		
		if(killCard.getType()>4)///played a jin nang card
		{
			Card tempCard;
			boolean playedAForce=false;
			boolean makeDemage=false;
			if (killCard==null)
			{
				
			}
			else if ((killCard.getType()==5)&&(DefendCard==null))//The killed tanglement
			{
				tempCard=killed.getAllHandCards().get(0);
				killed.getAllHandCards().remove(0);
				
				d.add(tempCard);//		tempCard
				
				
		
			}
			
		}
		else
		{
			kill(killer, killed, killCard, DefendCard);
			
			
		}
		
		
		
	}
	public static boolean HasOtherCards(Person p)  ////player has other cards except forces
	{
		ArrayList<Card> hand_cards=p.getAllHandCards();
		if(hand_cards.size()==0)
		{
			return false;
			
		}
		else
		{
			for (int i = 0; i < hand_cards.size(); i++) 
			{
				if	(hand_cards.get(i).getType()>4)
				{
					
					return true;
					
				}
			}
			return false;
			
		}
		
		
		
	}
	
	public static boolean ShouldWaitForPlayer(int state,Person p,Person c) {
		if(p.isHasPlayedForce()&&!HasOtherCards(p))
		{
			return false;
			
			
		}
		else {
			return true;
		}
	}
	public static void launchSkill(Person p,ArrayList<Card> d,int index) 
	{
		Card tempCard;
		if(p.getName().equals(Pascal))
		{
			if(index>=0&&index<=p.getAllHandCards().size()-1)
			{
				
			}
			else
			{
				index=0;
				
			}
			
			tempCard=p.removeOneCardAt(index);//(index, p);
			d.add(tempCard);
			
		}
		
	}
	
	public static String GetSkillDescription(Person p) 
	{
		return p.SkillDescription;
	}
	
	public static void ToggleSkillLaunchFlag(Person p) 
	{
		if	(p.MainSkillisLockSkill==false)
		{
				p.setLauchSkill(!p.isLauchSkill());	
		}
		
	}
	
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
			
			
			if(p.getName().equals(Pascal))
			{
//				drawnCard=p.removeOneCardAt(p.getAllHandCards().size()-1);
//				
//				d.add(drawnCard);
				
			}
			
			
			
			
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
		}
		else
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
		boolean makeDemage=false;
		if (KillCard==null)
		{
			
		}
		else if ((KillCard.getType()==0||KillCard.getType()==1||KillCard.getType()==2)&&(DefendCard==null))//The killed's mass is reduced by one
		{
			playedAForce=true;
			killed.setMass(killed.getMass()-1);
			makeDemage=true;
		}
		else if (KillCard.getType()!=Card.antipower &&DefendCard.getType()==Card. antipower)//One is power the other is anti
		{
			playedAForce=true;
		}
		else if(KillCard.getType()!=Card.antipower&&DefendCard.getType()!=Card.antipower)
		{
			playedAForce=true;
			killed.setMass(killed.getMass()-1);
			killed.getOneCard(DefendCard);
			makeDemage=true;
			
			
		}
		else if(KillCard.getType()==Card.antipower)
		{
			
			killer.getOneCard(KillCard);
			
			
		}
		
		
		killer.setHasPlayedForce(playedAForce);
			
		
		
		if(killer.getName().equals("Bernuli"))
		{
				((Bernuli)killer).setIfHasKilled(playedAForce);
		}
		if(killer.getName().equals(Pascal))
		{
			
			if(makeDemage==true&&killer.LauchSkill)
			{
				killed.setMass(killed.getMass()-1);
			}
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
	
	
	
	public static int GameStateUpdate(int CurrentState,Person p,Person c) 
	{
		
		
		int NextState;
		if (CurrentState==PlayerDrawCards) 
		{
			if(p.getName().equals(Pascal))
			{
				NextState=PlayerSkillLaunch;
			}
			else
			{
				NextState=PlayerPlay;	
			}
		}	
		else if (CurrentState==PlayerSkillLaunch) 
		{
			
			NextState=PlayerPlay;
		}
		else if (CurrentState==PlayerPlay) {
			NextState=CpuRespond;
		}else if (CurrentState==CpuRespond) {
			NextState=PUpdateState;
		}else if (CurrentState==PUpdateState) {
			
		if(p.getAllHandCards().size()==0){
			NextState=CpuDrawCards;
		}	
		else if(p.HasPlayedForce&& GameManager.HasOtherCards(p))
			{
				NextState=PlayerPlay;
				
			}
			else if(p.HasPlayedForce==true&&!GameManager.HasOtherCards(p))
			{
				NextState=PlayerDiscard;
				
			}
			else
			{
				
				NextState=PlayerPlay;
				
			}
			
			
			
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
