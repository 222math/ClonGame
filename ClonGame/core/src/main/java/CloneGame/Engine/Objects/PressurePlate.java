package CloneGame.Engine.Objects;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import CloneGame.Engine.Main.GameSettings;

public class PressurePlate extends GameObject {
    private boolean isHitPerson = false;
    private int y0;
    private int deltaY = 10;
    private float pressTimer = 0;

    public PressurePlate(String texture, int x, int y, int width, int height, World world) {
        super(texture, x, y, width, height, world, BodyDef.BodyType.KinematicBody);
        y0 = y;
    }

    public boolean getIsHitPerson() {
        return isHitPerson;
    }

    public void setIsHitPerson(boolean hit) {
        this.isHitPerson = hit;
    }

    public void update(float delta) {
        if (isHitPerson) {
            pressTimer = 0.2f;
        }

        if (pressTimer > 0) {
            pressTimer -= delta;
            // Опускаемся
            if (getY() > y0 - deltaY) {
                setY(getY() - 100 * delta);
            }
        }
        // ВАЖНО: else должен быть отдельно, без else if
        if (pressTimer <= 0 && getY() < y0) {
            // Поднимаемся
            setY(getY() + 100 * delta);
            if (getY() > y0) {
                setY(y0);
            }
        }

        // Ограничиваем, чтобы не ушла слишком далеко
        if (getY() < y0 - deltaY) {
            setY(y0 - deltaY);
        }
        if (getY() > y0) {
            setY(y0);
        }
    }
}
