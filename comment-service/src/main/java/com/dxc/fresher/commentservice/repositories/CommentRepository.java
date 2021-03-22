package com.dxc.fresher.commentservice.repositories;


import com.dxc.fresher.commentservice.entitties.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    public List<Comment> findByFileId(int fileId);
}
