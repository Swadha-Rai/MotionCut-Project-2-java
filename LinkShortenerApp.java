import java.util.HashMap;
import java.util.Random;

public class LinkShortenerApp {
    // Maps to store URL mappings
    private HashMap<String, String> shortToLongMap; // Short URL -> Long URL
    private HashMap<String, String> longToShortMap; // Long URL -> Short URL

    // Character set for generating short URLs
    private static final String CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6; // Length of short URL
    private Random random;

    // Constructor to initialize maps and random generator
    public LinkShortenerApp() {
        shortToLongMap = new HashMap<>();
        longToShortMap = new HashMap<>();
        random = new Random();
    }

    // Method to shorten a long URL
    public String shortenURL(String longURL) {
        // If the long URL already exists, return the existing short URL
        if (longToShortMap.containsKey(longURL)) {
            return longToShortMap.get(longURL);
        }

        // Generate a unique short URL
        String shortURL = generateShortURL();
        while (shortToLongMap.containsKey(shortURL)) {
            shortURL = generateShortURL(); // Keep generating until it's unique
        }

        // Store the mappings in both maps
        shortToLongMap.put(shortURL, longURL);
        longToShortMap.put(longURL, shortURL);

        return shortURL;
    }

    // Method to retrieve the original long URL using a short URL
    public String getOriginalURL(String shortURL) {
        if (shortToLongMap.containsKey(shortURL)) {
            return shortToLongMap.get(shortURL);
        } else {
            return "Short URL not found!";
        }
    }

    // Helper method to generate a random short URL
    private String generateShortURL() {
        StringBuilder shortURL = new StringBuilder();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            int index = random.nextInt(CHAR_SET.length());
            shortURL.append(CHAR_SET.charAt(index));
        }
        return shortURL.toString();
    }

    // Main method to run the application
    public static void main(String[] args) {
        // Create an instance of the LinkShortenerApp
        LinkShortenerApp linkShortener = new LinkShortenerApp();

        // Example long URLs
        String longURL1 = "https://www.example.com/very/long/url/1";
        String longURL2 = "https://www.example.com/another/very/long/url/2";

        // Shorten the long URLs
        String shortURL1 = linkShortener.shortenURL(longURL1);
        String shortURL2 = linkShortener.shortenURL(longURL2);

        // Print the shortened URLs
        System.out.println("Long URL: " + longURL1 + " -> Short URL: " + shortURL1);
        System.out.println("Long URL: " + longURL2 + " -> Short URL: " + shortURL2);

        // Retrieve and print the original URLs using the short URLs
        System.out.println("Short URL: " + shortURL1 + " -> Long URL: " + linkShortener.getOriginalURL(shortURL1));
        System.out.println("Short URL: " + shortURL2 + " -> Long URL: " + linkShortener.getOriginalURL(shortURL2));
    }
}

