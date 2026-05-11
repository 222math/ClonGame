package resg.ert;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import resg.ert.screens.ScreenGame;
import resg.ert.screens.ScreenMenu;
import resg.ert.screens.ScreenRestart;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public SpriteBatch batch;
    public OrthographicCamera camera;

    public static final int SCR_WIDTH = 1280;
    public static final int SCR_HEIGHT = 720;
     public int maxGamePoint;
     public  void IsMaxGamePoint(int gamePoint){
         if (maxGamePoint < gamePoint){
             maxGamePoint = gamePoint;
         }
     }

    ScreenGame screenGame;
    ScreenRestart screenRestart;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        ScreenMenu screenMenu = new ScreenMenu(this);
        setScreen(screenMenu);



    }


    @Override
    public void dispose() {
        batch.dispose();
    }
}
