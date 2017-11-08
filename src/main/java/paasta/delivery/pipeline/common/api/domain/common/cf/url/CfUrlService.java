package paasta.delivery.pipeline.common.api.domain.common.cf.url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paasta.delivery.pipeline.common.api.common.Constants;

import java.util.List;

/**
 * deliveryPipelineApi
 * paasta.delivery.pipeline.common.api.domain.common.cf.url
 *
 * @author REX
 * @version 1.0
 * @since 11 /6/2017
 */
@Service
public class CfUrlService {

    private final CfUrlRepository cfUrlRepository;


    /**
     * Instantiates a new Cf url service.
     *
     * @param cfUrlRepository the cf url repository
     */
    @Autowired
    public CfUrlService(CfUrlRepository cfUrlRepository) {this.cfUrlRepository = cfUrlRepository;}


    /**
     * Gets cf url list.
     *
     * @param serviceInstancesId the service instances id
     * @return the cf url list
     */
    List<CfUrl> getCfUrlList(String serviceInstancesId) {
        return cfUrlRepository.findAllByServiceInstancesIdOrderByCreatedDesc(serviceInstancesId);
    }


    /**
     * Gets cf url by id.
     *
     * @param id the id
     * @return the cf url by id
     */
    CfUrl getCfUrlById(int id) {
        return cfUrlRepository.findOne((long) id);
    }


    /**
     * Create cf url cf url.
     *
     * @param cfUrl the cf url
     * @return the cf url
     */
    CfUrl createCfUrl(CfUrl cfUrl) {
        return cfUrlRepository.save(cfUrl);
    }


    /**
     * Update cf url cf url.
     *
     * @param cfUrl the cf url
     * @return the cf url
     */
    CfUrl updateCfUrl(CfUrl cfUrl) {
        return cfUrlRepository.save(cfUrl);
    }


    /**
     * Delete cf ino by id string.
     *
     * @param id the id
     * @return the string
     */
    String deleteCfInoById(int id) {
        cfUrlRepository.delete((long) id);
        return Constants.RESULT_STATUS_SUCCESS;
    }

}
