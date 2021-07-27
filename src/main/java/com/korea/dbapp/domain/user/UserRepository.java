package com.korea.dbapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/*
 * Repository는 DAO에 붙어 DB에 있는 데이터를 가져올지 FAO에 붙어 file을 가져올지 정할 수 있다.
 * JpaRepository를 사용하면 DAO를 안만들어도 데이터를 가져올 수 있다.(공통적으로 쓸거 같은 데이터가 만들어져있다.)
 * JpaRepository를 이용하면 annotation을 안붙혀도  UserRepository가 메모리에 올라간다.
 */

// Data를 가져와서 자바 오브젝트로 만드는 것
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{ // User의 데이터를 받아 관리, Integer는 primary 키로 설정된 데이터
	
	@Query(value ="SELECT * FROM user WHERE username = :username", nativeQuery = true)
	User mFindByUsername(String username);
	
	@Query(value = "SELECT id, username, address, email, null password FROM user WHERE username = :username AND password = :password", nativeQuery = true )
	User mLogin(String username, String password);


}
