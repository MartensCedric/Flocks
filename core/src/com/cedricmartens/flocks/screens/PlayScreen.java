package com.cedricmartens.flocks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cedricmartens.flocks.agents.Agent;
import com.cedricmartens.flocks.agents.Circloid;
import com.cedricmartens.flocks.agents.Triboid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martens on 7/5/17.
 */
public class PlayScreen extends StageScreen{

    private List<Agent> agents;
    private ShapeRenderer shapeRenderer;
    private GestureDetector detector;
    private PlayScreenGesture behavior;

    public PlayScreen()
    {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        agents = new ArrayList<Agent>();

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

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
        {
         //   agents.add(new Circloid(new Vector2(target.x, target.y)));
        }

        if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT))
        {
            //agents.add(new Triboid(new Vector2(target.x, target.y)));
        }

        for(int i = 0; i < agents.size(); i++)
        {
            Agent agent = agents.get(i);
            agent.applyBehaviours(new Vector2(target.x, target.y), agents);
        }

        for(int i = 0; i < agents.size(); i++)
        {
            Agent agent = agents.get(i);

            agent.update();
        }

        shapeRenderer.begin();

        shapeRenderer.setProjectionMatrix(getCamera().combined);
        for(int i = 0; i < agents.size(); i++)
            agents.get(i).render(shapeRenderer);

        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.circle( 0,  0, 25);
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

    public List<Agent> getAgents() {
        return agents;
    }
}
