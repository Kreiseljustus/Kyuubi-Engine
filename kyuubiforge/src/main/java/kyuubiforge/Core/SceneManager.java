package kyuubiforge.Core;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class SceneManager {
    private List<AbstractScene> loadedScenes = new ArrayList<AbstractScene>();

    public <T extends AbstractScene> T loadScene(Class<T> scene)
    {
        AbstractScene sceneToLoad = null;

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

    public List<AbstractScene> getLoadedScenes()
    {
        return loadedScenes;
    }
}
