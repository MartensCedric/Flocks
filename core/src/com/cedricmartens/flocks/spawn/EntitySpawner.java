package com.cedricmartens.flocks.spawn;

import com.cedricmartens.flocks.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martens on 7/6/17.
 */

public abstract class EntitySpawner
{
    protected List<Entity> entities;

    public EntitySpawner() {
        setEntities(new ArrayList<Entity>());
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public abstract void spawnAt(float x, float y);

    public List<Entity> getEntities() {
        return entities;
    }
}
