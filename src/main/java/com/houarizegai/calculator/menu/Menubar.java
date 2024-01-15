package com.houarizegai.calculator.menu;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;

public class Menubar extends JMenuBar {

    public Menubar() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setPreferredSize(new Dimension(WIDTH, 50));
        setBorder(BorderFactory.createEmptyBorder());        
        setOpaque(true);
    }
    
}