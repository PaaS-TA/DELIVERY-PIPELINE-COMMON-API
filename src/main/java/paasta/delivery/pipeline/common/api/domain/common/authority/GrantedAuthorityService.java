package paasta.delivery.pipeline.common.api.domain.common.authority;

import org.springframework.stereotype.Service;
import paasta.delivery.pipeline.common.api.common.Constants;

import java.util.List;

/**
 * Created by Mingu on 2017-05-31.
 */
@Service
public class GrantedAuthorityService {


    private final GrantedAuthorityRepository grantedAuthorityRepository;

    public GrantedAuthorityService(GrantedAuthorityRepository grantedAuthorityRepository) {
        this.grantedAuthorityRepository = grantedAuthorityRepository;

    }

    public GrantedAuthority createGrantedAuthority(GrantedAuthority grantedAuthority) {
        return grantedAuthorityRepository.save(grantedAuthority);
    }

    public GrantedAuthority getGrantedAuthority(String id) {
        return grantedAuthorityRepository.findOne(id);
    }

    public GrantedAuthority getGrantedAuthorityByInstanceUseIdAndAuthorityId(Long instanceUseId, String authorityId) {
        return grantedAuthorityRepository.findTopByInstanceUseIdOrAuthority(instanceUseId, new Authority(authorityId));
//        return grantedAuthorityRepository.findByInstanceUseId(id);
    }

    public List<GrantedAuthority> findAll() {
        return grantedAuthorityRepository.findAll();
    }


    public String deleteGrantedAuthority(String id) {
        grantedAuthorityRepository.delete(id);
        return Constants.RESULT_STATUS_SUCCESS;
    }


    public List<GrantedAuthority> getGrantedAuthorityList() {
        return grantedAuthorityRepository.findAll();
    }

    /*public void deleteGrantedAuthorityRows(GrantedAuthority grantedAuthority){
        grantedAuthorityRepository.delete(grantedAuthority);
    }*/


    public List<GrantedAuthority> findByInstanceUseId(Long instanceUseId) {
        return grantedAuthorityRepository.findByInstanceUseId(instanceUseId);
    }

    public void deleteGrantedAuthorityRows(List<GrantedAuthority> grantedAuthorities) {
        grantedAuthorityRepository.delete(grantedAuthorities);
    }


}
