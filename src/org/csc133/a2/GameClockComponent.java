package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;

import java.util.Calendar;

public class GameClockComponent extends SevenSegDisplayComponent{


    //self animting, run separatedly from glasscockpit

    private int numDigitsShowing = 8;
    private int[] startTime = new int[2];
    private int[] time = {-1,-1,-1};

    private long stopTime = 0;

    public GameClockComponent()
    {
        super(8);
        setColor(ColorUtil.YELLOW);

        clockDigits[2] = colonImage;
        clockDigits[5] = colonImage;
    }

    @Override
    public void setDigits() {

        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR);
        setTime(hour == 0 ? 12 : hour,
                rightNow.get(Calendar.MINUTE),
                rightNow.get(Calendar.SECOND));
    }

    public int timeToInt(int hour, int minute, int second)
    {
        int val = hour*60*60 + minute*60 + second;
        return val;
    };

    public int[] intToTime(int intVal)
    {
      int[] time = new int[2];
      time[0] = Math.round(intVal / 3600);
      time[1] = Math.round(intVal%3600 / 60);
      time[2] = intVal % 60;
      System.out.println("Time debug:" + time);
      return time;

    };


    private void setTime(int h, int m, int s)
    {
        clockDigits[0] = digitImages[h/10];
        clockDigits[1] = digitImages[h%10];
        clockDigits[3] = digitImages[m/10];
        clockDigits[4] = digitImages[m%10];
        clockDigits[6] = digitImages[s/10];
        clockDigits[7] = digitImages[s%10];

    }


    public void resetResetElapsedTime()
    {};
    public void startElapsedTime()
    {
        Calendar rightNow = Calendar.getInstance();
        startTime[0] = rightNow.get(Calendar.HOUR);
        startTime[1] = rightNow.get(Calendar.MINUTE);
        startTime[2] = rightNow.get(Calendar.SECOND);

    };

    public void stopElapsedTime()
    {
        Calendar rightNow = Calendar.getInstance();
        time[0] = rightNow.get(Calendar.HOUR);
        time[1] = rightNow.get(Calendar.MINUTE);
        time[2] = rightNow.get(Calendar.SECOND);
    };
    public void  getElapsedTime()
    {
        Calendar rightNow = Calendar.getInstance();
        int rnInt = timeToInt(rightNow.get(Calendar.HOUR),rightNow.get(Calendar.MINUTE),rightNow.get(Calendar.SECOND));
        int startInt = timeToInt(startTime[0],startTime[1],startTime[2] );
        if(time[1] != -1) {
            int timeInt = timeToInt(time[0],time[1],time[2] );

        }

    };



}
