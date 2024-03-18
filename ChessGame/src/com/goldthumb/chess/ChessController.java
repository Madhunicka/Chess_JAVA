package com.goldthumb.chess;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessController implements ChessDelegate{
	private  ChessModel chessModel = new ChessModel();
	private ChessView chessBoardPanel;
	
	ChessController(){
		
		chessModel.reset();
		JFrame frame = new JFrame("Welcome to Chess!!!");
		frame.setSize(600,600);
		frame.setLayout(new BorderLayout());
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        int screenWidth = screenSize.width;
//        int screenHeight = screenSize.height;
//        int frameWidth = frame.getSize().width;
//        int frameHeight = frame.getSize().height;
//        int x = (screenWidth - frameWidth) / 2;
//        int y = (screenHeight - frameHeight) / 2;
//        frame.setLocation(x, y);
//		
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chessBoardPanel = new ChessView(this);
		
		frame.add(chessBoardPanel, BorderLayout.CENTER);
		
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton resetBtn = new JButton("Reset");
		
		resetBtn.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				chessModel.reset();
				chessBoardPanel.repaint();
			}
		});
		
		
		buttonsPanel.add(resetBtn);
		buttonsPanel.add(new JButton("Delete me"));
		
		frame.add(buttonsPanel, BorderLayout.PAGE_END);
		frame.add(chessBoardPanel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setResizable(false);
		
	}

	public static void main(String[] args) {
		
		new ChessController();
		

	}

	@Override
	public ChessPiece pieceAt(int col, int row) {
		return chessModel.pieceAt(col, row);
	}

	@Override
	public void movePiece(int fromCol, int fromRow, int toCol, int toRow) {
		chessModel.movePiece(fromCol, fromRow, toCol, toRow);
		chessBoardPanel.repaint();
		
	}

}
