package project.pkg1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Serra Ozturk
 */
public class testMeeting {

    public static ArrayList<Person> users = new ArrayList<>();
    public static ArrayList<Meeting> allMeetings = new ArrayList<>();
    public static int index;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Create a list of users, -1 to continue with menu: ");

        while (true) {
            System.out.println("Enter username: ");
            String name = input.next();
            if (name.equals("-1")) {
                System.out.println("");
                break;
            }
            Person createPerson = new Person(name);
            users.add(createPerson);
        }

        OUTER:
        while (true) {
            System.out.println("0. Login\n"
                    + "1. Create/Host new Meeting\n"
                    + "2. Cancel a meeting\n"
                    + "3. Attend an existing meeting\n"
                    + "4. Leave a meeting\n"
                    + "5. Display my Meetings\n"
                    + "6. Display Meetings organized by me\n"
                    + "7. Logout\n"
                    + "8. Exit quits the app.\n");

            System.out.print("Your choice: ");
            String menu = input.next();

            switch (menu) {
                case "0":
                    System.out.print("Enter user name to login: ");
                    String name = input.next();
                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).getName().equals(name)) {
                            index = i;
                            break;
                        }
                    }
                    System.out.println("");
                    break;
                case "1":
                    createMeeting();
                    System.out.println("");
                    break;
                case "2":
                    cancelMeeting();
                    System.out.println("");
                    break;
                case "3":
                    attendMeeting();
                    System.out.println("");
                    break;
                case "4":
                    leaveMeeting();
                    System.out.println("");
                    break;
                case "5":
                    if (users.get(index).getMyMeetings().isEmpty()) {
                        System.out.println("Your agenda is empty. ");
                    } else {
                        users.get(index).displayMyMeetings();
                    }
                    System.out.println("");
                    break;
                case "6":
                    if (users.get(index).getiOrganize().isEmpty()) {
                        System.out.println("You haven't organized any meeting yet! ");
                    } else {
                        users.get(index).displayMyOrganizations();
                    }
                    System.out.println("");
                    break;
                case "7":
                    System.out.println("Logged out of current user and Returns to main menu... ");
                    System.out.println("");
                    break;
                case "8":
                    System.out.println("Quits the app. Bye... ");
                    break OUTER;
                default:
                    break;
            }
        }
    }

    public static void createMeeting() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter name of the meeting: ");
        String mName = input.next();

        System.out.println("Enter date of the meeting (dd/mm/yyyy): ");
        int day = input.nextInt();
        int month = input.nextInt();
        int year = input.nextInt();

        Meeting m = new Meeting(mName, day, month, year, users.get(index), true, "https://zoom.us/");

        organizeMeeting(m);

    }

    public static void organizeMeeting(Meeting m) {
        users.get(index).organizeMeeting(m);
        allMeetings.add(m);

        System.out.println(m.toString() + " created successfully. ");
    }

    public static void cancelMeeting() {
        users.get(index).displayMyOrganizations();

        Scanner input = new Scanner(System.in);
        System.out.println("Enter meeting name to be cancelled: ");
        String name = input.next();

        for (int i = 0; i < allMeetings.size(); i++) {

            if (allMeetings.get(i).getmName().equals(name)) {
                users.get(index).cancelMeeting(allMeetings.get(i));

                for (int k = 0; k < users.size(); k++) {
                    users.get(k).removeMeeting(allMeetings.get(i));
                }
                System.out.println("All attendees of " + allMeetings.get(i).getmName() + " have been removed. " + allMeetings.get(i).getmName() + " is cancelled.");

                allMeetings.remove(i);
            }
        }

    }

    public static void attendMeeting() {
        Scanner input = new Scanner(System.in);

        for (int i = 0; i < allMeetings.size(); i++) {
            System.out.println(allMeetings.get(i).toString());
        }

        System.out.println("Would you like to attend any of these meetings? Enter (y) if you want to join, (n) if you don't. ");
        String choose = input.next();

        if (choose.equals("y")) {
            System.out.println("Event name you would like to attend: ");
            String name = input.next();

            for (int i = 0; i < allMeetings.size(); i++) {
                if (allMeetings.get(i).getmName().equals(name)) {
                    allMeetings.get(i).addAttendee(users.get(index));
                }
            }
        } else if (choose.equals("n")) {
            System.out.println("Returns to main menu... ");
        }
    }

    public static void leaveMeeting() {
        users.get(index).displayMyMeetings();

        Scanner input = new Scanner(System.in);
        System.out.println("Event name you would like to leave: ");
        String name = input.next();

        for (int i = 0; i < allMeetings.size(); i++) {
            if (allMeetings.get(i).getmName().equals(name)) {
                allMeetings.get(i).removeAttendee(users.get(index));
            }
        }

    }

}
