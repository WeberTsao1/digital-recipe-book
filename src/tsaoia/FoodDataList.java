//9-18-2019
//IB ComSci SL 2
//Purpose of class: Displaying the dishes stored inside database

package tsaoia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

public class FoodDataList extends JFrame implements ActionListener
{
    //Declaring variables
    public final Color MAIN_COLOR = new Color(43, 107, 92);
    public final Color SIDE_COLOR = new Color(91, 94, 93);
    public final Color TABLE_COLOR = new Color(121, 217, 80);
    public final Font MENU_FONT = new Font("Rockwell", Font.BOLD , 30);
    public final Font TABLE_FONT = new Font("Rockwell", Font.PLAIN , 10);
    public final Font HEADER_FONT = new Font("Rockwell", Font.BOLD , 20);
    
    private final String[] COLUMN_HEADER = {"ID", "Name", "Origin", "Type", "Property1",
                                            "Property2", "Description", "Source"};
    String[] columnName = {"ID", "Name", "Origin", "Type", "Property1",
                            "Property2", "Description", "Source"};
    private JTable dishTable;
    private JScrollPane myPane;
    private JTableHeader tableTitle;

    private JLabel title;
    private JPanel buttonHolder;
    private JButton accessButt;
    private JButton search;
    private JButton advancedSearch;
    private JButton dishAdder;
    private JButton back;
    private JButton exit;
    private JButton refresh;
    private JButton deleteDish;
    
    private JMenuBar helpMenuBar;
    private JMenu helpMenu;
    private JMenuItem helpItem;
    
    public FoodDataList()
    {
        //construction of frame
        super("FoodDataList");
        this.setBounds(400,20,1000,1000);
        this.getContentPane().setBackground(MAIN_COLOR);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        title = new JLabel("Dishes within the database: ");
        title.setFont(MENU_FONT)        ;
        title.setForeground(Color.BLACK);
        
        buttonHolder = new JPanel(new FlowLayout());
        buttonHolder.setBackground(SIDE_COLOR);
        
        accessButt = new JButton("Access A Dish");
        accessButt.addActionListener(this);
        
        search = new JButton("Search");
        search.addActionListener(this);
        
        advancedSearch = new JButton("Advanced Search");
        advancedSearch.addActionListener(this);
        
        dishAdder = new JButton("Add Dish");
        dishAdder.addActionListener(this);
        
        back = new JButton("Back");
        back.addActionListener(this);
        
        exit = new JButton("Exit");
        exit.addActionListener(this);
        
        refresh = new JButton("Refresh Table");
        refresh.addActionListener(this);
        
        deleteDish = new JButton("Delete Last Entry");
        deleteDish.addActionListener(this);
        
        buttonHolder.add(deleteDish);
        buttonHolder.add(accessButt);
        buttonHolder.add(search);
        buttonHolder.add(advancedSearch);
        buttonHolder.add(dishAdder);
        buttonHolder.add(back);
        buttonHolder.add(exit);
        buttonHolder.add(refresh);
        
        //Connect with the database then put the data into an two dimensional array 'data'
        DatabaseAccess accessor = new DatabaseAccess("RecipeDb");
        Connection myDbConn;
        
        myDbConn = accessor.getDbConn();
        
        Object[][] data = accessor.getData("FoodInfo", columnName);
        accessor.closeDbConn();
        
        //setting up the JTable with two dementional array returned from database
        dishTable = new JTable(data,COLUMN_HEADER);
        dishTable.setRowHeight(20);
        dishTable.setFont(TABLE_FONT);
        dishTable.setGridColor(TABLE_COLOR);
        
        tableTitle = dishTable.getTableHeader();
        tableTitle.setFont(HEADER_FONT);
        tableTitle.setBackground(TABLE_COLOR);
        
        myPane = new JScrollPane();
        myPane.getViewport().add(dishTable);
        dishTable.setFillsViewportHeight(true);
        
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
        this.add(buttonHolder, BorderLayout.NORTH);
        this.add(title, BorderLayout.CENTER);
        this.add(myPane, BorderLayout.SOUTH);
        this.setVisible(true);
    }
    
    public static void main(String[] args)
    {
        FoodDataList objFoodDataList = new FoodDataList();
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        //calling other frames for different features
        if (command.equals("Delete Last Entry"))
        {
            Confirmation objConfirmation = new Confirmation();
        }
        
        if (command.equals("Access A Dish"))
        {
            DishAccessor objDishAccessor = new DishAccessor();
            
        }
        
        if (command.equals("Search"))
        {
            Search objSearch = new Search();
            this.dispose();
        }
        
        if (command.equals("Advanced Search"))
        {
            AdvancedSearch objAdvancedSearch = new AdvancedSearch();
            this.dispose();
        }
        
        if (command.equals("Add Dish"))
        {
            DishAdder objDishAdder = new DishAdder();
            this.dispose();
        }
        
        //for navigating the program
        if (command.equals("Back"))
        {
            this.dispose();
            Welcome objWelcome = new Welcome();
        }
        
        if (command.equals("Exit"))
        {
            System.exit(0);
        }
        
        if (command.equals("Refresh Table"))
        {
            this.dispose();
            FoodDataList objFoodDataList = new FoodDataList();
        }
        
        if (command.equals("Help"))
        {
            Help objHelp = new Help();
        }
    }
    
    
    
    
    
}
