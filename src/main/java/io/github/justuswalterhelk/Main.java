package io.github.justuswalterhelk;

import io.github.justuswalterhelk.core.gui.TestContainer;
import io.github.justuswalterhelk.core.gui.Window;
import io.github.justuswalterhelk.core.gui.WindowHandler;

public class Main {

    public static void main(String[] args)
    {
        Window example = WindowHandler.get().initNewWindow(700, 500, "sefa", false);
        example.addContainer(new TestContainer()).initContainers();
        example.run();

    }
}