package org.csc133.a2;


import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;

public class GlassCockpit extends Container {

    private GameClockComponent timer;
    private FuelDisplay fuelDisplay;
    private DamageDisplay damageDisplay;
    private HeadingDisplay headingDisplay;
    private LastSkyScraperDisplay lastSkyScraperDisplay;
    private LivesDisplay livesDisplay;
    private ClockTest clockTest;
    private GameObjectCollection collection;


    public GlassCockpit()
    {
        collection = new GameObjectCollection();
        setLayout(new GridLayout(2,6));
        //setLayout(new BoxLayout(BoxLayout.X_AXIS_NO_GROW));
        timer = new GameClockComponent();
        fuelDisplay = new FuelDisplay();
        damageDisplay = new DamageDisplay();
        headingDisplay = new HeadingDisplay();
        lastSkyScraperDisplay = new LastSkyScraperDisplay();
        livesDisplay = new LivesDisplay();
        //clockTest = new ClockTest();
        Label timerForm = new Label("Timer");
        Label fuelForm = new Label("Fuel");
        Label damageForm = new Label("Damage");
        Label headingForm = new Label("Heading");
        Label lastSkyForm = new Label("Last");
        Label livesForm = new Label("Lives");

        //the displays are stored in a grid layout
        addAll(timerForm, fuelForm, damageForm, headingForm, lastSkyForm, livesForm);
        addComponent(timer);
        addComponent(fuelDisplay);
        addComponent(damageDisplay);
        addComponent(headingDisplay);
        addComponent(lastSkyScraperDisplay);
        addComponent(livesDisplay);

    }

    //update the values for all objects
    public void update(GameObjectCollection gameObjectCollection)
    {

        this.collection = gameObjectCollection;
        Hellicopter player = (Hellicopter) gameObjectCollection.get(0);
        fuelDisplay.setValue(player.getFuelLevel());
        damageDisplay.setValue(player.getDamageLevel());
        headingDisplay.setValue(player.getHeading());
        lastSkyScraperDisplay.setValue(player.getLastSkyScraperReached());
    }

}
