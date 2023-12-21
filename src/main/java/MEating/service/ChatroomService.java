package MEating.service;

import MEating.domain.Chatroom;
import MEating.domain.Member;
import MEating.repository.ChatroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatroomService {

    public final ChatroomRepository chatroomRepository;

    /** 채팅방 생성 */
    @Transactional
    public Long create(Chatroom chatroom) {
        chatroomRepository.save(chatroom);

        return chatroom.getId();
    }

    /** 채팅방 삭제 */
    @Transactional
    public void remove(Chatroom chatroom) {
        chatroomRepository.delete(chatroom);
    }

    /** 채팅방 단건 조회 */
    public Chatroom findOne(Long chatroomId) {
        return chatroomRepository.findOne(chatroomId);
    }

    /** 회원의 채팅방 전체 조회 */
    public List<Chatroom> findAll(Member member) {
        return chatroomRepository.findAll(member);
    }
}
