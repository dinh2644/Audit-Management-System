import javax.swing.JFrame;

/**
 * Coupon frame class that is linked with CouponPanel
 * 
 * @author Tu Dinh
 * @version 2022.05.12
 */
public class CouponGenerator{
    //fields
    private JFrame frameObject;//object to create frame
    
    /**
     * Constructor for CouponGenerator
     * 
     * @Citation(s): Class notes
     */
    public CouponGenerator(){
        //Create frame
        frameObject = new JFrame("Aldi coupon generator");
        //Make popup of frame in the center of monitor
        frameObject.setLocationRelativeTo(null);
        //Implements panel from CouponPanel class
        frameObject.add(new CouponPanel());
        //Forgot what this does
        frameObject.pack();
        //Closes frame
        frameObject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Makes frame visible
        frameObject.setVisible(true);
    }
}