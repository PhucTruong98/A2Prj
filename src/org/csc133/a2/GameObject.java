package org.csc133.a2;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

import java.util.Random;


public class GameObject implements IDrawable { //this class is the parent class of all game objects

    public Location getLocation() {
        return location;
    }

    public int getSize() {
        return size;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    private Location location;
    static final private GameSettings gameSettings = new GameSettings();
    private int size;
    private int color;

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public GameSettings getGameSettings()
    {
        return this.gameSettings;
    }

    static final private Random rand = new Random();

    public GameObject() {}


    public Random getRandom()
    {
        return this.rand;
    }

    public void setLocation(float x, float y)
    {
        if (this instanceof Fixed) //does not allow changing location if it is a Fixed object
        {
            System.out.println("Unable to change fixed object location");
        }
        else
        {
            location.setLocation(x, y);
        }
    }

    public Location randomLocation(int xConstraint, int yConstraint)
    {
        return new Location(rand.nextInt(xConstraint), rand.nextInt(yConstraint));
    }

    @Override
    public String toString()
    {
        String string = this.getLocation().toString() + " color= " + getColor() + " size= " + getSize();
        return string;
    }



    //the responsibility of drawing is on GameObject
    @Override
    public void draw(Graphics g, Point containerOrigin) {

    }
}
