package org.csc133.a2;

public class Location { //like a Point class

    private double x;
    private double y;

    public Location (double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double getX()
    {
       return x;
    }

    public double getY()
    {
        return y;
    }

    public void setLocation(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString()
    {
        String string = "loc= " + Math.round(getX()*10.0)/10.0 + " , " + Math.round(getY()*10.0)/10.0;
        return string;
    }
}
