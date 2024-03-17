package com.goldthumb.chess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ChessView extends JPanel {

	private static final long serialVersionUID = 1038510914214091521L;
	int originX = 55;
	int originY = 40;
	int cellSize = 60;
	
	ChessDelegate chessDelegate;

	// load all images using map
	Map<String, Image> keyNameValueImage = new HashMap<String, Image>();

	public ChessView() {
		String[] imageNames = { 
		"Bishop-black", 
		"Bishop-white",
		"King-black",
		"King-white",
		"Knight-black",
		"Knight-white",
		"Pawn-black",
		"Pawn-white",
		"Queen-black",
		"Queen-white",
		"Rook-black",
		"Rook-white"
			
		};

		try {
			for (String imgNm : imageNames) {
				Image img = loadImage(imgNm + ".png");
				keyNameValueImage.put(imgNm, img);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Override
	protected void paintChildren(Graphics g) {

		super.paintChildren(g);

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

}