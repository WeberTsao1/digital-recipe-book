//9-19-2019
//IB ComSci SL 2
//Purpose of Class: To accept user input for the advanced search algorithm

package tsaoia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdvancedSearch extends JFrame implements ActionListener
{
    //declaration of variables
    public final Color MAIN_COLOR = new Color(212, 224, 206);
    public final Color SIDE_COLOR = new Color(237, 207, 74);
    
    public final Font MENU_FONT = new Font("Rockwell", Font.BOLD , 30);
    public final Font SMALL_FONT = new Font("Rockwell", Font.PLAIN, 20);
    
    private JLabel title;
    private JLabel name;
    private JLabel origin;
    private JLabel type;
    private JLabel  property1;
    private JLabel property2;
    private JCheckBox nameCb;
    private JCheckBox originCb;
    private JCheckBox typeCb;
    private JCheckBox property1Cb;
    private JCheckBox property2Cb;
    private JTextField nameTf;
    private JComboBox<String> originDropMenu;
    private JComboBox<String> typeDropMenu;
    private JComboBox<String> propertyDropMenu1;
    private JComboBox<String> propertyDropMenu2;
    
    private JButton exit;
    private JButton back;
    private JButton advancedSearch;
    private JPanel buttonHolder;
    private JPanel namePan;
    private JPanel originPan;
    private JPanel typePan;
    private JPanel property1Pan;
    private JPanel property2Pan;
    private JPanel bulkPan;
    
    private JMenuBar helpMenuBar;
    private JMenu helpMenu;
    private JMenuItem helpItem;
    
    public AdvancedSearch()
    {
        //construction of frame
        super("Advanced Search");
        this.setBounds(400,150,1000,800);
        this.getContentPane().setBackground(MAIN_COLOR);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //constructors for Jlabels
        title = new JLabel("<html>" + "Choose the aspects you would like "
                + "to search with then enter information accordingly: (Origin HAS to be selected)" + "</html>") ;
        title.setFont(MENU_FONT);
        title.setForeground(Color.BLACK);
        
        origin = new JLabel("Origin: ");
        origin.setFont(SMALL_FONT);
        origin.setForeground(Color.BLACK);
        
        type = new JLabel("Type: ");
        type.setFont(SMALL_FONT);
        type.setForeground(Color.BLACK);
        
        property1 = new JLabel("Property1: ");
        property1.setFont(SMALL_FONT);
        property1.setForeground(Color.BLACK);
        
        property2 = new JLabel("Property2: ");
        property2.setFont(SMALL_FONT);
        property2.setForeground(Color.BLACK);
        //constructors for check box
        originCb = new JCheckBox();
        originCb.addActionListener(this);
        
        typeCb = new JCheckBox();
        typeCb.addActionListener(this);
        
        property1Cb = new JCheckBox();
        property1Cb.addActionListener(this);
        
        property2Cb = new JCheckBox();
        property2Cb.addActionListener(this);
        //constructors for combo boxes
        String[] origins = {"", "Greece", "France", "Italy", "Germany", "England",
            "Spain", "United States", "Canada", "Mexico", "Taiwan", "China", "Russia",
            "Brazil", "Japan", "Korea", "Thailand", "Turkey"};
        originDropMenu = new JComboBox<>(origins);
        originDropMenu.setFont(SMALL_FONT);
        originDropMenu.setSelectedItem(origins[0]);
        originDropMenu.addActionListener(this);
        
        String[] types = {"", "dessert", "dish", "soup", "appetizer"};
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
        //constructors for JPanels for holding different input group
        originPan = new JPanel(new FlowLayout());
        originPan.setBackground(MAIN_COLOR);
        originPan.add(originCb);
        originPan.add(origin);
        originPan.add(originDropMenu);
        
        typePan = new JPanel(new FlowLayout());
        typePan.setBackground(MAIN_COLOR);
        typePan.add(typeCb);
        typePan.add(type);
        typePan.add(typeDropMenu);
        
        property1Pan = new JPanel(new FlowLayout());
        property1Pan.setBackground(MAIN_COLOR);
        property1Pan.add(property1Cb);
        property1Pan.add(property1);
        property1Pan.add(propertyDropMenu1);
        
        property2Pan = new JPanel(new FlowLayout());
        property2Pan.setBackground(MAIN_COLOR);
        property2Pan.add(property2Cb);
        property2Pan.add(property2);
        property2Pan.add(propertyDropMenu2);
        
        bulkPan = new JPanel(new FlowLayout());
        bulkPan.setBackground(MAIN_COLOR);
        bulkPan.add(originPan);
        bulkPan.add(typePan);
        bulkPan.add(property1Pan);
        bulkPan.add(property2Pan);
        
        buttonHolder = new JPanel(new FlowLayout());
        buttonHolder.setBackground(SIDE_COLOR);
        //constructors for buttons
        back = new JButton("Back");
        back.addActionListener(this);
        
        exit = new JButton("Exit");
        exit.addActionListener(this);
        
        advancedSearch = new JButton("Advanced Search");
        advancedSearch.addActionListener(this);
        
        buttonHolder.add(exit);
        buttonHolder.add(back);
        buttonHolder.add(advancedSearch);
        
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
        this.add(bulkPan, BorderLayout.CENTER);
        this.add(buttonHolder, BorderLayout.SOUTH);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        
        //calling object computation class
        Computation searcher = new Computation();
        
        String originListener = (String) originDropMenu.getSelectedItem();
        String typeListener = (String) typeDropMenu.getSelectedItem();
        String propertyListener1 = (String) propertyDropMenu1.getSelectedItem();
        String propertyListener2 = (String) propertyDropMenu2.getSelectedItem();
        
        Computation searchTool = new Computation();
        
        if (command.equals("Help"))
        {
            Help objHelp = new Help();
        }
        
        if (command.equals("Exit"))
        {
            System.exit(0);
        }
        
        if (command.equals("Back"))
        {
            this.dispose();
            FoodDataList objFoodDataList = new FoodDataList();
        }
        
        if (command.equals("Advanced Search"))
        {
            //the array which will eventually hold the id of result
            int[] resultList = new int[1];
            //array to indicate the parameters user choose (ex: origin, property)
            //preset is -1 so when reading the parameter, the advanced search algorithm will know there's no selection
            int[] parameter = {-1,-1,-1,-1};
            //an array to store user input of choice (ex: the country of origin)
            String[] choice = {"", "", "", ""};
            //a counter to track the amount of parameters entered. Also tracking the psotion to populate parameter and choice array
            int parameterCounter = 0;
            //to make sure user checked and selected an origin
            int checker = 1;
            
            if (originCb.isSelected())
            {
                try
                {
                    //2 represents orgin because it is the 3rd column in database
                    parameter[parameterCounter] = 2;
                    
                    //load comboBox selection
                    switch (originListener) 
                    {
                        
                        case "Greece":
                            choice[parameterCounter] = originListener;
                            parameterCounter = parameterCounter + 1;
                            checker = 0;
                            break;
                        case "France":
                            choice[parameterCounter] = originListener;
                            parameterCounter = parameterCounter + 1;
                            checker = 0;
                            break;
                        case "Italy":
                            choice[parameterCounter] = originListener;
                            parameterCounter = parameterCounter + 1;
                            checker = 0;
                            break;
                        case "Germany":
                            choice[parameterCounter] = originListener;
                            parameterCounter = parameterCounter + 1;
                            checker = 0;
                            break;
                        case "England":
                            choice[parameterCounter] = originListener;
                            parameterCounter = parameterCounter + 1;
                            checker = 0;
                            break;
                        case "Spain":
                            choice[parameterCounter] = originListener;
                            parameterCounter = parameterCounter + 1;
                            checker = 0;
                            break;
                        case "United States":
                            choice[parameterCounter] = originListener;
                            parameterCounter = parameterCounter + 1;
                            checker = 0;
                            break;
                        case "Canada":
                            choice[parameterCounter] = originListener;
                            parameterCounter = parameterCounter + 1;
                            checker = 0;
                            break;
                        case "Mexico":
                            choice[parameterCounter] = originListener;
                            parameterCounter = parameterCounter + 1;
                            checker = 0;
                            break;
                        case "Taiwan":
                            choice[parameterCounter] = originListener;
                            parameterCounter = parameterCounter + 1;
                            checker = 0;
                            break;
                        case "China":
                            choice[parameterCounter] = originListener;
                            parameterCounter = parameterCounter + 1;
                            checker = 0;
                            break;
                        case "Russia":
                            choice[parameterCounter] = originListener;
                            parameterCounter = parameterCounter + 1;
                            checker = 0;
                            break;
                        case "Brazil":
                            choice[parameterCounter] = originListener;
                            parameterCounter = parameterCounter + 1;
                            checker = 0;
                            break;
                        case "Japan":
                            choice[parameterCounter] = originListener;
                            parameterCounter = parameterCounter + 1;
                            checker = 0;
                            break;
                        case "Korea":
                            choice[parameterCounter] = originListener;
                            parameterCounter = parameterCounter + 1;
                            checker = 0;
                            break;
                        case "Thailand":
                            choice[parameterCounter] = originListener;
                            parameterCounter = parameterCounter + 1;
                            checker = 0;
                            break;
                        case "Turkey":
                            choice[parameterCounter] = originListener;
                            parameterCounter = parameterCounter + 1;
                            checker = 0;
                            break;

                    }
                } 
                catch (Exception p)
                {
                    Error objError = new Error(3);
                }
                
            }
            
            if (typeCb.isSelected())
            {
                try
                {
                    //3 represents type because it is the 4th column in database
                    parameter[parameterCounter] = 3;
                    
                    //load comboBox value from type
                    switch (typeListener)
                    {
                        
                        case "dessert":
                            choice[parameterCounter] = typeListener;
                            parameterCounter = parameterCounter + 1;
                            break;
                        case "dish":
                            choice[parameterCounter] = typeListener;
                            parameterCounter = parameterCounter + 1;
                            break;
                        case "soup":
                            choice[parameterCounter] = typeListener;
                            parameterCounter = parameterCounter + 1;
                            break;
                        case "appetizer":
                            choice[parameterCounter] = typeListener;
                            parameterCounter = parameterCounter + 1;
                            break;
                    }
                    
                }
                catch (Exception p)
                {
                    Error objError = new Error(3);
                }
            }
            
            if (property1Cb.isSelected())
            {
                try
                {
                    //4 represents type because it is the 5th column in database
                    parameter[parameterCounter] = 4;
                    
                    //load property1
                    switch (propertyListener1)
                    {
                        case "sweet":
                            choice[parameterCounter] = propertyListener1;
                            parameterCounter = parameterCounter + 1;
                            break;
                        case "creamy":
                            choice[parameterCounter] = propertyListener1;
                            parameterCounter = parameterCounter + 1;
                            break;
                        case "salty":
                            choice[parameterCounter] = propertyListener1;
                            parameterCounter = parameterCounter + 1;
                            break;
                        case "spicy":
                            choice[parameterCounter] = propertyListener1;
                            parameterCounter = parameterCounter + 1;
                            break;
                        case "greasy":
                            choice[parameterCounter] = propertyListener1;
                            parameterCounter = parameterCounter + 1;
                            break;
                        case "crumbly":
                            choice[parameterCounter] = propertyListener1;
                            parameterCounter = parameterCounter + 1;
                            break;
                        case "crunchy":
                            choice[parameterCounter] = propertyListener1;
                            parameterCounter = parameterCounter + 1;
                            break;
                        case "crispy":
                            choice[parameterCounter] = propertyListener1;
                            parameterCounter = parameterCounter + 1;
                            break;
                    }
                    
                }
                catch (Exception p)
                {
                    Error objError = new Error(3);
                }
            }
            
            if (property2Cb.isSelected())
            {
                try
                {
                    //5 represents property2 because it is the 6th column in database
                    parameter[parameterCounter] = 5;
                    
                    //load property2
                    switch (propertyListener2)
                    {
                        default:
                            choice[parameterCounter] = propertyListener2;
                            break;
                        case "sweet":
                            choice[parameterCounter] = propertyListener2;
                            break;
                        case "creamy":
                            choice[parameterCounter] = propertyListener2;
                            break;
                        case "salty":
                            choice[parameterCounter] = propertyListener2;
                            break;
                        case "spicy":
                            choice[parameterCounter] = propertyListener2;
                            break;
                        case "greasy":
                            choice[parameterCounter] = propertyListener2;
                            break;
                        case "crumbly":
                            choice[parameterCounter] = propertyListener2;
                            break;
                        case "crunchy":
                            choice[parameterCounter] = propertyListener2;
                            break;
                        case "crispy":
                            choice[parameterCounter] = propertyListener2;
                            break;
                    }
                    
                }
                catch (Exception p)
                {
                    Error objError = new Error(3);
                }
            }

            //if nothing is selected
            if (parameter[0] == -1)
            {
                Error objError = new Error(3);
            }
            //if the user only choose one aspect
            else if (parameter[1] == -1)
            {
                Error objError = new Error(8);
            }
            //if origin is not selected
            else if(checker == 1)
            {
                Error objError = new Error(7);
            }
            //when user is inputting correctly
            else
            {
                //inputting parameter and choice array into advanced search algorithm
                searchTool.setParameters(parameter);
                searchTool.setChoices(choice);
                resultList = searchTool.AdvancedSearch();
                Result objResult = new Result(resultList);
                
                this.dispose();
                
            }
     
        }
    }
    
    public static void main(String[] args)
    {
        AdvancedSearch objAdvancedSearch = new AdvancedSearch();
    }
    
}
