package keymanager;

/**
 * ##KeyManager##
 * 
 * Copyright ##copyright## ##turane_gaku##
 * 
 * This is a library to manage plural key input automatically.
 * You can know the moment a key is pressed and frame the key is pressed.
 * The methods of this class call static.
 * 
 * @author ##turane_gaku##
 */

import processing.core.PApplet;

public class Key {
	private static int pressTime[] = null;
	private static boolean keyOff[] = null;
	
	private Key(){}

	/**
	 * a Constructor, usually called in the setup() method in your sketch to
	 * initialize and start the library.
	 * 
	 * @param applet
	 */
	public static void setApplet(PApplet applet) {
		pressTime = new int[Character.MAX_VALUE];
		keyOff = new boolean[Character.MAX_VALUE];

		applet.addKeyListener(new java.awt.event.KeyAdapter() {

			@Override
			public void keyPressed(java.awt.event.KeyEvent e) {
				if (pressTime[e.getKeyCode()] <= 0)
					pressTime[e.getKeyCode()] = 1;
			}

			@Override
			public void keyReleased(java.awt.event.KeyEvent e) {
				keyOff[e.getKeyCode()] = true;
			}
		});
		
		applet.registerMethod("post", new Key());
	}

	public static void post() {
		for (int i=0; i<pressTime.length; i++) {
			pressTime[i]+=pressTime[i]!=0?1:0;
			if (keyOff[i]) {
				pressTime[i]=-1;
				keyOff[i]=false;
			}
		}
	}


	/**
	 * @param code
	 * keycode you want to know information
	 * @return int 
	 * the frame the key is pressed
	 */
	public static int get(int code) {
		return pressTime[code];
	}

	/**
	 * @param code
	 * keycode you want to know information
	 * @return boolean
	 * the moment the key is pressed true other false
	 */
	public static boolean pressed(int code) {
		return get(code)==2;
	}

	/**
	 * @param code
	 * keycode you want to know information
	 * @return boolean
	 * the moment the key is released true other false
	 */
	public static boolean released(int code) {
		return get(code)==-1;
	}

	/**
	 * @param code
	 * keycode you want to know information
	 * @param n
	 * pushed return true only frame > n
	 * @param m
	 * pushed return true every m frame.
	 * @return boolean
	 * every m frame
	 */
	public static boolean pushed(int code, int n, int m) {
		return pressed(code)||(get(code)>=n&&get(code)%m==0);
	}

}
