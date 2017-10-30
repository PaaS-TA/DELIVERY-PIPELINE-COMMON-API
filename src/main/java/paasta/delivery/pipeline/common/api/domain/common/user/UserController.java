package paasta.delivery.pipeline.common.api.domain.common.user;

import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * paastaDeliveryPipelineApi
 * paasta.delivery.pipeline.common.api.userRepository
 *
 * @author REX
 * @version 1.0
 * @since 5 /11/2017
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final int PAGE_SIZE = 5;
    private final Logger logger = getLogger(getClass());
    private final UserService userService;

    /**
     * Instantiates a new User controller.
     *
     * @param userService the user repository
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * 사용자 목록 조회
     *
     * @param name
     * @param pageable
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public UserList getUserList(@RequestParam(value = "id", required = false) String name, @PageableDefault(sort = "name", direction = Sort.Direction.ASC, size = PAGE_SIZE) Pageable pageable) {
        return userService.getUserList(name, pageable);
    }


    /**
     *사용자 상세 조회
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable String id) {
        User oneUser = userService.getUser(id);
        return oneUser;
    }


    /**
     * 사용자 등록
     *
     * @param reqUser
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public User create(@RequestBody User reqUser) {
        User newUser = userService.createUser(reqUser);
        logger.info("####### add :: result ::{}", newUser.toString());
        return newUser;
    }


    /**
     * 사용자 정보 수정
     *
     * @param id
     * @param reqUser
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public User update(@PathVariable String id, @RequestBody User reqUser) {
        User modifyUser = userService.getUser(id);
        // CHECK REQUEST ID
        modifyUser.setDescription(reqUser.getDescription());
        return userService.updateUser(modifyUser);
    }


    /**
     * 사용자 정보 삭제
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable String id) {
        return userService.deleteUser(id);
    }


}
