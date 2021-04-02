package com.dxc.fresher.commentservice.controllers;

import com.dxc.fresher.commentservice.entitties.Comment;
import com.dxc.fresher.commentservice.services.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentControler {

    @Autowired
    private CommentService  commentService;

    @PostMapping("/{userName}/{fileId}")
    public ResponseEntity<Void> saveComment(@RequestBody Comment comment, @PathVariable("userName") String userName, @PathVariable("fileId") int fileId){
            commentService.saveComment(comment,fileId,userName);
            return ResponseEntity.ok().build();
    }

    @GetMapping("/{fileId}")
    private ResponseEntity<List<Comment>> getComment(@PathVariable("fileId") int fileId){
      List<Comment> commentList =  commentService.getComment(fileId);
      return new ResponseEntity<>(commentList, HttpStatus.OK);
    }
}
