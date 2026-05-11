package resg.ert.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class PointCounter {
    int x;
    int y;
    BitmapFont font;


    public PointCounter(int x, int y){
        this.x = x;
        this.y = y;

        font = new BitmapFont();
        font.getData().setScale(5f);
        font.setColor(Color.GOLD);
    }


    public void draw(Batch batch , int countOfPoints){
        font.draw(batch , "count: " + countOfPoints , x , y);
    }
    public void dispose(){
        font.dispose();
    }
}
