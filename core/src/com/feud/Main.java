package com.feud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class Main implements Screen {
	
	private World world;
	private Renderer renderer;
	private InputManager im;
	Feud game;
	
	public Main(Feud reference, Player player) {
		
		this.game = reference;
	
		world = new World(player);
		this.im = new InputManager(this.world, this.game.saveManager);
		renderer = new Renderer(world);
	}
	
	@Override
    public void render(float delta) {
		
		// Clears the screen
		Gdx.gl.glClearColor(1, 1, 1, 1);
   		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
   		
   		world.update(delta);  
		renderer.renderWorld(delta);
	}
	
	 @Override
     public void resize(int width, int height) {
     }


    @Override
     public void show() {
    	
  		Gdx.input.setInputProcessor(this.im);  		
  		game.musicmanager.play("Scarborough Fair.mp3");  		
     }


    @Override
     public void hide() {
        // called when current screen changes from this to a different screen
    	game.musicmanager.dispose();
     }


    @Override
     public void pause() {
     }


    @Override
     public void resume() {
     }


    @Override
     public void dispose() {
    	renderer.dispose();
    	game.musicmanager.dispose();
    }
}
