package resg.ert.characters;


import static java.lang.Math.min;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import static resg.ert.Main.SCR_HEIGHT;

import resg.ert.Main;
import resg.ert.screens.ScreenGameNorm;

public class Bird {
    int x;
    public float y;
    float speed;
    boolean jump;

    int frameCounter;
    Texture [] framesArray;

    int heightOfJump;
    ScreenGameNorm screenGameNorm;
    final int maxHeightOfJump = 100;
    float speedy;
    int t = 0;
    public void OnClick(String screen) {
        if (!screen.equals("ScreenGameNorm")){
            speedy = speed;
            jump = true;
            heightOfJump = (int) (maxHeightOfJump + y);
        } else {
            if (!jump){
                speedy = speed;
                jump = true;
            }
        }
    }
    int width;
    int height;


    Texture texture;
    public Bird(int x, int y , int speed , int width, int height){
        this.x = x;
        this.y = y;
        this.speed = speed;
        frameCounter = 0;
        framesArray = new Texture[]{
            new Texture("birdTiles/bird0.png"),
            new Texture("birdTiles/bird1.png"),
            new Texture("birdTiles/bird2.png"),
            new Texture("birdTiles/bird1.png"),
        };
        this.height = height;
        this.width = width;

    }
    float g = 150F;





    public void fly(float deltaTime){
        if (y >= heightOfJump){
            speedy = g*deltaTime;
            jump = false;

        }
        if (jump){
            speedy -= g*deltaTime;
            y += speedy*deltaTime;
        } else {
            if (speed >= speedy){
                speedy += g*deltaTime;
                y -= g*deltaTime;
            }
            if (speed < speedy){
                y -= speed/2*deltaTime;
            }
        }
    }
    public  void slideJump(float deltaTime , int floorX){
        if (y < floorX){
            jump = false;
            y = floorX;
        }
        if (jump){
            speedy -= g*deltaTime;
            y += speedy*deltaTime;
        }
    }
    int periud;
    public void draw(Batch batch){


        periud += 1;
        if (frameCounter == 4){
            frameCounter = 0;
        }
        batch.draw(framesArray[frameCounter] , x , y , width , height);
        if (periud == 5) {
            frameCounter += 1;
            periud = 0;
        }
    }
    public void dispose(){
        texture.dispose();
    }
    public boolean inField(){
        if (y > SCR_HEIGHT){
            return false;
        }
        if (y + height < 0){
            return false;
        } else {
            return true;
        }
    }





}
