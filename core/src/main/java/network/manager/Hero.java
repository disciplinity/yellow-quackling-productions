package network.manager;

import game.components.StatComponent;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Hero {
    private String heroName;
    private StatComponent stats;
}
