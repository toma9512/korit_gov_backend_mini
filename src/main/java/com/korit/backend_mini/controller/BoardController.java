package com.korit.backend_mini.controller;

import com.korit.backend_mini.dto.board.AddBoardReqDto;
import com.korit.backend_mini.dto.board.ModifyBoardReqDto;
import com.korit.backend_mini.dto.board.RemoveBoardReqDto;
import com.korit.backend_mini.security.model.PrincipalUser;
import com.korit.backend_mini.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/add")
    public ResponseEntity<?> addBoard(@RequestBody AddBoardReqDto addBoardReqDto, @AuthenticationPrincipal PrincipalUser principalUser) {
        return ResponseEntity.ok(boardService.addBoard(addBoardReqDto, principalUser));
    }

    @GetMapping("/list/infinite")
    public ResponseEntity<?> getBoardInfinite(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime cursorCreateDt,
            @RequestParam(required = false) Integer cursorBoardId,
            @RequestParam Integer limit) {
        return ResponseEntity.ok(boardService.getBoardInfinite(cursorCreateDt, cursorBoardId, limit));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getBoardList() {
        return ResponseEntity.ok(boardService.getBoardList());
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<?> getBoardByBoardId(@PathVariable Integer boardId) {
        return ResponseEntity.ok(boardService.getBoardByBoardId(boardId));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getBoardListByKeyword(@RequestParam String keyword) {
        return ResponseEntity.ok(boardService.getBoardListByKeyword(keyword));
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modifyBoard(@RequestBody ModifyBoardReqDto modifyBoardReqDto, @AuthenticationPrincipal PrincipalUser principalUser) {
        return ResponseEntity.ok(boardService.modifyBoard(modifyBoardReqDto, principalUser));
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removeBoard(@RequestBody RemoveBoardReqDto removeBoardReqDto, @AuthenticationPrincipal PrincipalUser principalUser) {
        return ResponseEntity.ok(boardService.removeBoard(removeBoardReqDto, principalUser));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getBoardListByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(boardService.getBoardListByUserId(userId));
    }
}









