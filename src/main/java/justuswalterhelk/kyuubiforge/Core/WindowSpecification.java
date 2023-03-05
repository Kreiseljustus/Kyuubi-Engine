package justuswalterhelk.kyuubiforge.Core;

public class WindowSpecification
{
    public WindowSpecification(int width, int height, String title, boolean isFullScreen)
    {
        this.width = width;
        this.height = height;
        this.title = title;
        this.isFullScreen = isFullScreen;
    }

    public int width;
    public int height;
    public String title;

    public boolean isFullScreen;
    public long windowID;
}
