import java.io.File;
import java.io.IOException;
import java.util.*;

    public class HangmanGame {
        private Map<Integer, List<String>> wordsByLength = new HashMap<>();
        private List<String> words = new ArrayList<>();
        private int wordLength;
        private int wrongGuessesAllowed;
        private Set<Character> guessedLetters = new HashSet<>();

        public void readWords(String fileName) throws IOException {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim().toLowerCase();
                words.add(line);
                int length = line.length();
                if (!wordsByLength.containsKey(length)) {
                    wordsByLength.put(length, new ArrayList<>());
                }
                wordsByLength.get(length).add(line);
            }
            scanner.close();
        }

        public void setUpGame() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Let's play Hangman!");
            System.out.println("____________________");

            boolean validInput = false;
            while (!validInput) {
                System.out.println("Enter word length:");
                String input = scanner.nextLine();
                try {
                    wordLength = Integer.parseInt(input);
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a number.");
                }
            }

            validInput = false;
            while (!validInput) {
                System.out.println("Enter the amount of wrong guesses allowed:");
                String input = scanner.nextLine();
                try {
                    wrongGuessesAllowed = Integer.parseInt(input);
                    if (wrongGuessesAllowed >= 0) {
                        validInput = true;
                    } else {
                        System.out.println("The number of wrong guesses cannot be negative. Please enter a valid number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a number.");
                }
            }
        }

        private String displayWord(String word) {
            StringBuilder display = new StringBuilder();

            for (char c : word.toCharArray()) {
                if (guessedLetters.contains(c)) {
                    display.append(c);
                } else {
                    display.append('_');
                }
                display.append(' ');
            }

            return display.toString().trim();
        }

        private boolean isGuessCorrect(String word, char guess) {
            return word.indexOf(guess) != -1;
        }

        private List<String> updateWordList(List<String> currentWords, char guess) {
            Map<String, List<String>> wordFamilies = new HashMap<>();

            for (String word : currentWords) {
                StringBuilder pattern = new StringBuilder();

                for (char c : word.toCharArray()) {
                    if (c == guess || guessedLetters.contains(c)) {
                        pattern.append(c);
                    } else {
                        pattern.append('_');
                    }
                }

                String key = pattern.toString();
                if (!wordFamilies.containsKey(key)) {
                    wordFamilies.put(key, new ArrayList<>());
                }
                wordFamilies.get(key).add(word);
            }

            int maxSize = 0;
            List<String> maxFamily = new ArrayList<>();
            for (Map.Entry<String, List<String>> entry : wordFamilies.entrySet()) {
                if (entry.getValue().size() > maxSize) {
                    maxSize = entry.getValue().size();
                    maxFamily = entry.getValue();
                }
            }

            return maxFamily;
        }

        public void playGame() {
            System.out.println("Starting the game...");
            System.out.println("______________________________");
            System.out.println("");

            Random random = new Random();
            List<String> currentWords = new ArrayList<>(wordsByLength.get(wordLength));
            int wrongGuesses = 0;

            Scanner scanner = new Scanner(System.in);

            while ((wrongGuesses < wrongGuessesAllowed) && (!currentWords.isEmpty())) {
                String currentWord = currentWords.get(random.nextInt(currentWords.size()));

                System.out.println("Current word: " + displayWord(currentWord));
                System.out.println("Guess a letter: ");
                char guess = scanner.next().toLowerCase().charAt(0);

                if (guessedLetters.contains(guess)) {
                    System.out.println("You already guessed that letter! Try again: ");
                    continue;
                }

                guessedLetters.add(guess);

                if (!isGuessCorrect(currentWord, guess)) {
                    wrongGuesses++;
                }

                System.out.println("Wrong guesses left: " + (wrongGuessesAllowed - wrongGuesses));
                currentWords = updateWordList(currentWords, guess);
            }

            if (currentWords.isEmpty()) {
                System.out.println("You win!");
            } else {
                System.out.println("You lost! The word was: " + currentWords.get(0));
            }
        }

        public static void main(String[] args) {
            HangmanGame hangmanGame = new HangmanGame();

            try {
                hangmanGame.readWords("words.txt");
            } catch (IOException e) {
                System.out.println("Error");
                return;
            }

            hangmanGame.setUpGame();
            hangmanGame.playGame();
        }
    }


