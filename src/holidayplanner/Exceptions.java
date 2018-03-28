package holidayplanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
* The Exceptions class implements specific exceptions
* for wrong situations including bad available period range
* @author  Draghici Ruxandra
* @version 1.0
 */
class DateException extends Exception {
    public DateException() {
        super("The available date is not valid!");
    }
}
