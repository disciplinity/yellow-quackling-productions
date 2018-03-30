package main.java.game.components;

import lombok.AllArgsConstructor;
import lombok.Data;
import main.java.game.items.Item;

@Data
@AllArgsConstructor
public class EquipmentComponent {
    private Item helm;
    private Item chest;
    private Item legs;
    private Item gloves;
    private Item boots;
    private Item mainHand;
    private Item offHand;

}

