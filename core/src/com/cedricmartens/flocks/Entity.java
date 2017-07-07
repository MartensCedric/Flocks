package com.cedricmartens.flocks;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by martens on 7/6/17.
 */

public abstract class Entity
{
    private Vector2 position;

    public Entity(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
