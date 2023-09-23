package net.fabricmc.example.event;

import net.fabricmc.example.pos.PlayerPos;
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
import java.util.ArrayList;
import java.util.HashMap;


import java.awt.*;
import java.awt.event.InputEvent;
import java.util.concurrent.TimeUnit;

public class KeyInputHandler {
    public static final String KEY_CATEGORY = "START KEYBINDING";
    public static final String START_KEY = "Start Key";

    public static final String LOG_NOTEBLOCK_KEY = "Log Noteblock Key";

    public static KeyBinding logNoteblockKey;

    public static ArrayList<PlayerPos> noteBlocks = new ArrayList<>();

    public static KeyBinding startKey;

    public static int i = 0;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (startKey.wasPressed()) {
                for (PlayerPos noteBlock : noteBlocks) {
                    float newYaw = noteBlock.getYaw();
                    float newPitch = noteBlock.getPitch();
                    client.player.setYaw(newYaw);
                    client.player.setPitch(newPitch);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

//                KeyBinding usekey = client.options.useKey;
//                KeyBinding breakkey = client.options.attackKey;
//                usekey.setPressed(true);
            }
            if (logNoteblockKey.wasPressed()) {
                i++;
                PlayerPos newNoteblockPos = new PlayerPos(client.player.getPitch(), client.player.getYaw());
                noteBlocks.add(newNoteblockPos);
                if (i>=10) {
                    System.out.println(noteBlocks);
                }
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
        logNoteblockKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                LOG_NOTEBLOCK_KEY,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_PERIOD,
                KEY_CATEGORY
        ));

        registerKeyInputs();
    }
}
