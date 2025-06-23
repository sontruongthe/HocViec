package SpringJPA.demo1.Repository;


import SpringJPA.demo1.Empty.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByName(String username);


    Optional<UserInfo> findByEmail(String email);
}
