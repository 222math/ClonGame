package resg.ert.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import resg.ert.screens.ScreenGame;
import resg.ert.screens.ScreenGameNorm;
import resg.ert.screens.ScreenRestart;

public class Portals {
    Texture texture;
    ScreenGameNorm screenGameNorm;
    float x , y;
    int width = 200, height = 400;
    public Portals(){
        texture = new Texture("portal/red_pr.png");
    }
    public void drow(Batch batch , float x , float y){
        this.x = x;
        this.y = y;
        batch.draw(texture , x , y , width , height);
    }
    public void dispose(){
        texture.dispose();
    }
    public boolean isInPortal(Bird bird){
        if (x <= bird.x+bird.width/2){
            return true;
        } else {
            return false;
        }
    }
}
