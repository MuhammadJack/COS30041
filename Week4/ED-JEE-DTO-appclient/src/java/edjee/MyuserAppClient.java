/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edjee;

import javax.ejb.EJB;
import entity.MyuserDTO;
import java.util.ArrayList;
import java.util.Scanner;
import session.MyuserFacadeRemote;

/**
 *
 * @author gamal
 */
public class MyuserAppClient {

    @EJB
    private static MyuserFacadeRemote myuserFacade;

    /**
     * @param args the command line arguments
     */
    public MyuserAppClient() {
    }

    public static void main(String[] args) {
        Scanner c = new Scanner(System.in);
        Scanner c1 = new Scanner(System.in);
        int choice = c.nextInt();
        MyuserAppClient client = new MyuserAppClient();
// assuming inputs from keyboard or any GUI
        MyuserDTO myuserDTO = new MyuserDTO("000001", "Peter Smith", "123456",
                "psmith@swin.edu.au", "9876543210", "Swinburne EN510f",
                "What is my name?", "Peter");
        boolean result = client.createRecord(myuserDTO);
        client.showCreateResult(result, myuserDTO);
// assuming inputs from keyboard or any GUI
        MyuserDTO myuserDTO2 = new MyuserDTO("000007", "David Lee", "654321",
                "dlee@swin.edu.au", "0123456789", "Swinburne EN510g",
                "What is my name?", "David");
        result = client.createRecord(myuserDTO2);
        client.showCreateResult(result, myuserDTO2);
        
        while (choice!=5) 
        {
            menu();
            
            
            switch (choice) {
                case 1:
                    System.out.println("\nEnter the ID of the record you would like to get:");
                    String choice2 = c1.nextLine();
                    MyuserDTO tempDTO;
                    tempDTO = myuserFacade.getRecord(choice2);
                    if (tempDTO == null) {
                        System.out.println("\nCould not find that record");
                    } else {
                        System.out.println("\nRecord Found");
                        System.out.println(tempDTO.getName());
                    }
                    break;
                case 2:
                    System.out.println("\nEnter the ID of the record you would like to Update:");
                    String USERID = c1.nextLine();
                    boolean check;

                    System.out.println("\nNew Name:");
                    String NAME = c1.nextLine();
                    System.out.println("\nNew Password:");
                    String PASSWORD = c1.nextLine();
                    System.out.println("\nNew Email:");
                    String EMAIL = c1.nextLine();
                    System.out.println("\nNew PhoneNumber:");
                    String PHONE = c1.nextLine();
                    System.out.println("\nNew Address:");
                    String ADDRESS = c1.nextLine();
                    System.out.println("\nNew SEC QUN:");
                    String SECQUN = c1.nextLine();
                    System.out.println("\nNew SEC ANS:");
                    String SECANS = c1.nextLine();
                    MyuserDTO tempDTO2 = new MyuserDTO(USERID, NAME, PASSWORD, EMAIL, PHONE, ADDRESS, SECQUN, SECANS);

                    check = myuserFacade.updateRecord(tempDTO2);
                    if (check == false) {
                        System.out.println("\nCould not find that record");
                    } else {
                        System.out.println("\nrecord Updated!");
                    }
                    break;
                case 3:
                    System.out.println("\nEnter the ID of the record you would like to Delete:");
                    String choice4 = c1.nextLine();
                    boolean checker;
                    checker = myuserFacade.deleteRecord(choice4);
                    if (checker == false) {
                        System.out.println("\nCould not find that record");
                    } else {
                        System.out.println("\nRecord Deleted");
                    }
                    break;
                case 4:
                    ArrayList<MyuserDTO> tempDTOList = new ArrayList<>();
                    System.out.println("\nEnter the address of the Record and we will retrieve all records with the same address");
                    String address = c1.nextLine();
                    tempDTOList = myuserFacade.getRecordsByAddress(address);

                    if (tempDTOList == null) {
                        System.out.println("\nCould not find any of thoese records");
                    } else {
                        for (MyuserDTO userDTO : tempDTOList) {
                            System.out.println(userDTO.getName());
                        }
                    }
                    break;
                case 5:
                    System.out.print("Program Successfully Endded");
                    break;
            }
        }
        
    }
    
    public static void menu()
    {
        System.out.println("\nWould you like to :");
        System.out.println("\n1: Get a Record");
        System.out.println("\n2: update a record");
        System.out.println("\n3: Delete a record");
        System.out.println("\n4: Get Record by Address");
        System.out.println("\n5: Exit the program");
    }

    public void showCreateResult(boolean result, MyuserDTO myuserDTO) {
        if (result) {
            System.out.println("Record with primary key " + myuserDTO.getUserid()
                    + " has been created in the database table.");
        } else {
            System.out.println("Record with primary key " + myuserDTO.getUserid()
                    + " could not be created in the database table!");
        }
    }

    public Boolean createRecord(MyuserDTO myuserDTO) {
        return myuserFacade.createRecord(myuserDTO);
    }

}
