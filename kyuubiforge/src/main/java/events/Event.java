package events;

public abstract class Event {

    //Events are blocking which means they pause the program flow until
    //handled! This will change in future versions

    public enum EventType {
        None,
        WindowClose, WindowResize, WindowFocus, WindowLostFocus, WindowMoved,
        AppTick, AppUpdate, AppRender,
        KeyPressed, KeyReleased,
        MouseButtonPressed, MouseButtonReleased, MouseMoved, MouseScrolled
    }

    public enum EventCategory{

        None(),
        EventCategoryApplication(0),
        EventCategoryInput(1),
        EventCategoryKeyboard(2),
        EventCategoryMouse(3),
        EventCategoryMouseButton(4);

        public final int BitMask;

        EventCategory(int shift) {
            BitMask = (1 << shift);
        }

        EventCategory() {
            BitMask = Integer.MAX_VALUE;
        }
    }

    private boolean eventHandled = false;
    abstract EventType getEventType();
    String getName() {return getClass().getName();};
    abstract int getCategoryBitMask();
    public boolean isInCategory(EventCategory category) {
        return (getCategoryBitMask() & category.BitMask) != 0;
    }
}
