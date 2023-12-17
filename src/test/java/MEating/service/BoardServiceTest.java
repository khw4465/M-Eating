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
        em.persist(member);

        Food food = new Food();
        FoodType chicken = FoodType.CHICKEN;
        food.setName(chicken);
        em.persist(food);



        Board board = new Board();
        board.setMember(member);
        board.setFood(food);
        board.setTitle("치킨 먹을사람 구해요~!");
        board.setContent("내일 저녁 8시에 여의도에서 같이 치킨 뜯으실 분 구합니다!");
        board.setType(BoardType.MakeFriends);
        em.persist(board);

        System.out.println("board = " + board);

        boardService.write(board);
        em.flush();
    }
}