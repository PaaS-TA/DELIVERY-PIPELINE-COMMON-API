package paasta.delivery.pipeline.common.api.domain.common.authority;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by Mingu on 2017-06-01.
 */
@RestController
@RequestMapping(value = "/authorities")
public class AuthorityController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityController.class);
    private final AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    /**
     * @apiVersion 0.1.0
     * @api {POST} /authorities Regist Authority
     * @apiDescription 권한을 등록한다.
     * @apiGroup Authorities
     * @apiExample {curl} Example usage:
     * curl 'http://localhost/authorities' -i -X POST \
     * -H 'Authorization: Basic YWRtaW46Y2xvdWR==' \
     * -H 'Content-Type: application/json' \
     * -d '{ "displayName": "dashboard.user",
     * "description": "dashboard user"
     * }'
     * @apiHeader {String} Authorization API 인증 토큰 (Basic)
     * @apiHeader {String} Content-Type Body 데이터 타입.
     * @apiParam {String} displayName 권한의 보여질 이름
     * @apiParam {String} [description] 권한 설명
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Authority add(HttpServletResponse response, @RequestBody Authority newAuthority) throws IOException {
        LOGGER.info("Add Authority: {}", newAuthority);
        Authority result = null;

        Authority authority = authorityService.getAuthorityByName(newAuthority.getDisplayName());
        if (authority != null) {
            LOGGER.info("Failed Add Authority: '{}' is already exist.", authority.getDisplayName());
            response.sendError(HttpStatus.CONFLICT.value(), "Authority '" + authority.getDisplayName() + "' is already exist.");
        } else {
            newAuthority.setId(UUID.randomUUID().toString());
            result = authorityService.createAuthority(newAuthority);
        }

        return result;
    }

    /**
     * @apiVersion 0.1.0
     * @api {GET} /authorities Get Authority List
     * @apiDescription 권한 목록을 조회한다.
     * @apiGroup Authorities
     * @apiExample {curl} Example usage:
     * curl 'http://localhost/authorities' -i -X GET \
     * -H 'Authorization: Basic YWRtaW46Y2xvdWR=='
     * @apiHeader {String} Authorization API 인증 토큰 (Basic)
     * @apiParam {String} authority_id 권한의 고유 식별자
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Authority> getList() {
        LOGGER.info("Get Authority List");
        List<Authority> result = authorityService.getAll();
        return result;
    }

    /**
     * @apiVersion 0.1.0
     * @api {GET} /authorities/{authority_id} Get Authority
     * @apiDescription 권한을 조회한다.
     * @apiGroup Authorities
     * @apiExample {curl} Example usage:
     * curl 'http://localhost/authorities/7f2b54f2-3c46-4fec-a8b4-242825fad8ed' -i -X GET \
     * -H 'Authorization: Basic YWRtaW46Y2xvdWR=='
     * @apiHeader {String} Authorization API 인증 토큰 (Basic)
     * @apiParam {String} authority_id 권한의 고유 식별자
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Authority get(@PathVariable("id") String id) {
        LOGGER.info("Get Authority: {}", id);
        return authorityService.getAuthority(id);
    }

    @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
    public Authority getAuthByAuthCode(@PathVariable("code") String code) {
        return authorityService.getAuthorityByCode(code);
    }

    @RequestMapping(value = "/authType/{authType}", method = RequestMethod.GET)
    public List<Authority> getAuthorityByAuthType(@PathVariable("authType") String authType) {
        return authorityService.getAuthorityByAuthType(authType);
    }

    /**
     * @apiVersion 0.1.0
     * @api {Delete} /authorities/{authority_id} Delete Authority
     * @apiDescription 권한을 삭제한다.
     * @apiGroup Authorities
     * @apiExample {curl} Example usage:
     * curl 'http://localhost/authorities/7f2b54f2-3c46-4fec-a8b4-242825fad8ed' -i -X Delete \
     * -H 'Authorization: Basic YWRtaW46Y2xvdWR=='
     * @apiHeader {String} Authorization API 인증 토큰 (Basic)
     * @apiParam {String} authority_id 권한의 고유 식별자
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        LOGGER.info("Delete Authority: {}", id);
        authorityService.deleteAuthority(id);
    }

//    not supported
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public Authority update(@PathVariable("id") String id,
//                            @RequestBody Authority updateAuthority) {
//        return authorityService.updateAuthority(id, updateAuthority);
//    }
}
