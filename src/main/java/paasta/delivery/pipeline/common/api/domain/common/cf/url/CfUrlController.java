package paasta.delivery.pipeline.common.api.domain.common.cf.url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * deliveryPipelineApi
 * paasta.delivery.pipeline.common.api.domain.common.cf.url
 *
 * @author REX
 * @version 1.0
 * @since 11 /6/2017
 */
@RestController
public class CfUrlController {

    private static final String REQ_URL = "/cf-urls";
    private final CfUrlService cfUrlService;


    /**
     * Instantiates a new Cf url controller.
     *
     * @param cfUrlService the cf url service
     */
    @Autowired
    public CfUrlController(CfUrlService cfUrlService) {this.cfUrlService = cfUrlService;}


    /**
     * Gets cf url list.
     *
     * @param serviceInstancesId the service instances id
     * @return the cf url list
     */
    @GetMapping(value = "/serviceInstances/{serviceInstancesId:.+}" + REQ_URL)
    public List<CfUrl> getCfUrlList(@PathVariable(value = "serviceInstancesId") String serviceInstancesId) {
        return cfUrlService.getCfUrlList(serviceInstancesId);
    }


    /**
     * Gets cf url by id.
     *
     * @param id the id
     * @return the cf url by id
     */
    @GetMapping(value = REQ_URL + "/{id:.+}")
    public CfUrl getCfUrlById(@PathVariable("id") int id) {
        return cfUrlService.getCfUrlById(id);
    }


    /**
     * Create cf url cf url.
     *
     * @param cfUrl the cf url
     * @return the cf url
     */
    @PostMapping(value = REQ_URL)
    public CfUrl createCfUrl(@RequestBody CfUrl cfUrl) {
        return cfUrlService.createCfUrl(cfUrl);
    }


    /**
     * Update cf url cf url.
     *
     * @param cfUrl the cf url
     * @return the cf url
     */
    @PutMapping(value = REQ_URL)
    public CfUrl updateCfUrl(@RequestBody CfUrl cfUrl) {
        return cfUrlService.updateCfUrl(cfUrl);
    }


    /**
     * Delete cf ino string.
     *
     * @param id the id
     * @return the string
     */
    @DeleteMapping(value = REQ_URL + "/{id:.+}")
    public String deleteCfIno(@PathVariable("id") int id) {
        return cfUrlService.deleteCfInoById(id);
    }

}
