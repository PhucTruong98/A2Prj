package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Point;

import java.io.IOException;

public class MapView extends Container {

    Image helicopterImg;

    GameObjectCollection gameObjectCollection;
    public MapView(GameObjectCollection gameObjectCollection)
    {

        this.gameObjectCollection = gameObjectCollection;
        try{
            helicopterImg = Image.createImage("/helicopter-top-view-silhouette-10.png");
            } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        this.addComponent(new Label("MAPVIEW LABEL"));

    }




    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(ColorUtil.CYAN);
        //g.drawImage(helicopterImg, 0,0, helicopterImg.getWidth(), helicopterImg.getHeight());
        g.fillRect(0,0, getWidth(),getHeight());
        Point containerPoint = new Point(this.getX(), this.getY());
        for(int i = 0 ; i < gameObjectCollection.size() ; i++)
        {
                gameObjectCollection.get(i).draw(g,containerPoint );

        }
    }


//loop through all gameobject and call draw()
    public void update(GameObjectCollection gameObjectCollection) {
        this.gameObjectCollection = gameObjectCollection;
        this.repaint();
    }
}
