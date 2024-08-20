//IB ComSci SL 2
//9-19-2019
//Purpose of class: To access a particular dish through entering the id of dish
package tsaoia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DishAccessor extends JFrame implements ActionListener
{
    //declaration of variables
    //colors for the frame
    public final Color MAIN_COLOR = new Color(128, 255, 0);
    //the main bigger sized font
    public final Font MENU_FONT = new Font("Rockwell", Font.BOLD , 30);
    
    private JLabel title;
    private JTextField idTf;
    private JPanel promptPan;
    private JButton enter;
    
    private JMenuBar helpMenuBar;
    private JMenu helpMenu;
    private JMenuItem helpItem;
    
    String[] idCol = {"ID"};
    
    int idCeiling = 0;
    
    public DishAccessor()
    {
        //the construction of frame
        super("Dish Accessor");
        this.setBounds(600,150,1000,800);
        this.getContentPane().setBackground(MAIN_COLOR);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        title = new JLabel("Enter the ID number of dish you are trying to look into below: ");
        title.setFont(MENU_FONT);
        
        idTf = new JTextField(4);
        idTf.setFont(MENU_FONT);
        
        promptPan = new JPanel(new BorderLayout());
        promptPan.setBackground(MAIN_COLOR);
        promptPan.add(title, BorderLayout.CENTER);
        promptPan.add(idTf, BorderLayout.SOUTH);
        
        enter = new JButton("Enter");
        enter.addActionListener(this);
        
         //For help menu
        helpMenuBar = new JMenuBar();
        
        helpMenu = new JMenu("Problem?");
        helpMenuBar.add(helpMenu);
        
        helpItem = new JMenuItem("Help");
        helpItem.addActionListener(this);
        helpMenu.add(helpItem);
        
        //Connect with the database then put the data into an two dimensional array 'data'
        DatabaseAccess accessor = new DatabaseAccess("RecipeDb");
        Connection myDbConn;

        myDbConn = accessor.getDbConn();

        Object[][] data = accessor.getData("FoodInfo", idCol);
        accessor.closeDbConn();
        
        //the highest id number inside the database
        idCeiling = (data.length) - 1;
        
        //for adding GUI components into frame
        this.add(helpMenuBar);
        this.setJMenuBar(helpMenuBar); 
        this.add(promptPan, BorderLayout.CENTER);
        this.add(enter, BorderLayout.SOUTH);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        int idValue;
        String command = e.getActionCommand();
        //to accept id number of dish inputted by user
        if (command.equals("Enter"))
        {
            try 
            {
                idValue = Integer.parseInt(idTf.getText());
                //user input of id cannot be below 0 nor above highest id
                //only id values within the database will be accepted
                if (idValue >= 0 && idValue <= idCeiling)
                {
                    DishInformation objDishInfo = new DishInformation(idValue);
                    this.dispose();
                }
                else
                {
                    Error objError = new Error(4);
                }
            }
            catch (Exception l)
            {
                Error objError = new Error(2);
            }
        }
        if (command.equals("Help"))
        {
            Help objHelp = new Help();
        }
    }
    
    public static void main(String[] args)
    {
        DishAccessor objDishAccessor = new DishAccessor();
    }
    
}
