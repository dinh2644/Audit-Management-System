import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;
import java.util.HashSet;
import java.util.InputMismatchException;

/**
 * Employee audit class
 * 
 * @author Tu Dinh
 * @version 2022.05.12
 * 
 * Description:
 * 
 * You are the payroll clerk working at Aldi and is responsibile for overlooking through the manager
 * and associate's financial information. 
 * 
 * 1st part of this class will have the selection sort that will sort a set of instructions for after you do your final audits for the employees.
 * 
 * 2nd part of this class will contain a main method which will receive the two generated text files "manager" and "associate" which
 * will then print an output of both the file's information into a single terminal.
 */

public class EmployeeAudit {
    //field declaration
    private String[] fileArray; //array for file audits
    
    /**
     * Constructor for fileArray
     */
    public EmployeeAudit(){
        fileArray = new String[5];
        setFileProcess();
    }
    
    //set method(s)
    
    /**
     * A method to prepopulate the new fileArray that pre-contains a storage of 5 indexes (0-4).
     */
    public void setFileProcess(){
        fileArray[0] = "2. Allowance checker";
        fileArray[1] = "3. Report deduction to IRS";
        fileArray[2] = "1. Time clock checker";
        fileArray[3] = "4. Direct deposit or paycheck";
        fileArray[4] = "5. Initiate pay transfer to employee";
    }
    
    //sort method(s)
    
    /**
     * A selection sort that will rearrange the string values in fileArray using ASCII order.
     * 
     * Citation(s): Class notes
     */
    public void sortInstructions(){
        String holder = null;
        int lowIndex = 0;
        for(int startIndex = 0; startIndex < fileArray.length-1; startIndex++){
            lowIndex = startIndex;
            for(int nextIndex = startIndex+1; nextIndex < fileArray.length; nextIndex++){
                if(fileArray[nextIndex].compareTo(fileArray[lowIndex])<0){
                    lowIndex = nextIndex;
                }
            }
            // swap
            holder = fileArray[startIndex];
            fileArray[startIndex] = fileArray[lowIndex];
            fileArray[lowIndex] = holder;
        }
        System.out.println("***********************" + "\n" +
        "File process checklist" + "\n" + "***********************");
        System.out.println();
        printFileArray();
    }
    
    /**
     * A method that displays the (unsorted) string values in fileArray into the terminal.
     * 
     * Citation(s): Class notes
     */
    public void printFileArray(){
        for(int index=0;index<fileArray.length; index++){
            System.out.println(fileArray[index]);
        }
        System.out.println();
    }
    
    /*-------------------------------------------Main Method-------------------------------------------*/
    
    /**
     * Main method for EmployeeAudit
     * 
     * Description: 
     * 
     * Creates an output file called "EmployeesFiles.txt" which allows us to write formatted data
     * through the usage of HashSet and scanners. The new EmployeeAudit.txt will have an overview of the store manager
     * and associate's information for (you) the payroll clerk to check and organize time sheet and/or
     * any sort of other data that contains in the audit. 
     * 
     * @throws FileNotFoundException - declares exeception for if the right file isn't located during execution
     * 
     * @Citation(s): 
     * https://stackoverflow.com/questions/40598402/java-io-using-scanner-and-printwriter-to-copy-the-contents-of-a-text-file-and-p
     * 
     */
    public static void main(String[] args) throws FileNotFoundException{ //thrown when file with specified pathname doesn'nt exist
        //Writes to a nested writer which is the formatted data from the manager.txt and associate.txt files
        //It is used to print the formatted representation of objects to the text-output stream
        //good for generating reports
        PrintWriter pw = new PrintWriter(new File("EmployeesFiles.txt"));
        
        //Separate scanners for the two newly created text files
        Scanner sc1 = new Scanner(new File("manager.txt"));
        Scanner sc2 = new Scanner(new File("associate.txt"));
        
        //HashSet to hold information about manager when writing to a manager.txt files in manager class. Same goes for sales associate.
        HashSet<StoreManager> managerSet = new HashSet<StoreManager>();
        HashSet<Associate> associateSet = new HashSet<Associate>();
        
        /**
         * Both while loop are the same, lines 133-138 for example, will scan each line in manager/associate.txt file up to hourly wage.
         * Then lines 137-142 for example will continue to read on each line until it appends everything to the HashSet to display.
         */
        try{
        while (sc1.hasNext()) {
            String positionType = sc1.nextLine();
            String positionStatus = sc1.nextLine();
            String name = sc1.nextLine();
            sc1.skip("Monthly hours: ");
            double monthlyHours = sc1.nextDouble();
            sc1.skip("\nHourly wage: ");
            double hourlyWage = sc1.nextDouble();
            if (positionType.equals("Manager")) {
                double monthlyStockReceived = sc1.nextDouble();
                double monthlyItemsSold = sc1.nextDouble();
                StoreManager manager = new StoreManager(name,monthlyHours,
                hourlyWage,monthlyStockReceived,monthlyItemsSold);
                managerSet.add(manager);
            }
        }
        while (sc2.hasNext()) {
            String positionType = sc2.nextLine();
            String positionStatus = sc2.nextLine();
            String name = sc2.nextLine();
            sc2.skip("Monthly hours: ");
            double monthlyHours = sc2.nextDouble();
            sc2.skip("\nHourly wage: ");
            double hourlyWage= sc2.nextDouble();
            if(positionType.equals("Associate")){
                double scanningRate = sc2.nextDouble();
                Associate salesAssociate = new Associate(name,monthlyHours,hourlyWage,scanningRate);
                associateSet.add(salesAssociate);
            }
        }
        
        //Visuals for EmployeeFiles.txt
        System.out.println("******************************************************");
        System.out.println("              ALDI MONTHLY EMPLOYEE AUDIT"+"\n"+
        "                  For: Payroll clerk");
        System.out.println("******************************************************");
        System.out.println();        
        System.out.println("--------------------" +"\n"+
        "Manager Information"+"\n"+
        "--------------------");  
        
        /**
         * The following two for loops are similiar with respect to their hash set. Contains a bunch of 
         * print statements and has miscellaneous info where a single if statement to checks if the 
         * employee is eligible for the monthly employee of the year if the criterias are matched.
         */
        for (StoreManager manager : managerSet) {
            System.out.println("Status: " + manager.positionStatus());
            System.out.print(manager);
            System.out.println(String.format(" (%.2f", manager.salesPercentManager()) + "% satisfied)");
            System.out.println("Monthly Gross Pay: $" + String.format("%.1f", manager.calculatePay()));
            System.out.println();
            System.out.println("Is " + manager.getName() + " eligible for overtime pay? ");
            if (manager.checkOvertimePay() == true) {
                System.out.println("-Yes, " + manager.getName() + " is eligible\nOvertime payment: $" +
                manager.calculateOvertimePay());
            } else {
                System.out.println("-No, " + manager.getName() + " does not qualify\n");
            }
            System.out.println();
        }
        System.out.println("----------------------" +"\n"+
        "Associate Information"+"\n"+
        "----------------------");
        for (Associate associate : associateSet) {
            System.out.println("Status: " + associate.positionStatus());
            System.out.print(associate);
            System.out.println(" (goal: ∼80%)");
            System.out.println("End of day till balance: $" + associate.tillBalanceReport());
            System.out.println("Monthly Gross Pay: $" + associate.calculatePay());
            System.out.println();
            System.out.println("Is " + associate.getName() + " eligible for overtime pay? ");
            if (associate.checkOvertimePay() == true) {
                System.out.println("-Yes, " + associate.getName() + " is eligible\nOvertime payment: $" +
                associate.calculateOvertimePay());
            } else {
                System.out.println("-No, " + associate.getName() + " does not qualify\n");
            }
            System.out.println("Does " + associate.getName() + " get a cash violation for this month? ");
            if(associate.tillBalanceReport() < 380) {
                System.out.println("- Yes, " + associate.getName() +"'s cash till is under $380, which means " +
                 " he/she has lost too much this month.");
            }else{
                System.out.println("-No, " + associate.getName() + "'s till balance is safely within the $400 range.");
            }
        }
        }catch(InputMismatchException e) { 
            //if there's any extra or less lines in the manager/associate.txt files, print error message
            System.out.print("Error: Cannot find next double or string.");
        }
        pw.close(); //end for loop and printwriter
    }
}