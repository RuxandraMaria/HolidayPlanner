package holidayplanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *This class is an abstraction of the hierarchy representation
 *containing a tree with the globe countries/towns hierarchy
 *and a list with all the locations
 * @author Draghici Ruxandra
 * @version 1.0
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
    
    public ArrayList<Location> getLocationsFromTown(String townName, AvailableDate date) {
        ArrayList<Location> ret = new ArrayList<>();
        for (Location loc : locations) {
            if (loc.getTown().compareTo(townName) == 0 
                    && (date.getStartDate().isEqual(loc.getAvailableDate().getStartDate())
                    ||date.getStartDate().isAfter(loc.getAvailableDate().getStartDate()))
                    &&(date.getEndDate().isEqual(loc.getAvailableDate().getEndDate())
                    ||date.getEndDate().isBefore(loc.getAvailableDate().getEndDate())))
                ret.add(loc);
        }
        return ret;
    }
    
    public ArrayList<Location> getLocationsFromDistrict(String districtName, AvailableDate date) {
        ArrayList<Location> ret = new ArrayList<>();
        for (Node<String> country : hierarchy.getChildren()) {
            for(Node<String> district : country.getChildren())
                if (district.getData().compareTo(districtName) == 0) {
                   for (Node<String> town : district.getChildren())
                       ret.addAll(getLocationsFromTown(town.getData(), date));
                }
        }
        return ret;
    }
    
    public ArrayList<Location> getLocationsFromCountry(String countryName, AvailableDate date) {
        ArrayList<Location> ret = new ArrayList<>();
        for (Node<String> country : hierarchy.getChildren()) {
            if (country.getData().compareTo(countryName) == 0) {
                for (Node<String> district : country.getChildren())
                    ret.addAll(getLocationsFromDistrict(district.getData(), date));
            }
        }
        return ret;
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
