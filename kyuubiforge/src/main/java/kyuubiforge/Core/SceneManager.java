package kyuubiforge.Core;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class SceneManager {
    private List<Scene> loadedScenes = new ArrayList<Scene>();

    public <T extends Scene> T loadScene(Class<T> scene)
    {
        Scene sceneToLoad = null;

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

    public List<Scene> getLoadedScenes()
    {
        return loadedScenes;
    }
}
