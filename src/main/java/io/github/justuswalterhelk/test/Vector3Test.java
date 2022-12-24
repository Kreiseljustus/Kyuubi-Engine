package io.github.justuswalterhelk.test;

import io.github.justuswalterhelk.datatypes.Vector3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Vector3Test
{
    @Test
    public void test()
    {
        Vector3 vector = new Vector3(1,0,0);
        assertEquals(1, vector.magnitude());
    }
}
