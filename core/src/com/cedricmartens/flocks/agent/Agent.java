package com.cedricmartens.flocks.agent;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.cedricmartens.flocks.Const;
import com.cedricmartens.flocks.Entity;
import com.cedricmartens.flocks.MathUtils;
import com.cedricmartens.flocks.obstacle.Obstacle;

import java.util.List;
import java.util.Random;

/**
 * Created by martens on 7/5/17.
 */
public abstract class Agent extends Entity
{
    private Vector2 velocity;
    private Vector2 acceleration;

    protected float maxSpeed;
    protected float maxForce;

    protected float sightDistance;
    protected float sightDegrees;


    public Agent(Vector2 position) {
        setPosition(position);
        initAgent();
    }

    public Agent()
    {
        Random random = new Random();
        setPosition(new Vector2());
        getPosition().set(random.nextInt(Const.WIDTH), random.nextInt(Const.HEIGHT));
        initAgent();
    }

    protected abstract void initAgent();

    public Vector2 seek(Vector2 target)
    {
        Vector2 desired = new Vector2(target).sub(getPosition());

        desired.nor();
        desired.scl(maxSpeed);

        Vector2 steering = new Vector2(desired).sub(velocity);
        steering.limit(maxForce);

        return steering;
    }

    public Vector2 flee(Vector2 target)
    {
        Vector2 desired = new Vector2(target).add(getPosition());

        desired.nor();
        desired.scl(maxSpeed);

        Vector2 steering = new Vector2(desired).sub(velocity);
        steering.limit(maxForce);

        return steering;
    }

    public Vector2 arrive(Vector2 target)
    {
        Vector2 desired = new Vector2(target).sub(getPosition());
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
                float d = getPosition().dst(other.getPosition());
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

    public Vector2 separate (List<Entity> agents) {

        Vector2 sum = new Vector2(0, 0);
        int count = 0;

        for (Entity other : agents) {
            float d = getPosition().dst(other.getPosition());

            if (d > 0 && d < sightDistance) {

                Vector2 diff = new Vector2(getPosition()).sub(other.getPosition());
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

    public void applyBehaviours(Vector2 target, List<Entity> agents, List<Entity> obstacles)
    {
        Vector2 separateForce = separate(agents);
        Vector2 seekForce = seek(target);

        separateForce.scl(2);
        seekForce.scl(1);

        applyForce(separateForce);
        applyForce(seekForce);

        for(int i = 0; i < obstacles.size(); i++)
        {
            Obstacle obstacle = (Obstacle) obstacles.get(i);
            Vector2 fleeForce = fleeInSight(obstacle.getPosition());
            fleeForce.scl(obstacle.getRepulsionForce());

            applyForce(fleeForce);
        }
    }

    public Vector2 fleeInSight(Vector2 target)
    {
        float d = target.dst(getPosition());
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

        getPosition().add(velocity);

        acceleration.setLength(0);
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
