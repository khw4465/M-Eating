package MEating.service;

import MEating.domain.*;
import MEating.exception.NoBoardException;
import MEating.repository.CommentRepository;
import jakarta.persistence.EntityManager;
import jdk.jfr.Name;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CommentServiceTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private EntityManager em;

    @Test
    @Rollback(false)
    public void 댓글생성() {
        Member member = new Member();
        member.setName("말왕");
        member.addRegionToMember(RegionName.Seoul);
        em.persist(member);

        Board board = Board.createBoard(member, FoodType.JOKBAL, 0);
        board.setTitle("장충동 왕!족!발! 보싸~암");
        board.setContent("이거 보세요~오~!");
        boardService.write(member, board);

        String content = "이러케! 어!";
        Long findComment = commentService.write(member, board, content);

        assertEquals(content, commentService.findOne(findComment).getContent());

        boardService.remove(board);

        System.out.println("findBoard2 = " + member.getBoards().get(0));
        System.out.println("findComment2 = " + member.getComments().get(0));
    }

    @Test(expected = NoBoardException.class)
    public void 댓글작성_예외() {
        Member member = new Member();
        member.setName("김채원");
        member.addRegionToMember(RegionName.Seoul);
        em.persist(member);

        Board board = Board.createBoard(member, FoodType.SUSHI, 0);
        board.setTitle("너 내 동료가 되라");
        board.setContent("도독도도ㅔ독");
        Long id = boardService.write(member, board);

        String content = "ㅋㅋㅋㅋㅋㅋ";

        boardService.remove(board);
        em.clear();

        Board findBoard = boardService.findOne(id);
        commentService.write(member, findBoard, content);

        fail("예외가 발생해야한다.");
    }
}