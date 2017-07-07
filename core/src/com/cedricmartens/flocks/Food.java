package com.cedricmartens.flocks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by martens on 7/7/17.
 */

public class Food extends Entity
{
    public Food(Vector2 position) {
        super(position);
    }

    public void render(ShapeRenderer renderer)
    {
        renderer.setColor(Color.BROWN);
        renderer.circle(getPosition().x, getPosition().y, 5);
    }
}
