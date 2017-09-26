package paasta.delivery.pipeline.common.api.domain.common.authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mingu on 2017-05-31.
 */

@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;


    @Autowired
    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public Authority createAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

    public List<Authority> getAll() {
        return authorityRepository.findAll();
    }

    public Authority getAuthority(String id) {
        return authorityRepository.findOne(id);
    }

    public Authority getAuthorityByName(String displayName) {
        return authorityRepository.findByDisplayName(displayName);
    }

    public Authority updateAuthority(String id, Authority updateAuthority) {
        updateAuthority.setId(id);
        return authorityRepository.save(updateAuthority);
    }

    public Authority getAuthorityByCode(String code) {
        return authorityRepository.findByCode(code);
    }

    public void deleteAuthority(String id) {
        authorityRepository.delete(id);
    }

    public List<Authority> getAuthorityByAuthType(String authType) {
        return authorityRepository.findByAuthType(authType);
    }
}
