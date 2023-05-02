package kyuubiforge.Core;

public abstract class IComponent
{
    public GameObject gameObject;

    public abstract  void Start();
    public abstract void Awake();
    public abstract void Update(float deltaTime);
}
