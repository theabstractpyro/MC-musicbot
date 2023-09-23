package net.fabricmc.example.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY = "START KEYBINDING";
    public static final String START_KEY = "Start Key";

    public static KeyBinding startKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (startKey.wasPressed()) {
                client.player.sendChatMessage("Hello");
                client.player.setHeadYaw(90);
                System.out.println(client.player.headYaw);
                //System.out.println(client.player);
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
