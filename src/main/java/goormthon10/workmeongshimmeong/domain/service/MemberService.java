package goormthon10.workmeongshimmeong.domain.service;

import goormthon10.workmeongshimmeong.domain.entity.Member;
import goormthon10.workmeongshimmeong.domain.enums.MemberType;
import goormthon10.workmeongshimmeong.domain.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member saveMember(String email, MemberType type) {
        return memberRepository.save(Member.builder()
                .email(email)
                .type(type)
                .build());
    }

    @Transactional
    public Member findMember(String email, String description, MemberType type) {
        Optional<Member> maybeMember = memberRepository.findByEmail(email);
        if (maybeMember.isPresent()) {
            return maybeMember.get();
        } else {
            return memberRepository.save(Member.builder()
                    .email(email)
                    .description(description)
                    .type(type)
                    .build());

        }
    }

    @Transactional
    public Member findGuest(String email, String name, String phone) {
        Optional<Member> maybeGuest = memberRepository.findByEmailAndPhoneNumber(email, phone);
        return maybeGuest.orElseGet(() -> memberRepository.save(Member.builder()
                .email(email)
                .name(name)
                .phoneNumber(phone)
                .type(MemberType.GUEST)
                .build()));
    }
}
