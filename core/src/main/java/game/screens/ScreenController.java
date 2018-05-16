package game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.esotericsoftware.minlog.Log;
import game.MyGdxGame;
import game.actors.CharacterFactory;
import game.models.combat.BattleStageGroup;
import game.models.combat.CombatSetup;
import network.client.GameClient;
import network.manager.PlayerCombatInfo;

import java.io.IOException;

public class ScreenController {

    /**
     * If screen has an alert field, bar, etc. set it's message
     */
    public static void setScreenAlert(String message) {
        /*ConnectionTestScreen screen;
        if (MyGdxGame.getInstance().getScreen() instanceof  ConnectionTestScreen) {
            screen = (ConnectionTestScreen) MyGdxGame.getInstance().getScreen();
            screen.setAlert(message);
            screen.connectButton.setTouchable(Touchable.enabled);
        }
        Log.error("Wrong game screen");*/
        StartMenuScreen screen;
        if (MyGdxGame.getInstance().getScreen() instanceof StartMenuScreen) {
            screen = (StartMenuScreen) MyGdxGame.getInstance().getScreen();
            screen.setAlert(message);
            screen.connectButton.setTouchable(Touchable.enabled);
        }
        Log.error("Wrong game screen");
    }

    /**
     * Action from Connection Screen. Create Client and try to connect to the server. Act accordingly to the response.
     */
    static void authorize() {
        /*ConnectionTestScreen screen;
        if (!(MyGdxGame.getInstance().getScreen() instanceof  ConnectionTestScreen)) {
            return;
        }
        screen = (ConnectionTestScreen) MyGdxGame.getInstance().getScreen();
        screen.setAlert("Connecting...");
        screen.connectButton.setTouchable(Touchable.disabled);*/

        StartMenuScreen screen;
        if (!(MyGdxGame.getInstance().getScreen() instanceof  StartMenuScreen)) {
            return;
        }
        screen = (StartMenuScreen) MyGdxGame.getInstance().getScreen();
        screen.setAlert("Connecting...");
        screen.connectButton.setTouchable(Touchable.disabled);
        /* Should it be this way here? Thread is needed for async actions like networking. */
        new Thread(() -> {
            try {
                GameClient client = new GameClient();
                MyGdxGame.getInstance().setClient(client);
                client.sendCredentials(screen.nameField.getText(), screen.passField.getText());
            } catch (IOException e) {
                screen.setAlert("No connection to the server");
                screen.connectButton.setTouchable(Touchable.enabled);
            }
        }).start();
    }

    /**
     * Set lobby screen (after authorization or after battle)
     */
    public static void setLobbyScreen() {
        Gdx.app.postRunnable(() -> MyGdxGame.getInstance().setLobbyScreen());
    }

    /**
     * Draw Battle Screen for the player.
     */
    public static void setBattleScreen(PlayerCombatInfo player, PlayerCombatInfo opponent) {
        Gdx.app.postRunnable(() -> {
                    CombatSetup playerCS = CharacterFactory.createCombatSetupFrom(player.getHeroes());
                    CombatSetup opponentCS = CharacterFactory.createCombatSetupFrom(opponent.getHeroes());
                    String stageName = "fairy-forest.jpg";

                    MyGdxGame.getInstance().setScreen(new CombatScreen(new BattleStageGroup(stageName, playerCS, opponentCS)));
                });
    }

    public static void displayCombatActionResult() {
    }


}
