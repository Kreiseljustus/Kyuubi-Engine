package kyuubiforge.Core.Window;

import kyuubiforge.Core.IImGuiLayer;

/**
 * Contains vital settings for the window creation.
 * <b>Also used to retrieve current resolution and title</b>
 */

public class WindowSpecification
{
    public WindowSpecification(int width, int height, String title)
    {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public WindowSpecification(int width, int height, String title, boolean isFullScreen)
    {
        this.width = width;
        this.height = height;
        this.title = title;
        this.isFullScreen = isFullScreen;
    }

    public WindowSpecification(int width, int height, String title, boolean isFullScreen, boolean isResizeable)
    {
        this.width = width;
        this.height = height;
        this.title = title;
        this.isFullScreen = isFullScreen;
        this.isResizeable = isResizeable;
    }

    public WindowSpecification(int width, int height, String title, boolean isFullScreen, boolean isResizeable, IImGuiLayer imGuiLayer)
    {
        this.width = width;
        this.height = height;
        this.title = title;
        this.isFullScreen = isFullScreen;
        this.isResizeable = isResizeable;
        this.imGuiLayer = imGuiLayer;
    }

    public int width;
    public int height;
    public String title;

    public boolean isFullScreen = true;
    public boolean isResizeable = true;

    public boolean isDecorated = false;

    public long windowID;

    public IImGuiLayer imGuiLayer = null;
}
