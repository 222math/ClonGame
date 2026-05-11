package resg.ert.screens;

import static resg.ert.GameResources.SHIP_IMG_PATH;
import static resg.ert.GameSettings.SCREEN_WIDTH;
import static resg.ert.GameSettings.SHIP_HEIGHT;
import static resg.ert.GameSettings.SHIP_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

import resg.ert.Main;
import resg.ert.object.ShipObject;

public class ScreenGame extends ScreenAdapter {

    Main main;
    ShipObject shipObject;
    public ScreenGame(Main main){
        this.main = main;

        shipObject = new ShipObject(SHIP_IMG_PATH , SCREEN_WIDTH/2 , 150 , SHIP_WIDTH  , SHIP_HEIGHT, main.world );
    }


    @Override
    public void render(float delta) {
        main.stepWorld();
        handleInput();
        draw();
    }

    @Override
    public void dispose() {
        shipObject.dispose();
    }
    public void handleInput(){
        if(Gdx.input.isTouched()){
            main.touch = main.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            shipObject.move(main.touch);
        }
    }
    public void draw(){
        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);
        ScreenUtils.clear(Color.CLEAR);

        main.batch.begin();

        shipObject.draw(main.batch);

        main.batch.end();
    }

}
