package justuswalterhelk.KyuubiForge.Core;

import justuswalterhelk.KyuubiForge.UI.TestContainer;
import justuswalterhelk.KyuubiForge.UI.Window;

public class Application
{
    private Window m_Window = null;

    private final ApplicationSpecification m_ApplicationSpecification;
    public ApplicationSpecification GetSpecification() {return m_ApplicationSpecification;}

    private static Application s_Instance = null;

    public Window GetWindow() { return m_Window;}

    private boolean m_Running = true;
    private float m_LastFrameTime = 0.0f;

    public Application(ApplicationSpecification specification)
    {
        s_Instance = this;

        m_ApplicationSpecification = specification;

        m_Window = new Window(specification.getWidth(), specification.getHeight(), specification.getName(), specification.isFullScreen());

        //Initalize Renderer

        //Create ImGui Layer
    }

    public static Application Get() { return s_Instance;}

    public void OnClose()
    {
        System.out.println("[KyuubiEditor] Shutting down update loop");
        m_Running = false;
        //Shutdown Renderer
    }

     static long m_LastTime = System.nanoTime();

    public void Run()
    {
        m_Window.initWindow().addContainer(new TestContainer()).initContainers().run();
        System.out.println("[KyuubiEditor] Initialized main window");

        while(m_Running)
        {
            long time = System.nanoTime();
            long delta_time = ((time-m_LastTime) / 1000000);
            m_LastTime = time;
            m_Window.OnUpdate(delta_time);
        }
    }


}