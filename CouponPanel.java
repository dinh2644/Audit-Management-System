import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import java.util.Random;
import javax.swing.BoxLayout;

/**
 * Coupon panel class that is a subclass of JPanel and linked with CouponGenerator
 * 
 * @author Tu Dinh
 * @version 2022.05.12
 */
public class CouponPanel extends JPanel{
    private JLabel labelObject;//first sentence in gui
    private JLabel labelObject2;//second sentence in gui
    private JButton buttonObject;//press button
    
    /**
     * Constructor for panel of CouponGenerator
     * 
     * @Citation(s): Class notes
     */
    public CouponPanel(){
        super();
        //box layout
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        //label
        labelObject = new JLabel("Press to generate 100% off Aldi coupon codes");
        labelObject.setAlignmentX(CENTER_ALIGNMENT);
        add(labelObject);
        //button
        buttonObject = new JButton("Press");
        buttonObject.setAlignmentX(CENTER_ALIGNMENT);
        buttonObject.addActionListener(new DemoButtonListener());
        add(buttonObject);
        //label2
        labelObject2 = new JLabel("(can be used at cash register)");
        labelObject2.setAlignmentX(CENTER_ALIGNMENT);
        add(labelObject2);
        //size
        setPreferredSize(new Dimension(300,100));
        //colors
        setBackground(new Color(255,0,255));
    }
    
    /* ................ private inner class ................ */
    private class DemoButtonListener implements ActionListener{
        /**
         * Private inner class for when upon hitting button, it will print a mixed series of letters and numbers 
         * that will in this case, display as coupon codes
         * 
         * @param ActionEvent event - An on event linked with JButton that prints a series of letters and numbers
         * into the terminal which is basically coupon codes in this scenario
         * 
         * @Citation(s): 
         * https://stackoverflow.com/questions/20536566/creating-a-random-string-with-a-z-and-0-9-in-java
         * @author: MouseLearnJava
         *
         */
        @Override
        public void actionPerformed(ActionEvent event){
            int len = 10;
            String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
            +"lmnopqrstuvwxyz!@#$%&";
            Random rand = new Random();
            //String is immutable while StringBuilder is mutable. If you wanted to add Strings, you would need to use the "+" sign
            //and create a new String object. With StringBuilder, there is no need to go through that creation and copy process since its
            //mutable and all is needed is the .append method. In other words, StringBuilder is a modifiable succession of characters.
            StringBuilder sb = new StringBuilder(len);
            for (int i = 0; i < len; i++){
                sb.append(chars.charAt(rand.nextInt(chars.length())));// sb appends a random character from "chars" until it reaches 
                //10 characters and appends it as "sb" in which for example, we get a new String of "afafa!49898FA8ga"
                System.out.print(sb);
            }
            System.out.println();
        }
    }
}