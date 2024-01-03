package MEating.service;

import MEating.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PromiseServiceTest {

    @Autowired PromiseService promiseService;
    @Autowired MemberService memberService;
    @Autowired BoardService boardService;
    @Autowired ChatroomService chatroomService;
    @Autowired ChattingService chattingService;

    @Test
    @Rollback(false)
    public void 모임_생성() {
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

        Long chatroomId = chatroomService.create(member2, findBoard);
        Chatroom findChatroom = chatroomService.findOne(chatroomId);

        Long promiseId = promiseService.create(findBoard, "모임", "2024-01-01 19:30", "맥도날드 강남점", member1, member2);

        Member member3 = new Member();
        member3.setName("회원3");
        member3.addRegionToMember(RegionName.GyungGi);
        memberService.join(member3);

        Promise findPromise = promiseService.findOne(promiseId);
        findPromise.joinPromise(member3);
    }
}