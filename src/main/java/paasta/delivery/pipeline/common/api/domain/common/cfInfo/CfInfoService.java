package paasta.delivery.pipeline.common.api.domain.common.cfInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import paasta.delivery.pipeline.common.api.common.CommonService;
import paasta.delivery.pipeline.common.api.common.Constants;

/**
 * paastaDeliveryPipelineApi
 * paasta.delivery.pipeline.common.api.cfInfo
 *
 * @author REX
 * @version 1.0
 * @since 7 /25/2017
 */
@Service
public class CfInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CfInfoService.class);
    private final CommonService commonService;
    private final CfInfoRepository cfInfoRepository;


    /**
     * Instantiates a new Cf info service.
     *
     * @param commonService    the common service
     * @param cfInfoRepository the cf info repository
     */
    public CfInfoService(CommonService commonService, CfInfoRepository cfInfoRepository) {
        this.commonService = commonService;
        this.cfInfoRepository = cfInfoRepository;
    }


    /**
     * Gets cf info list pageable.
     *
     * @param pageable           the pageable
     * @param serviceInstancesId the service instances id
     * @param cfName             the cf name
     * @return the cf info list pageable
     */
    CfInfoList getCfInfoListPageable(Pageable pageable, String serviceInstancesId, String cfName) {
        LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        LOGGER.info("  - PageNumber :: {}", pageable.getPageNumber());
        LOGGER.info("  - PageSize :: {}", pageable.getPageSize());
        LOGGER.info("  - Sort :: {}", pageable.getSort());
        LOGGER.info("  - Offset :: {}", pageable.getOffset());
        LOGGER.info("  - HasPrevious :: {}", pageable.hasPrevious());
        LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        CfInfoList resultList;
        Page<CfInfo> cfInfoListPage;

        if (serviceInstancesId == null || "".equals(serviceInstancesId)) {
            cfInfoListPage = cfInfoRepository.findAll(pageable);
        } else {
            if (cfName == null || "".equals(cfName)) {
                cfInfoListPage = cfInfoRepository.findAllByServiceInstancesId(pageable, serviceInstancesId);
            } else {
                cfInfoListPage = cfInfoRepository.findAllByServiceInstancesIdAndCfNameContaining(pageable, serviceInstancesId, cfName);
            }
        }

        resultList = (CfInfoList) commonService.setPageInfo(cfInfoListPage, CfInfoList.class);
        resultList.setCfInfos(cfInfoListPage.getContent());

        return resultList;
    }


    /**
     * Gets cf info by id.
     *
     * @param id the id
     * @return the cf info by id
     */
    CfInfo getCfInfoById(int id) {
        CfInfo resultModel = cfInfoRepository.findOne((long) id);
        resultModel.setCfPassword(commonService.setPasswordByAES256(Constants.AES256Type.DECODE, resultModel.getCfPassword()));
        return resultModel;
    }


    /**
     * Gets cf info count by service instances id and cf name.
     *
     * @param serviceInstancesId the service instances id
     * @param cfName             the cf name
     * @return the cf info count by service instances id and cf name
     */
    int getCfInfoCountByServiceInstancesIdAndCfName(String serviceInstancesId, String cfName) {
        return cfInfoRepository.countByServiceInstancesIdAndCfName(serviceInstancesId, cfName);
    }


    /**
     * Create cf info cf info.
     *
     * @param cfInfo the cf info
     * @return the cf info
     */
    CfInfo createCfInfo(CfInfo cfInfo) {
        cfInfo.setCfPassword(commonService.setPasswordByAES256(Constants.AES256Type.ENCODE, cfInfo.getCfPassword()));
        return cfInfoRepository.save(cfInfo);
    }


    /**
     * Update cf info cf info.
     *
     * @param cfInfo the cf info
     * @return the cf info
     */
    CfInfo updateCfInfo(CfInfo cfInfo) {
        cfInfo.setCfPassword(commonService.setPasswordByAES256(Constants.AES256Type.ENCODE, cfInfo.getCfPassword()));
        return cfInfoRepository.save(cfInfo);
    }


    /**
     * Delete cf ino by id string.
     *
     * @param id the id
     * @return the string
     */
    String deleteCfInoById(int id) {
        cfInfoRepository.delete((long) id);
        return Constants.RESULT_STATUS_SUCCESS;
    }

}
