package com.houarizegai.calculator.menu;
import javax.swing.JMenuItem;

public class MenuItem extends JMenuItem {
    String menuText;
    public MenuItem(String menuText){
        this.menuText = menuText;
        setText(this.menuText);
        
        
    }
}