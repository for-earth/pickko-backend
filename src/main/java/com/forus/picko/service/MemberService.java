package com.forus.picko.service;

import com.forus.picko.entity.Company;
import com.forus.picko.entity.Member;
import com.forus.picko.entity.JobPosition;
import com.forus.picko.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Member join(Member member) {
        Optional<Member> m = memberRepository
                .findByKakaoId(member.getKakaoId());

        System.out.println("Member: " + m);

        return m.orElseGet(() -> memberRepository.save(member));
    }

    // 유저 수정
    public Member update(Long id, Optional<String> nickname, Optional<String> email, Optional<Company> company, Optional<String> profileImage, Optional<JobPosition> jobPosition) throws HttpClientErrorException {
        Optional<Member> member = memberRepository.findById(id);

        return member.map(m -> {
            nickname.ifPresent(m::setNickname);
            email.ifPresent(m::setEmail);
            company.ifPresent(m::setCompany);
            profileImage.ifPresent(m::setProfileImage);
            jobPosition.ifPresent(m::setJobPosition);

            return m;
        }).orElseThrow(() -> new HttpClientErrorException(HttpStatusCode.valueOf(404), "Not Found"));
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {

        return memberRepository.findAll();
    }

    public Optional<Member> findMemberByKakaoId(Long kakaoId) {
        return memberRepository.findByKakaoId(kakaoId);
    }

    /**
     * 회원 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return  memberRepository.findById(memberId );
    }
}
