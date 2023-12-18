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
        Member.addRegionToMember(member, RegionName.JeonBuk);
        em.persist(member);

        Board board = Board.createBoard(member, FoodType.CHICKEN, 0);
        board.setTitle("치킨 먹을사람 구해요~!");
        board.setContent("내일 저녁 8시에 여의도에서 같이 치킨 뜯으실 분 구합니다!");
        board.setType(BoardType.MakeFriends);
        em.persist(board);

        Board board1 = Board.createBoard(member, FoodType.PIZZA, 0);
        board1.setTitle("피자 먹을사람 구해요~!");
        board1.setContent("내일 저녁 8시에 여의도에서 같이 피자 드실 분 구합니다!");
        board1.setType(BoardType.Meeting);
        em.persist(board1);

        System.out.println("board = " + board);

        boardService.write(board);

        Board findBoard = em.find(Board.class, board.getId());
        assertEquals(board, findBoard);
        
    }
}