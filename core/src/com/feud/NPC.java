/*
  * Class NPC
  *  Class for all neutral, friendly
  *	 and potentially hostile characters.
  *
  * Author: Jayant Shivarajan
  * Date  :  26/03/2015
  */

// Rectangle class : http://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/math/Rectangle.html
// Vector2 class : http://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/math/Vector2.html

package com.feud;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class NPC extends AbstractCharacter {
	
	public NPC() {
		
		this.direction = new Vector2(0,-1); // Default facing south
		this.position = new Vector2(24,24);
		this.speed = 10; 					// Affects move speed and attack speed (would also affect animation frame duration)
		this.isMoving = false;
		this.width = 32f;
		this.height = 32f;
		this.boundingBox = new Rectangle(this.position.x - (this.width/2), this.position.y-(this.height/2), this.width, this.height);
	}
	
	@Override
	void move(float delta, boolean xColliding, boolean yColliding) {
		// Move function for any NPC
		
		if(this.isMoving) {
			
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
