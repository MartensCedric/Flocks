package com.cedricmartens.flocks.agent;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by martens on 7/6/17.
 */

public class Squarel extends Agent {
    @Override
    protected void initAgent() {

        setVelocity(new Vector2());
        getVelocity().setToRandomDirection();

        setAcceleration(new Vector2());
        sightDistance = 125;
        maxSpeed = 3;
        maxForce = 0.25f;
    }

    public Squarel(Vector2 position) {
        super(position);
    }

    public Squarel() {
    }

    @Override
    public void render(ShapeRenderer renderer) {
        int radius = 25;
        renderer.setColor(Color.ORANGE);
        renderer.rect(getPosition().x - radius, getPosition().y - radius,
                radius * 2, radius * 2);

        renderer.line(getPosition().x, getPosition().y,
                getPosition().x + getVelocity().x * 5,
                getPosition().y + getVelocity().y * 5);

        if(drawSight)
            renderSight(renderer);
    }
}
