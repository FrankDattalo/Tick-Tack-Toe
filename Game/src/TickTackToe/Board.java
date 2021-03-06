package TickTackToe;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by fjdat on 3/27/2016.
 */
public class Board {
    private static List<Set<Position>> terminalCases;

    // Sets up list of terminal cases
    static {
        Board.terminalCases = new LinkedList<Set<Position>>();

        // Horizontal Terminal Cases
        Set<Position> s = new HashSet<Position>();
        s.add(Position.Zero);
        s.add(Position.One);
        s.add(Position.Two);
        Board.terminalCases.add(s);

        s = new HashSet<Position>();
        s.add(Position.Three);
        s.add(Position.Four);
        s.add(Position.Five);
        Board.terminalCases.add(s);

        s = new HashSet<Position>();
        s.add(Position.Six);
        s.add(Position.Seven);
        s.add(Position.Eight);
        Board.terminalCases.add(s);

        // Vertical Terminal Cases
        s = new HashSet<Position>();
        s.add(Position.Zero);
        s.add(Position.Three);
        s.add(Position.Six);
        Board.terminalCases.add(s);

        s = new HashSet<Position>();
        s.add(Position.One);
        s.add(Position.Four);
        s.add(Position.Seven);
        Board.terminalCases.add(s);

        s = new HashSet<Position>();
        s.add(Position.Two);
        s.add(Position.Five);
        s.add(Position.Eight);
        Board.terminalCases.add(s);

        // Diagonal Terminal Cases
        s = new HashSet<Position>();
        s.add(Position.Zero);
        s.add(Position.Four);
        s.add(Position.Eight);
        Board.terminalCases.add(s);

        s = new HashSet<Position>();
        s.add(Position.Two);
        s.add(Position.Four);
        s.add(Position.Six);
        Board.terminalCases.add(s);
    }

    private Action previousMove;
    private Set<Position> openLocations;
    private Set<Position> playerMoves;
    private Set<Position> computerMoves;

    /**
     * Board must be initialized.
     *
     * @param previousState  The previous state of the board.
     * @param previousAction The previous action.
     * @Requres Previous State must not be null.
     */
    public Board(Board previousState, Action previousAction) {
        this.computerMoves = new HashSet<Position>();
        this.playerMoves = new HashSet<Position>();
        this.previousMove = previousAction;
        this.openLocations = new HashSet<Position>();

        this.computerMoves.addAll(previousState.computerMoves);
        this.playerMoves.addAll(previousState.playerMoves);
        this.openLocations.addAll(previousState.openLocations);

        if (previousAction != null) {
            Player previousPlayer = previousAction.getPlayer();
            Position previousPosition = previousAction.getPosition();
            this.openLocations.remove(previousPosition);
            if (previousPlayer == Player.Computer) {
                this.computerMoves.add(previousPosition);
            } else {
                this.playerMoves.add(previousPosition);
            }
        }
    }

    /**
     * Default state of board.  All positions open, no actions taken.
     */
    public Board() {
        this.computerMoves = new HashSet<Position>();
        this.playerMoves = new HashSet<Position>();
        this.openLocations = new HashSet<Position>();
        this.previousMove = null;

        this.openLocations.add(Position.Zero);
        this.openLocations.add(Position.One);
        this.openLocations.add(Position.Two);
        this.openLocations.add(Position.Three);
        this.openLocations.add(Position.Four);
        this.openLocations.add(Position.Five);
        this.openLocations.add(Position.Six);
        this.openLocations.add(Position.Seven);
        this.openLocations.add(Position.Eight);
    }

    /**
     * Reports whether a given board state is a terminal case. IE -> no open positions, or a player has one.
     *
     * @return whether a given board state is a terminal case. IE -> no open positions, or a player has one.
     */
    public boolean isTerminalCase() {
        if (this.openLocations.size() == 0) {
            return true;
        }

        for (Set<Position> s :
                Board.terminalCases) {
            if (this.playerMoves.containsAll(s)) {
                return true;
            }
        }

        for (Set<Position> s :
                Board.terminalCases) {
            if (this.computerMoves.containsAll(s)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Reports whether this board state is a winning case.
     *
     * @return whether this board state is a winning case.
     */
    public boolean isWinningCase() {
        for (Set<Position> s :
                Board.terminalCases) {
            if (this.playerMoves.containsAll(s)) {
                return true;
            }
        }

        for (Set<Position> s :
                Board.terminalCases) {
            if (this.computerMoves.containsAll(s)) {
                return true;
            }
        }

        return false;
    }

    public Action getPreviousMove() {
        return this.previousMove;
    }

    public Set<Position> getOpenPositions() {
        return this.openLocations;
    }

    public void setPreviousMove(Action a) {
        this.previousMove = a;
    }

    public void display() {
        String spacer = "    -----------";
        System.out.println("y3: " + this.getValueAtPosition(Position.Six) + "|" +
                this.getValueAtPosition(Position.Seven) + "|" + this.getValueAtPosition(Position.Eight));
        System.out.println(spacer);
        System.out.println("y2: " + this.getValueAtPosition(Position.Three) + "|" +
                this.getValueAtPosition(Position.Four) + "|" + this.getValueAtPosition(Position.Five));
        System.out.println(spacer);
        System.out.println("y1: " + this.getValueAtPosition(Position.Zero) + "|" +
                this.getValueAtPosition(Position.One) + "|" + this.getValueAtPosition(Position.Two));
        System.out.println("     x1  x2  x3");

    }

    private String getValueAtPosition(Position p) {
        if(this.computerMoves.contains(p)) {
            return " X ";
        } else if(this.playerMoves.contains(p)) {
            return " O ";
        } else {
            return "   ";
        }
    }
}