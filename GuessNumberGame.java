import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GuessNumberGame extends JFrame {
    private int randomNumber;
    private int attempts;
    private JTextField guessField;
    private JButton guessButton;
    private JTextArea outputArea;

    public GuessNumberGame() {
        setTitle("Guess the Number Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;
        attempts = 0;

        JLabel guessLabel = new JLabel("Enter your guess (between 1 and 100):");
        guessField = new JTextField(10);

        JPanel inputPanel = new JPanel();
        inputPanel.add(guessLabel);
        inputPanel.add(guessField);

        guessButton = new JButton("Guess");
        outputArea = new JTextArea(10, 20);
        outputArea.setEditable(false);

        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        JScrollPane scrollPane = new JScrollPane(outputArea);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(guessButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(guessField.getText());
            attempts++;

            if (userGuess == randomNumber) {
                String result = String.format("Congratulations! You've guessed the number %d in %d attempts.", randomNumber, attempts);
                outputArea.setText(result);
                guessButton.setEnabled(false);
            } else if (attempts >= 3) {
                String result = String.format("Sorry, you've used all your attempts. The number was: %d", randomNumber);
                outputArea.setText(result);
                guessButton.setEnabled(false);
            } else if (userGuess < randomNumber) {
                outputArea.append("Too low! Try a higher number.\n");
            } else {
                outputArea.append("Too high! Try a lower number.\n");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GuessNumberGame game = new GuessNumberGame();
            game.setVisible(true);
        });
    }
}
