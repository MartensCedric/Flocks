package com.cedricmartens.flocks.agents;

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


        renderer.setColor(Color.BLUE);
        //renderer.circle(getPosition().x, getPosition().y, sightDistance);

        renderer.setColor(Color.RED);
        //renderer.arc(getPosition().x, getPosition().y, sightDistance,
          //      getVelocity().angle() - sightDegrees/2, sightDegrees);
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
