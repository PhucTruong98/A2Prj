package org.csc133.a2;

public class GameWorld {

    private GameObjectCollection collection;


    private int amountOfBirds; //amount of birds on screen
    private int amountOfSkyScrapers; //amount of skyScrapers on screen
    private int amountOfRefuelingBlimps; //amount of refueling blimps on screen

    private int clock;
    private int lives = 3;
    private Location startingLocation = new Location(100,100); //user starting location

    private GlassCockpit glassCockpit;
    private MapView mapView;


    public GameWorld()
    {
        collection = new GameObjectCollection();
    }

    public void registerListener(MapView mapView, GlassCockpit glassCockpit)
    {
        this.mapView = mapView;
        this.glassCockpit = glassCockpit;
    }

    public void updateGlassCockPit()
    {
        glassCockpit.update(this.getcollection());
    }

    public void updateMapView()
    {
        mapView.update(this.getcollection());
    }


    public void init()
    {
        clock = 0;
        amountOfBirds = 4;
        amountOfRefuelingBlimps = 4;
        collection = new GameObjectCollection(); //array game objects
        populateGameWorld();
        /////code here to create the
        ////initial game objects/setup
    }

    private void populateGameWorld() //initialize game world with game objects
    {
        collection.add(new Hellicopter(startingLocation));
        for(int i = 0; i < amountOfBirds; i++) {collection.add(new Bird());}
        for(int i = 0; i < amountOfRefuelingBlimps; i++) {collection.add(new RefuelingBlimp());}
        collection.add(new SkyScraper(new Location(startingLocation.getX(), startingLocation.getY()), 1));



        collection.add(new SkyScraper(null, 2));
        collection.add(new SkyScraper(null, 3));
        collection.add(new SkyScraper(null, 4));
        collection.add(new SkyScraper(null, 5));
        collection.add(new SkyScraper(null, 6));
        collection.add(new SkyScraper(null, 7));
        collection.add(new SkyScraper(null, 8));
        collection.add(new SkyScraper(null, 9));


    }

    public String map() //display current game state
    {
        String string = "lives = " + lives + " clock = " + clock + '\n' ;
        for (int i = 0; i < collection.size(); i++)
        {
            string += collection.get(i).toString() + "\n";
        }
        return string;
    }

    public void tick(int timeEventRate) //increase tick in game
    {
        clock += 1;
        Hellicopter heli = (Hellicopter)  collection.get(0);
        if(!heli.isDead()) //check if the helicopter is still running, if so
            // update clock tick for all movable objects in game
        {
            for(int i = 0; i < collection.size(); i++)
            {
                if(collection.get(i) instanceof Movable)
                {
                    Movable movable = (Movable) collection.get(i);
                    movable.updateClockTick(timeEventRate);
                    collection.set(i, movable);
                }
            }
        }
        else if( lives >= 1)
        {
            //restart helicopter position
            collection.set(0, new Hellicopter(startingLocation));
        }

        else {
            System.out.println("GAME OVER");
            System.exit(0);
        }

        updateMapView();
        updateGlassCockPit();

    }

    public void accelerate()
    {
        Hellicopter heli = (Hellicopter)  collection.get(0);
        heli.accelerate();
        collection.set(0, heli);
        System.out.println("accelerating");


    }

    public void brake()
    {
        Hellicopter heli = (Hellicopter)  collection.get(0);
        heli.brake();
        collection.set(0, heli);
        System.out.println("Braking");

    }

    public void steerLeft() {
        Hellicopter heli = (Hellicopter)  collection.get(0);
        heli.steerLeft();
        collection.set(0, heli);
        System.out.println("Steering Left");

    }

    public void steerRight() {
        Hellicopter heli = (Hellicopter)  collection.get(0);
        heli.steerRight();
        collection.set(0, heli);
        System.out.println("Steering Right");

    }

    public void collideWithBird()
    {
        Hellicopter heli = (Hellicopter)  collection.get(0);
        heli.collideWithBird();
        collection.set(0, heli);
        System.out.println("Collided With Bird");

    }

    public void collideWithSkyScraper(int sequenceNumber)
    {
        Hellicopter heli = (Hellicopter)  collection.get(0);
        heli.collideWithSkyscraper(sequenceNumber);
        collection.set(0, heli);
        System.out.println("Collided With Skyscraper");

    }

    public void collideWithHelicopter()
    {
        Hellicopter heli = (Hellicopter)  collection.get(0);
        heli.collideWithHelicopter();
        collection.set(0, heli);
        System.out.println("Collided With Helicopter");

    }

    public void collidedWithRefuelingBlimp()
    {
        //collide and refuel helicopter with one random refueling blimp on screen,
        // then add a random one into screen
        Hellicopter heli = (Hellicopter)  collection.get(0);
        RefuelingBlimp refuelingBlimp = null;
        for(int i = 0; i < collection.size(); i++)
        {
            if(collection.get(i) instanceof RefuelingBlimp)
            {
                 refuelingBlimp = (RefuelingBlimp) collection.get(i);
                 collection.remove(i);
                 break;
            }
        }
        heli.setFuelLevel(refuelingBlimp.getCapacity() + heli.getFuelLevel());
        collection.set(0, heli);
        collection.add(new RefuelingBlimp());
        System.out.println("Collided With Refueling Blimp");

    }

    public void display()
    {
        Hellicopter heli = (Hellicopter)  collection.get(0);
        String string = "DISPLAY: lives = " + lives + " clock = " + clock + '\n'
                + "last skyscraper reached = " + heli.getLastSkyScraperReached()
                + "fuel level = " + heli.getFuelLevel()
                + "damage level = " + heli.getDamageLevel() + "%";
        System.out.println(string);
    }

    public void exit()
    {
        System.exit(0);
    }

    public GameObjectCollection getcollection()
    {
        return collection;
    }

}
