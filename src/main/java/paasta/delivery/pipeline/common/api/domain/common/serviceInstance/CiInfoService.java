package paasta.delivery.pipeline.common.api.domain.common.serviceInstance;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import paasta.delivery.pipeline.common.api.common.CommonService;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by hrjin on 2017-05-29.
 */
@Service
public class CiInfoService {

    private final Logger LOGGER = getLogger(getClass());
    private final CommonService commonService;
    private final CiInfoRepository ciInfoRepository;

    private final String NOT_USED_SERVER = "N";

    @Autowired
    public CiInfoService(CommonService commonService, CiInfoRepository ciInfoRepository) {
        this.commonService = commonService;
        this.ciInfoRepository = ciInfoRepository;
    }


    public CiInfo getNotUsedCfinfo(String type) {

        List<CiInfo> ciInfos;
        if (type.equals("Shared")) {
            LOGGER.info("################### SHARED");
            ciInfos = ciInfoRepository.findByTypeOrderByUsedcount(type);
        } else {
            LOGGER.info("################### DEDICATED");
            ciInfos = ciInfoRepository.findByStatusAndTypeOrderByUsedcount(NOT_USED_SERVER, type);
        }


        if (ciInfos.size() > 0) {
            for (CiInfo ci : ciInfos) {
                LOGGER.info(ci.getServerUrl());
            }
            return ciInfos.get(0);
        } else {
            return null;
        }
    }

    public boolean update(CiInfo ciInfo) {
        ciInfoRepository.save(ciInfo);
        return true;
    }

    public boolean recovery(String server_url) {
        try {
            CiInfo ciInfo = ciInfoRepository.findByServerUrl(server_url);
            if (ciInfo != null) {
                ciInfo.setStatus(NOT_USED_SERVER);
                ciInfoRepository.save(ciInfo);
            }
        } catch (Exception e) {

        }
        return true;
    }

}
