package com.goldthumb.chess;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Chess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Welcome to Chess!!!");
		frame.setSize(600,600);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = frame.getSize().width;
        int frameHeight = frame.getSize().height;
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        frame.setLocation(x, y);
		
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ChessPanel panel = new ChessPanel();
		frame.add(panel);
		frame.setVisible(true);
//		frame.setResizable(false);
		
		
		
		

	}

}
