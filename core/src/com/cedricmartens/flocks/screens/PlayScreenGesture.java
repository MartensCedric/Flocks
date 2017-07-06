package com.cedricmartens.flocks.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.cedricmartens.flocks.agents.Agent;

import java.util.List;

/**
 * Created by martens on 7/6/17.
 */

public class PlayScreenGesture implements GestureDetector.GestureListener {

    private PlayScreen playScreen;
    private List<Agent> agents;
    private OrthographicCamera camera;
    private Stage stage;

    public PlayScreenGesture(PlayScreen playScreen) {
        this.playScreen = playScreen;
        this.agents = playScreen.getAgents();
        this.camera = playScreen.getCamera();
        this.stage = playScreen.getStage();
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {

        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
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
}
