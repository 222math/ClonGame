package CloneGame.Engine.Objects;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class PressurePlate extends GameObject{
    boolean isHitPerson = false;
    public PressurePlate(String texture , int  x , int y , int width , int height , World world){
        super(texture , x , y , width , height , world , BodyDef.BodyType.KinematicBody);
    }

}
