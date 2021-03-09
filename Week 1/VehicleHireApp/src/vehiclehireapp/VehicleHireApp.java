
package vehiclehireapp;

import au.edu.swin.vehicle.Vehicle;
import au.edu.swin.vehicle.VehicleType;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Gam
 */
public class VehicleHireApp 
{
    /*** @param args the command line arguments*/
    public static void main(String[] args) 
    {
        int choice = 1;
        int i = 1;
        // Create the vehicle types
        ArrayList<VehicleType> vehiclesT = new ArrayList();
        VehicleType sedan =new VehicleType("SEDAN", "A standard sedan", 4);
        VehicleType limo6 =new VehicleType("LIMO6", "A six seater limo", 6);
        VehicleType limo8 =new VehicleType("LIMO8", "An eight seater limo", 8);
        vehiclesT.add(sedan);
        vehiclesT.add(limo6);
        vehiclesT.add(limo8);
        
 
        // Create the vehicles
        ArrayList<Vehicle> vehicles = new ArrayList();
        vehicles.add(new Vehicle("Ed's Holden Caprice", "Silver", sedan, 2002));
        vehicles.add(new Vehicle("John's Mercedes C200", "Black", sedan, 2005));
        vehicles.add(new Vehicle("Guy's Volvo 244 DL", "Blue", sedan, 1976));
        vehicles.add(new Vehicle("Sasco's Ford Limo", "White", limo6, 2014));
        vehicles.add(new Vehicle("Peter's Ford Limo", "White", limo6, 2004));
        vehicles.add(new Vehicle("Robert's Ford Limo", "White", limo8, 2003));

        System.out.println("\n\nList of vehicles in system:");
        for(Vehicle vehicle : vehicles) 
            {
                System.out.println(vehicle);
            }
        
        while (choice !=4)
        {
            System.out.println("\n\nList of vehicle types");
            for(VehicleType vehicle : vehiclesT) 
                {

                    System.out.println(i+": " + vehicle.getCode());
                    i++;
                }
            System.out.println(i+": EXIT"); 
            i = 1;
            System.out.println("\n\nPlease Select an option");
            Scanner in = new Scanner(System.in);
            choice = in.nextInt();
            switch(choice)
            {
                case 1:
                    Search("SEDAN", vehicles);
                    break;
                case 2:
                    Search("LIMO6", vehicles);
                    break;
                case 3:
                    Search("LIMO8", vehicles);
                    break;
                case 4:
                    System.out.println("Succesfully ended program");
                    break;
                default:
                    System.out.println("Not a valid Choice");
            }
            
        }
        
    }
    
    public static void Search(String type, ArrayList<Vehicle> v)
    {
        System.out.println("\n\nList of vehicle of type " + type);
        for(Vehicle vehicle : v) 
        {
            if(vehicle.getType().getCode().equals(type))
            {
                System.out.println(vehicle);
            }
        }
    }
}
