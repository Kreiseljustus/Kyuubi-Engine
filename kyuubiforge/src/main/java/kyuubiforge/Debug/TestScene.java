package kyuubiforge.Debug;

import kyuubiforge.Core.GameObject;
import kyuubiforge.Core.Scene;

import static kyuubiforge.Debug.Debug.log;

public class TestScene extends Scene {
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
        addGameObject(testRenderer);
        super.init();
    }
}
