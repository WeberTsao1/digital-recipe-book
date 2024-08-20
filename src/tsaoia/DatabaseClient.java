//9-15-2019
//IB ComSci SL 2
//pupose of class: To populate the database

package tsaoia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseClient
{
    public static void main(String[] args)
    {
        
//columns for the FoodInfo database table
        //String[] columnName = {"ID", "Name", "Origin", "Type", "Property1", "Property2", "Description",
//                                "Source", "Ingredient1", "Quantity1", "Ingredient2", "Quantity2",
//                                "Ingredient3", "Quantity3", "Ingredient4", "Quantity4", "Ingredient5",
//                                "Quantity5", "Ingredient6", "Quantity6", "Ingredient7", "Quantity7",
//                                "Ingredient8", "Quantity8", "Ingredient9", "Quantity9", "Ingredient10",
//                                "Quantity10", "Ingredient11", "Quantity11", "Ingredient12", "Quantity12",
//                                "Ingredient13", "Quantity13"};
        
//columns for the Nation Cordinates database table
        String[] columnName = {"Origin", "Xcordinate", "Ycordinate"};
        
        DatabaseAccess accessor = new DatabaseAccess("RecipeDb");
        Connection myDbConn;
        
        //for populating the FoodInfo Table
        int id = 9;
        String name = "Schnitzel";
        String origin = "Germany";
        String type = "dish";
        String property1= "salty";
        String property2 = "crispy";
        String description = "Fried pork cutlets covered in bread crumb to create a crispy sensation";
        String source = "https://www.jocooks.com/recipes/pork-schnitzel/";
        String ingredient1 = "pork cutlets";
        double quantity1 = 10;
        String ingredient2 = "vegetable oil (liter)";
        double quantity2 = 1;
        String ingredient3 = "large eggs";
        double quantity3 = 2;
        String ingredient4 = "all-purpose flour (grams)";
        double quantity4 = 125;
        String ingredient5 = "breadcrumbs (grams)";
        double quantity5 = 108;
        String ingredient6 = "salt (teaspoon)";
        double quantity6 = 1;
        String ingredient7 = "pepper (teaspoon)";
        double quantity7 = 1;
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



        
        //String dishName = "blue berry pie";


//        for deleting
//        String locationName = "";
        
//for inserting into Nation Cordinate table
        String country = "Turkey";
        double xCord = 2;
        double yCord = -1;        
        
        myDbConn = accessor.getDbConn();
        
        //for inserting into FoodInfo Table
        String dbQuery1 = "INSERT INTO FoodInfo VALUES " +
                                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                                + "?,?,?,?)";
        
//for deleting in case of mistake
        String dbQuery2 = "DELETE FROM FoodInfo WHERE Name= ?";

//for inputting into NationCordinate Table
        String dbQuery3 = "INSERT INTO NationCordinates VALUES " +
                                "(?,?,?)";
        
//for deleting from NationCordinate Table
//        String dbQuery4 = "DELETE FROM NationCordinates WHERE Origin= ?";

//for making specific changes to database
//        Statement s = null;
//        ResultSet rs = null;        
//        //Query for special purpose other than inserting or deleting from database
//        String specificPurpose = ""; 
//        try
//        {
//            s = myDbConn.createStatement();
//            rs = s.executeQuery(specificPurpose);
//            System.out.println("Database has been succesfully dropped");
//        }
//        catch (SQLException err)
//        {
//            System.out.println("Unable to delete database");
//            System.exit(0);
//        }
        
// for executing prepared statements (used for inserting and deleting into the tables
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery3);
            
//            for deleting from FoodInfo Table
//            ps.setString(1,locationName);

            //ps.setString(1, dishName);
            
//            for inputting into FoodInfo Table
//            ps.setInt(1,id);
//            ps.setString(2,name);
//            ps.setString(3, origin);
//            ps.setString(4, type);
//            ps.setString(5, property1);
//            ps.setString(6, property2);
//            ps.setString(7, description);
//            ps.setString(8, source);
//            ps.setString(9, ingredient1 );
//            ps.setDouble(10, quantity1);
//            ps.setString(11, ingredient2);
//            ps.setDouble(12, quantity2);
//            ps.setString(13, ingredient3);
//            ps.setDouble(14, quantity3);
//            ps.setString(15, ingredient4);
//            ps.setDouble(16, quantity4);
//            ps.setString(17, ingredient5);
//            ps.setDouble(18, quantity5);
//            ps.setString(19, ingredient6);
//            ps.setDouble(20, quantity6);
//            ps.setString(21, ingredient7);
//            ps.setDouble(22, quantity7);
//            ps.setString(23, ingredient8);
//            ps.setDouble(24, quantity8);
//            ps.setString(25, ingredient9);
//            ps.setDouble(26, quantity9);
//            ps.setString(27, ingredient10);
//            ps.setDouble(28, quantity10);
//            ps.setString(29, ingredient11);
//            ps.setDouble(30, quantity11);
//            ps.setString(31, ingredient12);
//            ps.setDouble(32, quantity12);
//            ps.setString(33, ingredient13);
//            ps.setDouble(34, quantity13);

//            ps.setString(1,country);
//            ps.setDouble(2, xCord);
//            ps.setDouble(3, yCord);
            
            //ps.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println("Did not insert");
        }
        
//        one for FoodInfo table and another for NationCordinates table
//        for checking whether the table is filled up correctly
        //Object [][] data = accessor.getData("FoodInfo", columnName);
        Object [][] data = accessor.getData("NationCordinates", columnName);
        accessor.closeDbConn();
        for(int i =0; i<data.length; i++)
        {
            for(int j = 0; j<data[i].length; j++)
                System.out.println(data[i][j]);
        }
    }
}
