package paasta.delivery.pipeline.common.api.domain.common.serviceInstance;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by hrjin on 2017-05-29.
 */
@Service
public class CiInfoService {

    private final Logger logger = getLogger(getClass());
    private static final String DEDICATED = "Dedicated";
    private final CiInfoRepository ciInfoRepository;
    private static final String NOT_USED_SERVER = "N";


    @Autowired
    public CiInfoService(CiInfoRepository ciInfoRepository) {
        this.ciInfoRepository = ciInfoRepository;
    }


    public CiInfo getNotUsedCfinfo(String type) {

        List<CiInfo> ciInfos;
        if (type.equals(DEDICATED)) {
            ciInfos = ciInfoRepository.findByStatusAndTypeOrderByUsedcount(NOT_USED_SERVER, type);
        } else {
            ciInfos = ciInfoRepository.findByTypeOrderByUsedcount(type);
        }


        if (ciInfos.size() > 0) {
            return ciInfos.get(0);
        } else {
            return null;
        }
    }

    public boolean update(CiInfo ciInfo) {
        ciInfoRepository.save(ciInfo);
        return true;
    }

    public boolean recovery(String serverUrl) {
        try {
            CiInfo ciInfo = ciInfoRepository.findByServerUrl(serverUrl);
            if (ciInfo != null) {
                ciInfo.setStatus(NOT_USED_SERVER);
                ciInfoRepository.save(ciInfo);
            }
        } catch (Exception e) {
            logger.error("Exception :: {}", e);

        }
        return true;
    }

}
