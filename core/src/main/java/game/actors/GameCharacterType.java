package game.actors;


import static game.constants.TexturePaths.ICEMAGE_IDLE_TEXTURE;
import static game.constants.TexturePaths.KNIGHT_IDLE_TEXTURE;

public enum GameCharacterType {
    Warrior("Warrior", KNIGHT_IDLE_TEXTURE, 6, 1, 146, 102, 6),
    Mage("IceMage", ICEMAGE_IDLE_TEXTURE, 10, 1, 220, 200, 10);

    final String name;
    final String texturePath;
    final int columnAmount;
    final int rowAmount;
    final int width;
    final int height;
    final int endColumn;

    GameCharacterType(String name, String texturePath, int columnAmount, int rowAmount, int width, int height, int endColumn) {
        this.name = name;
        this.texturePath = texturePath;
        this.columnAmount = columnAmount;
        this.rowAmount = rowAmount;
        this.width = width;
        this.height = height;
        this.endColumn = endColumn;
    }
}
