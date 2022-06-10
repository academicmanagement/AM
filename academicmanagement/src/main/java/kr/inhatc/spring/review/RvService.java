package kr.inhatc.spring.review;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RvService {
	@Autowired
	private RvRepository rvRepository;
	
	public List<RvVo> findById(String id) {
		List<RvVo> rv = rvRepository.findById(id);
		return rv;
	}
	public List<RvVo> findByIdAndCrname(String id, String crname) {
		List<RvVo> rv = rvRepository.findByIdAndCrname(id, crname);
		return rv;
	}
	public List<RvVo> findByCrnameAndCrprofessor(String crname, String crprofessor) {
		List<RvVo> rv = rvRepository.findByCrnameAndCrprofessor(crname, crprofessor);
		return rv;
	}
	public RvVo save(RvVo review) {
		rvRepository.save(review);
		return review;
	}
	public void deleteByIdAndCrcode(String id, String crcode) {
		rvRepository.deleteByIdAndCrcode(id, crcode);
	}
	//회원 정보 수정
	public void updateById(Long rvNo, RvVo review) {
		Optional<RvVo> e = rvRepository.findById(rvNo);

		if (e.isPresent()) {
			e.get().setRating(review.getRating());
			e.get().setCrreview(review.getCrreview());
			rvRepository.save(review);
		}else {
			throw new NullPointerException();
		}
	}
}
