/**
 * Aldi employee class (abstract)
 * 
 * @author Tu Dinh
 * @version 2022.05.12
 * 
 * Description:
 * 
 * Here at Aldi, our goal is to provide customers with the best prices on quality groceries. To keep up with the mission statement, our Aldi employees' approach is based on 
 * our three core values; consistency, simplicity, and responsibility. Within any local Aldi store, the store operation will be run by our amazing managers and associates. This abstract class
 * will contain three variables of any Aldi employee whether it be a manager or employee. 
 */

public abstract class AldiEmployee implements AldiBot{
    //field declartion
    private String name; //Name
    private double hourlyWage; //Hourly pay
    private double monthlyHours;// Total hours clocked in within a month
    
    /**
     * Constructor for AldiEmployee which is a parent class that implements the interface class "AldiBot"
     * 
     * @param name - enter your name
     * @param basePay - enter any number
     * @param monthlyHours - enter your monthly hours (typically 120-200 hours for managers/associates)
     */
    public AldiEmployee(String name,double monthlyHours,double hourlyWage){
        this.name = name;
        this.monthlyHours = monthlyHours;
        this.hourlyWage = hourlyWage;
    }
    
    //position status
    
    /**
     * Abstract method of positionStatus that is used in both StoreManager and Associate class which determines if a manager is an assistant or 
     * store manager and if an associate is part/full time.
     */
    public abstract String positionStatus();
    
    //set method(s) for each fields/variables
    
    /**
     * Change the name of the employee
     * 
     * @param name - set new name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Change monthly hours of the employee
     * 
     * @param monthlyHours - set new monthly hours
     */
    public void setMonthlyHours(double monthlyHours) {
        this.monthlyHours = monthlyHours;
    }
    /**
     * Change hourly pay of the employee
     * 
     * @param hourlyWage - set new hourly pay
     */
    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }
    
    //retrieve method(s) for each fields/variables
    
    /**
     * Retrieve name of the employee
     * 
     * @return name of employee
     */
    public String getName() {
        return name;
    }
    /**
     * Retrieve the monthly hours of the employee
     * 
     * @return number of monthly hours
     */
    public double getMonthlyHours() {
        return monthlyHours;
    }
    /**
     * Retrieve the hourly pay of the employee
     * 
     * @return number of hourly pay
     */
    public double getHourlyWage() {
        return hourlyWage;
    }
    
    //toString method for abstract class
    
    /**
     * Method to return the values in a string format for abstract class
     * 
     * @return all objects in string format
     */
    @Override
    public String toString() {
        return 
        name +
        "\nMonthly hours: " + monthlyHours +
        "\nHourly wage: " + hourlyWage;
    }
}