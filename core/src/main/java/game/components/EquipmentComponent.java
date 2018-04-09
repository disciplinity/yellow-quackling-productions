package game.components;

import lombok.*;
import game.items.ItemSet;

@Data
@ToString
public class EquipmentComponent {
    private ItemSet itemSet;

    public EquipmentComponent(ItemSet itemSet) {
        this.itemSet = itemSet;
    }
}

