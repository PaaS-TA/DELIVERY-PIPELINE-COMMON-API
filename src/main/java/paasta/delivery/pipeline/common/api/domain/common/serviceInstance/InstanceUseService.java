package paasta.delivery.pipeline.common.api.domain.common.serviceInstance;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import paasta.delivery.pipeline.common.api.common.CommonService;
import paasta.delivery.pipeline.common.api.common.Constants;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by hrjin on 2017-05-29.
 */
@Service
public class InstanceUseService {

    private final Logger LOGGER = getLogger(getClass());
    private final CommonService commonService;
    private final InstanceUseRepository instanceUseRepository;

    @Autowired
    public InstanceUseService(CommonService commonService, InstanceUseRepository instanceUseRepository) {
        this.commonService = commonService;
        this.instanceUseRepository = instanceUseRepository;
    }


    public InstanceUse createInstanceUse(@RequestBody InstanceUse instanceUse) {
        InstanceUse newInstanceUse = instanceUseRepository.save(instanceUse);
        return newInstanceUse;
    }

    public List<InstanceUse> instanceServiceInstanceUseList() {
        List<InstanceUse> instanceServiceInstanceUseList = instanceUseRepository.findAll();
        return instanceServiceInstanceUseList;
    }


    public InstanceUseList getInstanceUseList(String serviceInstanceId, String userName, String authName, Pageable pageable) {
        LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        LOGGER.info("  - PageNumber :: {}", pageable.getPageNumber());
        LOGGER.info("  - PageSize :: {}", pageable.getPageSize());
        LOGGER.info("  - Sort :: {}", pageable.getSort());
        LOGGER.info("  - Offset :: {}", pageable.getOffset());
        LOGGER.info("  - HasPrevious :: {}", pageable.hasPrevious());
        LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        InstanceUseList instanceUseList;
        Page<InstanceUse> pageList;


        if (userName == null && authName == null) {
            pageList = instanceUseRepository.findByServiceInstancesId(serviceInstanceId, pageable);
        } else if (userName != null && authName == null) {
            pageList = instanceUseRepository.findByServiceInstancesIdAndUserNameContaining(serviceInstanceId, userName, pageable);
        } else if (userName == null && authName != null) {
            pageList = instanceUseRepository.findByServiceInstancesIdAndGrantedAuthorities_Authority_Id(serviceInstanceId, authName, pageable);
        } else {
            pageList = instanceUseRepository.findByServiceInstancesIdAndUserNameContainingAndGrantedAuthorities_Authority_Id(serviceInstanceId, userName, authName, pageable);
        }

        instanceUseList = (InstanceUseList) commonService.setPageInfo(pageList, new InstanceUseList());
        instanceUseList.setInstanceUses(pageList.getContent());

        return instanceUseList;
    }

    public InstanceUseList getInstanceUseListByPipelineContributor(String suid, Long pipelineId, String reqName, Pageable pageable) {

        InstanceUseList instanceUseList;
        Page<InstanceUse> instanceUsePage;


        if (reqName == null || "".equals(reqName)) {
            instanceUsePage = instanceUseRepository.findAllByServiceInstancesIdAndGrantedAuthoritiesAuthCode(suid, pipelineId, pageable);
        } else {
            instanceUsePage = instanceUseRepository.findAllByServiceInstancesIdAndUserNameContainingAndGrantedAuthoritiesAuthCode(suid, reqName, pipelineId, pageable);
        }

        instanceUseList = (InstanceUseList) commonService.setPageInfo(instanceUsePage, new InstanceUseList());
        instanceUseList.setInstanceUses(instanceUsePage.getContent());

        LOGGER.info("###### getInstanceUseListByPipelineContributor {}", instanceUseList);
        return instanceUseList;
    }

    public InstanceUse getInstanceUse(String serviceInstancesId, String userId) {
        InstanceUse getInstanceUse = instanceUseRepository.findByServiceInstancesIdAndUserId(serviceInstancesId, userId);
        return getInstanceUse;
    }

    public String deleteInstanceUse(Long instanceUseId) {
        instanceUseRepository.delete(instanceUseId);
        return Constants.RESULT_STATUS_SUCCESS;
    }

    public InstanceUse updateInstanceUse(InstanceUse updateInstanceUse) {
        return instanceUseRepository.save(updateInstanceUse);
    }
}
