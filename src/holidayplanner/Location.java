package holidayplanner;

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
import java.time.LocalDate;
import java.util.*;

public class Location {
    private String name;
    private String town;
    private double mediumPrice;
    private ArrayList<String> activities;
    private AvailableDate date;
    
    public Location(String name, String town, double mediumPrice,
            int yearA, int monthA, int dayA, int yearB, int monthB, int dayB)
            throws DateException {
        this.name = name;
        this.town = town;
        this.mediumPrice = mediumPrice;
        activities = new ArrayList<String>();
        date = new AvailableDate(yearA, monthA, dayA, yearB, monthB, dayB);
    }
    
    public String toString() {
        return "Location name: " + name + "\nTown: " + town + "\nActivities: " 
                + activities.toString() + "\nMedium price/day: " + mediumPrice 
                + "\nHoliday available date: " + date.toString();
    }
    
    public String formatedToString() {
        return "      Location name: " + name + "\n      Town: " + town + "\n      Activities: " 
                + activities.toString() + "\n      Medium price/day: " + mediumPrice 
                + "\n      Holiday available date: " + date.toString() + "\n\n";
    }
    public String getName() {
        return name;
    }
    
    public String getTown() {
        return town;
    }
    
    public double getMediumPrice() {
        return mediumPrice;
    }
    
    public AvailableDate getAvailableDate() {
        return date;
    }
    
     public ArrayList<String> getActivities() {
        return activities;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setTown(String town) {
        this.town = town;
    }
    
    public void setMediumPrice(double mediumPrice) {
        this.mediumPrice = mediumPrice;
    }
    
    public void addActivity(String activity) {
        activities.add(activity);
    }
 
    public void setAvailableDate(int yearA, int monthA, int dayA, int yearB,
            int monthB, int dayB) throws DateException {
        date.setStartDate(yearA, monthA, dayA);
        date.setEndDate(yearB, monthB, dayB);
        if (date.getStartDate().isAfter(date.getEndDate())) {
            throw new DateException();
        }
    }
    
}
