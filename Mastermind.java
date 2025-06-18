import java.util.Random;
import java.util.Scanner;

public class Mastermind {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {
            playGame(scanner);  
            System.out.print("Do you want to play again ? (y/n): ");
            String response = scanner.nextLine().trim().toLowerCase();
            playAgain = response.equals("y");
        } while (playAgain) ;
        System.out.println("Thank you for playing!");
        
    }

    public static void playGame(Scanner scanner) {
        Random random = new Random();

        // random generate a 4-digits numbers
        int[] secretCode = new int[4];
        for (int i = 0; i < 4; i++) {
            secretCode[i] = random.nextInt(6)+1;
        }

        
        int maxAttempts = 10;
        boolean won = false;

        System.out.println("Welcome to play Mastermind");
        System.out.println("Guess the 4-digit code, using numbers 1 to 6.");

        for (int attempt = 0 ; attempt < maxAttempts; attempt++) {
           
            System.out.printf("Attempt %d: Enter your guess: ", attempt + 1);
            String input = scanner.nextLine();

           
            if (!isValidGuess(input)) {
                System.out.println("Invalid input. Please enter 4 digits between 1 and 6.");
                attempt--; 
                continue;
            }

            int[] guess = new int[4];
            for (int i = 0; i < 4; i++) {
                guess[i] = Character.getNumericValue(input.charAt(i));
            }

           
            int correctPosition = 0;
            int correctNumber = 0;
            boolean[] secretUsed = new boolean[4];
            boolean[] guessUsed = new boolean[4];

            // both numbers and positions are correct
            for (int i = 0; i < 4; i++) {
                if (guess[i] == secretCode[i]) {
                    correctPosition++;
                    secretUsed[i] = true;
                    guessUsed[i] = true;
                }
            }

            // correct numbers but wriong position
            for (int i = 0; i < 4; i++) {
                if (!guessUsed[i]) {
                    for (int j = 0; j < 4; j++) {
                        if (!secretUsed[j] && guess[i] == secretCode[j]) {
                            correctNumber++;
                            secretUsed[j] = true;
                            break;
                        }
                    }
                }
            }

            // display result
            if (correctPosition == 4) {
                won = true;
                System.out.println("Congratulations! You've guessed the code correctly");
                break;
            } else {
                System.out.println("Black：" + correctPosition + " White : " + correctNumber );
                
            }
        }

        if (!won) {
            System.out.print("Sorry, you've used all your attempts. The correct code was:");
            for (int num : secretCode) {
                System.out.print(num);
            }
            System.out.println();
        }
    }

    // check input is a 4-digits number or not
    public static boolean isValidGuess(String input) {
        if (input.length() != 4) {
            return false;
        }
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c) || c < '0' || c > '6') {
                return false;
            }
        }
        return true;
    }

    
}

