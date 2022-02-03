package com.young.blog.repository;

import javax.transaction.Transactional;

import com.young.blog.model.Reply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    
    //네이티브 쿼리 사용하여 db 접근
    //@Modifying //insert update delete 를 네이티브 쿼리로 동작할경우 붙어야하는 어노테이션임(resultset 관련)
    //@Query(value = "INSERT INTO REPLY(userId, boardId,content, createDate) VALUES(?1,?2,?3,now())")
    //public int reply_save(int userId, int boardId, String content); // 업데이트된 행의 개수를 리턴해줌 따라서 리턴값을 int로 해야함

}
