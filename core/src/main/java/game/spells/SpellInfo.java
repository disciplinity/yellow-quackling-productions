package game.spells;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpellInfo {

    private String name;
    private String description;
    private TextureRegion txr;
}
