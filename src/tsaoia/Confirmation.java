//IB ComSci SL 2
//9-27-2019
//Purpose of class: to ask user for confirmation on whether the user wants to 
//delete the last entry from the database
package tsaoia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Confirmation extends JFrame implements ActionListener
{
    //Declarations
    public final Color MAIN_COLOR = new Color(90, 209, 185);
    public final Font MAIN_FONT = new Font("Rockwell", Font.BOLD , 35);
    
    public final java.net.URL WARNING = getClass().getResource("warning.jpg");
    public final ImageIcon WARNING_PIC = new ImageIcon(new ImageIcon(WARNING).getImage().getScaledInstance(420,250,Image.SCALE_DEFAULT));
    
    private JLabel mainText;
    private JLabel picHolder;
    private JButton confirm;
    private JButton cancel;
    private JPanel buttonHolder;
    
    private JMenuBar helpMenuBar;
    private JMenu helpMenu;
    private JMenuItem helpItem;
    
    String[] columnName = {"ID", "Name"};
    
    DatabaseAccess accessor = new DatabaseAccess("RecipeDb");
    Connection myDbConn;
        
    //for deleting in case of mistake
    String dbQuery2 = "DELETE FROM FoodInfo WHERE ID = ?";

    
    public Confirmation()
    {
        //construction of frame
        super("CONFIRMATION");
        this.setBounds(220,100,930,700);
        this.getContentPane().setBackground(MAIN_COLOR);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        mainText = new JLabel("Are you sure you would like to delete the last entry from the database?");
        mainText.setFont(MAIN_FONT);
        
        picHolder = new JLabel(WARNING_PIC);
        
        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        
        buttonHolder = new JPanel(new FlowLayout());
        buttonHolder.setBackground(Color.DARK_GRAY);
        //populating the panel with buttons
        buttonHolder.add(confirm);
        buttonHolder.add(cancel);
        
        helpMenuBar = new JMenuBar();
        
        helpMenu = new JMenu("Problem?");
        helpMenuBar.add(helpMenu);
        
        helpItem = new JMenuItem("Help");
        helpItem.addActionListener(this);
        helpMenu.add(helpItem);
        
        //adding objects to frame
        this.add(helpMenuBar);
        this.setJMenuBar(helpMenuBar);
        this.add(mainText, BorderLayout.NORTH);
        this.add(picHolder, BorderLayout.CENTER);
        this.add(buttonHolder, BorderLayout.SOUTH);
        this.setVisible(true);
    }
    
    public static void main(String[] args)
    {
        Confirmation objConfirmation = new Confirmation();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //for initialization
        int lastEntryId = 0;
        
        //Event driven algorithms
        String command = e.getActionCommand();
        if (command.equals("Confirm"))
        {
            myDbConn = accessor.getDbConn();
            //lastEntryId is the id of last item, or the id number of the most recent entry
            Object [][] data = accessor.getData("FoodInfo", columnName);
            lastEntryId = ((data.length) - 1);
            //for checking the correctness of the value
            //System.out.println("Id number of last entry: " + lastEntryId);
            
            try
            {
                PreparedStatement ps = myDbConn.prepareStatement(dbQuery2);

//              for deleting from FoodInfo Table where the id equals to the last id
                ps.setInt(1, lastEntryId); 
                ps.executeUpdate();
                accessor.closeDbConn();
                
                this.dispose();
                
            }
            catch(SQLException p)
            {
                Error objError = new Error(6);
            }

        }
        
        if (command.equals("Cancel"))
        {
            accessor.closeDbConn();
            this.dispose();
        }
        if (command.equals("Help"))
        {
            Help objHelp = new Help();
        }
    }
    
}
