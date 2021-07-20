package io.github.mygoodsupporter.dao;

import io.github.mygoodsupporter.domain.Member;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberDAO {

	int insertMember(Member member);

	Member getMemberById(String m_id);
}
