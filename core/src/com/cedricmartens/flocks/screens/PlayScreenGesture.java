package com.cedricmartens.flocks.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cedricmartens.flocks.spawn.AgentSpawner;
import com.cedricmartens.flocks.spawn.FoodSpawner;
import com.cedricmartens.flocks.spawn.ObstacleSpawner;

/**
 * Created by martens on 7/6/17.
 */

public class PlayScreenGesture implements GestureDetector.GestureListener {

    private PlayScreen playScreen;
    private OrthographicCamera camera;
    private Stage stage;
    private AgentSpawner agentSpawner;
    private ObstacleSpawner obstacleSpawner;
    private FoodSpawner foodSpawner;

    public PlayScreenGesture(PlayScreen playScreen) {
        this.playScreen = playScreen;
        this.agentSpawner = playScreen.getAgentSpawner();
        this.obstacleSpawner = playScreen.getObstacleSpawner();
        this.camera = playScreen.getCamera();
        this.stage = playScreen.getStage();
        this.foodSpawner = playScreen.getFoodSpawner();
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {

        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {

        Vector2 wc = toWorldCoords(x, y);

        if(button == Input.Buttons.LEFT)
            agentSpawner.spawnAt(wc.x, wc.y);
        else if (button == Input.Buttons.RIGHT)
            foodSpawner.spawnAt(wc.x, wc.y);

        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }

    private Vector2 toWorldCoords(float x, float y)
    {
        Viewport viewport = stage.getViewport();
        Vector3 unp = camera.unproject(new Vector3(x, y, 0),
                viewport.getScreenX(), viewport.getScreenY(),
                viewport.getScreenWidth(), viewport.getScreenHeight());
        return new Vector2(unp.x, unp.y);
    }
}
