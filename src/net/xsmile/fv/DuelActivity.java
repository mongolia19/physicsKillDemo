
package net.xsmile.fv;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class DuelActivity extends Activity{
    ArrayList<Card> Deck;
	Person Cpu;
	Person Player;
	final int  PlayerDrawCards=0;
	final int PlayerPlay=1;
	final int PlayerRespond=2;
	final int UpdateState=3;
	final int PlayerDiscard=4;
	final int  CpuDrawCards=5;
	final int CpuPlay=6;
	final int CpuRespond=7;
	final int CpuDiscard=8;
	final int GamePause=9;
	
	int GameState=GamePause;
	ImageButton HandCardArray[];
	void UpdateCardUI(ArrayList<Card> hand_cards,ImageButton[] handCardArray)
	{
		for	(int i=0;i<handCardArray.length;i++)
		{
			if(hand_cards.size()-1>=i)
			{
				if(hand_cards.get(i).getType()==0)
				{
					(handCardArray[i]).setBackgroundResource(R.drawable.gravity);
					
				}
				else
				{
					(handCardArray[i]).setBackgroundResource(R.drawable.anti);
					
				}
				
			}
			else
			{
				(handCardArray[i]).setBackgroundColor(Color.WHITE);//(R.drawable.gravity);
			}
			
			
		}
		
	}
	public void initDeck()
	{
		Deck=new ArrayList<Card>();
		Deck.add(new Card(0, 0));
		Deck.add(new Card(1, 0));
		Deck.add(new Card(2, 0));
		Deck.add(new Card(3, 0));
		Deck.add(new Card(0, 1));
		Deck.add(new Card(1, 1));
		Deck.add(new Card(2, 1));
		Deck.add(new Card(3, 1));
		Deck.add(new Card(0, 2));
		Deck.add(new Card(1, 2));
		Deck.add(new Card(2, 2));
		Deck.add(new Card(3, 2));
		Deck.add(new Card(0, 4));
		Deck.add(new Card(1, 4));
		Deck.add(new Card(2, 4));
		Deck.add(new Card(3, 4));
	}
	Card TakeOutOneAt(ArrayList<Card> d,int i)
	{
		Card rCard;
		rCard=d.get(i);
		d.remove(i);
		return rCard;
		
	}
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       String n;
        setContentView(R.layout.main);
            
        Intent intent = getIntent();
		//num = intent.getStringExtra("name");
		n = intent.getStringExtra("PersonNum");
     	int	PersonNum=Integer.valueOf(n);
     	
     	Cpu=new Joule("Joule", 3);
     	Player=new Newton("Newton",3);
     	
     	initDeck();
     	////////////////////
		////DrawCards for every player
		///////////////////
     	Player.getOneCard(TakeOutOneAt(Deck, 0));
     	Player.getOneCard(TakeOutOneAt(Deck,  Deck.size()-1));
     	Cpu.getOneCard(TakeOutOneAt(Deck, 2));
     	Cpu.getOneCard(TakeOutOneAt(Deck, Deck.size()-1));
     	
     	HandCardArray=new ImageButton[4];
     	HandCardArray[0]=(ImageButton)findViewById(R.id.ImageButton00);
     	HandCardArray[1]=(ImageButton)findViewById(R.id.ImageButton01);
     	HandCardArray[2]=(ImageButton)findViewById(R.id.ImageButton02);
     	HandCardArray[3]=(ImageButton)findViewById(R.id.ImageButton03);
     	UpdateCardUI(Deck,HandCardArray);
		
		
    }
    
  
    
    @Override
    public void onDestroy(){
    	super.onDestroy();
    	//在程序退出(Activity销毁）时销毁悬浮窗口
    	
    }
    
   
    
}