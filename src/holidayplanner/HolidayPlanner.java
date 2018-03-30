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
    private String inputFileName, commandsFileName, outputFileName;
    Hierarchy hierarchy;
    
    public HolidayPlanner(String inputFileName, String commandsFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.commandsFileName = commandsFileName;
        this.outputFileName = outputFileName;
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
    
    public void readExecuteCommands() {
        FileReader stream = null;
        LineNumberReader lnr = null;
        FileWriter write = null;
        BufferedWriter out = null;
        StringTokenizer st, dates;
        int numberOfCommands, yearA, yearB, monthA, monthB, dayA, dayB;
        String commandName, arg1, arg2, arg3;
        AvailableDate date;
        Location loc;
        
        try {
            stream = new FileReader(commandsFileName);
            lnr = new LineNumberReader(stream);
            write = new FileWriter(outputFileName);
            out = new BufferedWriter(write);
            
            numberOfCommands = Integer.parseInt(lnr.readLine());
            for(int i = 0; i < numberOfCommands; i++){
                st = new StringTokenizer(lnr.readLine(), ",");
                commandName = st.nextToken();
                switch(commandName) {
                    case "getAll":
                        out.write(Hierarchy.getInstance().toString());
                        out.newLine();
                        break;
                    case "getInfo":
                        arg1 = st.nextToken();
                        loc = getInfo(arg1);
                        if (loc != null)
                            out.write(loc.toString());
                        else
                            out.write("Location not found!");
                        out.newLine();
                        break;
                    case "top5":
                        arg1 = st.nextToken();
                        arg2 = st.nextToken();
                        dates = new StringTokenizer(arg2, "-");
                        dayA = Integer.parseInt(dates.nextToken());
                        monthA = Integer.parseInt(dates.nextToken());
                        yearA = Integer.parseInt(dates.nextToken());
                        dayB = Integer.parseInt(dates.nextToken());
                        monthB = Integer.parseInt(dates.nextToken());
                        yearB = Integer.parseInt(dates.nextToken());
                        date = new AvailableDate(yearA, monthA, dayA, yearB, monthB, dayB);
                        ArrayList<Location> locat = new ArrayList<Location>();
                        locat.addAll(getTop5(arg1, date));
                        if (locat != null)
                            for(Location l : locat) {
                                if(locat.indexOf(l) != locat.size() - 1)
                                    out.write(l.getName() + ", ");
                                else out.write(l.getName());
                            }
                        else
                            out.write("Nu s-au gasit locatii disponibile in aceasta perioada!");
                        out.newLine();
                        break;
                    case "best10":
                        arg1 = st.nextToken();
                        loc = best10(arg1);
                        if(loc != null) 
                            out.write(loc.getName());
                        else
                            out.write("Nu s-a gasit o locatie pentru activitatea dorita!");
                        out.newLine();
                        break;
                    default:
                        break;
                }
            }
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
                if (out != null) {
                    out.close();
                }
                if (write != null) {
                    write.close();
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public Location getInfo(String locationName) {
        for(Location loc : Hierarchy.getInstance().getLocations()) {
            if (loc.getName().compareTo(locationName) == 0) {
                return loc;
            }
        }
        return null;
    }
    
    public Location best10(String activity) {
        ArrayList<Location> ret = new ArrayList<>();
        for (Location loc : Hierarchy.getInstance().getLocations()) {
            if(loc.getActivities().contains(activity))
                ret.add(loc);
        }
        Collections.sort(ret, new Comparator<Location>() {
                @Override
                public int compare(Location o1, Location o2) {
                    if (o1.getMediumPrice() < o2.getMediumPrice()) {
                        return -1;
                    } else if (o1.getMediumPrice() > o2.getMediumPrice())
                        return 1;
                    else return 0;
                }
            });
        if (ret.size() > 0)
            return ret.get(0);
        return null;
    }
    
    public ArrayList<Location> getTop5(String townName, AvailableDate date) {
        ArrayList<Location> ret = new ArrayList<>();
        ret.addAll(Hierarchy.getInstance().getLocationsFromTown(townName, date));
        if (ret.size() == 0)
            ret.addAll(Hierarchy.getInstance().getLocationsFromDistrict(townName, date));
        if (ret.size() == 0) 
            ret.addAll(Hierarchy.getInstance().getLocationsFromCountry(townName, date));
        if (ret != null) {
            Collections.sort(ret, new Comparator<Location>() {
                @Override
                public int compare(Location o1, Location o2) {
                    if (o1.getMediumPrice() < o2.getMediumPrice()) {
                        return -1;
                    } else if (o1.getMediumPrice() > o2.getMediumPrice())
                        return 1;
                    else return 0;
                }
            });
        }
        if(ret.size() <= 5)
            return ret;
        else return (ArrayList)ret.subList(0, 4);
    }
    
    public static void main(String[] args) {
        HolidayPlanner hp = new HolidayPlanner("test/test01/test", "test/test01/input", "test/test01/out");
        hp.readStoreData();
        hp.readExecuteCommands();
    }   
}
