package io.github.justuswalterhelk.datatypes;

public class Vector3
{
    public int x;
    public int y;
    public int z;
    public double magnitude;

    public Vector3(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;

        magnitude = magnitude();
    }

    public double magnitude()
    {
        double sum = x*x + y*y + z*z;
        return Math.sqrt(sum);
    }
}
