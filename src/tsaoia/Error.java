//IB ComSci SL 2
//9-19-2019
//Purpose of class: To inform user that an error has occured
package tsaoia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Error extends JFrame implements ActionListener
{

    //Declarations
    public final java.net.URL WARNING = getClass().getResource("warning.jpg");
    public final ImageIcon WARNING_PIC = new ImageIcon(new ImageIcon(WARNING).getImage().getScaledInstance(420,250,Image.SCALE_DEFAULT));
    
    public final Color MAIN_COLOR = new Color(169, 201, 201);
    public final Color SIDE_COLOR = new Color(125, 83, 143);
    public final Font MENU_FONT = new Font("Rockwell", Font.BOLD , 30);
    
    private JLabel titlePic;
    private JLabel instruction;
    
    private JPanel buttonHolder;
    private JButton exit;
    
    private JMenuBar helpMenuBar;
    private JMenu helpMenu;
    private JMenuItem helpItem;
    
    public Error(int errorCode)
    {
        //construction of frame
        super("Error: " + errorCode);
        //constructing the frame
        this.setBounds(220,100,930,700);
        this.getContentPane().setBackground(MAIN_COLOR);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        titlePic = new JLabel(WARNING_PIC);
        
        instruction = new JLabel("<html>" + "Deafault Error Message" + "</html>");
        instruction.setFont(MENU_FONT);
        instruction.setForeground(SIDE_COLOR);
        
        if(errorCode ==1)
        {
            instruction.setText("Please enter letters from the alphabet");
        }
        if(errorCode ==2)
        {
            instruction.setText("Please enter numerical value");
        }
        if(errorCode ==3)
        {
            instruction.setText("Please select an option");
        }
        if(errorCode ==4)
        {
            instruction.setText("Please enter a valid ID");
        }
        if (errorCode == 5)
        {
            instruction.setText("Error inserting into database");
        }
        if (errorCode == 6)
        {
            instruction.setText("Error deleting from database");
        }
        if (errorCode == 7)
        {
            instruction.setText("Please select a country of origin");
        }
        if (errorCode == 8)
        {
            instruction.setText("<html>" + "Please select MORE than just one aspect. "
                    + "(If you only want to search for 1 aspect, use the search feature)"
                    + "     Check 'Help' for menu to lean more. " + "</html>");
        }

        exit = new JButton("ok");
        exit.addActionListener(this);
        
        buttonHolder = new JPanel(new FlowLayout());
        buttonHolder.setBackground(MAIN_COLOR);
        buttonHolder.add(exit);
        
        helpMenuBar = new JMenuBar();
        
        helpMenu = new JMenu("Problem?");
        helpMenuBar.add(helpMenu);
        
        helpItem = new JMenuItem("Help");
        helpItem.addActionListener(this);
        helpMenu.add(helpItem);
        
        //adding objects to frame
        this.add(helpMenuBar);
        this.setJMenuBar(helpMenuBar);
        this.add(titlePic, BorderLayout.NORTH);
        this.add(instruction, BorderLayout.CENTER);
        this.add(buttonHolder, BorderLayout.SOUTH);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        
        if (command.equals("ok"))
        {
            this.dispose();
        }
        if (command.equals("Help"))
        {
            Help objHelp = new Help();
        }
    }
    
    public static void main(String[] args)
    {
        int exErrorCode = 0;
        Error objError = new Error(exErrorCode);
    }
    
}
