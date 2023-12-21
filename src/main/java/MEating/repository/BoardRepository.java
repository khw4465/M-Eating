package MEating.repository;

import MEating.domain.*;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    /** 게시물 생성 **/
    public void save(Board board) {
        em.persist(board);
    }

    /** 게시물 삭제 **/
    public void delete(Board board) {
        em.remove(board);
    }

    /** 게시물 단건 조회 **/
    public Board findOne(Long id) {
        return em.find(Board.class, id);
    }

    /** 게시물 전체(지역,음식) 조회 **/
    public List<Board> findAll(Region region, FoodType food) {
        return em.createQuery("select b from Board b where b.region.name = :regionName and b.food.name = :food order by b.regDtm desc", Board.class)
                .setParameter("regionName", region.getName())
                .setParameter("food", food)
                .getResultList();
    }

    /** 회원의 게시물 조회 **/
    public List<Board> findByMember(Member member) {
        return em.createQuery("select b from Board b where b.member = :member order by b.regDtm", Board.class)
                .setParameter("member", member)
                .getResultList();
    }

    /** 제목으로 게시물 조회 **/
    public List<Board> findByTitle(String title) {
        return em.createQuery("select b from Board b where b.title like concat('%', :title, '%') order by b.regDtm", Board.class)
                .setParameter("title", title)
                .getResultList();
    }

    /** em.clear() **/
    public void clear() {
        em.clear();
    }
}
