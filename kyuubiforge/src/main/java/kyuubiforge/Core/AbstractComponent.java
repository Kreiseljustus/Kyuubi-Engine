package kyuubiforge.Core;

public abstract class AbstractComponent
{
    public GameObject gameObject;

    public abstract  void Start();
    public abstract void Awake();
    public abstract void Update(float deltaTime);
}
