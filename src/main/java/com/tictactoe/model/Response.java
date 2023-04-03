package com.tictactoe.model;

public class Response {
    private int code;
    private String player;
    private String message;

    public Response() {
        this.ok("");
    }

    public void ok(String player) {
        this.code = 0;
        this.player = player;
        this.message = "";
    }

    public void occupied() {
        this.code = 1;
        this.player = "";
        this.message = "Occupied";
    }

    public void other(String currentState) {
        this.code = 2;
        this.player = "";
        this.message = currentState;
    }

    public void winner(String winner, String player) {
        this.code = 3;
        this.player = player;
        this.message = winner;
    }

    public int getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

    public String getPlayer() {
        return player;
    }
}
