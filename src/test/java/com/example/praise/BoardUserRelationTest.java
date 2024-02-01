package com.example.praise;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.praise.model.entity.Board;
import com.example.praise.model.entity.User;
import com.example.praise.repository.BoardRepository;
import com.example.praise.repository.UserRepository;

@SpringBootTest
public class BoardUserRelationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testBoardUserRelation() {
        // User 객체 생성 및 저장
    	User user = User.builder().userId(1).name("LMS").build();
    	
    	User user0 = new User(2, "LLLLAAAAA", "PASASDFS", "이ASDF수", "AsI", "iso", 10, null,null);
    	//User user1 = new User(1, "Leeminsu", "123", "이민수", "AI", "io", 100000, null);
        userRepository.save(user);
        //User user2 = new User(2, "Kim se", "pass", "김세은", "CL", "iio", 10000, null);
        userRepository.save(user0);

        // Board 객체 생성 및 저장
        Board board1 = new Board(10, "이민수를 칭찬합니다.", "이민수는 타의 모범이 되었습니다.", user, user0);
        boardRepository.save(board1);

        // Board 객체가 null이 아닌지 확인
//        assertNotNull(board1, "Board 객체가 null입니다.");

        // Board 객체의 recevieUser 필드가 null이 아닌지 확인
        //assertNotNull(board1.getRecevieUser(), "Board의 recevieUser 필드가 null입니다.");

        // User 객체들을 Board에 추가
//        board1.addUser(user1);
//        board1.addUser(user2);
        
        // 추가 검증 로직...
    }
}
