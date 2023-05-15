package kyuubiforge.Debug;

import kyuubiforge.Core.GameObject;
import kyuubiforge.Core.AbstractScene;

import static kyuubiforge.Debug.Debug.log;

public class TestScene extends AbstractScene {
    @Override
    public void update(float dt) {
        for(GameObject g : this.gameObjects)
        {
            g.update(dt);
        }
    }

    @Override
    public void init() {
        log("[KyuubiForge] TestScene Init");
        GameObject testRenderer = new GameObject("TestRenderer");
        testRenderer.addComponent(new TestRenderer());
        GameObject testRenderButBetter = new GameObject("TestRendererButBetter");
        TestRenderer test = new TestRenderer();
        test.vertexArray = new float[]{
                // position               // color                  // UV Coordinates
                200f, 100f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1, 1, // Bottom right 0
                100f, 200f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0, 0, // Top left     1
                200f, 200f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1, 0, // Top right    2
                100f, 100f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0, 1  // Bottom left  3
        };
        testRenderButBetter.addComponent(test);
        addGameObject(testRenderer);
        addGameObject(testRenderButBetter);
        super.init();
    }
}
