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
import com.cedricmartens.flocks.Food;
import com.cedricmartens.flocks.agent.Agent;
import com.cedricmartens.flocks.obstacle.Obstacle;
import com.cedricmartens.flocks.spawn.AgentSpawner;
import com.cedricmartens.flocks.spawn.FoodSpawner;
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
    private FoodSpawner foodSpawner;

    public PlayScreen()
    {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        agentSpawner = new AgentSpawner();
        obstacleSpawner = new ObstacleSpawner();
        foodSpawner = new FoodSpawner();

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

        List<Entity> agents = agentSpawner.getEntities();
        for(int i = 0; i < agents.size(); i++)
        {
            Agent agent = (Agent) agents.get(i);
            agent.applyBehaviours(foodSpawner.getEntities(), agentSpawner.getEntities(), obstacleSpawner.getEntities());
        }

        for(int i = 0; i < agents.size(); i++)
        {
            Agent agent = (Agent) agents.get(i);

            agent.update();
        }

        shapeRenderer.begin();

        shapeRenderer.setProjectionMatrix(getCamera().combined);

        List<Entity> foods = foodSpawner.getEntities();
        for(int i = 0; i < foods.size(); i++)
        {
            Food f = (Food) foods.get(i);
            f.render(shapeRenderer);
        }

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

    public FoodSpawner getFoodSpawner() {
        return foodSpawner;
    }
}
