package com.dxc.fresher.commentservice.services;

import com.dxc.fresher.commentservice.entitties.Comment;

import java.util.List;

public interface CommentService {

        public void saveComment (Comment comment,int fileId, String userName);

        public List<Comment> getComment(int fileId);

}
