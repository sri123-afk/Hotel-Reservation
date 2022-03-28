package com.company;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hotel {
    /**
     * Creating methods before initialising the main program
     */

    public static Scanner input = new Scanner(System.in);  // Making public scanner in the beginning so that we don't need to assign it everytime in each method.
    public static Room[] hotel = new Room[9];    // Making arrays to store the numbers and names of the user
    public static ArrayList<String> queueList = new ArrayList<>();


    public static void initialise(Room[] hotel) {
        for (int x = 1; x < hotel.length; x++) {  // Initialising in the beginning by calculating the length of the hotel using for loops.
            hotel[x] = new Room("e");
        }
        System.out.println("Hotel Program");

    }


    public static void ViewRoom(Room[] hotel) { // Setting up view room to show both empty rooms and the occupied
        for (int x = 1; x < hotel.length; x++) {
            if (hotel[x].name.equals("e")) {
                System.out.println("Room " + x + " is empty");
            } else {
                System.out.println("Room " + x + " occupied by " + hotel[x].name + "\nNo of Guests " + hotel[x].guests +
                        "\nFirst Name " + hotel[x].first + "\nSurname " + hotel[x].surname +
                        "\nCredit Card No " + hotel[x].creditCard);
            }
            System.out.println("*************************************");
        }
    }

    public static void addRoom(Room[] hotel) {         // Adding rooms from 1 to 8 and making name equals "e" and asking user to input name of the room, no of guests, firstname, surname and credit card number.


        int x=0;
        for (int i = 1; i < hotel.length; i++) {
            if (!(hotel[i].name.equals("e"))) {
                x += 1;
            }
        }

        if (x == 8) {
            System.out.println("Since All the Rooms are Occupied You will be Added to the waiting list. \nPlease enter your room name to add in the waiting list:\n When a room is vacant, You will be added");

            queueList.add(input.next().toUpperCase());

        }else {
            System.out.println("Enter a room from (1-8)");
            int roomNum = input.nextInt();

            if (roomNum < 1 || roomNum > 8) {
                System.out.println("Invalid Selection");

            } else {
                if (hotel[roomNum].name.equals("e")) {
                    System.out.println("Enter the name for room " + roomNum + " : ");
                    String roomName = input.next().toUpperCase();
                    hotel[roomNum] = new Room(roomName);


                    System.out.println("Enter the no of guests : ");
                    String guest = input.next();
                    hotel[roomNum].guests(guest);
                    System.out.println("**********************************");

                    System.out.println("Enter Your First Name : ");
                    String first = input.next();
                    hotel[roomNum].first(first);
                    System.out.println("**********************************");

                    System.out.println("Enter Your Surname : ");
                    String surname = input.next();
                    hotel[roomNum].surname(surname);
                    System.out.println("**********************************");

                    System.out.println("Enter Your Credit Card No  : ");
                    String creditCard = input.next();
                    hotel[roomNum].creditCard(creditCard);
                    System.out.println("**********************************");

                    System.out.println("Successfully Recorded..");
                    System.out.println("**********************************");

                } else {
                    System.out.println("This room is already taken by " + hotel[roomNum].name);    // If user tries to enter in the same room number it will print the person has already taken the room.
                    System.out.println("**********************************************************");
                }
            }
        }
    }

    public static void display(Room[] hotel) {    // Display only rooms which are empty as set being "e".
        for (int x = 1; x < hotel.length; x++) {
            if (hotel[x].name.equals("e"))
            {
                System.out.println("Room " + x + " is empty");
            }
            System.out.println("**********************************");
        }

    }

    public static void delete(Room[] hotel) {      // Asking the user to input the a room number to delete if the room number does not have a string "e" it should delete.
        System.out.println("Enter room from (1-8) to delete");
        int roomNum = input.nextInt();
        if (!hotel[roomNum].name.equals("e"))
        {
            hotel[roomNum].name = "e";
            System.out.println("Deleted");
            int x=0;
            for (int i = 1; i < hotel.length; i++) {
                if (!(hotel[i].name.equals("e"))) {
                    x += 1;
                }
            }

            if (x == 7 && !(queueList.isEmpty())) {
                hotel[roomNum].name = queueList.get(0);

                queueList.remove(0);

                System.out.println("Number of Guests Staying : ");
                String guest = input.next();
                hotel[roomNum].guests = guest;
                System.out.println("Enter Your First name : ");
                String FName = input.next();
                hotel[roomNum].first = FName;
                System.out.println("Enter Your Surname");
                String SName = input.next();
                hotel[roomNum].surname = SName;
                System.out.println("Enter Your Credit Card number");
                String CName = input.next();
                hotel[roomNum].creditCard = CName;
            }
        } else {
            System.out.println("There is nothing to delete ");
        }
        System.out.println("**********************************");
    }


    public static void findRoom(Room[] hotel) {   // Asking the user to input room name that has been stored, once the user types in should output the details such as room name, surname, firstname, guests and credit card number.
        System.out.println("Type the name of the room");
        String roomName = input.next().toUpperCase();
        int count = 0;
        boolean order = true;
        for (int x = 1; x < hotel.length; x++)
        {
            if (hotel[x].name.equals(roomName))
            {
                System.out.println("The person is in room " + x +
                        "\nRoom Name " + hotel[x].name + "\nFirst Name " + hotel[x].first +
                        "\nSurname " + hotel[x].surname + "\nCredit Card No " + hotel[x].creditCard);
                order = false;
                break;
            } else {
                count += 1;
            }
        }
        if (order) {
            if (count > 0) {
                System.out.println("Name invalid");
            }
        }
        System.out.println("**********************************");

    }

    public static void storeRoom(Room[] hotel) throws IOException {  // Using 5 objects and 5 different text files to store the data room name, surname, first name, guests number and credit card number.
        File myObj = new File("hotelN.txt");
        myObj.createNewFile();  //A new file is created using the method
        FileWriter myWriter = new FileWriter(myObj.getName());
        for (int x = 1; x < hotel.length; x++)
        {
            myWriter.write(hotel[x].name + "\n");
        }
        myWriter.close();

        File myObj1 = new File("hotelG.txt");
        myObj.createNewFile();
        FileWriter myWriter1 = new FileWriter(myObj1.getName());
        for (int x = 1; x < hotel.length; x++)
        {
            myWriter1.write(hotel[x].guests + "\n");
        }
        myWriter1.close();

        File myObj2 = new File("hotelF.txt");
        myObj.createNewFile();
        FileWriter myWriter2 = new FileWriter(myObj2.getName());
        for (int x = 1; x < hotel.length; x++)
        {
            myWriter2.write(hotel[x].first + "\n");
        }
        myWriter2.close();

        File myObj3 = new File("hotelS.txt");
        myObj.createNewFile();
        FileWriter myWriter3 = new FileWriter(myObj3.getName());
        for (int x = 1; x < hotel.length; x++)
        {
            myWriter3.write(hotel[x].surname + "\n");
        }
        myWriter3.close();

        File myObj4 = new File("hotelC.txt");
        myObj.createNewFile();
        FileWriter myWriter4 = new FileWriter(myObj4.getName());
        for (int x = 1; x < hotel.length; x++)
        {
            myWriter4.write(hotel[x].creditCard + "\n");
        }
        myWriter4.close();
        System.out.println("Added Successfully..");
        System.out.println("**********************");




    }


    public static void loadRoom() throws IOException {  // Creating 5 variables and assigning it to 5 objects to and store it in 5 different files using while loop.
        int x = 1;
        int i = 1;
        int j = 1;
        int k = 1;
        int l = 1;

        File myObj = new File("hotelN.txt");
        File myObj1 = new File("hotelG.txt");
        File myObj2 = new File("hotelF.txt");
        File myObj3 = new File("hotelS.txt");
        File myObj4 = new File("hotelC.txt");

        Scanner myReader = new Scanner(myObj);
        Scanner myReader1 = new Scanner(myObj1);
        Scanner myReader2 = new Scanner(myObj2);
        Scanner myReader3 = new Scanner(myObj3);
        Scanner myReader4 = new Scanner(myObj4);

        while (myReader.hasNextLine())
        {      //
            String data = myReader.nextLine();
            hotel[x].name = data;
            x++;
        }

        while (myReader1.hasNextLine())
        {
            String data1 = myReader1.nextLine();
            hotel[i].guests = data1;
            i++;
        }
        while (myReader2.hasNextLine())
        {
            String data2 = myReader2.nextLine();
            hotel[j].first = data2;
            j++;
        }
        while (myReader3.hasNextLine())
        {
            String data3 = myReader3.nextLine();
            hotel[k].surname = data3;
            k++;
        }
        while (myReader4.hasNextLine())
        {
            String data4 = myReader4.nextLine();
            hotel[l].creditCard = data4;
            l++;
        }
        System.out.println("Loaded Successfully...");
        System.out.println("**********************************");

    }




    public static void orderAlpha(Room[] hotel){      // Once the user inputs the room name and clicks on letter "o" the letters should compare to each names using for loops and print from a to z.
        int count = 0;
        for (int x = 1; x < 9; x++) {
            if (!(hotel[x].name.equals("e"))) {
                count += 1;
            }
        }
        int count1 = 0;
        String[] order = new String[count];
        for (int x = 1; x < 9; x++) {
            if (!(hotel[x].name.equals("e"))) {
                order[count1] = hotel[x].name;
                count1 += 1;
            }
        }
        boolean swap = true;
        while (swap) {
            swap = false;
            for (int i = 1; i < order.length - 1; i++) {
                if (order[i].compareToIgnoreCase(order[i + 1]) > 0) {
                    String temp = order[i + 1];
                    order[i + 1] = order[i];
                    order[i] = temp;
                    swap = true;
                }
            }

        }
        for (String value : order)
        {
            System.out.println(value);
        }
        System.out.println("**********************************");
    }




    public static void main(String[] args) throws IOException {


        initialise(hotel);  // initialise at first since making the initial value as "e"

        boolean stop = false;

        while(!stop)   // Using while loop to run the menu and methods until the user presses exit.
        {
            System.out.println("**********************************");
            System.out.println("Enter 'A' to customer rooms\n"+"Enter 'V' to views all rooms\n"+"Enter 'E' to display Empty rooms\n"+
                    "Enter 'D' to delete customer from rooms\n"+"Enter 'F' to find room for customer\n"+
                    "Enter 'S' Store program data into file\n"+"Enter 'L' Load program data from file\n"+
                    "Enter 'O' View guests Ordered alphabetically by name\n"+"Type 'Exit' to stop program");
            System.out.println("**********************************");
            System.out.println("Enter Your Choice: ");

            String menu = input.next().toUpperCase();

            switch (menu){    // Using switch cases to make the user select options using letters to activate the methods
                case "V":
                    ViewRoom(hotel);
                    break;
                case "A":
                    addRoom(hotel);
                    break;
                case "E":
                    display(hotel);
                    break;
                case "D":
                    delete(hotel);
                    break;
                case "F":
                    findRoom(hotel);
                    break;
                case "S":
                    storeRoom(hotel);
                    break;
                case "L":
                    loadRoom();
                    break;
                case "O":
                    orderAlpha(hotel);
                default:
                    if(menu.equals("EXIT"))
                    {  // making the system exit if the user wants to stop the program.
                        System.out.println("The System is about to exit");
                        System.out.println("**********************************");
                        stop = true;
                    }
            }
        }




    }
}




