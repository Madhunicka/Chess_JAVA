package com.goldthumb.chess;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ChessController implements ChessDelegate, ActionListener, Runnable{
	private  ChessModel chessModel = new ChessModel();
	private JFrame frame;
	private ChessView chessBoardPanel;
	private JButton resetBtn;
	private JButton serverBtn;
	private JButton clientBtn;
	private PrintWriter printWriter;
	private Scanner scanner;
	
	
	
	ChessController(){
		
		chessModel.reset();
		frame = new JFrame("Welcome to Chess!!!");
		frame.setSize(500,550);
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
		
		var buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		resetBtn = new JButton("Reset");
		
		resetBtn.addActionListener(this);
		
		
		buttonsPanel.add(resetBtn);
		serverBtn = new JButton("Listen");
		buttonsPanel.add(serverBtn);
		serverBtn.addActionListener(this);
		
		clientBtn = new JButton("Connect");
		buttonsPanel.add(clientBtn);
		clientBtn.addActionListener(this);
		
		frame.add(buttonsPanel, BorderLayout.PAGE_END);
		frame.add(chessBoardPanel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				printWriter.close();
				scanner.close();
				
			}
			
		});
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
		if(printWriter != null) {
		printWriter.println(fromCol + "," + fromRow+ "," + toCol+ ","+ toRow);
		}
		
	}
	
	private void receiveMove() {
		while(scanner.hasNextLine()) {
			var movStr = scanner.nextLine();  //"0,2,0,1"
			System.out.println("from server:"+ movStr);
			var movStrArr = movStr.split(","); //array of strong "0","1","0","2"
			int fromCol = Integer.parseInt(movStrArr[0]);
			var fromRow = Integer.parseInt(movStrArr[1]);
			var toCol = Integer.parseInt(movStrArr[2]);
			var toRow =Integer.parseInt(movStrArr[3]);
			SwingUtilities.invokeLater(new Runnable(){

				@Override
				public void run() {
					chessModel.movePiece(fromCol, fromRow, toCol, toRow);
					chessBoardPanel.repaint();
				}
				
			});
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==resetBtn) {
			chessModel.reset();
			chessBoardPanel.repaint();
		}
		else if(e.getSource() == serverBtn) {
			frame.setTitle("Chess Server");

			var pool = Executors.newFixedThreadPool(1);
			pool.execute(this);
			
		}else if(e.getSource() == clientBtn){
			frame.setTitle("Chess Client");
			
			try {
				if(scanner == null || printWriter == null){
					var socket = new Socket("localhost", 50000);
					scanner = new Scanner(socket.getInputStream());
					printWriter = new PrintWriter(socket.getOutputStream(), true);
					
				}
				
				Executors.newFixedThreadPool(1).execute(new Runnable() {

					@Override
					public void run() {
						receiveMove();						
					}
					
				});
				
								
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
		
	}

	@Override
	public void run() {
		try(var listener = new ServerSocket(50000)){
			System.out.println("server is listening to port 50000");
			
				
					if(scanner == null || printWriter==null) {
					var socket = listener.accept();
					printWriter  = new PrintWriter(socket.getOutputStream(), true);
					
					scanner = new Scanner(socket.getInputStream());
					}
					receiveMove();
//					printWriter.println("0,1,0,3");
//					System.out.println("server: sending a move to client");
					
				
		
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

}
