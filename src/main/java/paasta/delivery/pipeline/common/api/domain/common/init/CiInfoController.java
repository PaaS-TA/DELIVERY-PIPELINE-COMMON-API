package paasta.delivery.pipeline.common.api.domain.common.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import paasta.delivery.pipeline.common.api.domain.common.serviceInstance.CiInfo;
import paasta.delivery.pipeline.common.api.domain.common.serviceInstance.CiInfoService;

import java.util.List;
import java.util.Map;

/**
 * Created by hrjin on 2017-05-29.
 */
@RestController
@RequestMapping("/serviceinit")
public class CiInfoController {

    private final CiInfoService ciInfoService;
    private Logger logger = LoggerFactory.getLogger(CiInfoController.class);

    @Autowired
    public CiInfoController(CiInfoService ciInfoService) {
        this.ciInfoService = ciInfoService;
    }

    /*
    * 서비스 생성에 필요한 데이터 초기화
    *
    * */
    @RequestMapping(value = "/ciinfo", method = RequestMethod.POST)
    public Map initCiinfo(@RequestBody List<CiInfo> ciInfos) {
        return ciInfoService.initCiinfo(ciInfos);
    }


}
