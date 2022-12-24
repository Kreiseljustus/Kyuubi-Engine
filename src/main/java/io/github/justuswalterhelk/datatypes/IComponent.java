package io.github.justuswalterhelk.datatypes;

import io.github.justuswalterhelk.registries.ComponentRegistry;

public abstract class IComponent
{
    private void register() {
        ComponentRegistry.register(this);
    }
    private void Awake() {}
    private void Start() {}
    private void Update() {}
}
