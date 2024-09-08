import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Question {
    String question;
    List<String> options;
    int correctAnswer;

    public Question(String question, List<String> options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class QuizGenerator {

    // Data structure to hold quizzes
    private static Map<String, List<Question>> quizzes = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadQuizzes();
        while (true) {
            showMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    createQuiz();
                    break;
                case "2":
                    addQuestion();
                    break;
                case "3":
                    takeQuiz();
                    break;
                case "4":
                    saveQuizzes();
                    break;
                case "5":
                    loadQuizzes();
                    break;
                case "6":
                    System.out.println("Exiting the application.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\nQuiz Generator Application");
        System.out.println("1. Create a new quiz");
        System.out.println("2. Add a question to a quiz");
        System.out.println("3. Take a quiz");
        System.out.println("4. Save quizzes");
        System.out.println("5. Load quizzes");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void createQuiz() {
        System.out.print("Enter the quiz name: ");
        String quizName = scanner.nextLine();
        if (quizzes.containsKey(quizName)) {
            System.out.println("Quiz already exists. Choose a different name.");
        } else {
            quizzes.put(quizName, new ArrayList<>());
            System.out.println("Quiz '" + quizName + "' created successfully.");
        }
    }

    private static void addQuestion() {
        System.out.print("Enter the quiz name to add a question to: ");
        String quizName = scanner.nextLine();
        if (!quizzes.containsKey(quizName)) {
            System.out.println("Quiz does not exist.");
            return;
        }

        System.out.print("Enter the question: ");
        String questionText = scanner.nextLine();
        List<String> options = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            System.out.print("Enter option " + i + ": ");
            options.add(scanner.nextLine());
        }

        System.out.print("Enter the correct option number (1-4): ");
        int correctAnswer = Integer.parseInt(scanner.nextLine());
        if (correctAnswer < 1 || correctAnswer > 4) {
            System.out.println("Invalid option number.");
            return;
        }

        Question question = new Question(questionText, options, correctAnswer);
        quizzes.get(quizName).add(question);
        System.out.println("Question added successfully.");
    }

    private static void takeQuiz() {
        System.out.print("Enter the quiz name you want to take: ");
        String quizName = scanner.nextLine();
        if (!quizzes.containsKey(quizName)) {
            System.out.println("Quiz does not exist.");
            return;
        }

        List<Question> questions = quizzes.get(quizName);
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + question.question);
            for (int j = 0; j < question.options.size(); j++) {
                System.out.println((j + 1) + ". " + question.options.get(j));
            }

            System.out.print("Your answer (1-4): ");
            int answer = Integer.parseInt(scanner.nextLine());
            if (answer == question.correctAnswer) {
                score++;
                System.out.println("Correct!");
            } else {
                System.out.println("Wrong! The correct answer was option " + question.correctAnswer + ".");
            }
        }

        System.out.println("\nYou completed the quiz '" + quizName + "'. Your score: " + score + "/" + questions.size());
    }

    private static void saveQuizzes() {
        // Placeholder for saving quizzes to a file
        System.out.println("Saving quizzes... (Not implemented)");
    }

    private static void loadQuizzes() {
        // Placeholder for loading quizzes from a file
        System.out.println("Loading quizzes... (Not implemented)");
    }
}
