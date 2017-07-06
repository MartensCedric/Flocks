package com.cedricmartens.flocks.obstacle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by martens on 7/6/17.
 */

public class Wall extends Obstacle {

    public Wall(Vector2 position)
    {
        super(position);
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(getPosition().x - radius, getPosition().y - radius,
                radius * 2, radius * 2);
    }

    @Override
    public void initObstacle() {
        radius = 25;
        repulsionForce = 2;
    }
}
