/*==============================||
|| Christopher Williams         ||
|| Database Management Project  ||
|| Java to Database program     ||
||==============================*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.*;

public class DatabaseProjectProgram
{
   public static Scanner input = new Scanner (System.in);
   public static Statement statement;
   public static Connection connection = null; 
   public static ResultSet rs;
   
   public static void main(String[]args) throws Exception
   {
      try
      {
        
    	 //TODO: Insert your MySQL Url here with username and password. We used Phpmyadmin
         connection = DriverManager.getConnection("");
         statement = connection.createStatement();
         
         System.out.println();
         System.out.println("You have just logged into Christopher Williams' Database about Pokemon!\n");
         System.out.println("For helpful information on adding information to the database, please use these websites: ");
         System.out.println("A document that displays the default infor in the tables. This is useful for referencing information");
         System.out.println("https://tinyurl.com/yyexq26w");
         //Or https://docs.google.com/spreadsheets/d/1eUHt7FJL6vyP1wrIdTMP5TUU2WNZgvUHNU2SZZK-Bh4/edit?usp=sharing
         //in case the shorter link doesnt work.
         System.out.println("Serebii.net is an amazing website for the Pokemon games.");
         System.out.println("https://www.serebii.net\n");
         
         menu();
         
         //I folded most of my methods because it saved time working on this program
         //You'll probably see the pluses you can double click, but its all there.
         
      }catch(SQLException sqle){
         System.out.println("SQLException: " + sqle.getMessage() );
         System.out.println("SQLState: "+ sqle.getSQLState() );
         System.out.println("VendorError: "+sqle.getErrorCode() );
      }catch(Exception e){
         System.out.println("Something else went wrong.");
      }
      finally
      {
         rs.close();
         statement.close();
         connection.close();
      }
   }
   
   public static void menu() throws Exception
   {
      int choice = 0;
      do
      {
         System.out.println("\nWhat would you like to do?");
         System.out.println("Press 1 for SELECT (Retrieve information from your database.)");
         System.out.println("Press 2 for INSERT (Push things into a new row in your database.)");
         System.out.println("Press 3 for UPDATE (Update certain values in your database.)");
         System.out.println("Press 4 for DELETE (Remove something from your database.)");
         System.out.println("Press 5 to do nothing");
         System.out.print("Please enter your number: ");
         
         choice = input.nextInt();
         while(choice <= 0 || choice >= 6)
         {
            System.out.print("\nThat is not one of the choices. Try again: ");
            choice = input.nextInt();
         }  
         if(choice == 1)
            select();
         else if(choice == 2)
            insert();
         else if(choice == 3)
            update();
         else if(choice == 4)
            delete(); // Print everything, then tell user to picn an id to delete
            
      }while(choice != 5);
      System.out.println("\nThank you for visiting Christopher Williams' Pokemon Database!");
   }// End menu()
   
   public static void select() throws Exception
   {
      System.out.println("\n\nYou are now selecting from the database!");
      int choice = 0;
      int choice2 = 0;
      do
      {   
         System.out.println("\nWhat table do you want to pull information from?");
         System.out.println("Enter 1 for Pokemon (IDs, Names, Stats)");
         System.out.println("Enter 2 for Moves (IDs, Names, Stats, and Descriptions)");
         System.out.println("Enter 3 for Trainers (IDs, Trainers, and their locations)");
         System.out.println("Enter 4 for Locations (IDs and Locations only)");
         System.out.println("Enter 5 for Pokemon and Moves (Pokemon knowing certain moves)");
         System.out.println("Enter 6 for Pokemon and Trainers (Pokemon being owned by certain people)");
         System.out.println("Enter 7 for Pokemon and Locations (Pokemon located on certain routes)");
         System.out.println("Enter 8 to exit");
         System.out.print("Please enter your choice: ");
         choice = input.nextInt();
         while(choice > 8 ||choice < 1)
         {
            System.out.print("That is not one of the choices. Try again: ");
            choice = input.nextInt();
         }  
         System.out.println();
         String tables;   
         String valueS;
         int valueI;
         
         if(choice == 1)// If Pokemon
         {
            String[] tableAttrTop = {"pokemon_id     ||","pokemon_name   ||","pokemon_typeOne||","pokemon_typeTwo||","pokemon_hp     ||","pokemon_atk    ||","pokemon_def    ||","pokemon_spAtk  ||","pokemon_spDef  ||","pokemon_speed  ||","pokemon_total  ||"};
            String[] tableAttr = {"pokemon_id","pokemon_name","pokemon_typeOne","pokemon_typeTwo","pokemon_hp","pokemon_atk","pokemon_def","pokemon_spAtk","pokemon_spDef","pokemon_speed","pokemon_total"};
            System.out.println("What details do you want to pull from this table specifically?");
            System.out.println("Enter 1 for ID number");
            System.out.println("Enter 2 for Name");
            System.out.println("Enter 3 for First type");
            System.out.println("Enter 4 for Second type");
            System.out.println("Enter 5 for Hp stats");
            System.out.println("Enter 6 for Attack stats");
            System.out.println("Enter 7 for Defence stats");
            System.out.println("Enter 8 for Special Attack stats");
            System.out.println("Enter 9 for Special Defence stats");
            System.out.println("Enter 10 for Speed stats");
            System.out.println("Enter 11 for Total stats");
            System.out.println("Enter 12 for Everything");
            System.out.print("Enter -1 to end the loop and continue.\nTry to avoid entering duplicates: ");
            int choice3 = input.nextInt();
            while(choice3 > 12||choice3 < 1)
            {
               System.out.print("\nPlease choose at least one value before continuing: ");
               choice3 = input.nextInt();
            }
            int count = 1;
            int c3i = 0;
            int[] list = new int[11];
            list[c3i] = choice3;
         
            if(choice3 == 12)// If Everything
            {
               count = 11;
               System.out.print("\nIn what order? Press 1 for default order and 2 for a custom order: ");
               int choice4 = input.nextInt();
               
               if(choice4 == 1)
                  for(int i = 0; i < list.length; i++)
                     list[i] = i + 1;
               else
               {
                  System.out.println("\nOrder the numbers 1 to 11 in your own order: ");
                  // Assuming the user doesn't put duplicates;
                  int order = 0;   
                     for(int i = 0; i < list.length; i++)
                     {
                        order = input.nextInt();
                        list[i] = order;
                     }
               }
            }// End if Everything 
            else
            {
               while((choice3 <= 11 || choice3 >= 0) && choice3 != -1)// While the user is choosing their values to see
               {
                  choice3 = input.nextInt();
                  if(choice3 > 11||choice3 < 1 )
                     while((choice3 > 11 || choice3 < 1) && choice3 != -1 && choice3 != -2)
                     {
                        System.out.println("\nThat is not one of the values. Please try again: ");
                        choice3 = input.nextInt();
                     }
                  
                  if(choice3 == -1)
                     break; // Sorry but I gotta do this.
                  else
                  {
                     if(count >= tableAttr.length)
                        break; // It's easier this way :p
                     else
                     {
                        count++;
                        c3i++;
                        list[c3i] = choice3;
                     }
                  }
               }// End choosing values
            }  
            
            tables = "Pokemon";
            System.out.print("\nAre you searching for a Pokemon by ID, by name, or by type?\nEnter 1 for ID, 2 for name, and 3 for type: ");
            choice2 = input.nextInt();
               
            if(choice2 == 1)// If Pokemon ID
            {
               System.out.print("\nPlease enter the Pokemon ID number: ");
               valueI = input.nextInt();
               
               String select = "SELECT ";
               
               for(int i = 0; i < count-1; i++)
               {
                  list[i]--;
                  select = select + tableAttr[list[i]];
                  select = select + ",";
               }
               list[count-1]--;
               select = select + tableAttr[list[count-1]];
               select = select + " ";
               
               String from = "FROM "+tables;
               
               String where = " WHERE pokemon_id = "+valueI;
               
               String sql = select + from + where;
               
               System.out.println("\nThis is your sql statement: "+sql+"\n");
               
               rs = statement.executeQuery(sql);
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               rs.beforeFirst(); 
               
               String[] result = new String[count];
               System.out.println("Pokemon Table");
               
               borderMaker(count);
               
               System.out.print("||");
               
               for(int i = 0; i < count; i++)
               {
                  System.out.print(tableAttrTop[list[i]]);
               }
               System.out.println();
               borderMaker(count);
               
               while(rs.next())
               {
                  for(int i = 0; i < result.length; i++)
                  {
                     if(list[i] == 0)
                     {
                        int id = rs.getInt("pokemon_id");
                        result[i] = id+"";
                     }
                     else if(list[i] == 1)
                        result[i] = rs.getString("pokemon_name");
                     else if(list[i] == 2)
                        result[i] = rs.getString("pokemon_typeOne");
                     else if(list[i] == 3)
                        result[i] = rs.getString("pokemon_typeTwo");
                     else if(list[i] == 4)
                     {
                        int hp = rs.getInt("pokemon_hp");
                        result[i] = hp + "";
                     }
                     else if(list[i] == 5)
                     {   
                        int atk = rs.getInt("pokemon_atk");
                        result[i] = atk + "";
                     }
                     else if(list[i] == 6)
                     {   
                        int def = rs.getInt("pokemon_def");
                        result[i] = def + "";
                     }
                     else if(list[i] == 7)
                     {     
                        int spAtk = rs.getInt("pokemon_spAtk");
                        result[i] = spAtk + "";
                     }
                     else if(list[i] == 8)
                     {
                        int spDef = rs.getInt("pokemon_spDef");
                        result[i] = spDef + "";
                     }
                     else if(list[i] == 9)
                     {
                        int speed = rs.getInt("pokemon_speed");
                        result[i] = speed + "";
                     }
                     else if(list[i] == 10) 
                     {
                        int total = rs.getInt("pokemon_total");
                        result[i] = total + "";
                     }
                  }
                  
               }  
                  
               for(int i = 0; i < result.length; i++)
               {
                  while(result[i].length() < 15)
                     result[i] = result[i] + " ";
               }
               
               for(int i = 0; i < result.length; i++)
               {
                  System.out.print("||");
                  System.out.print(result[i]);
               }   
               System.out.print("||\n");
               
               borderMaker(count);           
            
            }// End if Pokemon ID
            else if(choice2 == 2)// Else if Pokemon Name
            {
               System.out.print("\nPlease enter the Pokemon name: ");
               valueS = input.next();
               valueS = addQuotes(valueS);
               
               String select = "SELECT ";
               
               for(int i = 0; i < count-1; i++)
               {
                  list[i]--;
                  select = select + tableAttr[list[i]];
                  select = select + ",";
               }
               list[count-1]--;
               select = select + tableAttr[list[count-1]];
               select = select + " ";
               
               String from = "FROM "+tables;
               
               String where = " WHERE pokemon_name = "+valueS;
               
               String sql = select + from + where;

               
               System.out.println("\nThis is your sql statement: "+sql+"\n");
               
               rs = statement.executeQuery(sql);
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               rs.beforeFirst(); 
               
               String[] result = new String[count];
               System.out.println("Pokemon Table");
               
               borderMaker(count);
               
               System.out.print("||");
               
               for(int i = 0; i < count; i++)
               {
                  System.out.print(tableAttrTop[list[i]]);
               }
               System.out.println();
               borderMaker(count);
               
               while(rs.next())
               {
                  for(int i = 0; i < result.length; i++)
                  {
                     if(list[i] == 0)
                     {
                        int id = rs.getInt("pokemon_id");
                        result[i] = id+"";
                     }
                     else if(list[i] == 1)
                        result[i] = rs.getString("pokemon_name");
                     else if(list[i] == 2)
                        result[i] = rs.getString("pokemon_typeOne");
                     else if(list[i] == 3)
                        result[i] = rs.getString("pokemon_typeTwo");
                     else if(list[i] == 4)
                     {
                        int hp = rs.getInt("pokemon_hp");
                        result[i] = hp + "";
                     }
                     else if(list[i] == 5)
                     {   
                        int atk = rs.getInt("pokemon_atk");
                        result[i] = atk + "";
                     }
                     else if(list[i] == 6)
                     {   
                        int def = rs.getInt("pokemon_def");
                        result[i] = def + "";
                     }
                     else if(list[i] == 7)
                     {     
                        int spAtk = rs.getInt("pokemon_spAtk");
                        result[i] = spAtk + "";
                     }
                     else if(list[i] == 8)
                     {
                        int spDef = rs.getInt("pokemon_spDef");
                        result[i] = spDef + "";
                     }
                     else if(list[i] == 9)
                     {
                        int speed = rs.getInt("pokemon_speed");
                        result[i] = speed + "";
                     }
                     else if(list[i] == 10) 
                     {
                        int total = rs.getInt("pokemon_total");
                        result[i] = total + "";
                     }
                  }
                  
               }  
                  
               for(int i = 0; i < result.length; i++)
               {
                  while(result[i].length() < 15)
                     result[i] = result[i] + " ";
               }
               
               for(int i = 0; i < result.length; i++)
               {
                  System.out.print("||");
                  System.out.print(result[i]);
               }   
               System.out.print("||\n");
               
               borderMaker(count);           
            }// End if Pokemon Name
            else if(choice2 == 3)// If Pokemon type
            {
               System.out.print("\nAre you looking for it's first type or second type?\nEnter 1 for first type and 2 for second type: ");
               choice2 = input.nextInt();
               if(choice2 == 1)// If Pokemon first type
               {
                  System.out.print("\nPlease enter the typing you are looking for: ");
                  String type = input.next();
                  System.out.println("\nDo you want to see Pokemon who are pure "+type+" or do you want to see Pokemon who are type "+type+" and something else?");
                  System.out.print("Please enter 1 for pure and 2 for non-pure: ");
                  choice2 = input.nextInt();
                  if(choice2 == 1)// If Type one, no second type
                  {
                     type = addQuotes(type);
                     
                     String select = "SELECT ";
                     
                     for(int i = 0; i < count-1; i++)
                     {
                        list[i]--;
                        select = select + tableAttr[list[i]];
                        select = select + ",";
                     }  
                     list[count-1]--;
                     select = select + tableAttr[list[count-1]];
                     select = select + " ";
                     
                     String from = "FROM "+tables;
                     
                     String where = " WHERE pokemon_typeOne = "+type;
                     
                     String and = " AND pokemon_typeTwo = '---'";
                     
                     String orderBy = "";
                     
                     String ascDesc = "";
               
                     System.out.println("\nDo you want to sort this?");
                     System.out.print("Enter 1 to yes and 2 for no: ");
                     int sort = input.nextInt();
                     if(sort == 1)
                     {
                        orderBy = pokemonOrderBy(tableAttr);
                        ascDesc = ascDescMethod();
                     }
                     String sql = select + from + where + and + orderBy + ascDesc;
                           
                     System.out.println("\nThis is your sql statement: "+sql+"\n");
                     
                     rs = statement.executeQuery(sql);
                     if (!rs.first()) 
                     {
                        System.out.println("That entry either does not exist or the table is empty");
                        continue;
                     }
                     rs.beforeFirst(); 
                     
                     int size = 0;
                     while(rs.next())
                        size++;
                        
                     rs.beforeFirst();
                     String[][] result = new String[size][count];
                    
                     System.out.println("Pokemon table");
                     borderMaker(count);                     
                     System.out.print("||");
                     
                     for(int i = 0; i < count; i++)
                     {
                        System.out.print(tableAttrTop[list[i]]);
                     }
                     System.out.println();
                     borderMaker(count);
                     
                     for(int i = 0; i < result.length && rs.next(); i++)
                     {
                        for(int j = 0; j < result[i].length; j++)
                        {
                           if(list[j] == 0)
                           {
                              int id = rs.getInt("pokemon_id");
                              result[i][j] = id+"";
                           }
                           else if(list[j] == 1)
                              result[i][j] = rs.getString("pokemon_name");
                           
                           else if(list[j] == 2)
                              result[i][j] = rs.getString("pokemon_typeOne");
                           
                           else if(list[j] == 3)
                              result[i][j] = rs.getString("pokemon_typeTwo");
                              
                           else if(list[j] == 4)
                           {
                              int hp = rs.getInt("pokemon_hp");
                              result[i][j] = hp + "";
                           }
                           else if(list[j] == 5)
                           {
                              int atk = rs.getInt("pokemon_atk");
                              result[i][j] = atk + "";
                           }
                           else if(list[j] == 6)
                           {
                              int def = rs.getInt("pokemon_def");
                              result[i][j] = def + "";
                           }
                           else if(list[j] == 7)
                           {
                              int spAtk = rs.getInt("pokemon_spAtk");
                              result[i][j] = spAtk + "";;
                           }
                           else if(list[j] == 8)
                           {
                              int spDef = rs.getInt("pokemon_spDef");
                              result[i][j] = spDef + "";
                           }
                           else if(list[j] == 9)
                           {
                              int speed = rs.getInt("pokemon_speed");
                              result[i][j] = speed + "";
                           }
                           else if(list[j] == 10)
                           {
                              int total = rs.getInt("pokemon_total");
                              result[i][j] = total + "";
                           }
                        }                           
                     }
                     
                     for(int i = 0; i < result.length; i++)
                     {
                        for(int j = 0; j < result[i].length; j++)
                              while(result[i][j].length() < 15)
                                 result[i][j] = result[i][j] + " ";
                     }
                     
                     for(int i = 0; i < result.length; i++)
                     {
                        for(int j = 0; j < result[i].length; j++)
                        {
                           System.out.print("||");
                           System.out.print(result[i][j]);
                        }
                        System.out.println("||");
                     }   
                     borderMaker(count);
                     
                     
                  }// End Type one, no second type
                  else // Type one and type two
                  {
                     type = addQuotes(type);
                     
                     String select = "SELECT ";
                     
                     for(int i = 0; i < count-1; i++)
                     {
                        list[i]--;
                        select = select + tableAttr[list[i]];
                        select = select + ",";
                     }  
                     list[count-1]--;
                     select = select + tableAttr[list[count-1]];
                     select = select + " ";
                     
                     String from = "FROM "+tables;
                     
                     String where = " WHERE pokemon_typeOne = " + type;
                     
                     String orderBy = "";
                     
                     String ascDesc = "";
               
                     System.out.println("\nDo you want to sort this?");
                     System.out.print("Enter 1 to yes and 2 for no: ");
                     int sort = input.nextInt();
                     if(sort == 1)
                     {
                        orderBy = pokemonOrderBy(tableAttr);
                        ascDesc = ascDescMethod();
                     }
                     String sql = select + from + where + orderBy + ascDesc;
                           
                     System.out.println("\nThis is your sql statement: "+sql+"\n");
                     
                     rs = statement.executeQuery(sql);
                     if (!rs.first()) 
                     {
                        System.out.println("That entry either does not exist or the table is empty");
                        continue;
                     }
                     rs.beforeFirst(); 
                     
                     int size = 0;
                     while(rs.next())
                        size++;
                     rs.beforeFirst();
                     String[][] result = new String[size][count];
                    
                     System.out.println("Pokemon table");
                     borderMaker(count);                     
                     System.out.print("||");
                     
                     for(int i = 0; i < count; i++)
                     {
                        System.out.print(tableAttrTop[list[i]]);
                     }
                     System.out.println();
                     borderMaker(count);
                     
                     for(int i = 0; i < result.length && rs.next(); i++)
                     {
                        for(int j = 0; j < result[i].length; j++)
                        {
                           if(list[j] == 0)
                           {
                              int id = rs.getInt("pokemon_id");
                              result[i][j] = id+"";
                           }
                           else if(list[j] == 1)
                              result[i][j] = rs.getString("pokemon_name");
                           
                            else if(list[j] == 2)
                               result[i][j] = rs.getString("pokemon_typeOne");
                            
                            else if(list[j] == 3)
                               result[i][j] = rs.getString("pokemon_typeTwo");
                               
                            else if(list[j] == 4)
                            {
                               int hp = rs.getInt("pokemon_hp");
                               result[i][j] = hp + "";
                            }
                            else if(list[j] == 5)
                            {
                               int atk = rs.getInt("pokemon_atk");
                               result[i][j] = atk + "";
                            }
                            else if(list[j] == 6)
                            {
                               int def = rs.getInt("pokemon_def");
                               result[i][j] = def + "";
                            }
                            else if(list[j] == 7)
                            {
                               int spAtk = rs.getInt("pokemon_spAtk");
                               result[i][j] = spAtk + "";;
                            }
                            else if(list[j] == 8)
                            {
                               int spDef = rs.getInt("pokemon_spDef");
                               result[i][j] = spDef + "";
                            }
                            else if(list[j] == 9)
                            {
                               int speed = rs.getInt("pokemon_speed");
                               result[i][j] = speed + "";
                            }
                            else if(list[j] == 10)
                            {
                               int total = rs.getInt("pokemon_total");
                               result[i][j] = total + "";
                            }
                         }                           
                      }
                     
                      for(int i = 0; i < result.length; i++)
                      {
                         for(int j = 0; j < result[i].length; j++)
                               while(result[i][j].length() < 15)
                                 result[i][j] = result[i][j] + " ";
                     }
                     
                     for(int i = 0; i < result.length; i++)
                     {
                        for(int j = 0; j < result[i].length; j++)
                        {
                           System.out.print("||");
                           System.out.print(result[i][j]);
                        }
                        System.out.println("||");
                     }   
                     borderMaker(count);
                  }// End Type one and type two
               }// End Pokemon First Type
               else // Type two with type one
               {   
                  System.out.print("Please enter the typing you are looking for: ");
                  String type = input.next();
                  type = addQuotes(type);
                     
                  String select = "SELECT ";
                  
                  for(int i = 0; i < count-1; i++)
                  {
                     list[i]--;
                     select = select + tableAttr[list[i]];
                     select = select + ",";
                  }  
                  list[count-1]--;
                  select = select + tableAttr[list[count-1]];
                  select = select + " ";
                  
                  String from = "FROM "+tables;
                  
                  String where = " WHERE pokemon_typeTwo = "+type;
                  
                  String orderBy = "";
                  
                  String ascDesc = "";
                  System.out.println("\nDo you want to sort this?");
                  System.out.print("Enter 1 to yes and 2 for no: ");
                  int sort = input.nextInt();
                  System.out.println();
                  if(sort == 1)
                  {
                     orderBy = pokemonOrderBy(tableAttr);
                     ascDesc = ascDescMethod();
                  }
                  String sql = select + from + where + orderBy + ascDesc;
                      
                  System.out.println("\nThis is your sql statement: "+sql+"\n");
                  
                  rs = statement.executeQuery(sql);
                  if (!rs.first()) 
                  {
                     System.out.println("That entry either does not exist or the table is empty");
                     continue;
                  } 
                  rs.beforeFirst(); 
                  
                  int size = 0;
                  while(rs.next())
                     size++;
                  rs.beforeFirst();
                  
                  String[][] result = new String[size][count];
                   
                  System.out.println("Pokemon table");
                  borderMaker(count);                     
                  System.out.print("||");
                  
                  for(int i = 0; i < count; i++)
                  {
                     System.out.print(tableAttrTop[list[i]]);
                  }
                  System.out.println();
                  borderMaker(count);
                    
                  for(int i = 0; i < result.length && rs.next(); i++)
                  {
                     for(int j = 0; j < result[i].length; j++)
                     {
                        if(list[j] == 0)
                        {
                           int id = rs.getInt("pokemon_id");
                           result[i][j] = id+"";
                        }
                        else if(list[j] == 1)
                           result[i][j] = rs.getString("pokemon_name");
                         
                        else if(list[j] == 2)
                           result[i][j] = rs.getString("pokemon_typeOne");
                       
                        else if(list[j] == 3)
                           result[i][j] = rs.getString("pokemon_typeTwo");
                           
                        else if(list[j] == 4)
                        {
                           int hp = rs.getInt("pokemon_hp");
                           result[i][j] = hp + "";
                        }
                        else if(list[j] == 5)
                        {
                           int atk = rs.getInt("pokemon_atk");
                           result[i][j] = atk + "";
                        }
                        else if(list[j] == 6)
                        {
                           int def = rs.getInt("pokemon_def");
                           result[i][j] = def + "";
                        }
                        else if(list[j] == 7)
                        {
                           int spAtk = rs.getInt("pokemon_spAtk");
                           result[i][j] = spAtk + "";;
                        }
                        else if(list[j] == 8)
                        {
                           int spDef = rs.getInt("pokemon_spDef");
                           result[i][j] = spDef + "";
                        }
                        else if(list[j] == 9)
                        {
                           int speed = rs.getInt("pokemon_speed");
                           result[i][j] = speed + "";
                        }
                        else if(list[j] == 10)
                        {
                           int total = rs.getInt("pokemon_total");
                           result[i][j] = total + "";
                        }
                     }                            
                  }                        
                  for(int i = 0; i < result.length; i++)
                  {
                     for(int j = 0; j < result[i].length; j++)
                        while(result[i][j].length() < 15)
                           result[i][j] = result[i][j] + " ";
                  }
                  
                  for(int i = 0; i < result.length; i++)
                  {
                     for(int j = 0; j < result[i].length; j++)
                     {
                        System.out.print("||");
                        System.out.print(result[i][j]);
                     }
                     System.out.println("||");
                  }   
                  borderMaker(count);
               }// End Pokemon Second type
            }// End Pokemon type
         }// End if Pokemon
         else if(choice == 2)// If Moves
         {
            String[] tableAttrTop = {"move_id        ||","move_name      ||","move_type      ||","move_pp        ||","move_power     ||","move_accuracy  ||","move_desc                                                                                                               ||"};
            String[] tableAttr = {"move_id","move_name","move_type","move_pp","move_power","move_accuracy","move_desc"};
            System.out.println("What details do you want to pull from this table specifically?");
            System.out.println("Enter 1 for ID number");
            System.out.println("Enter 2 for Name");
            System.out.println("Enter 3 for Type");
            System.out.println("Enter 4 for Power Points");
            System.out.println("Enter 5 for Move Power Stats");
            System.out.println("Enter 6 for Move Accuracy Stats");
            System.out.println("Enter 7 for Move Description");
            System.out.println("Enter 8 for Everything");
            System.out.print("Enter -1 to end the loop and continue.\nTry to avoid entering duplicates: ");
            int choice3 = input.nextInt();
            while(choice3 > 8||choice3 < 1)
            {
               System.out.print("\nPlease choose at least one value before continuing: ");
               choice3 = input.nextInt();
            }
            int count = 1;
            int c3i = 0;
            int[] list = new int[7];
            list[c3i] = choice3;
            
            if(choice3 == 8)// If Everything
            {
               count = 7;
               System.out.print("\nIn what order? Press 1 for default order and 2 for a custom order: ");
               int choice4 = input.nextInt();
               
               if(choice4 == 1)
                  for(int i = 0; i < list.length; i++)
                     list[i] = i + 1;
               else
               {
                  System.out.println("\nOrder the numbers 1 to 7 in your own order: ");
                  int order = 0;   
                  for(int i = 0; i < list.length; i++)
                  {
                     order = input.nextInt();
                     list[i] = order;
                  }
               }
            }// End if Everything 
            else
            {
               while((choice3 <= 8 || choice3 >= 0) && choice3 != -1)
               {
                  choice3 = input.nextInt();
                  if(choice3 > 8||choice3 < 1 )
                     while((choice3 > 8 || choice3 < 1) && choice3 != -1 && choice3 != -2)
                     {
                        System.out.println("\nThat is not one of the values. Please try again: ");
                        choice3 = input.nextInt();
                     }
                  
                  if(choice3 == -1)
                     break;
                  else
                  {
                     if(count >= tableAttr.length)
                        break;
                     else
                     {
                        count++;
                        c3i++;
                        list[c3i] = choice3;
                     }
                  }
               }// End choosing values
            }  
            
            tables = "Moves";
            System.out.print("\nAre you searching for a move by ID, by name, or by type?\nEnter 1 for ID, 2 for name, and 3 for type: ");
            choice2 = input.nextInt();
            
            if(choice2 == 1)// If Moves ID
            {
               System.out.print("\nPlease enter the move ID number: ");
               valueI = input.nextInt();
               
               String select = "SELECT ";
               
               for(int i = 0; i < count-1; i++)
               {
                  list[i]--;
                  select = select + tableAttr[list[i]];
                  select = select + ",";
               }
               list[count-1]--;
               select = select + tableAttr[list[count-1]];
               select = select + " ";
               
               String from = "FROM "+tables;
               
               String where = " WHERE move_id = "+valueI;
               
               String sql = select + from + where;
               
               System.out.println("\nThis is your sql statement: "+sql+"\n");
               
               rs = statement.executeQuery(sql);
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               rs.beforeFirst(); 
               
               String[] result = new String[count];
               System.out.println("Moves Table");
               
               boolean moveDescCheck = false;
               for(int i = 0; i < list.length; i++)
                  if(list[i] == 6)
                     moveDescCheck = true;
               
               borderMakerDesc(count, moveDescCheck);
               
               System.out.print("||");
               
               for(int i = 0; i < count; i++)
               {
                  System.out.print(tableAttrTop[list[i]]);
               }
               System.out.println();
               borderMakerDesc(count, moveDescCheck);
               
               while(rs.next())
               {
                  for(int i = 0; i < result.length; i++)
                  {
                     if(list[i] == 0)
                     {
                        int id = rs.getInt("move_id");
                        result[i] = id+"";
                     }
                     else if(list[i] == 1)
                        result[i] = rs.getString("move_name");
                     else if(list[i] == 2)
                        result[i] = rs.getString("move_type");
                     else if(list[i] == 3)
                     {
                        int pp = rs.getInt("move_pp");
                        result[i] = pp + "";
                     }
                     else if(list[i] == 4)
                     {
                        int power = rs.getInt("move_power");
                        result[i] = power + "";
                     }
                     else if(list[i] == 5)
                     {   
                        int acc = rs.getInt("move_accuracy");
                        result[i] = acc + "";
                     }
                     else if(list[i] == 6)
                        result[i] = rs.getString("move_desc");
                  }              
               }  
               
               for(int i = 0; i < result.length; i++)
               {
                  while(result[i].length() < 15)
                  result[i] = result[i] + " ";
               }
               
               for(int i = 0; i < result.length; i++)
                  if(list[i] == 6)
                     while(result[i].length() < 120)
                        result[i] = result[i] + " ";
               
               for(int i = 0; i < result.length; i++)
               {
                  System.out.print("||");
                  System.out.print(result[i]);
               }   
               System.out.print("||\n");
               
               borderMakerDesc(count, moveDescCheck);           
               
            }// End if Moves ID
            else if(choice2 == 2)// Else if Moves Name
            {
               System.out.print("\nPlease enter the move name: ");
               input.nextLine();
               valueS = input.nextLine();
               valueS = addQuotes(valueS);
               
               String select = "SELECT ";
               
               for(int i = 0; i < count-1; i++)
               {
                  list[i]--;
                  select = select + tableAttr[list[i]];
                  select = select + ",";
               }
               list[count-1]--;
               select = select + tableAttr[list[count-1]];
               select = select + " ";
               
               String from = "FROM "+tables;
               
               String where = " WHERE move_name = "+valueS;
   
               String sql = select + from + where;     
               
               System.out.println("\nThis is your sql statement: "+sql+"\n");
               
               rs = statement.executeQuery(sql);
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               rs.beforeFirst(); 
               
               String[] result = new String[count];
               System.out.println("Moves Table");
               
               boolean moveDescCheck = false;
               for(int i = 0; i < list.length; i++)
                  if(list[i] == 6)
                     moveDescCheck = true;
                     
               borderMakerDesc(count, moveDescCheck);
               
               System.out.print("||");
   
               for(int i = 0; i < count; i++)
               {
                  System.out.print(tableAttrTop[list[i]]);
               }
               System.out.println();
               borderMakerDesc(count, moveDescCheck);
   
               while(rs.next())
               {
                  for(int i = 0; i < result.length; i++)
                  {
                     if(list[i] == 0)
                     {
                        int id = rs.getInt("move_id");
                        result[i] = id+"";
                     }
                     else if(list[i] == 1)
                        result[i] = rs.getString("move_name");
                     else if(list[i] == 2)
                        result[i] = rs.getString("move_type");
                     else if(list[i] == 3)
                     {
                        int pp = rs.getInt("move_pp");
                        result[i] = pp + "";
                     }
                     else if(list[i] == 4)
                     {
                        int power = rs.getInt("move_power");
                        result[i] = power + "";
                     }
                     else if(list[i] == 5)
                     {   
                        int acc = rs.getInt("move_accuracy");
                        result[i] = acc + "";
                     }
                     else if(list[i] == 6)
                        result[i] = rs.getString("move_desc");
                  }              
               }  
               
               for(int i = 0; i < result.length; i++)
               {
                  while(result[i].length() < 15)
                  result[i] = result[i] + " ";
               }
                                            
               for(int i = 0; i < result.length; i++)
                  if(list[i] == 6)
                     while(result[i].length() < 120)
                        result[i] = result[i] + " ";
               
               for(int i = 0; i < result.length; i++)
               {
                  System.out.print("||");
                  System.out.print(result[i]);
               }   
               System.out.print("||\n");
               
               borderMakerDesc(count, moveDescCheck);           
            }// End if Moves Name 
            else if(choice2 == 3)// If Moves type
            {
               System.out.print("\nPlease enter the typing you are looking for: ");
               String type = input.next();
               type = addQuotes(type);
                  
               String select = "SELECT ";
      
               for(int i = 0; i < count-1; i++)
               {
                  list[i]--;
                  select = select + tableAttr[list[i]];
                  select = select + ",";
               }  
               list[count-1]--;
               select = select + tableAttr[list[count-1]];
               select = select + " ";
                  
               String from = "FROM "+tables;
                  
               String where = " WHERE move_type = "+type;
                  
               String orderBy = "";
                  
               String ascDesc = "";
      
               System.out.println("\nDo you want to sort this?");
               System.out.print("Enter 1 to yes and 2 for no: ");
               int sort = input.nextInt();
               if(sort == 1)
               {
                  orderBy = movesOrderBy(tableAttr);
                  ascDesc = ascDescMethod();
               }
               String sql = select + from + where + orderBy + ascDesc;
               System.out.println("\nThis is your sql statement: "+sql+"\n");
               
               rs = statement.executeQuery(sql);
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               rs.beforeFirst(); 
               
               int size = 0;
               while(rs.next())
                  size++;
               
               rs.beforeFirst();
               String[][] result = new String[size][count];
               
               System.out.println("Moves table");
               
               boolean moveDescCheck = false;
               for(int i = 0; i < list.length; i++)
                  if(list[i] == 6)
                     moveDescCheck = true;
               
               borderMakerDesc(count, moveDescCheck);                     
               System.out.print("||");
               
               for(int i = 0; i < count; i++)
               {
                  System.out.print(tableAttrTop[list[i]]);
               }
               System.out.println();
               
               borderMakerDesc(count, moveDescCheck);
               
               for(int i = 0; i < result.length && rs.next(); i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                  {
                     if(list[j] == 0)
                     {
                        int id = rs.getInt("move_id");
                        result[i][j] = id+"";
                     }
                     else if(list[j] == 1)
                        result[i][j] = rs.getString("move_name");
                         else if(list[j] == 2)
                        result[i][j] = rs.getString("move_type");
               
                     else if(list[j] == 3)
                     {
                        int pp = rs.getInt("move_pp");
                        result[i][j] = pp + "";
                     }
                     else if(list[j] == 4)
                     {
                        int power = rs.getInt("move_power");
                        result[i][j] = power + "";
                     }
                        else if(list[j] == 5)
                     {
                        int acc = rs.getInt("move_accuracy");
                        result[i][j] = acc + "";
                     }
                     else if(list[j] == 6)
                        result[i][j] = rs.getString("move_desc");
                  }                              
               }
               
               for(int i = 0; i < result.length; i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                     while(result[i][j].length() < 15)
                        result[i][j] = result[i][j] + " ";
               }
               
               for(int i = 0; i < result.length; i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                     if(list[j] == 6)
                        while(result[i][j].length() < 120)
                           result[i][j] = result[i][j] + " ";
               }
               
               for(int i = 0; i < result.length; i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                  {
                     System.out.print("||");
                     System.out.print(result[i][j]);
                  }
                  
                  System.out.println("||");
               }
                  
               borderMakerDesc(count, moveDescCheck);
            }// End Moves Type
         }// End Moves
         else if(choice == 3)// If Trainers
         {
            String[] tableAttrTop = {"trainer_id            ||","trainer_name             ||","location_id           ||"};
			   String[] tableAttr = {"trainer_id","trainer_name","location_id"};
            
            tables = "Trainers";
            System.out.print("\nAre you searching for a trainer by ID, by name, or by location?\nEnter 1 for ID, 2 for name, and 3 for location: ");
            choice2 = input.nextInt();
            int count = 3;
            int[] list = {0,1,2};
            
            if(choice2 == 1)// If Trainer ID
            {
               System.out.print("\nPlease enter the trainer's ID number: ");
               valueI = input.nextInt();
               
               String select = "SELECT";
               
               select = select +" "+ tableAttr[0] + ","+tableAttr[1]+",location_name";
               
               String from = " FROM Locations,"+tables;
               
               String where = " WHERE trainer_id = "+valueI;
			   
			      String and = " AND Locations.location_id = Trainers.location_id";
               
               String sql = select + from + where + and;
               
               System.out.println("\nThis is your sql statement: "+sql+"\n");
               
               rs = statement.executeQuery(sql);
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               rs.beforeFirst(); 
               
               String[] result = new String[count];
               System.out.println("Trainer Table");
               
               System.out.print("========================");
               borderMaker(count);
               
               System.out.print("||");
               
               for(int i = 0; i < count; i++)
               {
                  System.out.print(tableAttrTop[list[i]]);
               }
               System.out.println();
               System.out.print("========================");
               borderMaker(count);
               
               while(rs.next())
               {
                  for(int i = 0; i < result.length; i++)
                  {
                     if(list[i] == 0)
                     {
                        int id = rs.getInt("trainer_id");
                        result[i] = id+"";
                     }
                     else if(list[i] == 1)
                        result[i] = rs.getString("trainer_name");
                     else if(list[i] == 2)
                        result[i] = rs.getString("location_name");
				      }
               }  
               
               for(int i = 0; i < result.length; i++)
               {
                  while(result[i].length() < 22)
                  result[i] = result[i] + " ";
               }
               while(result[1].length() < 25)
                  result[1] = result[1] + " ";
               
               for(int i = 0; i < result.length; i++)
               {
                  System.out.print("||");
                  System.out.print(result[i]);
               }   
               System.out.print("||\n");
               
               System.out.print("========================");
               borderMaker(count);               
            }// End if Trainer ID
            else if(choice2 == 2)// If trainer name
            {
               System.out.println("\nNOTE: Searching \"Rocket Grunt\" or anything\nlike \"Rocket\" or \"Grunt\" will yield multiple results\n");
			   
			      System.out.println("Are you searcing for an exact name or for a name like one you're thinking of?");
			      System.out.print("Enter 1 for exact name and 2 for searching a name like what you want: ");
			      int choice3 = input.nextInt();
			      while(choice3 != 1 && choice3 != 2)
			      {
			         System.out.println("That is not one of the options. Try again: ");
				      choice3 = input.nextInt();
			      }
			      
               System.out.print("Enter the trainers name: ");
               input.nextLine();
               valueS = input.nextLine();
                  
               String select = "SELECT ";
               
               select = select +" "+ tableAttr[0] + ","+tableAttr[1]+",location_name";
               
               String from = " FROM Locations,"+tables;
               
			      String where = "";
               
               String and = " AND Locations.location_id = Trainers.location_id";
               
			      if(choice3 == 1)
               {
                  valueS = addQuotes(valueS);
                  where = " WHERE trainer_name = "+valueS;
			      }
               else if(choice3 == 2)
               {
			         valueS = "%"+valueS+"%";
                  valueS = addQuotes(valueS);
                  where = " WHERE trainer_name LIKE "+valueS;
               }
               
               String sql = select + from + where + and;     
               
               System.out.println("\nThis is your sql statement: "+sql+"\n");
               
               rs = statement.executeQuery(sql);
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               rs.beforeFirst(); 
               
               System.out.println("Trainers Table");
               
			      System.out.print("========================");			   
               borderMaker(count);               
               System.out.print("||");
   
               for(int i = 0; i < count; i++)
               {
                  System.out.print(tableAttrTop[list[i]]);
               }
               System.out.println();
			      System.out.print("========================");
               borderMaker(count);
               
               int size = 0;
               while(rs.next())
                  size++;
               
               rs.beforeFirst();
               
               String[][] result = new String[size][count];
               
               for(int i = 0; i < result.length && rs.next(); i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                  {
                     if(list[j] == 0)
                     {
                        int id = rs.getInt("trainer_id");
                        result[i][j] = id+"";
                     }
                     else if(list[j] == 1)
                        result[i][j] = rs.getString("trainer_name");
                        
                     else if(list[j] == 2)
                        result[i][j] = rs.getString("location_name");
                  }                              
               } 
               
               for(int i = 0; i < result.length; i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                     while(result[i][j].length() < 22)
                        result[i][j] = result[i][j] + " ";
               }
               
               for(int i = 0; i < result.length; i++)
			         while(result[i][1].length() < 25)
                     result[i][1] = result[i][1] + " ";
               
               for(int i = 0; i < result.length; i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                  {
                     System.out.print("||");
                     System.out.print(result[i][j]);
                  }
                  
                  System.out.println("||");
               }
               
			      System.out.print("========================");
               borderMaker(count);
            }// End If Trainer Name
            else if(choice2 == 3)// If Trainer location
            {
               System.out.println("Are you searcing for an exact location name or for a name like one you're thinking of?");
			      System.out.print("Enter 1 for exact location name and 2 for searching a location name like what you want: ");
               int choice3 = input.nextInt();
               
               System.out.print("\nPlease enter the location for your trainers: ");
               input.nextLine();  
               valueS = input.nextLine();
                  
               String select = "SELECT";
      
               select = select +" "+ tableAttr[0] + ","+tableAttr[1]+",location_name";
                  
               String from = " FROM Locations,"+tables;
                  
               String where = "";
			   
			      String and = " AND Locations.location_id = Trainers.location_id";
			   
               String orderBy = "";
                  
               String ascDesc = "";
                
               System.out.println("\nDo you want to sort this?");
               System.out.print("Enter 1 to yes and 2 for no: ");
               int sort = input.nextInt();
               if(sort == 1)
               {
                  orderBy = locationOrderBy(tableAttr);
                  ascDesc = ascDescMethod();
               }
               
               if(choice3 == 1)
               {
                  valueS = addQuotes(valueS);
                  where = " WHERE location_name = "+valueS;
			      }
               else if(choice3 == 2)
               {
			         valueS = "%"+valueS+"%";
                  valueS = addQuotes(valueS);
                  where = " WHERE location_name LIKE "+valueS;
               }
               
               
               String sql = select + from + where + and + orderBy + ascDesc;
               System.out.println("\nThis is your sql statement: "+sql+"\n");
               
               rs = statement.executeQuery(sql);
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               rs.beforeFirst(); 
               
               int size = 0;
               while(rs.next())
                  size++;
               
               rs.beforeFirst();
               String[][] result = new String[size][count];
               
               System.out.println("Trainers table");
               
               System.out.print("========================");			   
               borderMaker(count);               
               System.out.print("||");
   
               for(int i = 0; i < count; i++)
               {
                  System.out.print(tableAttrTop[list[i]]);
               }
               System.out.println();
			      System.out.print("========================");
               borderMaker(count);

               for(int i = 0; i < result.length && rs.next(); i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                  {
                     if(list[j] == 0)
                     {
                        int id = rs.getInt("trainer_id");
                        result[i][j] = id+"";
                     }
                     else if(list[j] == 1)
                        result[i][j] = rs.getString("trainer_name");
                        
                     else if(list[j] == 2)
                        result[i][j] = rs.getString("location_name");
                  }                              
               } 
               
               for(int i = 0; i < result.length; i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                     while(result[i][j].length() < 22)
                        result[i][j] = result[i][j] + " ";
               }
               
               for(int i = 0; i < result.length; i++)
			         while(result[i][1].length() < 25)
                        result[i][1] = result[i][1] + " ";
               
               for(int i = 0; i < result.length; i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                  {
                     System.out.print("||");
                     System.out.print(result[i][j]);
                  }
                  
                  System.out.println("||");
               }
               
			      System.out.print("========================");
               borderMaker(count);
            }
         }// End If Trainers
         else if(choice == 4)// If Locations
         {
            String[] tableAttrTop = {"location_id           ||","location_name         ||"};
			   String[] tableAttr = {"location_id","location_name"};
            
            tables = "Locations";
            System.out.print("\nAre you searching for a location by ID or by name?\nEnter 1 for ID or 2 for name: ");
            choice2 = input.nextInt();
            int count = 2;
            int[] list = {0,1};
            
            if(choice2 == 1)// If Location ID
            {
               System.out.print("\nPlease enter the location's ID number: ");
               valueI = input.nextInt();
               
               String select = "SELECT";
               
               select = select +" "+ tableAttr[0] + ","+tableAttr[1];
               
               String from = " FROM Locations";
               
               String where = " WHERE location_id = "+valueI;
               
               String sql = select + from + where;
               
               System.out.println("\nThis is your sql statement: "+sql+"\n");
               
               rs = statement.executeQuery(sql);
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               rs .beforeFirst(); 
               
               String[] result = new String[count];
               System.out.println("Location Table");
               
               System.out.print("==============");
               borderMaker(count);
               
               System.out.print("||");
               
               for(int i = 0; i < count; i++)
               {
                  System.out.print(tableAttrTop[list[i]]);
               }
               System.out.println();
               System.out.print("==============");
               borderMaker(count);
               
               while(rs.next())
               {
                  for(int i = 0; i < result.length; i++)
                  {
                     if(list[i] == 0)
                     {
                        int id = rs.getInt("location_id");
                        result[i] = id+"";
                     }
                     else if(list[i] == 1)
                        result[i] = rs.getString("location_name");
				      }
               }  
               
               for(int i = 0; i < result.length; i++)
               {
                  while(result[i].length() < 22)
                  result[i] = result[i] + " ";
               }
               for(int i = 0; i < result.length; i++)
               {
                  System.out.print("||");
                  System.out.print(result[i]);
               }   
               System.out.print("||\n");
               
               System.out.print("==============");
               borderMaker(count);               
            }// End if Location ID
            else if(choice2 == 2)// If Location name
            {
			      System.out.println("\nAre you searcing for an exact location name or for a location name like one you're thinking of?");
			      System.out.print("Enter 1 for exact location name and 2 for searching a location name like what you want: ");
			      int choice3 = input.nextInt();
			      while(choice3 != 1 && choice3 != 2)
			      {
			         System.out.println("That is not one of the options. Try again: ");
				      choice3 = input.nextInt();
			      }
			         
               System.out.print("Enter the location name: ");
               input.nextLine();
               valueS = input.nextLine();
                  
               String select = "SELECT ";
               
               select = select +" "+ tableAttr[0] + ","+tableAttr[1];
               
               String from = " FROM Locations";
               
			      String where = "";
               
			      if(choice3 == 1)
               {
                  valueS = addQuotes(valueS);
                  where = " WHERE location_name = "+valueS;
			      }
               else if(choice3 == 2)
               {
			         valueS = "%"+valueS+"%";
                  valueS = addQuotes(valueS);
                  where = " WHERE location_name LIKE "+valueS;
               }
               
               String sql = select + from + where;     
               
               System.out.println("\nThis is your sql statement: "+sql+"\n");
               
               rs = statement.executeQuery(sql);
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               rs.beforeFirst();
               
               System.out.println("Locations Table");
               
			      System.out.print("==============");			   
               borderMaker(count);               
               System.out.print("||");
   
               for(int i = 0; i < count; i++)
               {
                  System.out.print(tableAttrTop[list[i]]);
               }
               System.out.println();
			      System.out.print("==============");
               borderMaker(count);
               
               int size = 0;
               while(rs.next())
                  size++;
               
               rs.beforeFirst();
               
               String[][] result = new String[size][count];
               
               for(int i = 0; i < result.length && rs.next(); i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                  {
                     if(list[j] == 0)
                     {
                        int id = rs.getInt("location_id");
                        result[i][j] = id+"";
                     }
                     else if(list[j] == 1)
                        result[i][j] = rs.getString("location_name");
                  }                              
               } 
               
               for(int i = 0; i < result.length; i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                     while(result[i][j].length() < 22)
                        result[i][j] = result[i][j] + " ";
               }
               
               for(int i = 0; i < result.length; i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                  {
                     System.out.print("||");
                     System.out.print(result[i][j]);
                  }
                  
                  System.out.println("||");
               }
               
			      System.out.print("==============");
               borderMaker(count);
            }// End If Location Name
         }// End If Location
         else if(choice == 5)// If PokeMoves
         {
            String[] tableAttrTop = {"pm_id                   ||","pokemon_name            ||","move_name               ||"};
            String[] tableAttr = {"pm_id","pokemon_name","move_name"};
				int[] list = {0,1,2};
				int count = 3;
            
            tables = "Pokemon, PokeMoves, Moves";
            System.out.print("\nAre you searching for a pokemon & move by ID, by pokemon name/id, or by move name/id?\nEnter 1 for ID, 2 for pokemon, and 3 for move: ");
            choice2 = input.nextInt();
         
            if(choice2 == 1)// If PokeMoves ID
            {
               System.out.print("\nPlease enter the Pokemon & Move association ID number: ");
               valueI = input.nextInt();
               
               String select = "SELECT ";
               
               for(int i = 0; i < count-1; i++)
               {
                  select = select + tableAttr[list[i]];
                  select = select + ",";
               }
               select = select + tableAttr[list[count-1]];
               select = select + " ";
               
               String from = "FROM "+tables;
               
               String where = " WHERE pm_id = "+valueI;
               
               String and = " AND Pokemon.pokemon_id = PokeMoves.pokemon_id AND Moves.move_id = PokeMoves.move_id";
               
               String sql = select + from + where + and;
               
               System.out.println("\nThis is your sql statement: "+sql+"\n");
               
               rs = statement.executeQuery(sql);
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               rs.beforeFirst();
               
               String[] result = new String[count];
               System.out.println("PokeMoves Table");
               System.out.print("===========================");
               borderMaker(count);
               
               System.out.print("||");
               
               for(int i = 0; i < count; i++)
               {
                  System.out.print(tableAttrTop[list[i]]);
               }
               System.out.println();
               System.out.print("===========================");
               borderMaker(count);
               
               while(rs.next())
               {
                  for(int i = 0; i < result.length; i++)
                  {
                     if(list[i] == 0)
                     {
                        int id = rs.getInt("pm_id");
                        result[i] = id+"";
                     }
                     else if(list[i] == 1)
                        result[i] = rs.getString("pokemon_name");
                     else if(list[i] == 2)
                        result[i] = rs.getString("move_name");
                  }              
               }  
               
               for(int i = 0; i < result.length; i++)
               {
                  while(result[i].length() < 24)
                  result[i] = result[i] + " ";
               }
               
               for(int i = 0; i < result.length; i++)
               {
                  System.out.print("||");
                  System.out.print(result[i]);
               }   
               System.out.print("||\n");
               
               System.out.print("===========================");
               borderMaker(count);
            }// End if PokeMoves ID
            else if(choice2 == 2)// If Pokemon
            {
               String select = "SELECT ";
					
					System.out.println("\nAre you typing a pokemon's id or name?");
					System.out.print("Press 1 for id and 2 for name: ");
					choice2 = input.nextInt();

					int id = 0;
					String name = "";
					String sqlSelect = "";
					
					if(choice2 == 1)
					{
						System.out.print("Enter the pokemon's ID: ");
						id = input.nextInt(); 
					}
					else
					{
						input.nextLine();
						System.out.print("Enter the pokemon's name: ");
						name = input.nextLine();
						sqlSelect = "SELECT pokemon_id FROM Pokemon WHERE pokemon_name = "+addQuotes(name);
						rs = statement.executeQuery(sqlSelect);
                  if (!rs.first()) 
                  {
                     System.out.println("That entry either does not exist or the table is empty");
                     continue;
                  }
                  while(rs.next())
							id = rs.getInt("pokemon_id");
					}
					 
                  for(int i = 0; i < count-1; i++)
               {
                  select = select + tableAttr[list[i]];
                  select = select + ",";
               }
               select = select + tableAttr[list[count-1]];
               select = select + " ";
               
               String from = "FROM "+tables;
               
               String where = " WHERE PokeMoves.pokemon_id = "+id;
					
					String and = " AND Pokemon.pokemon_id = PokeMoves.pokemon_id AND Moves.move_id = PokeMoves.move_id";
   
               String sql = select + from + where + and;     
               
               System.out.println("\nThis is your sql statement: "+sql+"\n");
               
               rs = statement.executeQuery(sql);
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               rs.beforeFirst();
               
               int size = 0;
               while(rs.next())
                  size++;
               
               rs.beforeFirst();
               String[][] result = new String[size][count];
               
               System.out.println("PokeMoves Table");
               System.out.print("===========================");
               borderMaker(count);
                                    
               System.out.print("||");
               
               for(int i = 0; i < count; i++)
               {
                  System.out.print(tableAttrTop[list[i]]);
               }
               System.out.println();
               
               System.out.print("===========================");
               borderMaker(count);
               
               for(int i = 0; i < result.length && rs.next(); i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                  {
                     if(list[j] == 0)
                     {
                        int pmid = rs.getInt("pm_id");
                        result[i][j] = pmid+"";
                     }
                     else if(list[j] == 1)
                        result[i][j] = rs.getString("pokemon_name");
                     else if(list[j] == 2)
                        result[i][j] = rs.getString("move_name");
                  }
               }               
               for(int i = 0; i < result.length; i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                     while(result[i][j].length() < 24)
                        result[i][j] = result[i][j] + " ";
               }
               
               for(int i = 0; i < result.length; i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                  {
                     System.out.print("||");
                     System.out.print(result[i][j]);
                  }
                  
                  System.out.println("||");
               }
               
               System.out.print("===========================");
               borderMaker(count);       
            }// End If Pokemon
            else if(choice2 == 3)// If Moves
            {
               String select = "SELECT ";
					
					System.out.println("\nAre you typing a move's id or name?");
					System.out.print("Press 1 for id and 2 for name: ");
					choice2 = input.nextInt();

					int id = 0;
					String name = "";
					String sqlSelect = "";
					
					if(choice2 == 1)
					{
						System.out.print("Enter the move's ID: ");
						id = input.nextInt(); 
					}
					else
					{
						input.nextLine();
						System.out.print("Enter the move's name: ");
						name = input.nextLine();
						sqlSelect = "SELECT move_id FROM Moves WHERE move_name = "+addQuotes(name);
						rs = statement.executeQuery(sqlSelect);
                  if (!rs.first()) 
                  {
                     System.out.println("That entry either does not exist or the table is empty");
                     continue;
                  }
						while(rs.next())
							id = rs.getInt("move_id");
					}
					 
               for(int i = 0; i < count-1; i++)
               {
                  select = select + tableAttr[list[i]];
                  select = select + ",";
               }
               select = select + tableAttr[list[count-1]];
               select = select + " ";
               
               String from = "FROM "+tables;
               
               String where = " WHERE PokeMoves.move_id = "+id;
					
					String and = " AND Pokemon.pokemon_id = PokeMoves.pokemon_id AND Moves.move_id = PokeMoves.move_id";
   
               String sql = select + from + where + and;     
               
               System.out.println("\nThis is your sql statement: "+sql+"\n");
               
               rs = statement.executeQuery(sql);
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               rs.beforeFirst();
               
               int size = 0;
               while(rs.next())
                  size++;
               
               rs.beforeFirst();
               String[][] result = new String[size][count];
               
               System.out.println("PokeMoves Table");
               System.out.print("===========================");
               borderMaker(count);
                                    
               System.out.print("||");
               
               for(int i = 0; i < count; i++)
               {
                  System.out.print(tableAttrTop[list[i]]);
               }
               System.out.println();
               
               System.out.print("===========================");
               borderMaker(count);
               
               for(int i = 0; i < result.length && rs.next(); i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                  {
                     if(list[j] == 0)
                     {
                        int pmid = rs.getInt("pm_id");
                        result[i][j] = pmid+"";
                     }
                     else if(list[j] == 1)
                        result[i][j] = rs.getString("pokemon_name");
                     else if(list[j] == 2)
                        result[i][j] = rs.getString("move_name");
                  }
               }               
               for(int i = 0; i < result.length; i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                     while(result[i][j].length() < 24)
                        result[i][j] = result[i][j] + " ";
               }
               
               for(int i = 0; i < result.length; i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                  {
                     System.out.print("||");
                     System.out.print(result[i][j]);
                  }
                  
                  System.out.println("||");
               }
               
               System.out.print("===========================");
               borderMaker(count);
			   }// End If Moves
         }// End If PokeMoves
         else if(choice == 6)// If PokeTrainers
         {
            String[] tableAttrTop = {"pt_id                   ||","pokemon_name            ||","trainer_name            ||"};
            String[] tableAttr = {"pt_id","pokemon_name","trainer_name"};
				int[] list = {0,1,2};
				int count = 3;
            
            tables = "Pokemon, PokeTrainers, Trainers";
            System.out.print("\nAre you searching for a pokemon & trainer by ID, by pokemon name/id, or by trainer name/id?\nEnter 1 for ID, 2 for pokemon, and 3 for trainers: ");
            choice2 = input.nextInt();
         
            if(choice2 == 1)// If PokeTrainers ID
            {
               System.out.print("\nPlease enter the Pokemon & Trainer association ID number: ");
               valueI = input.nextInt();
               
               String select = "SELECT ";
               
               for(int i = 0; i < count-1; i++)
               {
                  select = select + tableAttr[list[i]];
                  select = select + ",";
               }
               select = select + tableAttr[list[count-1]];
               select = select + " ";
               
               String from = "FROM "+tables;
               
               String where = " WHERE pt_id = "+valueI;
               
               String and = " AND Pokemon.pokemon_id = PokeTrainers.pokemon_id AND Trainers.trainer_id = PokeTrainers.trainer_id";
               
               String sql = select + from + where + and;
               
               System.out.println("\nThis is your sql statement: "+sql+"\n");
               
               rs = statement.executeQuery(sql);
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               rs.beforeFirst();
               
               String[] result = new String[count];
               System.out.println("PokeTrainers Table");
               System.out.print("===========================");
               borderMaker(count);
               
               System.out.print("||");
               
               for(int i = 0; i < count; i++)
               {
                  System.out.print(tableAttrTop[list[i]]);
               }
               System.out.println();
               System.out.print("===========================");
               borderMaker(count);
               
               while(rs.next())
               {
                  for(int i = 0; i < result.length; i++)
                  {
                     if(list[i] == 0)
                     {
                        int id = rs.getInt("pt_id");
                        result[i] = id+"";
                     }
                     else if(list[i] == 1)
                        result[i] = rs.getString("pokemon_name");
                     else if(list[i] == 2)
                        result[i] = rs.getString("trainer_name");
                  }              
               }  
               
               for(int i = 0; i < result.length; i++)
               {
                  while(result[i].length() < 24)
                  result[i] = result[i] + " ";
               }
               
               for(int i = 0; i < result.length; i++)
               {
                  System.out.print("||");
                  System.out.print(result[i]);
               }   
               System.out.print("||\n");
               
               System.out.print("===========================");
               borderMaker(count);
            }// End if PokeTrainers ID
            else if(choice2 == 2)// If Pokemon
            {
               String select = "SELECT ";
					
					System.out.println("\nAre you typing a pokemon's id or name?");
					System.out.print("Press 1 for id and 2 for name: ");
					choice2 = input.nextInt();

					int id = 0;
					String name = "";
					String sqlSelect = "";
					
					if(choice2 == 1)
					{
						System.out.print("Enter the pokemon's ID: ");
						id = input.nextInt(); 
					}
					else
					{
						input.nextLine();
						System.out.print("Enter the pokemon's name: ");
						name = input.nextLine();
						sqlSelect = "SELECT pokemon_id FROM Pokemon WHERE pokemon_name = "+addQuotes(name);
						rs = statement.executeQuery(sqlSelect);
                  if (!rs.first()) 
                  {
                     System.out.println("That entry either does not exist or the table is empty");
                     continue;
                  }
						while(rs.next())
							id = rs.getInt("pokemon_id");
					}
					 
               for(int i = 0; i < count-1; i++)
               {
                  select = select + tableAttr[list[i]];
                  select = select + ",";
               }
               select = select + tableAttr[list[count-1]];
               select = select + " ";
               
               String from = "FROM "+tables;
               
               String where = " WHERE PokeTrainers.pokemon_id = "+id;
					
					String and = " AND Pokemon.pokemon_id = PokeTrainers.pokemon_id AND Trainers.trainer_id = PokeTrainers.trainer_id";
   
               String sql = select + from + where + and;     
               
               System.out.println("\nThis is your sql statement: "+sql+"\n");
               
               rs = statement.executeQuery(sql);
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               rs.beforeFirst();
               
               int size = 0;
               while(rs.next())
                  size++;
               
               rs.beforeFirst();
               String[][] result = new String[size][count];
               
               System.out.println("PokeTrainers Table");
               System.out.print("===========================");
               borderMaker(count);
                                    
               System.out.print("||");
               
               for(int i = 0; i < count; i++)
               {
                  System.out.print(tableAttrTop[list[i]]);
               }
               System.out.println();
               
               System.out.print("===========================");
               borderMaker(count);
               
               for(int i = 0; i < result.length && rs.next(); i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                  {
                     if(list[j] == 0)
                     {
                        int pmid = rs.getInt("pt_id");
                        result[i][j] = pmid+"";
                     }
                     else if(list[j] == 1)
                        result[i][j] = rs.getString("pokemon_name");
                     else if(list[j] == 2)
                        result[i][j] = rs.getString("trainer_name");
                  }
               }               
               for(int i = 0; i < result.length; i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                     while(result[i][j].length() < 24)
                        result[i][j] = result[i][j] + " ";
               }
               
               for(int i = 0; i < result.length; i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                  {
                     System.out.print("||");
                     System.out.print(result[i][j]);
                  }
                  
                  System.out.println("||");
               }
               
               System.out.print("===========================");
               borderMaker(count);       
            }// End If Pokemon
            else if(choice2 == 3)// If Trainers
            {
               String select = "SELECT ";
					
					System.out.println("\nAre you typing a trainer's id or name?");
					System.out.print("Press 1 for id and 2 for name: ");
					choice2 = input.nextInt();

					int id = 0;
					String name = "";
					String sqlSelect = "";
					
					if(choice2 == 1)
					{
						System.out.print("Enter the trainer's ID: ");
						id = input.nextInt(); 
					}
					else
					{
						input.nextLine();
						System.out.print("Enter the trainer's name: ");
						name = input.nextLine();
						sqlSelect = "SELECT trainer_id FROM Trainers WHERE trainer_name = "+addQuotes(name);
						rs = statement.executeQuery(sqlSelect);
                  if (!rs.first()) 
                  {
                     System.out.println("That entry either does not exist or the table is empty");
                     continue;
                  }
						while(rs.next())
							id = rs.getInt("trainer_id");
					}
					 
               for(int i = 0; i < count-1; i++)
               {
                  select = select + tableAttr[list[i]];
                  select = select + ",";
               }
               select = select + tableAttr[list[count-1]];
               select = select + " ";
               
               String from = "FROM "+tables;
               
               String where = " WHERE PokeTrainers.trainer_id = "+id;
					
					String and = " AND Pokemon.pokemon_id = PokeTrainers.pokemon_id AND Trainers.trainer_id = PokeTrainers.trainer_id";
   
               String sql = select + from + where + and;     
               
               System.out.println("\nThis is your sql statement: "+sql+"\n");
               
               rs = statement.executeQuery(sql);
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               rs.beforeFirst();
               
               int size = 0;
               while(rs.next())
                  size++;
               
               rs.beforeFirst();
               String[][] result = new String[size][count];
               
               System.out.println("PokeTrainers Table");
               System.out.print("===========================");
               borderMaker(count);
                                    
               System.out.print("||");
               
               for(int i = 0; i < count; i++)
               {
                  System.out.print(tableAttrTop[list[i]]);
               }
               System.out.println();
               
               System.out.print("===========================");
               borderMaker(count);
               
               for(int i = 0; i < result.length && rs.next(); i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                  {
                     if(list[j] == 0)
                     {
                        int pmid = rs.getInt("pt_id");
                        result[i][j] = pmid+"";
                     }
                     else if(list[j] == 1)
                        result[i][j] = rs.getString("pokemon_name");
                     else if(list[j] == 2)
                        result[i][j] = rs.getString("trainer_name");
                  }
               }               
               for(int i = 0; i < result.length; i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                     while(result[i][j].length() < 24)
                        result[i][j] = result[i][j] + " ";
               }
               
               for(int i = 0; i < result.length; i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                  {
                     System.out.print("||");
                     System.out.print(result[i][j]);
                  }
                  
                  System.out.println("||");
               }
               
               System.out.print("===========================");
               borderMaker(count);
			   }// End If Trainers
         }// End If PokeTrainers
         else if(choice == 7)// If PokeLocations
         {
            String[] tableAttrTop = {"pl_id                   ||","pokemon_name            ||","location_name           ||"};
            String[] tableAttr = {"pl_id","pokemon_name","location_name"};
				int[] list = {0,1,2};
				int count = 3;
            
            tables = "Pokemon, PokeLocations, Locations";
            System.out.print("\nAre you searching for a pokemon & location by ID, by pokemon name/id, or by location name/id?\nEnter 1 for ID, 2 for pokemon, and 3 for locations: ");
            choice2 = input.nextInt();
         
            if(choice2 == 1)// If PokeLocations ID
            {
               System.out.print("\nPlease enter the Pokemon & Location association ID number: ");
               valueI = input.nextInt();
               
               String select = "SELECT ";
               
               for(int i = 0; i < count-1; i++)
               {
                  select = select + tableAttr[list[i]];
                  select = select + ",";
               }
               select = select + tableAttr[list[count-1]];
               select = select + " ";
               
               String from = "FROM "+tables;
               
               String where = " WHERE pl_id = "+valueI;
               
               String and = " AND Pokemon.pokemon_id = PokeLocations.pokemon_id AND Locations.location_id = PokeLocations.location_id";
               
               String sql = select + from + where + and;
               
               System.out.println("\nThis is your sql statement: "+sql+"\n");
               
               rs = statement.executeQuery(sql);
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               rs.beforeFirst();
               
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               
               String[] result = new String[count];
               System.out.println("PokeLocations Table");
               System.out.print("===========================");
               borderMaker(count);
               
               System.out.print("||");
               
               for(int i = 0; i < count; i++)
               {
                  System.out.print(tableAttrTop[list[i]]);
               }
               System.out.println();
               System.out.print("===========================");
               borderMaker(count);
               
               while(rs.next())
               {
                  for(int i = 0; i < result.length; i++)
                  {
                     if(list[i] == 0)
                     {
                        int id = rs.getInt("pl_id");
                        result[i] = id+"";
                     }
                     else if(list[i] == 1)
                        result[i] = rs.getString("pokemon_name");
                     else if(list[i] == 2)
                        result[i] = rs.getString("location_name");
                  }              
               }  
               
               for(int i = 0; i < result.length; i++)
               {
                  while(result[i].length() < 24)
                  result[i] = result[i] + " ";
               }
               
               for(int i = 0; i < result.length; i++)
               {
                  System.out.print("||");
                  System.out.print(result[i]);
               }   
               System.out.print("||\n");
               
               System.out.print("===========================");
               borderMaker(count);
            }// End if PokeLocations ID
            else if(choice2 == 2)// If Pokemon
            {
               String select = "SELECT ";
					
					System.out.println("\nAre you typing a pokemon's id or name?");
					System.out.print("Press 1 for id and 2 for name: ");
					choice2 = input.nextInt();

					int id = 0;
					String name = "";
					String sqlSelect = "";
					
					if(choice2 == 1)
					{
						System.out.print("Enter the pokemon's ID: ");
						id = input.nextInt(); 
					}
					else
					{
						input.nextLine();
						System.out.print("Enter the pokemon's name: ");
						name = input.nextLine();
						sqlSelect = "SELECT pokemon_id FROM Pokemon WHERE pokemon_name = "+addQuotes(name);
						rs = statement.executeQuery(sqlSelect);
                  if (!rs.first()) 
                  {
                     System.out.println("That entry either does not exist or the table is empty");
                     continue;
                  }
						while(rs.next())
							id = rs.getInt("pokemon_id");
					}
					 
               for(int i = 0; i < count-1; i++)
               {
                  select = select + tableAttr[list[i]];
                  select = select + ",";
               }
               select = select + tableAttr[list[count-1]];
               select = select + " ";
               
               String from = "FROM "+tables;
               
               String where = " WHERE PokeLocations.pokemon_id = "+id;
					
					String and = " AND Pokemon.pokemon_id = PokeLocations.pokemon_id AND Locations.location_id = PokeLocations.location_id";
   
               String sql = select + from + where + and;     
               
               System.out.println("\nThis is your sql statement: "+sql+"\n");
               
               rs = statement.executeQuery(sql);
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               rs.beforeFirst();
               
               int size = 0;
               while(rs.next())
                  size++;
               
               rs.beforeFirst();
               String[][] result = new String[size][count];
               
               System.out.println("PokeLocations Table");
               System.out.print("===========================");
               borderMaker(count);
                                    
               System.out.print("||");
               
               for(int i = 0; i < count; i++)
               {
                  System.out.print(tableAttrTop[list[i]]);
               }
               System.out.println();
               
               System.out.print("===========================");
               borderMaker(count);
               
               for(int i = 0; i < result.length && rs.next(); i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                  {
                     if(list[j] == 0)
                     {
                        int pmid = rs.getInt("pl_id");
                        result[i][j] = pmid+"";
                     }
                     else if(list[j] == 1)
                        result[i][j] = rs.getString("pokemon_name");
                     else if(list[j] == 2)
                        result[i][j] = rs.getString("location_name");
                  }
               }               
               for(int i = 0; i < result.length; i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                     while(result[i][j].length() < 24)
                        result[i][j] = result[i][j] + " ";
               }
               
               for(int i = 0; i < result.length; i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                  {
                     System.out.print("||");
                     System.out.print(result[i][j]);
                  }
                  
                  System.out.println("||");
               }
               
               System.out.print("===========================");
               borderMaker(count);       
            }// End If Pokemon
            else if(choice2 == 3)// If Locations
            {
               String select = "SELECT ";
					
					System.out.println("\nAre you typing a location's id or name?");
					System.out.print("Press 1 for id and 2 for name: ");
					choice2 = input.nextInt();

					int id = 0;
					String name = "";
					String sqlSelect = "";
					
					if(choice2 == 1)
					{
						System.out.print("Enter the location's ID: ");
						id = input.nextInt(); 
					}
					else
					{
						input.nextLine();
						System.out.print("Enter the location's name: ");
						name = input.nextLine();
						sqlSelect = "SELECT trainer_id FROM Locations WHERE location_name = "+addQuotes(name);
						rs = statement.executeQuery(sqlSelect);
                  if (!rs.first()) 
                  {
                     System.out.println("That entry either does not exist or the table is empty");
                     continue;
                  }
						while(rs.next())
							id = rs.getInt("trainer_id");
					}
					 
               for(int i = 0; i < count-1; i++)
               {
                  select = select + tableAttr[list[i]];
                  select = select + ",";
               }
               select = select + tableAttr[list[count-1]];
               select = select + " ";
               
               String from = "FROM "+tables;
               
               String where = " WHERE PokeLocations.location_id = "+id;
					
					String and = " AND Pokemon.pokemon_id = PokeLocations.pokemon_id AND Locations.location_id = PokeLocations.location_id";
   
               String sql = select + from + where + and;     
               
               System.out.println("\nThis is your sql statement: "+sql+"\n");
               
               rs = statement.executeQuery(sql);
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               rs.beforeFirst();
               
               int size = 0;
               while(rs.next())
                  size++;
               
               rs.beforeFirst();
               String[][] result = new String[size][count];
               
               System.out.println("PokeLocations Table");
               System.out.print("===========================");
               borderMaker(count);
                                    
               System.out.print("||");
               
               for(int i = 0; i < count; i++)
               {
                  System.out.print(tableAttrTop[list[i]]);
               }
               System.out.println();
               
               System.out.print("===========================");
               borderMaker(count);
               
               for(int i = 0; i < result.length && rs.next(); i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                  {
                     if(list[j] == 0)
                     {
                        int pmid = rs.getInt("pl_id");
                        result[i][j] = pmid+"";
                     }
                     else if(list[j] == 1)
                        result[i][j] = rs.getString("pokemon_name");
                     else if(list[j] == 2)
                        result[i][j] = rs.getString("location_name");
                  }
               }               
               for(int i = 0; i < result.length; i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                     while(result[i][j].length() < 24)
                        result[i][j] = result[i][j] + " ";
               }
               
               for(int i = 0; i < result.length; i++)
               {
                  for(int j = 0; j < result[i].length; j++)
                  {
                     System.out.print("||");
                     System.out.print(result[i][j]);
                  }
                  
                  System.out.println("||");
               }
               
               System.out.print("===========================");
               borderMaker(count);
			   }// End If Locations
         }// End If PokeLocations
      }while(choice != 8); 
   }// End select()
   
   public static void insert() throws Exception // insert()
   {
      int choice = 0;
      do
      { 
         System.out.println("\nWhat table do you insert information into?");
         System.out.println("Enter 1 for Pokemon (IDs, Names, Stats)");
         System.out.println("Enter 2 for Moves (IDs, Names, Stats, and Descriptions)");
         System.out.println("Enter 3 for Trainers (IDs, Trainers, and their locations)");
         System.out.println("Enter 4 for Locations (IDs and Locations only)");
         System.out.println("Enter 5 for Pokemon and Moves (Pokemon knowing certain moves)");
         System.out.println("Enter 6 for Pokemon and Trainers (Pokemon being owned by certain people)");
         System.out.println("Enter 7 for Pokemon and Locations (Pokemon located on certain routes)");
         System.out.println("Enter 8 to exit");
         System.out.print("Please enter your choice: ");
         choice = input.nextInt();
         while(choice > 8 ||choice < 1)
         {
            System.out.print("That is not one of the choices. Try again: ");
            choice = input.nextInt();
         }  
         System.out.println();
         String tables;   
         String valueS;
         int valueI;
         
         if(choice == 1)// If Inserting Pokemon
         {
            System.out.println("You are attempting to add a Pokemon.\n(Let's just assume we're enting Gen 3 now. Check the spreadsheet printed at the start to see what you should add.)");
            
            System.out.print("Enter Pokemon ID: ");
            int pid = input.nextInt();
            
            System.out.print("Enter Pokemon name: ");
            String pName = input.next();
            pName = addQuotes(pName);
            
            System.out.print("Enter Pokemon first type: ");
            String fType = input.next();
            fType = addQuotes(fType);
            
            System.out.print("Enter Pokemon second type\nIf it is only one type, enter \"---\": ");
            String sType = input.next();
            sType = addQuotes(sType);
            
            System.out.print("Enter Pokemon hp: ");
            int hp = input.nextInt();
            
            System.out.print("Enter Pokemon attack: ");
            int atk = input.nextInt();
            
            System.out.print("Enter Pokemon defence: ");
            int def = input.nextInt();
            
            System.out.print("Enter Pokemon special attack: ");
            int spAtk = input.nextInt();
            
            System.out.print("Enter Pokemon special defence: ");
            int spDef = input.nextInt();
            
            System.out.print("Enter Pokemon speed: ");
            int speed = input.nextInt();
            
            System.out.print("Enter Pokemon total: ");
            int total = input.nextInt();
            System.out.println();
            
            String insertInto = "INSERT INTO Pokemon (pokemon_id, pokemon_name, pokemon_typeOne, pokemon_typeTwo, pokemon_hp, pokemon_atk, pokemon_def, pokemon_spAtk, pokemon_spDef, pokemon_speed, pokemon_total)";
            String values = " VALUES("+pid+","+pName+","+fType+","+sType+","+hp+","+atk+","+def+","+spAtk+","+spDef+","+speed+","+total+")";
            
            String sqlInsert = insertInto + values;
            System.out.println("Your SQL Command is " + sqlInsert);
            
            System.out.print("Do you want to commit to these changes?\nEnter 1 for yes and 2 for no: ");
            input.nextLine();
            int confirm = input.nextInt();
            
            if(confirm == 1)
            {
               int changes = statement.executeUpdate(sqlInsert);
               System.out.print("\n"+changes+" change(s) made.\n");
            }
            else
               System.out.println("\nNo changes made.\n");
         }// End If Inserting Pokemon
         else if(choice == 2)// If Inserting Moves
         {
            System.out.println("You are attempting to add a Pokemon.\n(Investigate the Gen III Attackdex on https://serebii.net to see what to add)");
            
            input.nextLine();
            System.out.print("Enter move name: ");
            String mName = input.nextLine();
            mName = addQuotes(mName);
            
            System.out.print("Enter move type: ");
            String mType = input.next();
            mType = addQuotes(mType);
            
            System.out.print("Enter move power points: ");
            int pp = input.nextInt();
            
            System.out.print("Enter move power: ");
            int power = input.nextInt();
            
            System.out.print("Enter move accuracy: ");
            int acc = input.nextInt();
            
            input.nextLine();
            System.out.print("Enter move description: ");
            String mDesc = input.nextLine();
            mDesc = addQuotes(mDesc);
            
            String insertInto = "INSERT INTO Moves (move_name, move_type, move_pp, move_power, move_accuracy, move_desc)";
            String values = " VALUES("+mName+","+mType+","+pp+","+power+","+acc+","+mDesc+")";
            
            String sqlInsert = insertInto + values;
            System.out.println("Your SQL Command is " + sqlInsert);
            
            System.out.print("Do you want to commit to these changes?\nEnter 1 for yes and 2 for no: ");
            int confirm = input.nextInt();
            
            if(confirm == 1)
            {
               int changes = statement.executeUpdate(sqlInsert);
               System.out.print("\n"+changes+" change(s) made.\n");
            }
            else
               System.out.println("\nNo changes made.\n");
         }// End If Inserting Moves
         else if(choice == 3)// If Inserting Trainers
         {
            System.out.println("You are attempting to add a Trainer.\n(Check https://serebii.net 's Kanto Gen II map to see what to add for our trainers.)");
            
            input.nextLine();
            System.out.print("Enter Trainer name: ");
            String tName = input.nextLine();
            tName = addQuotes(tName);
            
            System.out.print("Enter trainer's location ID: ");
            int loc = input.nextInt();
            
            String insertInto = "INSERT INTO Trainers (trainer_name, location_id)";
            String values = " VALUES("+tName+","+loc+")";
            
            String sqlInsert = insertInto + values;
            System.out.println("Your SQL Command is " + sqlInsert);
            
            System.out.print("Do you want to commit to these changes?\nEnter 1 for yes and 2 for no: ");
            input.nextLine();
            int confirm = input.nextInt();
            
            if(confirm == 1)
            {
               int changes = statement.executeUpdate(sqlInsert);
               System.out.print("\n"+changes+" change(s) made.\n");
            }
            else
               System.out.println("\nNo changes made.\n");
         }// End If Inserting Trainers
         else if(choice == 4)// If Inserting Locations
         {
            System.out.println("You are attempting to add a Location.\n(Check https://serebii.net 's Hoeen Gen III map to see what to add for our locations.)");
            
            System.out.print("Enter location name: ");
            input.nextLine();
            String lName = input.nextLine();
            lName = addQuotes(lName);
            
            String insertInto = "INSERT INTO Locations (location_name)";
            String values = " VALUES("+lName+")";
            
            String sqlInsert = insertInto + values;
            System.out.println("Your SQL Command is " + sqlInsert);
            
            System.out.print("Do you want to commit to these changes?\nEnter 1 for yes and 2 for no: ");
            input.nextLine();
            int confirm = input.nextInt();
            
            if(confirm == 1)
            {
               int changes = statement.executeUpdate(sqlInsert);
               System.out.print("\n"+changes+" change(s) made.\n");
            }
            else
               System.out.println("\nNo changes made.\n");
         }// End If Inserting Locations
         else if(choice == 5)// If Inserting into PokeMoves
         {
            System.out.println("You are attempting to pair a pokemon to a move.");
            
            System.out.println("\nAre you typing a pokemon's id or name?");
            System.out.print("Press 1 for id and 2 for name: ");
            int choice2 = input.nextInt();
                        
            System.out.println("\nAre you typing a move's id or name?");
            System.out.print("Press 1 for id and 2 for name: ");
            int choice3 = input.nextInt();
            
            String insertInto = "INSERT INTO PokeMoves (pokemon_id, move_id) ";
            
            String values = "";
            
            int id = 0;
            String name = "";
            String sqlSelect = "";
            
            if(choice2 == 1)
            {
               System.out.print("Enter the pokemon's ID: ");
               id = input.nextInt(); 
               values = " VALUES("+id+","; 
            }
            else
            {
               input.nextLine();
               System.out.print("Enter the pokemon's name: ");
               name = input.nextLine();
               sqlSelect = "SELECT pokemon_id FROM Pokemon WHERE pokemon_name = "+addQuotes(name);
               rs = statement.executeQuery(sqlSelect);
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               rs.beforeFirst();
               while(rs.next())
                  id = rs.getInt("pokemon_id");
               values = " VALUES("+id+",";
            }
            
            if(choice3 == 1)
            {
               if(choice2 == 1)
                  input.nextLine();
               System.out.print("Enter the move's ID: ");
               id = input.nextInt(); 
               values = values + id+")";
            }
            else
            {
               if(choice2 == 1)
                  input.nextLine();
               System.out.print("Enter the move's name: ");
               name = input.nextLine();
               sqlSelect = "SELECT move_id FROM Moves WHERE move_name = "+addQuotes(name);
               rs = statement.executeQuery(sqlSelect);
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               rs.beforeFirst();
               while(rs.next())
                  id = rs.getInt("move_id");
               values = values + id+")";
            }
            
            String sqlInsert = insertInto + values;
            System.out.println("Your SQL Command is " + sqlInsert);
            
            System.out.print("Do you want to commit to these changes?\nEnter 1 for yes and 2 for no: ");
            int confirm = input.nextInt();
            
            if(confirm == 1)
            {
               int changes = statement.executeUpdate(sqlInsert);
               System.out.print("\n"+changes+" change(s) made.\n");
            }
            else
               System.out.println("\nNo changes made.\n");
         }// End If Inserting Into PokeMoves
         else if(choice == 6)// If Inserting into PokeTrainers
         {
            System.out.println("You are attempting to pair a pokemon to a trainer.");
            
            System.out.println("\nAre you typing a pokemon's id or name?");
            System.out.print("Press 1 for id and 2 for name: ");
            int choice2 = input.nextInt();
                        
            System.out.println("\nAre you typing a trainer's id or name?");
            System.out.print("Press 1 for id and 2 for name: ");
            int choice3 = input.nextInt();
            
            String insertInto = "INSERT INTO PokeTrainers (pokemon_id, trainer_id) ";
            
            String values = "";
            
            int id = 0;
            String name = "";
            String sqlSelect = "";
            
            if(choice2 == 1)
            {
               System.out.print("Enter the pokemon's ID: ");
               id = input.nextInt(); 
               values = " VALUES("+id+","; 
            }
            else
            {
               input.nextLine();
               System.out.print("Enter the pokemon's name: ");
               name = input.nextLine();
               sqlSelect = "SELECT pokemon_id FROM Pokemon WHERE pokemon_name = "+addQuotes(name);
               rs = statement.executeQuery(sqlSelect);
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               rs.beforeFirst();
               while(rs.next())
                  id = rs.getInt("pokemon_id");
               values = " VALUES("+id+",";
            }
            
            if(choice3 == 1)
            {
               if(choice2 == 1)
                  input.nextLine();
               System.out.print("Enter the trainer's ID: ");
               id = input.nextInt(); 
               values = values + id+")";
            }
            else
            {
               if(choice2 == 1)
                  input.nextLine();
               System.out.print("Enter the trainer's name: ");
               name = input.nextLine();
               sqlSelect = "SELECT trainer_id FROM Trainers WHERE trainer_name = "+addQuotes(name);
               rs = statement.executeQuery(sqlSelect);
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               rs.beforeFirst();
               while(rs.next())
                  id = rs.getInt("trainer_id");
               values = values + id+")";
            }
            
            String sqlInsert = insertInto + values;
            System.out.println("Your SQL Command is " + sqlInsert);
            
            System.out.print("Do you want to commit to these changes?\nEnter 1 for yes and 2 for no: ");
            int confirm = input.nextInt();
            
            if(confirm == 1)
            {
               int changes = statement.executeUpdate(sqlInsert);
               System.out.print("\n"+changes+" change(s) made.\n");
            }
            else
               System.out.println("\nNo changes made.\n");
         }// End If Inserting Into PokeTrainers
         else if(choice == 7)// If Inserting into PokeLocations
         {
            System.out.println("You are attempting to pair a pokemon to a location.");
            
            System.out.println("\nAre you typing a pokemon's id or name?");
            System.out.print("Press 1 for id and 2 for name: ");
            int choice2 = input.nextInt();
                        
            System.out.println("\nAre you typing a move's id or name?");
            System.out.print("Press 1 for id and 2 for name: ");
            int choice3 = input.nextInt();
            
            String insertInto = "INSERT INTO PokeLocations (pokemon_id, location_id) ";
            
            String values = "";
            
            int id = 0;
            String name = "";
            String sqlSelect = "";
            
            if(choice2 == 1)
            {
               System.out.print("Enter the pokemon's ID: ");
               id = input.nextInt(); 
               values = " VALUES("+id+","; 
            }
            else
            {
               input.nextLine();
               System.out.print("Enter the pokemon's name: ");
               name = input.next();
               sqlSelect = "SELECT pokemon_id FROM Pokemon WHERE pokemon_name = "+addQuotes(name);
               rs = statement.executeQuery(sqlSelect);
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               rs.beforeFirst();
               while(rs.next())
                  id = rs.getInt("pokemon_id");
               values = " VALUES("+id+",";
            }
            
            if(choice3 == 1)
            {
               if(choice2 == 1)
                  input.nextLine();
               System.out.print("Enter the location's ID: ");
               id = input.nextInt(); 
               values = values + id+")";
            }
            else
            {
               input.nextLine();
               System.out.print("Enter the location's name: ");
               name = input.nextLine();
               sqlSelect = "SELECT location_id FROM Locations WHERE location_name = "+addQuotes(name);
               rs = statement.executeQuery(sqlSelect);
               if (!rs.first()) 
               {
                  System.out.println("That entry either does not exist or the table is empty");
                  continue;
               }
               rs.beforeFirst();
               while(rs.next())
                  id = rs.getInt("location_id");
               values = values + id+")";
            }
            
            String sqlInsert = insertInto + values;
            System.out.println("Your SQL Command is " + sqlInsert);
            
            System.out.print("Do you want to commit to these changes?\nEnter 1 for yes and 2 for no: ");
            int confirm = input.nextInt();
            
            if(confirm == 1)
            {
               int changes = statement.executeUpdate(sqlInsert);
               System.out.print("\n"+changes+" change(s) made.\n");
            }
            else
               System.out.println("\nNo changes made.\n");
         }// End if Inserting Into PokeLocations
      }while(choice != 8);
   }// End insert()
   
   public static void update() throws Exception // update()
   {
      int choice = 0;
      int choice2 = 0;
      do
		{
			System.out.println("You are now trying to update the database!");
			
			System.out.println("\nWhat table do you want to update the information of?");
			System.out.println("Enter 1 for Pokemon (IDs, Names, Stats)");
			System.out.println("Enter 2 for Moves (IDs, Names, Stats, and Descriptions)");
			System.out.println("Enter 3 for Trainers (IDs, Trainers, and their locations)");
			System.out.println("Enter 4 for Locations (IDs and Locations only)");
			System.out.println("Enter 5 for Pokemon and Moves (Pokemon knowing certain moves)");
			System.out.println("Enter 6 for Pokemon and Trainers (Pokemon being owned by certain people)");
			System.out.println("Enter 7 for Pokemon and Locations (Pokemon located on certain routes)");
			System.out.println("Enter 8 to exit update");
			System.out.print("Please enter what you want to change: ");
			
			choice = input.nextInt();
			
			if(choice == 1)// If Update Pokemon
			{
				String sql = "SELECT * FROM Pokemon";
				
				String[] tableAttrTop = {"pokemon_id     ||","pokemon_name   ||","pokemon_typeOne||","pokemon_typeTwo||","pokemon_hp     ||","pokemon_atk    ||","pokemon_def    ||","pokemon_spAtk  ||","pokemon_spDef  ||","pokemon_speed  ||","pokemon_total  ||"};
				String[] tableAttr = {"pokemon_id","pokemon_name","pokemon_typeOne","pokemon_typeTwo","pokemon_hp","pokemon_atk","pokemon_def","pokemon_spAtk","pokemon_spDef","pokemon_speed","pokemon_total"};
				int list[] = {0,1,2,3,4,5,6,7,8,9,10};
				
				rs = statement.executeQuery(sql);
            if (!rs.first()) 
            {
               System.out.println("That entry either does not exist or the table is empty");
               continue;
            }
            rs.beforeFirst();
				int count = 11;
				
				int size = 0;
				while(rs.next())
					size++;
					
				rs.beforeFirst();
				String[][] result = new String[size][count];
			  
				System.out.println("Pokemon table");
				borderMaker(count);                     
				System.out.print("||");
				
				for(int i = 0; i < count; i++)
				{
					System.out.print(tableAttrTop[list[i]]);
				}
				System.out.println();
				borderMaker(count);
				
				for(int i = 0; i < result.length && rs.next(); i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						if(list[j] == 0)
						{
							int id = rs.getInt("pokemon_id");
							result[i][j] = id+"";
						}
						else if(list[j] == 1)
							result[i][j] = rs.getString("pokemon_name");
						
						else if(list[j] == 2)
							result[i][j] = rs.getString("pokemon_typeOne");
						
						else if(list[j] == 3)
							result[i][j] = rs.getString("pokemon_typeTwo");
							
						else if(list[j] == 4)
						{
							int hp = rs.getInt("pokemon_hp");
							result[i][j] = hp + "";
						}
						else if(list[j] == 5)
						{
							int atk = rs.getInt("pokemon_atk");
							result[i][j] = atk + "";
						}
						else if(list[j] == 6)
						{
							int def = rs.getInt("pokemon_def");
							result[i][j] = def + "";
						}
						else if(list[j] == 7)
						{
							int spAtk = rs.getInt("pokemon_spAtk");
							result[i][j] = spAtk + "";;
						}
						else if(list[j] == 8)
						{
							int spDef = rs.getInt("pokemon_spDef");
							result[i][j] = spDef + "";
						}
						else if(list[j] == 9)
						{
							int speed = rs.getInt("pokemon_speed");
							result[i][j] = speed + "";
						}
						else if(list[j] == 10)
						{
							int total = rs.getInt("pokemon_total");
							result[i][j] = total + "";
						}
					}                           
				}
				
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
							while(result[i][j].length() < 15)
								result[i][j] = result[i][j] + " ";
				}
				
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						System.out.print("||");
						System.out.print(result[i][j]);
					}
					System.out.println("||");
				}   
				borderMaker(count);
				
				System.out.println("\n");
				System.out.println("This is your Pokemon table");
				System.out.print("Please enter the row's id number that you wish to update: ");
				choice2 = input.nextInt();
				
				sql = "SELECT * FROM Pokemon WHERE pokemon_id = "+choice2;
				
				rs = statement.executeQuery(sql);
            if (!rs.first()) 
            {
               System.out.println("That entry either does not exist or the table is empty");
               continue;
            }
            rs.beforeFirst();
				
				System.out.print("\n");
				System.out.println("This is the row you've selected.");
				
				String[] resultS = new String[count];
				System.out.println("\nPokemon Table");
				
				borderMaker(count);
				
				System.out.print("||");
				
				for(int i = 0; i < count; i++)
				{
					System.out.print(tableAttrTop[list[i]]);
				}
				System.out.println();
				borderMaker(count);
				
				while(rs.next())
				{
					for(int i = 0; i < resultS.length; i++)
					{
						if(list[i] == 0)
						{
							int id = rs.getInt("pokemon_id");
							resultS[i] = id+"";
						}
						else if(list[i] == 1)
							resultS[i] = rs.getString("pokemon_name");
						else if(list[i] == 2)
							resultS[i] = rs.getString("pokemon_typeOne");
						else if(list[i] == 3)
							resultS[i] = rs.getString("pokemon_typeTwo");
						else if(list[i] == 4)
						{
							int hp = rs.getInt("pokemon_hp");
							resultS[i] = hp + "";
						}
						else if(list[i] == 5)
						{   
							int atk = rs.getInt("pokemon_atk");
							resultS[i] = atk + "";
						}
						else if(list[i] == 6)
						{   
							int def = rs.getInt("pokemon_def");
							resultS[i] = def + "";
						}
						else if(list[i] == 7)
						{     
							int spAtk = rs.getInt("pokemon_spAtk");
							resultS[i] = spAtk + "";
						}
						else if(list[i] == 8)
						{
							int spDef = rs.getInt("pokemon_spDef");
							resultS[i] = spDef + "";
						}
						else if(list[i] == 9)
						{
							int speed = rs.getInt("pokemon_speed");
							resultS[i] = speed + "";
						}
						else if(list[i] == 10) 
						{
							int total = rs.getInt("pokemon_total");
							resultS[i] = total + "";
						}
					}
					
				}  
					
				for(int i = 0; i < resultS.length; i++)
				{
					while(resultS[i].length() < 15)
						resultS[i] = resultS[i] + " ";
				}
				
				for(int i = 0; i < resultS.length; i++)
				{
					System.out.print("||");
					System.out.print(resultS[i]);
				}   
				System.out.print("||\n");
				
				borderMaker(count);
				
				System.out.println("\nWhat detail do you want to change in this row?");
				System.out.println("Enter 1 for ID number");
				System.out.println("Enter 2 for Name");
				System.out.println("Enter 3 for First type");
				System.out.println("Enter 4 for Second type");
				System.out.println("Enter 5 for Hp stats");
				System.out.println("Enter 6 for Attack stats");
				System.out.println("Enter 7 for Defence stats");
				System.out.println("Enter 8 for Special Attack stats");
				System.out.println("Enter 9 for Special Defence stats");
				System.out.println("Enter 10 for Speed stats");
				System.out.println("Enter 11 for Total stats");
				System.out.print("Please enter what you want to change: ");
				
				int choice3 = input.nextInt();
				while(choice3 > 11||choice3 < 1)
				{
					System.out.print("\nPlease choose at least one value before continuing: ");
					choice3 = input.nextInt();
				}
				String choiceS = "";
				input.nextLine();
				
				if(choice3 == 1)
				{
					System.out.print("\nPlease enter what you're changing it to: ");
					choice3 = input.nextInt();
					sql = "UPDATE Pokemon SET pokemon_id = "+choice3+" WHERE pokemon_id ="+choice2;
				}
				else if(choice3 == 2)
				{
					System.out.print("\nPlease enter what you're changing it to: ");
					choiceS = input.nextLine();
					sql = "UPDATE Pokemon SET pokemon_name = "+addQuotes(choiceS)+" WHERE pokemon_id ="+choice2;
				}
				else if(choice3 == 3)
				{
					System.out.print("\nPlease enter what you're changing it to: ");
					choiceS = input.nextLine();
					sql = "UPDATE Pokemon SET pokemon_typeOne = "+addQuotes(choiceS)+" WHERE pokemon_id ="+choice2;
				}
				else if(choice3 == 4)
				{
					System.out.print("\nPlease enter what you're changing it to: ");
					choiceS = input.nextLine();
					sql = "UPDATE Pokemon SET pokemon_typeTwo = "+addQuotes(choiceS)+" WHERE pokemon_id ="+choice2;
				}
				else if(choice3 == 5)
				{
					System.out.print("\nPlease enter what you're changing it to: ");
					choice3 = input.nextInt();
					sql = "UPDATE Pokemon SET pokemon_hp = "+choice3+" WHERE pokemon_id ="+choice2;
				}
				else if(choice3 == 6)
				{
					System.out.print("\nPlease enter what you're changing it to: ");
					choice3 = input.nextInt();
					sql = "UPDATE Pokemon SET pokemon_atk = "+choice3+" WHERE pokemon_id ="+choice2;
				}
				else if(choice3 == 7)
				{
					System.out.print("\nPlease enter what you're changing it to: ");
					choice3 = input.nextInt();
					sql = "UPDATE Pokemon SET pokemon_def = "+choice3+" WHERE pokemon_id ="+choice2;
				}
				else if(choice3 == 8)
				{
					System.out.print("\nPlease enter what you're changing it to: ");
					choice3 = input.nextInt();
					sql = "UPDATE Pokemon SET pokemon_spAtk = "+choice3+" WHERE pokemon_id ="+choice2;
				}
				else if(choice3 == 9)
				{
					System.out.print("\nPlease enter what you're changing : ");
					choice3 = input.nextInt();
					sql = "UPDATE Pokemon SET pokemon_spDef = "+choice3+" WHERE pokemon_id ="+choice2;
				}
				else if(choice3 == 10)
				{
					System.out.print("\nPlease enter what you're changing it to: ");
					choice3 = input.nextInt();
					sql = "UPDATE Pokemon SET pokemon_speed = "+choice3+" WHERE pokemon_id ="+choice2;
				}
				else if(choice3 == 11)
				{
					System.out.print("\nPlease enter what you're changing it to: ");
					choice3 = input.nextInt();
					sql = "UPDATE Pokemon SET pokemon_total = "+choice3+" WHERE pokemon_id ="+choice2;
				}
				
				System.out.println("Your SQL Command is " + sql);
				
				System.out.print("Do you want to commit to these changes?\nEnter 1 for yes and 2 for no: ");
				input.nextLine();
				int confirm = input.nextInt();
				
				if(confirm == 1)
				{
					int changes = statement.executeUpdate(sql);
					System.out.print("\n"+changes+" change(s) made.\n");
				}
				else
					System.out.println("\nNo changes made.\n");
			}// End if Updating Pokemon
			else if(choice == 2)// If Updating Moves
			{
				String sql = "SELECT * FROM Moves";
				
				String[] tableAttrTop = {"move_id        ||","move_name      ||","move_type      ||","move_pp        ||","move_power     ||","move_accuracy  ||","move_desc                                                                                                               ||"};
				String[] tableAttr = {"move_id","move_name","move_type","move_pp","move_power","move_accuracy","move_desc"};

				int list[] = {0,1,2,3,4,5,6};
				int count = 7;
				
				rs = statement.executeQuery(sql);
            if (!rs.first()) 
            {
               System.out.println("That entry either does not exist or the table is empty");
               continue;
            }
            rs.beforeFirst();
			
				int size = 0;
				while(rs.next())
					size++;
				
				rs.beforeFirst();
				String[][] result = new String[size][count];
				
				System.out.println("Moves table");
				
				boolean moveDescCheck = false;
				for(int i = 0; i < list.length; i++)
					if(list[i] == 6)
						moveDescCheck = true;
				
				borderMakerDesc(count, moveDescCheck);                     
				System.out.print("||");
				
				for(int i = 0; i < count; i++)
				{
					System.out.print(tableAttrTop[list[i]]);
				}
				System.out.println();
				
				borderMakerDesc(count, moveDescCheck);
				
				for(int i = 0; i < result.length && rs.next(); i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						if(list[j] == 0)
						{
							int id = rs.getInt("move_id");
							result[i][j] = id+"";
						}
						else if(list[j] == 1)
							result[i][j] = rs.getString("move_name");
							 else if(list[j] == 2)
							result[i][j] = rs.getString("move_type");
				
						else if(list[j] == 3)
						{
							int pp = rs.getInt("move_pp");
							result[i][j] = pp + "";
						}
						else if(list[j] == 4)
						{
							int power = rs.getInt("move_power");
							result[i][j] = power + "";
						}
							else if(list[j] == 5)
						{
							int acc = rs.getInt("move_accuracy");
							result[i][j] = acc + "";
						}
						else if(list[j] == 6)
							result[i][j] = rs.getString("move_desc");
					}                              
				}
				
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
						while(result[i][j].length() < 15)
							result[i][j] = result[i][j] + " ";
				}
				
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
						if(list[j] == 6)
							while(result[i][j].length() < 120)
								result[i][j] = result[i][j] + " ";
				}
				
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						System.out.print("||");
						System.out.print(result[i][j]);
					}
					
					System.out.println("||");
				}
					
				borderMakerDesc(count, moveDescCheck);
				
				System.out.println("\n");
				System.out.println("This is your Moves table");
				System.out.print("Please enter the row's id number that you wish to update: ");
				choice2 = input.nextInt();
				
				sql = "SELECT * FROM Moves WHERE move_id = "+choice2;
				
				rs = statement.executeQuery(sql);
            if (!rs.first()) 
            {
               System.out.println("That entry either does not exist or the table is empty");
               continue;
            }
            rs.beforeFirst();
				
				System.out.print("\n");
				System.out.println("This is the row you've selected.");
				
				String[] resultS = new String[count];
				System.out.println("\nMoves Table");
				
				borderMakerDesc(count, moveDescCheck);
				
				System.out.print("||");
				
				for(int i = 0; i < count; i++)
				{
					System.out.print(tableAttrTop[list[i]]);
				}
				System.out.println();
				borderMakerDesc(count, moveDescCheck);
				
				while(rs.next())
				{
					for(int i = 0; i < resultS.length; i++)
					{
						if(list[i] == 0)
						{
							int id = rs.getInt("move_id");
							resultS[i] = id+"";
						}
						else if(list[i] == 1)
							resultS[i] = rs.getString("move_name");
						else if(list[i] == 2)
							resultS[i] = rs.getString("move_type");

						else if(list[i] == 3)
						{
							int pp = rs.getInt("move_pp");
							resultS[i] = pp + "";
						}
						else if(list[i] == 4)
						{   
							int power = rs.getInt("move_power");
							resultS[i] = power + "";
						}
						else if(list[i] == 5)
						{   
							int acc = rs.getInt("move_accuracy");
							resultS[i] = acc + "";
						}
						else if(list[i] == 6) 
							resultS[i] = rs.getString("move_desc");
					}
				}
					
				for(int i = 0; i < resultS.length; i++)
				{
					while(resultS[i].length() < 15)
						resultS[i] = resultS[i] + " ";
				}
				
				for(int i = 0; i < resultS.length; i++)
					if(list[i] == 6)
						while(resultS[i].length() < 120)
							resultS[i] = resultS[i] + " ";
				
				for(int i = 0; i < resultS.length; i++)
				{
					System.out.print("||");
					System.out.print(resultS[i]);
				}   
						
				System.out.print("||\n");
				
				borderMakerDesc(count, moveDescCheck);
				
				System.out.println("\nWhat detail do you want to change in this row?");
				System.out.println("Enter 1 for ID number");
				System.out.println("Enter 2 for Name");
				System.out.println("Enter 3 for Type");
				System.out.println("Enter 4 for Power Points");
				System.out.println("Enter 5 for Move Power Stats");
				System.out.println("Enter 6 for Move Accuracy Stats");
				System.out.println("Enter 7 for Move Description");
				System.out.print("Please enter what you want to change: ");
				
				int choice3 = input.nextInt();
				while(choice3 > 7||choice3 < 1)
				{
					System.out.print("\nPlease choose at least one value before continuing: ");
					choice3 = input.nextInt();
				}
				String choiceS = "";
				input.nextLine();
				
				if(choice3 == 1)
				{
					System.out.print("\nPlease enter what you're changing it to: ");
					choice3 = input.nextInt();
					sql = "UPDATE Moves SET move_id = "+choice3+" WHERE move_id ="+choice2;
				}
				else if(choice3 == 2)
				{
					System.out.print("\nPlease enter what you're changing it to: ");
					choiceS = input.nextLine();
					sql = "UPDATE Moves SET move_name = "+addQuotes(choiceS)+" WHERE move_id ="+choice2;
				}
				else if(choice3 == 3)
				{
					System.out.print("\nPlease enter what you're changing it to: ");
					choiceS = input.nextLine();
					sql = "UPDATE Moves SET move_type = "+addQuotes(choiceS)+" WHERE move_id ="+choice2;
				}
				else if(choice3 == 4)
				{
					System.out.print("\nPlease enter what you're changing it to: ");
					choice3 = input.nextInt();
					sql = "UPDATE Moves SET move_pp = "+choice3+" WHERE move_id ="+choice2;
				}
				else if(choice3 == 5)
				{
					System.out.print("\nPlease enter what you're changing it to: ");
					choice3 = input.nextInt();
					sql = "UPDATE Moves SET move_power = "+choice3+" WHERE move_id ="+choice2;
				}
				else if(choice3 == 6)
				{
					System.out.print("\nPlease enter what you're changing it to: ");
					choice3 = input.nextInt();
					sql = "UPDATE Moves SET move_accuracy = "+choice3+" WHERE move_id ="+choice2;
				}
				else if(choice3 == 7)
				{
					System.out.print("\nPlease enter what you're changing it to: ");
					choiceS = input.nextLine();
					sql = "UPDATE Moves SET move_desc = "+addQuotes(choiceS)+" WHERE move_id ="+choice2;
				}
				
				System.out.println("Your SQL Command is " + sql);
				
				System.out.print("Do you want to commit to these changes?\nEnter 1 for yes and 2 for no: ");
				//input.nextLine();
				int confirm = input.nextInt();
				
				if(confirm == 1)
				{
					int changes = statement.executeUpdate(sql);
					System.out.print("\n"+changes+" change(s) made.\n");
				}
				else
					System.out.println("\nNo changes made.\n");
			}// End If Updating Moves
			else if(choice == 3)// If Updating Trainers
			{
				String sql = "SELECT * FROM Trainers";
				
				String[] tableAttrTop = {"trainer_id            ||","trainer_name             ||","location_id           ||"};
				String[] tableAttr = {"trainer_id","trainer_name","location_id"};
				
				int list[] = {0,1,2};
				int count = 3;
				
				rs = statement.executeQuery(sql);
				if (!rs.first()) 
            {
               System.out.println("That entry either does not exist or the table is empty");
               continue;
            }
            rs.beforeFirst();
            
				int size = 0;
				while(rs.next())
					size++;
				
				rs.beforeFirst();
				String[][] result = new String[size][count];
				
				System.out.println("Trainers table");
				
				System.out.print("========================");
				borderMaker(count);                     
				System.out.print("||");
				
				for(int i = 0; i < count; i++)
				{
					System.out.print(tableAttrTop[list[i]]);
				}
				System.out.println();
				
				System.out.print("========================");
				borderMaker(count);   
				
				for(int i = 0; i < result.length && rs.next(); i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						if(list[j] == 0)
						{
							int id = rs.getInt("trainer_id");
							result[i][j] = id+"";
						}
						else if(list[j] == 1)
							result[i][j] = rs.getString("trainer_name");
				
						else if(list[j] == 2)
						{
							int locId = rs.getInt("location_id");
							result[i][j] = locId + "";
						}
					}                              
				}
				
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
						while(result[i][j].length() < 22)
							result[i][j] = result[i][j] + " ";
				}
				
				for(int i = 0; i < result.length; i++)
					while(result[i][1].length() < 25)
						result[i][1] = result[i][1] + " ";
					
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						System.out.print("||");
						System.out.print(result[i][j]);
					}
					
					System.out.println("||");
				}
				
				System.out.print("========================");	
				borderMaker(count);   
				
				System.out.println("\n");
				System.out.println("This is your Trainers table");
				System.out.print("Please enter the row's id number that you wish to update: ");
				choice2 = input.nextInt();
				
				sql = "SELECT * FROM Trainers WHERE trainer_id = "+choice2;
				
				rs = statement.executeQuery(sql);
				if (!rs.first()) 
            {
               System.out.println("That entry either does not exist or the table is empty");
               continue;
            }
            rs.beforeFirst();
            
				System.out.print("\n");
				System.out.println("This is the row you've selected.");
				
				String[] resultS = new String[count];
				System.out.println("\nTrainers Table");
				
				System.out.print("========================");
				borderMaker(count);
				
				System.out.print("||");
				
				for(int i = 0; i < count; i++)
				{
					System.out.print(tableAttrTop[list[i]]);
				}
				System.out.println();
				System.out.print("========================");
				borderMaker(count);
				
				while(rs.next())
				{
					for(int i = 0; i < resultS.length; i++)
					{
						if(list[i] == 0)
						{
							int id = rs.getInt("trainer_id");
							resultS[i] = id+"";
						}
						else if(list[i] == 1)
							resultS[i] = rs.getString("trainer_name");
						else if(list[i] == 2)
							resultS[i] = rs.getString("location_id");
					}
				}
					
				for(int i = 0; i < resultS.length; i++)
				{
					while(resultS[i].length() < 22)
						resultS[i] = resultS[i] + " ";
				}
				
				while(resultS[1].length() < 25)
					resultS[1] = resultS[1] + " ";
				
				for(int i = 0; i < resultS.length; i++)
				{
					System.out.print("||");
					System.out.print(resultS[i]);
				}   
				System.out.print("||\n");
				
				System.out.print("========================");
				borderMaker(count);
				
				System.out.println("\nWhat detail do you want to change in this row?");
				System.out.println("Enter 1 for Trainer ID number");
				System.out.println("Enter 2 for Trainer Name");
				System.out.println("Enter 3 for Location ID (of where the trainer is located)");
				System.out.print("Please enter what you want to change: ");
				
				int choice3 = input.nextInt();
				while(choice3 > 3||choice3 < 1)
				{
					System.out.print("\nPlease choose at least one value before continuing: ");
					choice3 = input.nextInt();
				}
				String choiceS = "";
				input.nextLine();
				
				if(choice3 == 1)
				{
					System.out.print("\nPlease enter what you're changing it to: ");
					choice3 = input.nextInt();
					sql = "UPDATE Trainers SET trainer_id = "+choice3+" WHERE trainer_id ="+choice2;
				}
				else if(choice3 == 2)
				{
					System.out.print("\nPlease enter what you're changing it to: ");
					choiceS = input.nextLine();
					sql = "UPDATE Trainers SET trainer_name = "+addQuotes(choiceS)+" WHERE trainer_id ="+choice2;
				}
				else if(choice3 == 3)
				{
					System.out.print("\nPlease enter what you're changing it to: ");
					choice3 = input.nextInt();
					sql = "UPDATE Trainers SET location_id = "+choice3+" WHERE trainer_id ="+choice2;
				}
				
				System.out.println("Your SQL Command is " + sql);
				
				System.out.print("Do you want to commit to these changes?\nEnter 1 for yes and 2 for no: ");
				int confirm = input.nextInt();
				
				if(confirm == 1)
				{
					int changes = statement.executeUpdate(sql);
					System.out.print("\n"+changes+" change(s) made.\n");
				}
				else
					System.out.println("\nNo changes made.\n");
			}
			else if(choice == 4)// If Updating Locations
			{
				String sql = "SELECT * FROM Locations";
				
				String[] tableAttrTop = {"location_id           ||","location_name         ||"};
			   String[] tableAttr = {"location_id","location_name"};
				
				int list[] = {0,1};
				int count = 2;
				
            
				rs = statement.executeQuery(sql);
				if (!rs.first()) 
            {
               System.out.println("That entry either does not exist or the table is empty");
               continue;
            }
            rs.beforeFirst();
            
				int size = 0;
				while(rs.next())
					size++;
				
				rs.beforeFirst();
				String[][] result = new String[size][count];
				
				System.out.println("Locations table");
				
				System.out.print("==============");
				borderMaker(count);                     
				System.out.print("||");
				
				for(int i = 0; i < count; i++)
				{
					System.out.print(tableAttrTop[list[i]]);
				}
				System.out.println();
				
				System.out.print("==============");
				borderMaker(count);   
				
				for(int i = 0; i < result.length && rs.next(); i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						if(list[j] == 0)
						{
							int id = rs.getInt("location_id");
							result[i][j] = id+"";
						}
						else if(list[j] == 1)
							result[i][j] = rs.getString("location_name");
					}                              
				}
				
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
						while(result[i][j].length() < 22)
							result[i][j] = result[i][j] + " ";
				}
				
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						System.out.print("||");
						System.out.print(result[i][j]);
					}
					
					System.out.println("||");
				}
				System.out.print("==============");
				borderMaker(count);   
				
				System.out.println("\n");
				System.out.println("This is your Trainers table");
				System.out.print("Please enter the row's id number that you wish to update: ");
				choice2 = input.nextInt();
				
				sql = "SELECT * FROM Locations WHERE location_id = "+choice2;
				
				rs = statement.executeQuery(sql);
				if (!rs.first()) 
            {
               System.out.println("That entry either does not exist or the table is empty");
               continue;
            }
            rs.beforeFirst();
            
				System.out.print("\n");
				System.out.println("This is the row you've selected.");
				
				String[] resultS = new String[count];
				System.out.println("\nLocations Table");
				
				System.out.print("==============");
				borderMaker(count);
				
				System.out.print("||");
				
				for(int i = 0; i < count; i++)
				{
					System.out.print(tableAttrTop[list[i]]);
				}
				System.out.println();
				System.out.print("==============");
				borderMaker(count);
				
				while(rs.next())
				{
					for(int i = 0; i < resultS.length; i++)
					{
						if(list[i] == 0)
						{
							int id = rs.getInt("location_id");
							resultS[i] = id+"";
						}
						else if(list[i] == 1)
							resultS[i] = rs.getString("location_name");
					}
				}
					
				for(int i = 0; i < resultS.length; i++)
				{
					while(resultS[i].length() < 22)
					resultS[i] = resultS[i] + " ";
				}
				
				for(int i = 0; i < resultS.length; i++)
				{
					System.out.print("||");
					System.out.print(resultS[i]);
				}   
				System.out.print("||\n");
				System.out.print("==============");
				borderMaker(count);
				
				System.out.println("\nWhat detail do you want to change in this row?");
				System.out.println("Enter 1 for Location ID number");
				System.out.println("Enter 2 for Location Name");
				System.out.print("Please enter what you want to change: ");
				
				int choice3 = input.nextInt();
				while(choice3 > 2||choice3 < 1)
				{
					System.out.print("\nPlease choose at least one value before continuing: ");
					choice3 = input.nextInt();
				}
				String choiceS = "";
				input.nextLine();
				
				if(choice3 == 1)
				{
					System.out.print("\nPlease enter what you're changing it to: ");
					choice3 = input.nextInt();
					sql = "UPDATE Locations SET location_id = "+choice3+" WHERE location_id ="+choice2;
				}
				else if(choice3 == 2)
				{
					System.out.print("\nPlease enter what you're changing it to: ");
					choiceS = input.nextLine();
					sql = "UPDATE Locations SET location_name = "+addQuotes(choiceS)+" WHERE location_id ="+choice2;
				}
				
				System.out.println("Your SQL Command is " + sql);
				
				System.out.print("Do you want to commit to these changes?\nEnter 1 for yes and 2 for no: ");
				int confirm = input.nextInt();
				
				if(confirm == 1)
				{
					int changes = statement.executeUpdate(sql);
					System.out.print("\n"+changes+" change(s) made.\n");
				}
				else
					System.out.println("\nNo changes made.\n");
			}
         else if(choice == 5)// If Updating PokeMoves
			{
            String sql = "SELECT pm_id, pokemon_name, move_name FROM Pokemon, PokeMoves, Moves WHERE Pokemon.pokemon_id = PokeMoves.pokemon_id AND Moves.move_id = PokeMoves.move_id";
				
				String[] tableAttrTop = {"pm_id                   ||","pokemon_name            ||","move_name               ||"};
			   String[] tableAttr = {"pm_id","pokemon_name","move_name"};
            
            int count = 3;
            int[] list = {0,1,2};
				
				rs = statement.executeQuery(sql);
				if (!rs.first()) 
            {
               System.out.println("That entry either does not exist or the table is empty");
               continue;
            }
            rs.beforeFirst();
            
				int size = 0;
				while(rs.next())
					size++;
				
				rs.beforeFirst();
				String[][] result = new String[size][count];
				
				System.out.println("PokeMoves table");
				
				System.out.print("===========================");
				borderMaker(count);                     
				System.out.print("||");
				
				for(int i = 0; i < count; i++)
				{
					System.out.print(tableAttrTop[list[i]]);
				}
				System.out.println();
				
				System.out.print("===========================");
				borderMaker(count);   
				
				for(int i = 0; i < result.length && rs.next(); i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						if(list[j] == 0)
						{
							int id = rs.getInt("pm_id");
							result[i][j] = id+"";
						}
						else if(list[j] == 1)
							result[i][j] = rs.getString("pokemon_name");
						else if(list[j] == 2)
							result[i][j] = rs.getString("move_name");
					}                              
				}
				
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
						while(result[i][j].length() < 24)
							result[i][j] = result[i][j] + " ";
				}
					
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						System.out.print("||");
						System.out.print(result[i][j]);
					}
					
					System.out.println("||");
				}
				
				System.out.print("===========================");	
				borderMaker(count);   
				
				System.out.println("\n");
				System.out.println("This is your Pokemon & Moves table");
				System.out.print("Please enter the row's id number that you wish to update: ");
				choice2 = input.nextInt();
				
            sql = "SELECT pm_id, pokemon_name, move_name FROM Pokemon, PokeMoves, Moves WHERE pm_id = "+choice2+" AND Pokemon.pokemon_id = PokeMoves.pokemon_id AND Moves.move_id = PokeMoves.move_id";
				
				rs = statement.executeQuery(sql);
				if (!rs.first()) 
            {
               System.out.println("That entry either does not exist or the table is empty");
               continue;
            }
            rs.beforeFirst();
				
            String[] resultS = new String[count];
				System.out.println("\nPokeMoves Table");
			
				System.out.print("===========================");
				borderMaker(count);
				
				System.out.print("||");
				
				for(int i = 0; i < count; i++)
				{
					System.out.print(tableAttrTop[list[i]]);
				}
				System.out.println();
				System.out.print("===========================");
				borderMaker(count);
				
				while(rs.next())
				{
					for(int i = 0; i < resultS.length; i++)
					{
						if(list[i] == 0)
						{
							int id = rs.getInt("pm_id");
							resultS[i] = id+"";
						}
						else if(list[i] == 1)
							resultS[i] = rs.getString("pokemon_name");
						else if(list[i] == 2)
							resultS[i] = rs.getString("move_name");
					}
				}
				
            for(int i = 0; i < resultS.length; i++)
            {
               while(resultS[i].length() < 24)
                  resultS[i] = resultS[i] + " ";
            }
            
            for(int i = 0; i < resultS.length; i++)
            {
               System.out.print("||");
               System.out.print(resultS[i]);
            }   
            System.out.print("||\n");
            
            System.out.print("===========================");
            borderMaker(count);
         
				System.out.println("\nWhat detail do you want to change in this row?");
				System.out.println("Enter 1 for PokeMoves ID number");
				System.out.println("Enter 2 for Pokemon Name");
				System.out.println("Enter 3 for Move Name");
				System.out.print("Please enter what you want to change: ");
				
				int choice3 = input.nextInt();
				while(choice3 > 3||choice3 < 1)
				{
					System.out.print("\nPlease choose at least one value before continuing: ");
					choice3 = input.nextInt();
				}
				String choiceS = "";
				input.nextLine();
				
				if(choice3 == 1)
				{
					System.out.print("\nPlease enter the ID that you're changing it to: ");
					choice3 = input.nextInt();
					sql = "UPDATE PokeMoves SET pm_id = "+choice3+" WHERE pm_id ="+choice2;
				}
				else if(choice3 == 2)
				{
					System.out.print("\nPlease enter the ID that you're changing it to: ");
					choice3 = input.nextInt();
					sql = "UPDATE PokeMoves SET pokemon_id = "+choice3+" WHERE pm_id ="+choice2;
				}
				else if(choice3 == 3)
				{
					System.out.print("\nPlease enter the ID that you're changing it to: ");
					choice3 = input.nextInt();
					sql = "UPDATE PokeMoves SET move_id = "+choice3+" WHERE pm_id ="+choice2;;
				}
				
				System.out.println("Your SQL Command is " + sql);
				System.out.print("Do you want to commit to these changes?\nEnter 1 for yes and 2 for no: ");
				int confirm = input.nextInt();
				
				if(confirm == 1)
				{
					int changes = statement.executeUpdate(sql);
					System.out.print("\n"+changes+" change(s) made.\n");
				}
				else
					System.out.println("\nNo changes made.\n"); 
         }   
			else if(choice == 6)// If Updating PokeTrainers
			{
				String sql = "SELECT pt_id, pokemon_name, trainer_name FROM Pokemon, PokeTrainers, Trainers WHERE Pokemon.pokemon_id = PokeTrainers.pokemon_id AND Trainers.trainer_id = PokeTrainers.trainer_id";
				
				String[] tableAttrTop = {"pt_id                   ||","pokemon_name            ||","trainer_name            ||"};
			   String[] tableAttr = {"pt_id","pokemon_name","trainer_name"};
            
            int count = 3;
            int[] list = {0,1,2};
				
				rs = statement.executeQuery(sql);
				if (!rs.first()) 
            {
               System.out.println("That entry either does not exist or the table is empty");
               continue;
            }
            rs.beforeFirst();
            
				int size = 0;
				while(rs.next())
					size++;
				
				rs.beforeFirst();
				String[][] result = new String[size][count];
				
				System.out.println("PokeTrainers table");
				
				System.out.print("===========================");
				borderMaker(count);                     
				System.out.print("||");
				
				for(int i = 0; i < count; i++)
				{
					System.out.print(tableAttrTop[list[i]]);
				}
				System.out.println();
				
				System.out.print("===========================");
				borderMaker(count);   
				
				for(int i = 0; i < result.length && rs.next(); i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						if(list[j] == 0)
						{
							int id = rs.getInt("pt_id");
							result[i][j] = id+"";
						}
						else if(list[j] == 1)
							result[i][j] = rs.getString("pokemon_name");
						else if(list[j] == 2)
							result[i][j] = rs.getString("trainer_name");
					}                              
				}
				
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
						while(result[i][j].length() < 24)
							result[i][j] = result[i][j] + " ";
				}
					
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						System.out.print("||");
						System.out.print(result[i][j]);
					}
					
					System.out.println("||");
				}
				
				System.out.print("===========================");	
				borderMaker(count);   
				
				System.out.println("\n");
				System.out.println("This is your Pokemon & Trainers table");
				System.out.print("Please enter the row's id number that you wish to update: ");
				choice2 = input.nextInt();
				
            sql = "SELECT pt_id, pokemon_name, trainer_name FROM Pokemon, PokeTrainers, Trainers WHERE pt_id = "+choice2+" AND Pokemon.pokemon_id = PokeTrainers.pokemon_id AND Trainers.trainer_id = PokeTrainers.trainer_id";
                        
				rs = statement.executeQuery(sql);
				if (!rs.first()) 
            {
               System.out.println("That entry either does not exist or the table is empty");
               continue;
            }
            rs.beforeFirst();
				
            String[] resultS = new String[count];
				System.out.println("\nPokeTrainers Table");
			
				System.out.print("===========================");
				borderMaker(count);
				
				System.out.print("||");
				
				for(int i = 0; i < count; i++)
				{
					System.out.print(tableAttrTop[list[i]]);
				}
				System.out.println();
				System.out.print("===========================");
				borderMaker(count);
				
				while(rs.next())
				{
					for(int i = 0; i < resultS.length; i++)
					{
						if(list[i] == 0)
						{
							int id = rs.getInt("pt_id");
							resultS[i] = id+"";
						}
						else if(list[i] == 1)
							resultS[i] = rs.getString("pokemon_name");
						else if(list[i] == 2)
							resultS[i] = rs.getString("trainer_name");
					}
				}
				
            for(int i = 0; i < resultS.length; i++)
            {
               while(resultS[i].length() < 24)
                  resultS[i] = resultS[i] + " ";
            }
            
            for(int i = 0; i < resultS.length; i++)
            {
               System.out.print("||");
               System.out.print(resultS[i]);
            }   
            System.out.print("||\n");
            
            System.out.print("===========================");
            borderMaker(count);
         
				System.out.println("\nWhat detail do you want to change in this row?");
				System.out.println("Enter 1 for PokeTrainers ID number");
				System.out.println("Enter 2 for Pokemon Name");
				System.out.println("Enter 3 for Trainer Name");
				System.out.print("Please enter what you want to change: ");
				
				int choice3 = input.nextInt();
				while(choice3 > 3||choice3 < 1)
				{
					System.out.print("\nPlease choose at least one value before continuing: ");
					choice3 = input.nextInt();
				}
				String choiceS = "";
				input.nextLine();
				
				if(choice3 == 1)
				{
					System.out.print("\nPlease enter the ID that you're changing it to: ");
					choice3 = input.nextInt();
					sql = "UPDATE PokeTrainers SET pt_id = "+choice3+" WHERE pt_id ="+choice2;
				}
				else if(choice3 == 2)
				{
					System.out.print("\nPlease enter the ID that you're changing it to: ");
					choice3 = input.nextInt();
					sql = "UPDATE PokeTrainers SET pokemon_id = "+choice3+" WHERE pt_id ="+choice2;
				}
				else if(choice3 == 3)
				{
					System.out.print("\nPlease enter the ID that you're changing it to: ");
					choice3 = input.nextInt();
					sql = "UPDATE PokeTrainers SET trainer_id = "+choice3+" WHERE pt_id ="+choice2;;
				}
				
				System.out.println("Your SQL Command is " + sql);
				System.out.print("Do you want to commit to these changes?\nEnter 1 for yes and 2 for no: ");
				int confirm = input.nextInt();
				
				if(confirm == 1)
				{
					int changes = statement.executeUpdate(sql);
					System.out.print("\n"+changes+" change(s) made.\n");
				}
				else
					System.out.println("\nNo changes made.\n");
			}
			else if(choice == 7)// If Updating PokeLocations
			{
				String sql = "SELECT pl_id, pokemon_name, location_name FROM Pokemon, PokeLocations, Locations WHERE Pokemon.pokemon_id = PokeLocations.pokemon_id AND Locations.location_id = PokeLocations.location_id";
				
				String[] tableAttrTop = {"pl_id                   ||","pokemon_name            ||","location_name            ||"};
			   String[] tableAttr = {"pl_id","pokemon_name","location_name"};
            
            int count = 3;
            int[] list = {0,1,2};
				
				rs = statement.executeQuery(sql);
				if (!rs.first()) 
            {
               System.out.println("That entry either does not exist or the table is empty");
               continue;
            }
            rs.beforeFirst();
            
				int size = 0;
				while(rs.next())
					size++;
				
				rs.beforeFirst();
				String[][] result = new String[size][count];
				
				System.out.println("PokeLocations table");
				
				System.out.print("===========================");
				borderMaker(count);                     
				System.out.print("||");
				
				for(int i = 0; i < count; i++)
				{
					System.out.print(tableAttrTop[list[i]]);
				}
				System.out.println();
				
				System.out.print("===========================");
				borderMaker(count);   
				
				for(int i = 0; i < result.length && rs.next(); i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						if(list[j] == 0)
						{
							int id = rs.getInt("pl_id");
							result[i][j] = id+"";
						}
						else if(list[j] == 1)
							result[i][j] = rs.getString("pokemon_name");
						else if(list[j] == 2)
							result[i][j] = rs.getString("location_name");
					}                              
				}
				
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
						while(result[i][j].length() < 24)
							result[i][j] = result[i][j] + " ";
				}
					
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						System.out.print("||");
						System.out.print(result[i][j]);
					}
					
					System.out.println("||");
				}
				
				System.out.print("===========================");	
				borderMaker(count);   
				
				System.out.println("\n");
				System.out.println("This is your Pokemon & Locations table");
				System.out.print("Please enter the row's id number that you wish to update: ");
				choice2 = input.nextInt();
				
            sql = "SELECT pl_id, pokemon_name, location_name FROM Pokemon, PokeLocations, Locations WHERE pl_id = "+choice2+" AND Pokemon.pokemon_id = PokeLocations.pokemon_id AND Locations.location_id = PokeLocations.location_id";
                        
				rs = statement.executeQuery(sql);
				if (!rs.first()) 
            {
               System.out.println("That entry either does not exist or the table is empty");
               continue;
            }
            rs.beforeFirst();
				
            String[] resultS = new String[count];
				System.out.println("\nPokeLocations Table");
			
				System.out.print("===========================");
				borderMaker(count);
				
				System.out.print("||");
				
				for(int i = 0; i < count; i++)
				{
					System.out.print(tableAttrTop[list[i]]);
				}
				System.out.println();
				System.out.print("===========================");
				borderMaker(count);
				
				while(rs.next())
				{
					for(int i = 0; i < resultS.length; i++)
					{
						if(list[i] == 0)
						{
							int id = rs.getInt("pl_id");
							resultS[i] = id+"";
						}
						else if(list[i] == 1)
							resultS[i] = rs.getString("pokemon_name");
						else if(list[i] == 2)
							resultS[i] = rs.getString("location_name");
					}
				}
				
            for(int i = 0; i < resultS.length; i++)
            {
               while(resultS[i].length() < 24)
                  resultS[i] = resultS[i] + " ";
            }
            
            for(int i = 0; i < resultS.length; i++)
            {
               System.out.print("||");
               System.out.print(resultS[i]);
            }   
            System.out.print("||\n");
            
            System.out.print("===========================");
            borderMaker(count);
         
				System.out.println("\nWhat detail do you want to change in this row?");
				System.out.println("Enter 1 for PokeLocations ID number");
				System.out.println("Enter 2 for Pokemon Name");
				System.out.println("Enter 3 for Location Name");
				System.out.print("Please enter what you want to change: ");
				
				int choice3 = input.nextInt();
				while(choice3 > 3||choice3 < 1)
				{
					System.out.print("\nPlease choose at least one value before continuing: ");
					choice3 = input.nextInt();
				}
				String choiceS = "";
				input.nextLine();
				
				if(choice3 == 1)
				{
					System.out.print("\nPlease enter the ID that you're changing it to: ");
					choice3 = input.nextInt();
					sql = "UPDATE PokeLocations SET pl_id = "+choice3+" WHERE pl_id ="+choice2;
				}
				else if(choice3 == 2)
				{
					System.out.print("\nPlease enter the ID that you're changing it to: ");
					choice3 = input.nextInt();
					sql = "UPDATE PokeLocations SET pokemon_id = "+choice3+" WHERE pl_id ="+choice2;
				}
				else if(choice3 == 3)
				{
					System.out.print("\nPlease enter the ID that you're changing it to: ");
					choice3 = input.nextInt();
					sql = "UPDATE PokeLocations SET location_id = "+choice3+" WHERE pl_id ="+choice2;;
				}
				
				System.out.println("Your SQL Command is " + sql);
				System.out.print("Do you want to commit to these changes?\nEnter 1 for yes and 2 for no: ");
				int confirm = input.nextInt();
				
				if(confirm == 1)
				{
					int changes = statement.executeUpdate(sql);
					System.out.print("\n"+changes+" change(s) made.\n");
				}
				else
					System.out.println("\nNo changes made.\n");
         }
      }while(choice != 8);
   }// End update()
   
   public static void delete() throws Exception// delete()
   {
      int choice = 0;
      int choice2 = 0;
      do
		{
			System.out.println("You are now trying to delete from the database!");
			
			System.out.println("\nWhat table do you want to update the information of?");
			System.out.println("Enter 1 for Pokemon (IDs, Names, Stats)");
			System.out.println("Enter 2 for Moves (IDs, Names, Stats, and Descriptions)");
			System.out.println("Enter 3 for Trainers (IDs, Trainers, and their locations)");
			System.out.println("Enter 4 for Locations (IDs and Locations only)");
			System.out.println("Enter 5 for Pokemon and Moves (Pokemon knowing certain moves)");
			System.out.println("Enter 6 for Pokemon and Trainers (Pokemon being owned by certain people)");
			System.out.println("Enter 7 for Pokemon and Locations (Pokemon located on certain routes)");
			System.out.println("Enter 8 to exit update");
			System.out.print("Please enter what you want to change: ");
			
			choice = input.nextInt();
         
         if(choice == 1)// If Pokemon
         {
            String sql = "SELECT * FROM Pokemon";
				
				String[] tableAttrTop = {"pokemon_id     ||","pokemon_name   ||","pokemon_typeOne||","pokemon_typeTwo||","pokemon_hp     ||","pokemon_atk    ||","pokemon_def    ||","pokemon_spAtk  ||","pokemon_spDef  ||","pokemon_speed  ||","pokemon_total  ||"};
				String[] tableAttr = {"pokemon_id","pokemon_name","pokemon_typeOne","pokemon_typeTwo","pokemon_hp","pokemon_atk","pokemon_def","pokemon_spAtk","pokemon_spDef","pokemon_speed","pokemon_total"};
				int list[] = {0,1,2,3,4,5,6,7,8,9,10};
				
				rs = statement.executeQuery(sql);
            if (!rs.first()) 
            {
               System.out.println("That entry either does not exist or the table is empty");
               continue;
            }
            rs.beforeFirst();
				int count = 11;
				
				int size = 0;
				while(rs.next())
					size++;
					
				rs.beforeFirst();
				String[][] result = new String[size][count];
			  
				System.out.println("Pokemon table");
				borderMaker(count);                     
				System.out.print("||");
				
				for(int i = 0; i < count; i++)
				{
					System.out.print(tableAttrTop[list[i]]);
				}
				System.out.println();
				borderMaker(count);
				
				for(int i = 0; i < result.length && rs.next(); i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						if(list[j] == 0)
						{
							int id = rs.getInt("pokemon_id");
							result[i][j] = id+"";
						}
						else if(list[j] == 1)
							result[i][j] = rs.getString("pokemon_name");
						
						else if(list[j] == 2)
							result[i][j] = rs.getString("pokemon_typeOne");
						
						else if(list[j] == 3)
							result[i][j] = rs.getString("pokemon_typeTwo");
							
						else if(list[j] == 4)
						{
							int hp = rs.getInt("pokemon_hp");
							result[i][j] = hp + "";
						}
						else if(list[j] == 5)
						{
							int atk = rs.getInt("pokemon_atk");
							result[i][j] = atk + "";
						}
						else if(list[j] == 6)
						{
							int def = rs.getInt("pokemon_def");
							result[i][j] = def + "";
						}
						else if(list[j] == 7)
						{
							int spAtk = rs.getInt("pokemon_spAtk");
							result[i][j] = spAtk + "";;
						}
						else if(list[j] == 8)
						{
							int spDef = rs.getInt("pokemon_spDef");
							result[i][j] = spDef + "";
						}
						else if(list[j] == 9)
						{
							int speed = rs.getInt("pokemon_speed");
							result[i][j] = speed + "";
						}
						else if(list[j] == 10)
						{
							int total = rs.getInt("pokemon_total");
							result[i][j] = total + "";
						}
					}                           
				}
				
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
							while(result[i][j].length() < 15)
								result[i][j] = result[i][j] + " ";
				}
				
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						System.out.print("||");
						System.out.print(result[i][j]);
					}
					System.out.println("||");
				}   
				borderMaker(count);
				
				System.out.println("\n");
				System.out.println("This is your Pokemon table");
				System.out.print("Please enter the row's id number that you wish to update: ");
				choice2 = input.nextInt();
            
            sql = "DELETE FROM Pokemon WHERE pokemon_id = "+ choice2;
            
            System.out.println("Your SQL Command is " + sql);
				
				System.out.print("Do you want to commit to these changes?\nEnter 1 for yes and 2 for no: ");
				input.nextLine();
				int confirm = input.nextInt();
				
				if(confirm == 1)
				{
					int changes = statement.executeUpdate(sql);
					System.out.print("\n"+changes+" change(s) made.\n");
				}
				else
					System.out.println("\nNo changes made.\n");
         }// End If Pokemon
         else if(choice == 2)// If Moves
         {
            String sql = "SELECT * FROM Moves";
				
				String[] tableAttrTop = {"move_id        ||","move_name      ||","move_type      ||","move_pp        ||","move_power     ||","move_accuracy  ||","move_desc                                                                                                               ||"};
				String[] tableAttr = {"move_id","move_name","move_type","move_pp","move_power","move_accuracy","move_desc"};

				int list[] = {0,1,2,3,4,5,6};
				int count = 7;
				
				rs = statement.executeQuery(sql);
            if (!rs.first()) 
            {
               System.out.println("That entry either does not exist or the table is empty");
               continue;
            }
            rs.beforeFirst();
			
				int size = 0;
				while(rs.next())
					size++;
				
				rs.beforeFirst();
				String[][] result = new String[size][count];
				
				System.out.println("Moves table");
				
				boolean moveDescCheck = false;
				for(int i = 0; i < list.length; i++)
					if(list[i] == 6)
						moveDescCheck = true;
				
				borderMakerDesc(count, moveDescCheck);                     
				System.out.print("||");
				
				for(int i = 0; i < count; i++)
				{
					System.out.print(tableAttrTop[list[i]]);
				}
				System.out.println();
				
				borderMakerDesc(count, moveDescCheck);
				
				for(int i = 0; i < result.length && rs.next(); i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						if(list[j] == 0)
						{
							int id = rs.getInt("move_id");
							result[i][j] = id+"";
						}
						else if(list[j] == 1)
							result[i][j] = rs.getString("move_name");
							 else if(list[j] == 2)
							result[i][j] = rs.getString("move_type");
				
						else if(list[j] == 3)
						{
							int pp = rs.getInt("move_pp");
							result[i][j] = pp + "";
						}
						else if(list[j] == 4)
						{
							int power = rs.getInt("move_power");
							result[i][j] = power + "";
						}
							else if(list[j] == 5)
						{
							int acc = rs.getInt("move_accuracy");
							result[i][j] = acc + "";
						}
						else if(list[j] == 6)
							result[i][j] = rs.getString("move_desc");
					}                              
				}
				
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
						while(result[i][j].length() < 15)
							result[i][j] = result[i][j] + " ";
				}
				
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
						if(list[j] == 6)
							while(result[i][j].length() < 120)
								result[i][j] = result[i][j] + " ";
				}
				
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						System.out.print("||");
						System.out.print(result[i][j]);
					}
					
					System.out.println("||");
				}
					
				borderMakerDesc(count, moveDescCheck);
				
				System.out.println("\n");
				System.out.println("This is your Moves table");
				System.out.print("Please enter the row's id number that you wish to update: ");
				choice2 = input.nextInt();
            
            sql = "DELETE FROM Moves WHERE move_id = "+ choice2;
            
            System.out.println("Your SQL Command is " + sql);
				
				System.out.print("Do you want to commit to these changes?\nEnter 1 for yes and 2 for no: ");
				input.nextLine();
				int confirm = input.nextInt();
				
				if(confirm == 1)
				{
					int changes = statement.executeUpdate(sql);
					System.out.print("\n"+changes+" change(s) made.\n");
				}
				else
					System.out.println("\nNo changes made.\n");
         }
         else if(choice == 3)// If Trainers
         {
            String sql = "SELECT * FROM Trainers";
				
				String[] tableAttrTop = {"trainer_id            ||","trainer_name             ||","location_id           ||"};
				String[] tableAttr = {"trainer_id","trainer_name","location_id"};
				
				int list[] = {0,1,2};
				int count = 3;
				
				rs = statement.executeQuery(sql);
				if (!rs.first()) 
            {
               System.out.println("That entry either does not exist or the table is empty");
               continue;
            }
            rs.beforeFirst();
            
				int size = 0;
				while(rs.next())
					size++;
				
				rs.beforeFirst();
				String[][] result = new String[size][count];
				
				System.out.println("Trainers table");
				
				System.out.print("========================");
				borderMaker(count);                     
				System.out.print("||");
				
				for(int i = 0; i < count; i++)
				{
					System.out.print(tableAttrTop[list[i]]);
				}
				System.out.println();
				
				System.out.print("========================");
				borderMaker(count);   
				
				for(int i = 0; i < result.length && rs.next(); i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						if(list[j] == 0)
						{
							int id = rs.getInt("trainer_id");
							result[i][j] = id+"";
						}
						else if(list[j] == 1)
							result[i][j] = rs.getString("trainer_name");
				
						else if(list[j] == 2)
						{
							int locId = rs.getInt("location_id");
							result[i][j] = locId + "";
						}
					}                              
				}
				
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
						while(result[i][j].length() < 22)
							result[i][j] = result[i][j] + " ";
				}
				
				for(int i = 0; i < result.length; i++)
					while(result[i][1].length() < 25)
						result[i][1] = result[i][1] + " ";
					
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						System.out.print("||");
						System.out.print(result[i][j]);
					}
					
					System.out.println("||");
				}
				
				System.out.print("========================");	
				borderMaker(count);   
				
				System.out.println("\n");
				System.out.println("This is your Trainers table");
				System.out.print("Please enter the row's id number that you wish to update: ");
				choice2 = input.nextInt();
            
            sql = "DELETE FROM Trainers WHERE trainer_id = "+ choice2;
            
            System.out.println("Your SQL Command is " + sql);
				
				System.out.print("Do you want to commit to these changes?\nEnter 1 for yes and 2 for no: ");
				input.nextLine();
				int confirm = input.nextInt();
				
				if(confirm == 1)
				{
					int changes = statement.executeUpdate(sql);
					System.out.print("\n"+changes+" change(s) made.\n");
				}
				else
					System.out.println("\nNo changes made.\n");

         }
         else if(choice == 4)// If Locations
         {
            String sql = "SELECT * FROM Locations";
				
				String[] tableAttrTop = {"location_id           ||","location_name         ||"};
			   String[] tableAttr = {"location_id","location_name"};
				
				int list[] = {0,1};
				int count = 2;
				
            
				rs = statement.executeQuery(sql);
				if (!rs.first()) 
            {
               System.out.println("That entry either does not exist or the table is empty");
               continue;
            }
            rs.beforeFirst();
            
				int size = 0;
				while(rs.next())
					size++;
				
				rs.beforeFirst();
				String[][] result = new String[size][count];
				
				System.out.println("Locations table");
				
				System.out.print("==============");
				borderMaker(count);                     
				System.out.print("||");
				
				for(int i = 0; i < count; i++)
				{
					System.out.print(tableAttrTop[list[i]]);
				}
				System.out.println();
				
				System.out.print("==============");
				borderMaker(count);   
				
				for(int i = 0; i < result.length && rs.next(); i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						if(list[j] == 0)
						{
							int id = rs.getInt("location_id");
							result[i][j] = id+"";
						}
						else if(list[j] == 1)
							result[i][j] = rs.getString("location_name");
					}                              
				}
				
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
						while(result[i][j].length() < 22)
							result[i][j] = result[i][j] + " ";
				}
				
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						System.out.print("||");
						System.out.print(result[i][j]);
					}
					
					System.out.println("||");
				}
				System.out.print("==============");
				borderMaker(count);   
				
				System.out.println("\n");
				System.out.println("This is your Trainers table");
				System.out.print("Please enter the row's id number that you wish to update: ");
				choice2 = input.nextInt();
            
            sql = "DELETE FROM Locations WHERE location_id = "+ choice2;
            
            System.out.println("Your SQL Command is " + sql);
				
				System.out.print("Do you want to commit to these changes?\nEnter 1 for yes and 2 for no: ");
				input.nextLine();
				int confirm = input.nextInt();
				
				if(confirm == 1)
				{
					int changes = statement.executeUpdate(sql);
					System.out.print("\n"+changes+" change(s) made.\n");
				}
				else
					System.out.println("\nNo changes made.\n");
         }
         else if(choice == 5)// If PokeMoves
         {
            String sql = "SELECT pm_id, pokemon_name, move_name FROM Pokemon, PokeMoves, Moves WHERE Pokemon.pokemon_id = PokeMoves.pokemon_id AND Moves.move_id = PokeMoves.move_id";
				
				String[] tableAttrTop = {"pm_id                   ||","pokemon_name            ||","move_name               ||"};
			   String[] tableAttr = {"pm_id","pokemon_name","move_name"};
            
            int count = 3;
            int[] list = {0,1,2};
				
				rs = statement.executeQuery(sql);
				if (!rs.first()) 
            {
               System.out.println("That entry either does not exist or the table is empty");
               continue;
            }
            rs.beforeFirst();
            
				int size = 0;
				while(rs.next())
					size++;
				
				rs.beforeFirst();
				String[][] result = new String[size][count];
				
				System.out.println("PokeMoves table");
				
				System.out.print("===========================");
				borderMaker(count);                     
				System.out.print("||");
				
				for(int i = 0; i < count; i++)
				{
					System.out.print(tableAttrTop[list[i]]);
				}
				System.out.println();
				
				System.out.print("===========================");
				borderMaker(count);   
				
				for(int i = 0; i < result.length && rs.next(); i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						if(list[j] == 0)
						{
							int id = rs.getInt("pm_id");
							result[i][j] = id+"";
						}
						else if(list[j] == 1)
							result[i][j] = rs.getString("pokemon_name");
						else if(list[j] == 2)
							result[i][j] = rs.getString("move_name");
					}                              
				}
				
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
						while(result[i][j].length() < 24)
							result[i][j] = result[i][j] + " ";
				}
					
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						System.out.print("||");
						System.out.print(result[i][j]);
					}
					
					System.out.println("||");
				}
				
				System.out.print("===========================");	
				borderMaker(count);   
				
				System.out.println("\n");
				System.out.println("This is your Pokemon & Moves table");
				System.out.print("Please enter the row's id number that you wish to update: ");
				choice2 = input.nextInt();
            
            sql = "DELETE FROM PokeMoves WHERE pm_id = "+ choice2;
            
            System.out.println("Your SQL Command is " + sql);
				
				System.out.print("Do you want to commit to these changes?\nEnter 1 for yes and 2 for no: ");
				input.nextLine();
				int confirm = input.nextInt();
				
				if(confirm == 1)
				{
					int changes = statement.executeUpdate(sql);
					System.out.print("\n"+changes+" change(s) made.\n");
				}
				else
					System.out.println("\nNo changes made.\n");
         }
         else if(choice == 6)// If PokeTrainers
         {
            String sql = "SELECT pt_id, pokemon_name, trainer_name FROM Pokemon, PokeTrainers, Trainers WHERE Pokemon.pokemon_id = PokeTrainers.pokemon_id AND Trainers.trainer_id = PokeTrainers.trainer_id";
				
				String[] tableAttrTop = {"pt_id                   ||","pokemon_name            ||","trainer_name            ||"};
			   String[] tableAttr = {"pt_id","pokemon_name","trainer_name"};
            
            int count = 3;
            int[] list = {0,1,2};
				
				rs = statement.executeQuery(sql);
				if (!rs.first()) 
            {
               System.out.println("That entry either does not exist or the table is empty");
               continue;
            }
            rs.beforeFirst();
            
				int size = 0;
				while(rs.next())
					size++;
				
				rs.beforeFirst();
				String[][] result = new String[size][count];
				
				System.out.println("PokeTrainers table");
				
				System.out.print("===========================");
				borderMaker(count);                     
				System.out.print("||");
				
				for(int i = 0; i < count; i++)
				{
					System.out.print(tableAttrTop[list[i]]);
				}
				System.out.println();
				
				System.out.print("===========================");
				borderMaker(count);   
				
				for(int i = 0; i < result.length && rs.next(); i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						if(list[j] == 0)
						{
							int id = rs.getInt("pt_id");
							result[i][j] = id+"";
						}
						else if(list[j] == 1)
							result[i][j] = rs.getString("pokemon_name");
						else if(list[j] == 2)
							result[i][j] = rs.getString("trainer_name");
					}                              
				}
				
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
						while(result[i][j].length() < 24)
							result[i][j] = result[i][j] + " ";
				}
					
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						System.out.print("||");
						System.out.print(result[i][j]);
					}
					
					System.out.println("||");
				}
				
				System.out.print("===========================");	
				borderMaker(count);   
				
				System.out.println("\n");
				System.out.println("This is your Pokemon & Trainers table");
				System.out.print("Please enter the row's id number that you wish to update: ");
				choice2 = input.nextInt();
            
            sql = "DELETE FROM PokeTrainers WHERE pt_id = "+ choice2;
            
            System.out.println("Your SQL Command is " + sql);
				
				System.out.print("Do you want to commit to these changes?\nEnter 1 for yes and 2 for no: ");
				input.nextLine();
				int confirm = input.nextInt();
				
				if(confirm == 1)
				{
					int changes = statement.executeUpdate(sql);
					System.out.print("\n"+changes+" change(s) made.\n");
				}
				else
					System.out.println("\nNo changes made.\n");
         }
         else if(choice == 7)// If PokeLocations
         {
            String sql = "SELECT pl_id, pokemon_name, location_name FROM Pokemon, PokeLocations, Locations WHERE Pokemon.pokemon_id = PokeLocations.pokemon_id AND Locations.location_id = PokeLocations.location_id";
				
				String[] tableAttrTop = {"pl_id                   ||","pokemon_name            ||","location_name            ||"};
			   String[] tableAttr = {"pl_id","pokemon_name","location_name"};
            
            int count = 3;
            int[] list = {0,1,2};
				
				rs = statement.executeQuery(sql);
				if (!rs.first()) 
            {
               System.out.println("That entry either does not exist or the table is empty");
               continue;
            }
            rs.beforeFirst();
            
				int size = 0;
				while(rs.next())
					size++;
				
				rs.beforeFirst();
				String[][] result = new String[size][count];
				
				System.out.println("PokeLocations table");
				
				System.out.print("===========================");
				borderMaker(count);                     
				System.out.print("||");
				
				for(int i = 0; i < count; i++)
				{
					System.out.print(tableAttrTop[list[i]]);
				}
				System.out.println();
				
				System.out.print("===========================");
				borderMaker(count);   
				
				for(int i = 0; i < result.length && rs.next(); i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						if(list[j] == 0)
						{
							int id = rs.getInt("pl_id");
							result[i][j] = id+"";
						}
						else if(list[j] == 1)
							result[i][j] = rs.getString("pokemon_name");
						else if(list[j] == 2)
							result[i][j] = rs.getString("location_name");
					}                              
				}
				
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
						while(result[i][j].length() < 24)
							result[i][j] = result[i][j] + " ";
				}
					
				for(int i = 0; i < result.length; i++)
				{
					for(int j = 0; j < result[i].length; j++)
					{
						System.out.print("||");
						System.out.print(result[i][j]);
					}
					
					System.out.println("||");
				}
				
				System.out.print("===========================");	
				borderMaker(count);   
				
				System.out.println("\n");
				System.out.println("This is your Pokemon & Locations table");
				System.out.print("Please enter the row's id number that you wish to update: ");
				choice2 = input.nextInt();
            
            sql = "DELETE FROM PokeLocations WHERE pl_id = "+ choice2;
            
            System.out.println("Your SQL Command is " + sql);
				
				System.out.print("Do you want to commit to these changes?\nEnter 1 for yes and 2 for no: ");
				input.nextLine();
				int confirm = input.nextInt();
				
				if(confirm == 1)
				{
					int changes = statement.executeUpdate(sql);
					System.out.print("\n"+changes+" change(s) made.\n");
				}
				else
					System.out.println("\nNo changes made.\n");
         }
      }while(choice != 8);
   }
   
   public static String pokemonOrderBy(String[] arr)// pokemonOrderBy()
   {
      System.out.println("\nPlease select which value to sort by");
      System.out.println("Enter 1 for ID number");
      System.out.println("Enter 2 for Name");
      System.out.println("Enter 3 for First type");
      System.out.println("Enter 4 for Second type");
      System.out.println("Enter 5 for Hp stats");
      System.out.println("Enter 6 for Attack stats");
      System.out.println("Enter 7 for Defence stats");
      System.out.println("Enter 8 for Special Attack stats");
      System.out.println("Enter 9 for Special Defence stats");
      System.out.println("Enter 10 for Speed stats");
      System.out.println("Enter 11 for Total stats");
      System.out.print("Enter your value here: ");
      int sort = input.nextInt();
           
      while(sort > 12||sort < 1)
      {
         System.out.println("Please choose at least one value before continuing: ");
         sort = input.nextInt();
      }
      String a = " ORDER BY "+arr[sort-1]; 
      return a;
   }// End pokemonOrderBy()   
   
   public static String movesOrderBy(String[] arr)// movesOrderBy()
   {
      System.out.println("What value do you want to sort by?");
      System.out.println("Enter 1 for ID number");
      System.out.println("Enter 2 for Name");
      System.out.println("Enter 3 for Type");
      System.out.println("Enter 4 for Power Points");
      System.out.println("Enter 5 for Move Power Stats");
      System.out.println("Enter 6 for Move Accuracy Stats");
      System.out.println("Enter 7 for Move Description");
      int sort = input.nextInt();
      while(sort > 7||sort < 1)
      {
         System.out.print("\nPlease choose at least one value before continuing: ");
         sort = input.nextInt();
      }
      String a = " ORDER BY "+arr[sort-1]; 
      return a;
   }
   
   public static String locationOrderBy(String[] arr)// locationOrderBy() [This is for trainers, whoops]
   {
      System.out.println("What value do you want to sort by?");
      System.out.println("Enter 1 for Trainer ID number");
      System.out.println("Enter 2 for Trainer name");
      System.out.println("Enter 3 for Location name");
      int sort = input.nextInt();
      while(sort > 3||sort < 1)
      {
         System.out.print("\nPlease choose at least one value before continuing: ");
         sort = input.nextInt();
      }
      String a = " ORDER BY "+arr[sort-1];
         if(a.equals(" ORDER BY location_id"))
            a = " ORDER BY "+"location_name"; 
      return a;
   }
   
   public static String ascDescMethod()// ascDescMethod()
   {
      System.out.println("\nAscending or Descending?");
      System.out.print("Enter 1 for ascending and 2 for descending: ");
      int sort = input.nextInt();
      
      while(sort > 12||sort < 1)
      {
         System.out.println("Please choose at least one value before continuing: ");
         sort = input.nextInt();
      }
      String a = "";
      
      if(sort == 1)
         a = " ASC";
      else
         a = " DESC";
         
      return a;
   }// End ascDescMethod()
   
   public static String addQuotes(String s)// addQuotes()
   {
      s = "'"+s+"'";
      return s;
   }// End addQuotes()
   
   public static void borderMaker(int a)// borderMaker()
   {
      System.out.print("=");
         for(int i = 0; i < a * 17; i++)
            System.out.print("=");
     System.out.print("=");
     System.out.println();
   }// end borderMaker()
   
   public static void borderMakerDesc(int a, boolean tf)// borderMakerDesc()
   {
      if(tf == true)
         System.out.print("=========================================================================================================");
      
      System.out.print("=");
      for(int i = 0; i < a * 17; i++)
         System.out.print("=");
      System.out.print("=");
      System.out.println();
   }// End borderMakerDesc()


   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   //This was used in my program as a sort of safety feature when working together with people
   //Just so someone looking over my shoulder couldn't grab my password
   //Ignore this unless you wanna use it
   
   public static String getPassword()
   {
      System.out.print("Enter your password: ");
      String password = input.next();
      System.out.println();
      return password;
   }
}