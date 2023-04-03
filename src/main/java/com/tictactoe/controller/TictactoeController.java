package com.tictactoe.controller;

import com.tictactoe.logic.TictactoeService;
import com.tictactoe.model.Move;
import com.tictactoe.model.Response;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin
public class TictactoeController {
    TictactoeService tictactoeService = new TictactoeService();

    @PostMapping("/")
    public Response makeMove(@RequestBody Move move) {
        return tictactoeService.makeMove(move);
    }

    @GetMapping("/restart")
    public Character[][] restart() {
        return tictactoeService.newGame();
    }

    @GetMapping("/")
    public Character[][] game() {
        return tictactoeService.newGame();
    }
}
