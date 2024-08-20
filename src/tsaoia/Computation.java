//9-28-2019
//IB ComSci SL 2
//Purpose of class: Contains the search, advanced search, and portion sclaer algorithm
package tsaoia;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

public class Computation
{
     //Connect with the database then put the data into an two dimensional array 'data'
    DatabaseAccess accessor = new DatabaseAccess("RecipeDb");
    Connection myDbConn;
    
    //    columns for the FoodInfo database table
    String[] columnName = {"ID", "Name", "Origin", "Type", "Property1", "Property2"};
    
    String[] columnNameOfNation = {"Origin" , "Xcordinate" ,"Ycordinate"};
    
    //the varaibles which will be accepting the values from FoodRecipe database
    int idV = 0;
    String nameV = "";
    String originV = "";
    String typeV = "";
    String property1V= "";
    String property2V = "";
    //for regular search
    private int targetParameter;
    //for advanced search
    private int[] parameters;
    private String[] choices;
    int searchAspect1;
    int searchAspect2;
    int searchAspect3;
    int searchAspect4;
    
    //getters and setters
    public int[] getParameters()
    {
        return this.parameters;
    }
    
    public void setParameters(int[] parameters)
    {
        this.parameters = parameters;
    }
    
    public String[] getChoices()
    {
        return this.choices;
    }
    
    public void setChoices(String[] choices)
    {
        this.choices = choices;
    }
    
    public int getTargetParameter()
    {
        return targetParameter;
    }

    public void setTargetParameter(int targetParameter)
    {
        this.targetParameter = targetParameter;
    }
    
    public double DistanceCalculator(String country1, String country2, Object[][] nationData)
    {
        //getting a 2 dimentional array of nations and its cordinates
        Object[][] data;
        data = nationData;
        int counter = 1;
        double x1 = 1;
        double y1 = 1;
        double x2 = 1;
        double y2 = 1;
        double xSection = 1;
        double ySection = 1;
        double withinSqRoot = 1;
        double distance = 100;
        
        //To get the value of x1 y1 x2 y2
        for (int row = 0; row <data.length;row++)
        {
            
            //if either country 1 or country 2 is found
            if ( (country1.compareTo(String.valueOf(data[row][0])) == 0) || (country2.compareTo(String.valueOf(data[row][0]))) == 0 )
            {
                if (counter == 1)
                {
                    x1 = Double.parseDouble(String.valueOf(data[row][1]));
                    y1 = Double.parseDouble(String.valueOf(data[row][2]));
                    counter += 1;
                }
                else if (counter == 2)
                {
                    x2 = Double.parseDouble(String.valueOf(data[row][1]));
                    y2 = Double.parseDouble(String.valueOf(data[row][2]));
                }
            }
   
        }
        //DISTANCE FORMULA TO FIND DISTANCE
        //x2 - x1
        xSection = x2 - x1;
        // (x2-x1)^2
        xSection = xSection * xSection;
        //y2 - y1
        ySection = y2 -y1;
        // (y2-y1)^2
        ySection = ySection * ySection;
        //adding the two sections
        withinSqRoot = xSection + ySection;
        //Putting everything under square root
        distance = Math.sqrt(withinSqRoot);

        return distance;
    }
    
    public int[] AdvancedSearch()
    {
        //2 dementional array from database
        myDbConn = accessor.getDbConn();
        Object[][] data = accessor.getData("FoodInfo", columnName);
        
        String originHolder;
        String typeHolder;
        String property1Holder;
        String property2Holder;
        
        int[] resultList = {-1};
        //to store the dish with the best match to user input
        int bestPosition = 0;
        //to determine which dish matches the best to user input
        int score = 0;
        //to record best score
        int bestScore = 0;
        //to hold the distance number of the best match dish
        double bestDistance = 100000;
        //to hold distance
        double distanceNumber;
        //to track how many aspects are inputted
        int amountAspect = 0;
        
        for (int i = 0; i<parameters.length; i++)
        {
            //starting from the beginning of parameter array. Load the user selected parameters.
            //Only load if there is a parameter passed into the algorithm (when position dont equal -1)
            if (i == 0 && parameters[i] != -1)
            {
                //to identify the parameter and store corresponding choice
                if (parameters[i] == 2)
                {
                    searchAspect1 = parameters[i];
                    originV = choices[i];
                }
                if (parameters[i] == 3)
                {
                    searchAspect1 = parameters[i];
                    typeV = choices[i];
                }
                if (parameters[i] == 4)
                {
                    searchAspect1 = parameters[i];
                    property1V = choices[i];
                }
                if (parameters[i] == 5)
                {
                    searchAspect1 = parameters[i];
                    property2V = choices[i];
                }
                amountAspect += 1;   
            }
            
            if (i == 1 && parameters[i] != -1)
            {
                //to identify the parameter and store corresponding choice
                if (parameters[i] == 2)
                {
                    searchAspect2 = parameters[i];
                    originV = choices[i];
                }
                if (parameters[i] == 3)
                {
                    searchAspect2 = parameters[i];
                    typeV = choices[i];
                }
                if (parameters[i] == 4)
                {
                    searchAspect2 = parameters[i];
                    property1V = choices[i];
                }
                if (parameters[i] == 5)
                {
                    searchAspect2 = parameters[i];
                    property2V = choices[i];
                }
                amountAspect += 1;
            }
            
            if (i == 2 && parameters[i] != -1)
            {
                //to identify the parameter and store corresponding choice
                if (parameters[i] == 2)
                {
                    searchAspect3 = parameters[i];
                    originV = choices[i];
                }
                if (parameters[i] == 3)
                {
                    searchAspect3 = parameters[i];
                    typeV = choices[i];
                }
                if (parameters[i] == 4)
                {
                    searchAspect3 = parameters[i];
                    property1V = choices[i];
                }
                if (parameters[i] == 5)
                {
                    searchAspect3 = parameters[i];
                    property2V = choices[i];
                }
                amountAspect += 1;
            }
            
            if (i == 3 && parameters[i] != -1)
            {
                //to identify the parameter and store corresponding choice
                if (parameters[i] == 2)
                {
                    searchAspect4 = parameters[i];
                    originV = choices[i];
                }
                if (parameters[i] == 3)
                {
                    searchAspect4 = parameters[i];
                    typeV = choices[i];
                }
                if (parameters[i] == 4)
                {
                    searchAspect4 = parameters[i];
                    property1V = choices[i];
                }
                if (parameters[i] == 5)
                {
                    searchAspect4 = parameters[i];
                    property2V = choices[i];
                }
                amountAspect += 1;
            }
            
        }
        //to find the best match
        for (int row = 0; row<data.length; row++)
        {
            //score will reset for each row
            score = 0;
            originHolder = String.valueOf(data[row][2]);
            typeHolder = String.valueOf(data[row][3]);
            property1Holder = String.valueOf(data[row][4]);
            property2Holder = String.valueOf(data[row][5]);
            
            Object[][] nationData = accessor.getData("NationCordinates", columnNameOfNation);
            distanceNumber = DistanceCalculator(originV, originHolder, nationData);
            //best match in a situation which all four parameters are selected
            if (amountAspect ==4)
            {
                //if the user input matches a dish. score + 1
                if (distanceNumber == 0)
                {
                    score += 1;
                }
                if (typeHolder.compareTo(typeV) == 0)
                {
                    score += 1;
                }
                if (property1Holder.compareTo(property1V) == 0 || property1Holder.compareTo(property2V) == 0)
                {
                    score +=1;
                }
                if (property2Holder.compareTo(property1V) == 0 || property2Holder.compareTo(property2V) == 0)
                {
                    score += 1;
                }
                //at the end if a particular dish got a higher score than the best matching dish. 
                //that dish becomes the best matching dish. inheriting the best score, distance number, and position
                if (score > bestScore)
                {
                    bestScore = score;
                    bestDistance = distanceNumber;
                    bestPosition = Integer.parseInt(String.valueOf(data[row][0]));
                }
                //at the end if a particular dish got the same score as the best matching dish, distance number will be compared
                //if one dish's distance is closer to user choice, that dish becomes the new best matching dish
                if (score == bestScore)
                {
                    if (distanceNumber < bestDistance)
                    {
                        bestDistance = distanceNumber;
                        bestPosition = Integer.parseInt(String.valueOf(data[row][0]));
                    }
                }
                
            }
            //when there are 3 parameters and choice made by user
            if (amountAspect == 3)
            {

                //since in Advanced search origin input is required and is inputted first, first parameter is country
                if (choices[0].compareTo(String.valueOf(data[row][searchAspect1])) == 0)
                {
                    score += 1;
                }
                if (choices[1].compareTo(String.valueOf(data[row][searchAspect2])) == 0)
                {
                    score += 1;
                }
                if (choices[2].compareTo(String.valueOf(data[row][searchAspect3])) == 0)
                {
                    score += 1;
                }
                //change the best position if there's a better match
                if (score > bestScore)
                {
                    bestScore = score;
                    bestDistance = distanceNumber;
                    bestPosition = Integer.parseInt(String.valueOf(data[row][0]));
                }
                //if tied, compare distance between the countries
                if (score == bestScore)
                {
                    if (distanceNumber < bestDistance)
                    {
                        bestDistance = distanceNumber;
                        bestPosition = Integer.parseInt(String.valueOf(data[row][0]));
                    }
                }
            }
            
            if (amountAspect == 2)
            {
                //since in Advanced search origin input is required and is inputted first, first parameter is country
                if (choices[0].compareTo(String.valueOf(data[row][searchAspect1])) == 0)
                {
                    score += 1;
                }
                if (choices[1].compareTo(String.valueOf(data[row][searchAspect2])) == 0)
                {
                    score += 1;
                }
                //if the current dish matches better than precious best
                if (score > bestScore)
                {
                    //save highest score, and change the bestDistance to that new best match
                    bestScore = score;
                    //saving the id of the current best match
                    bestPosition = Integer.parseInt(String.valueOf(data[row][0]));
                    bestDistance = distanceNumber;
                }
                //if the current dish tie the amount of matches of current best match
                if (score == bestScore)
                {
                    //if the current dish is closer to the country of user's choice
                    if (distanceNumber < bestDistance)
                    {
                        bestDistance = distanceNumber;
                        bestPosition = Integer.parseInt(String.valueOf(data[row][0]));
                    }
                }
            }
            
        }
        //resultList will hold the best matching dish's ID number
        resultList[0] = bestPosition;
        accessor.closeDbConn();
        return resultList;
    }

    
    public int[] Search(String choice)
    {
        String userChoice = "";
        userChoice = choice;
        getTargetParameter();
        
        //to hold the search result
        ArrayList <Integer> temporaryResultList = new ArrayList <> (10);
       
        //the amount of match. This would be used to represent the position 
        //of which values would be populated in an array
        int resultAmount = 0;
       
        myDbConn = accessor.getDbConn();
        Object[][] data = accessor.getData("FoodInfo", columnName);
        accessor.closeDbConn();
        
        //to traverse through 2 demensional array
        for (int row = 0; row<data.length; row++)
        {
            //getting the id number of each row so the id of the matching dish can be saved
            idV = Integer.parseInt(String.valueOf(data[row][0]));
            
            if (getTargetParameter() == 1)
            {
                nameV = String.valueOf(data[row][1]);
                if ( (userChoice.compareToIgnoreCase(nameV)) == 0 )
                {
                    temporaryResultList.add(idV);
                }
            }
            
            else if (getTargetParameter() == 2)
            {
                originV = String.valueOf(data[row][2]);
                //if the user choice mathes item in database
                if ( (userChoice.compareTo(originV)) == 0 )
                {
                    temporaryResultList.add(idV);
                }
            }
            
            else if (getTargetParameter() == 3)
            {
                typeV = String.valueOf(data[row][3]);
                //if the user choice mathes item in database
                if ( (userChoice.compareTo(typeV)) == 0 )
                {
                    temporaryResultList.add(idV);
                }
            }
            
            else if (getTargetParameter() == 4)
            {
                property1V = String.valueOf(data[row][4]);
                property2V = String.valueOf(data[row][5]);
                //if the user choice matches item in database
                if ( (userChoice.compareTo(property1V)) == 0 )
                {
                    temporaryResultList.add(idV);
                }
                else if ( (userChoice.compareTo(property2V)) == 0 )
                {
                    temporaryResultList.add(idV);
                }
            }
            
        }
        
        //the array size is set to the size of the arraList so it can display all the matches
            int[] resultList = new int[temporaryResultList.size()];
            Iterator<Integer> arrayIterator;
            arrayIterator = temporaryResultList.iterator();
            
        if (temporaryResultList.isEmpty() == false)
        {
           
            for (int i = 0; i<temporaryResultList.size(); i++)
            {
                if (arrayIterator.hasNext())
                {
                    resultList[i] = arrayIterator.next();
                    //System.out.println(resultList[i]);
                }
            }
        }
            
        else
        {
            //setting first position of array to -1 to the result frame will know that there is no match found
            int[] emptyList = {-1};
            resultList = emptyList;
        }
        
        return resultList;   
    }
    
    public static void main(String[] args)
    {
//        Computation tool = new Computation();
//        System.out.println("Distance btw us and jp: " + tool.DistanceCalculator("United States", "Japan"));
    }
    
}
