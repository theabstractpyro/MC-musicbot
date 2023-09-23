package net.fabricmc.example.pos;

public class PlayerPos {
    private final float pitch;
    private final float yaw;

    public PlayerPos(float aPitch, float aYaw)
    {
        pitch   = aPitch;
        yaw = aYaw;
    }

    public float getPitch()   { return pitch; }
    public float getYaw() { return yaw; }
}
