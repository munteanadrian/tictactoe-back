package com.tictactoe.logic;

import com.tictactoe.model.Move;
import com.tictactoe.model.Response;

public class TictactoeService {
    private Character player;
    private String board;
    private String status;

    public TictactoeService() {
        this.newGame();
    }

    public Character[][] newGame() {
        this.player = 'X';
        this.board = "         ";
        this.status = "";

        return boardAsMatrix(this.board);
    }

    public static Character[][] boardAsMatrix(String board) {
        Character[][] boardMatrix = new Character[3][3];

        int current = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardMatrix[i][j] = board.charAt(current++);
            }
        }

        return boardMatrix;
    }

    public Response makeMove(Move move) {
        Response response = new Response();
        this.status = check();

        if (!this.status.equals("")) {
            response.other(this.status);
        } else if (board.charAt(move.getX() * 3 + move.getY()) != ' ') {
            response.occupied();
        } else {
            this.player = this.player == 'X' ? 'O' : 'X';
            board = board.substring(0, move.getX() * 3 + move.getY()) + this.player + board.substring(move.getX() * 3 + move.getY() + 1);

            this.status = check();
            if (!this.status.equals("")) {
                response.winner(this.status, String.valueOf(this.player));
            } else {
                response.ok(String.valueOf(this.player));
            }
        }

        return response;
    }

    public String check() {
        String result = "";

        boolean X = false;
        boolean O = false;

        for (int i = 0; i < 3; i++) {
//            check lines
            if (board.charAt(i * 3) != ' ' && (board.charAt(i * 3) == board.charAt(i * 3 + 1) && board.charAt(i * 3 + 1) == board.charAt(i * 3 + 2))) {
                X = board.charAt(i * 3) == 'X';
                O = board.charAt(i * 3) == 'O';
            }

//            check columns
            if (board.charAt(i + 3) != ' ' && (board.charAt(i) == board.charAt(i + 3) && board.charAt(i + 3) == board.charAt(i + 6))) {
                X = board.charAt(i) == 'X';
                O = board.charAt(i) == 'O';
            }
        }

//        check main diagonal
        if (board.charAt(4) != ' ' && (board.charAt(0) == board.charAt(4) && board.charAt(4) == board.charAt(8))) {
            X = board.charAt(4) == 'X';
            O = board.charAt(4) == 'O';
        }

//        check secondary diagonal
        if (board.charAt(4) != ' ' && (board.charAt(2) == board.charAt(4) && board.charAt(4) == board.charAt(6))) {
            X = board.charAt(4) == 'X';
            O = board.charAt(4) == 'O';
        }

        if (!X && !O) {
            if (this.countAppearances(' ') == 0 && this.countAppearances('_') == 0) {
                result = "Draw";
            }
        } else if (X || O) {
            result = X ? "X wins" : (O ? "O wins" : null);
        }

        return result;
    }

    public int countAppearances(char lookup) {
        return board.length() - board.replace(String.valueOf(lookup), "").length();
    }
}
