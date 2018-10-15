package main

import ui.ParentWindow
import javax.swing.UIManager

fun main(args: Array<String>) {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    ParentWindow()
}