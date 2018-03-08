package game;

import game.battle.BattleStageGroup;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import lombok.Getter;
import game.models.CombatSetup;
import game.screens.InBattleScreen;
import game.screens.StartMenuScreen;

import static game.factory.CombatSetupTestFactory.createCombatGroupExample1;
import static game.factory.CombatSetupTestFactory.createCombatGroupExample2;

// TODO: NOW IT'S JUST A MODEL FOR SPRITE BATCH :D
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