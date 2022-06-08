import java.util.Random;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Associate class
 * 
 * @author Tu Dinh
 * @version 2022.05.12
 * 
 * Description:
 * 
 * You are an associate working at Aldi, the following information(params) will be collected from you 
 * in your previous month of work to be passed onto the payroll clerk so they can audit and finalize your paycheck.
 * 
 * Side note: scanningRate will not affect your payroll but will be used to determine
 * if you will get a cash violation if your cash till is below $380.00.
 */


public class Associate extends AldiEmployee {
    //field declaration
    private double scanningRate;//a number that determines the average food/item scanned per minute
    
    /**
     * Constructor for Associate which is a sub-class of AldiEmployee 
     * 
     * @param name - enter your name
     * @param monthlyHours - enter your monthly hours (typically for associates, 160 hours is max since 40hrs * 4 weeks = 160.) 
     * @param hourlyWage - enter any number
     * @param scanningRate - enter any number (recommended: 0-150)
     */
    public Associate(String name,double monthlyHours,double hourlyWage,double scanningRate) {
        super(name,monthlyHours,hourlyWage);
        this.scanningRate = scanningRate;
    }
    
    //method(s) from abstract class
    
    /**
     * For associates, this method will decide if you are a part time associate if your monthly hours
     * dictates that you work no more than 30 hours a week for the whole month, else you are full time.
     * 
     * @return the associate type depending on the monthly hours to determine if you are a full/part time
     */
    @Override
    public String positionStatus(){
        String positionType = null;
        if(getMonthlyHours() < 121){
            return "Part time";
        }else if(getMonthlyHours() >=121){
            return "Full time";
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
     * As an associate at Aldi, you are given your own cash till with a default balance of $400. Your
     * goal through your entire career here is to keep your till balance as close to 400 as possible. 
     * Too much over or under before the first day of a new month will give you a cash violation strike 
     * where 3 strikes results in termination. 
     * 
     * @return number depending on which arithmetic operator it underwent from the random number generated
     * which determines the till balance for the associate
     */
    @Override
    public double tillBalanceReport() {
        Random num = new Random();
        double n = num.nextInt(700);
        if (getScanningRate() < 80 && n < 100) { 
            //400-24 = 375 If SR is betweem 75-79% and you scanned less than 100 items
            return TILL - (TILL * tillPenalty + 21); 
        }else if(getScanningRate() < 75 && n < 300){
            //400-10 = 390 If SR is between 70-74% and scanned between 100-299 items
            return TILL - (TILL * tillPenalty + 6); 
        }else if(getScanningRate() < 70 && n < 500){
            //400-5 = 395 If SR is below 70% and scanned between 300-499 items
            return TILL - (TILL * tillPenalty + 1); 
        }else{
            return TILL; //400 If SR is 80% or higher or if you scanned 500 or more items
        }
    }
    
    //set and retrieve method(s) for Associate class
    
    /**
     * Change scanning rate for the associate
     * 
     * @param scanningRate - set new number for scanningRate
     */
    public void setScanningRate(double scanningRate){
        this.scanningRate = scanningRate;
    }
    /**
     * Retrieve scanning rate of the associate
     * 
     * @return number of scanningRate
     */
    public double getScanningRate() {
        return scanningRate;
    }
    
    //toString method for Associate class
    
    /**
     * Method to return the values in a string format for associate class
     * 
     * @return the objects of associate in a string format in each line
     */
    @Override
    public String toString() {
        return super.toString() +
        "\nMonthly average scanning rate : " + scanningRate +"%";
    }
    
    //file i/o
    
    /**
     * This method will write to a new file in which you have to name it "associate.txt" for this specific class. The file will display all parent class's information plus the additional variable within the 
     * Associate class (scanningRate) on separate lines 
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
                writer.write("Associate\n" + "Status: " + positionStatus() + "\n" + super.toString() + "\n" +
                scanningRate);
                
                //3. close the data
                writer.close();
            }catch(IOException ioException){
                System.out.println("Error: Can't process file");
            }
        }
    }
}