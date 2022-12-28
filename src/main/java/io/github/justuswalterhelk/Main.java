package io.github.justuswalterhelk;

import io.github.justuswalterhelk.core.gui.Window;
import io.github.justuswalterhelk.core.gui.WindowHandler;

import java.io.IOException;

public class Main {

    public static void main(String[] args)
    {
        Window example = WindowHandler.get().runNewWindow(600,800, "Foxfire", false);
    }
}