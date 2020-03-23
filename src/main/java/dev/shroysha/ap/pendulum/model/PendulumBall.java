package dev.shroysha.ap.pendulum.model;

import javax.swing.*;
import java.awt.*;

public final class PendulumBall {

    public static final double AoG = 9.81;
    private static int NUMBER_OF_BALLS;
    private final int pos;
    private final double period;
    private final Color ballColor;
    private final JPanel panel;
    private final Pendulum pen;
    private double x, y;
    private double time;

    public PendulumBall(double lengthOfWire, int pos, JPanel panel, Pendulum pen) {
        this.x = 0;
        this.y = 0;
        this.pos = pos;
        this.panel = panel;
        this.pen = pen;
        ballColor = Color.gray;
        period = 2 * Math.PI * Math.pow(lengthOfWire / AoG, .5);
        time = 0;
    }

    public static void setNUMBER_OF_BALLS(int NUMBER_OF_BALLS) {
        PendulumBall.NUMBER_OF_BALLS = NUMBER_OF_BALLS;
    }

    public void move(double sec) {
        time += sec;

        if (time > period) {
            time = 0;
        }

        x = -((pen.getHoldX() * Math.sin(time / Math.PI)) / period);
        System.out.println(x);
    }

    public void draw(Graphics2D g) {
        g.setColor(ballColor);

        final int size = panel.getHeight() / NUMBER_OF_BALLS / 2;
        //int ballX =  (int) (panel.getWidth() / 2 - pen.getHoldX() + x);
        int ballX = (int) (pen.getHoldX() + x);
        y = size * (pos * 2.0) + size / 2.0;

        g.fillOval(ballX, (int) y, size, size);
    }

    public void resetTime() {
        time = 0;
    }

}
