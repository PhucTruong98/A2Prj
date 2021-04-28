package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;

import java.io.IOException;

public class RefuelingBlimp extends Fixed {

    private int capacity;

    Image image;

    public int getCapacity() {
        return capacity;
    }

    public RefuelingBlimp()
    {
        super();
        try {
            image = Image.createImage("/blimp.png");
        } catch (IOException e) {
            e.printStackTrace();
        }        //the size of refueling blimp is random as well as the location
        this.setSize(this.getGameSettings().fuelBlimpSizeConstraint[0]
                + this.getRandom().nextInt(this.getGameSettings().fuelBlimpSizeConstraint[1]));
        //capacity is proportional to size
        capacity = this.getSize() * 2;
        this.setLocation(randomLocation(
                this.getGameSettings().screenConstraintX,
                this.getGameSettings().screenConstraintY));
        this.setColor(ColorUtil.BLUE);
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
        return "RefuelingBlimp: " + super.toString() + " capacity = " + this.capacity;
    }
}
