package kyuubiforge.Renderer;

import java.util.ArrayList;
import java.util.List;

public class RenderManager {
    protected List<IRenderLayer> renderLayers = new ArrayList<IRenderLayer>();

    public <T extends IRenderLayer> void addRenderer(T renderer)
    {
        renderer.init();

        renderLayers.add(renderer);

    }

    public IRenderLayer getRenderer(int index)
    {
        return renderLayers.get(index);
    }
}
