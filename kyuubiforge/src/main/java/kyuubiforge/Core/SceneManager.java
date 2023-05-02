package kyuubiforge.Core;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class SceneManager {
    private List<IScene> loadedScenes = new ArrayList<IScene>();

    public <T extends IScene> T loadScene(Class<T> scene)
    {
        IScene sceneToLoad = null;

        try {
            sceneToLoad = scene.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        sceneToLoad.init();
        loadedScenes.add(sceneToLoad);

        sceneToLoad.start();
        return null;
    }

    public List<IScene> getLoadedScenes()
    {
        return loadedScenes;
    }
}
