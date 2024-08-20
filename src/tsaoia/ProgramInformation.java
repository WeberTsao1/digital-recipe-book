//9-17-2019
//IB ComSci SL 2
//Purpose of class: Providing user the informations about this application

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

public class ProgramInformation extends JFrame implements ActionListener
{
    //declaration of variables
    public final Color MAIN_COLOR = new Color(85, 204, 217);
    public final Color SIDE_COLOR = new Color(124, 190, 191);
    public final Color EXTRA_COLOR = new Color(67, 146, 224);
    public final Font MENU_FONT = new Font("Rockwell", Font.BOLD , 30);
    public final Font BODY_FONT = new Font("Rockwell", Font.BOLD , 25);

    private JLabel title;
    private JLabel body;
    private JPanel buttonHolder;
    private JButton close;
    
    private JMenuBar helpMenuBar;
    private JMenu helpMenu;
    private JMenuItem helpItem;
    
    public ProgramInformation()
    {
        //construction of frame
        super("Program Information");
        this.setBounds(650,150,600,800);
        this.getContentPane().setBackground(MAIN_COLOR);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        //an introduction paragraph
        title = new JLabel("<html>" + "This program is made for people " +
                "who loves to cook. In this program you would "
                + "be able to view and store food recipes." + "</html>");
        title.setForeground(Color.BLACK);
        title.setFont(MENU_FONT);
        
        //the body text
        body = new JLabel("<html>" + "This program provides various features such as search, advanced search, "
                + "portion scaler, and a dish adder feature. Search feature allow user to search for "
                + "a dish through 1 particular aspect of the dish. Advanced search offer user "
                + "the ability to search for a dish by multiple aspects of a dish. Since there "
                + "might not always be a dish that matches the user selection, the program would find "
                + "the best match for the user. Portion scaler allows user to change the displayed portion "
                + "by user defined scale (In percent form to indicate increase/decrease). "
                + "Dish adder feature allows user to add user’s own recipe into "
                + "the database of the program." + "</html>");
        body.setForeground(Color.WHITE);
        body.setFont(BODY_FONT);
        
        buttonHolder = new JPanel(new FlowLayout());
        buttonHolder.setBackground(SIDE_COLOR);
        
        close = new JButton("Close");
        close.addActionListener(this);
        buttonHolder.add(close);
        
        //For help menu
        helpMenuBar = new JMenuBar();
        
        helpMenu = new JMenu("Problem?");
        helpMenuBar.add(helpMenu);
        
        helpItem = new JMenuItem("Help");
        helpItem.addActionListener(this);
        helpMenu.add(helpItem);
        
        //for adding GUI components into frame
        this.add(helpMenuBar);
        this.setJMenuBar(helpMenuBar);
        this.add(title, BorderLayout.NORTH);
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
        if (command.equals("Help"))
        {
            Help objHelp = new Help();
            this.dispose();
        }
    }
    
    public static void main(String[] args)
    {
        ProgramInformation objProgramInformation = new ProgramInformation();
    }
    
}
