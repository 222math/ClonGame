package resg.ert.object;

import com.badlogic.gdx.physics.box2d.World;

public class TrashObject extends GameObject{
    public TrashObject(String texture , int  x , int y , int width , int height , World world){
        super(texture , x , y , width , height , world);
    }

}
