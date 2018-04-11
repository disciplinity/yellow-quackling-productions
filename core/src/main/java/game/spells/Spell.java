package game.spells;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.components.SpellBookComponent;
import game.utils.FontGenerator;
import lombok.Setter;

public class Spell extends Actor {

    @Setter
    private SpellSlot spellSlot;
    private SpellInfo spellInfo;
    @Setter
    private ShapeRenderer sr;
    @Setter
    private FontGenerator fontGenerator;

    private boolean hovered;
    @Setter
    private boolean clicked;

    private Spell reference;
    ParticleEffect pe;


    public Spell(SpellInfo spellInfo) {
        this.spellInfo = spellInfo;
        this.reference = this;


        this.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                spellSlot.setHovered(true);
                hovered = true;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                spellSlot.setHovered(false);
                hovered = false;
            }

        });

        this.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (SpellBookComponent.currentSpellChosen != null) {
                    SpellBookComponent.currentSpellChosen.setClicked(false);
                }
                if (SpellBookComponent.currentSpellChosen == reference) {
                    clicked = false;
                    SpellBookComponent.currentSpellChosen = null;

                } else {
                    clicked = true;
                    SpellBookComponent.currentSpellChosen = reference;
                }

            }
        });


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
//        pe.update(Gdx.graphics.getDeltaTime());
//        pe.setPosition(prev, Gdx.graphics.getHeight() / 2);
        batch.begin();
//        pe.draw(batch);
        batch.draw(spellInfo.getTxr(), spellSlot.getX() + 1, spellSlot.getY() + 1, spellSlot.getWidth() - 2, spellSlot.getHeight() - 2);
        batch.end();

        if (hovered) {
            drawTextBox();
            writeTextToTextBox(batch);
        }

        if (clicked) {
            drawBorderAroundSpell();
        }



    }

    private void drawBorderAroundSpell() {
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(Color.GREEN);
        sr.rect(getX() + 2, getY() + 2, 46, 46);
        sr.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private void drawTextBox() {
        // Main frame
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(0.788f, 0.69f, 0.478f, 0.8f);
        sr.rect(getX() + 25, getY() + 60, 300, 200);

        // Now draw border for it
        sr.set(ShapeRenderer.ShapeType.Line);
        sr.setColor(Color.valueOf("51310f"));
        sr.rect(getX() + 25, getY() + 60, 300, 200);
        sr.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private void writeTextToTextBox(Batch batch) {
        batch.begin();

        // Name of the item
        fontGenerator.parameter.size = 16;
        fontGenerator.parameter.color = Color.FOREST;
        fontGenerator.parameter.borderWidth = 3;
        fontGenerator.parameter.borderColor = Color.BLACK;


        fontGenerator.font12 = fontGenerator.getGenerator().generateFont(fontGenerator.getParameter());
        fontGenerator.font12.draw(batch, spellInfo.getName(), getX() + 30, getY() + 240);

        // Description of the item
        fontGenerator.parameter.size = 15;
        fontGenerator.parameter.color = Color.valueOf("e06328");
        fontGenerator.parameter.borderWidth = 2;

        fontGenerator.parameter.shadowColor = Color.valueOf("682708");

        fontGenerator.font12 = fontGenerator.getGenerator().generateFont(fontGenerator.getParameter());
        fontGenerator.font12.draw(batch, spellInfo.getDescription(), getX() + 30, getY() + 220);

        batch.end();
    }


}
