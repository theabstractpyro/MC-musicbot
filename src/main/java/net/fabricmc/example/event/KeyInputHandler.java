package net.fabricmc.example.event;

import net.fabricmc.example.autoclicker.AutoClicker;
import net.fabricmc.example.pos.NoteblockPos;
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
    public static KeyBinding startKey;
    public static final String NOTE_BLOCK_CATEGORY = "LOG NOTEBLOCK KEYBINDING";
    public static final String LOG_NOTEBLOCK_KEY = "Log Noteblock Key";

    public static KeyBinding logNoteblockKey;

    public static ArrayList<NoteblockPos> noteBlocks = new ArrayList<>();

    public static int i = 0;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (startKey.wasPressed()) {
                System.out.println("Start Automation");
                AutoClicker clicker = new AutoClicker(noteBlocks);
                try {
                    clicker.tunePiano();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

//                KeyBinding usekey = client.options.useKey;
//                KeyBinding breakkey = client.options.attackKey;
//                usekey.setPressed(true);
            if (logNoteblockKey.wasPressed()) {
                NoteblockPos newNoteblockPos = new NoteblockPos(client);
                noteBlocks.add(newNoteblockPos);
                System.out.println(newNoteblockPos.getYaw());
                System.out.println(newNoteblockPos.getPitch());
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
                NOTE_BLOCK_CATEGORY
        ));

        registerKeyInputs();
    }
}
