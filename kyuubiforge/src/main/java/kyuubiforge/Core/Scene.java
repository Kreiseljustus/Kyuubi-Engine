package kyuubiforge.Core;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    private boolean isRunning = false;
    protected List<GameObject> gameObjects = new ArrayList<>();

    protected List<GameObject> selectedGameObjects = new ArrayList<>();
    protected GameObject activeGameObject = null;

    public Scene()
    {

    }

    public void init()
    {

    }

    public void start()
    {
        for(GameObject g : gameObjects)
        {
            g.start();
        }
        isRunning = true;
    }

    public void addGameObject(GameObject g)
    {
        if(!isRunning)
        {
            gameObjects.add(g);
        }
        else
        {
            gameObjects.add(g);
            g.start();
        }
    }

    public abstract void update(float dt);

    public void handleInspector()
    {
        if(activeGameObject != null)
        {
            activeGameObject.drawInspector();
        }

        customSceneImgui();
    }

    /**
     * Can be overwritten to add scene specific imgui calls
     */
    public void customSceneImgui()
    {

    }
}
