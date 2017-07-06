package com.cedricmartens.flocks;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.cedricmartens.flocks.agents.Agent;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by martens on 7/6/17.
 */

public class AgentSpawner
{
    private List<Agent> agents;
    public AgentSpawner(List<Agent> agents)
    {
        this.agents = agents;
    }

    public void spawnAgent(float x, float y, Class<? extends Agent> type)
    {
        try {
            Constructor<?> ctor = type.getConstructor(Vector2.class);
            agents.add((Agent) ctor.newInstance(new Vector2(x, y)));
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
