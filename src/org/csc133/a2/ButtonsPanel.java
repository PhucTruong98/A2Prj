package org.csc133.a2;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.GridLayout;

public class ButtonsPanel extends Container {


    //will be implemented later
    private Button accelButton;
    private Button brakeButton;
    private Button leftButton;
    private Button rightButton;


    public  ButtonsPanel()
    {
        accelButton = new Button("Accelerate");
        brakeButton = new Button("Brake");
        leftButton = new Button("Left");
        rightButton = new Button("Right");

       this.setLayout(new GridLayout(1,4));
       this.addAll(accelButton, brakeButton, leftButton,rightButton);
    }

}
