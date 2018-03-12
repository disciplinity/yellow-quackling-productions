package game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import game.components.GraphicsComponent;
import game.components.StatComponent;
import lombok.Getter;

import java.sql.Connection;
import java.util.Properties;


public class Knight extends Actor {


    private Animation<TextureRegion> standingAnimation;
    private float stateTime;
    @Getter
    private GraphicsComponent graphicsComponent;

    @Getter
    private StatComponent statComponent;
    public static Knight currentlyChosen = null;
    private Knight reference;
    private TextureRegion currentFrame;
    private boolean isOpponent = false;
    private Connection connection;




    public Knight(StatComponent statComponent) {

        setSize(146, 102);

        this.statComponent = statComponent;
        graphicsComponent = new GraphicsComponent("knight.png", 6, 1);

        standingAnimation = new Animation<>(0.120f, graphicsComponent.getAnimationSheet(1, 1, 1, 6));
        standingAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        stateTime = 0;
        reference = this;

        this.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("ay");
                currentlyChosen = reference;
                return false;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }

        });

   }

    public void setOpponent() {
        isOpponent = true;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = standingAnimation.getKeyFrame(stateTime, true);
        if (!currentFrame.isFlipX()) {
            currentFrame.flip(isOpponent, false);
        }
        batch.begin();
//        super.draw(batch, parentAlpha);
        batch.draw(currentFrame, getX(), getY(), getWidth(), getHeight());
    }
}
