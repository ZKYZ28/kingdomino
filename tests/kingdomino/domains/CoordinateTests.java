package kingdomino.domains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CoordinateTests {

	@Test
	void iniCoordinate() {
		//GIVEN
		Coordinate coord = new Coordinate();
		
		//WHEN
		assertEquals(0, coord.getCol());
		assertEquals(0, coord.getRow());
		assertEquals(0, coord.getDirection());
	}
	
	@Test
	void getRowSecondDirectionOne() {
		//GIVEN
		Coordinate coord = new Coordinate();
		//WHEN
		coord.setDirection(1);
		
		//THEN
		assertEquals(1, coord.getDirection());
		assertEquals(-1, coord.getRowSecond());
	}
	
	@Test
	void getRowSecondDirectionThree() {
		//GIVEN
		Coordinate coord = new Coordinate();
		
		//WHNEN
		coord.setDirection(3);
		
		//THEN
		assertEquals(3, coord.getDirection());
		assertEquals(1, coord.getRowSecond());
	}
	
	@Test
	void getColSecondDirectionZero() {
		Coordinate coord = new Coordinate();
		assertEquals(1, coord.getColSecond());
	}
	
	@Test
	void getRowSecondDirectionTwo() {
		Coordinate coord = new Coordinate();
		coord.setDirection(2);
		assertEquals(2, coord.getDirection());
		assertEquals(-1, coord.getColSecond());
	}
	
	@Test
	void normalCaseTranslate() {
		//GIVEN
		Coordinate coord = new Coordinate();
		coord.setRow(5);
		coord.setCol(5);
		
		//WHEN
		coord.translate(1, 0);
		
		//THEN
		assertEquals(6, coord.getRow());
		assertEquals(5, coord.getCol());
	}
	
	@Test
	void cantTranslateWithMaxRow() {
		//GIVEN
		Coordinate coord = new Coordinate();
		coord.setRow(8);
		coord.setCol(7);
		
		//WHEN
		coord.translate(1, 0);
		
		//THEN
		assertEquals(8, coord.getRow());
		assertEquals(7, coord.getCol());
	}
	
	@Test
	void cantTranslateWithMinRow() {
		//GIVEN
		Coordinate coord = new Coordinate();
		coord.setRow(0);
		coord.setCol(7);
		
		//WHEN
		coord.translate(-1, 0);
		
		//THEN
		assertEquals(0, coord.getRow());
		assertEquals(7, coord.getCol());
	}
	
	@Test
	void cantTranslateWithMaxCol() {
		//GIVEN
		Coordinate coord = new Coordinate();
		coord.setRow(7);
		coord.setCol(8);
		
		//WHEN
		coord.translate(0, 1);
		
		//THEN
		assertEquals(7, coord.getRow());
		assertEquals(8, coord.getCol());
	}
	
	@Test
	void cantTranslateWithMinCol() {
		//GIVEN
		Coordinate coord = new Coordinate();
		coord.setRow(1);
		coord.setCol(0);
		
		//WHEN
		coord.translate(0, -1);
		
		//THEN
		assertEquals(1, coord.getRow());
		assertEquals(0, coord.getCol());
	}
	
	@Test
	void normalCaseRotate() {
		//GIVEN
		Coordinate coord = new Coordinate();;
		coord.setRow(7);
		coord.setCol(6);
		assertEquals(0, coord.getDirection());
		
		//WHEN
		coord.rotate();
		
		//THEN
		assertEquals(1, coord.getDirection());
	}
	
	@Test
	void normalCaseRotateForToZero() {
		//GIVEN
		Coordinate coord = new Coordinate();;
		coord.setRow(7);
		coord.setCol(6);
		coord.setDirection(3);
		assertEquals(3, coord.getDirection());
		
		//WHEN
		coord.rotate();
		
		//THEN
		assertEquals(0, coord.getDirection());
	}
	
	@Test
	void cantRotateTopLeft() {
		//GIVEN
		Coordinate coord = new Coordinate();;
		coord.setRow(0);
		coord.setCol(1);
		assertEquals(0, coord.getDirection());
		
		//WHEN
		coord.rotate();
		
		//THEN
		assertEquals(0, coord.getDirection());
	}
	
	@Test
	void cantRotateBottomLeft() {
		//GIVEN
		Coordinate coord = new Coordinate();;
		coord.setRow(8);
		coord.setCol(0);
		coord.setDirection(1);
		assertEquals(1, coord.getDirection());
		
		//WHEN
		coord.rotate();
		
		//THEN
		assertEquals(1, coord.getDirection());
	}
	
	@Test
	void resetCoord() {
		//GIVEN
		Coordinate coord = new Coordinate();
		coord.setRow(5);
		coord.setCol(6);
		coord.setDirection(2);
		
		//WHEN
		coord.resetCoord();
		
		//THEN
		assertEquals(0, coord.getCol());
		assertEquals(0, coord.getRow());
		assertEquals(0, coord.getDirection());
	}
	

}
