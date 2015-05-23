 /*
  * Class Feud
  *  Main game class which uses the manager classes
  * and screens.
  *
  * Author: Jayant Shivarajan
  * Date  :  26/03/2015
  */

package com.feud;

import com.badlogic.gdx.Game;

public class Feud extends Game {
	
	MainMenu mainMenu;
	MusicManager musicmanager;
	CharacterCreation characterCreation;
	SaveManager saveManager;
	
	@Override
    public void create() {
			saveManager = new SaveManager(false);
			musicmanager = new MusicManager();
            mainMenu = new MainMenu(this);
            characterCreation = new CharacterCreation(this);
            setScreen(mainMenu);              
    }
}