package com.boulderspace3d.app;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.boulderspace3d.di.Injection;
public class MainApplication {
    public static void main(String[] args){
        Injection.setup();
        LwjglApplicationConfiguration cfg=new LwjglApplicationConfiguration();
        cfg.title="BoulderSpace3D AI";cfg.width=1280;cfg.height=720;
        new LwjglApplication(new GameMain(),cfg);
    }
}
