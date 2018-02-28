//package network.kryonet.ui_frame;
//
//import com.badlogic.gdx.ApplicationAdapter;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.InputMultiplexer;
//import com.badlogic.gdx.InputProcessor;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.*;
//import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.badlogic.gdx.utils.Align;
//import com.badlogic.gdx.utils.Timer;
//import com.badlogic.gdx.utils.viewport.ScreenViewport;
//
//public class MyUITest extends ApplicationAdapter implements InputProcessor {
//    private Stage stage;
//    private Skin skin;
//
//    private Table table;
//    private Label nameLabel;
//    private Label addressLabel;
//    private TextField nameField;
//    private TextField addressField;
//    private TextButton connectButton;
//
//    /**
//     * Legacy
//     */
//    private SpriteBatch batch;
//    private TextButton startButton;
//    private TextButton quitButton;
//    private Sprite sprite;
//
//    @Override
//    public void create() {
//        skin = new Skin(Gdx.files.internal("data/uiskin.json"));
//        stage = new Stage(new ScreenViewport());
//
//        drawTestConnectionMenu();
////        drawStartAndQuitButtons();
////        drawTestButtonAndShowDialog();
//
//        // Now our stage can handle I/O events; Previously here was a second arg "this", so background could be clicked and processed
//        InputMultiplexer im = new InputMultiplexer(stage);
//        Gdx.input.setInputProcessor(im);
//    }
//
//    @Override
//    public void render() {
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        stage.act(Gdx.graphics.getDeltaTime());
//        stage.draw();
//    }
//
//    private void drawTestConnectionMenu() {
//        table = new Table();
//        table.setFillParent(true);
//        stage.addActor(table);
//
//        nameField = new TextField("", skin);
//        nameLabel = new Label("Name:", skin);
//        addressField = new TextField("", skin);
//        addressLabel = new Label("Address:", skin);
//        connectButton = new TextButton("Connect", skin);
//
//        connectButton.addListener(new ClickListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                Gdx.app.log("Clicked", "Yes, you did");
//                System.out.println(nameField.getText());
//            }
//        });
//
//        table.defaults().uniform().space(10).width(100);
//        table.add(nameLabel);
//        table.add(nameField);
//        table.row();
//        table.add(addressLabel);
//        table.add(addressField);
//        table.row();
//        table.add(connectButton).colspan(2);
//        // Use table
////        textFieldUsername.setPosition();
////        textFieldUsername.setSize();
////        stage.addActor(textFieldUsername);
//
//        System.out.println(nameField.getText());
//
//    }
//
//    /**
//     * King of Legacy
//     */
//    private void drawStartAndQuitButtons() {
//        table = new Table();
//        table.setWidth(stage.getWidth());
//        table.align(Align.center| Align.top);
//
//        table.setPosition(0, Gdx.graphics.getHeight());
//
//        startButton = new TextButton("New Game", skin);
//        quitButton = new TextButton("Quit Game", skin);
//
//        startButton.addListener(new ClickListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                Gdx.app.log("Clicked", "Yes, you did");
//            }
//        });
//        table.padTop(30);
//        table.add(startButton).padBottom(30);
//        table.row();
//        table.add(quitButton);
//
//        stage.addActor(table);
//
//        batch = new SpriteBatch();
//        sprite = new Sprite(new Texture(Gdx.files.internal("data/badlogic.jpg")));
//        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//    }
//
//    /**
//     * Legacy
//     */
//    private void drawTestButtonAndShowDialog() {
//        final TextButton button = new TextButton("Click Me", skin, "default");
//        button.setWidth(200);
//        button.setHeight(50);
//
//        final Dialog dialog = new Dialog("Sofa king font presents", skin);
//        // Show dialog after a click.
//        button.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                dialog.show(stage);
//                // Add timer to hide after showing.
//                Timer.schedule(new Timer.Task(){
//                    @Override
//                    public void run() {
//                        dialog.hide();
//                    }
//                }, 3);
//            }
//        });
//        stage.addActor(button);
//    }
//
//    @Override
//    public boolean keyDown(int keycode) {
//        return false;
//    }
//
//    @Override
//    public boolean keyUp(int keycode) {
//        return false;
//    }
//
//    @Override
//    public boolean keyTyped(char character) {
//        return false;
//    }
//
//    @Override
//    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        sprite.setFlip(!sprite.isFlipX(), sprite.isFlipY());
//        return true;
//    }
//
//    @Override
//    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//        return false;
//    }
//
//    @Override
//    public boolean touchDragged(int screenX, int screenY, int pointer) {
//        return false;
//    }
//
//    @Override
//    public boolean mouseMoved(int screenX, int screenY) {
//        return false;
//    }
//
//    @Override
//    public boolean scrolled(int amount) {
//        return false;
//    }
//}
