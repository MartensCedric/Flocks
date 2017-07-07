package com.cedricmartens.flocks.spawn;

import com.badlogic.gdx.math.Vector2;
import com.cedricmartens.flocks.Food;

/**
 * Created by martens on 7/7/17.
 */

public class FoodSpawner extends EntitySpawner {
    @Override
    public void spawnAt(float x, float y) {
        getEntities().add(new Food(new Vector2(x, y)));
    }
}
