/**
 * AldiBot class (interface)
 * 
 * @author Tu Dinh
 * @version 2022.05.12
 * 
 * Description:
 * 
 * Here at Aldi's, we have a friendly AI bot named AldiBot that helps with financial calculations
 */

public interface AldiBot {
    //constant variables for the whole Aldi store system
    static final double TILL = 400.0;
    static final double tillPenalty = .01;
    
    //by default, all methods in interface class are public abstract
    double tillBalanceReport();// for associate class only
    double calculatePay();// for both manager and associate
    boolean checkOvertimePay();// for both manager and associate
    double calculateOvertimePay();// for both manager and associate
}