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
	private Player player;
	
	public Choreographer(Player player) {
		
		this.player = player;
	}
	
	public void setDirection(float x, float y) {
		
		this.player.direction.x = x;
		this.player.direction.y = y;
	}
	
	public void setMoving(boolean bool) {
		
		this.player.isMoving = bool;
		
		if(this.player.isMoving) {
			
			this.player.isAttacking = false;
		}
	}

	
	public void setRunning(boolean bool) {
		
		this.player.isRunning = bool;
	}
	
	public void setAttacking(boolean bool) {
		
		this.player.isAttacking = bool;
		
		if(this.player.isAttacking) {
			
			this.player.isMoving = false;
		}			
	}
}
