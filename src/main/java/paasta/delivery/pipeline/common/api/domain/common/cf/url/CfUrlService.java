package paasta.delivery.pipeline.common.api.domain.common.cf.url;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import paasta.delivery.pipeline.common.api.common.Constants;

import java.util.List;

@Service
public class CfUrlService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CfUrlService.class);
    private final CfUrlRepository cfUrlRepository;

    @Autowired
    public CfUrlService(CfUrlRepository cfUrlRepository) {this.cfUrlRepository = cfUrlRepository;}


    List<CfUrl> getCfUrlListPageable(Pageable pageable, String serviceInstancesId) {
        LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        LOGGER.info("  - PageNumber :: {}", pageable.getPageNumber());
        LOGGER.info("  - PageSize :: {}", pageable.getPageSize());
        LOGGER.info("  - Sort :: {}", pageable.getSort());
        LOGGER.info("  - Offset :: {}", pageable.getOffset());
        LOGGER.info("  - HasPrevious :: {}", pageable.hasPrevious());
        LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        return cfUrlRepository.findAllByServiceInstancesId(pageable, serviceInstancesId).getContent();
    }


    CfUrl getCfUrlById(int id) {
        return cfUrlRepository.findOne((long) id);
    }


    CfUrl createCfUrl(CfUrl cfUrl) {
        return cfUrlRepository.save(cfUrl);
    }


    CfUrl updateCfUrl(CfUrl cfUrl) {
        return cfUrlRepository.save(cfUrl);
    }


    String deleteCfInoById(int id) {
        cfUrlRepository.delete((long) id);
        return Constants.RESULT_STATUS_SUCCESS;
    }

}
