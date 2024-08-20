//9-16-2019
//IB ComSci SL 2
//Purpose of class: For creating the database

package tsaoia;

import java.sql.Statement;

public class DatabaseInstall
{
    
    public static void main(String[] args)
    {
        String newTable;
        String newTable2;
        DatabaseAccess accessor = new DatabaseAccess();
        accessor.CreateDb("RecipeDb");
        
        //the table which contains food informations
        newTable = "CREATE TABLE FoodInfo ( " +
                        "ID int, " +
                        "Name varchar(60), " +
                        "Origin varchar(40), " +
                        "Type varchar(20), " +
                        "Property1 varchar(20), " +
                        "Property2 varchar(20), " +
                        "Description varchar(500), " +
                        "Source varchar(500), " +
                        "Ingredient1 varchar(60), " +
                        "Quantity1 double, " +
                        "Ingredient2 varchar(60), " +
                        "Quantity2 double, " +
                        "Ingredient3 varchar(60), " +
                        "Quantity3 double, " +
                        "Ingredient4 varchar(60), " +
                        "Quantity4 double, " +
                        "Ingredient5 varchar(60), " +
                        "Quantity5 double, " +
                        "Ingredient6 varchar(60), " +
                        "Quantity6 double, " +
                        "Ingredient7 varchar(60), " +
                        "Quantity7 double, "   +
                        "Ingredient8 varchar(60), " +
                        "Quantity8 double, "   +
                        "Ingredient9 varchar(60), " +
                        "Quantity9 double, "   +
                        "Ingredient10 varchar(60), " +
                        "Quantity10 double, "   +
                        "Ingredient11 varchar(60), " +
                        "Quantity11 double, "   +
                        "Ingredient12 varchar(60), " +
                        "Quantity12 double, "   +
                        "Ingredient13 varchar(60), " +
                        "Quantity13 double "   +
                        ")";

        //the table which contains country's cordinates
        newTable2 = "CREATE TABLE NationCordinates ( " +
                        "Origin varchar(40), " +
                        "Xcordinate double, " +
                        "Ycordinate double " +
                        ")";
        
        System.out.println(newTable);
        System.out.println(newTable2);
        accessor.createTable(newTable, "RecipeDb");
        accessor.createTable(newTable2, "RecipeDb");
    }
}
