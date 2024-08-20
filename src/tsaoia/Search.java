//9-18-2019
//IB ComSci SL 2
//Purpose of class: For providing the input of Search Algorithm

package tsaoia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Search extends JFrame implements ActionListener
{
    //declaration of variables
    public final Color MAIN_COLOR = new Color(212, 224, 206);
    public final Color SIDE_COLOR = new Color(91, 184, 44);
    
    public final Font MENU_FONT = new Font("Rockwell", Font.BOLD , 30);
    public final Font SMALL_FONT = new Font("Rockwell", Font.PLAIN, 20);
    
    private JLabel title;
    private JLabel name;
    private JLabel origin;
    private JLabel type;
    private JLabel  property;
    private JRadioButton nameB;
    private JRadioButton originB;
    private JRadioButton typeB;
    private JRadioButton  propertyB;
    private ButtonGroup choiceGroup;
    private JTextField nameTf;
    private JComboBox<String> originDropMenu;
    private JComboBox<String> typeDropMenu;
    private JComboBox<String> propertyDropMenu;
    
    private JButton exit;
    private JButton back;
    private JButton search;
    private JPanel buttonHolder;
    private JPanel namePan;
    private JPanel originPan;
    private JPanel typePan;
    private JPanel propertyPan;
    private JPanel bulkPan;
    
    private JMenuBar helpMenuBar;
    private JMenu helpMenu;
    private JMenuItem helpItem;
    
    public Search()
    {
        super("Search");
        this.setBounds(400,150,1000,800);
        this.getContentPane().setBackground(MAIN_COLOR);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //constructors for JLabels
        title = new JLabel("<html>" + "Choose one aspect you would like to search then enter "
                + "information accordingly: " + "</html>") ;
        title.setFont(MENU_FONT);
        title.setForeground(Color.BLACK);
        
        name = new JLabel("Name: ");
        name.setFont(SMALL_FONT);
        name.setForeground(Color.BLACK);
        
        origin = new JLabel("Origin: ");
        origin.setFont(SMALL_FONT);
        origin.setForeground(Color.BLACK);
        
        type = new JLabel("Type: ");
        type.setFont(SMALL_FONT);
        type.setForeground(Color.BLACK);
        
        property = new JLabel("Property: ");
        property.setFont(SMALL_FONT);
        property.setForeground(Color.BLACK);
        //constructors for radio butttons
        nameB = new JRadioButton();
        nameB.addActionListener(this);
        
        originB = new JRadioButton();
        originB.addActionListener(this);
        
        typeB = new JRadioButton();
        typeB.addActionListener(this);
        
        propertyB = new JRadioButton();
        propertyB.addActionListener(this);
        
        choiceGroup = new ButtonGroup();
        choiceGroup.add(nameB);
        choiceGroup.add(originB);
        choiceGroup.add(typeB);
        choiceGroup.add(propertyB);
        //constructors for tools of user input (Text field and combo box)
        nameTf = new JTextField(40);
        
        String[] origins = {"", "Greece", "France", "Italy", "Germany", "England",
            "Spain", "United States", "Canada", "Mexico", "Taiwan", "China", "Russia",
            "Brazil", "Japan", "Korea", "Thailand", "Turkey"};
        originDropMenu = new JComboBox<>(origins);
        originDropMenu.setFont(SMALL_FONT);
        originDropMenu.setSelectedItem(origins[0]);
        originDropMenu.addActionListener(this);
        
        String[] types = {"dessert", "dish", "soup", "appetizer"};
        typeDropMenu = new JComboBox<>(types);
        typeDropMenu.setFont(SMALL_FONT);
        typeDropMenu.setSelectedItem(types[0]);
        typeDropMenu.addActionListener(this);
        
        String[] properties = {"sweet", "creamy", "salty", "spicy",
            "greasy", "crumbly", "crunchy", "crispy"};
        propertyDropMenu = new JComboBox<>(properties);
        propertyDropMenu.setFont(SMALL_FONT);
        propertyDropMenu.setSelectedItem(properties[0]);
        propertyDropMenu.addActionListener(this);
        
        namePan = new JPanel(new FlowLayout());
        namePan.setBackground(MAIN_COLOR);
        namePan.add(nameB);
        namePan.add(name);
        namePan.add(nameTf);
        
        originPan = new JPanel(new FlowLayout());
        originPan.setBackground(MAIN_COLOR);
        originPan.add(originB);
        originPan.add(origin);
        originPan.add(originDropMenu);
        
        typePan = new JPanel(new FlowLayout());
        typePan.setBackground(MAIN_COLOR);
        typePan.add(typeB);
        typePan.add(type);
        typePan.add(typeDropMenu);
        
        propertyPan = new JPanel(new FlowLayout());
        propertyPan.setBackground(MAIN_COLOR);
        propertyPan.add(propertyB);
        propertyPan.add(property);
        propertyPan.add(propertyDropMenu);
        
        bulkPan = new JPanel(new FlowLayout());
        bulkPan.setBackground(MAIN_COLOR);
        bulkPan.add(namePan);
        bulkPan.add(originPan);
        bulkPan.add(typePan);
        bulkPan.add(propertyPan);
        
        buttonHolder = new JPanel(new FlowLayout());
        buttonHolder.setBackground(SIDE_COLOR);
        
        back = new JButton("Back");
        back.addActionListener(this);
        
        exit = new JButton("Exit");
        exit.addActionListener(this);
        
        search = new JButton("Search");
        search.addActionListener(this);
        
        buttonHolder.add(exit);
        buttonHolder.add(back);
        buttonHolder.add(search);
        
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
        //the array which will be holding the id of the search results
        int[] resultList = new int[10];
        //variables to represent users selection
        int targetParameter;
        String choice = "";
        //calling object computation class
        Computation searcher = new Computation();
        //a flag to check whether there is an issue
        int flag = 1;
        
        String originListener = (String) originDropMenu.getSelectedItem();
        String typeListener = (String) typeDropMenu.getSelectedItem();
        String propertyListener = (String) propertyDropMenu.getSelectedItem();
        
        if (command.equals("Back"))
        {
            this.dispose();
            FoodDataList objFoodDataList = new FoodDataList();
        }
        
        if (command.equals("Exit"))
        {
            System.exit(0);
        }
        
        if (command.equals("Help"))
        {
            Help objHelp = new Help();
        }
        
        if (command.equals("Search"))
        {
            //when different parameters are selected
            if (nameB.isSelected())
            {
                flag = 0;
                searcher.setTargetParameter(1);
                resultList = searcher.Search(nameTf.getText());
            }
            
            else if (originB.isSelected())
            {
                flag = 0;
                searcher.setTargetParameter(2);
                try
                {
                    //load comboBox selection
                    switch (originListener) 
                    {

                        case "":
                            flag = 1;
                            Error objError = new Error(3);
                            break;
                        case "Greece":
                            flag = 0;
                            resultList = searcher.Search(originListener);
                            break;
                        case "France":
                            flag = 0;
                            resultList = searcher.Search(originListener);
                            break;
                        case "Italy":
                            flag = 0;
                            resultList = searcher.Search(originListener);
                            break;
                        case "Germany":
                            flag = 0;
                            resultList = searcher.Search(originListener);
                            break;
                        case "England":
                            flag = 0;
                            resultList = searcher.Search(originListener);
                            break;
                        case "Spain":
                            flag = 0;
                            resultList = searcher.Search(originListener);
                            break;
                        case "United States":
                            flag = 0;
                            resultList = searcher.Search(originListener);
                            break;
                        case "Canada":
                            flag = 0;
                            resultList = searcher.Search(originListener);
                            break;
                        case "Mexico":
                            flag = 0;
                            resultList = searcher.Search(originListener);
                            break;
                        case "Taiwan":
                            flag = 0;
                            resultList = searcher.Search(originListener);
                            break;
                        case "China":
                            flag = 0;
                            resultList = searcher.Search(originListener);
                            break;
                        case "Russia":
                            flag = 0;
                            resultList = searcher.Search(originListener);
                            break;
                        case "Brazil":
                            flag = 0;
                            resultList = searcher.Search(originListener);
                            break;
                        case "Japan":
                            flag = 0;
                            resultList = searcher.Search(originListener);
                            break;
                        case "Korea":
                            flag = 0;
                            resultList = searcher.Search(originListener);
                            break;
                        case "Thailand":
                            flag = 0;
                            resultList = searcher.Search(originListener);
                            break;
                        case "Turkey":
                            flag = 0;
                            resultList = searcher.Search(originListener);
                            break;

                    }
                } 
                catch (Exception p)
                {
                    Error objError = new Error(3);
                }
            }
            else if (typeB.isSelected())
            {
                flag = 0;
                searcher.setTargetParameter(3);
                try
                {
                    //load comboBox value from type
                    switch (typeListener)
                    {
                       case "":
                           flag = 1;
                            Error objError = new Error(3);
                            break;
                        case "dessert":
                            flag = 0;
                            resultList = searcher.Search(typeListener);
                            break;
                        case "dish":
                            flag = 0;
                            resultList = searcher.Search(typeListener);
                            break;
                        case "soup":
                            flag = 0;
                            resultList = searcher.Search(typeListener);
                            break;
                        case "appetizer":
                            flag = 0;
                            resultList = searcher.Search(typeListener);
                            break;
                    }
                    
                }
                catch (Exception p)
                {
                    Error objError = new Error(3);
                }
            }
            else if (propertyB.isSelected())
            {
                flag = 0;
                searcher.setTargetParameter(4);
                try
                {
                    //load property
                    switch (propertyListener)
                    {
                        
                        case "":
                            flag = 1;
                            Error objError = new Error(3);
                            break;
                        case "sweet":
                            flag = 0;
                            resultList = searcher.Search(propertyListener);
                            break;
                        case "creamy":
                            flag = 0;
                            resultList = searcher.Search(propertyListener);
                            break;
                        case "salty":
                            flag = 0;
                            resultList = searcher.Search(propertyListener);
                            break;
                        case "spicy":
                            flag = 0;
                            resultList = searcher.Search(propertyListener);
                            break;
                        case "greasy":
                            flag = 0;
                            resultList = searcher.Search(propertyListener);
                            break;
                        case "crumbly":
                            flag = 0;
                            resultList = searcher.Search(propertyListener);
                            break;
                        case "crunchy":
                            flag = 0;
                            resultList = searcher.Search(propertyListener);
                            break;
                        case "crispy":
                            flag = 0;
                            resultList = searcher.Search(propertyListener);
                            break;
                    }
                    
                }
                catch (Exception p)
                {
                    Error objError = new Error(3);
                }
            }
            //for testing the search method
//            System.out.println(resultList[0]);
//            System.out.println(resultList[1]);

            //if there is no errors
            if (flag == 0)
            {
                Result objResult = new Result(resultList);
                this.dispose();
            }
            else
            {
               Error objError = new Error(3); 
            }
            
        }
    }
    
    public static void main(String[] args)
    {
        Search objSearch = new Search();
    }
    
}
