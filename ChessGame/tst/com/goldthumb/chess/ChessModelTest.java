package com.goldthumb.chess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.goldthumb.chess.ChessModel;
import com.goldthumb.chess.Player;
import com.goldthumb.chess.Rank;

class ChessModelTest {
	
	@Test
	void testReset() {
		ChessModel chessModel = new ChessModel();
		chessModel.reset();
		chessModel.movePiece(0, 1,0,2);
		chessModel.reset();
		assertNull(chessModel.pieceAt(0, 2));
		System.out.println(chessModel);
		
	}
	
	
	@Test
	
	void testMovePiece() {
		ChessModel chessModel = new ChessModel();
		chessModel.reset();
		System.out.println("Piece Moved ...");
		assertNull(chessModel.pieceAt(0, 2));
		
		chessModel.movePiece(0, 1,0,2);
		assertNotNull(chessModel.pieceAt(0, 0));
		System.out.println(chessModel);
		

		
	}
	
	@Test
	
	void testPieceAt(){
		ChessModel chessModel = new ChessModel();
		assertNull(chessModel.pieceAt(0, 0));
		
		chessModel.reset();
		assertNotNull(chessModel.pieceAt(0, 0));
		
		assertEquals(Player.WHITE, chessModel.pieceAt(0, 0).player);
		assertEquals(Rank.ROOK, chessModel.pieceAt(0, 0).rank);

		
		
	}

	@Test
	void testToString() {
		ChessModel chessModel = new ChessModel();
		assertTrue(chessModel.toString().contains("0 . . . . . . . ."));
	
		chessModel.reset();
		System.out.println(chessModel);
		assertTrue(chessModel.toString().contains("0 r n b q k b n r"));

		
		
	}

}
