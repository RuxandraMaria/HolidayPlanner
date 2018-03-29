/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package holidayplanner;

/**
 * The HolidayPlanner class reads the input files, creates the locations
 * and the hierarchy, reads the actions to be done, execute them and 
 * write the outputs to file
 * @author Draghici Ruxandra-Maria
 * @version 1.0
 */
import java.util.*;
import java.io.*;

public class HolidayPlanner {
    private String inputFileName;
    Hierarchy hierarchy;
    
    public HolidayPlanner(String inputFileName) {
        this.inputFileName = inputFileName;
    }
    public void readStoreData() {
        
        FileReader stream = null;
        LineNumberReader lnr = null;
        String locationName, country, district, townname;
        StringTokenizer st, town, activ, date;
        int numberOfLocations, mediumPrice, type;
        int yearA, yearB, dayA, dayB, monthA, monthB;
        Hierarchy hierarchy;
        Location loc;
        
        try {
            stream = new FileReader(inputFileName);
            lnr = new LineNumberReader(stream);
            numberOfLocations = Integer.parseInt(lnr.readLine());
            for(int i = 0; i < numberOfLocations; i++){
                st = new StringTokenizer(lnr.readLine(), ";");
                locationName = st.nextToken();
                
                town = new StringTokenizer(st.nextToken(), "-");
                type = Integer.parseInt(town.nextToken());
                country = town.nextToken();
                district = town.nextToken();
                townname = town.nextToken();
                hierarchy = Hierarchy.getInstance();
                if(hierarchy.getHierarchy().hadThisChild(country) == -1) {
                    hierarchy.getHierarchy().addChild(country, 1);
                }
                
                if(hierarchy.getHierarchy().getChild(country).hadThisChild(district) == -1) {
                    hierarchy.getHierarchy().getChild(country).addChild(new Node(district, type));
                }
                if(hierarchy.getHierarchy().getChild(country).getChild(district).hadThisChild(townname) == -1) {
                     hierarchy.getHierarchy().getChild(country).getChild(district).addChild(townname, 3);
                }
                
                if(type > 3) {
                    /* add new levels in hierarchy*/
                }
                
                mediumPrice = Integer.parseInt(st.nextToken());
                
                date = new StringTokenizer(st.nextToken(), "-");
                dayA = Integer.parseInt(date.nextToken());
                monthA = Integer.parseInt(date.nextToken());
                yearA = Integer.parseInt(date.nextToken());
                dayB = Integer.parseInt(date.nextToken());
                monthB = Integer.parseInt(date.nextToken());
                yearB = Integer.parseInt(date.nextToken());
                
                loc = new Location(locationName, townname, mediumPrice,
                        yearA, monthA, dayA, yearB, monthB, dayB);
                activ = new StringTokenizer(st.nextToken(), ",");
                while (activ.hasMoreTokens()) {
                    loc.addActivity(activ.nextToken());
                }                
                hierarchy.getLocations().add(loc);
            }
            System.out.println(Hierarchy.getInstance());
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } catch(DateException e) {
            e.printStackTrace();
        } finally {
            try{
                if (stream != null) {
                    stream.close();
                }
                if (lnr != null) {
                    lnr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        HolidayPlanner hp = new HolidayPlanner("test/test1");
        hp.readStoreData();
    }   
}
