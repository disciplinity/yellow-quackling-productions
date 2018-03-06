package game;

import battle.BattleStageGroup;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import lombok.Getter;
import models.CombatSetup;
import screens.InBattleScreen;
import screens.StartMenuScreen;

import static factory.CombatSetupTestFactory.createCombatGroupExample1;
import static factory.CombatSetupTestFactory.createCombatGroupExample2;

public class MyGdxGame extends Game {


    @Getter
    private SpriteBatch spriteBatch;

    public void create () {
        spriteBatch = new SpriteBatch();

//        CombatSetup playerCS = createCombatGroupExample1();
//        CombatSetup opponentCS = createCombatGroupExample2();
//        BattleStageGroup bsg = new BattleStageGroup("fairy-forest.jpg", playerCS, opponentCS);
//        this.setScreen(new InBattleScreen(this, bsg));
        this.setScreen(new StartMenuScreen(this));
    }


    public void resize (int width, int height) {
    }

    public void setBattleScreen() {

        CombatSetup playerCS = createCombatGroupExample1();
        CombatSetup opponentCS = createCombatGroupExample2();
        BattleStageGroup bsg = new BattleStageGroup("fairy-forest.jpg", playerCS, opponentCS);
        this.setScreen(new InBattleScreen(this, bsg));
    }


    public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        screen.render(0.033f);
    }


    public void dispose() {
        screen.dispose();
        spriteBatch.dispose();
    }


}