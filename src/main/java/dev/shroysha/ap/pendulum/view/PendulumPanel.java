package dev.shroysha.ap.pendulum.view;

import dev.shroysha.ap.pendulum.model.Pendulum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PendulumPanel extends JPanel {

    private final static double REFRESH_TIME = 50.0 / 1000.0;
    private boolean drawLine;
    private boolean active;
    private Pendulum pendulum;
    private int median;

    public PendulumPanel() {
        super();
        init();
    }

    private void init() {
        thisInit();
        initConditions();
        new RefreshThread().start();
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (drawLine) {
            g.setColor(Color.red);
            median = this.getWidth() / 2;
            g.drawLine(median, 0, median, this.getHeight());
        }

        if (pendulum != null) {
            pendulum.draw(g2d);
        }
    }

    private void thisInit() {
        this.setBackground(Color.black);
        this.addMouseListener(new MouseAdapter() {

            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                mousePressed(e);
            }


            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (pendulum != null)
                    pendulum.setHoldX(Math.abs(median - e.getX()));
            }
        });
    }

    private void initConditions() {
        drawLine = false;
        active = false;
    }

    public boolean isDrawLine() {
        return drawLine;
    }

    public void setDrawLine(boolean condition) {
        drawLine = condition;
    }

    public void startPendulum() {
        pendulum = new Pendulum(this, 8, 17, 16);
        active = true;
        pendulum.setHoldX(0);
    }

    private class RefreshThread extends Thread {

        public void run() {
            //noinspection InfiniteLoopStatement
            while (true) {
                try {
                    Thread.sleep((int) (REFRESH_TIME * 1000));
                } catch (InterruptedException ignored) {
                }

                PendulumPanel.this.repaint();

                if (active) {
                    pendulum.refresh(.1);
                }

            }
        }
    }

}
