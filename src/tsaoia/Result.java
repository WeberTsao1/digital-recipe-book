//9-19-2019
//IB ComSci SL 2
//Purpose of Class: To display the search result
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

public class Result extends JFrame implements ActionListener
{
    //declaration of variables
    public final Color MAIN_COLOR = new Color(207, 172, 227);
    public final Color SIDE_COLOR = new Color(70, 67, 71);
    public final Color TABLE_COLOR = new Color(121, 217, 80);
    public final Font RESULT_FONT = new Font("Rockwell", Font.BOLD , 60);
    public final Font MENU_FONT = new Font("Rockwell", Font.BOLD , 30);
    public final Font TABLE_FONT = new Font("Rockwell", Font.PLAIN , 10);
    public final Font HEADER_FONT = new Font("Rockwell", Font.BOLD , 20);
    
    private JTable dishTable;
    private JScrollPane myPane;
    private JTableHeader tableTitle;
    
    private JLabel title;
    private JPanel buttonHolder;
    private JButton close;
    private JButton back;
    
    private JMenuBar helpMenuBar;
    private JMenu helpMenu;
    private JMenuItem helpItem;
    
    private Object[][] tableData;
    
    public Result(int[] position)
    {
        //construction of frame
        super("Result");
        this.setBounds(400,20,1000,1000);
        this.getContentPane().setBackground(MAIN_COLOR);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //columns for the JTable
        final String[] COLUMN_HEADER = {"ID", "Name", "Origin", "Type", "Property1",
                                            "Property2"};
        String[] columnName = {"ID", "Name", "Origin", "Type", "Property1",
                            "Property2"};
    
        //connection to the database
        DatabaseAccess accessor = new DatabaseAccess("RecipeDb");
        Connection myDbConn;
        myDbConn = accessor.getDbConn();
        
        Object[][] data = accessor.getData("FoodInfo", columnName);
        accessor.closeDbConn();
        
        //the 2 dementional array which the JTable will be using
        //size of tableData is the same as position array. Since the 
        //JTable can't display more than the amount of search results
        tableData = new Object[position.length][6];
 
        //to traverse through the array of id automatically
        int arrayPosition = 0;
           
        //locate the position of result row and dispalying
        for (int row = 0; row<data.length; row++)
        {
            
            //if the id in the database matches the id stored in position array
            if (position[arrayPosition] == Integer.parseInt(String.valueOf(data[row][0])))
            {

                for (int col = 0; col < data[row].length; col++)
                {

                    tableData[arrayPosition][col] = data[row][col];

                    //System.out.println("Values at arry["+row+"]["+col+"] is "+tableData[row][coltwo]);
                }

                //since the first result id is found, it will go to the next position
                if (arrayPosition != (position.length - 1))
                {
                    arrayPosition = arrayPosition + 1;
                }


            }
            
        }
        
        dishTable = new JTable(tableData,COLUMN_HEADER);
        dishTable.setRowHeight(20);
        dishTable.setFont(TABLE_FONT);
        dishTable.setGridColor(TABLE_COLOR);
        
        tableTitle = dishTable.getTableHeader();
        tableTitle.setFont(HEADER_FONT);
        tableTitle.setBackground(TABLE_COLOR);
        
        myPane = new JScrollPane();
        myPane.getViewport().add(dishTable);
        dishTable.setFillsViewportHeight(true);
        
        title = new JLabel("Here is the results from your search: ");
        title.setFont(MENU_FONT);
        title.setForeground(Color.BLACK);
        
        if (position[0] == -1)
        {
            title.setText("No results were found");
            title.setFont(RESULT_FONT);
            myPane.setVisible(false);
        }
        
        buttonHolder = new JPanel(new FlowLayout());
        buttonHolder.setBackground(SIDE_COLOR);
        
        back = new JButton("Return to Food List window");
        back.addActionListener(this);
        buttonHolder.add(back);
        
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
        this.add(myPane, BorderLayout.CENTER);
        this.add(buttonHolder, BorderLayout.SOUTH);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        
        if (command.equals("Return to Food List window"))
        {
            this.dispose();
            FoodDataList objFoodDataList = new FoodDataList();
        }
        if (command.equals("Close"))
        {
            this.dispose();
        }
    }
    
    public static void main(String[] args)
    {
        int[] test = {3};
        Result objResult = new Result(test);
    }
    
}
