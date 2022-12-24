package io.github.justuswalterhelk.components;

import io.github.justuswalterhelk.datatypes.IComponent;
import io.github.justuswalterhelk.datatypes.Vector3;

public class PositionComponent extends IComponent
{
    public Vector3 position;

    public PositionComponent(Vector3 position)
    {
        this.position = position;
    }
}
