/*
  * Class MainMenu
  *  Class for the main menu screen.
  *
  * Author: Jayant Shivarajan
  * Date  :  26/03/2015
  */

package com.feud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenu implements Screen {
	
	private SpriteBatch batch;
	private Texture texture;
	private BitmapFont text;
	private Skin skin;
	private TextureAtlas buttonAtlas;
	private Stage stage;
	private TextButton new_game;
	private TextButtonStyle textButtonStyle;
	private Table table;
	private Label title;
	private LabelStyle titleStyle;
	static Feud game;
	
     public MainMenu(Feud reference){
    	 
    	 	game = reference;
    	 	
    	 	batch = new SpriteBatch();
            
            /*******************************************************************/
      		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(		//Generates a Freetype font from a .TFF file
      				Gdx.files.internal("PARCHM.TTF"));
      		/*******************************************************************/
      		
      		/*******************************************************************/
      		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();	//Parameters for the freetype font
      		parameter.size = 60;
      		parameter.color = Color.valueOf("E6E4D8");
      		parameter.borderWidth = 3;
      		/*******************************************************************/
      		
      		/*******************************************************************/
      		text = new BitmapFont();
      		text = generator.generateFont(parameter);		
      		/*******************************************************************/
      		
      		/*******************************************************************/
      		BitmapFont title_text = new BitmapFont();
      		FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
      		param.size = 120;
      		param.borderWidth = 5;
      		param.borderStraight = true;
      		title_text = generator.generateFont(param);
      		/*******************************************************************/
      		
      		generator.dispose(); //Dispose generator since it's no longer used
      		
      		/*******************************************************************/
      		texture = new Texture(Gdx.files.internal("menu_background.jpg")); 
      		
      		/*******************************************************************/
      		stage = new Stage();
      		table = new Table();
      		Gdx.input.setInputProcessor(stage);
      		/*******************************************************************/
      		
      		/*******************************************************************/
      		skin = new Skin();
      		buttonAtlas = new TextureAtlas(Gdx.files.internal("button.pack"));
      		skin.addRegions(buttonAtlas);
      		/*******************************************************************/
      		
      		/*******************************************************************/
      		textButtonStyle = new TextButtonStyle();
      		textButtonStyle.font = text;
      		textButtonStyle.up = skin.getDrawable("stone_button"); 
      		textButtonStyle.down = skin.getDrawable("stone_button_pressed");
      		textButtonStyle.checked = skin.getDrawable("stone_button");
      		/*******************************************************************/
      		
      		/*******************************************************************/
      		new_game = new TextButton("New Game", textButtonStyle);
      		TextButton load_game = new TextButton("Load Game", textButtonStyle);
      		TextButton quit = new TextButton("Quit", textButtonStyle);
      		new_game.padBottom(50);
      		load_game.padBottom(50);
      		quit.padBottom(50);      		
      		/*******************************************************************/
      		 
      		/*******************************************************************/
      		titleStyle = new LabelStyle(title_text,Color.valueOf("ED7926"));
      		title = new Label("Feud",titleStyle);
      		title.setAlignment(Align.center);
      		table.add(title).size(600,200).padBottom(100).center().row();
      		table.add(new_game).size(500,80).padBottom(80).center().row();
      		table.add(load_game).size(500,80).padBottom(80).center().row();
      		table.add(quit).size(500,80).padBottom(80).center().row();
      		table.setFillParent(true);
      		table.center();
      		stage.addActor(table);
      		/*******************************************************************/

      		
      		/*******************************************************************/
      		Pixmap pm = new Pixmap(Gdx.files.internal("cursor.png")); //Custom cursor
      		Gdx.input.setCursorImage(pm, 0, 0);
      		pm.dispose();
      		/*******************************************************************/
      		
        	new_game.addListener(new ClickListener() { //ActionListener for the new game button

    			@Override
      			public void clicked(InputEvent event, float x, float y) {
    				game.setScreen(new Main(game,new Player()));
      			}
      		});
        	
        	load_game.addListener(new ClickListener() { //ActionListener for the load game button

    			@Override
      			public void clicked(InputEvent event, float x, float y) {
    				game.setScreen(new Main(game,game.saveManager.loadPlayer()));
      			}
      		});
        	
      		quit.addListener(new ClickListener(){ //Action listener for the quit button
      	            @Override
      	            public void clicked(InputEvent event, float x, float y) {
      	                Gdx.app.exit();
      	           }
      	    });
     }
     
     @Override
     public void render(float delta) {
    	 
    	// Clears the screen
    	Gdx.gl.glClearColor(1,1,1,1);
   		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
   		
   		batch.begin();
  		batch.draw(texture, 0,0);  		
  		batch.end();
  		stage.act();
  		stage.draw();
     }


    @Override
     public void resize(int width, int height) {
     }


    @Override
     public void show() {
    	game.musicmanager.play("Greensleeves.mp3");
     }


    @Override
     public void hide() {
    	
    	batch.dispose();
		text.dispose();
		texture.dispose();
		buttonAtlas.dispose();
		stage.dispose();
		skin.dispose();
		game.musicmanager.dispose();
     }


    @Override
     public void pause() {
     }


    @Override
     public void resume() {
     }


    @Override
     public void dispose() {
    	
     }
}