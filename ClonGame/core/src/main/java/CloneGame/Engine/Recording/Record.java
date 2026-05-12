package CloneGame.Engine.Recording;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import jdk.internal.net.http.common.Pair;

public class Record {
    private float recordTimer = 0;
    private float recordInterval = 0.05f;
    private boolean lastSost = false;
    public Record(){

    }
    List<Vector2> positions = new ArrayList<>();
    ArrayList<Pair<Boolean, Float>> plate = new ArrayList<>();
    public void recordingPos(float x, float y, float delta) {

        recordTimer += delta;
        if (recordTimer >= recordInterval) {
            recordTimer = 0;
            positions.add(new Vector2(x, y));
        }
    }
    public void recordingPlate(boolean sost , float time){
        if (sost != lastSost){
            plate.add(new Pair<>(sost, time));
        }
        lastSost = sost;
    }

    public List<Vector2> getPositions() {
        return positions;
    }
    public ArrayList<Pair<Boolean, Float>> getPlate(){
        return plate;
    }

    public void deleteRecord(){
        positions.clear();
        plate.clear();
    }
}
