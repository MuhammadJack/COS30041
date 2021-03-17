/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.jdbc;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author gamal
 */
public class SetUpMyUser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MyDB mydb = new MyDB();
        String ID = "";
        String Name = "";
        String Password = "";
        String email = "";
        String phone = "";
        String address = "";
        String secQn = "";
        String secAns = "";
        Scanner deets = new Scanner(System.in);

        int choice = 0;
        /**
         * drop table first for a clean start* may cause error if table does not
         * exist
         */
        mydb.dropMyuserTable();
        mydb.createMyuserTable();
        ArrayList<Myuser> aList = prepareMyuserData();
        mydb.addRecords(aList);

        while (choice != 4) {
            menu();
            System.out.println("\n\nPlease Select an option");
            Scanner in = new Scanner(System.in);
            choice = in.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n\nPlease Enter ID");
                    ID = deets.nextLine();
                    /*System.out.println("\n\nPlease Enter Name");
                    Name = deets.nextLine();
                    System.out.println("\n\nPlease Enter Password");
                    Password = deets.nextLine();
                    System.out.println("\n\nPlease Enter Email");
                    email = deets.nextLine();
                    System.out.println("\n\nPlease Enter phone");
                    phone = deets.nextLine();
                    System.out.println("\n\nPlease Enter address");
                    address = deets.nextLine();
                    System.out.println("\n\nPlease Enter Secret Question");
                    secQn = deets.nextLine();
                    System.out.println("\n\nPlease Enter Answer to " + secQn);
                    secAns = deets.nextLine();
                     */

                    Myuser muser = new Myuser(ID, Name, Password, email, phone, address, secQn, secAns);

                    if (mydb.createRecord(muser)) {
                        aList.add(muser);
                        System.out.println("successfully created new user");
                    } else {
                        System.out.println("This User Already Exists");
                    }
                    break;
                case 2:
                    System.out.println("\n\nPlease Enter ID of user to be updated");
                    ID = deets.nextLine();
                    System.out.println("\n\nPlease Enter Name");
                    Name = deets.nextLine();
                    /*System.out.println("\n\nPlease Enter Password");
                    Password = deets.nextLine();
                    System.out.println("\n\nPlease Enter Email");
                    email = deets.nextLine();
                    System.out.println("\n\nPlease Enter phone");
                    phone = deets.nextLine();
                    System.out.println("\n\nPlease Enter address");
                    address = deets.nextLine();
                    System.out.println("\n\nPlease Enter Secret Question");
                    secQn = deets.nextLine();
                    System.out.println("\n\nPlease Enter Answer to " + secQn);
                    secAns = deets.nextLine();
                     */
                    Myuser updateuser = new Myuser(ID, Name, Password, email, phone, address, secQn, secAns);
                    mydb.updateRecord(updateuser);
                    System.out.println("successfully updated user");
                    break;
                case 3:
                    System.out.println("\n\nPlease Enter ID of user to be deleted");
                    ID = deets.nextLine();
                    Myuser deleteuser = new Myuser(ID, Name, Password, email, phone, address, secQn, secAns);
                    mydb.deleteRecord(deleteuser);
                case 4:
                    System.out.println("Program Successfully ended");
                default:
                    System.out.println("invalid choice");
            }
        }
    }

    public static ArrayList<Myuser> prepareMyuserData() {
        ArrayList<Myuser> myList = new ArrayList<Myuser>();
        Myuser myuser1 = new Myuser("000001", "PeterSmith", "123456", "psmith@swin.edu.au", "9876543210", "Swinburne EN510f", "What is my name?", "Peter");
        Myuser myuser2 = new Myuser("000002", "James T. Kirk", "234567", "jkirk@swin.edu.au", "8765432109", "Swinburne EN511a", "What is my name?", "James");
        Myuser myuser3 = new Myuser("000003", "Sheldon Cooper", "345678", "scooper@swin.edu.au", "7654321098", "Swinburne EN512a", "What is my last name?", "Cooper");
        Myuser myuser4 = new Myuser("000004", "Clark Kent", "456789", "ckent@swin.edu.au", "6543210987", "SwinburneEN513a", "What is my last name?", "Kent");
        Myuser myuser5 = new Myuser("000005", "Harry Potter", "567890", "hpotter@swin.edu.au", "6543210987", "Swinburne EN514a", "Whatis my last name?", "Potter");
        myList.add(myuser1);
        myList.add(myuser2);
        myList.add(myuser3);
        myList.add(myuser4);
        myList.add(myuser5);
        return myList;
    }

    public static void menu() {
        System.out.println("Select 1 to Add user");
        System.out.println("Select 2 to update user");
        System.out.println("Select 3 to delete user");
        System.out.println("Select 4 to end Program");
    }
}
