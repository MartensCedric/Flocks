package com.cedricmartens.flocks.obstacle;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.cedricmartens.flocks.Entity;

/**
 * Created by martens on 7/6/17.
 */

public abstract class Obstacle extends Entity
{
    protected int radius;
    protected float repulsionForce;

    public abstract void render(ShapeRenderer shapeRenderer);
    public abstract void initObstacle();

    public Obstacle(Vector2 position) {
        setPosition(position);
        initObstacle();
    }

    public float getRepulsionForce() {
        return repulsionForce;
    }
}
