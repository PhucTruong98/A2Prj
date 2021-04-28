package org.csc133.a2;

import java.util.ArrayList;


//this is where gameworld store all its objects
public class GameObjectCollection {

    private ArrayList<GameObject> collection;

    public  GameObjectCollection()
    {
        collection = new ArrayList<GameObject>();
    }

    public void add(GameObject gameObject)
    {
        collection.add(gameObject);
    }

    public void remove(int i)
    {
        collection.remove(i);
    }

    public ArrayList<GameObject> getCollection() {
        return collection;
    }

    public int size()
    {
        return collection.size();
    }

    public GameObject get(int i)
    {
        return collection.get(i);
    }

    public boolean set(int i, GameObject newGameObject)
    {
        collection.set(i, newGameObject);
        return true;
    }


}
