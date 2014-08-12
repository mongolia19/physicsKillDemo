
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
    		
    		UpdateAllUIComponents();
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
	private static final String Joule = "Joule";
	private static final String Bernuli = "Bernuli";
	private static final String Pascal = "Pascal";


	int GameState=GamePause;/////record the game state
	boolean SkillValid=false;
	
	ImageButton HandCardArray[];
	////////UIs
	Button PlayBtn;
	Button DeSelectBtn;
	Button SkillButton;
	Button AbandonButton;
	
	ImageView PlayerDeckCardView;
	ImageView CpuDeckCardView;
	
	TextView PlayerMassTextView;
	TextView CpuMassTextView;
	
	TextView PlayerCardNumTextView;
	TextView CpuCardNumTextView;
	
	TextView GameStateTextView;
	
	ImageView PlayerImageView;
	ImageView CpuImageView;
	
	protected boolean WaitForHumanAction=true;
	boolean AbandonFlag=false;
	////////////////////
	void PersonImageSet(Person p,ImageView iv)
	{
		if(p.getName().equals(Bernuli))
		{
			iv.setImageResource(R.drawable.bernuli);
			
		}
		else if(p.getName().equals(Joule))
		{
			iv.setImageResource(R.drawable.joe);
			
		}
		else if (p.getName().equals(Pascal)) {
			iv.setImageResource(R.drawable.pascal);
			
		}
		else
		{
			iv.setImageResource(R.drawable.blank);
		}
		
	}
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
			tv.setText("玩家出牌阶段");
			
		}
		else if(state==PlayerDiscard)
		{
			tv.setText("玩家弃牌阶段");
			
		}
		else if(state==PlayerRespond)
		{
			tv.setText("玩家响应阶段");
			
		}
		else if(state==GameManager.PlayerSkillLaunch)
		{
			tv.setText("是否发动技能 ？");
		}
		else
		{
			
			tv.setText("计算机处理阶段");
		}
		
		
	}
	Person PersonChooseAndInit(Person p,int index)
	{
		if(index==0){
			
			p=new Joule(Joule,4);
		}
		else if (index==1) 
		{
			p=new Bernuli(Bernuli, 4);
			
		}
		else if (index==5) {
			p=new Pascal(Pascal, 4);
		}
		else
		{
			p=new Person("sic", 4);
			
		}
		return p;
		
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
		if(ChosenCardIndex>=0&&ChosenCardIndex<=handCardArray.length-1)//person selected one card
		{
			
			
			for (int i = 0; i < handCardArray.length; i++) 
			{
				if(i!=ChosenCardIndex)
				{
					
					handCardArray[i].setAlpha(255);
				}
			}
			handCardArray[ChosenCardIndex].setAlpha(150);//.setAlpha(150);
		}
		else
		{
			for (int i = 0; i < handCardArray.length; i++) 
			{
				handCardArray[i].setAlpha(255);
			}
			
		}
		
		
	}
	
	void setPicByCardType(ImageButton v,Card c)
	{
		v=(ImageButton)v;
		if(c==null)
		{
			v.setImageResource(R.drawable.blank);
			//v.setBackgroundColor(Color.WHITE);
			
		}
		else if(c.getType()==antipower)
		{
			v.setImageResource(R.drawable.anti);
		}
		else if (c.getType()==gravity) {
			v.setImageResource(R.drawable.gravity);
		}
		else if (c.getType()==electric) {
			v.setImageResource(R.drawable.electric);
		}
		else if (c.getType()==strong) {
			v.setImageResource(R.drawable.strong);
		}
		else if (c.getType()==weak) {
			v.setImageResource(R.drawable.weak);
		}
		
	}
	void UpdateDeckCardsUI(ImageView IV,Card c)
	{
		setDeckCardPicByCardType(IV, c);
		
		
	}
	
	
	private void setDeckCardPicByCardType(ImageView iV, Card c) {
		// TODO Auto-generated method stub
		if(c==null)
		{
			iV.setBackgroundColor(Color.WHITE);
		}
		else if(c.getType()==antipower)
		{
			iV.setImageResource(R.drawable.anti);
		}
		else if (c.getType()==gravity) {
			iV.setImageResource(R.drawable.gravity);
		}
		else if (c.getType()==electric) {
			iV.setImageResource(R.drawable.electric);
		}
		else if (c.getType()==strong) {
			iV.setImageResource(R.drawable.strong);
		}
		else if (c.getType()==weak) {
			iV.setImageResource(R.drawable.weak);
		}
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
		
		
		//////////////skill button UI update
		
		if(Player.isLauchSkill())
		{
			SkillButton.getBackground().setAlpha(150);
		}
		else
		{
			SkillButton.getBackground().setAlpha(255);
			
			
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
		Deck.add(new Card(0, 4));
		Deck.add(new Card(1, 4));
		Deck.add(new Card(2, 4));
		Deck.add(new Card(3, 4));
		Deck.add(new Card(0, 3));
		Deck.add(new Card(1, 3));
		Deck.add(new Card(2, 3));
		Deck.add(new Card(3, 3));
	
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
		if(index>p.getAllHandCards().size()-1||index<0)
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

     	Player=PersonChooseAndInit(Player, PersonNum);
     	if(PersonNum==0)
     	{
     		Cpu=PersonChooseAndInit(Cpu, 5);
    		
    	 
     	}else if (PersonNum==1) {
     		
     		Cpu	=PersonChooseAndInit(Cpu, 5);
		}
     	else {
     		Cpu =PersonChooseAndInit(Cpu, 0);
		}
     	
     	PlayerImageView=(ImageView)findViewById(R.id.PlayerImageView);
     	CpuImageView=(ImageView)findViewById(R.id.CpuImageView);
     	
     	
     	PersonImageSet(Player, PlayerImageView);
     	PersonImageSet(Cpu, CpuImageView);
     	
     	
     	SkillButton=(Button)findViewById(R.id.SkillButton);
     	SkillButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GameManager.ToggleSkillLaunchFlag(Player);
				
				UpdateAllUIComponents();
			}
		});
     	AbandonButton=(Button)findViewById(R.id.AbandonBtn);
     	AbandonButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AbandonFlag=!AbandonFlag;
				//////////////////////Abandon Button UI Update
				
				if(AbandonFlag)
				{
					AbandonButton.getBackground().setAlpha(150);
				}
				else
				{
					AbandonButton.getBackground().setAlpha(255);
				}
				
			}
		});
     	
     	initDeck();
     	////////////////////
		////DrawCards for every player
		///////////////////
     	GameManager.giveCardsTo(Player, Deck);
     	
     	GameManager.giveCardsTo(Cpu, Deck);
     
     	
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
     	DeSelectBtn=(Button)findViewById(R.id.DeSelectBtn);
     	DeSelectBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ChosenCardIndex=-1;
				UpdateAllUIComponents();
			}
		});
     	
     	GameState=GameManager.PlayerSkillLaunch;//human go first
     	
     	PlayBtn.setOnClickListener(new OnClickListener() 
     	{
			
			@Override
			public void onClick(View v) 
			{
				WaitForHumanAction=!WaitForHumanAction;
				
					
					if(GameState==GameManager.PlayerSkillLaunch)
					{
						if(WaitForHumanAction==false)
						{
						
							GameState=GameManager.GameStateUpdate(GameState, Player, Cpu);
							if(Player.isLauchSkill())
							{
								GameManager.launchSkill(Player, Deck, ChosenCardIndex);
								
								
							}
							
							WaitForHumanAction=true;
						}
					}
				
				if (GameState==PlayerPlay)
				{
					//Toast.makeText(getApplicationContext(), "请出牌", Toast.LENGTH_SHORT).show();
					if(AbandonFlag)
					{
						WaitForHumanAction=false;
					}
					else if(AbandonFlag==false&&GameManager.HasOtherCards(Player))
					{
						WaitForHumanAction=true;
						
					}
					else if(Player.HasPlayedForce==true&&GameManager.HasOtherCards(Player))
					{
						WaitForHumanAction=true;
						
					}else if(Player.HasPlayedForce==false)
					{
						
						WaitForHumanAction=true;
						
					}
					else
					{
						
						WaitForHumanAction=false;
					}
					
				//should be a function	
				//	WaitForHumanAction=GameManager.ShouldWaitForPlayer(GameState, Player, Cpu);
					
					if(WaitForHumanAction==false)
					{
						if(ChosenCardIndex>=0&&ChosenCardIndex<=Player.getAllHandCards().size()-1)
						{
					
							playedCardByPlayer=	DrawOneCardFromAPerson(ChosenCardIndex, Player);
						// TODO Auto-generated method stub
						}
						else
						{
							//playedCardByPlayer=	DrawOneCardFromAPerson(0, Player);
						}
						/////////////Game State update
						GameState=GameManager.GameStateUpdate(GameState, Player, Cpu);
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
					
					
					GameState=GameManager.GameStateUpdate(GameState, Player, Cpu);
					
					
					
					UpdateAllUIComponents();
			
				}
				
				
				if(GameState==PUpdateState)
				{
					GameState=GameManager.GameStateUpdate(GameState, Player, Cpu);
					
					
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
					//Toast.makeText(getApplicationContext(), "请弃牌", Toast.LENGTH_SHORT).show();
						
					if(WaitForHumanAction==false)
					{
						GameManager.takeAwayCardsFromPlayerReturnWaitFlag(Player, Deck, ChosenCardIndex);
						
						GameState=GameManager.GameStateUpdate(GameState, Player, Cpu);
						
					
					}
					UpdateAllUIComponents();
				}
				if (GameState==CpuDrawCards) 
				{
					GameState=GameManager.GameStateUpdate(GameState, Player, Cpu);
					GameManager.giveCardsTo(Cpu, Deck);
					//GameManager.giveCardsTo(Cpu, Deck);
				
					
				}
				if (GameState==CpuPlay) 
				{
					GameState=GameManager.GameStateUpdate(GameState, Player, Cpu);
					playedCardByCpu= AiAttackLogic(Cpu);
					
					UpdateAllUIComponents();
					 WaitForHumanAction=true;
				}
				if(GameState==PlayerRespond)
				{
					//Toast.makeText(getApplicationContext(), "请响应", Toast.LENGTH_SHORT).show();
					if(WaitForHumanAction==false)
					{
						GameState=GameManager.GameStateUpdate(GameState, Player, Cpu);
						
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
						
					GameState=GameManager.GameStateUpdate(GameState, Player, Cpu);
				
					

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
						
					GameState=GameManager.GameStateUpdate(GameState, Player, Cpu);
					
					/////Ai discard logic
					GameManager.takeAwayCardsFromCpuReturnWaitFlag(Cpu, Deck, 0);
					
				
					
					
				}
				
				if (GameState==PlayerDrawCards)
				{
						
					GameState=GameManager.GameStateUpdate(GameState, Player, Cpu);
					
					GameManager.giveCardsTo(Player, Deck);
					//GameManager.giveCardsTo(Player, Deck);
//					Player.getOneCard(TakeOutOneAt(Deck, Random2N(Deck.size()-1)));
//					Player.getOneCard(TakeOutOneAt(Deck, Random2N(Deck.size()-1)));
					
					/////UI update
					UpdateAllUIComponents();
					WaitForHumanAction=true;
					
				}
				
				UpdateAllUIComponents();
			}
		});
     	
     	
		
    }
    
  
    
    protected Card AiDefendLogic(Person p ,Card c) 
    {
		// TODO Auto-generated method stub
    	Card	cardShouldPlay=null;
    	ArrayList<Card> all_hand_cards=p.getAllHandCards();
    	if(c==null)
    	{
    		
    	}
    	else
    	{
    		
    	
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
    	//在程序退出(Activity销毁）时销毁悬浮窗口
    	
    }
    
   
    
}