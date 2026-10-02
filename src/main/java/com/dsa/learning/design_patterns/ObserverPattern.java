package com.dsa.learning.design_patterns;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer Pattern: a subject (NewsPublisher) notifies all registered observers when its state changes.
 */
public class ObserverPattern {

    interface Observer {
        void update(String news);
    }

    static class NewsPublisher {
        private final List<Observer> observers = new ArrayList<>();

        void subscribe(Observer observer) {
            observers.add(observer);
        }

        void unsubscribe(Observer observer) {
            observers.remove(observer);
        }

        void publish(String news) {
            observers.forEach(o -> o.update(news));
        }
    }

    public static void main(String[] args) {
        NewsPublisher publisher = new NewsPublisher();

        Observer email = news -> System.out.println("Email subscriber got: " + news);
        Observer sms = news -> System.out.println("SMS subscriber got: " + news);

        publisher.subscribe(email);
        publisher.subscribe(sms);
        publisher.publish("Observer pattern added");

        publisher.unsubscribe(sms);
        publisher.publish("SMS unsubscribed");
    }
}
