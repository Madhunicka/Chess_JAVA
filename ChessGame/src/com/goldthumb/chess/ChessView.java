package com.goldthumb.chess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ChessView extends JPanel implements MouseListener{

	private static final long serialVersionUID = 1038510914214091521L;
	double scaleFactor = 0.9;
	int originX = -1;
	int originY = -1;
	int cellSize = -1;
	
	
	private ChessDelegate chessDelegate;

	// load all images using map
	private Map<String, Image> keyNameValueImage = new HashMap<String, Image>();
	private int fromCol=-1;
	private int fromRow=-1;
	

	ChessView(ChessDelegate chessDelegate) {
		this.chessDelegate=chessDelegate;
		String[] imageNames = { 
		ChessConstants.bBishop, 
		ChessConstants.wBishop,
		ChessConstants.bKing,
		ChessConstants.wKing,
		ChessConstants.bKnight,
		ChessConstants.wKnight,
		ChessConstants.bPawn,
		ChessConstants.wPawn,
		ChessConstants.bQueen,
		ChessConstants.wQueen,
		ChessConstants.bRook,
		ChessConstants.wRook,
			
		};

		try {
			for (String imgNm : imageNames) {
				Image img = loadImage(imgNm + ".png");
				keyNameValueImage.put(imgNm, img);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		addMouseListener(this);
	}

	@Override
	protected void paintChildren(Graphics g) {

		super.paintChildren(g);
		
		int smaller = Math.min(getSize().width, getSize().height);
		cellSize=(int) ((int)((double)smaller)*scaleFactor / 8);
		originX=(getSize().width-8*cellSize)/2;
		originX=(getSize().height-8*cellSize)/2;

		Graphics2D g2 = (Graphics2D) g;

		drawBoard(g2);
		drawPieces(g2);

	}
	
	
	private void drawPieces(Graphics2D g2) {
		
		for(int row=7; row>=0; row--) {
			for(int col=0; col<8; col++) {
				
				ChessPiece p = chessDelegate.pieceAt(col, row);
				if(p!=null) {
					drawImage(g2,col,row,p.imgName);
				}
				
			}
		}
//		drawImage(g2, 0, 0, "Knight-black");

		
	}
	
	private void drawImage(Graphics2D g2, int col, int row, String imgName) {
		Image img = keyNameValueImage.get(imgName);
		g2.drawImage(img, originX+col * cellSize, originY+row * cellSize,cellSize, cellSize, null);
		
		
	}

	private Image loadImage(String ImgFileName) throws Exception {

		ClassLoader classLoader = getClass().getClassLoader();
		URL resURL = classLoader.getResource("img/"+ ImgFileName);
		if (resURL == null) {
			return null;

		} else {

			File imgFile = new File(resURL.toURI());
			return ImageIO.read(imgFile);

		}

	}

	private void drawBoard(Graphics2D g2) {
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 4; i++) {
				drawSquare(g2, 0 + 2 * i, 0 + 2 * j, true);
				drawSquare(g2, 1 + 2 * i, 1 + 2 * j, true);

				drawSquare(g2, 1 + 2 * i, 0 + 2 * j, false);
				drawSquare(g2, 0 + 2 * i, 1 + 2 * j, false);

			}
		}

	}

	private void drawSquare(Graphics2D g2, int row, int col, boolean light) {
		Color brown = new Color(193, 154, 107);
		;
		g2.setColor(light ? brown : Color.BLACK);
		g2.fillRect(originX + col * cellSize, originY + row * cellSize, cellSize, cellSize);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		fromCol = (e.getPoint().x-originX)/cellSize;
		fromRow = (e.getPoint().y-originY)/cellSize;

		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int col = (e.getPoint().x-originX)/cellSize;
		int row = (e.getPoint().y-originY)/cellSize;

//		System.out.println("from "+ fromCol+" to"+ col);
		chessDelegate.movePiece(fromCol, fromRow, col, row);
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
