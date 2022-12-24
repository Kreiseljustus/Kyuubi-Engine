package io.github.justuswalterhelk;

public class Constants
{
    public final static int FRAMES_PER_SECOND = 30;
    //Time to wait before the next engine tick depending on current frame rate
    public final static int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
}
