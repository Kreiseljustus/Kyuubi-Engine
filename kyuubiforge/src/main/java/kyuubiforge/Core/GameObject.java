package kyuubiforge.Core;

import kyuubiforge.Debug.Debug;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameObject
{
    private String name;
    private List<Component> components = new ArrayList<>();

    public GameObject(String name)
    {
        this.name = name;
    }

    public <T extends Component> T getComponent(Class<T> componentClass)
    {
        for(Component c : components)
        {
            if(componentClass.isAssignableFrom(c.getClass()))
            {
                try
                {
                    return componentClass.cast(c);
                }
                catch (ClassCastException classCastException)
                {
                    Debug.log("[KyuubiForge] " + Arrays.toString(classCastException.getStackTrace()));
                    assert false : "Error: Casting Component";
                }
            }
        }
        return null;
    }

    public <T extends Component> void removeComponent(Class<T> componentClass)
    {
        for(int i = 0; i < components.size(); i++)
        {
            Component c = components.get(i);
            if(componentClass.isAssignableFrom(c.getClass()))
            {
                components.remove(i);
                return;
            }
        }
    }

    public void addComponent(Component c)
    {
        this.components.add(c);
        c.gameObject = this;
    }

    public void drawInspector()
    {

    }

    public void start()
    {
        for (int i = 0; i < components.size(); i++)
        {
            components.get(i).Start();
        }
    }

    public void update(float deltaTime)
    {
        for(Component c : this.components)
        {
            c.Update(deltaTime);
        }
    }
}
