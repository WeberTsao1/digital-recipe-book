//9-17-2019
//IB ComSci SL 2
//Purpose of class: The beginning of this application. Provides some introduction to the application

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
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Welcome extends JFrame implements ActionListener
{
    //declaration of variables
    public final Color MAIN_COLOR = new Color(169, 201, 201);
    public final Color SIDE_COLOR = new Color(125, 83, 143);
    public final Color EXTRA_COLOR = new Color(67, 146, 224);
    public final Font MENU_FONT = new Font("Rockwell", Font.BOLD , 30);

    public final java.net.URL MAIN = getClass().getResource("Digital recipe book.png");
    public final ImageIcon MAIN_PIC = new ImageIcon(new ImageIcon(MAIN).getImage().getScaledInstance(470,500,Image.SCALE_DEFAULT));
    
    private JLabel title;
    private JLabel picHolder;
    private JPanel buttonHolder;
    private JButton info;
    private JButton start;
    private JButton exit;
    
    private JMenuBar helpMenuBar;
    private JMenu helpMenu;
    private JMenuItem helpItem;
    
    public Welcome()
    {
        //construction of frame
        super("Welcome");
        this.setBounds(400,150,1000,800);
        this.getContentPane().setBackground(MAIN_COLOR);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        title = new JLabel("Digital Recipe Book " , SwingConstants.CENTER);
        title.setForeground(EXTRA_COLOR);
        title.setFont(MENU_FONT);
        
        picHolder = new JLabel(MAIN_PIC);
        
        buttonHolder = new JPanel(new FlowLayout());
        buttonHolder.setBackground(SIDE_COLOR);
        
        info = new JButton("Info");
        info.addActionListener(this);
        
        start = new JButton("Start");
        start.addActionListener(this);
        
        exit = new JButton("Exit");
        exit.addActionListener(this);
        
        buttonHolder.add(info);
        buttonHolder.add(start);
        buttonHolder.add(exit);
        
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
        this.add(buttonHolder, BorderLayout.SOUTH);
        this.add(picHolder, BorderLayout.CENTER);
        this.add(title, BorderLayout.NORTH);
        this.setVisible(true);
    }
    

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        
        if (command.equals("Info"))
        {
            ProgramInformation objProgramInformation = new ProgramInformation();
        }
        if (command.equals("Start"))
        {
            FoodDataList objFoodDataList = new FoodDataList();
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
        Welcome objWelcome = new Welcome();

    }
    
}
