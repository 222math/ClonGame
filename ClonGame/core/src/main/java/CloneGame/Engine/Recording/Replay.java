package CloneGame.Engine.Recording;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class Replay {
    private List<Vector2> positions;
    private Texture texture;
    private int currentFrame = 0;
    private float timer = 0;
    private float frameDelay = 0.05f;
    private boolean playing = true;

    public Replay(List<Vector2> positions, String pathToTexture) {
        this.positions = positions;
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

    public Vector2 getCurrentPosition() {
        if (currentFrame >= 0 && currentFrame < positions.size()) {
            return positions.get(currentFrame);
        }
        return null;
    }

    public Texture getTexture() {
        return texture;
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
