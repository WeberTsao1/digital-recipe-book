//IB ComSci SL 2
//9-19-2019
//Purpose of class: For displaying all the information about a particular dish in detail

package tsaoia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DishInformation extends JFrame implements ActionListener
{
    //declaration of variables
    //colors
    public final Color MAIN_COLOR = new Color(222, 129, 150);
    public final Color SIDE_COLOR = new Color(23, 89, 36);
    public final Color EXTRA_COLOR = new Color(98, 181, 114);
    //fonts
    public final Font MENU_FONT = new Font("Rockwell", Font.BOLD , 35);
    public final Font MID_FONT = new Font("Rockwell", Font.PLAIN,30);
    public final Font SMALL_FONT = new Font("Rockwell", Font.PLAIN,15);
    
//    columns for the FoodInfo database table
    String[] columnName = {"ID", "Name", "Origin", "Type", "Property1", "Property2", "Description",
                            "Source", "Ingredient1", "Quantity1", "Ingredient2", "Quantity2",
                            "Ingredient3", "Quantity3", "Ingredient4", "Quantity4", "Ingredient5",
                            "Quantity5", "Ingredient6", "Quantity6", "Ingredient7", "Quantity7",
                            "Ingredient8", "Quantity8", "Ingredient9", "Quantity9", "Ingredient10",
                            "Quantity10", "Ingredient11", "Quantity11", "Ingredient12", "Quantity12",
                            "Ingredient13", "Quantity13"};
    
    //pictures
    public final java.net.URL DISHES = getClass().getResource("dishes.jpg");
    public final ImageIcon DISH_PIC = new ImageIcon(new ImageIcon(DISHES).getImage().getScaledInstance(700,400,Image.SCALE_DEFAULT));
    
    public final java.net.URL SOUP = getClass().getResource("soup.jpg");
    public final ImageIcon SOUP_PIC = new ImageIcon(new ImageIcon(SOUP).getImage().getScaledInstance(700,400,Image.SCALE_DEFAULT));
    
    public final java.net.URL DESSERT = getClass().getResource("desserts.jpg");
    public final ImageIcon DESSERT_PIC = new ImageIcon(new ImageIcon(DESSERT).getImage().getScaledInstance(700,400,Image.SCALE_DEFAULT));
    
    public final java.net.URL APPETIZER = getClass().getResource("appetizer.jpg");
    public final ImageIcon APPETIZER_PIC = new ImageIcon(new ImageIcon(APPETIZER).getImage().getScaledInstance(700,400,Image.SCALE_DEFAULT));
    
    //GUI components (mainly to display dish information)
    private JLabel dishPhotoHolder;
    private JLabel soupPhotoHolder;
    private JLabel appetizerPhotoHolder;
    private JLabel dessertPhotoHolder;
    private JLabel dishName;
    private JLabel origin;
    private JLabel type;
    private JLabel property1;
    private JLabel property2;
    private JLabel description;
    private JLabel source;
    private JLabel ingredient1;
    private JLabel quantity1;
    private JLabel ingredient2;
    private JLabel quantity2;
    private JLabel ingredient3;
    private JLabel quantity3;
    private JLabel ingredient4;
    private JLabel quantity4;
    private JLabel ingredient5;
    private JLabel quantity5;
    private JLabel ingredient6;
    private JLabel quantity6;
    private JLabel ingredient7;
    private JLabel quantity7;
    private JLabel ingredient8;
    private JLabel quantity8;
    private JLabel ingredient9;
    private JLabel quantity9;
    private JLabel ingredient10;
    private JLabel quantity10;
    private JLabel ingredient11;
    private JLabel quantity11;
    private JLabel ingredient12;
    private JLabel quantity12;
    private JLabel ingredient13;
    private JLabel quantity13;
    private Box organizedLeft;
    private JPanel organizedCenter;
    private JPanel organizedRight;
    
    private JButton portionScaler;
    private JTextField portionTf;
    private JButton exit;
    private JButton close;
    private JPanel dishNameSpot;
    private JPanel generalInfoSpot;
    private JPanel bulkPan;
    private JPanel portionScalerPan;
    private JPanel buttonHolder;
    private JPanel southPanel;
    private JPanel descriptionAndSource;
    
    private JMenuBar helpMenuBar;
    private JMenu helpMenu;
    private JMenuItem helpItem;
    
    //the varaibles which will be accepting the values from FoodRecipe database
    String nameV = "";
    String originV = "";
    String typeV = "";
    String property1V= "";
    String property2V = "";
    String descriptionV = "";
    String sourceV = "";
    String ingredient1V = "n/a";
    double quantity1V = 0;
    String ingredient2V = "n/a";
    double quantity2V = 0;
    String ingredient3V = "n/a";
    double quantity3V = 0;
    String ingredient4V = "n/a";
    double quantity4V = 0;
    String ingredient5V = "n/a";
    double quantity5V = 0;
    String ingredient6V = "n/a";
    double quantity6V = 0;
    String ingredient7V = "n/a";
    double quantity7V = 0;
    String ingredient8V = "n/a";
    double quantity8V = 0;
    String ingredient9V = "n/a";
    double quantity9V= 0;
    String ingredient10V = "n/a";
    double quantity10V = 0;
    String ingredient11V = "n/a";
    double quantity11V = 0;
    String ingredient12V = "n/a";
    double quantity12V = 0;
    String ingredient13V = "n/a";
    double quantity13V = 0;
    
    //used to compare to the string value from database 
    String dishComparer = "dish";
    String dessertComparer = "dessert";
    String soupComparer = "soup";
    String appetizerComparer = "appetizer";
    
    private int id;
    
    public void setId(int id)
    {
        this.id = id;
    }
    public int getId()
    {
        return this.id;
    }
    
    //accepts the id number of dish. So the class can access the dish with
    //the corresponding id number thus displaying it
    public DishInformation(int id)
    {
        super("Dish Information");
        this.setBounds(50,75,1800,900);
        this.getContentPane().setBackground(MAIN_COLOR);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //Connect with the database then put the data into an two dimensional array 'data'
        DatabaseAccess accessor = new DatabaseAccess("RecipeDb");
        Connection myDbConn;

        myDbConn = accessor.getDbConn();

        Object[][] data = accessor.getData("FoodInfo", columnName);
        accessor.closeDbConn();
        
        //for checking if the information is correct
//        for (int i = 0; i < data[id].length; i++)
//        {
//           
//            System.out.println(data[id][i]);
//        
//        }
        
//to test whether the information is retrieved correctly from database
//        String testDbValue = "";
//        testDbValue = String.valueOf(data[id][1]);
//        System.out.println("This is the value in database passed into string: " + testDbValue);

        setId(id);

//      Converting values from two demensional array into string and double
//      so the GUI components would be able to display the data
        nameV = String.valueOf(data[getId()][1]);
        originV = String.valueOf(data[getId()][2]);
        typeV = String.valueOf(data[getId()][3]);
        property1V = String.valueOf(data[getId()][4]);
        property2V = String.valueOf(data[getId()][5]);
        descriptionV = String.valueOf(data[getId()][6]);
        sourceV = String.valueOf(data[getId()][7]);
        ingredient1V = String.valueOf(data[getId()][8]);
        quantity1V = Double.parseDouble(String.valueOf(data[getId()][9]));
        ingredient2V = String.valueOf(data[getId()][10]);
        quantity2V = Double.parseDouble(String.valueOf(data[getId()][11]));
        ingredient3V = String.valueOf(data[getId()][12]);
        quantity3V = Double.parseDouble(String.valueOf(data[getId()][13]));
        ingredient4V = String.valueOf(data[getId()][14]);
        quantity4V = Double.parseDouble(String.valueOf(data[getId()][15]));
        ingredient5V = String.valueOf(data[getId()][16]);
        quantity5V = Double.parseDouble(String.valueOf(data[getId()][17]));
        ingredient6V = String.valueOf(data[getId()][18]);
        quantity6V = Double.parseDouble(String.valueOf(data[getId()][19]));
        ingredient7V = String.valueOf(data[getId()][20]);
        quantity7V = Double.parseDouble(String.valueOf(data[getId()][21]));
        ingredient8V = String.valueOf(data[getId()][22]);
        quantity8V = Double.parseDouble(String.valueOf(data[getId()][23]));
        ingredient9V = String.valueOf(data[getId()][24]);
        quantity9V = Double.parseDouble(String.valueOf(data[getId()][25]));
        ingredient10V = String.valueOf(data[getId()][26]);
        quantity10V = Double.parseDouble(String.valueOf(data[getId()][27]));
        ingredient11V = String.valueOf(data[getId()][28]);
        quantity11V = Double.parseDouble(String.valueOf(data[getId()][29]));
        ingredient12V = String.valueOf(data[getId()][30]);
        quantity12V = Double.parseDouble(String.valueOf(data[getId()][31]));
        ingredient13V = String.valueOf(data[getId()][32]);
        quantity13V = Double.parseDouble(String.valueOf(data[getId()][33]));
        
        //all photos are invisible until the frame figure out the type of dish it is displaying
        dishPhotoHolder = new JLabel(DISH_PIC);
        dishPhotoHolder.setVisible(false);
        
        soupPhotoHolder = new JLabel(SOUP_PIC);
        soupPhotoHolder.setVisible(false);
        
        appetizerPhotoHolder = new JLabel(APPETIZER_PIC);
        appetizerPhotoHolder.setVisible(false);
        
        dessertPhotoHolder = new JLabel(DESSERT_PIC);
        dessertPhotoHolder.setVisible(false);
        
        //different photos appear based on the type of food it is 
        if (typeV.compareTo(dishComparer) == 0)
        {
            dishPhotoHolder.setVisible(true);
            revalidate();
            repaint();

        }
        if (typeV.compareTo(soupComparer) == 0)
        {
            soupPhotoHolder.setVisible(true);
            revalidate();
            repaint();
        }
        if (typeV.compareTo(appetizerComparer) == 0)
        {
            appetizerPhotoHolder.setVisible(true);
            revalidate();
            repaint();

        }
        if (typeV.compareTo(dessertComparer) == 0)
        {
            dessertPhotoHolder.setVisible(true);
            revalidate();
            repaint();
        }       
        
        portionScaler = new JButton("Portion Scaler");
        portionScaler.addActionListener(this);
        
        portionTf = new JTextField(5);
        
        exit = new JButton("Exit");
        exit.addActionListener(this);
        
        close = new JButton("Close");
        close.addActionListener(this);
        
        buttonHolder = new JPanel(new FlowLayout());
        buttonHolder.setBackground(SIDE_COLOR);
        buttonHolder.add(close);
        buttonHolder.add(exit);
        
        portionScalerPan = new JPanel(new FlowLayout());
        portionScalerPan.setBackground(EXTRA_COLOR);
        portionScalerPan.add(portionScaler);
        portionScalerPan.add(portionTf);
        
        southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(MAIN_COLOR);
        southPanel.add(portionScalerPan, BorderLayout.CENTER);
        southPanel.add(buttonHolder, BorderLayout.SOUTH);
        
        
        //Dish information populating into GUI components starts here
        dishName = new JLabel(nameV);
        dishName.setFont(MENU_FONT);
        
        origin = new JLabel(originV + " that is ");
        origin.setFont(MID_FONT);
        
        type = new JLabel("A " + typeV + " from ");
        type.setFont(MID_FONT);
        
        property1 = new JLabel(property1V + " and ");
        property1.setFont(MID_FONT);
        
        property2 = new JLabel(property2V);
        property2.setFont(MID_FONT);
        
        description = new JLabel("<html>" + "Description: " + 
                descriptionV + "</html>");
        description.setFont(MID_FONT);
        
        source = new JLabel("<html>" + "Source: " + sourceV + "</html>");
        source.setFont(SMALL_FONT);
        
        ingredient1 = new JLabel("              " + ingredient1V);
        ingredient1.setFont(SMALL_FONT);
        
        quantity1 = new JLabel("<html>" + "<li>" + quantity1V + " of" + "</li>" + "</html>");
        quantity1.setFont(SMALL_FONT);
        
        ingredient2 = new JLabel("              " + ingredient2V);
        ingredient2.setFont(SMALL_FONT);
        
        quantity2 = new JLabel("<html>" + "<li>" + quantity2V + " of" + "</li>" + "</html>");
        quantity2.setFont(SMALL_FONT);
        
        ingredient3 = new JLabel("              " + ingredient3V);
        ingredient3.setFont(SMALL_FONT);
        
        quantity3 = new JLabel("<html>" + "<li>" + quantity3V + " of" + "</li>" + "</html>");
        quantity3.setFont(SMALL_FONT);
        
        ingredient4 = new JLabel("              " + ingredient4V);
        ingredient4.setFont(SMALL_FONT);
        
        quantity4 = new JLabel("<html>" + "<li>" + quantity4V + " of" + "</li>" + "</html>");
        quantity4.setFont(SMALL_FONT);
        
        ingredient5 = new JLabel("              " + ingredient5V);
        ingredient5.setFont(SMALL_FONT);
        
        quantity5 = new JLabel("<html>" + "<li>" + quantity5V + " of" + "</li>" + "</html>");
        quantity5.setFont(SMALL_FONT);
        
        ingredient6 = new JLabel("              " + ingredient6V);
        ingredient6.setFont(SMALL_FONT);
        
        quantity6 = new JLabel("<html>" + "<li>" + quantity6V + " of" + "</li>" + "</html>");
        quantity6.setFont(SMALL_FONT);
        
        ingredient7 = new JLabel("              " + ingredient7V);
        ingredient7.setFont(SMALL_FONT);
        
        quantity7 = new JLabel("<html>" + "<li>" + quantity7V + " of" + "</li>" + "</html>");
        quantity7.setFont(SMALL_FONT);
        
        ingredient8 = new JLabel("              " + ingredient8V);
        ingredient8.setFont(SMALL_FONT);
        
        quantity8 = new JLabel("<html>" + "<li>" + quantity8V + " of" + "</li>" + "</html>");
        quantity8.setFont(SMALL_FONT);
        
        ingredient9 = new JLabel("              " + ingredient9V);
        ingredient9.setFont(SMALL_FONT);
        
        quantity9 = new JLabel("<html>" + "<li>" + quantity9V + " of" + "</li>" + "</html>");
        quantity9.setFont(SMALL_FONT);
        
        ingredient10 = new JLabel("              " + ingredient10V);
        ingredient10.setFont(SMALL_FONT);
        
        quantity10 = new JLabel("<html>" + "<li>" + quantity10V + " of" + "</li>" + "</html>");
        quantity10.setFont(SMALL_FONT);
        
        ingredient11 = new JLabel("              " + ingredient11V);
        ingredient11.setFont(SMALL_FONT);
        
        quantity11 = new JLabel("<html>" + "<li>" + quantity11V + " of" + "</li>" + "</html>");
        quantity11.setFont(SMALL_FONT);
        
        ingredient12 = new JLabel("              " + ingredient12V);
        ingredient12.setFont(SMALL_FONT);
        
        quantity12 = new JLabel("<html>" + "<li>" + quantity12V + " of" + "</li>" + "</html>");
        quantity12.setFont(SMALL_FONT);
        
        ingredient13 = new JLabel("              " + ingredient13V);
        ingredient13.setFont(SMALL_FONT);
        
        quantity13 = new JLabel("<html>" + "<li>" + quantity13V + " of" + "</li>" + "</html>");
        quantity13.setFont(SMALL_FONT);
        
        //if columns from one point on doesnt have anymeaningful info, the JLabels wont appear

        if (quantity5V ==0)
        {
            ingredient5.setVisible(false);
            quantity5.setVisible(false);
            ingredient6.setVisible(false);
            quantity6.setVisible(false);
            ingredient7.setVisible(false);
            quantity7.setVisible(false);
            ingredient8.setVisible(false);
            quantity8.setVisible(false);
            ingredient9.setVisible(false);
            quantity9.setVisible(false);
            ingredient10.setVisible(false);
            quantity10.setVisible(false);
            ingredient11.setVisible(false);
            quantity11.setVisible(false);
            ingredient12.setVisible(false);
            quantity12.setVisible(false);
            ingredient13.setVisible(false);
            quantity13.setVisible(false);
            
        }
        if (quantity6V ==0)
        {
            ingredient6.setVisible(false);
            quantity6.setVisible(false);
            ingredient7.setVisible(false);
            quantity7.setVisible(false);
            ingredient8.setVisible(false);
            quantity8.setVisible(false);
            ingredient9.setVisible(false);
            quantity9.setVisible(false);
            ingredient10.setVisible(false);
            quantity10.setVisible(false);
            ingredient11.setVisible(false);
            quantity11.setVisible(false);
            ingredient12.setVisible(false);
            quantity12.setVisible(false);
            ingredient13.setVisible(false);
            quantity13.setVisible(false);
            
        }
        if (quantity7V ==0)
        {
            ingredient7.setVisible(false);
            quantity7.setVisible(false);
            ingredient8.setVisible(false);
            quantity8.setVisible(false);
            ingredient9.setVisible(false);
            quantity9.setVisible(false);
            ingredient10.setVisible(false);
            quantity10.setVisible(false);
            ingredient11.setVisible(false);
            quantity11.setVisible(false);
            ingredient12.setVisible(false);
            quantity12.setVisible(false);
            ingredient13.setVisible(false);
            quantity13.setVisible(false);
            
        }
        if (quantity8V ==0)
        {
            ingredient8.setVisible(false);
            quantity8.setVisible(false);
            ingredient9.setVisible(false);
            quantity9.setVisible(false);
            ingredient10.setVisible(false);
            quantity10.setVisible(false);
            ingredient11.setVisible(false);
            quantity11.setVisible(false);
            ingredient12.setVisible(false);
            quantity12.setVisible(false);
            ingredient13.setVisible(false);
            quantity13.setVisible(false);
            
        }
        else if (quantity9V ==0)
        {
            ingredient9.setVisible(false);
            quantity9.setVisible(false);
            ingredient10.setVisible(false);
            quantity10.setVisible(false);
            ingredient11.setVisible(false);
            quantity11.setVisible(false);
            ingredient12.setVisible(false);
            quantity12.setVisible(false);
            ingredient13.setVisible(false);
            quantity13.setVisible(false);
            
        }
        else if (quantity10V ==0)
        {
            ingredient10.setVisible(false);
            quantity10.setVisible(false);
            ingredient11.setVisible(false);
            quantity11.setVisible(false);
            ingredient12.setVisible(false);
            quantity12.setVisible(false);
            ingredient13.setVisible(false);
            quantity13.setVisible(false);
            
        }
        else if (quantity11V ==0)
        {
            ingredient11.setVisible(false);
            quantity11.setVisible(false);
            ingredient12.setVisible(false);
            quantity12.setVisible(false);
            ingredient13.setVisible(false);
            quantity13.setVisible(false);
            
        }
        else if (quantity12V ==0)
        {
            ingredient12.setVisible(false);
            quantity12.setVisible(false);
            ingredient13.setVisible(false);
            quantity13.setVisible(false);   
        }
        else if (quantity13V ==0)
        {
            ingredient13.setVisible(false);
            quantity13.setVisible(false);
            
        }
        //panel contructors
        descriptionAndSource = new JPanel(new BorderLayout());
        descriptionAndSource.setBackground(MAIN_COLOR);
        descriptionAndSource.add(description, BorderLayout.NORTH);
        descriptionAndSource.add(source, BorderLayout.SOUTH);
        southPanel.add(descriptionAndSource, BorderLayout.NORTH);
        
        generalInfoSpot = new JPanel(new FlowLayout());
        generalInfoSpot.setBackground(MAIN_COLOR);
        generalInfoSpot.add(type);
        generalInfoSpot.add(origin);
        generalInfoSpot.add(property1);
        generalInfoSpot.add(property2);
        
        dishNameSpot = new JPanel(new BorderLayout());
        dishNameSpot.setBackground(MAIN_COLOR);
        dishNameSpot.add(dishName, BorderLayout.NORTH);
        dishNameSpot.add(generalInfoSpot, BorderLayout.CENTER);

        Box organizedLeft = Box.createVerticalBox();
        //organizedLeft = new JPanel();
        organizedLeft.setBackground(MAIN_COLOR); 
        organizedLeft.add(quantity1);
        organizedLeft.add(ingredient1);
        organizedLeft.add(Box.createVerticalStrut(20));
        organizedLeft.add(quantity2);
        organizedLeft.add(ingredient2);
        organizedLeft.add(Box.createVerticalStrut(20));
        organizedLeft.add(quantity3);
        organizedLeft.add(ingredient3);
        organizedLeft.add(Box.createVerticalStrut(20));
        organizedLeft.add(quantity4);
        organizedLeft.add(ingredient4);
        organizedLeft.add(Box.createVerticalStrut(20));
        organizedLeft.add(quantity5);
        organizedLeft.add(ingredient5);
        organizedLeft.add(Box.createVerticalStrut(20));
        organizedLeft.add(quantity6);
        organizedLeft.add(ingredient6);
        organizedLeft.add(Box.createVerticalStrut(20));
        organizedLeft.add(quantity7);
        organizedLeft.add(ingredient7);
        
        organizedCenter = new JPanel(new FlowLayout());
        organizedCenter.setBackground(MAIN_COLOR);
        
        organizedCenter.add(dishPhotoHolder);

        organizedCenter.add(soupPhotoHolder);

        organizedCenter.add(appetizerPhotoHolder);
        
        organizedCenter.add(dessertPhotoHolder);        
    
        Box organizedRight = Box.createVerticalBox();
        //organizedRight = new JPanel(new FlowLayout());
        organizedRight.setBackground(MAIN_COLOR);
        organizedRight.add(quantity9);
        organizedRight.add(ingredient9);
        organizedRight.add(Box.createVerticalStrut(25));
        organizedRight.add(quantity10);
        organizedRight.add(ingredient10);
        organizedRight.add(Box.createVerticalStrut(25));
        organizedRight.add(quantity11);
        organizedRight.add(ingredient11);
        organizedRight.add(Box.createVerticalStrut(25));
        organizedRight.add(quantity12);
        organizedRight.add(ingredient12);
        organizedRight.add(Box.createVerticalStrut(25));
        organizedRight.add(quantity13);
        organizedRight.add(ingredient13);
        
        bulkPan = new JPanel(new BorderLayout());
        bulkPan.setBackground(MAIN_COLOR);
        bulkPan.add(organizedLeft, BorderLayout.WEST);
        bulkPan.add(organizedCenter, BorderLayout.CENTER);
        bulkPan.add(organizedRight, BorderLayout.EAST);
        
        //dishNameSpot.add(bulkPan, BorderLayout.SOUTH);
        
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
        
        this.add(dishNameSpot, BorderLayout.NORTH);
        this.add(bulkPan, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        //will hold the value of which the user wants to alter the portion size by
        double portionAlter;
        
        //Connect with the database then put the data into an two dimensional array 'data'
        DatabaseAccess accessor = new DatabaseAccess("RecipeDb");
        Connection myDbConn;

        myDbConn = accessor.getDbConn();

        Object[][] data = accessor.getData("FoodInfo", columnName);
        accessor.closeDbConn();
        //portion scaler is for adjusting the food portion size. 
        //user can use it by entering a number in percent to indicate whether
        //he or she wants to up or down scale the food portion
        if (command.equals("Portion Scaler"))
        {
            try
            {
                //reading the user input
                portionAlter = Double.parseDouble(portionTf.getText());
                //converting the user input number in percent number into decimals
                portionAlter = 1 + (portionAlter / 100);
                //getting the values from database
                quantity1V = Double.parseDouble(String.valueOf(data[getId()][9]));
                quantity2V = Double.parseDouble(String.valueOf(data[getId()][11]));
                quantity3V = Double.parseDouble(String.valueOf(data[getId()][13]));
                quantity4V = Double.parseDouble(String.valueOf(data[getId()][15]));
                quantity5V = Double.parseDouble(String.valueOf(data[getId()][17]));;
                quantity6V = Double.parseDouble(String.valueOf(data[getId()][19]));
                quantity7V = Double.parseDouble(String.valueOf(data[getId()][21]));
                quantity8V = Double.parseDouble(String.valueOf(data[getId()][23]));
                quantity9V = Double.parseDouble(String.valueOf(data[getId()][25]));
                quantity10V = Double.parseDouble(String.valueOf(data[getId()][27]));
                quantity11V = Double.parseDouble(String.valueOf(data[getId()][29]));
                quantity12V = Double.parseDouble(String.valueOf(data[getId()][31]));
                quantity13V = Double.parseDouble(String.valueOf(data[getId()][33]));
                //multiplying the original value by the user intended scale
                quantity1V = quantity1V * portionAlter;
                quantity2V = quantity2V * portionAlter;
                quantity3V = quantity3V * portionAlter;
                quantity4V = quantity4V * portionAlter;
                quantity5V = quantity5V * portionAlter;
                quantity6V = quantity6V * portionAlter;
                quantity7V = quantity7V * portionAlter;
                quantity8V = quantity8V * portionAlter;
                quantity9V = quantity9V * portionAlter;
                quantity10V = quantity10V * portionAlter;
                quantity11V = quantity11V * portionAlter;
                quantity12V = quantity12V * portionAlter;
                quantity13V = quantity13V * portionAlter;
                //updating the JLabels with new numbers
                quantity1.setText("<html>" + "<li>" + quantity1V + " of" + "</li>" + "</html>");
                quantity2.setText("<html>" + "<li>" + quantity2V + " of" + "</li>" + "</html>");
                quantity3.setText("<html>" + "<li>" + quantity3V + " of" + "</li>" + "</html>");
                quantity4.setText("<html>" + "<li>" + quantity4V + " of" + "</li>" + "</html>");
                quantity5.setText("<html>" + "<li>" + quantity5V + " of" + "</li>" + "</html>");
                quantity6.setText("<html>" + "<li>" + quantity6V + " of" + "</li>" + "</html>");
                quantity7.setText("<html>" + "<li>" + quantity7V + " of" + "</li>" + "</html>");
                quantity8.setText("<html>" + "<li>" + quantity8V + " of" + "</li>" + "</html>");
                quantity9.setText("<html>" + "<li>" + quantity9V + " of" + "</li>" + "</html>");
                quantity10.setText("<html>" + "<li>" + quantity10V + " of" + "</li>" + "</html>");
                quantity11.setText("<html>" + "<li>" + quantity11V + " of" + "</li>" + "</html>");
                quantity12.setText("<html>" + "<li>" + quantity12V + " of" + "</li>" + "</html>");
                quantity13.setText("<html>" + "<li>" + quantity13V + " of" + "</li>" + "</html>");
                
                revalidate();
                repaint();
                
            }
            catch (Exception p)
            {
                Error objError = new Error(2);
            }
        }
        if (command.equals("Close"))
        {
            this.dispose();
        }
        if (command.equals("Exit"))
        {
            System.exit(0);
        }
        if (command.equals("Help"))
        {
            Help objHelp = new Help();
        }
    }
    
    public static void main(String[] args)
    {
        DishInformation objDishInfo = new DishInformation(0);
    }
    
}
