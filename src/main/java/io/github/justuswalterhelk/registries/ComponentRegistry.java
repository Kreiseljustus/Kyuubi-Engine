package io.github.justuswalterhelk.registries;

import io.github.justuswalterhelk.datatypes.IComponent;

import java.util.LinkedList;
import java.util.List;

public class ComponentRegistry
{
    public static List<IComponent> components = new LinkedList<IComponent>();

    public static void register(IComponent component)
    {
        components.add(component);
    }
}
