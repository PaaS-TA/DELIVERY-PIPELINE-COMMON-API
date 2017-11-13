package paasta.delivery.pipeline.common.api.domain.common.serviceInstance;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import paasta.delivery.pipeline.common.api.common.Constants;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by hrjin on 2017-05-29.
 */
@Service
public class ServiceInstancesService {

    private final Logger LOGGER = getLogger(getClass());

    private final ServiceInstancesRepository serviceInstancesRepository;


    private static final String SHARED = "shared";
    private static final String DEDICATED = "dedicated";

    private final String USED_SERVER = "Y";

    @Autowired
    private CiInfoService ciInfoService;

    @Autowired
    public ServiceInstancesService(ServiceInstancesRepository serviceInstancesRepository) {
        this.serviceInstancesRepository = serviceInstancesRepository;
    }

    public ServiceInstances createInstances(@RequestBody ServiceInstances serviceInstances) {
        CiInfo ciInfo;

        if (DEDICATED.equals(serviceInstances.getService_type())) {
            ciInfo = ciInfoService.getNotUsedCfinfo(DEDICATED);
        } else {
            ciInfo = ciInfoService.getNotUsedCfinfo(SHARED);
        }
        if (ciInfo != null) {
            serviceInstances.setCiServerUrl(ciInfo.getServerUrl());
            serviceInstances.setService_type(serviceInstances.getService_type());
            ServiceInstances newInstances = serviceInstancesRepository.save(serviceInstances);

            ciInfo.setUsedcount(ciInfo.getUsedcount()+1);
            ciInfo.setStatus(USED_SERVER);
            ciInfoService.update(ciInfo);

            return newInstances;
        } else {
            return null;
        }
    }

    public String deleteInstance(String id) {
        ServiceInstances serviceInstances = serviceInstancesRepository.getOne(id);
        String ci_server = serviceInstances.getCiServerUrl();
        serviceInstancesRepository.delete(id);
        ciInfoService.recovery(ci_server);
        return Constants.RESULT_STATUS_SUCCESS;
    }

    public List<ServiceInstances> getServiceInstances() {
        List<ServiceInstances> serviceInstances = serviceInstancesRepository.findAll();
        return serviceInstances;
    }

    public ServiceInstances getServiceInstance(String id) {
        ServiceInstances serviceInstance = serviceInstancesRepository.findOne(id);
        // TODO :: REMOVE TEMPORARY VALUE
        serviceInstance.setCiServerUrl("http://115.68.46.29");
        return serviceInstance;
    }
}
