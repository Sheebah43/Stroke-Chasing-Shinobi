import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShinobiLadderMission {
    public static void main(String[] args) throws IOException, InterruptedException {
        String challenge = "shadow stealth infiltration";
        System.out.println("Mission: Type the words to build the ladder.");
        System.out.println("Challenge text: " + challenge);
        System.out.println("Accuracy must be 100% or the shinobi gets caught!");

        // Countdown before typing starts
        System.out.println("\nGet ready...");
        for (int i = 5; i > 0; i--) {
            if (i == 1) {
                System.out.println("START TYPING!");
            } else {
                System.out.println(i + "...");
            }
            Thread.sleep(1000);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Start timer only when countdown finishes
        long startTime = System.currentTimeMillis();
        String typed = reader.readLine();
        long endTime = System.currentTimeMillis();

        long elapsedTime = endTime - startTime;
        if (elapsedTime <= 0) elapsedTime = 1;

        double minutes = elapsedTime / 60000.0;

        // WORD-BASED COMPARISON
        int correctChars = 0;
        int wrongChars = 0;    // typed but incorrect
        int missedChars = 0;   // not typed
        int extraChars = 0;    // extra characters typed
        int errors = 0;

        String[] challengeWords = challenge.split("\\s+");
        String[] typedWords = typed.trim().split("\\s+");

        int maxWords = Math.max(challengeWords.length, typedWords.length);

        for (int w = 0; w < maxWords; w++) {

            String challengeWord = (w < challengeWords.length) ? challengeWords[w] : "";

            String typedWord = (w < typedWords.length) ? typedWords[w] : "";

            int minLen = Math.min(challengeWord.length(), typedWord.length());

            // Compare characters only within the current word
            for (int i = 0; i < minLen; i++) {
                if (challengeWord.charAt(i) == typedWord.charAt(i)) {
                    correctChars++;
                }
                else {
                    wrongChars++;
                    errors++;
                }
            }

            // Missing characters (user pressed space early)
            if (typedWord.length() < challengeWord.length()) {
                int missing = challengeWord.length() - typedWord.length();
                missedChars += missing;
                errors += missing;
            }

            // Extra characters in the typed word
            if (typedWord.length() > challengeWord.length()) {
                int extra = typedWord.length() - challengeWord.length();
                extraChars += extra;
                errors += extra;
            }
        }

        int totalChars = typed.replace(" ", "").length();

        // Metrics
        double typedAccuracy = (totalChars == 0)? 0 : Math.floor((double) correctChars / totalChars * 100);
        double challengeAccuracy = (double) correctChars / challenge.replace(" ", "").length() * 100;
        double grossWpm = (totalChars / 5.0) / minutes;
        double netWpm = grossWpm * challengeAccuracy / 100;

        // Results
        System.out.println("\n--- Results ---");
        System.out.println("Total characters typed: " + totalChars);
        System.out.println("Correct characters: " + correctChars);
        System.out.println("Wrong characters: " + wrongChars);
        System.out.println("Missed characters: " + missedChars);
        System.out.println("Extra characters: " + extraChars);
        System.out.println("Total errors: " + errors);

        System.out.printf("Time taken: %.2f seconds%n", elapsedTime / 1000.0);

        System.out.printf("Gross WPM: %.2f%n", grossWpm);
        System.out.printf("Net WPM: %.2f%n", netWpm);

        System.out.printf("Accuracy (typed): %.0f%%%n", typedAccuracy);

        System.out.printf("Accuracy (challenge): %.0f%%%n", challengeAccuracy);

        // Mission outcome
        if (errors > 0 || challengeAccuracy < 100) {
            System.out.println("Shinobi got caught!");
        }
        else {
            System.out.println("Shinobi climbs over the wall successfully!");
        }
    }
}