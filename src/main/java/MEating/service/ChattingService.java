package MEating.service;

import MEating.domain.Chatroom;
import MEating.domain.Chatting;
import MEating.domain.Member;
import MEating.repository.ChattingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChattingService {

    private final ChattingRepository chattingRepository;

    /** 채팅 쓰기 */
    @Transactional
    public Long write(Member member, Chatroom chatroom, String content) {
        Chatting chatting = member.chatting(chatroom, content);
        chattingRepository.save(chatting);
        return chatting.getId();
    }

    /** 채팅 단건 조회 */
    public Chatting findOne(Long chattingId) {
        return chattingRepository.findOne(chattingId);
    }

    /** 채팅 전체 조회 */
    public List<Chatting> findAll(Chatroom chatroom) {
        return chattingRepository.findAll(chatroom);
    }

    /** 채팅 내역 조회 */
    public List<Chatting> findByContent(Chatroom chatroom, String content) {
        return chattingRepository.findByContent(chatroom, content);
    }
}
