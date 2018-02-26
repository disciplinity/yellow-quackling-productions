package actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import components.GraphicsComponent;
import components.StatComponent;
import lombok.Getter;


public class IceMage extends Actor {


    private Animation<TextureRegion> standingAnimation;
    private float stateTime;
    @Getter
    private GraphicsComponent graphicsComponent;

    @Getter
    private StatComponent statComponent;
    public static IceMage currentlyChosen = null;
    private IceMage reference;
    private TextureRegion currentFrame;


    public IceMage(StatComponent statComponent) {

        setSize(220, 200);

        this.statComponent = statComponent;
        graphicsComponent = new GraphicsComponent("mage-standing.png", 10, 1);

        standingAnimation = new Animation<>(0.120f, graphicsComponent.getAnimationSheet(1, 1, 1, 10));
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


    @Override
    public void draw(Batch batch, float parentAlpha) {
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = standingAnimation.getKeyFrame(stateTime, true);

        super.draw(batch, parentAlpha);
        batch.draw(currentFrame, getX(), getY(), getWidth(), getHeight());
    }

}
