package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;
import network.kryonet.ui_frame.MyUITest;
import network.kryonet.ui_frame.UISimpleTest;
import network.kryonet.ui_frame.UITest;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Zen";
		config.width = 800;
        config.height = 600;
		new LwjglApplication(new MyUITest(), config);
	}
}
