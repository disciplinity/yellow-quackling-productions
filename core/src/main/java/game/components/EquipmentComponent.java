package main.java.game.components;

import lombok.*;
import main.java.game.items.ItemSet;

@Data
@ToString
public class EquipmentComponent {
    private ItemSet itemSet;

    public EquipmentComponent(ItemSet itemSet) {
        this.itemSet = itemSet;
    }
}

