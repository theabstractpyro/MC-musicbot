package net.fabricmc.example.pos;

import net.minecraft.client.MinecraftClient;

public class NoteblockPos {
    private MinecraftClient mc;
    private final float pitch;
    private final float yaw;

    public NoteblockPos(MinecraftClient client)
    {
        assert client.player != null;
        this.mc = client;
        pitch = client.player.getPitch();
        yaw = client.player.getYaw();
    }

    public float getPitch()   { return pitch; }
    public float getYaw() { return yaw; }
    public MinecraftClient getClient() { return mc; }
    public void setPlayerAt() {
        assert mc.player != null;
        mc.player.setPitch(getPitch());
        mc.player.setYaw(getYaw());
    }
}
