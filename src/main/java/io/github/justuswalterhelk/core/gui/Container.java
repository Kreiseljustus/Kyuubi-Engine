package io.github.justuswalterhelk.core.gui;

public abstract class Container
{
    public String containerName = null;
    public String containerDescription;

    public void render() {}

    public void init() {}
    public void update(float deltaTime) {}
}
