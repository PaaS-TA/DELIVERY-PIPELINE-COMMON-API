package paasta.delivery.pipeline.common.api.domain.common.user;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import paasta.delivery.pipeline.common.api.common.CommonService;
import paasta.delivery.pipeline.common.api.common.Constants;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by user on 2017-05-18.
 */
@Service
public class UserService {

    private final Logger LOGGER = getLogger(getClass());
    private final CommonService commonService;
    private final UserRepository userRepository;

    @Autowired
    public UserService(CommonService commonService, UserRepository userRepository) {
        this.commonService = commonService;
        this.userRepository = userRepository;
    }


    public UserList getUserList(String reqName, Pageable pageable) {
        LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        LOGGER.info("  - PageNumber :: {}", pageable.getPageNumber());
        LOGGER.info("  - PageSize :: {}", pageable.getPageSize());
        LOGGER.info("  - Sort :: {}", pageable.getSort());
        LOGGER.info("  - Offset :: {}", pageable.getOffset());
        LOGGER.info("  - HasPrevious :: {}", pageable.hasPrevious());
        LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        UserList userList;
        Page<User> userListPage;

        if (reqName == null || "".equals(reqName)) {
            userListPage = userRepository.findAll(pageable);
        } else {
            userListPage = userRepository.findAll(UserSpecifications.hasName(reqName), pageable);
        }

        userList = (UserList) commonService.setPageInfo(userListPage, new UserList());
        userList.setUsers(userListPage.getContent());

        LOGGER.info("###### {}", userList);
        return userList;
    }


    public User getUser(String id) {
        User getUser = userRepository.findOne(id);
        return getUser;
    }


    public User createUser(@RequestBody User reqUser) {
        LOGGER.info("####### add :: {}", reqUser.toString());
        User newUser = userRepository.save(reqUser);
        LOGGER.info("####### add :: result ::{}", newUser.toString());
        return newUser;
    }


    public User updateUser(@PathVariable String id, @RequestBody User reqUser) {
        //reqUser.setId(id);
        User modifyUser = userRepository.save(reqUser);
        return modifyUser;
    }


    public String deleteUser(String id) {
        userRepository.delete(id);
        return Constants.RESULT_STATUS_SUCCESS;
    }

}
