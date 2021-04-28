package org.csc133.a2;

import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.UITimer;

public class Game extends Form implements Runnable{
    private GameWorld gameWorld;
    private MapView mapView;
    private GlassCockpit glassCockpit;

    private UITimer timer;

    private static int TIMER_EVENT_RATE = 20;


    private Button accelButton;
    private Button brakeButton;
    private Button leftButton;
    private Button rightButton;


    public Game()
    {


        accelButton = new Button("Accelerate");
        brakeButton = new Button("Brake");
        leftButton = new Button("Left");
        rightButton = new Button("Right");


        //UITimer to run animation
        this.timer = new UITimer(this);

        this.timer.schedule(TIMER_EVENT_RATE, true, this);
        gameWorld = new GameWorld();
        mapView = new MapView(gameWorld.getcollection());
        glassCockpit = new GlassCockpit();
        //register listenter so they can be updated in UI
        gameWorld.registerListener(mapView, glassCockpit);
        gameWorld.init();
        this.setLayout(new BorderLayout());
        this.add(BorderLayout.NORTH, this.glassCockpit);
        this.add(BorderLayout.CENTER, this.mapView);
        //these are for the buttons
        Container buttons = new Container();
        buttons.setLayout(new GridLayout(1,4));
        buttons.addAll(accelButton, brakeButton, leftButton, rightButton);


        accelButton.addActionListener((e) -> {
            gameWorld.accelerate();
        });

        brakeButton.addActionListener((e) -> {
            gameWorld.brake();
        });

        leftButton.addActionListener((e) -> {
            gameWorld.steerLeft();
        });

        rightButton.addActionListener((e) -> {
            gameWorld.steerRight();
        });


        this.add(BorderLayout.SOUTH, buttons);

        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");

        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_WARNING, s);

        this.getToolbar().addCommandToSideMenu("Exit", icon, (e) -> {
            Display.getInstance().exitApplication();});
        this.getToolbar().addCommandToSideMenu("Change Strategy", icon, (e) -> {
            Dialog d = new Dialog("Change Strategy");
            d.setLayout(new BorderLayout());
            d.add(BorderLayout.CENTER, new SpanLabel("Change", "DialogBody"));
            d.showPacked(BorderLayout.SOUTH, true);
        });
        this.getToolbar().addCommandToSideMenu("About", icon, (e) -> {
            Dialog d = new Dialog("About");
            d.setLayout(new BorderLayout());
            d.add(BorderLayout.CENTER, new SpanLabel("This game was developed by Phuc Truong", "DialogBody"));
            d.showPacked(BorderLayout.SOUTH, true);
        });
        this.getToolbar().addCommandToSideMenu("Help", icon, (e) -> {
            Dialog d = new Dialog("Help");
            d.setLayout(new BorderLayout());
            d.add(BorderLayout.CENTER, new SpanLabel("Use key board and buttons to play the game", "DialogBody"));
            d.showPacked(BorderLayout.SOUTH, true);
        });


        this.show();
    }



    @Override
    public void run() {
        this.gameWorld.tick(TIMER_EVENT_RATE);
    }
}
