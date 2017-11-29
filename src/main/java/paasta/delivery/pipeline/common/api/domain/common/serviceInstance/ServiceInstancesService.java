package paasta.delivery.pipeline.common.api.domain.common.serviceInstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import paasta.delivery.pipeline.common.api.common.Constants;

import java.util.List;

/**
 * Created by hrjin on 2017-05-29.
 */
@Service
public class ServiceInstancesService {

    private final ServiceInstancesRepository serviceInstancesRepository;

    private static final String SHARED = "Shared";


    private static final String USED_SERVER = "Y";

    @Autowired
    private CiInfoService ciInfoService;

    @Autowired
    public ServiceInstancesService(ServiceInstancesRepository serviceInstancesRepository) {
        this.serviceInstancesRepository = serviceInstancesRepository;
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
