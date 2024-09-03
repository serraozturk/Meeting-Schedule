package project.pkg1;

import java.util.ArrayList;

/**
 *
 * @author Serra Ozturk
 */
public class Meeting {

    private String mName;
    private MDate date;
    private ArrayList<Person> attendees;
    private Person host;
    private boolean isOnline;
    private String url;
    private String location;

    public Meeting(String mName, int day, int month, int year, Person host, boolean isOnline, String urlOrLocation) {
        this.mName = mName;
        this.date = new MDate(day, month, year);
        attendees = new ArrayList<>();
        this.host = host;
        this.isOnline = isOnline;
        if (isOnline) {
            this.url = urlOrLocation;
        } else {
            this.location = urlOrLocation;
        }
    }

    public String getmName() {
        return mName;
    }

    public MDate getDate() {
        return date;
    }

    public Person getHost() {
        return host;
    }

    public void setIsOnline(boolean isOnline, String urlOrLocation) {
        this.isOnline = isOnline;
        if (isOnline) {
            this.url = urlOrLocation;
        } else {
            this.location = urlOrLocation;
        }
    }

    public boolean equals(Meeting other) {
        return (this.date.equals(other.date) && this.host.equals(other.host) && this.attendees.equals(other.attendees));
    }

    public void addAttendee(Person p) {

        if (!attendees.contains(p)) {
            if (p.addMeeting(this)) {
                attendees.add(p);
                System.out.println("OK, the meeting has been added to your agenda. :)");
            }
        } else {
            System.out.println("The person is already in the list.");
        }

        /*p.addMeeting(this);
        attendees.add(p);
        System.out.println("ok, the meeting has been added to your agenda.");*/
    }

    public boolean removeAttendee(Person p) {
        if (attendees.contains(p)) {
            attendees.remove(p);
            p.removeMeeting(this);
            return true;
        } else {
            return false;
        }
    }

    public void removeAllAttendees() {
        attendees.removeAll(attendees);
    }

    @Override
    public String toString() {
        return "Meeting{" + "Name: " + mName + ", Date: " + date + ", Attendees: " + attendees.size() + ", Host: " + host.getName() + ", Is it online?: " + isOnline + ", URL: " + url + ", Location: " + location + '}';
    }

}
