package kyuubiforge.Graphics;

public enum TextureFilter {

    NEAREST(0x2600),
    LINEAR(0x2601);

    private int value;

    private TextureFilter(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
