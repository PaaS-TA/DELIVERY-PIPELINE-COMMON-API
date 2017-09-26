package paasta.delivery.pipeline.common.api.domain.common.authority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mingu on 2017-05-31.
 */

@Repository
public interface GrantedAuthorityRepository extends JpaRepository<GrantedAuthority, String> {
    //    GrantedAuthority findByInstanceUseIdAndAuthorityId(long instanceUseId, String authorityId);
    GrantedAuthority findTopByInstanceUseIdOrAuthority(Long instanceUseId, Authority authority);

    List<GrantedAuthority> findByInstanceUseId(Long instanceUseId);

    //GrantedAuthority findOneByInstanceUseId(Long instanceUseId);
}
