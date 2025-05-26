/*
 * Apache Felix OSGi tutorial.
 */

package tutorial.example2;

import java.util.Hashtable;
import java.util.Set;
import java.util.HashSet;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceEvent;
import tutorial.example2.service.DictionaryService;

/**
 * This class implements a simple bundle that uses the bundle
 * context to register an English language dictionary service
 * with the OSGi framework. The dictionary service interface is
 * defined in a separate class file and is implemented by an
 * inner class.
 */
public class Activator implements BundleActivator {

    /**
     * Implements BundleActivator.start(). Registers an
     * instance of a dictionary service using the bundle context;
     * attaches properties to the service that can be queried
     * when performing a service look-up.
     * 
     * @param context the framework context for the bundle.
     */
    @Override
    public void start(BundleContext context) {
        Hashtable<String, String> props = new Hashtable<>();
        props.put("Language", "English");

        context.registerService(
                DictionaryService.class.getName(),
                new DictionaryImpl(),
                props);

        System.out.println("Dictionary service registered and started successfully");
    }

    /**
     * Implements BundleActivator.stop(). Does nothing since
     * the framework will automatically unregister any registered services.
     * 
     * @param context the framework context for the bundle.
     */
    @Override
    public void stop(BundleContext context) {
        // NOTE: The service is automatically unregistered.
        System.out.println("Dictionary service stopped.");
    }

    /**
     * A private static inner class that implements a dictionary service;
     * see DictionaryService for details of the service.
     */
    private static class DictionaryImpl implements DictionaryService {

        private static final Set<String> DICTIONARY = Set.of(
                "welcome", "to", "the", "osgi", "tutorial");

        /**
         * Implements DictionaryService.checkWord(). Determines
         * if the passed-in word is contained in the dictionary.
         * 
         * @param word the word to be checked.
         * @return true if the word is in the dictionary,
         *         false otherwise.
         */
        @Override
        public boolean checkWord(String word) {
            if (word == null) {
                return false;
            }
            return DICTIONARY.contains(word.toLowerCase());
        }
    }
}
