package paasta.delivery.pipeline.common.api.domain.common.cf.url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CfUrlController {

    private static final String REQ_URL = "/cf-url";
    private static final int PAGE_SIZE = 3;
    private final CfUrlService cfUrlService;


    @Autowired
    public CfUrlController(CfUrlService cfUrlService) {this.cfUrlService = cfUrlService;}


    @GetMapping(value = "/serviceInstances/{serviceInstancesId:.+}" + REQ_URL)
    public List<CfUrl> getCfUrlListPageable(@PageableDefault(sort = "created", direction = Sort.Direction.DESC, size = PAGE_SIZE) Pageable pageable,
                                            @PathVariable(value = "serviceInstancesId", required = false) String serviceInstancesId) {
        return cfUrlService.getCfUrlListPageable(pageable, serviceInstancesId);
    }


    @GetMapping(value = REQ_URL + "/{id:.+}")
    public CfUrl getCfUrlById(@PathVariable("id") int id) {
        return cfUrlService.getCfUrlById(id);
    }


    @PostMapping(value = REQ_URL)
    public CfUrl createCfUrl(@RequestBody CfUrl cfUrl) {
        return cfUrlService.createCfUrl(cfUrl);
    }


    @PutMapping(value = REQ_URL)
    public CfUrl updateCfUrl(@RequestBody CfUrl cfUrl) {
        return cfUrlService.updateCfUrl(cfUrl);
    }


    @DeleteMapping(value = REQ_URL + "/{id:.+}")
    public String deleteCfIno(@PathVariable("id") int id) {
        return cfUrlService.deleteCfInoById(id);
    }

}
