package events;

public class KeyPressedEvent extends KeyEvent{

    private final int repeatCount;
    protected KeyPressedEvent(int keyCode, int repeatCount) {
        super(keyCode);
        this.repeatCount = repeatCount;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    @Override
    EventType getEventType() {
        return EventType.KeyPressed;
    }
}
