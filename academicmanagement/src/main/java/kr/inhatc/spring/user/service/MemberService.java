package kr.inhatc.spring.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.inhatc.spring.user.repository.MemberRepository;
import kr.inhatc.spring.user.utils.ScriptUtils;
import lombok.RequiredArgsConstructor;
import kr.inhatc.spring.user.dto.MemberDTO;
import kr.inhatc.spring.user.entity.MemberVo;

@Service
//@Transactional
@RequiredArgsConstructor
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	ScriptUtils scriptUtils;

	//모든 정보 검색
	public List<MemberVo> findAll() {
		List<MemberVo> members = new ArrayList<>();
		memberRepository.findAll().forEach(e -> members.add(e));
		return members;
	}

	//회원 mbrNo 찾기
	public Optional<MemberVo> findById(Long mbrNo) {
		Optional<MemberVo> member = memberRepository.findById(mbrNo);
		return member;
	}
	
	//회원 id 찾기
	public boolean findById(MemberVo member) {
		try {
			MemberVo findMember = memberRepository.findById(member.getId());
			if(findMember.getId() != null) return true;
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	//회원 삭제
	public void deleteById(Long mbrNo) {
		memberRepository.deleteById(mbrNo);
	}
	
	//선택 회원 삭제
	public void deleteAll(Long[] deleteId) {
		memberRepository.deleteMember(deleteId);
	}

	//회원 가입
	public MemberVo save(MemberVo member) {
		memberRepository.save(member);
		return member;
	}
	
	//로그인 기능
	public boolean login(MemberVo member) {	
		try {
			MemberVo findMember = memberRepository.findByIdAndPw(member.getId(), member.getPw());
			if(findMember.getId() != null && findMember.getPw() !=null) return true;
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	//페이징 처리
	public Page<MemberVo> list(int page){
		//RageRequest.of(우리가 보는 페이지, n개씩 페이징 처리, 내림차순 정렬)
		return memberRepository.findAll(PageRequest.of(page, 3, Sort.by(Sort.Direction.DESC, "mbrNo")));
	}
	
	//검색 기능
	@Transactional
	public Page<MemberVo> searchList(String keyword, int page) {
		return memberRepository.findByIdContaining(keyword, PageRequest.of(page, 3, Sort.by(Sort.Direction.DESC, "mbrNo")));
    }
	
	//회원 정보 수정
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
