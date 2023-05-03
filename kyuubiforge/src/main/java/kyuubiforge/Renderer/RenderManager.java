package kyuubiforge.Renderer;

import java.util.ArrayList;
import java.util.List;

public class RenderManager {
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
}
