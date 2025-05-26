package tutorial.example5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import tutorial.example2.service.DictionaryService;

public class Activator implements BundleActivator {

    private BundleContext context;
    private ServiceTracker<DictionaryService, DictionaryService> tracker;

    @Override
    public void start(BundleContext context) throws Exception {
        this.context = context;

        // Track DictionaryService with Language property
        tracker = new ServiceTracker<>(
            context,
            context.createFilter("(&(objectClass=" + DictionaryService.class.getName() + ")(Language=*))"),
            null
        );
        tracker.open();

        System.out.println("Enter a blank line to exit.");

        // Run word check in a separate thread (best practice)
        new Thread(() -> {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
                while (true) {
                    System.out.print("Enter word: ");
                    String word = in.readLine();

                    if (word == null || word.trim().isEmpty()) {
                        System.out.println("Exiting dictionary client.");
                        break;
                    }

                    DictionaryService dictionary = tracker.getService();

                    if (dictionary == null) {
                        System.out.println("No dictionary available.");
                    } else if (dictionary.checkWord(word)) {
                        System.out.println("Correct.");
                    } else {
                        System.out.println("Incorrect.");
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading input: " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void stop(BundleContext context) {
        if (tracker != null) {
            tracker.close();
        }
        System.out.println("Dictionary client stopped.");
    }
}
