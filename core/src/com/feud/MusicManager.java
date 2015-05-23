/*
  * Class Feud
  *  Handles game music across different scenes.
  *
  * Author: Jayant Shivarajan
  * Date  :  26/03/2015
  */

package com.feud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicManager {
	
	private Music music;
	
	public MusicManager() {			
			
	}
	
	public void play(String filename) {
		
		music = Gdx.audio.newMusic(Gdx.files.internal(filename));
		music.setLooping(true);
		music.play();
	}
	
	public void dispose() {
		music.dispose();
	}
}
