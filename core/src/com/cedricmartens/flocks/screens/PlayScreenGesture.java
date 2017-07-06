package com.cedricmartens.flocks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cedricmartens.flocks.AgentSpawner;
import com.cedricmartens.flocks.agents.Agent;
import com.cedricmartens.flocks.agents.Circloid;
import com.cedricmartens.flocks.agents.Triboid;

import java.util.List;

/**
 * Created by martens on 7/6/17.
 */

public class PlayScreenGesture implements GestureDetector.GestureListener {

    private PlayScreen playScreen;
    private OrthographicCamera camera;
    private Stage stage;
    private AgentSpawner spawner;
    private Class<? extends Agent> currentSpawn;

    public PlayScreenGesture(PlayScreen playScreen) {
        this.playScreen = playScreen;
        this.spawner = new AgentSpawner(playScreen.getAgents());
        this.camera = playScreen.getCamera();
        this.stage = playScreen.getStage();
        currentSpawn = Circloid.class;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {

        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {

        Vector2 wc = toWorldCoords(x, y);

        if(button == Input.Buttons.LEFT)
            currentSpawn = Circloid.class;

        if(button == Input.Buttons.RIGHT)
            currentSpawn = Triboid.class;

        spawner.spawnAgent(wc.x, wc.y, currentSpawn);
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
