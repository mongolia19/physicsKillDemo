package net.xsmile.fv;

import java.util.ArrayList;

public class AiLogics {
	 public static Card AiDefendLogic(Person p ,Card c) 
	    {
			// TODO Auto-generated method stub
	    	Card	cardShouldPlay=null;
	    	ArrayList<Card> all_hand_cards=p.getAllHandCards();
	    	if(c==null)
	    	{
	    		
	    	}
	    	else
	    	{
	    		
	    	
		    	if(c.getType()==Card.gravity||c.getType()==Card.electric||c.getType()==Card.strong)
		    	{
					for (int i = 0; i < all_hand_cards.size(); i++)
					{
						if(all_hand_cards.get(i).getType()==Card.antipower)
						{
							
							cardShouldPlay=p.removeOneCardAt(i);
						}
					}
		    	}
		    	else
		    	{
		    		
		    		
		    	}
	    	}
	    	return cardShouldPlay;
	    	
		}
	    
	    

	    
	 public static Card AiAttackLogic(Person p) 
	    {
			// TODO Auto-generated method stub
	    	Card	cardShouldPlay=null;
	    	ArrayList<Card> all_hand_cards=p.getAllHandCards();
	    
				for (int i = 0; i < all_hand_cards.size(); i++)
				{
					if(all_hand_cards.get(i).getType()!=Card.antipower)
					{
						
						cardShouldPlay=p.removeOneCardAt(i);
					}
				}
	  
	    
	    	return cardShouldPlay;
	    	
		}
	    
	    
	 public static Card AiDisCardLogic(Person p) 
	    {
			// TODO Auto-generated method stub
	    	Card	cardShouldPlay=null;
	    	ArrayList<Card> all_hand_cards=p.getAllHandCards();
	    
			if (all_hand_cards.size()>0) {
				cardShouldPlay=p.removeOneCardAt(0);
			}
						
		
				
	  
	    
	    	return cardShouldPlay;
	    	
		}

}
