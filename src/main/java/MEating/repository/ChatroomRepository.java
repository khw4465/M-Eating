package MEating.repository;

import MEating.domain.Chatroom;
import MEating.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatroomRepository {

    private final EntityManager em;

    /** 채팅방 생성 */
    public void save(Chatroom chatroom) {
        em.persist(chatroom);
    }

    /** 채팅방 삭제 */
    public void delete(Chatroom chatroom) {
        em.remove(chatroom);
    }

    /** 채팅방 단건 조회 */
    public Chatroom findOne(Long id) {
        return em.find(Chatroom.class, id);
    }

    /** 회원의 채팅방 전체 조회 */
    public List<Chatroom> findAll(Member member) {
        return em.createQuery("select c from Chatroom c where c.memberChatroomList = :memberChatroomList", Chatroom.class)
                .setParameter("memberChatroomList", member.getMemberChatroomList())
                .getResultList();
    }
}
