package main.java.game.items;

import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class ItemSet {

    private Item helm;
    private Item mainHand;
    private Item offHand;
    private Item chest;
    private Item gloves;
    private Item boots;
    private Item legs;
    private List<Item> allItems;

    public ItemSet(Item helm, Item mainHand, Item offHand, Item chest, Item gloves, Item boots, Item legs) {
        this.helm = helm;
        this.mainHand = mainHand;
        this.offHand = offHand;
        this.chest = chest;
        this.gloves = gloves;
        this.boots = boots;
        this.legs = legs;

        allItems = new ArrayList<>();
        allItems.add(helm);
        allItems.add(mainHand);
        allItems.add(offHand);
        allItems.add(chest);
        allItems.add(gloves);
        allItems.add(boots);
        allItems.add(legs);

    }

    public List<Item> getAllItems() {
        return allItems;
    }


}
