package APFINAL;
import java.awt.Graphics;
import java.awt.event.KeyEvent; 
import java.awt.image.BufferedImage;
import java.io.File; 
import java.io.IOException;
import java.util.Timer; 
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.ethanzeigler.jgamegui.JGameGUI;
import com.ethanzeigler.jgamegui.animation.Animation;
import com.ethanzeigler.jgamegui.animation.AnimationRunnable;
import com.ethanzeigler.jgamegui.element.AbstractElement;
import com.ethanzeigler.jgamegui.element.CollidableImageElement;
import com.ethanzeigler.jgamegui.element.ImageElement;
import com.ethanzeigler.jgamegui.element.TextElement;
import com.ethanzeigler.jgamegui.sound.AudioClip;
import com.ethanzeigler.jgamegui.window.Window;
public class GameMain extends JGameGUI
{
	private CardinalDir direction;
	ImageIcon img1, img2, img3, img4, farmPlot1, farmPlot2, backgroundImage, menu, PauseBackground, upgradeImg;
	ImageIcon NorthUp1, NorthUp2, SouthDown1, SouthDown2, Left1, Left2, Right1, Right2;

	ImageIcon NorthUp1Blue, NorthUp2Blue, SouthDown1Blue, SouthDown2Blue, Left1Blue, Left2Blue, Right1Blue, Right2Blue; //upgrade blue imgs
	ImageIcon img1Blue, img2Blue, img3Blue, img4Blue; //upgrade blue imgs

	ImageIcon NorthUpHarv1, NorthUpHarv2, NorthUpHarv3; //harvest upgrade
	ImageIcon sickleDown1, house;
	AudioClip startSound, inGameSound, upgradeSound;

	private ImageElement background, menu1, menu2, upgradeMenu;
	private CollidableImageElement player, enemy;
	private CollidableImageElement  farmPlotEmpty1, farmPlotEmpty2, farmPlotEmpty3, farmPlotEmpty4, houseUpgrade;
	private CollidableImageElement farmPlotHarvest1, farmPlotHarvest2, farmPlotHarvest3, farmPlotHarvest4;
	private TextElement potatoCounter;
	private Window window, startMenu,PauseMenu, upgradeWindow;
	private int windowCounter=0;
	int potatoCount=40, firstTime=0, farmPlotCounter=0, farmWaitTick=4000, enemyCounter=0, potatoFarmed=3;
	private boolean charNull=false, farmFill1=true, farmFill2=false, farmFill3=false, farmFill4=false, playerUpgradeColor=true; //blue is false, red is true for player color
	private boolean harvestUpgrade=false;
	private boolean enemyfarm1=false, enemyfarm2=false, enemyfarm3=false, enemyfarm4=false;
	enum CardinalDir 
	{
		NORTH,
		SOUTH,
		EAST,
		WEST
	}
	public GameMain(int width, int height)
	{
		super(width, height);
	}
	@Override
	protected void onStart(JGameGUI g) 
	{

		startMenu= new Window();
		window = new Window();
		PauseMenu= new Window();
		upgradeWindow=new Window();
		direction = CardinalDir.SOUTH;
		startSound=new AudioClip("APFINAL/startSound2.wav");
		inGameSound=new AudioClip("APFINAL/inGameMusic.wav");
		upgradeSound=new AudioClip("APFINAL/ugradeSound.wav");
		house=JGameGUI.loadImageFromFile("APFINAL/houseUpgrade.png");
		img1 = JGameGUI.loadImageFromFile("APFINAL/Layer 1.png"); //Original South Not Moving red
		img2 = JGameGUI.loadImageFromFile("APFINAL/Layer 2.png"); //Original North Not Moving red
		img3 = JGameGUI.loadImageFromFile("APFINAL/Layer 3.png"); //Original Left Not Moving red
		img4 = JGameGUI.loadImageFromFile("APFINAL/Layer 4.png"); //Original Right Not Moving red

		img1Blue=JGameGUI.loadImageFromFile("APFINAL/Layer1Blue.png"); //blue south
		img2Blue=JGameGUI.loadImageFromFile("APFINAL/Layer2Blue.png"); //blue north
		img3Blue=JGameGUI.loadImageFromFile("APFINAL/Layer3Blue.png"); //blue left
		img4Blue=JGameGUI.loadImageFromFile("APFINAL/Layer4Blue.png"); //blue right
		//North blue
		NorthUp1Blue=JGameGUI.loadImageFromFile("APFINAL/NorthUp1Blue.png");
		NorthUp2Blue=JGameGUI.loadImageFromFile("APFINAL/NorthUp2Blue.png");
		//South blue
		SouthDown1Blue=JGameGUI.loadImageFromFile("APFINAL/SouthDown1Blue.png");
		SouthDown2Blue=JGameGUI.loadImageFromFile("APFINAL/SouthDown2Blue.png");
		//Left blue
		Left1Blue=JGameGUI.loadImageFromFile("APFINAL/Left1Blue.png");
		Left2Blue=JGameGUI.loadImageFromFile("APFINAL/Left2Blue.png");
		//Right blue
		Right1Blue=JGameGUI.loadImageFromFile("APFINAL/Right1Blue.png");
		Right2Blue=JGameGUI.loadImageFromFile("APFINAL/Right2Blue.png");
		potatoCounter= new TextElement(700, 100, 2, "");
		//North
		NorthUp1=JGameGUI.loadImageFromFile("APFINAL/NorthUp1.png");
		NorthUp2=JGameGUI.loadImageFromFile("APFINAL/NorthUp2.png");
		//South
		SouthDown1=JGameGUI.loadImageFromFile("APFINAL/SouthDown1.png");
		SouthDown2=JGameGUI.loadImageFromFile("APFINAL/SouthDown2.png");
		//Left
		Left1=JGameGUI.loadImageFromFile("APFINAL/Left1.png");
		Left2=JGameGUI.loadImageFromFile("APFINAL/Left2.png");
		//Right
		Right1=JGameGUI.loadImageFromFile("APFINAL/Right1.png");
		Right2=JGameGUI.loadImageFromFile("APFINAL/Right2.png");
		//Harvest Animation
		NorthUpHarv1=JGameGUI.loadImageFromFile("APFINAL/HarvestNorth1.png");
		NorthUpHarv2=JGameGUI.loadImageFromFile("APFINAL/HarvestNorth2.png");
		NorthUpHarv3=JGameGUI.loadImageFromFile("APFINAL/HarvestNorth3.png");
		//Farm plots
		farmPlot1=JGameGUI.loadImageFromFile("APFINAL/farmPlotEmpty.png"); //empty
		farmPlot2=JGameGUI.loadImageFromFile("APFINAL/farmPlotHarvest.png"); //harvest

		backgroundImage= JGameGUI.loadImageFromFile("APFINAL/farm12.png"); // main background
		upgradeImg=JGameGUI.loadImageFromFile("APFINAL/upgradeMenu.png");
		menu= JGameGUI.loadImageFromFile("APFINAL/startMenu.png"); // start menu
		PauseBackground= JGameGUI.loadImageFromFile("APFINAL/pauseMenu.png"); //pause menu
		menu2=new ImageElement(PauseBackground, 0, 0, 1);
		menu1=new ImageElement(menu, 0, 0, 1);
		player = new CollidableImageElement(img1, 400, 293.5, 14, 19, 4);//Player
		enemy=new CollidableImageElement(img1, 135.5, 430, 14, 19, 4);//enemy
		//FARM PLOTS
		//1
		farmPlotEmpty1=new CollidableImageElement(farmPlot1, 100, 100, 87, 87, 1); 
		farmPlotHarvest1=new CollidableImageElement(farmPlot2, 100, 100, 87, 87, 1);
		//2
		farmPlotEmpty2=new CollidableImageElement(farmPlot1, 100, 400, 87, 87, 1);
		farmPlotHarvest2=new CollidableImageElement(farmPlot2, 100, 400, 87, 87, 1);
		//3
		farmPlotEmpty3=new CollidableImageElement(farmPlot1, 600, 100, 87, 87, 1);
		farmPlotHarvest3=new CollidableImageElement(farmPlot2, 600, 100, 87, 87, 1);
		//4
		farmPlotEmpty4=new CollidableImageElement(farmPlot1, 600, 400, 87, 87, 1);
		farmPlotHarvest4=new CollidableImageElement(farmPlot2, 600, 400, 87, 87, 1);
		houseUpgrade=new CollidableImageElement(house, 400, 200, 64, 63, 5);
		background=new ImageElement(backgroundImage, 0, 0, 1); //Main Background
		upgradeMenu=new ImageElement(upgradeImg, 0, 0, 1);
		upgradeWindow.addElement(upgradeMenu);
		upgradeWindow.addElement(potatoCounter);
		//initializes Start Menu
		startMenu.addElement(menu1);
		startSound.playUntilStopped();
		g.setWindow(startMenu);
		//Pause Menu
		PauseMenu.addElement(menu2);
		//Main Menu
		window.addElement(background);
		window.addElement(farmPlotHarvest1);
		window.addElement(farmPlotEmpty2);
		window.addElement(farmPlotEmpty3);
		window.addElement(farmPlotEmpty4);
		window.addElement(player);
		window.addElement(enemy);
		window.addElement(houseUpgrade);
		window.addElement(potatoCounter);

	}
	@Override
	public void keyReleased(KeyEvent e) 
	{
		switch(e.getKeyCode())
		{ 
		case KeyEvent.VK_ESCAPE:
			if((charNull==true)&&(enemyCounter==0))
			{
				animationEnemy();
				enemyCounter++;
			}
		case KeyEvent.VK_UP:
			if(charNull==true)
			{
				if(playerUpgradeColor==true)
				{
					player.setAnimation(null);
					player.setImg(img2);
				}
				if(playerUpgradeColor==false)
				{
					player.setAnimation(null);
					player.setImg(img2Blue);
				}

			}
			break;
		case KeyEvent.VK_DOWN:
			if(player.isCollidingWith(houseUpgrade))
				if(charNull==true)
				{
					if(playerUpgradeColor==true)
					{
						player.setAnimation(null);
						player.setImg(img1);
					}
					if(playerUpgradeColor==false)
					{
						player.setAnimation(null);
						player.setImg(img1Blue);
					}
				}
			break;
		case KeyEvent.VK_LEFT:
			if(charNull==true)
			{
				if(playerUpgradeColor==true)
				{
					player.setAnimation(null);
					player.setImg(img3);
				}
				if(playerUpgradeColor==false)
				{
					player.setAnimation(null);
					player.setImg(img3Blue);
				}
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(charNull==true)
			{
				if(playerUpgradeColor==true)
				{
					player.setAnimation(null);
					player.setImg(img4);
				}
				if(playerUpgradeColor==false)
				{
					player.setAnimation(null);
					player.setImg(img4Blue);
				}
			}
			break;
		}
	};
	@Override
	public void keyPressed(KeyEvent e) 
	{
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_UP:
			if(charNull==true)
			{
				direction=CardinalDir.NORTH;
				animationNorth();
				player.moveVertically(-10);
			}
			break;
		case KeyEvent.VK_DOWN:
			if(charNull==true)
			{
				direction=CardinalDir.SOUTH;
				animationSouth();
				player.moveVertically(10);
			}
			break;
		case KeyEvent.VK_LEFT:
			if(charNull==true)
			{
				direction=CardinalDir.WEST;	
				animationWest();
				player.moveHorizontally(-10);
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(charNull==true)
			{
				direction=CardinalDir.EAST;	
				animationEast();
				player.moveHorizontally(10);
			}
			break;
		case KeyEvent.VK_U:
			if(player.isCollidingWith(houseUpgrade))
			{
				if(windowCounter==0)
				{
					setWindow(upgradeWindow);
					potatoCounter.setText(Integer.toString(potatoCount));					
					inGameSound.stop();
					upgradeSound.playUntilStopped();
					windowCounter++;
					charNull=false;
					break;
				}
				else
				{
					potatoCounter.setText(Integer.toString(potatoCount));
					setWindow(window);
					upgradeSound.stop();
					inGameSound.playUntilStopped();
					windowCounter=0;
					charNull=true;
					break;
				}
			}
			break;
		case KeyEvent.VK_1:
			if(getWindow().equals(upgradeWindow))
			{
				if((potatoCount>=11)&&(playerUpgradeColor==true))
				{
					potatoCount=potatoCount-10;
					potatoCounter.setText(Integer.toString(potatoCount));
					farmWaitTick=2000;
					playerUpgradeColor=false;
				}
			}
			break;
		case KeyEvent.VK_2:
			if(getWindow().equals(upgradeWindow))
			{
				if((potatoCount>=21)&&(playerUpgradeColor==false)&&(harvestUpgrade==false))
				{
					potatoCount=potatoCount-20;
					potatoCounter.setText(Integer.toString(potatoCount));
					harvestUpgrade=true;
				}
			}
			break;
		case KeyEvent.VK_ESCAPE:
			if(windowCounter==0)
			{
				potatoCounter.setText(Integer.toString(potatoCount));
				setWindow(window);
				startSound.stop();
				inGameSound.playUntilStopped();
				windowCounter++;
				charNull=true;
				break;
			}
			else
			{
				setWindow(PauseMenu);
				upgradeSound.stop();
				windowCounter=0;
				charNull=false;
				break;
			}
		case KeyEvent.VK_P:
			if(player.isCollidingWith(farmPlotEmpty1))
			{
				if((farmFill1==false)&&(potatoCount>0))
				{
					farmPlotCounter=1;
					potatoCount--;
					potatoCounter.setText(Integer.toString(potatoCount));
					farmWait();
					farmFill1=true;
				}				
				break;
			}
			if(player.isCollidingWith(farmPlotEmpty2))
			{
				if((farmFill2==false)&&(potatoCount>0))
				{
					farmPlotCounter=2;
					potatoCount--;
					potatoCounter.setText(Integer.toString(potatoCount));
					farmWait();
					farmFill2=true;
				}
				break;
			}
			if(player.isCollidingWith(farmPlotEmpty3))
			{
				if((farmFill3==false)&&(potatoCount>0))
				{
					farmPlotCounter=3;
					potatoCount--;
					potatoCounter.setText(Integer.toString(potatoCount));
					farmWait();
					farmFill3=true;
				}
				break;
			}
			if(player.isCollidingWith(farmPlotEmpty4))
			{
				if((farmFill4==false)&&(potatoCount>0))
				{
					farmPlotCounter=4;
					potatoCount--;
					potatoCounter.setText(Integer.toString(potatoCount));
					farmWait();
					farmFill4=true;
				}
				break;
			}
		case KeyEvent.VK_H:
			if(player.isCollidingWith(farmPlotHarvest1))
			{
				if((farmFill1==true)&&(potatoCount>=0))
				{
					if(enemyfarm1==false)
					{
						potatoFarmed=3;
					}
					if(enemyfarm1==true)
					{
						potatoFarmed=2;
					}
					potatoCount=potatoCount+potatoFarmed;
					if(harvestUpgrade==true)
					{
						harvestAnimation();
						potatoFarmed=3;
						potatoCount=potatoCount+potatoFarmed;
						potatoCounter.setText(Integer.toString(potatoCount));
					}	
					potatoCounter.setText(Integer.toString(potatoCount));
					window.removeElement(farmPlotHarvest1);
					window.addElement(farmPlotEmpty1);
					farmFill1=false;
					enemyfarm1=false;
				}
				break;
			}
			if(player.isCollidingWith(farmPlotHarvest2))
			{
				if((farmFill2==true)&&(potatoCount>=0))
				{
					if(enemyfarm2==false)
					{
						potatoFarmed=3;
					}
					if(enemyfarm2==true)
					{
						potatoFarmed=2;
					}
					potatoCount=potatoCount+potatoFarmed;
					if(harvestUpgrade==true)
					{
						harvestAnimation();
						potatoFarmed=3;
						potatoCount=potatoCount+potatoFarmed;
						potatoCounter.setText(Integer.toString(potatoCount));
					}
					potatoCounter.setText(Integer.toString(potatoCount));
					window.removeElement(farmPlotHarvest2);
					window.addElement(farmPlotEmpty2);
					farmFill2=false;
					enemyfarm2=false;
				}
				break;
			}
			if(player.isCollidingWith(farmPlotHarvest3))
			{
				if((farmFill3==true)&&(potatoCount>=0))
				{
					if(enemyfarm3==false)
					{
						potatoFarmed=3;
					}
					if(enemyfarm3==true)
					{
						potatoFarmed=2;
					}
					potatoCount=potatoCount+potatoFarmed;
					if(harvestUpgrade==true)
					{
						harvestAnimation();
						potatoFarmed=3;
						potatoCount=potatoCount+potatoFarmed;
						potatoCounter.setText(Integer.toString(potatoCount));
					}
					potatoCounter.setText(Integer.toString(potatoCount));
					window.removeElement(farmPlotHarvest3);
					window.addElement(farmPlotEmpty3);
					farmFill3=false;
					enemyfarm3=false;
				}
				break;
			}if(player.isCollidingWith(farmPlotHarvest4))
			{
				if((farmFill4==true)&&(potatoCount>=0))
				{
					if(enemyfarm4==false)
					{
						potatoFarmed=3;
					}
					if(enemyfarm4==true)
					{
						potatoFarmed=2;
					}
					potatoCount=potatoCount+potatoFarmed;
					if(harvestUpgrade==true)
					{
						harvestAnimation();
						potatoFarmed=3;
						potatoCount=potatoCount+potatoFarmed;
						potatoCounter.setText(Integer.toString(potatoCount));
					}
					potatoCounter.setText(Integer.toString(potatoCount));
					window.removeElement(farmPlotHarvest4);
					window.addElement(farmPlotEmpty4);
					farmFill4=false;
					enemyfarm4=false;
				}
				break;
			}

		} 
	} 
	public void animationEnemy()
	{
		Animation animationEnemy=new Animation(new AnimationRunnable()
		{
			@Override 
			public void onUpdate(long numOfTicks, AbstractElement element, Animation animation) 
			{
				int tickOfSecond = (int) (numOfTicks%360); 
				if(tickOfSecond%2!=0)
				{
					if(tickOfSecond<61)
					{
						if(enemy.isCollidingWith(farmPlotHarvest1))
						{
							enemyfarm1=true;
						}
						enemy.setImg(NorthUp1);
						enemy.moveVertically(-5);
						enemy.setImg(NorthUp2);
						enemy.moveVertically(-5);
					}
					if((tickOfSecond>65)&&(tickOfSecond<167))
					{
						if(enemy.isCollidingWith(farmPlotHarvest2))
						{
							enemyfarm2=true;
						}
						enemy.setImg(Right1);
						enemy.moveHorizontally(5);
						enemy.setImg(Right2);
						enemy.moveHorizontally(5); 
					}
					if((tickOfSecond>167)&&(tickOfSecond<229))
					{
						if(enemy.isCollidingWith(farmPlotHarvest3))
						{
							enemyfarm3=true;
						}
						enemy.setImg(SouthDown1);
						enemy.moveVertically(5);
						enemy.setImg(SouthDown2);
						enemy.moveVertically(5);
					}
					if((tickOfSecond>229)&&(tickOfSecond<331))
					{
						if(enemy.isCollidingWith(farmPlotHarvest4))
						{
							enemyfarm4=true;
						}
						enemy.setImg(Left1);
						enemy.moveHorizontally(-5);
						enemy.setImg(Left2);
						enemy.moveHorizontally(-5);
					}
				}
			}
		});
		enemy.setAnimation(animationEnemy);
	}
	public void animationNorth() //North animation
	{
		Animation animationNorth=new Animation(new AnimationRunnable()
		{

			@Override
			public void onUpdate(long numOfTicks, AbstractElement element, Animation animation) 
			{

				int tickOfSecond = (int) (numOfTicks%360);
				if(tickOfSecond==1)
				{
					if(playerUpgradeColor==true)
					{
						player.setImg(NorthUp1);
					}
					else
					{
						player.setImg(NorthUp1Blue);
					}
				}
				if(tickOfSecond==3)
				{
					if(playerUpgradeColor==true)
					{
						player.setImg(NorthUp2);
					}
					else
					{
						player.setImg(NorthUp2Blue);
					}
				}
			}
		});
		player.setAnimation(animationNorth);

	}
	public void animationSouth() //South animation
	{
		Animation animationSouth=new Animation(new AnimationRunnable()
		{

			@Override
			public void onUpdate(long numOfTicks, AbstractElement element, Animation animation) 
			{
				int tickOfSecond = (int) (numOfTicks%360);
				if(tickOfSecond==1)
				{
					if(playerUpgradeColor==true)
					{
						player.setImg(SouthDown1);
					}
					else
					{
						player.setImg(SouthDown1Blue);
					}
				}
				if(tickOfSecond==3)
				{
					if(playerUpgradeColor==true)
					{
						player.setImg(SouthDown2);
					}
					else
					{
						player.setImg(SouthDown2Blue);
					}
				}
			}

		});
		player.setAnimation(animationSouth);
	}
	public void animationWest() //West animation
	{
		Animation animationWest=new Animation(new AnimationRunnable()
		{
			@Override
			public void onUpdate(long numOfTicks, AbstractElement element, Animation animation) 
			{
				int tickOfSecond = (int) (numOfTicks%360);
				if(tickOfSecond==1)
				{
					if(playerUpgradeColor==true)
					{
						player.setImg(Left1);
					}
					else
					{
						player.setImg(Left1Blue);
					}
				}
				if(tickOfSecond==3)
				{
					if(playerUpgradeColor==true)
					{
						player.setImg(Left2);
					}
					else
					{
						player.setImg(Left2Blue);
					}
				}
			}
		});
		player.setAnimation(animationWest);
	}
	public void animationEast() //East animation
	{
		Animation animationEast=new Animation(new AnimationRunnable()
		{
			@Override
			public void onUpdate(long numOfTicks, AbstractElement element, Animation animation) 
			{
				int tickOfSecond = (int) (numOfTicks%360);
				if(tickOfSecond==1)
				{
					if(playerUpgradeColor==true)
					{
						player.setImg(Right1);
					}
					else
					{
						player.setImg(Right1Blue);
					}
				}
				if(tickOfSecond==3)
				{
					if(playerUpgradeColor==true)
					{
						player.setImg(Right2);
					}
					else
					{
						player.setImg(Right2Blue);
					}
				}
			}
		});
		player.setAnimation(animationEast);
	}
	public void harvestAnimation()
	{
		Animation animationHarvest=new Animation(new AnimationRunnable()
		{
			@Override
			public void onUpdate(long numOfTicks, AbstractElement element, Animation animation) 
			{
				int tickOfSecond = (int) (numOfTicks%360);
				if(tickOfSecond==5)
				{
					player.setImg(NorthUpHarv1);
				}
				if(tickOfSecond==10)
				{
					player.setImg(NorthUpHarv2);
				}
				if(tickOfSecond==15)
				{
					player.setImg(NorthUpHarv3);
				}
			}
		});
		player.setAnimation(animationHarvest);
	}
	public static void main(String[] args) 
	{
		new GameMain(800, 500);
	}
	public void farmWait()
	{
		new Timer().schedule(new TimerTask() 
		{

			@Override
			public void run() 
			{
				if(farmPlotCounter==1)
				{
					window.removeElement(farmPlotEmpty1);
					window.addElement(farmPlotHarvest1);
					enemyfarm1=false;
					farmPlotCounter=0;
				}
				if(farmPlotCounter==2)
				{
					window.removeElement(farmPlotEmpty2);
					window.addElement(farmPlotHarvest2);
					enemyfarm2=false;
					farmPlotCounter=0;
				}
				if(farmPlotCounter==3)
				{
					window.removeElement(farmPlotEmpty3);
					window.addElement(farmPlotHarvest3);
					enemyfarm3=false;
					farmPlotCounter=0;
				}
				if(farmPlotCounter==4)
				{
					window.removeElement(farmPlotEmpty4);
					window.addElement(farmPlotHarvest4);
					enemyfarm4=false;
					farmPlotCounter=0;
				}
			}
		}, farmWaitTick);
	}
}

