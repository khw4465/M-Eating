package MEating.service;

import MEating.domain.Board;
import MEating.domain.Member;
import MEating.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    /** 게시물 작성 */
    @Transactional
    public void write(Board board) {
        boardRepository.save(board);
    }

    /** 게시물 1개 조회 */
    public Board findOne(Long boardId) {
        return boardRepository.findOne(boardId);
    }

    /** 게시물 전체 조회 */
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    /** 회원의 게시물 조회 */
    public List<Board> findByMember(Member member) {
        return boardRepository.findByMember(member);
    }

    /** 제목으로 게시물 조회 */
    public List<Board> findByTitle(String title) {
        return boardRepository.findByTitle(title);
    }
}
