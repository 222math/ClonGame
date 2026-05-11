package resg.ert.characters;

import static resg.ert.Main.SCR_HEIGHT;
import static resg.ert.Main.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.Random;

public class Tube {

    Texture textureUpperTube;
    Texture textureDownTube;
    Random random = new Random();
    int width = 250;
    int height = 700;
    public float x;
    public int gapHeight = 400;
    public int gapY;
    int padding = 50;
    int distanceBetweenTubes;
    public int tubeInx;

    Bird bird;

    public Tube(int tubeCount , int tubeInx){
        this.tubeInx =tubeInx;
        textureUpperTube = new Texture("tubes/tube_flipped.png");
        textureDownTube = new Texture("tubes/tube.png");
        gapY  = gapHeight / 2 + random.nextInt(SCR_HEIGHT - 2*(padding + gapHeight/2));
        distanceBetweenTubes = (SCR_WIDTH + width)/(tubeCount - 1);
        x = distanceBetweenTubes * tubeInx + SCR_WIDTH;

    }
    int speed = 300;
    boolean iSPointReceived;
    public void move(float deltaTime){
        x -= speed*deltaTime;
        if (x <= - height){
            iSPointReceived = false;
            x =  SCR_WIDTH;
            gapY = gapHeight / 2 + random.nextInt(SCR_HEIGHT - 2*(padding + gapHeight/2));
        }

    }
    public boolean NeedAddPoint(Bird bird){
        if (bird.x > x + width && !iSPointReceived ){
            iSPointReceived = true;
            return true;
        } else {
            return false;
        }
    }

    public void drow(Batch batch ){
        batch.draw(textureUpperTube , x , gapY + (gapHeight/2) , width , height );
        batch.draw(textureDownTube , x , gapY - (gapHeight / 2) - height, width , height);
    }
    public void dispose(){
        textureUpperTube.dispose();
        textureDownTube.dispose();
    }
    public boolean IsHit (Bird bird) {
        if (bird.x + bird.width >= x && bird.x <= x + width / 2 && bird.y <= gapY - gapHeight / 2) {
            return true;
        } else if (bird.y + bird.height >= gapY + gapHeight / 2 && bird.x + bird.width >= x && bird.x <= x + width / 2 ){
            return true;
        }else {
            return false;
        }
    }
    public void setPointReceived(){
        iSPointReceived = true;
    }





}
