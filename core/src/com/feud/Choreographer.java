/*
  * Class Choreographer
  *  Handles the player's motion logic such 
  *  as direction and movement status.
  *
  * Author: Jayant Shivarajan
  * Date  :  26/03/2015
  */

package com.feud;

public class Choreographer {
	private AbstractCharacter character;
	
	public Choreographer(AbstractCharacter player) {
		
		this.character = player;
	}
	
	public void setDirection(float x, float y) {
		
		this.character.direction.x = x;
		this.character.direction.y = y;
	}
	
	public void setMoving(boolean bool) {
		
		this.character.isMoving = bool;
		
		if(this.character.isMoving) {
			
			this.character.isAttacking = false;
		}
	}

	
	public void setRunning(boolean bool) {
		
		this.character.isRunning = bool;
	}
	
	public void setAttacking(boolean bool) {
		
		this.character.isAttacking = bool;
		
		if(this.character.isAttacking) {
			
			this.character.isMoving = false;
		}			
	}
}
