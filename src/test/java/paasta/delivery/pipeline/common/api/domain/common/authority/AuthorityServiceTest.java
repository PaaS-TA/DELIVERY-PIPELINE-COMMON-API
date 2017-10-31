package paasta.delivery.pipeline.common.api.domain.common.authority;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import paasta.delivery.pipeline.common.api.common.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created by Mingu on 2017-05-31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthorityServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityServiceTest.class);

    @InjectMocks
    private AuthorityService authorityService;

    @Mock
    private AuthorityRepository authorityRepository;


    private String PARAM = "TEST_PARAM";
    private Authority auth;
    private List<Authority> testAuthorityList;
    private List<GrantedAuthority> testGrantedAuthorityList;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        authorityService = new AuthorityService(authorityRepository);

        auth = new Authority();
        auth.setId("test-auth-" + UUID.randomUUID());
        auth.setDisplayName("test-display-name" + UUID.randomUUID());
        auth.setAuthCode("authcode");
        auth.setAuthType("authtype");
        auth.setCode("code");
        auth.setDescription("description");
        testGrantedAuthorityList = setGrantedAuthorityList();
        auth.setGrantedAuthorities(testGrantedAuthorityList);
        testAuthorityList = setAuthorityList();

    }

    private List<Authority> setAuthorityList() {
        List<Authority> returnValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            returnValue.add(getAuthority(i));
        }
        return returnValue;
    }

    private List<GrantedAuthority> setGrantedAuthorityList() {
        List<GrantedAuthority> returnValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            long authCode = 12345667 + i;
            long instanceUseId_ = 123456789 + 1;
            GrantedAuthority grantedAuthority = new GrantedAuthority();
            grantedAuthority.setAuthority(getAuthority(i));
            grantedAuthority.setId("id_" + i);
            grantedAuthority.setAuthCode(authCode);
            grantedAuthority.setAuthorityId("authorityId_" + i);
            grantedAuthority.setInstanceUseId(instanceUseId_);
            grantedAuthority.setCreated(new Date());
            returnValue.add(grantedAuthority);
        }

        return returnValue;
    }

    private Authority getAuthority(int i) {
        Authority returnValue = new Authority();
        returnValue.setId("id_" + i);
        returnValue.setDisplayName("test-display-name_" + i);
        returnValue.setAuthCode("authcode_" + i);
        returnValue.setAuthType("authtype" + i);
        returnValue.setCode("code_" + i);
        returnValue.setDescription("description_" + i);
        return returnValue;
    }


    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void test_getAll() {

        when(authorityRepository.findAll()).thenReturn(testAuthorityList);

        List<Authority> result = authorityService.getAll();

        assertThat(result).isNotNull();
        assertEquals(testAuthorityList.get(0).getId(), result.get(0).getId());
        assertEquals(testAuthorityList.get(0).getDisplayName(), result.get(0).getDisplayName());
        assertEquals(testAuthorityList.get(0).getAuthType(), result.get(0).getAuthType());
        assertEquals(testAuthorityList.get(0).getCode(), result.get(0).getCode());
        assertEquals(testAuthorityList.get(0).getDescription(), result.get(0).getDescription());
        assertEquals(testAuthorityList.get(0).getGrantedAuthorities(), result.get(0).getGrantedAuthorities());
    }

    @Test
    public void test_getAuthority() {

        Authority testResult = getAuthority(1);
        when(authorityRepository.findOne(PARAM)).thenReturn(testResult);

        Authority result = authorityService.getAuthority(PARAM);

        assertEquals(testResult.getId(), testResult.getId());
        assertEquals(testResult.getDisplayName(), testResult.getDisplayName());
        assertEquals(testResult.getAuthType(), testResult.getAuthType());
        assertEquals(testResult.getCode(), testResult.getCode());
        assertEquals(testResult.getDescription(), testResult.getDescription());
        assertEquals(testResult.getGrantedAuthorities(), testResult.getGrantedAuthorities());

    }

    @Test
    public void test_getAuthorityByName() {
        Authority testResult = getAuthority(1);
        when(authorityRepository.findByDisplayName(anyString())).thenReturn(testResult);

        Authority result = authorityService.getAuthorityByName(PARAM);

        assertEquals(testResult.getId(), testResult.getId());
        assertEquals(testResult.getDisplayName(), testResult.getDisplayName());
        assertEquals(testResult.getAuthType(), testResult.getAuthType());
        assertEquals(testResult.getCode(), testResult.getCode());
        assertEquals(testResult.getDescription(), testResult.getDescription());
        assertEquals(testResult.getGrantedAuthorities(), testResult.getGrantedAuthorities());
    }


    @Test
    public void test_getAuthorityByCode() {
        Authority testResult = getAuthority(1);
        when(authorityRepository.findByCode(anyString())).thenReturn(testResult);

        Authority result = authorityService.getAuthorityByCode(PARAM);

        assertEquals(testResult.getId(), testResult.getId());
        assertEquals(testResult.getDisplayName(), testResult.getDisplayName());
        assertEquals(testResult.getAuthType(), testResult.getAuthType());
        assertEquals(testResult.getCode(), testResult.getCode());
        assertEquals(testResult.getDescription(), testResult.getDescription());
        assertEquals(testResult.getGrantedAuthorities(), testResult.getGrantedAuthorities());
    }


    @Test
    public void test_getAuthorityByAuthType() {
        Authority testResult = getAuthority(1);
        when(authorityRepository.findByAuthType(anyString())).thenReturn(testAuthorityList);

        List<Authority> result = authorityService.getAuthorityByAuthType(PARAM);

        assertEquals(testAuthorityList.get(0).getId(), result.get(0).getId());
        assertEquals(testAuthorityList.get(0).getDisplayName(), result.get(0).getDisplayName());
        assertEquals(testAuthorityList.get(0).getAuthType(), result.get(0).getAuthType());
        assertEquals(testAuthorityList.get(0).getCode(), result.get(0).getCode());
        assertEquals(testAuthorityList.get(0).getDescription(), result.get(0).getDescription());
        assertEquals(testAuthorityList.get(0).getGrantedAuthorities(), result.get(0).getGrantedAuthorities());
    }

    @Test
    public void test_001_createAuthority() throws Exception {
        when(authorityRepository.save(auth)).thenReturn(auth);
        Authority result = authorityService.createAuthority(auth);
        assertThat(result).isNotNull();
        assertEquals(auth.getId(), result.getId());
    }


    @Test
    public void test_updateAuthority() {
        Authority testResult = getAuthority(1);
        when(authorityRepository.save(testResult)).thenReturn(testResult);
        Authority result = authorityService.updateAuthority(PARAM, auth);

        assertEquals(testResult.getId(), testResult.getId());
        assertEquals(testResult.getDisplayName(), testResult.getDisplayName());
        assertEquals(testResult.getAuthType(), testResult.getAuthType());
        assertEquals(testResult.getCode(), testResult.getCode());
        assertEquals(testResult.getDescription(), testResult.getDescription());
        assertEquals(testResult.getGrantedAuthorities(), testResult.getGrantedAuthorities());
    }

    @Test
    public void test_deleteAuthority() {
        doNothing().when(authorityRepository).delete(PARAM);
        String result = authorityService.deleteAuthority(PARAM);
        assertThat(result).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, result);
    }
}
