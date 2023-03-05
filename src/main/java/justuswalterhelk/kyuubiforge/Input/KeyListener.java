package justuswalterhelk.kyuubiforge.Input;

public class KeyListener
{
    private static KeyListener instance;
    //Key map with 350 values
    //See https://www.glfw.org/docs/3.3/group__keys.html
    private final boolean[] keyPressed = new boolean[350];

    private KeyListener() {}

    //Singleton
    public static KeyListener get()
    {
        if(KeyListener.instance == null)
        {
            KeyListener.instance = new KeyListener();
        }

        return KeyListener.instance;
    }

    //Mods are things like STRG + Key
    public static void keyCallback(long window, int key, int scancode, int action, int mods)
    {
        if(action == 1)
        {
            get().keyPressed[key] = true;
        }
        else if (action == 0)
        {
            get().keyPressed[key] = false;
        }
    }

    public static boolean isKeyPressed(int keyCode)
    {
        return get().keyPressed[keyCode];
    }
}
