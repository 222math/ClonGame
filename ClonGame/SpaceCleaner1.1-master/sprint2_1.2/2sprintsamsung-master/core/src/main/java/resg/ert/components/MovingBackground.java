package resg.ert.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import resg.ert.Main;

public class MovingBackground {
    Main main;
    Texture texture;
    int speed = 120;
    public float x1;
    public float x2;
    public MovingBackground(String pathToTexture){
        x1 = 0;
        x2 = main.SCR_WIDTH;
        texture = new Texture(pathToTexture);

    }
    public void move(float deltaTime){
        x1 -= speed*deltaTime;
        x2 -= speed*deltaTime;
        if (x1 + main.SCR_WIDTH < 0){
            x1 = 2*main.SCR_WIDTH  + x1;
        }
        if (x2 + main.SCR_WIDTH < 0){
            x2 = 2*main.SCR_WIDTH  + x2;
        }
    }

    public void draw(Batch batch){
        batch.draw(texture, x1  , 0 , main.SCR_WIDTH , main.SCR_HEIGHT);
        batch.draw(texture , x2 , 0 , main.SCR_WIDTH , main.SCR_HEIGHT);
    }




    public void dispose(){
        texture.dispose();
    }
}
