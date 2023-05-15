package kyuubiforge.Renderer;

import kyuubiforge.Core.AbstractScene;
import kyuubiforge.Core.GameObject;
import kyuubiforge.Core.SceneManager;
import kyuubiforge.Debug.TestRenderer;

import java.util.ArrayList;
import java.util.List;

import static kyuubiforge.Debug.Debug.log;

public class GameRenderer extends AbstractRenderLayer {
    //Draw all GameObjects to screen

    private List<AbstractScene> loadedScenes = new ArrayList<AbstractScene>();

    @Override
    public void init()
    {
        loadedScenes = SceneManager.getLoadedScenes();

        /*
        All setup for the renderer should happen BEFORE this line!
         */
    }

    @Override
    public void update(float dt)
    {
        for (AbstractScene scene : loadedScenes)
        {
            for (GameObject g : scene.getGameObjects())
            {
                g.update(dt);
            }
        }
    }

    public void OnSceneLoaded()
    {
        //Inefficient because of duplicating the whole array?
        //Maybe create load and unload callbacks and remove/add the scenes
        loadedScenes = SceneManager.getLoadedScenes();
    }
}
