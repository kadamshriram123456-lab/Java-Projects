import java.util.Scanner;

class Room
{
    int roomNumber;
    String roomType;     //instance variables ;
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

    // ---------- NEW: update methods ----------

    void increaseDays(int extraDays)
    {
        this.days += extraDays;
    }

    // returns false if the reduction is not allowed (days must stay at least 1)
    boolean decreaseDays(int lessDays)
    {
        if (lessDays <= 0 || lessDays >= this.days)
        {
            return false;
        }
        this.days -= lessDays;
        return true;
    }

    void updateCustomerName(String newName)
    {
        this.customerName = newName;
    }

    void updatePhoneNumber(String newPhone)
    {
        this.phoneNumber = newPhone;
    }

    double getTotalBill()
    {
        return price * days;
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
            System.out.println("Total Bill    : Rs." + getTotalBill());
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

    // Reads an integer safely; returns -1 if the input is not a number
    static int readInt()
    {
        try
        {
            return Integer.parseInt(sc.nextLine().trim());
        }
        catch (NumberFormatException e)
        {
            return -1;
        }
    }

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
        int roomNumber = readInt();

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
        int days = readInt();

        if (name.trim().isEmpty() || phone.trim().isEmpty() || days <= 0)
        {
            System.out.println("Invalid booking details!");
            return;
        }

        rooms[index].bookRoom(name, phone, days);

        System.out.println("\nRoom Booked Successfully!");
        System.out.println("Room Type: " + rooms[index].roomType);
        System.out.println("Total Bill: Rs." + rooms[index].getTotalBill());
    }

    static void checkRoom()
    {
        System.out.print("\nEnter Room Number: ");
        int roomNumber = readInt();

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
        int roomNumber = readInt();

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

    // ---------- NEW: modify an existing booking ----------
    static void modifyBooking()
    {
        System.out.print("\nEnter Room Number: ");
        int roomNumber = readInt();

        int index = findRoom(roomNumber);

        if (index == -1)
        {
            System.out.println("Invalid Room Number!");
            return;
        }

        Room room = rooms[index];

        if (!room.isBooked)
        {
            System.out.println("This room is not booked, so there is nothing to modify.");
            return;
        }

        int option = 0;

        do
        {
            System.out.println("\n===== MODIFY BOOKING (Room " + room.roomNumber + ") =====");
            System.out.println("Customer : " + room.customerName);
            System.out.println("Phone    : " + room.phoneNumber);
            System.out.println("Days     : " + room.days);
            System.out.println("Bill     : Rs." + room.getTotalBill());
            System.out.println("--------------------------------");
            System.out.println("1. Increase Number of Days");
            System.out.println("2. Decrease Number of Days");
            System.out.println("3. Update Customer Name");
            System.out.println("4. Update Phone Number");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter Your Choice: ");

            option = readInt();

            switch (option)
            {
                case 1:
                    increaseDays(room);
                    break;

                case 2:
                    decreaseDays(room);
                    break;

                case 3:
                    updateName(room);
                    break;

                case 4:
                    updatePhone(room);
                    break;

                case 5:
                    break;

                default:
                    System.out.println("Invalid Choice! Try Again.");
            }

        } while (option != 5);
    }

    static void increaseDays(Room room)
    {
        System.out.print("Enter number of days to ADD: ");
        int extra = readInt();

        if (extra <= 0)
        {
            System.out.println("Please enter a valid number of days (1 or more).");
            return;
        }

        room.increaseDays(extra);
        System.out.println("Days increased by " + extra + ". New stay: " + room.days + " days.");
        System.out.println("Updated Total Bill: Rs." + room.getTotalBill());
    }

    static void decreaseDays(Room room)
    {
        System.out.print("Enter number of days to REDUCE: ");
        int less = readInt();

        if (less <= 0)
        {
            System.out.println("Please enter a valid number of days (1 or more).");
            return;
        }

        if (room.decreaseDays(less))
        {
            System.out.println("Days reduced by " + less + ". New stay: " + room.days + " days.");
            System.out.println("Updated Total Bill: Rs." + room.getTotalBill());
        }
        else
        {
            System.out.println("Cannot reduce by " + less + " days. The booking has only "
                               + room.days + " day(s); at least 1 day must remain.");
            System.out.println("To remove the booking entirely, use 'Cancel Booking'.");
        }
    }

    static void updateName(Room room)
    {
        System.out.print("Enter new Customer Name: ");
        String newName = sc.nextLine();

        if (newName.trim().isEmpty())
        {
            System.out.println("Name cannot be empty!");
            return;
        }

        room.updateCustomerName(newName.trim());
        System.out.println("Customer name updated successfully.");
    }

    static void updatePhone(Room room)
    {
        System.out.print("Enter new Phone Number: ");
        String newPhone = sc.nextLine();

        if (newPhone.trim().isEmpty())
        {
            System.out.println("Phone number cannot be empty!");
            return;
        }

        room.updatePhoneNumber(newPhone.trim());
        System.out.println("Phone number updated successfully.");
    }

    static void cancelBooking()
    {
        System.out.print("\nEnter Room Number: ");
        int roomNumber = readInt();

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
            System.out.println("6. Modify Booking (Days / Name / Phone)");
            System.out.println("7. Cancel Booking");
            System.out.println("8. Exit");
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
                    modifyBooking();
                    break;

                case 7:
                    cancelBooking();
                    break;

                case 8:
                    System.out.println("Thank You for Using Hotel Booking System!");
                    break;

                default:
                    System.out.println("Invalid Choice! Try Again.");
            }

        } while (choice != 8);

        sc.close();
    }
}
