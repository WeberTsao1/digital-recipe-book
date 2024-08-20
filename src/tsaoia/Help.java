//9-17-2019
//IB ComSci SL 2
//Purpose of class: Providing user help to use the application
package tsaoia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Help extends JFrame implements ActionListener
{
    //declaration of variables
    public final Color MAIN_COLOR = new Color(235, 171, 194);
    public final Color SIDE_COLOR = new Color(111, 129, 209);
    public final Font MENU_FONT = new Font("Rockwell", Font.PLAIN , 20);

    private JLabel body;
    private JPanel buttonHolder;
    private JButton close;
    
    public Help()
    {
        super("Help");
        this.setBounds(620,150,630,800);
        this.getContentPane().setBackground(MAIN_COLOR);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        body = new JLabel("<html>" + "<li>" + "Search - For search feature you should click on "
                + "one of the radio buttons before entering information." + "</li>" +
                "<li>" + "Advanced Search - For advanced search feature you would always "
                + "have to include the origin of the dish as one of the search criteria. "
                + "Also make sure you select more than 1 criteria." + "</li>"
                + "<li>" + "Portion Scaler - For the access of portion scaler please enter the "
                + "dish information page by clicking on the "
                + "dishes in the database page. On the bottom of the page you will see a text field next to the 'portion scaler' button "
                + "Please enter a number in percent form then press the button." + "</li>" + "<li>" + "Dish Adder - "
                + "For the access of dish adder please "
                + "go to dish information page first. When entering the information about your dish first"
                + " enter text/number or select item and then press 'input' button. After pressing the 'input' "
                + " button, you can press 'next' button to proceed. During the process, you might notice that there is a button called"
                + " 'No More ingredients'. The button is for when there's is no more ingredients left with your recipe. "
                + "Clicking the button will complete your recipe saving process. You also are not required to press that button to complete "
                + "the recipe saving process, after inputting all 13 ingredients (this programs limit) you will "
                + "see a button called 'finish' appear: pressing the button will also complete your recipe saving process" + "</li>" + "</html>");
        body.setFont(MENU_FONT);
        body.setForeground(Color.BLACK);
        
        buttonHolder = new JPanel(new FlowLayout());
        buttonHolder.setBackground(SIDE_COLOR);
        
        close = new JButton("Close");
        close.addActionListener(this);
        buttonHolder.add(close);
        
        this.add(body, BorderLayout.CENTER);
        this.add(buttonHolder, BorderLayout.SOUTH);
        this.setVisible(true);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        
        if (command.equals("Close"))
        {
            this.dispose();
        }
    }
    
    public static void main(String[] args)
    {
        Help objHelp = new Help();
    }
    
}
