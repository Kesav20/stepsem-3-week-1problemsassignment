import java.util.Locale;

/**
 * Assignment Solutions for Week 1 — 5 Programming Tasks
 */
public class Main {

    /**
     * Problem 1: The Exam Hall Seat Duplication Checker
     * 
     * Scans an array of seat numbers to check for duplicates using only basic arrays and nested loops.
     * Prints duplicate seat numbers if found, or a confirmation message if no duplicates exist.
     * 
     * @param seatNumbers Array of seat numbers assigned to students
     */
    public static void checkDuplicateSeats(int[] seatNumbers) {
        boolean foundDuplicate = false;

        for (int i = 0; i < seatNumbers.length; i++) {
            // Check if the current seat number was already checked to avoid repeated logs
            boolean alreadyReported = false;
            for (int k = 0; k < i; k++) {
                if (seatNumbers[k] == seatNumbers[i]) {
                    alreadyReported = true;
                    break;
                }
            }
            if (alreadyReported) {
                continue;
            }

            // Compare seatNumbers[i] against all subsequent elements
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                    foundDuplicate = true;
                    break; // Found duplicate for this seat number, move to next i
                }
            }
        }

        if (!foundDuplicate) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    /**
     * Problem 2: The Typing Speed Test Accuracy Checker
     * 
     * Compares the original string and typed string character by character at each position.
     * Calculates accuracy percentage and reports the first mismatch position (1-based) if present.
     * 
     * @param original The target text
     * @param typed The text entered by the user
     */
    public static void checkTypingAccuracy(String original, String typed) {
        int total = original.length();
        int matched = 0;
        int firstMismatchPos = -1;
        char origChar = ' ';
        char typedChar = ' ';

        for (int i = 0; i < total; i++) {
            char c1 = original.charAt(i);
            char c2 = typed.charAt(i);

            if (c1 == c2) {
                matched++;
            } else if (firstMismatchPos == -1) {
                firstMismatchPos = i + 1; // Convert 0-based index to 1-based position
                origChar = c1;
                typedChar = c2;
            }
        }

        double accuracy = ((double) matched / total) * 100.0;

        StringBuilder output = new StringBuilder();
        output.append("Matched: ").append(matched).append("/").append(total);
        output.append(String.format(Locale.US, " | Accuracy: %.2f%% | ", accuracy));

        if (firstMismatchPos == -1) {
            output.append("No Mismatches");
        } else {
            output.append("First Mismatch at position ")
                  .append(firstMismatchPos)
                  .append(" ('").append(origChar).append("' vs '").append(typedChar).append("')");
        }

        System.out.println(output.toString());
    }

    /**
     * Problem 3: The Traffic Signal Streak Analyzer
     * 
     * Traverses the signal log to track and output the longest streak of consecutive identical signal characters.
     * 
     * @param signalLog Sequence of signal character readings (e.g., "RRGGGYRR")
     */
    public static void findLongestStreak(String signalLog) {
        if (signalLog == null || signalLog.isEmpty()) {
            System.out.println("No signal data provided.");
            return;
        }

        char maxChar = signalLog.charAt(0);
        int maxStreak = 1;

        char currentChar = signalLog.charAt(0);
        int currentStreak = 1;

        for (int i = 1; i < signalLog.length(); i++) {
            char c = signalLog.charAt(i);
            if (c == currentChar) {
                currentStreak++;
            } else {
                if (currentStreak > maxStreak) {
                    maxStreak = currentStreak;
                    maxChar = currentChar;
                }
                currentChar = c;
                currentStreak = 1;
            }
        }

        // Final check after loop termination
        if (currentStreak > maxStreak) {
            maxStreak = currentStreak;
            maxChar = currentChar;
        }

        System.out.println("Longest Streak: '" + maxChar + "' repeated " + maxStreak + " times");
    }

    /**
     * Problem 4: The Warehouse Inventory Balancer
     * 
     * Computes totals for Section A and Section B, checks if they are balanced, and identifies
     * the overall highest quantity item along with its section and item number.
     * 
     * @param sectionA Array of item quantities in Section A
     * @param sectionB Array of item quantities in Section B
     */
    public static void analyzeInventory(int[] sectionA, int[] sectionB) {
        int totalA = 0;
        int totalB = 0;

        for (int qty : sectionA) {
            totalA += qty;
        }
        for (int qty : sectionB) {
            totalB += qty;
        }

        String status = (totalA == totalB) ? "Balanced" : "Not Balanced";

        int maxQty = Integer.MIN_VALUE;
        String maxSection = "";
        int maxItemIndex = -1;

        // Scan Section A for maximum quantity
        for (int i = 0; i < sectionA.length; i++) {
            if (sectionA[i] > maxQty) {
                maxQty = sectionA[i];
                maxSection = "Section A";
                maxItemIndex = i + 1; // 1-based item index
            }
        }

        // Scan Section B for maximum quantity
        for (int i = 0; i < sectionB.length; i++) {
            if (sectionB[i] > maxQty) {
                maxQty = sectionB[i];
                maxSection = "Section B";
                maxItemIndex = i + 1; // 1-based item index
            }
        }

        System.out.println("Section A Total: " + totalA + " | Section B Total: " + totalB +
                           " | Status: " + status + " | Highest Quantity: " + maxQty +
                           " (" + maxSection + ", Item " + maxItemIndex + ")");
    }

    /**
     * Problem 5: The Movie Review Word Length Profiler
     * 
     * Splits a review string into individual words and categorizes them as Short (1-4 letters),
     * Medium (5-8 letters), or Long (9+ letters).
     * 
     * @param review Movie review input text
     */
    public static void classifyWordLengths(String review) {
        if (review == null || review.trim().isEmpty()) {
            System.out.println("Short: 0 | Medium: 0 | Long: 0");
            return;
        }

        String[] words = review.trim().split("\\s+");
        int shortCount = 0;
        int mediumCount = 0;
        int longCount = 0;

        for (String word : words) {
            // Strip attached punctuation to count only letters/digits
            String cleanedWord = word.replaceAll("[^a-zA-Z0-9]", "");
            int length = cleanedWord.isEmpty() ? word.length() : cleanedWord.length();

            if (length >= 1 && length <= 4) {
                shortCount++;
            } else if (length >= 5 && length <= 8) {
                mediumCount++;
            } else if (length >= 9) {
                longCount++;
            }
        }

        System.out.println("Short: " + shortCount + " | Medium: " + mediumCount + " | Long: " + longCount);
    }

    /**
     * Main method executing sample test cases for all 5 problems.
     */
    public static void main(String[] args) {
        // --- Problem 1 Test Cases ---
        checkDuplicateSeats(new int[]{101, 102, 103, 102, 105});
        checkDuplicateSeats(new int[]{101, 102, 103, 104, 105});

        // --- Problem 2 Test Cases ---
        checkTypingAccuracy("hello world", "hello worlt");
        checkTypingAccuracy("coding", "coding");

        // --- Problem 3 Test Cases ---
        findLongestStreak("RRGGGYRR");
        findLongestStreak("RRRRYYGG");

        // --- Problem 4 Test Case ---
        analyzeInventory(new int[]{20, 15, 30}, new int[]{25, 10, 30});

        // --- Problem 5 Test Case ---
        classifyWordLengths("This movie was absolutely fantastic and thrilling");
    }
}