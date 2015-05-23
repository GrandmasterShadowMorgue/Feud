/*
  * Class DesktopLauncher
  *  Used for launching the game and configuring
  *	 general settings for the game.
  *
  * Author: Jayant Shivarajan
  * Date  :  26/03/2015
  */

package com.feud.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.feud.Feud;

public class DesktopLauncher {
	public static void main (String[] arg) {
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		System.setProperty("org.lwjgl.opengl.Window.undecorated", "true"); // Makes window borderless
		
		config.width = LwjglApplicationConfiguration.getDesktopDisplayMode().width;
		config.height = LwjglApplicationConfiguration.getDesktopDisplayMode().height;
		config.fullscreen = true;
		config.title = "Feud";

		new LwjglApplication(new Feud(), config);
	}
}