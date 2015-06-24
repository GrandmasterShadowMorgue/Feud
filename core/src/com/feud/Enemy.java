 /*
  * Class Enemy
  *  Class for all enemy characters.
  *
  * Author: Jayant Shivarajan
  * Date  :  26/03/2015
  */

package com.feud;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends AbstractCharacter {
	
	private boolean isRespawnable;
	
	public Enemy(Type type, boolean isRespawnable) {
		
		this.health = type.health;
		this.mana = type.mana;
		this.direction = new Vector2(0,-1); // Default facing south
		this.position = new Vector2(24,24);
		this.speed = type.speed; 			// Affects move speed and attack speed (would also affect animation frame duration)
		this.isAlive = true;
		this.isMoving = false;
		this.isRunning = false;
		this.isAttacking = false;
		this.width = 32f;
		this.height = 32f;
		this.boundingBox = new Rectangle(this.position.x - (this.width/2), this.position.y-(this.height/2), this.width, this.height);
		this.isRespawnable = isRespawnable;
	}
	
	public enum Type {
		
		HUMANOID (150, 50, 10),
		UNDEAD (300 , 20, 5);
		
		private final int health, mana, speed;
		
		Type(int health, int mana, int speed) {
			this.health = health;
			this.mana = mana;
			this.speed = speed;
		}
	}
	
	@Override
	void move(float delta, boolean xColliding, boolean yColliding) {
		// Moves the character whilst checking collisions along each axis.

		if(this.isMoving) {
			
			if(this.isRunning) {
				// Running is 2 times faster

				if(!xColliding) {
					
					this.position.x += 2*10*this.speed*this.direction.x*delta;
				}
			
				if(!yColliding) {
					
					this.position.y += 2*10*this.speed*this.direction.y*delta;
				}			
			}
		}
		
		else {
			
			if(!xColliding) {
				
				this.position.x += 10*this.speed*this.direction.x*delta;
			}
			
			if(!yColliding) {
				
				this.position.y += 10*this.speed*this.direction.y*delta;
			}
		}
		
		this.boundingBox.setPosition(this.position.x - (this.width/2), this.position.y-(this.height/2));

	}
}
