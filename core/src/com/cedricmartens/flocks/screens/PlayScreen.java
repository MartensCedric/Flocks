package com.cedricmartens.flocks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cedricmartens.flocks.Entity;
import com.cedricmartens.flocks.agent.Agent;
import com.cedricmartens.flocks.obstacle.Obstacle;
import com.cedricmartens.flocks.spawn.AgentSpawner;
import com.cedricmartens.flocks.spawn.ObstacleSpawner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martens on 7/5/17.
 */
public class PlayScreen extends StageScreen{

    private ShapeRenderer shapeRenderer;
    private GestureDetector detector;
    private PlayScreenGesture behavior;
    private ObstacleSpawner obstacleSpawner;
    private AgentSpawner agentSpawner;

    public PlayScreen()
    {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        agentSpawner = new AgentSpawner();
        obstacleSpawner = new ObstacleSpawner();

        setMultiplexer();
    }

    private void setMultiplexer()
    {
        behavior = new PlayScreenGesture(this);
        detector = new GestureDetector(behavior);
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(getStage());
        inputMultiplexer.addProcessor(detector);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Viewport viewport = getStage().getViewport();
        Vector3 target = getCamera().unproject(
                new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0),
                viewport.getScreenX(), viewport.getScreenY(),
                viewport.getScreenWidth(), viewport.getScreenHeight());

        List<Entity> agents = agentSpawner.getEntities();
        for(int i = 0; i < agents.size(); i++)
        {
            Agent agent = (Agent) agents.get(i);
            agent.applyBehaviours(new Vector2(target.x, target.y), agents, obstacleSpawner.getEntities());
        }

        for(int i = 0; i < agents.size(); i++)
        {
            Agent agent = (Agent) agents.get(i);

            agent.update();
        }

        shapeRenderer.begin();

        shapeRenderer.setProjectionMatrix(getCamera().combined);

        for(int i = 0; i < agents.size(); i++)
        {
            Agent agent = (Agent) agents.get(i);
            agent.render(shapeRenderer);
        }

        List<Entity> obstacles = obstacleSpawner.getEntities();
        for(int i = 0; i < obstacles.size(); i++)
        {
            Obstacle obstacle = (Obstacle) obstacles.get(i);
            obstacle.render(shapeRenderer);
        }

        shapeRenderer.end();

        super.render(delta);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void show() {
        setMultiplexer();
    }

    public ObstacleSpawner getObstacleSpawner() {
        return obstacleSpawner;
    }

    public AgentSpawner getAgentSpawner() {
        return agentSpawner;
    }
}
