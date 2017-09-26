package paasta.delivery.pipeline.common.api.domain.common.authority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mingu on 2017-05-31.
 */

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
    Authority findByDisplayName(String displayName);


    Authority findByAuthCode(String authCode);

    List<Authority> findByAuthType(String authType);

    Authority findByCode(String code);
}
