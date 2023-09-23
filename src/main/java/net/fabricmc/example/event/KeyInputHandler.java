package net.fabricmc.example.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.checkerframework.checker.lock.qual.Holding;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.awt.event.InputEvent;

public class KeyInputHandler {
    public static final String KEY_CATEGORY = "START KEYBINDING";
    public static final String START_KEY = "Start Key";

    public static KeyBinding startKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (startKey.wasPressed()) {
                client.player.setYaw(90);
                client.player.setPitch(0);
                KeyBinding usekey = client.options.useKey;
                KeyBinding breakkey = client.options.attackKey;
                usekey.setPressed(true);
            }
        });
    }

    public static void register() {
        startKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                START_KEY,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_COMMA,
                KEY_CATEGORY
        ));

        registerKeyInputs();
    }
}
