package MEating.repository;

import MEating.domain.Member;
import MEating.domain.Promise;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PromiseRepository {

    private final EntityManager em;

    /** 모임 생성 */
    public void save(Promise promise) {
        em.persist(promise);
    }

    /** 모임 삭제 */
    public void delete(Promise promise) {
        em.remove(promise);
    }

    /** 모임 단건 조회 */
    public Promise findOne(Long id) {
        return em.find(Promise.class, id);
    }

    /** 모임 전체 조회 */
    public List<Promise> findAll() {
        return em.createQuery("select p from Promise p", Promise.class)
                .getResultList();
    }

    /** 회원의 모임 조회 */
    public List<Promise> findByMember(Member member) {
        return em.createQuery("select mp.promise from MemberPromise mp join fetch mp.promise where mp.member = :member", Promise.class)
                .setParameter("member", member)
                .getResultList();
    }
}
