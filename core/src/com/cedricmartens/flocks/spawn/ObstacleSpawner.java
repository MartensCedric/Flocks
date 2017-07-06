package com.cedricmartens.flocks.spawn;

import com.badlogic.gdx.math.Vector2;
import com.cedricmartens.flocks.obstacle.Wall;

/**
 * Created by martens on 7/6/17.
 */

public class ObstacleSpawner extends EntitySpawner
{
    @Override
    public void spawnAt(float x, float y) {
        getEntities().add(new Wall(new Vector2(x, y)));
    }
}
