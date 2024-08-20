//IB ComSci SL 2
//9-19-2019
//Purpose of class: To allow user to input recipe into recipe

package tsaoia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DishAdder extends JFrame implements ActionListener
{
    //declaration of constants    
    public final Color MAIN_COLOR = new Color(91, 99, 87);
    public final Color SIDE_COLOR = new Color(40, 72, 115);
    public final Font SMALL_FONT = new Font("Rockwell", Font.PLAIN, 30);
    public final Font TINY_FONT = new Font("Rockwell", Font.PLAIN, 15);
    //declarations for GUI
    private JLabel prompter;
    private JTextField nameTf;
    private JComboBox<String> originDropMenu;
    private JComboBox<String> typeDropMenu;
    private JComboBox<String> propertyDropMenu1;
    private JComboBox<String> propertyDropMenu2;
    private JTextField descriptionTf;
    private JTextField sourceTf;
    private JTextField ingredientTf;
    private JTextField quantityTf;

    private JButton noMore;
    private JButton finish;
    private JButton input;
    private JButton next;
    private JButton back;
    private JPanel buttonHolder;
    private JPanel promptBox;
    private JPanel traverseButtonHolder;
    
    private JMenuBar helpMenuBar;
    private JMenu helpMenu;
    private JMenuItem helpItem;
    
    //counter will increase by one when one input is inserted
    int counter = 1;
    //to make sure application doesnt ask for next input before the current input is entered
    int flag = 0;
        
    // for populating the database 
    String[] columnName = {"ID", "Name", "Origin", "Type", "Property1", "Property2", "Description",
                        "Source", "Ingredient1", "Quantity1", "Ingredient2", "Quantity2",
                        "Ingredient3", "Quantity3", "Ingredient4", "Quantity4", "Ingredient5",
                        "Quantity5", "Ingredient6", "Quantity6", "Ingredient7", "Quantity7",
                        "Ingredient8", "Quantity8", "Ingredient9", "Quantity9", "Ingredient10",
                        "Quantity10", "Ingredient11", "Quantity11", "Ingredient12", "Quantity12",
                        "Ingredient13", "Quantity13"};
    DatabaseAccess accessor = new DatabaseAccess("RecipeDb");
    Connection myDbConn;

    //for inserting into FoodInfo Table
    String insertQuery = "INSERT INTO FoodInfo VALUES " +
                            "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                            + "?,?,?,?)";

    Object [][] data = accessor.getData("FoodInfo", columnName);
    
    //the variables which the input of user will be stored. Eventually to be passed into database
    int idNumber = 0;
    String name = "";
    String origin = "";
    String type = "";
    String property1= "";
    String property2 = "";
    String description = "";
    String source = "";
    String ingredient1 = "n/a";
    double quantity1 = 0;
    String ingredient2 = "n/a";
    double quantity2 = 0;
    String ingredient3 = "n/a";
    double quantity3 = 0;
    String ingredient4 = "n/a";
    double quantity4 = 0;
    String ingredient5 = "n/a";
    double quantity5 = 0;
    String ingredient6 = "n/a";
    double quantity6 = 0;
    String ingredient7 = "n/a";
    double quantity7 = 0;
    String ingredient8 = "n/a";
    double quantity8 = 0;
    String ingredient9 = "n/a";
    double quantity9= 0;
    String ingredient10 = "n/a";
    double quantity10 = 0;
    String ingredient11 = "n/a";
    double quantity11 = 0;
    String ingredient12 = "n/a";
    double quantity12 = 0;
    String ingredient13 = "n/a";
    double quantity13 = 0;
    
    public DishAdder()
    {
        //construction of frame
        super("Dish Adder");
        this.setBounds(200,150,1500,800);
        this.getContentPane().setBackground(MAIN_COLOR);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //getting the database connection
        myDbConn = accessor.getDbConn();
        
        prompter = new JLabel("Enter name of dish: ");
        prompter.setFont(SMALL_FONT);
        
        nameTf = new JTextField(60);
        
        //construction of JComboBoxes. setting the default selection and adding action listener
        String[] origins = {"","Greece", "France", "Italy", "Germany", "England",
            "Spain", "United States", "Canada", "Mexico", "Taiwan", "China", "Russia",
            "Brazil", "Japan", "Korea", "Thailand", "Turkey"};
        originDropMenu = new JComboBox<>(origins);
        originDropMenu.setFont(SMALL_FONT);
        originDropMenu.setSelectedItem(origins[0]);
        originDropMenu.addActionListener(this);
        
        String[] types = {"","dessert", "dish", "soup", "appetizer"};
        typeDropMenu = new JComboBox<>(types);
        typeDropMenu.setFont(SMALL_FONT);
        typeDropMenu.setSelectedItem(types[0]);
        typeDropMenu.addActionListener(this);
        
        String[] properties = {"", "sweet", "creamy", "salty", "spicy",
            "greasy", "crumbly", "crunchy", "crispy"};
        propertyDropMenu1 = new JComboBox<>(properties);
        propertyDropMenu1.setFont(SMALL_FONT);
        propertyDropMenu1.setSelectedItem(properties[0]);
        propertyDropMenu1.addActionListener(this);
        
        propertyDropMenu2 = new JComboBox<>(properties);
        propertyDropMenu2.setFont(SMALL_FONT);
        propertyDropMenu2.setSelectedItem(properties[0]);
        propertyDropMenu2.addActionListener(this);
        
        descriptionTf = new JTextField(100);
        
        sourceTf = new JTextField(100);
         
        ingredientTf = new JTextField(60);
        quantityTf = new JTextField(60);        
        
        //construction of promptbox. The Panel contains components for inputting
        //data into the database
        promptBox = new JPanel(new FlowLayout());
        promptBox.setBackground(MAIN_COLOR);
        promptBox.add(prompter);
        promptBox.add(nameTf);
        promptBox.add(originDropMenu);
        promptBox.add(typeDropMenu);
        promptBox.add(propertyDropMenu1);
        promptBox.add(propertyDropMenu2);
        promptBox.add(descriptionTf);
        promptBox.add(sourceTf);
        promptBox.add(ingredientTf);
        promptBox.add(quantityTf);
        
        //for keeping most gui components invisible and only visible when the
        //application needs it for input. Or to inform user about what to input
        if (counter != 1)
            nameTf.setVisible(false);
        originDropMenu.setVisible(false);
        typeDropMenu.setVisible(false);
        propertyDropMenu1.setVisible(false);
        propertyDropMenu2.setVisible(false);
        descriptionTf.setVisible(false);
        sourceTf.setVisible(false);
        ingredientTf.setVisible(false);
        quantityTf.setVisible(false);
        
        //finish button only appear clickable when every other fields are filled out
        finish = new JButton("Finish");
        finish.addActionListener(this);
        finish.setVisible(false);
        
        //next button only appear after user press input and the input is the 
        //expected type by the database
        next = new JButton("Next");
        next.addActionListener(this);
        next.setVisible(false);
        
        noMore = new JButton("No More Ingredients");
        noMore.addActionListener(this);
        noMore.setVisible(false);
        
        input = new JButton("Input");
        input.addActionListener(this);
        
        back = new JButton("Go Back");
        back.addActionListener(this);
        
        //the panel holds all the buttons for the user to input values
        traverseButtonHolder = new JPanel(new FlowLayout());
        traverseButtonHolder.setBackground(MAIN_COLOR);
        traverseButtonHolder.add(finish);
        traverseButtonHolder.add(input);
        traverseButtonHolder.add(next);
        traverseButtonHolder.add(noMore);
        
        buttonHolder = new JPanel(new FlowLayout());
        buttonHolder.setBackground(SIDE_COLOR);
        buttonHolder.add(back);
        
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
        this.add(promptBox, BorderLayout.NORTH);
        this.add(traverseButtonHolder, BorderLayout.CENTER);
        this.add(buttonHolder, BorderLayout.SOUTH);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
            
        //to make the name text field only appear on first stage of input prompt
        final int ALWAYS = 0;
        if (flag == 0)
        {
            if (counter != 1)
            {
                nameTf.setVisible(false);
            }
            
        }
        
        String command = e.getActionCommand();
        
        String originListener = (String) originDropMenu.getSelectedItem();
        String typeListener = (String) typeDropMenu.getSelectedItem();
        String propertyListener1 = (String) propertyDropMenu1.getSelectedItem();
        String propertyListener2 = (String) propertyDropMenu2.getSelectedItem();
        
        //for passing user input into the application to be eventually passed into the database
        if (command.equals("Input"))
        {
            //to check if the algorithm is funtioning
            //System.out.println("INPUT is pressed. COUNTER = " + counter);
            if(counter == 1)
            {
  
                try
                {
                    name = nameTf.getText();
                    //checking input
                    //System.out.println(name);
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(1);
                }
                
            }
            if (counter == 2)
            {
                try
                {
                    //load comboBox selection
                    switch (originListener) 
                    {
                        case "":
                            flag = 1;
                            break;
                        case "Greece":
                            origin = "Greece";
                            flag = 0;
                            break;
                        case "France":
                            origin = "France";
                            flag = 0;
                            break;
                        case "Italy":
                            origin = "Italy";
                            flag = 0;
                            break;
                        case "Germany":
                            origin = "Germany";
                            flag = 0;
                            break;
                        case "England":
                            origin = "England";
                            flag = 0;
                            break;
                        case "Spain":
                            origin = "Spain";
                            flag = 0;
                            break;
                        case "United States":
                            origin = "United States";
                            flag = 0;
                            break;
                        case "Canada":
                            origin = "Canada";
                            flag = 0;
                            break;
                        case "Mexico":
                            origin = "Mexico";
                            flag = 0;
                            break;
                        case "Taiwan":
                            origin = "Taiwan";
                            flag = 0;
                            break;
                        case "China":
                            origin = "China";
                            flag = 0;
                            break;
                        case "Russia":
                            origin = "Russia";
                            flag = 0;
                            break;
                        case "Brazil":
                            origin = "Brazil";
                            flag = 0;
                            break;
                        case "Japan":
                            origin = "Japan";
                            flag = 0;
                            break;
                        case "Korea":
                            origin = "Korea";
                            flag = 0;
                            break;
                        case "Thailand":
                            origin = "Thailand";
                            flag = 0;
                            break;
                        case "Turkey":
                            origin = "Turkey";
                            flag = 0;
                            break;

                    }
                    if (flag == 0)
                    {
                        next.setVisible(true);
                        input.setEnabled(false);
                    }
                } 
                catch (Exception p)
                {
                    Error objError = new Error(3);
                    flag = 1;
                }
                //to check whether the Jcombobox is funtioning
                //System.out.println(origin);

            }
            if (counter ==3)
            {
                try
                {
                    //load comboBox value from type
                    switch (typeListener)
                    {
                        case "":
                            flag = 1;
                            break;
                        case "dessert":
                            type = "dessert";
                            flag = 0;
                            break;
                        case "dish":
                            type = "dish";
                            flag = 0;
                            break;
                        case "soup":
                            type = "soup";
                            flag = 0;
                            break;
                        case "appetizer":
                            type = "appetizer";
                            flag = 0;
                            break;
                    }
                    if (flag == 0)
                    {
                        next.setVisible(true);
                        input.setEnabled(false);
                    }
                    
                }
                catch (Exception p)
                {
                    Error objError = new Error(3);
                }
            }
            if (counter ==4)
            {
                try
                {
                    //load property1
                    switch (propertyListener1)
                    {
                        case "":
                            flag = 1;
                            break;
                        case "sweet":
                            property1 = "sweet";
                            flag = 0;
                            break;
                        case "creamy":
                            property1 = "creamy";
                            flag = 0;
                            break;
                        case "salty":
                            property1 = "salty";
                            flag = 0;
                            break;
                        case "spicy":
                            property1 = "spicy";
                            flag = 0;
                            break;
                        case "greasy":
                            property1 = "greasy";
                            flag = 0;
                            break;
                        case "crumbly":
                            property1 = "crumbly";
                            flag = 0;
                            break;
                        case "crunchy":
                            property1 = "crunchy";
                            flag = 0;
                            break;
                        case "crispy":
                            property1 = "crispy";
                            flag = 0;
                            break;
                    }
                    if (flag == 0)
                    {
                        next.setVisible(true);
                        input.setEnabled(false);
                    }
                }
                catch (Exception p)
                {
                    Error objError = new Error(3);
                }
            }
            if (counter ==5)
            {
                try
                    {
                        
                        switch (propertyListener2)
                        {
                            case "":
                                flag = 1;
                                break;
                            case "sweet":
                                property2 = "sweet";
                                flag = 0;
                                break;
                            case "creamy":
                                property2 = "creamy";
                                flag = 0;
                                break;
                            case "salty":
                                property2 = "salty";
                                flag = 0;
                                break;
                            case "spicy":
                                property2 = "spicy";
                                flag = 0;
                                break;
                            case "greasy":
                                property2 = "greasy";
                                flag = 0;
                                break;
                            case "crumbly":
                                property2 = "crumbly";
                                flag = 0;
                                break;
                            case "crunchy":
                                property2 = "crunchy";
                                flag = 0;
                                break;
                            case "crispy":
                                property2 = "crispy";
                                flag = 0;
                                break;
                        }
                        if (flag == 0)
                        {
                            next.setVisible(true);
                            input.setEnabled(false);
                        }
                        
                    }
                    catch (Exception p)
                    {
                        Error objError = new Error(3);
                    }
            }
            if (counter ==6)
            {
                try
                {   
                    
                    description = descriptionTf.getText();
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                    
                }
                catch (Exception p)
                {
                    Error objError = new Error(1);
                }
            }
            if (counter ==7)
            {
                try
                {
                    source = sourceTf.getText();
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(1);
                }
            }
            if (counter ==8)
            {
                try
                {
                    ingredient1 = ingredientTf.getText();
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(1);
                }
            }
            if (counter ==9)
            {
                try
                {
                    quantity1 = Double.parseDouble(quantityTf.getText());
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(2);
                }
            }
            if (counter ==10)
            {
                try
                {
                    ingredient2 = ingredientTf.getText();
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                    
                }
                catch (Exception p)
                {
                    Error objError = new Error(1);
                }
            }
            if (counter ==11)
            {
                try
                {
                    quantity2 = Double.parseDouble(quantityTf.getText());
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(2);
                }
            }
            if (counter ==12)
            {
                try
                {
                    ingredient3 = ingredientTf.getText();
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(1);
                }
            }
            if (counter ==13)
            {
                try
                {
                    quantity3 = Double.parseDouble(quantityTf.getText());
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(2);
                }
            }
            if (counter ==14)
            {
                try
                {
                    ingredient4 = ingredientTf.getText();
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(1);
                }
            }
            if (counter ==15)
            {
                try
                {
                    quantity4 = Double.parseDouble(quantityTf.getText());
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(2);
                }
            }
            if (counter ==16)
            {
                try
                {
                    ingredient5 = ingredientTf.getText();
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                    noMore.setVisible(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(1);
                }
            }
            if (counter ==17)
            {
                try
                {
                    quantity5 = Double.parseDouble(quantityTf.getText());
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(2);
                }
            }
            if (counter ==18)
            {
                try
                {
                    ingredient6 = ingredientTf.getText();
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                    noMore.setVisible(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(1);
                }
            }
            if (counter ==19)
            {
                try
                {
                    quantity6 = Double.parseDouble(quantityTf.getText());
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(2);
                }
            }
            if (counter ==20)
            {
                try
                {
                    ingredient7 = ingredientTf.getText();
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                    noMore.setVisible(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(1);
                }
            }
            if (counter ==21)
            {
                try
                {
                    quantity7 = Double.parseDouble(quantityTf.getText());
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(2);
                }
            }
            if (counter ==22)
            {
                try
                {
                    ingredient8 = ingredientTf.getText();
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                    noMore.setVisible(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(1);
                }
            }
            if (counter ==23)
            {
                try
                {
                    quantity8 = Double.parseDouble(quantityTf.getText());
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(2);
                }
            }
            if (counter ==24)
            {
                try
                {
                    ingredient9 = ingredientTf.getText();
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                    noMore.setVisible(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(1);
                }
            }
            if (counter ==25)
            {
                try
                {
                    quantity9 = Double.parseDouble(quantityTf.getText());
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(2);
                }
            }
            if (counter ==26)
            {
                try
                {
                    ingredient10 = ingredientTf.getText();
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                    noMore.setVisible(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(1);
                }
            }
            if (counter ==27)
            {
                try
                {
                    quantity10 = Double.parseDouble(quantityTf.getText());
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(2);
                }
            }
            if (counter ==28)
            {
                try
                {
                    ingredient11 = ingredientTf.getText();
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                    noMore.setVisible(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(1);
                }
            }
            if (counter ==29)
            {
                try
                {
                    quantity11 = Double.parseDouble(quantityTf.getText());
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(2);
                }
            }
            if (counter ==30)
            {
                try
                {
                    ingredient12 = ingredientTf.getText();
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                    noMore.setVisible(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(1);
                }
            }
            if (counter ==31)
            {
                try
                {
                    quantity12 = Double.parseDouble(quantityTf.getText());
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(2);
                }
            }
            if (counter ==32)
            {
                try
                {
                    ingredient13 = ingredientTf.getText();
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                    noMore.setVisible(false);
                }
                catch (Exception p)
                {
                    Error objError = new Error(1);
                }
            }
            if (counter ==33)
            {
                try
                {
                    quantity13 = Double.parseDouble(quantityTf.getText());
                    next.setVisible(true);
                    flag = 0;
                    input.setEnabled(false);
                    
                }
                catch (Exception p)
                {
                    Error objError = new Error(2);
                }
            }
            
        }
        
        //for traversing through different input stages
        if (command.equals("Next"))
        {
            //to check if the algorithm is working
            //System.out.println("NEXT is pressed. COUNTER = " + counter);
            if(counter == 1)
            {
                //After pressing input button, if value is successfully inputted
                //the next button will appear. Based on the number of counter, 
                //it will make the GUI components for input appear
                //flag is to make sure the components only show up when values are inputted correctly
                if (flag == 0)
                {
                    counter = counter + 1;
                    nameTf.setVisible(false);
                    prompter.setText("Choose origin");
                    originDropMenu.setVisible(true);
                    next.setVisible(false);
                    input.setEnabled(true);
                    flag = 1;
                    
                }
                          
            }
            if (counter == 2)
            {
               
                if (flag == 0)
                {
                    counter = counter + 1;
                    originDropMenu.setVisible(false);
                    prompter.setText("<html>" + "Choose Type: " + "<br>" + "(NOTE: this choice decides what image your dish will show up as)"
                            + "</html>" );
                    prompter.setFont(TINY_FONT);
                    typeDropMenu.setVisible(true);
                    next.setVisible(false);
                    input.setEnabled(true);
                    flag = 1;
                }

            }
            if (counter ==3)
            {
                if (flag == 0)
                {
                    counter += 1;
                    typeDropMenu.setVisible(false);
                    prompter.setText("Choose property 1: ");
                    prompter.setFont(SMALL_FONT);
                    propertyDropMenu1.setVisible(true);
                    next.setVisible(false);
                    input.setEnabled(true);
                    flag = 1;
                }
                    
            }
            if (counter ==4)
            {
                if (flag == 0)
                {
                    counter += 1;
                    propertyDropMenu1.setVisible(false);
                    prompter.setText("Choose property 2: ");
                    propertyDropMenu2.setVisible(true);
                    next.setVisible(false);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==5)
            {
                if (flag == 0)
                {
                    counter += 1;
                    propertyDropMenu2.setVisible(false);
                    prompter.setText("Enter Description: ");
                    descriptionTf.setVisible(true);
                    next.setVisible(false);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==6)
            {
                if (flag == 0)
                {
                    counter += 1;
                    descriptionTf.setVisible(false);
                    prompter.setText("Enter source: ");
                    sourceTf.setVisible(true);
                    next.setVisible(false);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==7)
            {
                if (flag ==0)
                {
                    counter += 1;
                    sourceTf.setVisible(false);
                    prompter.setText("Enter ingredient 1: ");
                    ingredientTf.setVisible(true);
                    next.setVisible(false);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==8)
            {
                if (flag == 0)
                {
                    counter += 1;
                    ingredientTf.setVisible(false);
                    ingredientTf.setText("");
                    prompter.setText("Enter quantity 1: ");
                    quantityTf.setVisible(true);
                    next.setVisible(false);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==9)
            {
                if (flag == 0)
                {
                    counter += 1;
                    quantityTf.setVisible(false);
                    quantityTf.setText("");
                    prompter.setText("Enter ingredient 2: ");
                    ingredientTf.setVisible(true);
                    next.setVisible(false);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==10)
            {
                if (flag == 0)
                {
                    counter += 1;
                    ingredientTf.setVisible(false);
                    ingredientTf.setText("");
                    prompter.setText("Enter quantity 2: ");
                    quantityTf.setVisible(true);
                    next.setVisible(false);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==11)
            {
                if (flag ==0)
                {
                    counter += 1;
                    quantityTf.setVisible(false);
                    quantityTf.setText("");
                    prompter.setText("Enter ingredient 3: ");
                    ingredientTf.setVisible(true);
                    next.setVisible(false);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==12)
            {
                if (flag ==0)
                {
                    counter += 1;
                    ingredientTf.setVisible(false);
                    ingredientTf.setText("");
                    prompter.setText("Enter quantity 3: ");
                    quantityTf.setVisible(true);
                    next.setVisible(false);
                    input.setEnabled(true);
                    flag = 1;
                    
                }
            }
            if (counter ==13)
            {
                if (flag ==0)
                {
                    counter += 1;
                    quantityTf.setVisible(false);
                    quantityTf.setText("");
                    prompter.setText("Enter ingredient 4: ");
                    ingredientTf.setVisible(true);
                    next.setVisible(false);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==14)
            {
                if (flag ==0)
                {
                  
                    ingredientTf.setVisible(false);
                    ingredientTf.setText("");
                    prompter.setText("Enter quantity 4: ");
                    quantityTf.setVisible(true);
                    next.setVisible(false);
                    input.setEnabled(true);
                    counter += 1;
                    flag = 1;
                }
            }
            if (counter ==15)
            {
                if (flag ==0)
                {
                    counter += 1;
                    quantityTf.setVisible(false);
                    quantityTf.setText("");
                    prompter.setText("Enter ingredient 5: ");
                    ingredientTf.setVisible(true);
                    next.setVisible(false);
                    noMore.setVisible(true);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==16)
            {
                if (flag ==0)
                {
                    counter += 1;
                    ingredientTf.setVisible(false);
                    ingredientTf.setText("");
                    prompter.setText("Enter quantity 5: ");
                    quantityTf.setVisible(true);
                    next.setVisible(false);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==17)
            {
                if (flag == 0)
                {
                    counter += 1;
                    quantityTf.setVisible(false);
                    quantityTf.setText("");
                    prompter.setText("Enter ingredient 6: ");
                    ingredientTf.setVisible(true);
                    next.setVisible(false);
                    input.setEnabled(true);
                    noMore.setVisible(true);
                    flag = 1;
                }
            }
            if (counter ==18)
            {
                if (flag == 0)
                {
                    counter += 1;
                    ingredientTf.setVisible(false);
                    ingredientTf.setText("");
                    prompter.setText("Enter quantity 6: ");
                    quantityTf.setVisible(true);
                    next.setVisible(false);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==19)
            {
                if (flag == 0)
                {
                    counter += 1;
                    quantityTf.setVisible(false);
                    quantityTf.setText("");
                    prompter.setText("Enter ingredient 7: ");
                    ingredientTf.setVisible(true);
                    next.setVisible(false);
                    noMore.setVisible(true);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==20)
            {
                if (flag == 0)
                {
                    counter += 1;
                    ingredientTf.setVisible(false);
                    ingredientTf.setText("");
                    prompter.setText("Enter quantity 7: ");
                    quantityTf.setVisible(true);
                    next.setVisible(false);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==21)
            {
                if (flag == 0)
                {
                    counter += 1;
                    quantityTf.setVisible(false);
                    quantityTf.setText("");
                    prompter.setText("Enter ingredient 8: ");
                    ingredientTf.setVisible(true);
                    next.setVisible(false);
                    noMore.setVisible(true);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==22)
            {
                if (flag == 0)
                {
                    counter += 1;
                    ingredientTf.setVisible(false);
                    ingredientTf.setText("");
                    prompter.setText("Enter quantity 8: ");
                    quantityTf.setVisible(true);
                    next.setVisible(false);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==23)
            {
                if (flag == 0)
                {
                    counter += 1;
                    quantityTf.setVisible(false);
                    quantityTf.setText("");
                    prompter.setText("Enter ingredient 9: ");
                    ingredientTf.setVisible(true);
                    next.setVisible(false);
                    noMore.setVisible(true);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==24)
            {
                if (flag == 0) 
                {
                    counter += 1;
                    ingredientTf.setVisible(false);
                    ingredientTf.setText("");
                    prompter.setText("Enter quantity 9: ");
                    quantityTf.setVisible(true);
                    next.setVisible(false);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==25)
            {
                if (flag == 0) {
                    counter += 1;
                    quantityTf.setVisible(false);
                    quantityTf.setText("");
                    prompter.setText("Enter ingredient 10: ");
                    ingredientTf.setVisible(true);
                    next.setVisible(false);
                    noMore.setVisible(true);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==26)
            {
                if (flag ==0) {
                    counter += 1;
                    ingredientTf.setVisible(false);
                    ingredientTf.setText("");
                    prompter.setText("Enter quantity 10: ");
                    quantityTf.setVisible(true);
                    next.setVisible(false);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==27)
            {
                if (flag == 0) {
                    counter += 1;
                    quantityTf.setVisible(false);
                    quantityTf.setText("");
                    prompter.setText("Enter ingredient 11: ");
                    ingredientTf.setVisible(true);
                    next.setVisible(false);
                    noMore.setVisible(true);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==28)
            {
                if (flag ==0) {
                    counter += 1;
                    ingredientTf.setVisible(false);
                    ingredientTf.setText("");
                    prompter.setText("Enter quantity 11: ");
                    quantityTf.setVisible(true);
                    next.setVisible(false);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==29)
            {
                if (flag == 0) {
                    counter += 1;
                    quantityTf.setVisible(false);
                    quantityTf.setText("");
                    prompter.setText("Enter ingredient 12: ");
                    ingredientTf.setVisible(true);
                    next.setVisible(false);
                    noMore.setVisible(true);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==30)
            {
                if (flag == 0) {
                    counter += 1;
                    ingredientTf.setVisible(false);
                    ingredientTf.setText("");
                    prompter.setText("Enter quantity 12: ");
                    quantityTf.setVisible(true);
                    next.setVisible(false);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==31)
            {
                if (flag == 0) {
                    counter += 1;
                    quantityTf.setVisible(false);
                    quantityTf.setText("");
                    prompter.setText("Enter ingredient 13: ");
                    ingredientTf.setVisible(true);
                    next.setVisible(false);
                    noMore.setVisible(true);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==32)
            {
                if (flag == 0) {
                    counter += 1;
                    ingredientTf.setVisible(false);
                    prompter.setText("Enter quantity 13: ");
                    quantityTf.setVisible(true);
                    next.setVisible(false);
                    input.setEnabled(true);
                    flag = 1;
                }
            }
            if (counter ==33)
            {
                if (flag == 0)
                {
                    next.setVisible(false);
                    input.setEnabled(false);
                    finish.setVisible(true);
                }
            }
    
        }
        
        //when this button is pressed, the rest of the columns in the database
        //will be filled with the specific format to indicate that there's empty values
        if (command.equals("No More Ingredients"))
        {
            input.setVisible(false);
            
            if (counter == 16)
            {
                ingredient5 = "n/a";
                quantity5 = 0;
                ingredient6 = "n/a";
                quantity6 = 0;
                ingredient7 = "n/a";
                quantity7 = 0;
                ingredient8 = "n/a";
                quantity8 = 0;
                ingredient9 = "n/a";
                quantity9= 0;
                ingredient10 = "n/a";
                quantity10 = 0;
                ingredient11 = "n/a";
                quantity11 = 0;
                ingredient12 = "n/a";
                quantity12 = 0;
                ingredient13 = "n/a";
                quantity13 = 0;
            }
            else if (counter == 18)
            {
                ingredient6 = "n/a";
                quantity6 = 0;
                ingredient7 = "n/a";
                quantity7 = 0;
                ingredient8 = "n/a";
                quantity8 = 0;
                ingredient9 = "n/a";
                quantity9= 0;
                ingredient10 = "n/a";
                quantity10 = 0;
                ingredient11 = "n/a";
                quantity11 = 0;
                ingredient12 = "n/a";
                quantity12 = 0;
                ingredient13 = "n/a";
                quantity13 = 0;
            }
            else if (counter == 20)
            {    
                ingredient7 = "n/a";
                quantity7 = 0;
                ingredient8 = "n/a";
                quantity8 = 0;
                ingredient9 = "n/a";
                quantity9= 0;
                ingredient10 = "n/a";
                quantity10 = 0;
                ingredient11 = "n/a";
                quantity11 = 0;
                ingredient12 = "n/a";
                quantity12 = 0;
                ingredient13 = "n/a";
                quantity13 = 0;
            }
            else if (counter == 22)
            {    
                ingredient8 = "n/a";
                quantity8 = 0;
                ingredient9 = "n/a";
                quantity9= 0;
                ingredient10 = "n/a";
                quantity10 = 0;
                ingredient11 = "n/a";
                quantity11 = 0;
                ingredient12 = "n/a";
                quantity12 = 0;
                ingredient13 = "n/a";
                quantity13 = 0;
            }
            else if (counter == 24)
            {    
                ingredient9 = "n/a";
                quantity9= 0;
                ingredient10 = "n/a";
                quantity10 = 0;
                ingredient11 = "n/a";
                quantity11 = 0;
                ingredient12 = "n/a";
                quantity12 = 0;
                ingredient13 = "n/a";
                quantity13 = 0;
            }
            else if (counter == 26)
            {    
                ingredient10 = "n/a";
                quantity10 = 0;
                ingredient11 = "n/a";
                quantity11 = 0;
                ingredient12 = "n/a";
                quantity12 = 0;
                ingredient13 = "n/a";
                quantity13 = 0;
            }
            else if (counter == 28)
            {    
               
                ingredient11 = "n/a";
                quantity11 = 0;
                ingredient12 = "n/a";
                quantity12 = 0;
                ingredient13 = "n/a";
                quantity13 = 0;
            }
            else if (counter == 30)
            {    
                ingredient12 = "n/a";
                quantity12 = 0;
                ingredient13 = "n/a";
                quantity13 = 0;
            }
            else if (counter == 32)
            {    
                ingredient13 = "n/a";
                quantity13 = 0;
            }
            
            //id number's value will be the amount of rows in the "FoodRecipe' Table
            //which equals to the id  number of the position
            //to make sure everytime user input a recipe, the id number wont overwrite the 
            //preexisting information
            idNumber = (data.length);
            
            try
            {
                PreparedStatement ps = myDbConn.prepareStatement(insertQuery);

//              for inputting into FoodInfo Table
                ps.setInt(1,idNumber);
                ps.setString(2,name);
                ps.setString(3, origin);
                ps.setString(4, type);
                ps.setString(5, property1);
                ps.setString(6, property2);
                ps.setString(7, description);
                ps.setString(8, source);
                ps.setString(9, ingredient1 );
                ps.setDouble(10, quantity1);
                ps.setString(11, ingredient2);
                ps.setDouble(12, quantity2);
                ps.setString(13, ingredient3);
                ps.setDouble(14, quantity3);
                ps.setString(15, ingredient4);
                ps.setDouble(16, quantity4);
                ps.setString(17, ingredient5);
                ps.setDouble(18, quantity5);
                ps.setString(19, ingredient6);
                ps.setDouble(20, quantity6);
                ps.setString(21, ingredient7);
                ps.setDouble(22, quantity7);
                ps.setString(23, ingredient8);
                ps.setDouble(24, quantity8);
                ps.setString(25, ingredient9);
                ps.setDouble(26, quantity9);
                ps.setString(27, ingredient10);
                ps.setDouble(28, quantity10);
                ps.setString(29, ingredient11);
                ps.setDouble(30, quantity11);
                ps.setString(31, ingredient12);
                ps.setDouble(32, quantity12);
                ps.setString(33, ingredient13);
                ps.setDouble(34, quantity13);

                ps.executeUpdate();
                accessor.closeDbConn();
                noMore.setVisible(false);
                this.dispose();
                FoodDataList objFoodDataList = new FoodDataList();
                
            }
            catch(Exception l)
            {
                Error objError = new Error(5);
                System.out.println("ID: " + idNumber);
            }

//            for checking whether the table is filled up correctly
//            Object [][] data = accessor.getData("FoodInfo", columnName);
//
//            for(int i =0; i<data.length; i++)
//            {
//                for(int j = 0; j<data[i].length; j++)
//                    System.out.println(data[i][j]);
//              }
        }
        
        if (command.equals("Finish"))
        {
            //id number's value will be the amount of rows in the "FoodRecipe' Table
            //which equals to the id  number of the position
            //to make sure everytime user input a recipe, the id number wont overwrite the 
            //preexisting information
            idNumber = (data.length);
            
            try
            {
                PreparedStatement ps = myDbConn.prepareStatement(insertQuery);

//              for inputting into FoodInfo Table
                ps.setInt(1,idNumber);
                ps.setString(2,name);
                ps.setString(3, origin);
                ps.setString(4, type);
                ps.setString(5, property1);
                ps.setString(6, property2);
                ps.setString(7, description);
                ps.setString(8, source);
                ps.setString(9, ingredient1 );
                ps.setDouble(10, quantity1);
                ps.setString(11, ingredient2);
                ps.setDouble(12, quantity2);
                ps.setString(13, ingredient3);
                ps.setDouble(14, quantity3);
                ps.setString(15, ingredient4);
                ps.setDouble(16, quantity4);
                ps.setString(17, ingredient5);
                ps.setDouble(18, quantity5);
                ps.setString(19, ingredient6);
                ps.setDouble(20, quantity6);
                ps.setString(21, ingredient7);
                ps.setDouble(22, quantity7);
                ps.setString(23, ingredient8);
                ps.setDouble(24, quantity8);
                ps.setString(25, ingredient9);
                ps.setDouble(26, quantity9);
                ps.setString(27, ingredient10);
                ps.setDouble(28, quantity10);
                ps.setString(29, ingredient11);
                ps.setDouble(30, quantity11);
                ps.setString(31, ingredient12);
                ps.setDouble(32, quantity12);
                ps.setString(33, ingredient13);
                ps.setDouble(34, quantity13);

                ps.executeUpdate();
                finish.setVisible(false);
                accessor.closeDbConn();
                this.dispose();
                FoodDataList objFoodDataList = new FoodDataList();
            }
            catch(Exception l)
            {
                Error objError = new Error(5);
            }

//            for checking whether the table is filled up correctly
//            Object [][] data = accessor.getData("FoodInfo", columnName);
//
//            for(int i =0; i<data.length; i++)
//            {
//                for(int j = 0; j<data[i].length; j++)
//                    System.out.println(data[i][j]);
//            }
        }
        
        if (command.equals("Go Back"))
        {
            FoodDataList objFoodDataList = new FoodDataList();
            accessor.closeDbConn();
            this.dispose();         
        }
        if (command.equals("Help"))
        {
            Help objHelp = new Help();
        }
    }
    
    public static void main(String[] args)
    {
        DishAdder objDishAdder = new DishAdder();
    }
    
}
