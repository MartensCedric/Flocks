package com.cedricmartens.flocks.agents;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.cedricmartens.flocks.Const;
import com.cedricmartens.flocks.MathUtils;

import java.util.List;
import java.util.Random;

/**
 * Created by martens on 7/5/17.
 */
public abstract class Agent
{
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    protected float maxSpeed;
    protected float maxForce;

    protected float sightDistance;
    protected float sightDegrees;


    public Agent(Vector2 position) {
        this.position = position;
        initAgent();
    }

    public Agent()
    {
        Random random = new Random();
        position = new Vector2();
        position.set(random.nextInt(Const.WIDTH), random.nextInt(Const.HEIGHT));
        initAgent();
    }

    protected abstract void initAgent();

    public Vector2 seek(Vector2 target)
    {
        Vector2 desired = new Vector2(target).sub(position);

        desired.nor();
        desired.scl(maxSpeed);

        Vector2 steering = new Vector2(desired).sub(velocity);
        steering.limit(maxForce);

        return steering;
    }

    public Vector2 flee(Vector2 target)
    {
        Vector2 desired = new Vector2(target).add(position);

        desired.nor();
        desired.scl(maxSpeed);

        Vector2 steering = new Vector2(desired).sub(velocity);
        steering.limit(maxForce);

        return steering;
    }

    public Vector2 arrive(Vector2 target)
    {
        Vector2 desired = new Vector2(target).sub(position);
        float d = desired.len();

        if(d < sightDistance)
        {
            float m = MathUtils.map(d, 0, sightDistance, 0, maxSpeed);
            desired.setLength(m);
        }else{
            desired.setLength(maxSpeed);
        }

        Vector2 steering = new Vector2(desired).sub(velocity);
        steering.limit(maxForce);
        return steering;
    }

    public Vector2 align(List<Agent> agents)
    {
        Vector2 sum = new Vector2(0, 0);
        int count = 0;

        for(Agent other : agents)
        {
            if(this.getClass() == other.getClass())
            {
                float d = position.dst(other.position);
                if(d > 0 && d < sightDistance/2)
                {
                    sum.add(other.velocity);
                    count++;
                }
            }
        }

        if(count > 0)
        {
            sum.setLength(maxSpeed);
            Vector2 steering = new Vector2(sum).sub(velocity);
            steering.limit(maxForce);
            return steering;
        }

        return Vector2.Zero;
    }

    public Vector2 separate (List<Agent> agents) {

        Vector2 sum = new Vector2(0, 0);
        int count = 0;

        for (Agent other : agents) {
            float d = position.dst(other.position);

            if (d > 0 && d < sightDistance) {

                Vector2 diff = new Vector2(position).sub(other.position);
                diff.nor();
                diff.scl(1/d);
                sum.add(diff);
                count++;
            }
        }

        if (count > 0) {

            sum.setLength(maxSpeed);

            Vector2 steering = new Vector2(sum).sub(velocity);
            steering.limit(maxForce);
            return steering;
        }

        return Vector2.Zero;
    }

    public void applyForce(Vector2 force)
    {
        acceleration.add(force);
    }

    public void applyBehaviours(Vector2 target, List<Agent> agents)
    {
        Vector2 separateForce = separate(agents);
        Vector2 seekForce = seek(target);
        Vector2 fleeForce = fleeInSight(new Vector2(0, 0));

        separateForce.scl(2);
        seekForce.scl(1);
        fleeForce.scl(5);

        applyForce(separateForce);
        applyForce(seekForce);
        applyForce(fleeForce);
    }

    public Vector2 fleeInSight(Vector2 target)
    {
        float d = target.dst(position);
        if(d < sightDistance)
        {
            return flee(target);
        }

        return Vector2.Zero;
    }

    public void update()
    {
        velocity.add(acceleration);
        velocity.limit(maxSpeed);

        position.add(velocity);

        acceleration.setLength(0);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = acceleration;
    }

    public abstract void render(ShapeRenderer shapeRenderer);
}
