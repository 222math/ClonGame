package CloneGame.Engine.Recording;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import jdk.internal.net.http.common.Pair;

public class Replay {
    private List<Vector2> positions;
    ArrayList<Pair<Boolean, Float>> plate;
    private Texture texture;
    private int currentFrame = 0;
    private float timer = 0;
    private float frameDelay = 0.05f;
    private boolean playing = true;
    private float timePlate = 0;


    public Replay(List<Vector2> positions, ArrayList<Pair<Boolean, Float>> plate ,  String pathToTexture) {
        this.positions = positions;
        this.plate = plate;
        this.texture = new Texture(pathToTexture);
        this.currentFrame = 0;
        this.playing = true;
    }

    public void update(float delta) {
        if (!playing) return;

        timer += delta;
        if (timer >= frameDelay) {
            timer = 0;
            currentFrame++;

            if (currentFrame >= positions.size()) {
                playing = false;
            }
        }
    }
    public void updPlate(){
        if (plate.get(0).second < timePlate){

            plate.remove(0);
        }
    }

    public Vector2 getCurrentPosition() {
        if (currentFrame >= 0 && currentFrame < positions.size()) {
            return positions.get(currentFrame);
        }
        return null;
    }

    public Texture getTexture() {
        return texture;
    }

    public float getTimer() {
        return timer;
    }

    public void setTimer(float timer) {
        this.timer = timer;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void dispose() {
        if (texture != null) {
            texture.dispose();
        }
    }
}
