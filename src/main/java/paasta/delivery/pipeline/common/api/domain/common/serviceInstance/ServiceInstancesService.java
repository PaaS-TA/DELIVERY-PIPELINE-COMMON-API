package paasta.delivery.pipeline.common.api.domain.common.serviceInstance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import paasta.delivery.pipeline.common.api.common.Constants;
import paasta.delivery.pipeline.common.api.domain.common.authority.GrantedAuthority;
import paasta.delivery.pipeline.common.api.domain.common.authority.GrantedAuthorityService;
import paasta.delivery.pipeline.common.api.domain.common.cf.info.CfInfo;
import paasta.delivery.pipeline.common.api.domain.common.cf.info.CfInfoRepository;
import paasta.delivery.pipeline.common.api.domain.common.cf.url.CfUrl;
import paasta.delivery.pipeline.common.api.domain.common.cf.url.CfUrlRepository;
import paasta.delivery.pipeline.common.api.domain.common.pipeline.Pipeline;
import paasta.delivery.pipeline.common.api.domain.common.pipeline.PipelineRepository;
import paasta.delivery.pipeline.common.api.domain.common.pipeline.PipelineService;
import paasta.delivery.pipeline.common.api.domain.common.project.Project;
import paasta.delivery.pipeline.common.api.domain.common.project.ProjectRepository;
import paasta.delivery.pipeline.common.api.domain.common.project.ProjectService;

import java.util.List;

/**
 * Created by hrjin on 2017-05-29.
 */
@Service
public class ServiceInstancesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceInstancesService.class);
    private final ServiceInstancesRepository serviceInstancesRepository;
    private final PipelineRepository pipelineRepository;
    private final ProjectRepository projectRepository;
    private final CfInfoRepository cfInfoRepository;
    private final CfUrlRepository cfUrlRepository;

    private static final String SHARED = "Shared";

    private static final String USED_SERVER = "Y";

    @Autowired
    private CiInfoService ciInfoService;

    @Autowired
    private PipelineService pipelineService;

    @Autowired
    private InstanceUseService instanceUseService;

    @Autowired
    private GrantedAuthorityService grantedAuthorityService;

    @Autowired
    private ProjectService projectService;


    @Autowired
    public ServiceInstancesService(ServiceInstancesRepository serviceInstancesRepository, PipelineRepository pipelineRepository, ProjectRepository projectRepository, CfInfoRepository cfInfoRepository, CfUrlRepository cfUrlRepository) {
        this.serviceInstancesRepository = serviceInstancesRepository;
        this.pipelineRepository = pipelineRepository;
        this.projectRepository = projectRepository;
        this.cfInfoRepository = cfInfoRepository;
        this.cfUrlRepository = cfUrlRepository;
    }

    public ServiceInstances createInstances(@RequestBody ServiceInstances serviceInstances) {
        CiInfo ciInfo;
        if(serviceInstances.getServiceType() == null || serviceInstances.getServiceType() == ""){
            serviceInstances.setServiceType(SHARED);
        }


        ciInfo = ciInfoService.getNotUsedCfinfo(serviceInstances.getServiceType());

        if (ciInfo != null) {
            serviceInstances.setCiServerUrl(ciInfo.getServerUrl());
            serviceInstances.setServiceType(serviceInstances.getServiceType());
            ServiceInstances newInstances = serviceInstancesRepository.save(serviceInstances);
            newInstances.setServiceType(serviceInstances.getServiceType());
            ciInfo.setUsedcount(ciInfo.getUsedcount() + 1);
            ciInfo.setStatus(USED_SERVER);
            ciInfoService.update(ciInfo);

            return newInstances;
        } else {
            return null;
        }
    }

    public String deleteInstance(String id) {
        try {
            ServiceInstances serviceInstances = serviceInstancesRepository.getOne(id);
            String ciServerUrl = serviceInstances.getCiServerUrl();

            // 1. cf url 삭제
            List<CfUrl> cfUrlList = cfUrlRepository.findByServiceInstancesId(id);
            if(cfUrlList.size() > 0) {
                for (int i = 0; i < cfUrlList.size(); i++) {
                    cfInfoRepository.delete(cfUrlList.get(i).getId());
                }
            }

            // 1. cf info 삭제
            List<CfInfo> cfInfoList = cfInfoRepository.findByServiceInstancesId(id);
            if(cfInfoList.size() > 0) {
                for (int i = 0; i < cfInfoList.size(); i++) {
                    cfInfoRepository.deleteCfInoById(cfInfoList.get(i).getId());
                }
            }

            // 1. 프로젝트 삭제
            List<Project> projects = projectRepository.findByserviceInstancesId(id);
            if(projects.size() > 0) {
                for (int i = 0; i < projects.size(); i++) {
                    projectService.deleteProject(projects.get(i));
                }
            }

            // 1.  서비스 인스턴스가 삭제되기 전 관련 Granted_Authority 먼저 삭제.
            List<InstanceUse> instanceUseList = instanceUseService.findByServiceInstancesId(serviceInstances.getId());
            for(int i = 0; i < instanceUseList.size(); i++){
                List<GrantedAuthority> grantedAuthorityList = grantedAuthorityService.findByInstanceUseId(instanceUseList.get(i).getId());
                LOGGER.info("지워질 grantedAuthority 목록 :::::::::: " + grantedAuthorityList);
                if(grantedAuthorityList.size() > 0) {
                    grantedAuthorityService.deleteGrantedAuthorityRows(grantedAuthorityList);
                }
            }

            // 1. 해당 서비스 인스턴스 아이디를 가지고 있는 파이프라인 목록을 가져온다.
            List<Pipeline> deletePipelineList = pipelineRepository.findByServiceInstancesId(serviceInstances.getId());
            LOGGER.info("지워질 파이프라인 목록 :::::::::: " + deletePipelineList.toString());

            // 2. deletePipelineList 가 존재하면 해당 파이프라인 목록으로 for 문을 작성한다. 이 때 PipelineService 를 이용하여 Pipeline 를 차례대로 삭제한다.
            if(deletePipelineList.size() > 0) {
                for (int i = 0; i < deletePipelineList.size(); i++) {
                    pipelineService.deletePipeline(deletePipelineList.get(i).getId());
                }
            }

            serviceInstancesRepository.delete(id);
            ciInfoService.recovery(ciServerUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Constants.RESULT_STATUS_SUCCESS;
    }

    public List<ServiceInstances> getServiceInstances() {
        List<ServiceInstances> serviceInstances = serviceInstancesRepository.findAll();
        return serviceInstances;
    }

    public ServiceInstances getServiceInstance(String id) {
        ServiceInstances serviceInstance = serviceInstancesRepository.findOne(id);
        return serviceInstance;
    }
}
