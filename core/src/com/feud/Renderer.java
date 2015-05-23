 /*
  * Class Renderer
  *  Handles anything to do with rendering
  *  and rendering logic.
  *
  * Author: Jayant Shivarajan
  * Date  :  26/03/2015
  */

package com.feud;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Renderer {
	
	private Texture texture;
	private float elapsedTime, runTime;
	OrthographicCamera camera;
	private SpriteBatch batch;
	private TextureAtlas walk, idle, run, attack;
	private Player player;
	private World world;
	private HashMap<Vector2,Animation> walkMap, idleMap, runMap, attackMap;
	private TiledMapRenderer mapRenderer;
	
	public Renderer(World world) {
		
		batch = new SpriteBatch();
		this.elapsedTime = 0;
		this.world = world;
		this.player = this.world.getPlayer();
		
  		camera = new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
  		camera.update();
  		
  		//Create spritesheets
  		walk = new TextureAtlas(Gdx.files.internal("Animations/Swordsman/walk.txt"));
  		idle = new TextureAtlas(Gdx.files.internal("Animations/Swordsman/idle.txt"));
  		run = new TextureAtlas(Gdx.files.internal("Animations/Swordsman/run.txt"));
  		attack = new TextureAtlas(Gdx.files.internal("Animations/Swordsman/attack.txt"));
        mapRenderer = new OrthogonalTiledMapRenderer(this.world.getMap());
        mapRenderer.setView(camera);
        
  		//Create and map animations  		
  		walkMap = new HashMap<Vector2, Animation>();
  		walkMap.put(new Vector2(0, 1), new Animation(1/15f,getMySprites(walk,"walkN",7),Animation.PlayMode.LOOP));
  		walkMap.put(new Vector2(0, -1), new Animation(1/15f,getMySprites(walk,"walkS",7),Animation.PlayMode.LOOP));
  		walkMap.put(new Vector2(1, 0), new Animation(1/15f,getMySprites(walk,"walkE",7),Animation.PlayMode.LOOP));
  		walkMap.put(new Vector2(-1, 0), new Animation(1/15f,getMySprites(walk,"walkW",7),Animation.PlayMode.LOOP));
  		walkMap.put(new Vector2( 0.707107f,  0.707107f), new Animation(1/15f,getMySprites(walk,"walkNE",7),Animation.PlayMode.LOOP));
  		walkMap.put(new Vector2( -0.707107f,  0.707107f), new Animation(1/15f,getMySprites(walk,"walkNW",7),Animation.PlayMode.LOOP));
  		walkMap.put(new Vector2( 0.707107f,  -0.707107f), new Animation(1/15f,getMySprites(walk,"walkSE",7),Animation.PlayMode.LOOP));
  	    walkMap.put(new Vector2( -0.707107f,  -0.707107f), new Animation(1/15f,getMySprites(walk,"walkSW",7),Animation.PlayMode.LOOP));
  		
  		idleMap = new HashMap<Vector2, Animation>();
  		idleMap.put(new Vector2(0, 1), new Animation(1/15f,idle.createSprites("idleN"),Animation.PlayMode.LOOP));
  		idleMap.put(new Vector2(0, -1),new Animation(1/15f,idle.createSprites("idleS"),Animation.PlayMode.LOOP));
  		idleMap.put(new Vector2(1, 0), new Animation(1/15f,idle.createSprites("idleE"),Animation.PlayMode.LOOP));
  		idleMap.put(new Vector2(-1, 0),new Animation(1/15f,idle.createSprites("idleW"),Animation.PlayMode.LOOP));
  		idleMap.put(new Vector2( 0.707107f,  0.707107f),  new Animation(1/15f,idle.createSprites("idleNE"),Animation.PlayMode.LOOP));
  		idleMap.put(new Vector2( -0.707107f,  0.707107f), new Animation(1/15f,idle.createSprites("idleNW"),Animation.PlayMode.LOOP));
  		idleMap.put(new Vector2( 0.707107f,  -0.707107f), new Animation(1/15f,idle.createSprites("idleSE"),Animation.PlayMode.LOOP));
  		idleMap.put(new Vector2( -0.707107f,  -0.707107f),new Animation(1/15f,idle.createSprites("idleSW"),Animation.PlayMode.LOOP));
  		
  		runMap = new HashMap<Vector2, Animation>();
  		runMap.put(new Vector2(0, 1), new Animation(1/15f,getMySprites(run,"runN",7),Animation.PlayMode.LOOP));
  		runMap.put(new Vector2(0, -1), new Animation(1/15f,getMySprites(run,"runS",7),Animation.PlayMode.LOOP));
  		runMap.put(new Vector2(1, 0), new Animation(1/15f,getMySprites(run,"runE",7),Animation.PlayMode.LOOP));
  		runMap.put(new Vector2(-1, 0), new Animation(1/15f,getMySprites(run,"runW",7),Animation.PlayMode.LOOP));
  		runMap.put(new Vector2( 0.707107f,  0.707107f), new Animation(1/15f,getMySprites(run,"runNE",7),Animation.PlayMode.LOOP));
  		runMap.put(new Vector2( -0.707107f,  0.707107f), new Animation(1/15f,getMySprites(run,"runNW",7),Animation.PlayMode.LOOP));
  		runMap.put(new Vector2( 0.707107f,  -0.707107f), new Animation(1/15f,getMySprites(run,"runSE",7),Animation.PlayMode.LOOP));
  	    runMap.put(new Vector2( -0.707107f,  -0.707107f), new Animation(1/15f,getMySprites(run,"runSW",7),Animation.PlayMode.LOOP));
  		
  	    attackMap = new HashMap<Vector2, Animation>();
  	    attackMap.put(new Vector2(0, 1), new Animation(1/15f,getMySprites(attack,"attackN",8), Animation.PlayMode.NORMAL));
		attackMap.put(new Vector2(0, -1), new Animation(1/15f,getMySprites(attack,"attackS",8),Animation.PlayMode.NORMAL));
		attackMap.put(new Vector2(1, 0), new Animation(1/15f,getMySprites(attack,"attackE",8), Animation.PlayMode.NORMAL));
		attackMap.put(new Vector2(-1, 0), new Animation(1/15f,getMySprites(attack,"attackW",8),Animation.PlayMode.NORMAL));
		attackMap.put(new Vector2(0.707107f, 0.707107f), new Animation(1/15f,getMySprites( attack,"attackNE",8), Animation.PlayMode.NORMAL));
		attackMap.put(new Vector2(-0.707107f, 0.707107f), new Animation(1/15f,getMySprites(attack,"attackNW",8), Animation.PlayMode.NORMAL));
		attackMap.put(new Vector2(0.707107f, -0.707107f), new Animation(1/15f,getMySprites(attack,"attackSE",8), Animation.PlayMode.NORMAL));
	    attackMap.put(new Vector2(-0.707107f, -0.707107f), new Animation(1/15f,getMySprites(attack,"attackSW",8),Animation.PlayMode.NORMAL));
	    
	}
	
	public TextureRegion getAnimationFrame(float elapsedTime, float delta) {
		// Gets the appropriate frame of an animation.
		
		if(this.player.isAttacking) {
			this.runTime += delta;
			this.player.isMoving = false;
			if(attackMap.get(this.player.direction).isAnimationFinished(runTime)){
				this.player.isAttacking = false;
				runTime = 0;
			}
			return attackMap.get(this.player.direction).getKeyFrame(runTime, true);
		}
		
		if(this.player.isMoving) {
			
			if(this.player.isRunning) {
				
				return runMap.get(this.player.direction).getKeyFrame(elapsedTime);
			}
			
			else {
				
				return walkMap.get(this.player.direction).getKeyFrame(elapsedTime);
			}
			
		}
		
		else {
			
			return idleMap.get(this.player.direction).getKeyFrame(elapsedTime);
		}
	}
	
	private Array<Sprite> getMySprites(TextureAtlas atlas, String name, int max) {
		// Gets sprites from the atlas file or spritesheet.
		
		Array<Sprite> sprites = new Array<Sprite>();
		
		for(int i=0; i <= max; i++){
			
			sprites.add(atlas.createSprite(String.format("%s_000%d",name,i)));
		}
		
		return sprites;
	}
	
	private void renderBackground() {
		// Renders the background layers
		
        mapRenderer.render(new int[]{0,1,2});
	}
	
	private void renderIntermediate(TextureRegion frame) {
		// Decides the order of rendering for the player sprite and the environment layer.
		
		Vector2 rectCentre = new Vector2();
		Vector2 playerCentre = new Vector2();
		Vector2 detectionArea = new Vector2();
		
		playerCentre = this.player.boundingBox.getCenter(playerCentre);
		
		for(MapObject collidable: this.world.collidables) {
			
				Rectangle rect = ((RectangleMapObject) collidable).getRectangle();
				rectCentre = rect.getCenter(rectCentre);
				
			try {
				
				switch(collidable.getProperties().get("type").toString()) { // Different cases depending on the object type;
				
				case "tree_big":					
					
					detectionArea.set(78, 148);
					break;
					
				case "tree_medium":
					
					detectionArea.set(58,124);
					break;
					
				case "tree_small":
					
					detectionArea.set(58,90);
					break;
				
				default:
					detectionArea.set(0,0);
					break;
				}
			}		
			
			catch (NullPointerException e) {
				
				System.out.println("NullPointerException! Possible reason: Object type not found");
			}
			
			if(renderFirst(playerCentre, rectCentre, detectionArea)) {
				// Render player below the object's layer
				
				this.batch.begin();
				this.batch.draw(frame, this.player.position.x - (frame.getRegionWidth()/2), this.player.position.y - (frame.getRegionHeight()/2));	
				this.batch.end();
				mapRenderer.render(new int[]{3});
				return;
			}
		}		
		
		// Render player on top
		mapRenderer.render(new int[]{3});
		this.batch.begin();
		this.batch.draw(frame, this.player.position.x - (frame.getRegionWidth()/2), this.player.position.y - (frame.getRegionHeight()/2));	
		this.batch.end();
	}
	
	private void renderForeground() {
		// Renders the foreground layers
		
        mapRenderer.render(new int[]{5});
	}
	
	public void renderWorld(float delta) {
		// Renders the world as well as the player
		
		this.elapsedTime += delta;
		TextureRegion frame = this.getAnimationFrame(this.elapsedTime, delta);
		this.renderBackground();
		
		renderIntermediate(frame);
		this.renderForeground();
	}
	
	
	
	public void dispose() {
		
		batch.dispose();
		this.world.getMap().dispose();
		texture.dispose();
		walk.dispose();
		idle.dispose();
	}
	
	private boolean renderFirst(Vector2 objCentre, Vector2 otherCentre, Vector2 detectionArea) {
		// Decides which object to render first
		
		if(otherCentre.x+detectionArea.x >= objCentre.x && objCentre.x >= otherCentre.x - detectionArea.x ) {
			
			if(objCentre.y >= otherCentre.y && objCentre.y <= otherCentre.y + detectionArea.y) {
				
				return true;
			}
		}
		
		return false;
	}
	
}
