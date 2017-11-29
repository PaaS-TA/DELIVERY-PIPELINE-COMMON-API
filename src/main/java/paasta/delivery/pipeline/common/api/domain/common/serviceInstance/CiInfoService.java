package paasta.delivery.pipeline.common.api.domain.common.serviceInstance;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map initCiinfo(List<CiInfo> ciInfos) {
        List<Map> register = new ArrayList<>();
        List<Map> remove = new ArrayList<>();
        List<String> serverUrls = new ArrayList<>();
        /*
        * 1. 등록 된 Ci정보가 있는 확인
        * 2. 있으면, 업데이트
        * 3. 없으면, 등록
        * 4. 그리고 남은 애들 삭제
        */

        for (CiInfo data : ciInfos) {
            Map initStatus = new HashMap();
            serverUrls.add(data.getServerUrl());
            initStatus.put("serverUrl", data.getServerUrl());
            initStatus.put("type", data.getType());
            CiInfo ciInfo = null;
            try {
                ciInfo = ciInfoRepository.findByServerUrl(data.getServerUrl());
            } catch (Exception e) {
                continue;
            }
            try {

                if (ciInfo != null) {
                    initStatus.put("process", "Update");
                    ciInfo.setType(data.getType());
                    ciInfoRepository.save(ciInfo);
                } else {
                    initStatus.put("process", "Insert");
                    data.setUsedcount(0);
                    data.setStatus("N");
                    ciInfoRepository.save(data);
                }

                initStatus.put("status", "SUCCESS");
            } catch (Exception e) {
                e.printStackTrace();
                initStatus.put("status", "FAIL");
            }
            register.add(initStatus);
        }
        /*
        * 전체 데이터 삭제....
         */
        if (serverUrls.size() == 0) {
            serverUrls.add("");
        }

        List<CiInfo> deleteServcerList = ciInfoRepository.findByServerUrlNotIn(serverUrls);
        for (CiInfo data : deleteServcerList) {
            Map removeStatus = new HashMap();
            removeStatus.put("serverUrl", data.getServerUrl());
            removeStatus.put("type", data.getType());
            removeStatus.put("process", "Remove");
            try {
                logger.info(data.getId() + "  " + data.getServerUrl());
                ciInfoRepository.delete(data.getId());
                removeStatus.put("status", "SUCCESS");
            } catch (Exception e) {
                e.printStackTrace();
                removeStatus.put("status", "FAIL");
            }
            remove.add(removeStatus);
        }

        Map result = new HashMap();
        result.put("register", register);
        result.put("remove", remove);
        return result;
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
