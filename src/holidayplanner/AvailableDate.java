package holidayplanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
* The AvailableDate class is the abstraction of a period
* when a person can go on a holiday for a specific location,
* defined by a starting date(day-month-year) and an end
* date(day-month-year), where start date is a previous
* calendar date than the end date
* @author  Draghici Ruxandra
* @version 1.0
*/
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class AvailableDate {
    private LocalDate startDate, endDate;
    
    public AvailableDate(int yearA, int monthA, int dayA,
            int yearB, int monthB, int dayB) throws DateException  {
        startDate = LocalDate.of(yearA, monthA, dayA);
        endDate = LocalDate.of(yearB, monthB, dayB);
        if (startDate.isAfter(endDate)) {
            throw new DateException();
        }
    }
    
    public String toString() {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String parsedStartDate = startDate.format(formatters);
        String parsedEndDate = endDate.format(formatters);
        return "Start date: " + parsedStartDate
                + "    End date: " + parsedEndDate;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }
    
    public int getStartDateDay() {
        return startDate.getDayOfMonth();
    }
    
    public int getStartDateMonth() {
        return startDate.getMonthValue();
    }
    
    public int getStartDateYear() {
        return startDate.getYear();
    }
    
    public int getEndDateDay() {
        return endDate.getDayOfMonth();
    }
    
    public int getEndDateMonth() {
        return endDate.getMonthValue();
    }
    
    public int getEndDateYear() {
        return endDate.getYear();
    }
    
    public void setStartDate(int startYear, int startMonth, int startDay) {
        startDate.with(LocalDate.of(startYear, startMonth, startDay));
    }
    
    public void setEndDate(int endYear, int endMonth, int endDay) {
        endDate.with(LocalDate.of(endYear, endMonth, endDay));
    }
    
    public void setStartDateYear(int startYear) {
        startDate.withYear(startYear);
    }
    
     public void setStartDateDay(int startDay) {
        startDate.withDayOfMonth(startDay);
    }
     
     public void setStartDateMonth(int startMonth) {
        startDate.withMonth(startMonth);
    }
     
    public void setEndDateYear(int endYear) {
        endDate.withYear(endYear);
    }
    
     public void setEndDateDay(int endDay) {
        endDate.withDayOfMonth(endDay);
    }
     
    public void setEndDateMonth(int endMonth) {
        endDate.withMonth(endMonth);
    }
    
}
