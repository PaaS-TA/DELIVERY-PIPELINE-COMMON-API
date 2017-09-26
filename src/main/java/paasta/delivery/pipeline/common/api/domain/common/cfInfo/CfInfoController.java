package paasta.delivery.pipeline.common.api.domain.common.cfInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

/**
 * paastaDeliveryPipelineApi
 * paasta.delivery.pipeline.common.api.cfInfo
 *
 * @author REX
 * @version 1.0
 * @since 7 /25/2017
 */
@RestController
public class CfInfoController {

    private static final String REQ_URL = "/cf-info";
    private static final int PAGE_SIZE = 3;
    private final CfInfoService cfInfoService;


    /**
     * Instantiates a new Cf info controller.
     *
     * @param cfInfoService the cf info service
     */
    @Autowired
    public CfInfoController(CfInfoService cfInfoService) {this.cfInfoService = cfInfoService;}


    /**
     * Gets cf info list pageable.
     *
     * @param pageable           the pageable
     * @param serviceInstancesId the service instances id
     * @param cfName             the cf name
     * @return the cf info list pageable
     */
    @GetMapping(value = "/serviceInstances/{serviceInstancesId:.+}" + REQ_URL)
    public CfInfoList getCfInfoListPageable(@PageableDefault(sort = "created", direction = Sort.Direction.DESC, size = PAGE_SIZE) Pageable pageable,
                                            @PathVariable(value = "serviceInstancesId", required = false) String serviceInstancesId,
                                            @RequestParam(value = "cfName", required = false) String cfName) {
        return cfInfoService.getCfInfoListPageable(pageable, serviceInstancesId, cfName);
    }


    /**
     * Gets cf info by id.
     *
     * @param id the id
     * @return the cf info by id
     */
    @GetMapping(value = REQ_URL + "/{id:.+}")
    public CfInfo getCfInfoById(@PathVariable("id") int id) {
        return cfInfoService.getCfInfoById(id);
    }


    /**
     * Gets cf info count by service instances id and cf name.
     *
     * @param serviceInstancesId the service instances id
     * @param cfName             the cf name
     * @return the cf info count by service instances id and cf name
     */
    @GetMapping(value = "/serviceInstances/{serviceInstancesId:.+}" + REQ_URL + "/cf-name/{cfName:.+}")
    public int getCfInfoCountByServiceInstancesIdAndCfName(@PathVariable("serviceInstancesId") String serviceInstancesId, @PathVariable("cfName") String cfName) {
        return cfInfoService.getCfInfoCountByServiceInstancesIdAndCfName(serviceInstancesId, cfName);
    }


    /**
     * Create cf info cf info.
     *
     * @param cfInfo the cf info
     * @return the cf info
     */
    @PostMapping(value = REQ_URL)
    public CfInfo createCfInfo(@RequestBody CfInfo cfInfo) {
        return cfInfoService.createCfInfo(cfInfo);
    }


    /**
     * Update cf info cf info.
     *
     * @param cfInfo the cf info
     * @return the cf info
     */
    @PutMapping(value = REQ_URL)
    public CfInfo updateCfInfo(@RequestBody CfInfo cfInfo) {
        return cfInfoService.updateCfInfo(cfInfo);
    }


    /**
     * Delete cf ino string.
     *
     * @param id the id
     * @return the string
     */
    @DeleteMapping(value = REQ_URL + "/{id:.+}")
    public String deleteCfIno(@PathVariable("id") int id) {
        return cfInfoService.deleteCfInoById(id);
    }

}
