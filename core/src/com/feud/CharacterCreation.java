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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class CharacterCreation implements Screen {
	
	Feud game;
	private SpriteBatch batch;
	private Stage stage;
	private Table table;
	private Texture texture;
	
	public CharacterCreation(Feud reference) {
		
		game = reference;
		
		batch = new SpriteBatch();
        
        /*******************************************************************/
  		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(		//Genereates a Freetype font from a .TFF file
  				Gdx.files.internal("PARCHM.TTF"));
  		/*******************************************************************/
  		
  		/*******************************************************************/
  		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();	//Parameters for the freetype font
  		parameter.size = 60;
  		parameter.color = Color.valueOf("C0C0C0");
  		parameter.borderWidth = 3;
  		/*******************************************************************/
  		
  		/*******************************************************************/
		Pixmap pm = new Pixmap(Gdx.files.internal("cursor.png")); //Custom cursor
  		Gdx.input.setCursorImage(pm, 0, 0);
  		pm.dispose();
  		/*******************************************************************/
  		
  		/*******************************************************************/
  		texture = new Texture(Gdx.files.internal("menu_background.jpg"));
  		/*******************************************************************/
  		
  		/*******************************************************************/
  		BitmapFont text = new BitmapFont();
  		text = generator.generateFont(parameter);		
  		/*******************************************************************/
  		
  		/*******************************************************************/
  		stage = new Stage();
  		table = new Table();
  		/*******************************************************************/
  		
  		/*******************************************************************/
  		Skin skin = new Skin();
  		TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("button.pack"));
  		skin.addRegions(buttonAtlas);
  		/*******************************************************************/
  		
  		/*******************************************************************/
  		TextButton.TextButtonStyle textButtonStyle = new TextButtonStyle();
  		textButtonStyle.font = text;
  		textButtonStyle.up = skin.getDrawable("stone_button"); 
  		textButtonStyle.down = skin.getDrawable("stone_button_pressed");
  		textButtonStyle.checked = skin.getDrawable("stone_button");
  		/*******************************************************************/
  		
  		TextField.TextFieldStyle textFieldStyle = new TextFieldStyle();
  		textFieldStyle.font = text;
  		textFieldStyle.fontColor = Color.valueOf("C0C0C0");

  		/*******************************************************************/
  		TextField textfield = new TextField("", textFieldStyle);
  		/*******************************************************************/
  		
  		/*******************************************************************/
  		Label.LabelStyle style = new LabelStyle(text,Color.valueOf("C0C0C0"));
  		Label label = new Label("Enter your name",style);
  		label.setAlignment(Align.center);
  		/*******************************************************************/
  		
  		/*******************************************************************/
  		table.add(label);
  		table.add(textfield).row();
  		table.center();
  		stage.addActor(table);
  		/*******************************************************************/
  		
	}
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
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
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		stage.dispose();
		batch.dispose();
		texture.dispose();
		game.musicmanager.dispose();
	}

	@Override
	public void dispose() {

	}

}
