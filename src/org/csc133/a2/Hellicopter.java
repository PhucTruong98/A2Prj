package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;

import java.io.IOException;

public class Hellicopter extends Movable implements IsSteerable {
    private double stickAngle; //range from - 40 to 40
    private double maximumSpeed;
    private double fuelLevel;
    private double fuelConsumptionRate;
    private double damageLevel;

    Image image;

    public void setFuelLevel(double fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public double getFuelLevel() {
        return fuelLevel;
    }

    public double getDamageLevel() {
        return damageLevel;
    }

    public double getLastSkyScraperReached() {
        return lastSkyScraperReached;
    }

    private double lastSkyScraperReached;






    public Hellicopter(Location location)
    {
        super();
        try {
            image = Image.createImage("/helicopter.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setSize(this.getGameSettings().hellicopterSize);
        this.setColor(ColorUtil.MAGENTA);
        this.maximumSpeed = 80;
        this.fuelLevel = 100;
        this.fuelConsumptionRate = 1;
        this.damageLevel = 0;
        this.lastSkyScraperReached = 1;
        this.setLocation(location);
        this.setSpeed(50);
        this.setHeading(90);
    }

    @Override
    public String toString()
    {
        String string = "Hellicopter: " + super.toString() +
                " maximumSpeed = " + this.maximumSpeed + " fuelLevel = " + this.fuelLevel +
                " fuelConsumptionRate = " + this.fuelConsumptionRate + " damageLevel = " + this.damageLevel
                + "lastSkyScraperReached" + this.lastSkyScraperReached;
        return string;
    }

    public void accelerate()
    {
        if(0 < this.speed && this.speed < maximumSpeed)
        {
            this.speed += 10;
            if (this.speed > maximumSpeed) {this.speed = maximumSpeed;}
        }
    }

    public void brake()
    {
        if(0 < this.speed && this.speed < maximumSpeed)
        {
            this.speed -= 10;
            if (this.speed < 0) {this.speed = 0;}
        }
    }

    @Override
    public void steerLeft() {
        stickAngle += 5;
    }

    @Override
    public void steerRight() {
        stickAngle -= 5;
    }


    public void collideWithBird()
    {
        damageLevel += 10;
    }

    public void collideWithHelicopter()
    {
        damageLevel += 20;
    }

    public void collideWithSkyscraper(int sequenceNumber)
    {
        if(sequenceNumber == this.lastSkyScraperReached+1)
        {
            this.lastSkyScraperReached += 1;
            System.out.println("reached new sky scraper number: " + sequenceNumber);
        }
        else {
            System.out.println("Already been here before or haven't reach this number yet");
        }


    }

    @Override
    public void updateClockTick(int timerEventRate)
    {
        if(heading >= 360) heading -= 360;
        if(heading <= 0) heading += 360;

        maximumSpeed = maximumSpeed* (1 - damageLevel/100);
        fuelLevel = fuelLevel - fuelConsumptionRate * timerEventRate/1000;
        if(stickAngle < 0) {heading += 5; stickAngle = 0;} //turning left
        else if (stickAngle > 0) {heading -= 5; stickAngle = 0;} //turning right
        if(!isDead())
        {
            super.move(timerEventRate);
        }
    }
    //check if the helicopter is still up and running by checking the fuel level and speed
    public boolean isDead() {return (fuelLevel <= 0) || this.speed == 0;}


    @Override
    public void draw(Graphics g, Point containerOrigin) {

        double x = this.getLocation().getX() + containerOrigin.getX();
        double y = this.getLocation().getY() + containerOrigin.getY();

        g.setColor(this.getColor());

        g.drawImage(image, (int)x, (int) y,this.getSize(), this.getSize());
        System.out.println("Debug hellicopter position = "  + this.getLocation());


    }
}
