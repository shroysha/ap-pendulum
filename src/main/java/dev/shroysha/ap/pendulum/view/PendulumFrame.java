package dev.shroysha.ap.pendulum.view;

import javax.swing.*;
import java.awt.*;

public class PendulumFrame extends JFrame {

    private PendulumPanel pendulumPanel;

    public PendulumFrame() {
        super("Pendulum Waves");
        init();
    }

    private void init() {
        createThis();

        pendulumPanel = new PendulumPanel();

        this.add(pendulumPanel, BorderLayout.CENTER);

    }

    private void createThis() {


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setLayout(new BorderLayout());
        this.setJMenuBar(new OptionsMenuBar());
    }

    private class OptionsMenuBar extends JMenuBar {
        public OptionsMenuBar() {
            init();
        }

        private void init() {

            JMenuItem drawLine = new JMenuItem("Draw Median Line");
            drawLine.addActionListener(e -> pendulumPanel.setDrawLine(!pendulumPanel.isDrawLine()));

            JMenuItem start = new JMenuItem("Start");
            start.addActionListener(e -> pendulumPanel.startPendulum());


            JMenu optionMenu = new JMenu("Options");
            optionMenu.add(start);
            optionMenu.add(drawLine);

            this.add(optionMenu);
        }
    }

}
