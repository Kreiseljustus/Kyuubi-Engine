package kyuubiforge.Core.Window;
/**
* Represents the current state of the window
 */
public enum WindowState {
    /**
     * <b>Default State</b><br>
     * The window is open.
     */
    OPEN,
    /**
     * The window is closed and will be picked up by garbage collection.
     */
    CLOSED,
    /**
     * The window is hidden. <br>
     * <b>Rendering will be paused during this duration!</b>
     */
    HIDDEN,
    /**
     * The window is minimized.<br>
     * <b>Rendering will be paused during this duration!</b>
     */
    MINIMIZED,
    /**
     * The window is maximized.
     */
    MAXIMIZED,
    /**
     * The window can be resized to any resolution
     */
    RESIZEABLE,
    /**
     * The window is disabled. <br>
     * It cannot be interacted with until its enabled again.
     */
    DISABLED,
    /**
     * The window needs a user interaction before the rest of the application becomes intractable again. <br>
     * Think of it like a message box.
     */
    MODAL;
}
