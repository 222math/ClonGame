package resg.ert.components;



import static java.lang.Math.max;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MaxGamePoint {
    int maxGamePoint;
    BitmapFont font;
    int x , y;
    public MaxGamePoint(int x , int y , int gamePoint){
        this.x = x;
        this.y = y;
        this.maxGamePoint = max(gamePoint , maxGamePoint);

        font = new BitmapFont();
        font.getData().setScale(5f);
        font.setColor(Color.RED);
    }

    public void drow(Batch batch){
        font.draw(batch , "max point counter: " + maxGamePoint , x , y);
    }
    public void dispose(){
        font.dispose();
    }
}
