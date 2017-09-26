package paasta.delivery.pipeline.common.api.domain.common.serviceInstance;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import paasta.delivery.pipeline.common.api.common.Constants;
import paasta.delivery.pipeline.common.api.domain.common.authority.Authority;
import paasta.delivery.pipeline.common.api.domain.common.authority.AuthorityService;
import paasta.delivery.pipeline.common.api.domain.common.authority.GrantedAuthority;
import paasta.delivery.pipeline.common.api.domain.common.authority.GrantedAuthorityService;
import paasta.delivery.pipeline.common.api.domain.common.user.User;
import paasta.delivery.pipeline.common.api.domain.common.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by hrjin on 2017-05-29.
 */
@RestController
@RequestMapping("/serviceInstance")
public class InstanceUseController {
    private static final int PAGE_SIZE = 5;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final InstanceUseService instanceUseService;
    private final ServiceInstancesRepository serviceInstancesRepository;
    private final UserRepository userRepository;
    private final GrantedAuthorityService grantedAuthorityService;
    private final AuthorityService authorityService;

    @Autowired
    public InstanceUseController(InstanceUseService instanceUseService, ServiceInstancesRepository serviceInstancesRepository, UserRepository userRepository, GrantedAuthorityService grantedAuthorityService, AuthorityService authorityService) {
        this.instanceUseService = instanceUseService;
        this.serviceInstancesRepository = serviceInstancesRepository;
        this.userRepository = userRepository;
        this.grantedAuthorityService = grantedAuthorityService;
        this.authorityService = authorityService;
    }


    /**
     * ServiceInstance 와 User 관계 목록 조회
     *
     * @return
     */
    @RequestMapping(value = "/detail")
    public List<InstanceUse> instanceServiceInstanceUseList() {
        List<InstanceUse> instanceServiceInstanceUseList = instanceUseService.instanceServiceInstanceUseList();
        return instanceServiceInstanceUseList;
    }


    /*
    *  하나의 ServiceInstance 당 User 목록 조회
    *
    * */
    @RequestMapping(value = "/{serviceInstanceId}/detail", method = RequestMethod.GET)
    public InstanceUseList instanceUseList(@PathVariable String serviceInstanceId,
                                           @RequestParam(value = "id", required = false) String userName,
                                           @RequestParam(value = "authName", required = false) String authName,
                                           @PageableDefault(sort = "user.name", direction = Sort.Direction.ASC, size = PAGE_SIZE) Pageable pageable) {
        InstanceUseList instanceUseList = instanceUseService.getInstanceUseList(serviceInstanceId, userName, authName, pageable);
        return instanceUseList;
    }


    /*
    *  상세 조회
    *
    * */
    @RequestMapping(value = "/{serviceInstanceId}/user/{userId}", method = RequestMethod.GET)
    public InstanceUse getInstanceUse(@PathVariable String serviceInstanceId, @PathVariable String userId) {
        InstanceUse instanceUse = instanceUseService.getInstanceUse(serviceInstanceId, userId);
        return instanceUse;
    }


    /*
    *  생성
    *
    * */
    @RequestMapping(value = "/{serviceInstanceId}/user/{userId}", method = RequestMethod.POST)
    public InstanceUse createInstanceUse(@PathVariable String serviceInstanceId, @PathVariable String userId) {

        ServiceInstances serviceInstances = serviceInstancesRepository.findOne(serviceInstanceId);

        User user = userRepository.findOne(userId);

        if (serviceInstances != null && user != null) {
            InstanceUse newInstanceUse = instanceUseService.createInstanceUse(new InstanceUse(serviceInstances, user));
            logger.info("newInstanceUse:::" + newInstanceUse);

            return newInstanceUse;
        }
            /*logger.info("서비스 인스턴스 아이디"+instanceUse.getServiceInstances().getId());
            logger.info("유저 아이디"+instanceUse.getUser().getId());*/

        return instanceUseService.getInstanceUse(serviceInstanceId, userId);
    }


    /*
    *  삭제
    *
    * */
    @RequestMapping(value = "/{serviceInstanceId}/user/{userId}", method = RequestMethod.DELETE)
    public String deleteInstanceUse(@PathVariable String serviceInstanceId, @PathVariable String userId) {

        // 1. InstanceUse 정보가 삭제 될 시 해당 grantedAuthority 가 먼저 삭제 됨.
        InstanceUse getInstanceUse = instanceUseService.getInstanceUse(serviceInstanceId, userId);
        List<GrantedAuthority> grantedAuthorities = grantedAuthorityService.findByInstanceUseId(getInstanceUse.getId());
        logger.info("grantedAuthorities ::: " + grantedAuthorities);
        grantedAuthorityService.deleteGrantedAuthorityRows(grantedAuthorities);


        // 2. 그 다음 InstanceUse 정보 삭제
        InstanceUse deleteInstanceUse = instanceUseService.getInstanceUse(serviceInstanceId, userId);
        logger.info("deleteInstanceUse:::" + deleteInstanceUse);
        instanceUseService.deleteInstanceUse(deleteInstanceUse.getId());

        return Constants.RESULT_STATUS_SUCCESS;
    }

    /*
    *  권한 업데이트
    *
    * */
    @RequestMapping(value = "/{serviceInstanceId}/user/{userId}", method = RequestMethod.PUT)
    public InstanceUse updateInstanceUse(@PathVariable String serviceInstanceId, @PathVariable String userId, @RequestBody InstanceUse instanceUse) {
        InstanceUse updateInstanceUse = instanceUseService.getInstanceUse(serviceInstanceId, userId);

        // 1. description update (optional condition to be !!!!!!!!!!!!!) 자꾸 널포인트 이셉션 떨어져서 추가함...그리고...모르겟음...어짜피 값안넣으면 널이니간
        if (instanceUse.getUserDescription() != null) {
            updateInstanceUse.getUser().setDescription(instanceUse.getUserDescription());
        } else if (instanceUse.getUser().getDescription() != null) {
            updateInstanceUse.getUser().setDescription(instanceUse.getUser().getDescription());
        }

        // 2. 권한 목록 list 화 시키는 부분.
        String[] authValues = instanceUse.getAuthListStr().split("/");

        Authority manager = authorityService.getAuthorityByCode("dashboard.manager");
        Authority user = authorityService.getAuthorityByCode("dashboard.user");


        List<String> authList = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < authValues.length; i++) {
            authList.add(authValues[i]);
            if (manager.getId().equalsIgnoreCase(authValues[i]) || user.getId().equalsIgnoreCase(authValues[i])) {
                count++;
            }
        }

        // 3. 해당되는 instance_use_id 를 가지고 있는 권한을 다 지운다. -> 관리자 OR 사용자 변경 일경우 다른 권한 삭제 안함
        List<GrantedAuthority> grantedAuthorities = grantedAuthorityService.findByInstanceUseId(updateInstanceUse.getId());
        if (count <= 0) {
            for (int i = 0; i < grantedAuthorities.size(); i++) {
                if (grantedAuthorities.get(i).getAuthority().getAuthType().equalsIgnoreCase("pipeline") && grantedAuthorities.get(i).getAuthCode() != null && grantedAuthorities.get(i).getAuthCode().intValue() == instanceUse.getPipelineId().intValue()) {
                    grantedAuthorityService.deleteGrantedAuthority(grantedAuthorities.get(i).getId());
                }
            }
        } else {
            List<GrantedAuthority> deleteGrantedAuthority = new ArrayList<>();
            for (GrantedAuthority grantedAuthority : grantedAuthorities) {
                if (manager.getId().equalsIgnoreCase(grantedAuthority.getAuthority().getId()) || user.getId().equalsIgnoreCase(grantedAuthority.getAuthority().getId())) {
                    deleteGrantedAuthority.add(grantedAuthority);
                }
            }
            grantedAuthorityService.deleteGrantedAuthorityRows(deleteGrantedAuthority);
        }
        // 4. param 으로 들어온 authority_id 를 authList 의 size 로 for 문을 돌려서 새로 등록해준다.
        // 이 때 필요한 건? instance_use_id 와 authority_id 딱 2개면 된다!
        for (int i = 0; i < authList.size(); i++) {
            GrantedAuthority grantedAuthority = new GrantedAuthority();

            grantedAuthority.setId(UUID.randomUUID().toString());
            grantedAuthority.setAuthorityId(authList.get(i));
            grantedAuthority.setInstanceUseId(updateInstanceUse.getId());

            if (instanceUse.getPipelineId() != null) {
                grantedAuthority.setAuthCode(instanceUse.getPipelineId());
            }
            //grantedAuthority.setAuthCode(instanceUse.getPipelineId());
            grantedAuthority.setAuthority(authorityService.getAuthority(authList.get(i)));


            grantedAuthorityService.createGrantedAuthority(grantedAuthority);
        }

        InstanceUse modifyInstanceUse = instanceUseService.updateInstanceUse(updateInstanceUse);

        return modifyInstanceUse;
    }


    /*
    *  파이프라인에 따른 참여자 리스트 조회
    *
    * */
    @RequestMapping(value = "/{suid}/pipeline/{pipelineId}")
    public InstanceUseList getInstanceUseListByPipelineContributor(@RequestParam(value = "id", required = false) String name,
                                                                   @PathVariable String suid, @PathVariable Long pipelineId, @PageableDefault(sort = "user.name", direction = Sort.Direction.ASC, size = PAGE_SIZE) Pageable pageable) {
        InstanceUseList instanceUseList = instanceUseService.getInstanceUseListByPipelineContributor(suid, pipelineId, name, pageable);
        return instanceUseList;
    }
}
