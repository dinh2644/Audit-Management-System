import java.util.Random;
import javax.swing.JFileChooser;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

/**
 * Store manager class
 * 
 * @author Tu Dinh
 * @version 2022.05.12
 * 
 * Description:
 * 
 * You are a store manager working at Aldi, the following information(params) will be collected from you 
 * in your previous month of work to be passed onto the payroll clerk so they can audit and finalize your paycheck.
 * 
 * Side note: Your responsibility to overview the monthly items received/sold will not affect your payroll.
 */

public class StoreManager extends AldiEmployee{
    //field declaration
    private double monthlyStockReceived;//a number that will be totaled from all the items(units) that the weekly truck came in to provide item stock 
    private double monthlyItemsSold;//a number that shows how many items(units) your store has sold in that month 
    
    /**
     * Constructor for Manager which is a sub-class of AldiEmployee
     * 
     * @param name - enter your name
     * @param monthlyHours - enter your monthly hours (typically for associates, 160 hours is max since 40hrs * 4 weeks = 160.) 
     * @param hourlyWage - enter any number
     * @param monthlyStocksReceived - enter any number
     * @param monthlyItemsSold - enter any number (this number may exceed that of monthlyStocksReceived since trucks only provide stocks for items that sells fast)
     */
    public StoreManager(String name,double monthlyHours,double hourlyWage,double monthlyStockReceived,double monthlyItemsSold) {
        super(name,monthlyHours,hourlyWage);
        this.monthlyStockReceived = monthlyStockReceived;
        this.monthlyItemsSold = monthlyItemsSold;
    }
    
    //method(s) from abstract class
    
    /**
     * For managers, this method will decide if you are an assistant manager if your hourly wage
     * dictates that you make less than $25/hr, else you are a store manager who makes more or equal to
     * $25/hr.
     * 
     * @return the manager type depending on the hourly wage which an assistant manager makes less than
     * $25/hr while store managers make $25/hr or more.
     */
    @Override
    public String positionStatus(){
        String positionType = null;
        if(getHourlyWage() < 25){
            return "Assistant manager";
        }else if(getHourlyWage() >=25){
            return "Store manager";
        }
        return positionType;
    }
    
    //methods from the interface class "PayrollSystem"
    
    /**
     * This method will calculate your gross pay before taxes and other deductions. 
     * It is your monthly hours times hourly wage.
     * 
     * @return the product of both monthlyHours and hourlyWage
     */
    @Override
    public double calculatePay() {
        return super.getMonthlyHours() * super.getHourlyWage();
    }
    /**
     * According to the Fair Labor Standards Act (FLSA), employees covered by the act must receive
     * overtime pay for hours worked over 40 in a week. This method checks if an employee has worked
     * for over 161 hours since 40 hours x 4 weeks equals 160 hours.
     * 
     * @return true if monthlyHours is equal to or greater than 161
     */
    @Override
    public boolean checkOvertimePay() {
        if (getMonthlyHours() >= 161.0) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Calculation to determine overtime pay for a specific employee 
     * 
     * @return product of hourlyWage, 1.5, monthlyHours which determines overtime payment
     * 
     * @Citation(s): By googling "overtime pay calculation"
     */
    @Override
    public double calculateOvertimePay(){
        return super.getHourlyWage() * 1.5 * getMonthlyHours();
    }
    /**
     * This returns 0 because manager are usually never on the cash register, thus they do not need a till report
     * 
     * @return 0 since store manager don't need a till report
     */
    @Override
    public double tillBalanceReport(){
        return 0;
    }
    
    //set and retrieve method(s) for Manager class
    
    /**
     * Change the number of monthly stock received
     * 
     * @param monthlyStockReceived - set new number for monthlyStockReceived
     */
    public void setMonthlyStockReceived(double monthlyStockReceived){
        this.monthlyStockReceived = monthlyStockReceived;
    }
    /**
     * Change the number of monthly items sold
     * 
     * @param monthlyItemsSold - set new number for monthlyItemsSold
     */
    public void setMonthlyItemsSold(double monthlyItemsSold){
        this.monthlyItemsSold = monthlyItemsSold;
    }
    /**
     * Retrieve the number of monthly stock received
     * 
     * @return number of monthlyStockReceived
     */
    public double getMonthlyStockReceived() {
        return monthlyStockReceived;
    }
    
    /**
     * Retrieve the number of monthly items sold
     * 
     * @return numberof monthlyItemsSold
     */
    public double getMonthlyItemsSold() {
        return monthlyItemsSold;
    }
    /**
     * Calculates the percentage of items sold compared to how many were supposed to be sold/received.
     * 
     * @return the value of monthlyItemsSold divded by monthyStockReceived times 100
     */
    public double salesPercentManager() {
        return (monthlyItemsSold / monthlyStockReceived) * 100;
    }
    
    //toString method for Associate class
    
    /**
     * Method to return the values in a string format for manager class
     * 
     * @return the objects of store manager in a string format in each line
     */
    @Override
    public String toString() {
        return super.toString() +
        "\nTotal monthly stock: " + monthlyStockReceived +
        "\nTotal items sold: " + monthlyItemsSold;
    }
    
    //file i/o
    /**
     * This method will write to a new file in which you have to name it "associate.txt" for this specific class. The file will display all parent class's information plus the additional variable within the 
     * Manager class (monthlyStockReceived, monthlyItemsSold) on separate lines 
     * 
     * @Citation(s): Class notes
     */
    public void generateAuditFile(){
        JFileChooser chooser = new JFileChooser("./");
        File fileObject = null;
        int buttonInt = chooser.showSaveDialog(null);
        if(buttonInt == JFileChooser.APPROVE_OPTION){
            try{
                //using JFileChooser
                fileObject = chooser.getSelectedFile();
                //end of JFileChooser
                
                //1. open the connection
                FileWriter writer = new FileWriter(fileObject);
                //2. write the data
                writer.write("Manager\n" + "Status: " + positionStatus() + "\n" + super.toString() + "\n" +
                monthlyStockReceived + "\n" + monthlyItemsSold);
            
                //3. close the data
                writer.close();
            }catch(IOException ioException){
                System.out.println("Error: Can't process file");
            }
        }
    }
}