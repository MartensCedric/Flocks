package com.cedricmartens.flocks.obstacles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by martens on 7/6/17.
 */

public abstract class Obstacle
{
    private Vector2 position;

    public abstract void render(ShapeRenderer shapeRenderer);

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
