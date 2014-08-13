package net.xsmile.fv;

public class Card {
	
	public static final int antipower = 4;
	public static final int gravity = 0;
	public static final int electric = 1;
	public static final int strong = 2;
	public static final int weak = 3;
	public static final int qunitimTanglle=5;
	public static final int GravityCapture=6;
	public static final int timeArrow=7;
	int color=0;//0 spade,1 heart 2 club 3 diamond
	
	
	int Type=0;//0 Gravity 1 electric 2 strong 3 weak 4 antiForce 5 qunitimTanglle 6 GravityCapture 7 timeArrow
	
	
	int Num=1;
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
