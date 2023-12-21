package MEating.repository;

import MEating.domain.Chatroom;
import MEating.domain.Chatting;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChattingRepository {

    private final EntityManager em;

    /** 채팅 생성 */
    public void save(Chatting chatting) {
        em.persist(chatting);
    }

    /** 채팅 단건 조회 */
    public Chatting findOne(Long id) {
        return em.find(Chatting.class, id);
    }

    /** 채팅 전체 조회 */
    public List<Chatting> findAll(Chatroom chatroom) {
        return em.createQuery("select c from Chatting c where c.chatroom = :chatroom", Chatting.class)
                .setParameter("chatroom", chatroom)
                .getResultList();
    }

    /** 채팅 내역 조회 */
    public List<Chatting> findByContent(Chatroom chatroom, String content) {
        return em.createQuery("select c from Chatting c where c.chatroom = :chatroom and c.content = concat('%', :content, '%')", Chatting.class)
                .setParameter("chatroom", chatroom)
                .setParameter("content", content)
                .getResultList();
    }
}
