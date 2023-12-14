package MEating.service;

import MEating.domain.Member;
import MEating.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /** 중복체크 후 회원가입 **/
    public Long join(Member member) {
        validateDuplicateLoginId(member);
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    /** 아이디 중복체크 **/
    private void validateDuplicateLoginId(Member member) {
        List<Member> findMembers = memberRepository.findByLoginId(member.getLoginId());
        if(findMembers.size() != 0) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }

    /** 회원 중복체크 **/
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByInfo(member.getName(), member.getBirthday(), member.getPhoneNumber());
        if(findMembers.size() != 0) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
