package MEating.service;

import MEating.domain.*;
import MEating.repository.BoardRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    /** 게시물 작성 */
    @Transactional
    public Long write(Member member, Board board) {
        board.setRegDtm(LocalDateTime.now());
        boardRepository.save(board);

        member.getBoards().add(board);
        board.getFood().getBoards().add(board);

        return board.getId();
    }

    /** 게시물 삭제 **/
    @Transactional
    public void remove(Board board) {
        boardRepository.delete(board);

        //== 작성자의 게시물 목록에서 삭제 ==//
        board.getMember().getBoards().removeIf(b -> b.equals(board));

        //== 댓글 작성자의 댓글 목록에서 삭제 ==//
        board.getComments().removeIf(c -> c.getMember().getComments().equals(c));

        //== 게시물의 댓글 삭제 ==//
        board.getComments().clear();

        boardRepository.clear();
    }

    /** 게시물 1개 조회 */
    public Board findOne(Long boardId) {
        return boardRepository.findOne(boardId);
    }

    /** 게시물 전체(지역,음식) 조회 */
    public List<Board> findAll(Member member, int regionNum, FoodType food) {
        Region region = member.getMemberRegionList().get(regionNum).getRegion();
        return boardRepository.findAll(region, food);
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
