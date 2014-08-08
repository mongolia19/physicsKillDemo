package net.xsmile.fv;

import java.util.ArrayList;

public class Person {
	String Name;
	int Mass;
	ArrayList<Card> HandCards;
	public Person(String name,int health)
	{
		Name=name;
		Mass=health;
		HandCards=new ArrayList<Card>();
	}
	
	public void getOneCard(Card c)
	{
		HandCards.add(c);
		
		
		
	}
	public Card removeOneCard()
	{
		Card c=HandCards.get(HandCards.size()-1);
		HandCards.remove(HandCards.size()-1);
		return c;
		
	}
	public Card removeOneCardAt(int i)
	{
		Card c=HandCards.get(i);
		HandCards.remove(i);
		return c;
		
	}
	
	public int getMass()
	{
		
		return Mass;
	}
	public void setMass(int newMass)
	{
		Mass=newMass;
		
		
	}
	
	
	public ArrayList<Card> getAllHandCards()
	{
		
		
		return HandCards;
		
		
	}
	
}
