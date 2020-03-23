package dev.shroysha.ap.pendulum.model;

import javax.swing.*;
import java.awt.*;

public final class Pendulum {

    private final int NUM_OF_BALLS;
    private final double LONGEST_WIRE;
    private final int CYCLES;
    private final JPanel panel;
    private PendulumBall[] balls;
    private int holdX = 0; // At hold x, the velocity equals zero.

    public Pendulum(JPanel panel, int NUM_OF_BALLS, double LONGEST_WIRE, int cycles) {
        this.panel = panel;
        this.NUM_OF_BALLS = NUM_OF_BALLS;
        this.LONGEST_WIRE = LONGEST_WIRE;
        this.CYCLES = cycles;
        createBalls();
    }

    private void createBalls() {
        PendulumBall.setNUMBER_OF_BALLS(NUM_OF_BALLS);
        balls = new PendulumBall[NUM_OF_BALLS];


        for (int i = 0; i < NUM_OF_BALLS; i++) {
            int cycle = CYCLES + i;
            double length = calculateLength(cycle);
            balls[i] = new PendulumBall(length, i, panel, this);
        }
    }

    private double calculateLength(int cycle) {
        double length;

        length = LONGEST_WIRE * Math.pow(((double) CYCLES / cycle), 2);

        return length;
    }

    public void refresh(double time) {
        for (PendulumBall ball : balls) {
            ball.move(time);
        }
    }

    public void draw(Graphics2D g) {
        for (PendulumBall ball : balls) {
            ball.draw(g);
        }
    }

    public int getHoldX() {
        return holdX;
    }

    public void setHoldX(int holdX) {
        this.holdX = holdX;
        for (PendulumBall ball : balls) {
            ball.resetTime();
        }
    }
}
