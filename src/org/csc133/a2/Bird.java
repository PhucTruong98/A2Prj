package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;

import java.io.IOException;

public class Bird extends Movable {

    Image image ;

    private int randomSteerValue = 5;

    public Bird()
    {
        super();
        try {
            image = Image.createImage("/bird.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setColor(ColorUtil.rgb(34,65,78));
        this.setSize(this.getGameSettings().birdSizeConstraint[0] +
                this.getRandom().nextInt(this.getGameSettings().birdSizeConstraint[1]));
        this.setLocation(randomLocation(1024, 768))  ;
        this.setHeading(this.getRandom().nextInt(360));
        this.setSpeed(50);

    }


    @Override
    public void updateClockTick(int timeEventRate)
    {
        heading += -5 + this.getRandom().nextInt(10);

        super.move(timeEventRate);

    }

    @Override
    public String toString()
    {
        String string = "Bird: " + super.toString();
        return string;
    }

    @Override
    public void draw(Graphics g, Point containerOrigin) {

//        g.rotateRadians(getHeading());

        double x = this.getLocation().getX() + containerOrigin.getX();
        double y = this.getLocation().getY() + containerOrigin.getY();

        g.setColor(ColorUtil.GREEN);

        g.drawImage(image, (int)x, (int) y,this.getSize(), this.getSize());
//        g.rotateRadians(-getHeading());


    }
}
