package actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import lombok.Data;
import lombok.Getter;


public class IceMage extends Actor {

    private TextureRegion[][] textureRegion;
    private Texture spriteSheet;
    private Animation<TextureRegion> standingAnimation;
    private float stateTime;
    private int x, y, width, height;
    private boolean flip;
    private final int COLUMN_NUMBER = 5;


    public IceMage(int x, int y, int width, int height, boolean flip) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.flip = flip;

        spriteSheet = new Texture("spritesheet.png");
        textureRegion = TextureRegion.split(spriteSheet,
                spriteSheet.getWidth() / COLUMN_NUMBER,
                spriteSheet.getHeight());
        this.setPosition(0, 0);

        standingAnimation = new Animation<>(0.066f, textureRegion[0]);
        standingAnimation.setPlayMode(Animation.PlayMode.LOOP);
        stateTime = 0;
   }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = standingAnimation.getKeyFrame(stateTime, false);
        batch.draw(currentFrame, flip ? x+width : x, y, flip ? -width : width, height);



    }


}
