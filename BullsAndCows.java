import java.util.*;

public class BullsAndCows{

    // Method to generate a random uppercase string of given length
    public static String generateRandomString(int length) {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < length; i++) {
            char randomChar = (char) ('A' + rand.nextInt(26));
            sb.append(randomChar);
        }

        return sb.toString();
    }

    // Method to calculate bulls and cows
    public static int[] calculateBullsAndCows(String secret, String guess) {
        int bulls = 0, cows = 0;
        int[] secretFreq = new int[26];
        int[] guessFreq = new int[26];

        for(int i = 0; i < secret.length(); i++) {
            if(secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else {
                secretFreq[secret.charAt(i) - 'A']++;
                guessFreq[guess.charAt(i) - 'A']++;
            }
        }

        for(int i = 0; i < 26; i++) {
            cows += Math.min(secretFreq[i], guessFreq[i]);
        }

        return new int[]{bulls, cows};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Bulls and Cows Game! \n");
        System.out.println("Rules:");
        System.out.println("1. The computer will generate a random word of your chosen length.");
        System.out.println("2. The word contains only uppercase English letters (A-Z).");
        System.out.println("3. You have to guess the word.");
        System.out.println("4. After each guess, you'll get feedback in the form of:");
        System.out.println("   - X Bulls: letters that are correct and in the correct position.");
        System.out.println("   - Y Cows: letters that are correct but in the wrong position.");
        System.out.println("Example:");
        System.out.println("   Secret: MACM, Guess: MMMB -> 1 Bull, 1 Cow\n");
        System.out.print("Enter the length of the secret word: ");
        int length = sc.nextInt();
        sc.nextLine(); // consume newline

        String secret = generateRandomString(length);
        // Uncomment the next line for debugging
        // System.out.println("DEBUG: Secret word is " + secret);

        System.out.println("Guess the word (uppercase letters only):");

        while(true) {
            System.out.print("Your guess: ");
            String guess = sc.nextLine().toUpperCase();
            if(guess=="#"){
                System.out.println("The word was "+secret+", Better luck next time!");
            }
            if(guess.length() != length) {
                System.out.println("Invalid input length. Try again.");
                continue;
            }

            int[] result = calculateBullsAndCows(secret, guess);
            System.out.println(result[0] + " Bulls, " + result[1] + " Cows");

            if(result[0] == length) {
                System.out.println("Congratulations! You guessed the word!");
                break;
            }
        }

        sc.close();
    }
}
