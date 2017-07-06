package com.cedricmartens.flocks.agent;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by martens on 7/5/17.
 */
public class Circloid extends Agent {

    @Override
    public void render(ShapeRenderer renderer)
    {
        renderer.setColor(Color.BLUE);
        renderer.circle(getPosition().x, getPosition().y, 10);
        renderer.line(getPosition().x, getPosition().y,
                getPosition().x + getVelocity().x * 5,
                getPosition().y + getVelocity().y * 5);

        if(drawSight)
            renderSight(renderer);
    }

    public Circloid(Vector2 position) {
        super(position);
    }

    public Circloid()
    {
        super();
    }

    @Override
    protected void initAgent() {
        setVelocity(new Vector2());
        getVelocity().setToRandomDirection();

        setAcceleration(new Vector2());
        sightDistance = 100;
        maxSpeed = 5;
        maxForce = 0.2f;
    }
}
