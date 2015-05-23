/*
  * Class AbstractCharacter
  *  Abstract class for association with
  *  different types of characters.
  *
  * Author: Jayant Shivarajan
  * Date  :  26/03/2015
  */

package com.feud;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractCharacter {
	
	protected Integer health, mana, speed;
	protected Vector2 direction, position;
	protected String name;
	protected Float width, height;
	protected Rectangle boundingBox, attackBox;
	protected Boolean isMoving, isAttacking, isRunning, isAlive;
	
	abstract void move(float delta, boolean xColliding, boolean yColliding);
}
