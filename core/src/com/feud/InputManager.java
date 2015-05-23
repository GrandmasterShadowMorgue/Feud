/*
  * Class InputManager
  *  Manages input event handling.
  *
  * Author: Jayant Shivarajan
  * Date  :  26/03/2015
  */

package com.feud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class InputManager implements InputProcessor{
	
	Choreographer c;
	World world;
	SaveManager saveManager;
	
	public InputManager(World world, SaveManager saveManager) {
		
		this.saveManager = saveManager;
		this.world = world;;		
		c = new Choreographer(this.world.getPlayer());
	}

	 @Override
	    public boolean keyDown(int keycode) { 
		 	if(keycode == Keys.F5) {
		 		
		 		saveManager.savePlayer(this.world.getPlayer());
		 	}
		 	
		 	if(Gdx.input.isKeyPressed(Keys.X)) {
		 		
		 		c.setAttacking(true);
		 		c.setMoving(false);
		 		return true;
		 	}
		 	
		 	if(Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) || Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT)) {
		 		
		 		c.setRunning(true);
		 	}
		 	
		 	if((Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W)) &&
		 	   (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) ) {
		 		
		 		c.setMoving(true);
	        	c.setDirection( 0.707107f,  0.707107f);
	        }
		 	
	        else if((Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W)) &&
	        		(Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) ) {
	        	
	        	c.setMoving(true);
	        	c.setDirection( -0.707107f,  0.707107f);
	        }
		 	
	        else if((Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S)) &&
	        		(Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) ) {
	        	
	        	c.setMoving(true);
	        	c.setDirection( 0.707107f,  -0.707107f);
	        }
		 	
	        else if((Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S)) &&
	        		(Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) ) {
	        	
	        	c.setMoving(true);
	        	c.setDirection( -0.707107f,  -0.707107f);
	        }
		 	
		 	else if((Gdx.input.isKeyPressed(Keys.UP)) || (Gdx.input.isKeyPressed(Keys.W))) {
		 		
	        	c.setMoving(true);
	        	c.setDirection(0,1);
	        }
		 	
	        else if((Gdx.input.isKeyPressed(Keys.DOWN)) || (Gdx.input.isKeyPressed(Keys.S))) {
	        	
	        	c.setMoving(true);
	        	c.setDirection(0,-1);
	        }
		 	
	        else if((Gdx.input.isKeyPressed(Keys.LEFT)) || (Gdx.input.isKeyPressed(Keys.A))) {
	        	
	        	c.setMoving(true);
	        	c.setDirection(-1,0);
	        }
		 	
	        else if((Gdx.input.isKeyPressed(Keys.RIGHT)) || (Gdx.input.isKeyPressed(Keys.D))) {
	        	
	        	c.setMoving(true);
	        	c.setDirection(1,0);
	        }
		 	
	        return true;
	    }

	    @Override
	    public boolean keyUp(int keycode) {
	    	
	    	if(!(Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) || Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT))) {
	    		
	    		c.setRunning(false);
	    	}
	    	
	    	if (!((Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) ||
	    		  (Gdx.input.isKeyPressed(Keys.LEFT)  || Gdx.input.isKeyPressed(Keys.A)) ||
	    		  (Gdx.input.isKeyPressed(Keys.UP) 	  || Gdx.input.isKeyPressed(Keys.W)  ||
	    		  (Gdx.input.isKeyPressed(Keys.DOWN)  || Gdx.input.isKeyPressed(Keys.S) )))) {
	    		
	    		c.setMoving(false);
	    	}
	        return true;
	    }

	    @Override
	    public boolean keyTyped(char character) {
	        return false;
	    }

	    @Override
	    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	       
	        return false;
	    }

	    @Override
	    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
	        return false;
	    }

	    @Override
	    public boolean touchDragged(int screenX, int screenY, int pointer) {
	        return false;
	    }

	    @Override
	    public boolean mouseMoved(int screenX, int screenY) {
	        return false;
	    }

	    @Override
	    public boolean scrolled(int amount) {
	        return false;
	    }
}
