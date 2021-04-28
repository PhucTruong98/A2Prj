package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;

import java.io.IOException;
import java.util.Calendar;


public class SevenSegDisplayComponent extends Component {
    Image[] digitImages = new Image[10];
    Image colonImage;


    private double value = 12345678;
    private int ledColor;
    private static int HM_COLON_IDX = 2;
    private static int MS_COLON_IDX = 5;
    private int numDigitsShowing = 0;
    Image[] clockDigits;


    public void setValue(double newValue)
    {
        this.value = newValue;
    }

    public SevenSegDisplayComponent(int noDigit)
    {

        numDigitsShowing = noDigit;
        clockDigits = new Image[noDigit];
        try{
            //refactored this code to reduce redundancy
            for(int i = 0; i < 10; i++) {
                digitImages[i] = Image.createImage("/LED_digit_"+ i + ".png");
            }
            colonImage = Image.createImage("/LED_colon.png");

        } catch (IOException e) {e.printStackTrace();}

        for(int i = 0; i < numDigitsShowing; i ++)
        {
            clockDigits[i] = digitImages[0];
        }

    }

    public void setColor(int color)
    {
        this.ledColor = color;
    }

    public void setDigits()
    {
        String stringValue = String.format("%0"+ numDigitsShowing + "d", (int) this.value);
        for(int i = 0; i < numDigitsShowing; i++)
        {
//            System.out.println("DEBUG display value: " + this.value);
            char digit = stringValue.charAt(i);
            clockDigits[i] = digitImages[Character.getNumericValue(digit)];

        }
    }

    //should only be called once
    public void start()
    {
        getComponentForm().registerAnimated(this);
    }

    public void stop()
    {
        getComponentForm().deregisterAnimated(this);
    }

    //this method is called automatically by codename one
    public void laidOut()
    {
        this.start();
    }

    //this method is called between every single frame
    public boolean animate()
    {
        setDigits();
        return true;
    }


    private void setCurrentTime()
    {
        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR);

    }

    public void setLedColor(int ledColor)
    {
        this.ledColor = ledColor;
    }

    public Dimension calcPreferredSize()
    {
        return new Dimension(getWidth(), getHeight());
        //return new Dimension(getPreferredSize().getWidth(), Integer.MAX_VALUE);
    }




    public void paint(Graphics g)
    {
        super.paint(g);
        final int COLOR_PAD = 1;


        int digitWidth = clockDigits[0].getWidth();
        int digitHeight = clockDigits[0].getHeight();
        int clockWidth = digitWidth * numDigitsShowing;

        //determine how much to scale the image files
        float scaleFactor = Math.min(
                getInnerHeight() / (float) digitHeight,
                getInnerWidth() / (float) clockWidth);

        //calculate the image size using scale factor
        int displayDigitWidth = (int) (scaleFactor*digitWidth);
        int displayDigitHeight = (int) (scaleFactor*digitHeight);
        int displayCLockWidth = displayDigitWidth*numDigitsShowing;

        //display X and Y are the starting point of each drawing
        int displayX = getX() + (getWidth() - displayCLockWidth)/2;
        int displayY = getY() + (getHeight() - displayDigitHeight)/2;

        //setting background color
        g.setColor(ColorUtil.BLACK);
        g.fillRect(getX(), getY(), getWidth(), getHeight());

        g.setColor(ledColor);
        g.fillRect(displayX+COLOR_PAD,
                displayY+COLOR_PAD,
                displayCLockWidth - COLOR_PAD*2,
                displayDigitHeight - COLOR_PAD*2);

        for(int i = 0; i < numDigitsShowing; i++)
        {
            g.drawImage(
                    clockDigits[i],
                    displayX + displayDigitWidth*i,
                    displayY,
                    displayDigitWidth,
                    displayDigitHeight
            );
        }

    }



}
