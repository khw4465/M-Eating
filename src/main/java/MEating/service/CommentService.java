package MEating.service;

import MEating.domain.Board;
import MEating.domain.Comment;
import MEating.domain.Member;
import MEating.exception.NoBoardException;
import MEating.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    /** 댓글 생성 **/
    @Transactional
    public Long write(Member member, Board board, String content) {
        if (board != null) {
            Comment comment = Comment.createComment(member, board);
            comment.setContent(content);
            comment.setRegDtm(LocalDateTime.now());
            commentRepository.save(comment);

            member.getComments().add(comment);
            board.getComments().add(comment);

            return comment.getId();
        } else {
            throw new NoBoardException("게시물이 존재하지 않습니다");
        }
    }

    /** 댓글 삭제 **/
    @Transactional
    public void remove(Comment comment) {
        commentRepository.delete(comment);

        //== 댓글 작성자의 댓글 리스트에서 해당 댓글 삭제 ==//
        comment.getMember().getComments().removeIf(c -> c.equals(comment));

        //== 해당 댓글의 게시물에서 댓글 삭제 ==//
        comment.getBoard().getComments().removeIf(c -> c.equals(comment));

        commentRepository.clear();
    }

    /** 댓글 단건 조회 **/
    public Comment findOne(Long commentId) {
        return commentRepository.findOne(commentId);
    }

    /** 게시물별 댓글 전체 조회 **/
    public List<Comment> findAll(Board board) {
        return commentRepository.findAll(board);
    }
}
