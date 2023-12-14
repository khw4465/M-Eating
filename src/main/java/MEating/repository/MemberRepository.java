package MEating.repository;

import MEating.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    /** 회원가입 **/
    public void save(Member member) {
        em.persist(member);
    }
    /** 회원 단건조회 **/
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }
    /** 회원 전체조회 **/
    public List<Member> findAAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    /** 이름, 생년월일, 전화번호로 회원조회 **/
    public List<Member> findByInfo(String name, LocalDateTime birth, String phoneNum) {
        return em.createQuery("select m from Member m where m.name = :name and m.birthday = :birth and m.phoneNumber = :phoneNum", Member.class)
                .setParameter("name", name)
                .setParameter("birth", birth)
                .setParameter("phoneNum", phoneNum)
                .getResultList();
    }
    /** 로그인아이디로 회원조회 **/
    public Member findByLoginId(String loginId) {
        return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getSingleResult();
    }
}
