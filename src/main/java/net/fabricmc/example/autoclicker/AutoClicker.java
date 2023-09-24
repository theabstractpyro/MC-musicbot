package net.fabricmc.example.autoclicker;

import net.fabricmc.example.pos.NoteblockPos;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

import java.util.ArrayList;

public class AutoClicker implements Runnable {
    int tuneValue;
    ArrayList<NoteblockPos> piano;
    public AutoClicker(ArrayList<NoteblockPos> noteBlocks) {
        this.piano = noteBlocks;
    }
    public void tuneNote(int i) {
        for (int j = 0; j < i; j++) {
            KeyBinding usekey = piano.get(i).getClient().options.useKey;
            usekey.setPressed(true);
            if (usekey.isPressed()) {
                usekey.setPressed(false);
            }
        }
    }
    public void tunePiano() throws InterruptedException {
        for (int i = 0; i < piano.size(); i++) {
            tuneValue = i;
            Thread t = new Thread(this);
            t.start();
            t.join();

        }
    }
    @Override
    public void run() {
        System.out.println("hey");
        for (int j = 0; j < tuneValue; j++) {
            piano.get(tuneValue).setPlayerAt();
            KeyBinding usekey = piano.get(tuneValue).getClient().options.useKey;
            usekey.setPressed(true);
            if (usekey.isPressed()) {
                usekey.setPressed(false);
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }
}
