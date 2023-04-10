package kyuubiforge.Graphics;

public enum TextureWrapMode {
    CLAMP (0x2900),
     REPEAT(0x2901);

    private int value;

    private TextureWrapMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
