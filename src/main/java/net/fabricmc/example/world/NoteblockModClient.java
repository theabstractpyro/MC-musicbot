package net.fabricmc.example.world;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.example.event.KeyInputHandler;

public class NoteblockModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
    }
}
