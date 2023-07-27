import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Canvas extends JComponent{

    public void paint(Graphics g){
        drawGrid(g);
        drawCell(g, 10, 10);
        drawCell(g, 10, 20);
        drawCell(g, 10, 30);
    }

    public void drawGrid(Graphics g){
        g.setColor(Color.BLACK);
        for (int i = 0; i < 800; i += 10) {
            g.drawLine(i, 0, i, 800);
            g.drawLine(0, i, 800, i);
        }
    }

    public void drawCell(Graphics g, int x, int y){
        g.setColor(Color.BLACK);
        g.fillRect(x, y, 10, 10);
    }
}

public class Main extends JFrame implements ActionListener {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Conways Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().add(new Canvas());
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}