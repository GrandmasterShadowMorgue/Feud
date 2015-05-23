 /*
  * Class SaveManager
  *  Manages character save files.
  *
  * Author: Jayant Shivarajan
  * Date  :  26/03/2015
  */

package com.feud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;

public class SaveManager {
    
    private boolean encoded;
    private FileHandle playerSave = Gdx.files.local("bin/player.json");
    
    
    
    public SaveManager(boolean encoded){
        this.encoded = encoded;
    }
    
    public void savePlayer(Player player) { //Saves the player in json format to the player save file.
    	
    	Json json = new Json();
    	json.setOutputType(OutputType.json);
    	json.setSerializer(Player.class, Player.getSerializer());
    	if(encoded) playerSave.writeString(Base64Coder.encodeString(json.prettyPrint(json.toJson(player, Player.class))), false);
        else playerSave.writeString(json.prettyPrint(json.toJson(player, Player.class)), false);
    }
    
    public Player loadPlayer() { // Loads the player from the player save file.
    	
    	Json json = new Json();
    	json.setSerializer(Player.class, Player.getSerializer());    
    	return json.fromJson(Player.class, playerSave.readString());
    }
}
