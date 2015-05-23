 /*
  * Class  Player
  *  Class for the player character.
  *
  * Author: Jayant Shivarajan
  * Date  :  date
  */

// Rectangle class : http://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/math/Rectangle.html
// Vector2 class : http://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/math/Vector2.html
 
package com.feud;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class Player extends AbstractCharacter {
	//private int health, mana;
	//private int strength, intelligence, dexterity, speed;
	
	public Player(){
		
		this.direction = new Vector2(0,-1); // Default facing south
		this.position = new Vector2(24,24);
		this.speed = 10; 					// Affects move speed and attack speed (would also affect animation frame duration)
		this.isAlive = true;
		this.isMoving = false;
		this.isRunning = false;
		this.isAttacking = false;
		this.width = 32f;
		this.height = 32f;
		this.boundingBox = new Rectangle(this.position.x - (this.width/2), this.position.y-(this.height/2), this.width, this.height);
	}
		
	@Override
	public void move(float delta, boolean xColliding, boolean yColliding){
		// Moves the character whilst checking collisions along each axis.
		
		if(this.isMoving){
			
			if(this.isRunning){
				// Running is 2 times faster
				
				if(!xColliding) {
					
					this.position.x += 2*10*this.speed*this.direction.x*delta;
				}
				
				if(!yColliding) {
					
					this.position.y += 2*10*this.speed*this.direction.y*delta;
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
		}
		
		this.boundingBox.setPosition(this.position.x - (this.width/2), this.position.y-(this.height/2));
	}
	
	public void debugInfo() {
		// Prints all the player's information to console. Intended for debugging,
		
		System.out.format("Player : \n");
		System.out.format("DirX : %f Dir y: %f \n", this.direction.x, this.direction.y);
		System.out.format("PosX : %f Pos y: %f \n", this.position.x, this.position.y);
		System.out.format("Speed : %d \n", this.speed);
		System.out.format("isMoving : %b\n",this.isMoving);
		System.out.format("isRunning : %b\n",this.isRunning);
		System.out.format("is Attacking : %b\n", this.isAttacking);
		System.out.format("width : %f\n", this.width);
		System.out.format("height : %f\n", this.height);
		System.out.format("bBox.x : %f bBox.y: %f bBox.width : %f bBox.height : %f \n", this.boundingBox.x, this.boundingBox.y, this.boundingBox.width, this.boundingBox.height);
	}
	
	public static Json.Serializer<Player> getSerializer() {
		return playerSerializer;
	}
	
	private static Json.Serializer<Player> playerSerializer = new Json.Serializer<Player>() {
    	
		   public void write (Json json, Player player, Class knownType) {
			   
		      json.writeObjectStart();
		      
		      json.writeObjectStart("direction");
		      json.writeField(player.direction, "x", "x");
		      json.writeField(player.direction, "y", "y");
		      json.writeObjectEnd();
		      
		      json.writeObjectStart("position");
		      json.writeField(player.position, "x", "x");
		      json.writeField(player.position, "y", "y");
		      json.writeObjectEnd();
		      
		      json.writeValue("speed", player.speed);
		      json.writeValue("isMoving", player.isMoving);		      
		      json.writeValue("isRunning", player.isRunning);
		      json.writeValue("isAttacking", player.isAttacking);
		      json.writeValue("width", player.width);
		      json.writeValue("height", player.height);
		      
		      json.writeObjectStart("boundingBox");
		      json.writeFields(player.boundingBox);
		      json.writeObjectEnd();
		      
		      json.writeObjectEnd();
		   }

		   public Player read (Json json, JsonValue jsonData, Class type) {
			   
			  Player player = new Player();
			  json.readFields(player, jsonData);
			  return player;
		   }
		};
}
