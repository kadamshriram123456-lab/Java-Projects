import java.util.Scanner;

class Room
{
    int roomNumber;
    String roomType;
    double price;
    boolean isBooked;

    String customerName;
    String phoneNumber;
    int days;

    Room(int roomNumber, String roomType, double price)
    {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.isBooked = false;
    }

    void bookRoom(String name, String phone, int days)
    {
        this.customerName = name;
        this.phoneNumber = phone;
        this.days = days;
        this.isBooked = true;
    }

    void cancelBooking()
    {
        this.customerName = "";
        this.phoneNumber = "";
        this.days = 0;
        this.isBooked = false;
    }

    void displayRoom()
    {
        System.out.println("--------------------------------");
        System.out.println("Room Number : " + roomNumber);
        System.out.println("Room Type   : " + roomType);
        System.out.println("Price/Day   : Rs." + price);

        if (isBooked)
        {
            System.out.println("Status      : BOOKED");
        }
        else
        {
            System.out.println("Status      : AVAILABLE");
        }
    }

    void displayBookingDetails()
    {
        displayRoom();

        if (isBooked)
        {
            System.out.println("Customer Name : " + customerName);
            System.out.println("Phone Number  : " + phoneNumber);
            System.out.println("Days          : " + days);
            System.out.println("Total Bill    : Rs." + (price * days));
        }
    }
}

public class HotelRoomBooking
{
    static Scanner sc = new Scanner(System.in);

    static Room[] rooms = {
        new Room(101, "Non-Luxury", 1000),
        new Room(102, "Non-Luxury", 1000),
        new Room(103, "Non-Luxury", 1000),
        new Room(104, "Luxury", 2500),
        new Room(105, "Luxury", 2500),
        new Room(106, "Luxury", 2500)
    };

    static int findRoom(int roomNumber)
    {
        for (int i = 0; i < rooms.length; i++)
        {
            if (rooms[i].roomNumber == roomNumber)
            {
                return i;
            }
        }
        return -1;
    }

    static void displayAllRooms()
    {
        System.out.println("\n===== ALL HOTEL ROOMS =====");

        for (int i = 0; i < rooms.length; i++)
        {
            rooms[i].displayRoom();
        }
    }

    static void bookRoom()
    {
        System.out.print("\nEnter Room Number: ");
        int roomNumber = Integer.parseInt(sc.nextLine());

        int index = findRoom(roomNumber);

        if (index == -1)
        {
            System.out.println("Invalid Room Number!");
            return;
        }

        if (rooms[index].isBooked)
        {
            System.out.println("Sorry! This room is already booked.");
            return;
        }

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Phone Number: ");
        String phone = sc.nextLine();

        System.out.print("Enter Number of Days: ");
        int days = Integer.parseInt(sc.nextLine());

        if (name.trim().isEmpty() || phone.trim().isEmpty() || days <= 0)
        {
            System.out.println("Invalid booking details!");
            return;
        }

        rooms[index].bookRoom(name, phone, days);

        System.out.println("\nRoom Booked Successfully!");
        System.out.println("Room Type: " + rooms[index].roomType);
        System.out.println("Total Bill: Rs." +
                           (rooms[index].price * days));
    }

    static void checkRoom()
    {
        System.out.print("\nEnter Room Number: ");
        int roomNumber = Integer.parseInt(sc.nextLine());

        int index = findRoom(roomNumber);

        if (index == -1)
        {
            System.out.println("Room Not Found!");
        }
        else
        {
            rooms[index].displayRoom();
        }
    }

    static void displayAllBookingDetails()
    {
        System.out.println("\n===== ALL ROOM BOOKING DETAILS =====");

        for (int i = 0; i < rooms.length; i++)
        {
            rooms[i].displayBookingDetails();
        }
    }

    static void searchBooking()
    {
        System.out.print("\nEnter Room Number: ");
        int roomNumber = Integer.parseInt(sc.nextLine());

        int index = findRoom(roomNumber);

        if (index == -1)
        {
            System.out.println("Room Not Found!");
        }
        else if (!rooms[index].isBooked)
        {
            System.out.println("This room is currently available.");
        }
        else
        {
            System.out.println("\n===== BOOKING DETAILS =====");
            rooms[index].displayBookingDetails();
        }
    }

    static void cancelBooking()
    {
        System.out.print("\nEnter Room Number: ");
        int roomNumber = Integer.parseInt(sc.nextLine());

        int index = findRoom(roomNumber);

        if (index == -1)
        {
            System.out.println("Invalid Room Number!");
        }
        else if (!rooms[index].isBooked)
        {
            System.out.println("This room is not booked.");
        }
        else
        {
            rooms[index].cancelBooking();
            System.out.println("Booking Cancelled Successfully!");
        }
    }

    public static void main(String[] args)
    {
        int choice = 0;

        do
        {
            System.out.println("\n================================");
            System.out.println("   HOTEL ROOM BOOKING SYSTEM");
            System.out.println("================================");
            System.out.println("1. Display All Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Check Room Availability");
            System.out.println("4. Display All Booking Details");
            System.out.println("5. Search Booking Details");
            System.out.println("6. Cancel Booking");
            System.out.println("7. Exit");
            System.out.print("Enter Your Choice: ");

            try
            {
                choice = Integer.parseInt(sc.nextLine());
            }
            catch (NumberFormatException e)
            {
                System.out.println("Please enter a valid number!");
                continue;
            }

            switch (choice)
            {
                case 1:
                    displayAllRooms();
                    break;

                case 2:
                    bookRoom();
                    break;

                case 3:
                    checkRoom();
                    break;

                case 4:
                    displayAllBookingDetails();
                    break;

                case 5:
                    searchBooking();
                    break;

                case 6:
                    cancelBooking();
                    break;

                case 7:
                    System.out.println("Thank You for Using Hotel Booking System!");
                    break;

                default:
                    System.out.println("Invalid Choice! Try Again.");
            }

        } while (choice != 7);

        sc.close();
    }
}