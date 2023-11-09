package events;

abstract class KeyEvent extends Event{

    protected KeyEvent(int keyCode) {
        this.keyCode = keyCode;
    }

    abstract EventType getEventType();

    @Override
    public int getCategoryBitMask() {
        return (EventCategory.EventCategoryKeyboard.BitMask | EventCategory.EventCategoryInput.BitMask);
    }

    private final int keyCode;

    public final int getKeyCode() {
        return keyCode;
    }
}
