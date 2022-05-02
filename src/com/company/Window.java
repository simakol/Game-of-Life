package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.annotation.processing.SupportedSourceVersion;
import javax.swing.*;

public class Window extends JFrame {
    private JPanel panel;
    private JFrame frame;
    private Box[][] boxes;


    public Window(int w, int h) {
//        initPanel(h, w);
        initFrame(w, h);
        initBoxes();
    }

    private void initFrame(int w, int h) {
        frame = new JFrame();
//        pack();
//        frame.setPreferredSize(new Dimension(w, h));
        frame.setSize(w, h);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // устанавливаем позицию окна по центру
        frame.setResizable(false); // запрещаем изменение размеров окна
        frame.setTitle("Game of Life");
        frame.setVisible(true);

    }

    private void initPanel(int h, int w) {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(w, h));
        this.frame.add(panel);
    }

    private void initBoxes() {
        boxes = new Box[Config.CELLMAP_WIDTH][Config.CELLMAP_HEIGHT];
        for (int x = 0; x < Config.CELLMAP_WIDTH; x++) {
            for (int y = 0; y < Config.CELLMAP_HEIGHT; y++) {
                boxes[x][y] = new Box(x, y);
                frame.add(boxes[x][y]);
            }
        }

    }

//    private void initContainer() {
//        JButton startButton = new JButton("Почати");
//        JButton newGameButton = new JButton("Нова гра");
//        Container container = this.getContentPane();
//        container.setLayout(new GridLayout(1, 2, 10, 10));
//        startButton.addActionListener(new ButtonEventListener());
//        container.add(startButton);
//        newGameButton.addActionListener(new ButtonEventListener());
//        container.add(newGameButton);
//    }
//
//    class ButtonEventListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("Pressed!");
//            System.out.println(e);
//
//        }
//    }
}
