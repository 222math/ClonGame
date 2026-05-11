package resg.ert.object;

import static resg.ert.GameSettings.SCREEN_HEIGHT;
import static resg.ert.GameSettings.SCREEN_WIDTH;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;

public class ShipObject extends GameObject{

    public ShipObject(String texture , int  x , int y , int width , int height , World world){
        super(texture , x , y , width , height , world);
    }
    @Override
    public void draw(SpriteBatch batch){
        putInFrame();
        super.draw(batch);
    }
    public  void dispose(){
        texture.dispose();
    }
    private void putInFrame(){
        if(getY() > SCREEN_HEIGHT/2 - height/2){
            setY(SCREEN_HEIGHT/2 - height/2);
        }
        if(getY() < height/2){
            setY(height/2);
        }
        if (getX() < width/2){
            setX(width/2);
        }
        if (getX()>SCREEN_WIDTH - width/2){
            setX(SCREEN_WIDTH - width/2);
        }
    }

    public void move(Vector3 vector3){
        float fx = vector3.x - getX();
        float fy = vector3.y - getY();

        body.applyForceToCenter(new Vector2(fx*10 , fy*10) , true);
        body.setLinearDamping(10);
    }


}
