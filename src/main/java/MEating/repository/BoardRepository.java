package MEating.repository;

import MEating.domain.Board;
import MEating.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    /** 게시물 생성 **/
    public void save(Board board) {
        em.persist(board);
    }

    /** 게시물 단건 조회 **/
    public Board findOne(Long id) {
        return em.find(Board.class, id);
    }

    /** 게시물 전체 조회 **/
    public List<Board> findAll() {
        return em.createQuery("select b from Board b", Board.class)
                .getResultList();
    }

    /** 회원의 게시물 조회 **/
    public List<Board> findByMember(Member member) {
        return em.createQuery("select b from Board b where b.member = :member", Board.class)
                .setParameter("member", member)
                .getResultList();
    }

    /** 제목으로 게시물 조회 **/
    public List<Board> findByTitle(String title) {
        return em.createQuery("select b from Board b where b.title like concat('%', :title, '%')", Board.class)
                .setParameter("title", title)
                .getResultList();
    }

}
