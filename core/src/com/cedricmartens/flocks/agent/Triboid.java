package com.cedricmartens.flocks.agent;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.cedricmartens.flocks.Const;

/**
 * Created by martens on 7/6/17.
 */

public class Triboid extends Agent{
    @Override
    public void render(ShapeRenderer shapeRenderer) {

        shapeRenderer.setColor(Color.RED);

        Vector2 vDir = getVelocity().cpy();

        vDir.setLength(5);

        Vector2 vecPerp1 = new Vector2(-vDir.y, vDir.x);
        Vector2 vecPerp2 = new Vector2(vDir.y, -vDir.x);

        Vector2 vEnd = new Vector2(getPosition()).sub(vDir);
        shapeRenderer.line(vEnd, vDir.add(getPosition()));

        vecPerp1.add(vEnd);
        vecPerp2.add(vEnd);

        shapeRenderer.line(vEnd.x, vEnd.y, vecPerp1.x, vecPerp1.y);
        shapeRenderer.line(vEnd.x, vEnd.y, vecPerp2.x, vecPerp2.y);

        shapeRenderer.line(vecPerp1.x, vecPerp1.y, vDir.x, vDir.y);
        shapeRenderer.line(vecPerp2.x, vecPerp2.y, vDir.x, vDir.y);

        renderSight(shapeRenderer);
    }

    public Triboid(Vector2 position) {
        super(position);
    }

    @Override
    protected void initAgent() {
        setVelocity(new Vector2());
        getVelocity().setToRandomDirection();

        setAcceleration(new Vector2());
        sightDistance = 80;
        maxSpeed = 9;
        maxForce = 0.1f;
    }
}
