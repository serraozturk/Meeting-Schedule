package org.example;

import java.util.ArrayList;

/**
 *
 * @author Serra Ozturk
 */
public class Person {

    private String name;
    private int id = 1000;
    private static int count;
    private ArrayList<Meeting> myMeetings;
    private ArrayList<Meeting> iOrganize;

    public Person(String name) {
        this.name = name;
        id = id + count;
        count++;
        myMeetings = new ArrayList<>();
        iOrganize = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Meeting> getMyMeetings() {
        return myMeetings;
    }

    public ArrayList<Meeting> getiOrganize() {
        return iOrganize;
    }

    public boolean equals(Person other) {
        return (this.name.equals(other.name) && this.id == other.id);
    }

    public boolean addMeeting(Meeting m) {

        boolean control = false;

        if (myMeetings.isEmpty()) {
            myMeetings.add(m);
            control = true;
        } else {
            for (int i = 0; i < myMeetings.size(); i++) {
                if (!myMeetings.get(i).getDate().toString().equals(m.getDate().toString())) {
                    control = true;
                } else {
                    System.out.println("There is another meeting at the same time. ");
                    control = false;
                    break;
                }
            }
            if (control) {
                myMeetings.add(m);
            }
        }
        return control;
    }

    public void removeMeeting(Meeting m) {
        myMeetings.remove(m);
    }

    public void organizeMeeting(Meeting m) {
        iOrganize.add(m);
    }

    public void cancelMeeting(Meeting m) {
        iOrganize.remove(m);
        m.removeAllAttendees();
    }

    public void displayMyMeetings() {
        for (int i = 0; i < myMeetings.size(); i++) {
            System.out.println("Meeting Name: " + myMeetings.get(i).getmName() + ", Date: " + myMeetings.get(i).getDate().toString() + ", Host: " + myMeetings.get(i).getHost().getName());
        }
    }

    public void displayMyOrganizations() {
        for (int i = 0; i < iOrganize.size(); i++) {
            System.out.println("Meeting Name: " + iOrganize.get(i).getmName() + ", Date: " + iOrganize.get(i).getDate().toString());
        }
    }

    @Override
    public String toString() {
        return "User " + name + " with ID " + id + " has " + myMeetings.size() + " meetings to attend and " + iOrganize.size() + " meetings organized.";
    }

}
