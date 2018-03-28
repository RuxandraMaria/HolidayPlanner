package holidayplanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ruxi
 */
import java.util.*;
public class Hierarchy {
    private static Hierarchy instance = null;
    private ArrayList<Location> locations = new ArrayList<Location>();
    private Hierarchy() { }
    public static Hierarchy getInstance(){ 
        if(instance == null)
            instance = new Hierarchy();  
        return instance;
    }
    
    public ArrayList<Location> getLocations() {
        return locations;
    }
}
