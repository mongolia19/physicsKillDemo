
package net.xsmile.fv;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

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
	Button HandCardArray[];
	void UpdateCardUI(ArrayList<Card> hand_cards,Button [] hand_cards_UI)
	{
		for	(int i=0;i<hand_cards_UI.length;i++)
		{
			if(hand_cards.size()>=i)
			{
				if(hand_cards.get(i).getType()==0)
				{
					(hand_cards_UI[i]).setBackgroundResource(R.drawable.gravity);
					
				}
				else
				{
					(hand_cards_UI[i]).setBackgroundResource(R.drawable.anti);
					
				}
				
			}else
			{
				
				(hand_cards_UI[i]).setBackgroundColor(Color.WHITE);//(R.drawable.gravity);
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
     	
     	Player.getOneCard(TakeOutOneAt(Deck, 0));
     	Player.getOneCard(TakeOutOneAt(Deck, 0));
     	Cpu.getOneCard(TakeOutOneAt(Deck, 2));
     	Cpu.getOneCard(TakeOutOneAt(Deck, Deck.size()-1));
     	
     	HandCardArray=new Button[4];
     	HandCardArray[0]=(Button)findViewById(R.id.ImageButton00);
     	HandCardArray[1]=(Button)findViewById(R.id.ImageButton01);
     	HandCardArray[2]=(Button)findViewById(R.id.ImageButton02);
     	HandCardArray[3]=(Button)findViewById(R.id.ImageButton03);
     	
    }
    
  
    
    @Override
    public void onDestroy(){
    	super.onDestroy();
    	//在程序退出(Activity销毁）时销毁悬浮窗口
    	
    }
    
   
    
}