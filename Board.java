package omok;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private int size;

    private Player[][] board;
    /** Create a new board of the default size. */
    public Board() {
        this(15);
    }
    /** Create a new board of the specified size. */
    public Board(int size) {
        this.size = size;
        board = new Player[size][size];
    }
    /** Return the size of this board. */
    public int size() {
        return size;
    }
    /** Removes all the stones placed on the board, effectively
     * resetting the board to its original state.
     */
    public void clear() {
        board = new Player[size][size];
    }
    /** Return a boolean value indicating whether all the places
     * on the board are occupied or not.
     */
    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (isEmpty(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Place a stone for the specified player at a specified
     * intersection (x, y) on the board.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     * @param player Player whose stone is to be placed
     */
    public void placeStone(int x, int y, Player player) {
        if (isEmpty(x, y)) {
            board[x][y] = player;
        }
    }
    /**
     * Return a boolean value indicating whether the specified
     * intersection (x, y) on the board is empty or not.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public boolean isEmpty(int x, int y) {
        return board[x][y] == null;
    }
    /**
     * Is the specified place on the board occupied?
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public boolean isOccupied(int x, int y) {
        return !isEmpty(x, y);
    }
    /**
     * Rreturn a boolean value indicating whether the specified
     * intersection (x, y) on the board is occupied by the given
     * player or not.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public boolean isOccupiedBy(int x, int y, Player player) {
        return board[x][y] == player;
    }
    /**
     * Return the player who occupies the specified intersection (x, y)
     * on the board. If the place is empty, this method returns null.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public Player playerAt(int x, int y) {
        return board[x][y];
    }
    /**
     * Return a boolean value indicating whether the given player
     * has a winning row on the board. A winning row is a consecutive
     * sequence of five or more stones placed by the same player in
     * a horizontal, vertical, or diagonal direction.
     */
    public boolean isWonBy(Player player) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == player) {
                    List<Place> row = checkWin(i, j);
                    if (row != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /** Return the winning row. For those who are not familiar with
     * the Iterable interface, you may return an object of
     * List<Place>. */
    public List<Place> winningRow() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Player current = board[i][j];
                if (current == null) {
                    continue;
                }
                List<Place> row = checkWin(i, j);
                if (row != null) {
                    return row;
                }
            }
        }
        return null;
    }

    /**
     *
     *
     Check if there is a winning row of five or more stones of the same color that includes the specified position on the board.
     @param x the 0-based column index of the position to check
     @param y the 0-based row index of the position to check
     @return a List of Places that form a winning row, or null if there is no winning row
     */

    private List<Place> checkWin(int x, int y) {
        int[][] directions = {{1, 0}, {0, 1}, {1, 1}, {1, -1}};
        Player player = board[x][y];

        for (int[] dir : directions) {
            List<Place> sequence = new ArrayList<>();
            int newX = x;
            int newY = y;

            while (newX >= 0 && newX < size && newY >= 0 && newY < size && board[newX][newY] == player) {
                sequence.add(new Place(newX, newY));
                newX += dir[0];
                newY += dir[1];
            }

            if (sequence.size() >= 5) {
                return sequence;
            }
        }

        return null;
    }
    /**
     * An intersection on an Omok board identified by its 0-based column
     * index (x) and row index (y). The indices determine the position
     * of a place on the board, with (0, 0) denoting the top-left
     * corner and (n-1, n-1) denoting the bottom-right corner,
     * where n is the size of the board.
     */
    public static class Place {
        public final int x;
        public final int y;

        public Place(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
