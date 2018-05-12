//package game.actors;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Animation;
//import com.badlogic.gdx.graphics.g2d.Batch;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.scenes.scene2d.Actor;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.InputListener;
//import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import main.java.game.components.GraphicsComponent;
//import main.java.game.components.StatComponent;
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//import game.constants.CharacterSizes;
//
//
//public class Archer extends Actor {
//
//    private TextureRegion[][] textureRegion;
//
//    private Animation<TextureRegion> standingAnimation;
//    private float stateTime;
//    private int x, y, width, height;
//    private boolean flip;
//    private GraphicsComponent graphicsComponent;
//
//    @Getter
//    private StatComponent statComponent;
//    public static IceMage currentlyChosen = null;
//    private IceMage reference;
//
//
//    public Archer(StatComponent statComponent, int x, int y, boolean flip) {
//        this.x = x;
//        this.y = y;
//        setWidth(CharacterSizes.ARCHER_WIDTH);
//        setHeight(CharacterSizes.ARCHER_HEIGHT);
//        this.flip = flip;
//        this.statComponent = statComponent;
//
//        graphicsComponent = new GraphicsComponent("mage-standing.png", 10, 1);
//        textureRegion = graphicsComponent.getTextureRegions();
//
//        TextureRegion[] animationSheet = new TextureRegion[COLUMN_NUMBER * ROW_NUMBER - emptySpaces];
//        for (int i = 0, counter = 0; i < ROW_NUMBER; i++) {
//            for (int j = 0; j < COLUMN_NUMBER; j++) {
//                // Stop adding sprites for animation if encountered empty cell when empty cell
//                if (i == ROW_NUMBER - 1 && j == COLUMN_NUMBER - emptySpaces) {
//                    break;
//                }
//                animationSheet[counter++] = textureRegion[i][j];
//            }
//        }
//        standingAnimation = new Animation<>(0.120f, animationSheet);
//        standingAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
//        stateTime = 0;
//
//        this.setBounds(x, y, width, height);
//
//
//        this.addListener(new InputListener() {
//            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                currentlyChosen = reference;
//                return true;
//            }
//
//            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
//                System.out.println("up");
//            }
//        });
//        reference = this;
//    }
//
//
//    @Override
//    public void draw(Batch batch, float parentAlpha) {
//        stateTime += Gdx.graphics.getDeltaTime();
//        TextureRegion currentFrame = standingAnimation.getKeyFrame(stateTime, false);
//        batch.draw(currentFrame, flip ? x+width : x, y, flip ? -width : width, height);
//    }
//
//}
