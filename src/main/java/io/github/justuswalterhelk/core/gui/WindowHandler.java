package io.github.justuswalterhelk.core.gui;

import java.util.HashMap;

public class WindowHandler
{
    private static WindowHandler handler = null;

    private static HashMap<Long, Window> activeWindows = new HashMap<Long, Window>();

    private WindowHandler()
    {
        handler = this;
    }

    public static WindowHandler get()
    {
        if(handler == null) {handler = new WindowHandler();}
        return handler;
    }

    public Window runNewWindow(int width, int height, String title, boolean fullScreen)
    {
        Window window = new Window(width, height, title, fullScreen).initWindow().run();
        activeWindows.put(window.getWindowID(), window);
        return window;
    }

    public HashMap<Long, Window> getActiveWindows()
    {
        return activeWindows;
    }

}
