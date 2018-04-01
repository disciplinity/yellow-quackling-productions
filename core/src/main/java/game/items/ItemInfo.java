package main.java.game.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemInfo {

    private String name;
    private String description;
    private TextureRegion txr;
}
