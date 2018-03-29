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
    private Node<String> hierarchy = new Node<String>("Earth", 0);
    private Hierarchy() { }
    public static Hierarchy getInstance(){ 
        if(instance == null)
            instance = new Hierarchy();  
        return instance;
    }
    
    public ArrayList<Location> getLocations() {
        return locations;
    }
    public Node<String> getHierarchy() {
        return hierarchy;
    }
    
    public String toString() {
        String ret = "Earth\n" + " Countries:\n";
        for (Node<String> child : hierarchy.getChildren()) {
            ret +=  "  " + child.getData() + " - districts:" + child.getChildren() + "\n";
        }
        for (Node<String> child : hierarchy.getChildren()) {
            for (Node<String> child2 : child.getChildren()) {
                ret += "   " + child2.getData() + " - towns: " + child2.getChildren() + "\n";
                for (Node<String> child3 : child2.getChildren()) {
                    ret += "     " + child3.getData() + " - locations:\n";
                    for (Location loc : locations) {
                        if (loc.getTown().compareTo(child3.getData()) == 0) {
                            ret += loc.formatedToString() + "\n";
                        }
                    }
                }
            }
        }
        return ret;
    }
}
