
package net.xsmile.fv;

import java.util.ArrayList;
import android.app.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

	int GameState=GamePause;/////record the game state
	ImageButton HandCardArray[];
	////////UIs
	Button PlayBtn;
	ImageView PlayerDeckCardView;
	ImageView CpuDeckCardView;
	
	TextView PlayerMassTextView;
	TextView CpuMassTextView;
	
	TextView PlayerCardNumTextView;
	TextView CpuCardNumTextView;
	
	TextView GameStateTextView;
	
	protected boolean WaitForHumanAction=true;
	
	
	/////////////////////
	int Random2N(int n)
	{
		
		 return (int)(Math.random()*n)+1;
	}
	///////////////////
	void UpdateGameStateTextView(TextView tv,int state)
	{
		if(state==PlayerPlay)
		{
			tv.setText("Íæ¼Ò³öÅÆ½×¶Î");
			
		}
		else if(state==PlayerDiscard)
		{
			tv.setText("Íæ¼ÒÆúÅÆ½×¶Î");
			
		}
		else if(state==PlayerRespond)
		{
			tv.setText("Íæ¼ÒÏìÓ¦½×¶Î");
			
		}
		else
		{
			
			tv.setText("¼ÆËã»ú´¦Àí½×¶Î");
		}
		
		
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
	
	
	void UpdateAllUIComponents()
	{
		UpdateCardUI(Player.getAllHandCards(),HandCardArray);
		
		UpdateDeckCardsUI(PlayerDeckCardView, playedCardByPlayer);
		UpdateDeckCardsUI(CpuDeckCardView, playedCardByCpu);
		
		
		PlayerMassTextView.setText(String.valueOf(Player.getMass()));
		CpuMassTextView.setText(String.valueOf(Cpu.getMass()));
		
		PlayerCardNumTextView.setText(String.valueOf(Player.getAllHandCards().size()));
		CpuCardNumTextView.setText(String.valueOf(Cpu.getAllHandCards().size()));
		
		UpdateGameStateTextView(GameStateTextView, GameState);
		
		
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
	
	Card DrawOneCardFromAPerson(int index,Person p)
	{
		Card rCard=null;
		if(index>p.getAllHandCards().size()-1)
		{
			
			
		}
		else
		{
			
			rCard=p.removeOneCardAt(index);
			
			
		}
		return rCard;
		
	}
	
	void PutCardBackToDeck(Card c,ArrayList<Card> d)
	{
		if (c!=null) {
			d.add(c);
		}
		
		
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
     	
     	PlayerMassTextView=(TextView)findViewById(R.id.PlayerMassView);
     	CpuMassTextView=(TextView)findViewById(R.id.CpuMassView);
     	
     	
     	PlayerCardNumTextView=(TextView)findViewById(R.id.PlayerHandCardNumView);
     	CpuCardNumTextView=(TextView)findViewById(R.id.CpuHandCardNumView);
     	
     	GameStateTextView=(TextView)findViewById(R.id.GameStateTextView);
     	
     	for (int i = 0; i < HandCardArray.length; i++)
     	{
     		HandCardArray[i].setOnClickListener(cardListener);
			
		}
     	
     	PlayerDeckCardView=(ImageView)findViewById(R.id.PlayerCardImageView);
     	CpuDeckCardView=(ImageView)findViewById(R.id.CpuCardImageView);
     	
     	playedCardByCpu=null;
     	playedCardByPlayer=null;
     	
     	
     	
     	UpdateAllUIComponents();
		
     	PlayBtn=(Button)findViewById(R.id.PlayBtn);
     	
     	
     	GameState=PlayerPlay;//human go first
     	
     	PlayBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				WaitForHumanAction=!WaitForHumanAction;
				if (GameState==PlayerPlay)
				{
					//Toast.makeText(getApplicationContext(), "Çë³öÅÆ", Toast.LENGTH_SHORT).show();
					
					if(WaitForHumanAction==false)
					{
					
						playedCardByPlayer=	DrawOneCardFromAPerson(ChosenCardIndex, Player);
						// TODO Auto-generated method stub
					
						/////////////Game State update
						GameState=GameManager.GameStateUpdate(GameState);
						////////////
						//update UI
						
					
					}
					UpdateAllUIComponents();
				}
				
				
				if	(GameState==CpuRespond)
				{
					/////AI Logic
					playedCardByCpu=AiDefendLogic(Cpu,playedCardByPlayer);
					////figure out who should lose mass
					GameManager.kill(Player, Cpu, playedCardByPlayer, playedCardByCpu);

					///////////put cards back to deck
					PutCardBackToDeck(playedCardByPlayer, Deck);
					PutCardBackToDeck(playedCardByCpu, Deck);
					
					
					GameState=GameManager.GameStateUpdate(GameState);
					
					
					
					UpdateAllUIComponents();
			
				}
				
				
				if(GameState==PUpdateState)
				{
					GameState=GameManager.GameStateUpdate(GameState);
					
					
					Intent intent=new Intent(DuelActivity.this,MyFloatViewActivity.class);
	   	              
					int winFlag=  GameManager.CheckWhoWin(Player, Cpu);   
					if(winFlag==1)
					{
						
						Toast.makeText(getApplicationContext(), "You Lose!", Toast.LENGTH_SHORT).show();
						startActivity(intent);
					}
					else if(winFlag==2)
					{
						Toast.makeText(getApplicationContext(), "You Win!", Toast.LENGTH_SHORT).show();
						startActivity(intent);
					}
					WaitForHumanAction=true;
					
				}
				if(GameState==PlayerDiscard)
				{
					//Toast.makeText(getApplicationContext(), "ÇëÆúÅÆ", Toast.LENGTH_SHORT).show();

					if(WaitForHumanAction==false)
					{
						
						
						if(Player.getAllHandCards().size()>Player.getMass())
						{
						
							playedCardByPlayer=DrawOneCardFromAPerson(ChosenCardIndex,Player);
						
							PutCardBackToDeck(playedCardByPlayer, Deck);
						
							if(Player.getAllHandCards().size()>Player.getMass())
							{
								
								WaitForHumanAction=true;
								
							}
							else
							{
								GameState=GameManager.GameStateUpdate(GameState);
								
							}
							
						}
						else
						{
							GameState=GameManager.GameStateUpdate(GameState);
							
						}
					
					}
					UpdateAllUIComponents();
				}
				if (GameState==CpuDrawCards) 
				{
					GameState=GameManager.GameStateUpdate(GameState);
					
					Cpu.getOneCard(TakeOutOneAt(Deck, Random2N(Deck.size()-1)));
					Cpu.getOneCard(TakeOutOneAt(Deck, Random2N(Deck.size()-1)));
					
				}
				if (GameState==CpuPlay) 
				{
					GameState=GameManager.GameStateUpdate(GameState);
					playedCardByCpu= AiAttackLogic(Cpu);
					
					UpdateAllUIComponents();
					 WaitForHumanAction=true;
				}
				if(GameState==PlayerRespond)
				{
					//Toast.makeText(getApplicationContext(), "ÇëÏìÓ¦", Toast.LENGTH_SHORT).show();
					if(WaitForHumanAction==false)
					{
						GameState=GameManager.GameStateUpdate(GameState);
						
						playedCardByPlayer=	DrawOneCardFromAPerson(ChosenCardIndex, Player);
						
						GameManager.kill(Cpu, Player, playedCardByCpu,playedCardByPlayer );
						
						///////////put cards back to deck
						PutCardBackToDeck(playedCardByPlayer, Deck);
						PutCardBackToDeck(playedCardByCpu, Deck);
						
						
					}
					UpdateAllUIComponents();
				}
				if (GameState==CUpdateState)
				{
						
					GameState=GameManager.GameStateUpdate(GameState);
				
					

					Intent intent=new Intent(DuelActivity.this,MyFloatViewActivity.class);
	   	              
					int winFlag=  GameManager.CheckWhoWin(Player, Cpu);   
					if(winFlag==1)
					{
						
						Toast.makeText(getApplicationContext(), "You Lose!", Toast.LENGTH_SHORT).show();
						startActivity(intent);
					}
					else if(winFlag==2)
					{
						Toast.makeText(getApplicationContext(), "You Win!", Toast.LENGTH_SHORT).show();
						startActivity(intent);
					}
					
					
				}
				
				if (GameState==CpuDiscard)
				{
						
					GameState=GameManager.GameStateUpdate(GameState);
					
					/////Ai discard logic
					
					while(Cpu.getMass()<Cpu.getAllHandCards().size())
					{
						
						playedCardByCpu= AiDisCardLogic(Cpu);
						PutCardBackToDeck(playedCardByCpu, Deck);
						
						
					}
					
					
				}
				
				if (GameState==PlayerDrawCards)
				{
						
					GameState=GameManager.GameStateUpdate(GameState);
					
					Player.getOneCard(TakeOutOneAt(Deck, Random2N(Deck.size()-1)));
					Player.getOneCard(TakeOutOneAt(Deck, Random2N(Deck.size()-1)));
					
					/////UI update
					UpdateAllUIComponents();
					WaitForHumanAction=true;
					
				}
				
				
			}
		});
     	
     	
		
    }
    
  
    
    protected Card AiDefendLogic(Person p ,Card c) 
    {
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
    
    

    
    protected Card AiAttackLogic(Person p) 
    {
		// TODO Auto-generated method stub
    	Card	cardShouldPlay=null;
    	ArrayList<Card> all_hand_cards=p.getAllHandCards();
    
			for (int i = 0; i < all_hand_cards.size(); i++)
			{
				if(all_hand_cards.get(i).getType()!=antipower)
				{
					
					cardShouldPlay=p.removeOneCardAt(i);
				}
			}
  
    
    	return cardShouldPlay;
    	
	}
    
    
    protected Card AiDisCardLogic(Person p) 
    {
		// TODO Auto-generated method stub
    	Card	cardShouldPlay=null;
    	ArrayList<Card> all_hand_cards=p.getAllHandCards();
    
		if (all_hand_cards.size()>0) {
			cardShouldPlay=p.removeOneCardAt(0);
		}
					
	
			
  
    
    	return cardShouldPlay;
    	
	}
    
	@Override
    public void onDestroy(){
    	super.onDestroy();
    	//ÔÚ³ÌÐòÍË³ö(ActivityÏú»Ù£©Ê±Ïú»ÙÐü¸¡´°¿Ú
    	
    }
    
   
    
}