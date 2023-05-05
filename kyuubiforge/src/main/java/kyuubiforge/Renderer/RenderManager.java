package kyuubiforge.Renderer;

import kyuubiforge.Core.ILifeCycle;

import java.util.ArrayList;
import java.util.List;

public class RenderManager implements ILifeCycle {
    protected List<AbstractRenderLayer> renderLayers = new ArrayList<AbstractRenderLayer>();

    public <T extends AbstractRenderLayer> void addRenderer(T renderer)
    {
        renderer.init();

        renderLayers.add(renderer);

    }

    public AbstractRenderLayer getRenderer(int index)
    {
        return renderLayers.get(index);
    }

    @Override
    public void init() {
        for (AbstractRenderLayer renderLayer : renderLayers)
        {
            renderLayer.init();
        }
    }

    @Override
    public void update(float dt) {
        for (AbstractRenderLayer renderLayer : renderLayers)
        {
            renderLayer.update(dt);
        }
    }

    @Override
    public void dispose() {
        for (AbstractRenderLayer renderLayer : renderLayers)
        {
            renderLayer.dispose();
        }
    }
}
