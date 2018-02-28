package models;

import com.badlogic.gdx.scenes.scene2d.Actor;
import lombok.Getter;

public class CombatGroup {
    @Getter
    private Actor[] actorGroup;

    public CombatGroup(Actor actor1, Actor actor2, Actor actor3) {
        actorGroup = new Actor[]{actor1, actor2, actor3};
    }
}
