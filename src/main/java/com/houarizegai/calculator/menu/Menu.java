package com.houarizegai.calculator.menu;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JMenu;

public class Menu extends JMenu  {
    
    public Menu(String menuText) {
        setFont(new Font("Tahoma", Font.BOLD, 22));
        setText(menuText);
    }
        
}

