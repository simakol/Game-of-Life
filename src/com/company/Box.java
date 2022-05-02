package com.company;

import javax.swing.*;
import java.awt.*;

public class Box extends JPanel {
    public Box(int x, int y) {
        super();
        setBounds(x * Config.CELL_SIZE, y * Config.CELL_SIZE, Config.CELL_SIZE, Config.CELL_SIZE);
        setForeground(Color.BLACK);
//        setOpaque(true);

    }

}
