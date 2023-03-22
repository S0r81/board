package omok;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;
    private Player player1;
    private Player player2;

    @BeforeEach
    void setUp() {
        board = new Board();
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
    }

    @Test
    void testBoardSize() {
        assertEquals(15, board.size());
    }

    @Test
    void testClear() {
        board.placeStone(0, 0, player1);
        board.clear();
        assertTrue(board.isEmpty(0, 0));
    }

    @Test
    void testIsFull() {
        assertFalse(board.isFull());

        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.size(); j++) {
                board.placeStone(i, j, player1);
            }
        }

        assertTrue(board.isFull());
    }

    @Test
    void testPlaceStone() {
        board.placeStone(0, 0, player1);
        assertFalse(board.isEmpty(0, 0));
        assertEquals(player1, board.playerAt(0, 0));
    }

    @Test
    void testIsEmpty() {
        assertTrue(board.isEmpty(0, 0));
    }

    @Test
    void testIsOccupied() {
        assertFalse(board.isOccupied(0, 0));
        board.placeStone(0, 0, player1);
        assertTrue(board.isOccupied(0, 0));
    }

    @Test
    void testIsOccupiedBy() {
        assertFalse(board.isOccupiedBy(0, 0, player1));
        board.placeStone(0, 0, player1);
        assertTrue(board.isOccupiedBy(0, 0, player1));
    }

    @Test
    void testPlayerAt() {
        assertNull(board.playerAt(0, 0));
        board.placeStone(0, 0, player1);
        assertEquals(player1, board.playerAt(0, 0));
    }

    @Test
    void testIsWonBy() {
        assertFalse(board.isWonBy(player1));

        for (int i = 0; i < 5; i++) {
            board.placeStone(i, 0, player1);
        }

        assertTrue(board.isWonBy(player1));
    }

    @Test
    void testWinningRow() {
        assertNull(board.winningRow());

        for (int i = 0; i < 5; i++) {
            board.placeStone(i, 0, player1);
        }

        assertNotNull(board.winningRow());
        assertEquals(5, board.winningRow().size());
    }
}
