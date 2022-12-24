package io.github.justuswalterhelk.datatypes;

import io.github.justuswalterhelk.registries.ComponentRegistry;

public abstract class IComponent
{
    public final void register() {
        ComponentRegistry.register(this);
    }
    public void Awake() {}
    public void Start() {}
    public void Update() {}
}
