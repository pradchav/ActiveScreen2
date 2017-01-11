package com;

/*********************************************************************************************************
 * Tool Name	: ScreenActivator
 * Description	: A tool to keep your machine from getting locked automatically due to inactivity
 * 				  using mouse moves based on user defined time intervals
 * Author		: pradyconsulting.com
 * License		: This is a freeware for anyones use
 * 
 *********************************************************************************************************/

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.util.Scanner;

public class ScreenActivator {
	static int miliSecsBetweenMouseMoves;
	PointerInfo pi;
	Point p;
	static boolean retry;
	
	public static void main(String[] args) {
		ScreenActivator sa = new ScreenActivator();
		// Menu to select duration between mouse moves
		do{
			retry = false;
			System.out.println("Welcome to active screen!");
			System.out.println("This tool will keep your machine from getting locked automatically due to inactivity");
			System.out.println("by moving the mouse pointer in a limited non noticable range at a fixed duration.");
			System.out.println("You can set this duration here as per your need. Every 'x' seconds, "
					+ "your mouse will move a little bit. Please choose 'x': ");
			System.out.println("1. 30 seconds \n2. 60 seconds\n3. 90 seconds\n4. 120 seconds\n5. 5 minutes");
			Scanner sc = new Scanner(System.in);
			switch (sc.next()) {
			case "1":
				miliSecsBetweenMouseMoves = 30000;
				break;

			case "2":
				miliSecsBetweenMouseMoves = 60000;
				break;

			case "3":
				miliSecsBetweenMouseMoves = 90000;
				break;

			case "4":
				miliSecsBetweenMouseMoves = 120000;
				break;

			case "5":
				miliSecsBetweenMouseMoves = 300000;
				break;

			default:
				System.out.println("Looks like you choose something outside of options 1-5. Please reenter.");
				retry =  true;
				break;
			}
		}while(retry);
		sa.activate(miliSecsBetweenMouseMoves);
	}

	//Move mouse at parameterized interval
	private void activate(long miliSecsBetweenMouseMoves) {
		
		try {
			Robot r = new Robot();
			while(true){
				pi = MouseInfo.getPointerInfo(); 
				p = pi.getLocation();
				pi.getLocation().move(100, 100);
				r.mouseMove((int)pi.getLocation().getX(), (int)pi.getLocation().getY());
				Thread.sleep(miliSecsBetweenMouseMoves/2);
				pi.getLocation().move(101, 101);
				r.mouseMove((int)pi.getLocation().getX(), (int)pi.getLocation().getY());
				Thread.sleep(miliSecsBetweenMouseMoves/2);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
