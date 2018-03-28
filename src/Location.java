/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
* The Location class is the abstraction of a location
* defined by name, medium price spent here, a list of
* activities that can be done here and an available date
* when a person can go on a holiday here
* @author  Draghici Ruxandra
* @version 1.0
*/
import java.util.*;

public class Location {
    private String name;
    private double mediumPrice;
    private ArrayList<String> activities;
    private AvailableDate date;
    
    public Location(String name, double mediumPrice, int yearA, int monthA, int dayA, int yearB, int monthB, int dayB) {
        this.name = name;
        this.mediumPrice = mediumPrice;
        activities = new ArrayList<String>();
        date = new AvailableDate(yearA, monthA, dayA, yearB, monthB, dayB);
    }
    
    public String getName() {
        return name;
    }
    
    public double getMediumPrice() {
        return mediumPrice;
    }
    
    public AvailableDate getAvailableDate() {
        return date;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setMediumPrice(double mediumPrice) {
        this.mediumPrice = mediumPrice;
    }
    
    public void addActivity(String activity) {
        activities.add(activity);
    }
  
}
