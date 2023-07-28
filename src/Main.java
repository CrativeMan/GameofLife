import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends JFrame {

    private final int rows;
    private final int cols;
    private boolean[][] grid;
    private final int cellSize = 10;
    private final int delay = 100;
    private Timer timer;
    private AtomicBoolean running = new AtomicBoolean(false);

    public Main(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new boolean[rows][cols];
        initializeBoard();
        setupUI();
    }

    private void initializeBoard() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = false;
            }
        }
    }

    private void setupUI() {
        setTitle("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(cols * cellSize, rows * cellSize);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGrid(g);
                drawCells(g);
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = e.getY() / cellSize;
                int col = e.getX() / cellSize;
                if (row >= 0 && row < rows && col >= 0 && col < cols) {
                    grid[row][col] = !grid[row][col];
                    panel.repaint();
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    running.set(!running.get());
                    if (running.get()) {
                        startSimulation();
                    } else {
                        stopSimulation();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    resetBoard();
                    panel.repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_R) {
                    randomizeBoard();
                    panel.repaint();
                }
            }
        });

        setFocusable(true);
        requestFocusInWindow();

        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (running.get()) {
                    evolve();
                    panel.repaint();
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.GRAY);
        for (int row = 0; row <= rows; row++) {
            int y = row * cellSize;
            g.drawLine(0, y, cols * cellSize, y);
        }

        for (int col = 0; col <= cols; col++) {
            int x = col * cellSize;
            g.drawLine(x, 0, x, rows * cellSize);
        }
    }

    private void drawCells(Graphics g) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col]) {
                    g.setColor(Color.GREEN);
                    g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    private int countAliveNeighbors(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int r = row + i;
                int c = col + j;
                if (r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c]) {
                    count++;
                }
            }
        }
        return count;
    }

    private void evolve() {
        boolean[][] newGrid = new boolean[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int neighbors = countAliveNeighbors(row, col);
                if (grid[row][col]) {
                    newGrid[row][col] = neighbors == 2 || neighbors == 3;
                } else {
                    newGrid[row][col] = neighbors == 3;
                }
            }
        }

        grid = newGrid;
    }

    private void startSimulation() {
        timer.start();
    }

    private void stopSimulation() {
        timer.stop();
    }

    private void resetBoard() {
        initializeBoard();
    }

    private void randomizeBoard() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = Math.random() < 0.5; // Adjust the probability for live cells
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main game = new Main(100, 100);
        });
    }
}
