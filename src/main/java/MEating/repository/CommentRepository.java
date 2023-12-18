package MEating.repository;

import MEating.domain.Board;
import MEating.domain.Comment;
import MEating.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    /** 댓글 생성 **/
    public void save(Comment comment) {
        em.persist(comment);
    }

    /** 댓글 단건 조회 **/
    public Comment findOne(Long id){
        return em.find(Comment.class, id);
    }

    /** 게시물별 댓글 전체 조회 **/
    public List<Comment> findAll(Board board) {
        return em.createQuery("select c from Comment c where c.board = :board order by c.regDtm", Comment.class)
                .setParameter("board", board)
                .getResultList();
    }

    /** 회원의 댓글 조회 **/
    public List<Comment> findByMember(Member member) {
        return em.createQuery("select c from Comment c where c.member = :member order by c.regDtm", Comment.class)
                .setParameter("member", member)
                .getResultList();
    }

}
