package APFINAL;

import java.applet.Applet;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
//public class GameEngine extends Applet implements Runnable, KeyListener
//{
//
//	public static BufferedImage img1, img2, img3, img4;
//	public Thread animator;
//	public GameEngine()
//	{
//		try 
//		{                
//			img1 = ImageIO.read(new File("Layer 1.png"));
//			img2 = ImageIO.read(new File("Layer 2.png"));
//			img3 = ImageIO.read(new File("Layer 3.png"));
//			img4 = ImageIO.read(new File("Layer 4.png"));
//
//		} 
//		catch (IOException ex) 
//		{
//			// handle exception...
//		}
//		
//		animator = new Thread(() -> {
//			while(true) {
//				repaint();
//				try {
//					Thread.sleep(30);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
//		animator.setDaemon(true);
//		animator.start();
//
//	}
//	@Override
//	public void run()
//	{
//
//	}
//		//	@Override
//		//	public void keyTyped(KeyEvent e) 
//		//	{
//		//		if(e.getKeyCode() == e.VK_W)
//		//		{
//		//			System.out.println("pressed");
//		//		}
//		//	}
//		//	@Override
//		//	public void keyPressed(KeyEvent e) 
//		//	{
//		//
//		//	}
//		//	@Override
//		//	public void keyReleased(KeyEvent e) 
//		//	{
//		//
//		//	}
//	@Override
//	public void keyTyped(KeyEvent e) {
//		// TODO Auto-generated method stub
//
//	}
//	@Override
//	public void keyReleased(KeyEvent e) {
//		// TODO Auto-generated method stub
//
//	}
//	@Override
//	public void keyPressed(KeyEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//	public void keyPressed(KeyEvent e, Graphics g) {
//		// TODO Auto-generated method stub
//		
//	}
//}
