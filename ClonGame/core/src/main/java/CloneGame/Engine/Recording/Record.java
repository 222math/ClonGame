package CloneGame.Engine.Recording;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Record {
    private float recordTimer = 0;
    private float recordInterval = 0.05f;
    public Record(){

    }
    List<Vector2> positions = new ArrayList<>();
    public void recordingPos(float x, float y, float delta) {

        recordTimer += delta;
        if (recordTimer >= recordInterval) {
            recordTimer = 0;
            positions.add(new Vector2(x, y));
        }
    }

    public List<Vector2> getPositions() {
        return positions;
    }
    public void deleteRecord(){
        positions.clear();
    }
}
