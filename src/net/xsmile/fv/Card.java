package net.xsmile.fv;

public class Card {
	int color=0;//0 spade,1 heart 2 club 3 diamond
	
	
	int Type=0;//0 Gravity 1 electric 2 strong 3 weak 4 antiForce 
	
	
	
	public Card( int c,int t) {
		
		color=c;
		Type=t;
		// TODO Auto-generated constructor stub
	}



	public int getColor() {
		return color;
	}



	public void setColor(int color) {
		this.color = color;
	}



	public int getType() {
		return Type;
	}



	public void setType(int type) {
		Type = type;
	}
	
	
	
}
