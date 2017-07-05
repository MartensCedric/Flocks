package com.cedricmartens.flocks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by martens on 7/5/17.
 */
public class Boid extends Agent {

    @Override
    public void render(ShapeRenderer renderer)
    {
        renderer.setColor(Color.BLACK);
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
}
