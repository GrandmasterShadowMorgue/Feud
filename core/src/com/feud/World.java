 /*
  * Class World
  *  Handles collisions and the logical 
  *	 interaction between various objects.
  *
  * Author: Jayant Shivarajan
  * Date  :  26/03/2015
  */

// Rectangle class : http://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/math/Rectangle.html
// Vector2 class : http://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/math/Vector2.html
 
package com.feud;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.function.Function;

public class World {
	
	private Player player;
	private TiledMap map;
	protected MapObjects collidables;
	private boolean xColliding, yColliding;
	private float delta;
	private ArrayList<Enemy> enemies = new ArrayList<> ();	
	
	//TODO - Distinguish world co-ordinates from screen co-ordinates.
	
	public World(Player player) {
		
		this.player = player;
  		map = new TmxMapLoader().load("Map 1/Map1.tmx");
  		this.collidables = map.getLayers().get("Collision").getObjects();
  		xColliding = false;
  		yColliding = false;
  		enemies.add(new Enemy(Enemy.Type.HUMANOID));
  		enemies.add(new Enemy(Enemy.Type.HUMANOID));
  		enemies.add(new Enemy(Enemy.Type.UNDEAD));
	}
	
	public Player getPlayer() { return this.player;}
	
	public TiledMap getMap() {return this.map;}
	
	public void setPlayer(Player player) { this.player = player;}
	
	public void update(float delta) {
		
		this.delta = delta;
		
		checkPlayerColliding();		
		this.player.move(delta, xColliding, yColliding);		
	}
	
	private void checkPlayerColliding() { 
		// Checks if the player is colliding with all collidable objects
		
		// TODO - Separate collision logic for projectiles			
		
		xColliding = false;
		yColliding = false;
		
		for(MapObject collidable: this.collidables) {

				
				Rectangle rect = ((RectangleMapObject) collidable).getRectangle();
				
				xColliding = xColliding || isCharacterColliding(this.player, rect, v -> v.x, v -> v.y);
				yColliding = yColliding ||  isCharacterColliding(this.player, rect, v -> v.y, v -> v.x);
			}				
	}
	
	public boolean isCharacterColliding(AbstractCharacter character, Rectangle other, Function<Vector2,Float> along, Function<Vector2,Float> align) {
		// Checks if the given character is colliding along a given axis with another collidable object's collision box
		
		Vector2 characterCentre = new Vector2();
		Vector2 otherCentre = new Vector2();
		Vector2 offset = new Vector2();
		Vector2 avg = new Vector2((character.boundingBox.width + other.width)/2, (character.boundingBox.height + other.height)/2);
		
		characterCentre = character.boundingBox.getCenter(characterCentre);
		characterCentre.x += 2*10*character.speed*character.direction.x*delta; // Important : this makes sure the collision is accounted for BEFORE the character collides.
		characterCentre.y += 2*10*character.speed*character.direction.y*delta; // Thus preventing intersection and leaving no chance of getting stuck.
		otherCentre = other.getCenter(otherCentre);
		offset = characterCentre.sub(otherCentre);
		
		return 	(character.isMoving) 																&&  //If player is moving
				(Math.abs(align.apply(offset)) <= align.apply(avg))									&&  // If aligned
				(Math.abs(along.apply(offset)) <= along.apply(avg)) 								&&  // If overlapping
				(Math.signum(along.apply(offset)) != Math.signum(along.apply(character.direction)));  	// If player is moving towards the object
				
	}
}
