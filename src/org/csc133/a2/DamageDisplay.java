package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;


//all display classes extends from seven segment display
public class DamageDisplay extends SevenSegDisplayComponent {

    public DamageDisplay()
    {
        //2 is numbner of digits
        super(2);
        setColor(ColorUtil.MAGENTA);
    }
}
