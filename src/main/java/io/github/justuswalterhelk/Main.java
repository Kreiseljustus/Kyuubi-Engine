package io.github.justuswalterhelk;

import io.github.justuswalterhelk.components.PositionComponent;
import io.github.justuswalterhelk.datatypes.Vector3;

import java.io.Console;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main
{
    final static int FRAMES_PER_SECOND = 60;
    //Time to wait before the next engine tick depending on current frame rate
    final static int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;

    static long start = 0;

    public static void main(String[] args)
    {
        System.out.println("Initializing main engine");
        startTicks();
        int next_game_tick = getTickCount();

        int sleep_time = 0;

        boolean game_is_running = true;

        while(game_is_running)
        {
            //update game
            //display game

            next_game_tick += SKIP_TICKS;
            sleep_time = next_game_tick - getTickCount();
            if(sleep_time >= 0)
            {
                try {
                    TimeUnit.MILLISECONDS.sleep(sleep_time);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else
            {
                System.out.println("Running behind");
            }
        }
    }

    private static void startTicks()
    {
        start = System.currentTimeMillis();
    }

    private static int getTickCount()
    {
        return (int) (System.currentTimeMillis() - start);
    }
}


