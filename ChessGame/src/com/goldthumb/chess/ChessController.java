package com.goldthumb.chess;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class ChessController implements ChessDelegate{
	private  ChessModel chessModel = new ChessModel();
	private ChessView panel;
	
	ChessController(){
		
		chessModel.reset();
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
		panel = new ChessView();
		panel.chessDelegate=this;
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setResizable(false);
		
	}

	public static void main(String[] args) {
		
		new ChessController();
		

	}

	@Override
	public ChessPiece pieceAt(int col, int row) {
		// TODO Auto-generated method stub
		return chessModel.pieceAt(col, row);
	}

}
