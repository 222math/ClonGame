package resg.ert.characters;

import static resg.ert.Main.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.Random;

import resg.ert.screens.ScreenGameNorm;

public class Thorns {
    Random r;
    int distance = 500;
    int speed = 300;
    public int width , hied;
    public int  y = 135;
    int ver;
    Texture texture;
    public float x;
    boolean iSPointReceived;
    public int thornsInx;
    public Thorns(int thornsInx){
        r = new Random();
        this.thornsInx = thornsInx;
        this.x = 2*SCR_WIDTH + 750*thornsInx;
        ver = r.nextInt(100);
        if (ver <50){
            width = 75;
            hied = 75;
            texture = new Texture("thorns/red_th.png");
        } else if (ver < 85){
            width = 100;
            hied = 60;
            texture = new Texture("thorns/two_red_th.png");
        } else {
            width = 115;
            hied = 50;
            texture = new Texture("thorns/three_red_th.png");
        }


    }
    public void move(float delta , float x0){
        x -= speed*delta;
        if (x+width<0){
            x = x0 + distance + r.nextInt(250);
            iSPointReceived = false;
            ver = r.nextInt(100);
            if (ver <50){
                width = 75;
                hied = 75;
                texture = new Texture("thorns/red_th.png");
            } else if (ver < 85){
                width = 95;
                hied = 70;
                texture = new Texture("thorns/two_red_th.png");
            } else {
                width = 115;
                hied = 70;
                texture = new Texture("thorns/three_red_th.png");
            }

        }
    }

    public boolean IsHit (Bird bird) {
        if (bird.x + bird.width >= x+20 && bird.x-10 <= x + width &&
            bird.y + bird.height >= y && bird.y + 15 <= y + hied/2) {
            return true;
        } else {
            return false;
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




    public void draw(Batch batch){

        batch.draw(texture , x , y , width , hied);
    }
    public void dispose(){
        texture.dispose();
    }


}
