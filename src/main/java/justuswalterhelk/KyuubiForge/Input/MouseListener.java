package justuswalterhelk.KyuubiForge.Input;

import org.joml.Vector2f;

public class MouseListener
{
    private static MouseListener instance;

    //Scroll values
    private double scrollX, scrollY;

    private double xPos, yPos, lastY, lastX, worldX, worldY, lastWorldX, lastWorldY;
    //Place for nine mouse buttons
    private boolean mouseButtonPressed[] = new boolean[9];


    private boolean isDragging;

    private int mouseButtonDown = 0;

    private Vector2f gameViewportPos = new Vector2f();
    private Vector2f gameViewportSize = new Vector2f();

    private MouseListener()
    {
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    public static void endFrame()
    {
        //TODO: Finish mouse listener
    }

    public static MouseListener get()
    {
        if(MouseListener.instance == null)
        {
            MouseListener.instance = new MouseListener();
        }

        return MouseListener.instance;
    }
}
