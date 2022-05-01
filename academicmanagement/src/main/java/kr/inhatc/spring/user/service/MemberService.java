package kr.inhatc.spring.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.inhatc.spring.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import kr.inhatc.spring.user.dto.MemberDTO;
import kr.inhatc.spring.user.entity.MemberVo;

@Service
//@Transactional
//@RequiredArgsConstructor
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;

	public List<MemberVo> findAll() {
		List<MemberVo> members = new ArrayList<>();
		memberRepository.findAll().forEach(e -> members.add(e));
		return members;
	}

	public Optional<MemberVo> findById(Long mbrNo) {
		Optional<MemberVo> member = memberRepository.findById(mbrNo);
		return member;
	}
	
	public void deleteById(Long mbrNo) {
		memberRepository.deleteById(mbrNo);
	}

	public MemberVo save(MemberVo member) {
		memberRepository.save(member);
		return member;
	}
	//로그인 기능
	public boolean login(MemberVo member) {
		List<MemberVo> findMember = memberRepository.findById(member.getId());
		if(findMember == null) {
			return false;
		}
		else {
			findMember = memberRepository.findByPw(member.getPw());
			if(findMember == null) {
				return false;
			}
			else return true;
		}
	}

	public void updateById(Long mbrNo, MemberVo member) {
		Optional<MemberVo> e = memberRepository.findById(mbrNo);

		if (e.isPresent()) {
			e.get().setMbrNo(member.getMbrNo());
			e.get().setId(member.getId());
			e.get().setPw(member.getPw());
			e.get().setDept(member.getDept());
			e.get().setBan(member.getBan());
			memberRepository.save(member);
		}
	}
}
