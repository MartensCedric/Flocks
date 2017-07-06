package com.cedricmartens.flocks.spawn;

import com.badlogic.gdx.math.Vector2;
import com.cedricmartens.flocks.Entity;
import com.cedricmartens.flocks.agent.Agent;
import com.cedricmartens.flocks.agent.Circloid;
import com.cedricmartens.flocks.agent.Squarel;
import com.cedricmartens.flocks.agent.Triboid;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;

/**
 * Created by martens on 7/6/17.
 */

public class AgentSpawner extends EntitySpawner
{

    @Override
    public void spawnAt(float x, float y) {
        Class<? extends Agent> spawn;
        Random random = new Random();

        int index = random.nextInt(3);

        switch (index)
        {
            case 0 :
                spawn = Circloid.class;
                break;
            case 1 :
                spawn = Triboid.class;
                break;
            case 2 :
                spawn = Squarel.class;
                break;
            default:
                throw new RuntimeException();
        }

        spawnAt(x, y, spawn);
    }

    public void spawnAt(float x, float y, Class<? extends Agent> type) {

        try {
            Constructor<?> ctor = type.getConstructor(Vector2.class);
            getEntities().add((Entity) ctor.newInstance(new Vector2(x, y)));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
