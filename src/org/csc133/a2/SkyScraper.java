package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;

import java.io.IOException;

public class SkyScraper extends Fixed{
    public int getSequenceNumber() {
        return sequenceNumber;
    }

    Image image;
    private int sequenceNumber;

    public SkyScraper(Location location, int sequenceNumber)
    {
        super();

        try {
            image = Image.createImage("/sky"+ (sequenceNumber - 1) + ".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
//if no location is provided, create a random one
        if(location != null)
        {this.setLocation(location);}
        else
        {
            this.setLocation(randomLocation(getGameSettings().screenConstraintX, getGameSettings().screenConstraintY));
        }
        this.sequenceNumber = sequenceNumber;
        this.setSize(this.getGameSettings().skyScraperSize);
        this.setColor(ColorUtil.BLACK);
    }



    @Override
    public void draw(Graphics g, Point containerOrigin) {
        double x = this.getLocation().getX() + containerOrigin.getX();
        double y = this.getLocation().getY() + containerOrigin.getY();

        g.setColor(this.getColor());

        g.drawImage(image, (int)x, (int) y,this.getSize(), this.getSize());

    }

    @Override
    public String toString()
    {
        return "Skyscraper: " + super.toString() + " sequenceNumber = " + this.sequenceNumber;
    }
}
