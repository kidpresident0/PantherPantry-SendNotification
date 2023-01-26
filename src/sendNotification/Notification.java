package sendNotification;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a class for a notification that can be sent to multiple subscribers from different staff
 * staff members of Panther Pantry at PCC.
 * @author John Christian
 * @version 2023.01.26
 */
public class Notification {
    private String subject;
    private String body;
    private String sender;
    private List<String> subscribers;

    public Notification(String subject, String body, String sender) {
        this.subject = subject;
        this.body = body;
        this.sender = sender;
        this.subscribers = new ArrayList<>();
    }

    public void addSubscriber(String subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubscriber(String subscriber) {
        subscribers.remove(subscriber);
    }

    public void send() {
        int count = subscribers.size();
        for (String subscriber : subscribers) {
            // send the notification to the subscriber
            System.out.println("Notification sent to: " + subscriber + " with subject: " + subject + " from " + sender +
                    ". This notification was sent to: " + count + " subscribers.");

        }
        Notification notification = new Notification("Hello", "We have this food, come pick it up " +
                "at this campus at this this time,", "Pantry Dude");
        notification.addSubscriber("Hungry Joe");
        notification.addSubscriber("Hungry Jane");
        notification.removeSubscriber("Full John");
        notification.send();

        //System.out.println("sendNotification.Notification sent to " + count + " subscriber(s).");



    }



}



