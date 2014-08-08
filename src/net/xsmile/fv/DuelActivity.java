
package net.xsmile.fv;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class DuelActivity extends Activity{
	private static final int antipower = 4;
	private static final int gravity = 0;
	private static final int electric = 1;
	private static final int strong = 2;
	private static final int weak = 3;


	/////////
	//
	///////////////
	OnClickListener  cardListener=new OnClickListener() {
    	@Override  
        public void onClick(View v) 
    	{  
    		String tag = v.getTag().toString();
    		ChosenCardIndex=Integer.valueOf(tag);
        }

	
	};
	
	
	
	///////////////////
	ArrayList<Card> Deck;
	Person Cpu;
	Person Player;
	int ChosenCardIndex;
	Card playedCardByPlayer;
	Card playedCardByCpu;
	
	/////
	///below are consts
	///
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
	
	int GameState=GamePause;/////record the game state
	ImageButton HandCardArray[];
	////////UIs
	Button PlayBtn;
	ImageView PlayerDeckCardView;
	ImageView CpuDeckCardView;
	
	/////////////////////
	int Random2N(int n)
	{
		
		 return (int)(Math.random()*n)+1;
	}
	
	/////////////////////
	void UpdateCardUI(ArrayList<Card> hand_cards,ImageButton[] handCardArray)
	{
		for	(int i=0;i<handCardArray.length;i++)
		{
			if(hand_cards.size()-1>=i)
			{
				setPicByCardType( handCardArray[i],hand_cards.get(i) );
				
				
			}
			else
			{
				setPicByCardType( handCardArray[i],null);
			}
			
			
		}
		
	}
	
	void setPicByCardType(View v,Card c)
	{
		if(c==null)
		{
			v.setBackgroundColor(Color.WHITE);
		}
		else if(c.getType()==antipower)
		{
			v.setBackgroundResource(R.drawable.anti);
		}
		else if (c.getType()==gravity) {
			v.setBackgroundResource(R.drawable.gravity);
		}
		else if (c.getType()==electric) {
			v.setBackgroundResource(R.drawable.electric);
		}
		else if (c.getType()==strong) {
			v.setBackgroundResource(R.drawable.strong);
		}
		else if (c.getType()==weak) {
			v.setBackgroundResource(R.drawable.weak);
		}
		
	}
	void UpdateDeckCardsUI(ImageView IV,Card c)
	{
		setPicByCardType(IV, c);
		
		
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
     	Player.getOneCard(TakeOutOneAt(Deck, Random2N(Deck.size()-1)));
     	Player.getOneCard(TakeOutOneAt(Deck, Random2N( Deck.size()-1)));
     	Cpu.getOneCard(TakeOutOneAt(Deck, Random2N(Deck.size()-1)));
     	Cpu.getOneCard(TakeOutOneAt(Deck, Random2N(Deck.size()-1)));
     	
     	HandCardArray=new ImageButton[4];
     	HandCardArray[0]=(ImageButton)findViewById(R.id.ImageButton00);
     	HandCardArray[1]=(ImageButton)findViewById(R.id.ImageButton01);
     	HandCardArray[2]=(ImageButton)findViewById(R.id.ImageButton02);
     	HandCardArray[3]=(ImageButton)findViewById(R.id.ImageButton03);
     	
     	for (int i = 0; i < HandCardArray.length; i++)
     	{
     		HandCardArray[i].setOnClickListener(cardListener);
			
		}
     	
     	PlayerDeckCardView=(ImageView)findViewById(R.id.PlayerCardImageView);
     	CpuDeckCardView=(ImageView)findViewById(R.id.CpuCardImageView);
     	
     	playedCardByCpu=null;
     	playedCardByPlayer=null;
     	
     	
     	
     	UpdateCardUI(Player.getAllHandCards(),HandCardArray);
		
     	PlayBtn=(Button)findViewById(R.id.PlayBtn);
     	
     	
     	GameState=PlayerPlay;//human go first
     	
     	PlayBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (GameState==PlayerPlay)
				{
					
				
					// TODO Auto-generated method stub
					if(ChosenCardIndex>Player.getAllHandCards().size()-1)
					{
						
						
					}
					else
					{
						
						playedCardByPlayer=Player.removeOneCardAt(ChosenCardIndex);
						
						
					}
					/////////////Game State update
					GameState=GameManager.GameStateUpdate(GameState);
					////////////
					//update UI
					UpdateCardUI(Player.getAllHandCards(),HandCardArray);
					
					UpdateDeckCardsUI(PlayerDeckCardView, playedCardByPlayer);
					UpdateDeckCardsUI(CpuDeckCardView, playedCardByCpu);
					
				}
				
				
				if	(GameState==CpuRespond)
				{
					/////AI Logic
					playedCardByCpu=AiDefendLogic(Cpu,playedCardByPlayer);
					GameManager.kill(Player, Cpu, playedCardByPlayer, playedCardByCpu);
					
					
					
					GameState=GameManager.GameStateUpdate(GameState);
					
					
					
					UpdateCardUI(Player.getAllHandCards(),HandCardArray);
					
					UpdateDeckCardsUI(PlayerDeckCardView, playedCardByPlayer);
					UpdateDeckCardsUI(CpuDeckCardView, playedCardByCpu);
			
				}
				
				
			}
		});
     	
     	
		
    }
    
  
    
    protected Card AiDefendLogic(Person p ,Card c) {
		// TODO Auto-generated method stub
    	Card	cardShouldPlay=null;
    	ArrayList<Card> all_hand_cards=p.getAllHandCards();
    	if(c.getType()==gravity||c.getType()==electric||c.getType()==strong)
    	{
			for (int i = 0; i < all_hand_cards.size(); i++)
			{
				if(all_hand_cards.get(i).getType()==antipower)
				{
					
					cardShouldPlay=p.removeOneCardAt(i);
				}
			}
    	}
    	else
    	{
    		
    		
    	}
    	return cardShouldPlay;
    	
	}

	@Override
    public void onDestroy(){
    	super.onDestroy();
    	//在程序退出(Activity销毁）时销毁悬浮窗口
    	
    }
    
   
    
}