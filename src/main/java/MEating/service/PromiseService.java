package MEating.service;

import MEating.domain.Board;
import MEating.domain.Member;
import MEating.domain.Promise;
import MEating.repository.PromiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PromiseService {

    private final PromiseRepository promiseRepository;

    /** 모임 생성 */
    @Transactional
    public Long create(Board board, String name, String dateTime, String place, Member... member) {
        Promise promise = Promise.createPromise(board, name, dateTime, place, member);
        promiseRepository.save(promise);
        return promise.getId();
    }

    /** 모임 삭제 */
    @Transactional
    public void remove(Promise promise) {
        promiseRepository.delete(promise);
    }

    /** 모임 단건 조회 */
    public Promise findOne(Long promiseId) {
        return promiseRepository.findOne(promiseId);
    }

    /** 모임 전체 조회 */
    public List<Promise> findAll() {
        return promiseRepository.findAll();
    }

    /** 회원의 모임 조회 */
    public List<Promise> findByMember(Member member) {
        return promiseRepository.findByMember(member);
    }
}
