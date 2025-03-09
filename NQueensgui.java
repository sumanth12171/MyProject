package javaPbl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class NQueensgui extends JFrame
{
	    private int n;
	    private JButton[][] buttons;
	    private ArrayList<Point> queens;
	    private JPanel boardPanel;
	    private JTextField inputField;

	    public NQueensgui() {
	        setTitle("N Queens");
	        setSize(800, 900);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLayout(new BorderLayout());

	        // Input Panel
	        JPanel inputPanel = new JPanel();
	        inputPanel.setLayout(new FlowLayout());

	        JLabel inputLabel = new JLabel("Enter n: ");
	        inputLabel.setFont(new Font("Arial", Font.BOLD, 16));
	        inputField = new JTextField(5);
	        inputField.setFont(new Font("Arial", Font.PLAIN, 16));
	        JButton generateButton = new JButton("Generate Board");
	        generateButton.setFont(new Font("Arial", Font.BOLD, 16));

	        inputPanel.add(inputLabel);
	        inputPanel.add(inputField);
	        inputPanel.add(generateButton);

	        add(inputPanel, BorderLayout.NORTH);

	        // Board Panel
	        boardPanel = new JPanel();
	        boardPanel.setBackground(Color.BLACK);
	        boardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	        add(boardPanel, BorderLayout.CENTER);

	        // Event Listeners
	        generateButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                generateBoard();
	            }
	        });
	    }

	    private void generateBoard() {
	        try {
	            n = Integer.parseInt(inputField.getText());
	            if (n <= 0) {
	                showMessage("Invalid input. Please enter a positive integer.");
	                return;
	            }
	            queens = new ArrayList<>();
	            boardPanel.removeAll();
	            boardPanel.setLayout(new GridLayout(n, n, 5, 5)); // Add gaps between buttons
	            buttons = new JButton[n][n];
	            for (int i = 0; i < n; i++) {
	                for (int j = 0; j < n; j++) {
	                    buttons[i][j] = new JButton();
	                    buttons[i][j].setBackground(Color.BLACK);
	                    buttons[i][j].setForeground(Color.WHITE);
	                    buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.WHITE));
	                    buttons[i][j].setFocusPainted(false);
	                    buttons[i][j].setFont(new Font("Arial", Font.BOLD, 20));
	                    int finalI = i;
	                    int finalJ = j;
	                    buttons[i][j].addActionListener(new ActionListener() {
	                        @Override
	                        public void actionPerformed(ActionEvent e) {
	                            toggleQueen(finalI, finalJ);
	                        }
	                    });
	                    boardPanel.add(buttons[i][j]);
	                }
	            }
	            JPanel buttonPanel = new JPanel();
	            buttonPanel.setLayout(new FlowLayout());
	            JButton checkButton = new JButton("Check");
	            checkButton.setFont(new Font("Arial", Font.BOLD, 18));
	            checkButton.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    checkQueens();
	                }
	            });
	            JButton solveButton = new JButton("Solve");
	            solveButton.setFont(new Font("Arial", Font.BOLD, 18));
	            solveButton.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    solveNQueens();
	                }
	            });
	            buttonPanel.add(checkButton);
	            buttonPanel.add(solveButton);
	            add(buttonPanel, BorderLayout.SOUTH);
	            boardPanel.revalidate();
	            boardPanel.repaint();
	        } catch (NumberFormatException e) {
	            showMessage("Invalid input. Please enter a positive integer.");
	        }
	    }

	    private void toggleQueen(int i, int j) {
	        Point p = new Point(i, j);
	        if (queens.contains(p)) {
	            queens.remove(p);
	            buttons[i][j].setText("");
	        } else {
	            if (queens.size() < n) {
	                queens.add(p);
	                buttons[i][j].setText("Q");
	            } else {
	                showMessage("You cannot place more than " + n + " queens.");
	            }
	        }
	    }

	    private void checkQueens() {
	        if (isValidConfiguration(queens, n)) {
	            showMessage("Valid configuration!");
	        } else {
	            showMessage("Invalid configuration!");
	        }
	    }

	    private boolean isValidConfiguration(ArrayList<Point> queens, int n) {
	        for (int i = 0; i < queens.size(); i++) {
	            for (int j = i + 1; j < queens.size(); j++) {
	                Point q1 = queens.get(i);
	                Point q2 = queens.get(j);
	                if (q1.x == q2.x || q1.y == q2.y || Math.abs(q1.x - q2.x) == Math.abs(q1.y - q2.y)) {
	                    return false;
	                }
	            }
	        }
	        return true;
	    }

	    private void solveNQueens() {
	        queens.clear();
	        solve(0);
	        updateBoard();
	    }

	    private boolean solve(int row) {
	        if (row == n) return true;
	        for (int col = 0; col < n; col++) {
	            Point p = new Point(row, col);
	            if (isValidMove(p)) {
	                queens.add(p);
	                if (solve(row + 1)) return true;
	                queens.remove(p);
	            }
	        }
	        return false;
	    }

	    private boolean isValidMove(Point p) {
	        for (Point q : queens) {
	            if (q.x == p.x || q.y == p.y || Math.abs(q.x - p.x) == Math.abs(q.y - p.y)) {
	                return false;
	            }
	        }
	        return true;
	    }

	    private void updateBoard() {
	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) {
	                if (queens.contains(new Point(i, j))) {
	                    buttons[i][j].setText("Q");
	                } else {
	                    buttons[i][j].setText("");
	                }
	            }
	        }
	    }

	    private void showMessage(String message) {
	        JOptionPane.showMessageDialog(this, message);
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new NQueensgui().setVisible(true);
	            }
	        });
	    }
	}
