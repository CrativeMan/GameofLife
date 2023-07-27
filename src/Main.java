import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class Canvas extends JComponent{

    public void paint(Graphics g){
        drawGrid(g);
    }

    public void drawGrid(Graphics g){
        g.setColor(Color.BLACK);
        for (int i = 0; i < 800; i += 10) {
            g.drawLine(i, 0, i, 800);
            g.drawLine(0, i, 800, i);
        }
    }

    public void drawCell(Graphics g, int x, int y){
        g.setColor(Color.darkGray);
        g.fillRect(x, y, 10, 10);
    }
}

public class Main extends JFrame implements MouseListener {

    // cells array with size 80x80
    public Cell[][] cells = new Cell[80][80];
    public static Canvas canvas = new Canvas();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Conways Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().add(canvas);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.addMouseListener(new Main());
        frame.setVisible(true);
    }

    public void newCell(int x, int y){
        System.out.println("New cell at: " + x + ", " + y);
        canvas.drawCell(canvas.getGraphics(), x-10, y-10);
        Cell cell = new Cell(x, y, true);
        cells[x/10-10][y/10-10] = cell;

        // for loop to check if cell is in array
        System.out.println("Cells in array: ");
        for (Cell[] value : cells) {
            for (int j = 0; j < cells.length; j++) {
                if (value[j] != null)
                    System.out.println(value[j].getX() + ", " + value[j].getY() + ", is Alive:" + value[j].isAlive());
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            int x = e.getX() + 10; // Adjust for the starting position of cells
            int y = e.getY() + 10;
            int roundedX = (x / 10) * 10;
            int roundedY = (y / 10) * 10;
            System.out.println("Clicked at: " + roundedX + ", " + roundedY);
            newCell(roundedX, roundedY);
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}