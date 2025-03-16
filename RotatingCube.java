import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class RotatingCube extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int CUBE_SIZE = 100;

    private static final double CAMERA_SPEED = 5.0;
    private static final double ROTATION_SPEED = 0.02;

    private double cameraX = 0;
    private double cameraY = 0;
    private double cameraZ = 500;

    private double angleX = 0;
    private double angleY = 0;

    private boolean wPressed = false;
    private boolean sPressed = false;
    private boolean aPressed = false;
    private boolean dPressed = false;
    private boolean qPressed = false;
    private boolean ePressed = false;

    public RotatingCube() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);

        javax.swing.Timer timer = new javax.swing.Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (wPressed) cameraZ -= CAMERA_SPEED;
                if (sPressed) cameraZ += CAMERA_SPEED;
                if (aPressed) cameraX -= CAMERA_SPEED;
                if (dPressed) cameraX += CAMERA_SPEED;
                if (qPressed) cameraY -= CAMERA_SPEED;
                if (ePressed) cameraY += CAMERA_SPEED;

                angleX += ROTATION_SPEED;
                angleY += ROTATION_SPEED;

                repaint();
            }
        });
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_W) wPressed = true;
                if (key == KeyEvent.VK_S) sPressed = true;
                if (key == KeyEvent.VK_A) aPressed = true;
                if (key == KeyEvent.VK_D) dPressed = true;
                if (key == KeyEvent.VK_Q) qPressed = true;
                if (key == KeyEvent.VK_E) ePressed = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_W) wPressed = false;
                if (key == KeyEvent.VK_S) sPressed = false;
                if (key == KeyEvent.VK_A) aPressed = false;
                if (key == KeyEvent.VK_D) dPressed = false;
                if (key == KeyEvent.VK_Q) qPressed = false;
                if (key == KeyEvent.VK_E) ePressed = false;
            }
        });

        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double[][] vertices = {
            {-CUBE_SIZE, -CUBE_SIZE, -CUBE_SIZE},
            {CUBE_SIZE, -CUBE_SIZE, -CUBE_SIZE},
            {CUBE_SIZE, CUBE_SIZE, -CUBE_SIZE},
            {-CUBE_SIZE, CUBE_SIZE, -CUBE_SIZE},
            {-CUBE_SIZE, -CUBE_SIZE, CUBE_SIZE},
            {CUBE_SIZE, -CUBE_SIZE, CUBE_SIZE},
            {CUBE_SIZE, CUBE_SIZE, CUBE_SIZE},
            {-CUBE_SIZE, CUBE_SIZE, CUBE_SIZE}
        };

        double[][] rotatedVertices = new double[8][3];
        for (int i = 0; i < 8; i++) {
            rotatedVertices[i] = rotateVertex(vertices[i], angleX, angleY);
        }

        double[][] translatedVertices = new double[8][3];
        for (int i = 0; i < 8; i++) {
            translatedVertices[i] = translateVertex(rotatedVertices[i], cameraX, cameraY, cameraZ);
        }

        int[] projectedX = new int[8];
        int[] projectedY = new int[8];
        for (int i = 0; i < 8; i++) {
            double factor = cameraZ / (cameraZ - translatedVertices[i][2]);
            projectedX[i] = (int) (translatedVertices[i][0] * factor + WIDTH / 2);
            projectedY[i] = (int) (translatedVertices[i][1] * factor + HEIGHT / 2);
        }

        g2d.setColor(Color.WHITE);
        int[][] edges = {
            {0, 1}, {1, 2}, {2, 3}, {3, 0},
            {4, 5}, {5, 6}, {6, 7}, {7, 4},
            {0, 4}, {1, 5}, {2, 6}, {3, 7}
        };

        for (int[] edge : edges) {
            int x1 = projectedX[edge[0]];
            int y1 = projectedY[edge[0]];
            int x2 = projectedX[edge[1]];
            int y2 = projectedY[edge[1]];
            g2d.drawLine(x1, y1, x2, y2);
        }

        g2d.setColor(Color.WHITE);
        for (int i = 0; i < 8; i++) {
            g2d.fillOval(projectedX[i] - 5, projectedY[i] - 5, 10, 10);
        }
    }

    private double[] rotateVertex(double[] vertex, double angleX, double angleY) {
        double cosX = Math.cos(angleX);
        double sinX = Math.sin(angleX);
        double cosY = Math.cos(angleY);
        double sinY = Math.sin(angleY);

        double x = vertex[0];
        double y = vertex[1] * cosX - vertex[2] * sinX;
        double z = vertex[1] * sinX + vertex[2] * cosX;

        double newX = x * cosY + z * sinY;
        double newY = y;
        double newZ = -x * sinY + z * cosY;

        return new double[]{newX, newY, newZ};
    }

    private double[] translateVertex(double[] vertex, double cameraX, double cameraY, double cameraZ) {
        return new double[]{
            vertex[0] - cameraX,
            vertex[1] - cameraY,
            vertex[2] - cameraZ
        };
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("alguma merda ai");
        RotatingCube panel = new RotatingCube();
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
