package MEating.service;

import MEating.domain.*;
import MEating.repository.ChatroomRepository;
import jakarta.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ChatroomServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private ChatroomService chatroomService;
    @Autowired
    private EntityManager em;

    @Test
    public void 채팅방생성() {
        Member member1 = new Member();
        member1.setName("회원1");
        member1.addRegionToMember(RegionName.GyungGi);
        memberService.join(member1);

        Board board = Board.createBoard(member1, FoodType.HAMBURGER, 0);
        board.setTitle("햄버거 드실분");
        board.setContent("구합니다");
        Long boardId = boardService.write(member1, board);

        Member member2 = new Member();
        member2.setName("회원2");
        member2.addRegionToMember(RegionName.GyungGi);
        memberService.join(member2);

        List<Board> list = boardService.findAll(member2, 0, FoodType.HAMBURGER);
        Board findBoard = list.get(0);

        Chatroom chatroom = Chatroom.createChatroom(member2, findBoard);
        Long chatroomId = chatroomService.create(chatroom);

        assertEquals(chatroom, chatroomService.findOne(chatroomId));
    }
}