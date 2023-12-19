package MEating.service;

import MEating.domain.*;
import MEating.repository.BoardRepository;
import jakarta.persistence.EntityManager;
import org.hibernate.type.BagType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardServiceTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;
    @Autowired
    private EntityManager em;

    @Test
    @Rollback(false)
    public void 게시물_등록() {

        Member member = new Member();
        member.setName("박명수");
        member.addRegionToMember(RegionName.JeonBuk);
        em.persist(member);

        Board board = Board.createBoard(member, FoodType.CHICKEN, 0);
        board.setTitle("치킨 먹을사람 구해요~!");
        board.setContent("내일 저녁 8시에 여의도에서 같이 치킨 뜯으실 분 구합니다!");
        board.setType(BoardType.MakeFriends);

        Board board1 = Board.createBoard(member, FoodType.PIZZA, 0);
        board.setTitle("피자 먹을사람 구해요~!");
        board.setContent("내일 저녁 8시에 여의도에서 같이 피자 드실 분 구합니다!");
        board1.setType(BoardType.Meeting);

        boardService.write(member, board);

        Board findBoard = em.find(Board.class, board.getId());
        assertEquals(board, findBoard);

        for (Board memberBoard : member.getBoards()) {
            System.out.println("memberBoard = " + memberBoard);
        }

        System.out.println("size = " + member.getBoards().size());
        System.out.println("size = " + member.getComments().size());
    }

    @Test
    @Rollback(false)
    public void 게시물삭제() {
        Member member = new Member();
        member.setName("김계란");
        member.addRegionToMember(RegionName.Seoul);
        em.persist(member);

        Board board = Board.createBoard(member, FoodType.TTEOKBOKKI, 0);
        board.setTitle("QWER 멤버 모집");
        board.setContent("미소녀 걸그룹 락밴드 QWER 멤버 모집합니다.");
        boardService.write(member, board);

        assertTrue(member.getBoards().size() == 1);

        boardService.remove(board);

//        assertTrue(member.getBoards().size() == 0);

//        Board board2 = em.find(Board.class, board.getId());
//        System.out.println("board2 = " + board2);

        for (Board memberBoard : member.getBoards()) {
            System.out.println("memberBoard = " + memberBoard);
        }
    }
}