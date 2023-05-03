package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository {
  //회원 저장
  Member save(Member member);

  //저장소에서 id로 찾기
  Optional<Member> findById(Long id);

  //저장소에서 name으로 찾기
  Optional<Member> findByName(String name);

  //지금까지 저장된 모든 회원을 list으로 반환해줌
   List<Member> findAll();
}
