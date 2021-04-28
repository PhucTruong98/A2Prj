package org.csc133.a2;


// children of this class can move
public class Movable extends GameObject {
    protected int heading;
    protected double speed;



    public int getHeading() {
        return heading;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    //this method is called when the in game clock is increased,
    //should be override by children class
    public void updateClockTick(int timerEventRate) {

    }

    public void move(int elapsedTime)
    { //change the location using the math equation provided on the spec

        double r = this.getSpeed() * elapsedTime / 1000;
        double angle = Math.toRadians(90 - this.getHeading());

        double deltaX = (double)Math.round(r * Math.cos(angle)); // Movement in the X direction.
        double deltaY = (double)Math.round(r * Math.sin(angle)); // Movement in the Y direction.

        float newX = (float) this.getLocation().getX() + (float) deltaX; // Total X.
        float newY = (float)this.getLocation().getY() - (float)deltaY; // Total Y.

        this.setLocation(newX, newY);


        //if out of bound, change heading
        if(this.getLocation().getX() > getGameSettings().screenConstraintX
        || this.getLocation().getX() < 0
        || this.getLocation().getY() > getGameSettings().screenConstraintY
        || this.getLocation().getY() < 0)
        {
            setHeading(getHeading() + 90);
        }

    }

    @Override
    public String toString()
    {
        String string = super.toString() + " heading= " + getHeading() + " speed = " + getSpeed();
        return string;
    }
}
