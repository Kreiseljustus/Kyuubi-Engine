package events;

public class KeyReleasedEvent extends KeyEvent{
    protected KeyReleasedEvent(int keyCode) {
        super(keyCode);
    }

    @Override
    EventType getEventType() {
        return EventType.KeyReleased;
    }
}
