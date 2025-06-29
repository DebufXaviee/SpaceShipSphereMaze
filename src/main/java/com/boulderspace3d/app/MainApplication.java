package com.boulderspace3d.app;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.boulderspace3d.di.Injection;

/** Lanceur desktop. */
public class MainApplication {
    public static void main(String[] args) {
        Injection.setup();
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title  = "BoulderSpace3D AI";
        config.width  = 1280;
        config.height = 720;
        new LwjglApplication(new GameMain(), config);
    }
}