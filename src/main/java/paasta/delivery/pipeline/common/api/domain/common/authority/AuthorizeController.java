package paasta.delivery.pipeline.common.api.domain.common.authority;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import paasta.delivery.pipeline.common.api.common.Constants;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by Mingu on 2017-06-01.
 */
@RestController
@RequestMapping(value = "/authorize")
public class AuthorizeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizeController.class);
    private final AuthorityService authorityService;
    private final GrantedAuthorityService grantedAuthorityService;

    public AuthorizeController(AuthorityService authorityService, GrantedAuthorityService grantedAuthorityService) {
        this.authorityService = authorityService;
        this.grantedAuthorityService = grantedAuthorityService;
    }

    /**
     * @apiVersion 0.1.0
     * @api {POST} /authorities Authorize
     * @apiDescription 사용자에게 권한을 부여한다.
     * @apiGroup Authorize
     * @apiExample {curl} Example usage:
     * curl 'http://localhost/authorize' -i -X POST \
     * -H 'Authorization: Basic YWRtaW46Y2xvdWR==' \
     * -H 'Content-Type: application/json' \
     * -d '{ "instance_use_id": 1,
     * "authority_id": "7f2b54f2-3c46-4fec-a8b4-242825fad8ed"
     * }'
     * @apiHeader {String} Authorization API 인증 토큰 (Basic)
     * @apiHeader {String} Content-Type Body 데이터 타입.
     * @apiParam {Long} instance_use_id 서비스 인스턴스 사용자의 고유식별자
     * @apiParam {String} authority_id 권한의 고유식별자
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public GrantedAuthority authorize(HttpServletResponse response, @RequestBody GrantedAuthority newGrantedAuthority) throws IOException {

        LOGGER.info("############# newGrantedAuthority:::{}", newGrantedAuthority.getAuthCode());

        newGrantedAuthority.setId(UUID.randomUUID().toString());
        newGrantedAuthority.setAuthority(new Authority(newGrantedAuthority.getAuthorityId()));

        if(newGrantedAuthority.getAuthCode() == null) {
            newGrantedAuthority.setAuthCode((long) 0);
        }
        return grantedAuthorityService.createGrantedAuthority(newGrantedAuthority);
    }


    /**
     * @apiVersion 0.1.0
     * @api {GET} /authorities Get Authorize List
     * @apiDescription 권한 부여 목록을 조회한다.
     * @apiGroup Authorize
     * @apiExample {curl} Example usage:
     * curl 'http://localhost/authorize' -i -X GET \
     * -H 'Authorization: Basic YWRtaW46Y2xvdWR=='
     * @apiHeader {String} Authorization API 인증 토큰 (Basic)
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<GrantedAuthority> getList() {
        return grantedAuthorityService.getGrantedAuthorityList();
    }


    /**
     * @apiVersion 0.1.0
     * @api {GET} /authorize/{authorize_id} Get Authorize
     * @apiDescription 사용자에게 부여된 권한을 조회한다.
     * @apiGroup Authorize
     * @apiExample {curl} Example usage:
     * curl 'http://localhost/authorize/4ea73add-d702-4020-a549-5ee87b25ce65' -i -X GET \
     * -H 'Authorization: Basic YWRtaW46Y2xvdWR=='
     * @apiHeader {String} Authorization API 인증 토큰 (Basic)
     * @apiParam {String} authorize_id 사용자에게 부여된 권한의 고유식별자
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public GrantedAuthority get(@PathVariable String id) {
        return grantedAuthorityService.getGrantedAuthority(id);
    }


    /**
     * @apiVersion 0.1.0
     * @api {DELETE} /authorize/{authorize_id} Delete Authorize
     * @apiDescription 사용자에게 부여된 권한을 해제한다.
     * @apiGroup Authorize
     * @apiExample {curl} Example usage:
     * curl 'http://localhost/authorize/4ea73add-d702-4020-a549-5ee87b25ce65' -i -X DELETE \
     * -H 'Authorization: Basic YWRtaW46Y2xvdWR=='
     * @apiHeader {String} Authorization API 인증 토큰 (Basic)
     * @apiParam {String} authorize_id 사용자에게 부여된 권한의 고유식별자
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable String id) {
        grantedAuthorityService.deleteGrantedAuthority(id);
        return Constants.RESULT_STATUS_SUCCESS;
    }
}
