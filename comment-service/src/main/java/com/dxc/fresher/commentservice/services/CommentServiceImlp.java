package com.dxc.fresher.commentservice.services;

import com.dxc.fresher.commentservice.entitties.Comment;
import com.dxc.fresher.commentservice.repositories.CommentRepository;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImlp implements CommentService {

    @Autowired
    private CommentRepository commentRepository;


    @Override
    @Transactional
    public void saveComment(Comment comment,int fileId, String userName) {
        Comment commentRepo = new Comment();
        commentRepo.setUserName(userName);
        commentRepo.setFileId(fileId);
        commentRepo.setContent(comment.getContent());
        commentRepository.save(commentRepo);
    }


    @Override
    @Transactional
    public List<Comment> getComment(int fileId) {
        return commentRepository.findByFileId(fileId);
    }
}
