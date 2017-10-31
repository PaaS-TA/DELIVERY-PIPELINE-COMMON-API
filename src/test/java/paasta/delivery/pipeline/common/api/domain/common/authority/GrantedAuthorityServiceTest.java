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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created by Mingu on 2017-05-31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GrantedAuthorityServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(GrantedAuthorityServiceTest.class);

    @InjectMocks
    private GrantedAuthorityService grantedAuthorityService;

    @Mock
    private GrantedAuthorityRepository grantedAuthorityRepository;


    private String PARAM = "TEST_PARAM";
    private GrantedAuthority grantedauth;
    private List<GrantedAuthority> testGrantedAuthorityList;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        grantedAuthorityService = new GrantedAuthorityService(grantedAuthorityRepository);

        long authCode = 12345667;
        long instanceUseId_ = 123456789;
        grantedauth = new GrantedAuthority();
        grantedauth.setAuthority(getAuthority(1));
        grantedauth.setId("id");
        grantedauth.setAuthCode(authCode);
        grantedauth.setAuthorityId("authorityId");
        grantedauth.setInstanceUseId(instanceUseId_);
        grantedauth.setCreated(new Date());

        testGrantedAuthorityList = setGrantedAuthorityList();

        GrantedAuthority grantedAuthority = new GrantedAuthority("ID",instanceUseId_,"authorityId");
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
            long instanceUseId_ = 123456789 + i;
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


    private GrantedAuthority getGrantedAuthority(int i) {
        GrantedAuthority returnValue = new GrantedAuthority();
        long authCode = 12345667 + i;
        long instanceUseId_ = 123456789 + i;
        returnValue = new GrantedAuthority();
        returnValue.setAuthority(getAuthority(1));
        returnValue.setId("id_" + i);
        returnValue.setAuthCode(authCode);
        returnValue.setAuthorityId("authorityId_" + i);
        returnValue.setInstanceUseId(instanceUseId_);
        returnValue.setCreated(new Date());
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
    public void test_getGrantedAuthority() {
        GrantedAuthority grantedAuthority = getGrantedAuthority(1);
        when(grantedAuthorityRepository.findOne(anyString())).thenReturn(grantedAuthority);

        GrantedAuthority result = grantedAuthorityService.getGrantedAuthority(PARAM);

        assertEquals(grantedAuthority.getId(), result.getId());
        assertEquals(grantedAuthority.getAuthority(), result.getAuthority());
        assertEquals(grantedAuthority.getAuthCode(), result.getAuthCode());
        assertEquals(grantedAuthority.getAuthorityId(), result.getAuthorityId());
        assertEquals(grantedAuthority.getInstanceUseId(), result.getInstanceUseId());
        assertEquals(grantedAuthority.getCreated(), result.getCreated());

    }

    @Test
    public void test_getGrantedAuthorityByInstanceUseIdAndAuthorityId() {

        GrantedAuthority grantedAuthority = getGrantedAuthority(1);
        when(grantedAuthorityRepository.findTopByInstanceUseIdOrAuthority(anyLong(),any(Authority.class))).thenReturn(grantedAuthority);

        GrantedAuthority result = grantedAuthorityService.getGrantedAuthorityByInstanceUseIdAndAuthorityId(grantedAuthority.getInstanceUseId(),PARAM);

        assertEquals(grantedAuthority.getId(), result.getId());
        assertEquals(grantedAuthority.getAuthority(), result.getAuthority());
        assertEquals(grantedAuthority.getAuthCode(), result.getAuthCode());
        assertEquals(grantedAuthority.getAuthorityId(), result.getAuthorityId());
        assertEquals(grantedAuthority.getInstanceUseId(), result.getInstanceUseId());
        assertEquals(grantedAuthority.getCreated(), result.getCreated());


    }

    @Test
    public void test_findAll() {
        when(grantedAuthorityRepository.findAll()).thenReturn(testGrantedAuthorityList);

        List<GrantedAuthority> result = grantedAuthorityService.findAll();

        assertThat(result).isNotNull();
        assertEquals(testGrantedAuthorityList.get(0).getId(), result.get(0).getId());
        assertEquals(testGrantedAuthorityList.get(0).getAuthority(), result.get(0).getAuthority());
        assertEquals(testGrantedAuthorityList.get(0).getAuthCode(), result.get(0).getAuthCode());
        assertEquals(testGrantedAuthorityList.get(0).getAuthorityId(), result.get(0).getAuthorityId());
        assertEquals(testGrantedAuthorityList.get(0).getInstanceUseId(), result.get(0).getInstanceUseId());
        assertEquals(testGrantedAuthorityList.get(0).getCreated(), result.get(0).getCreated());
    }



    @Test
    public void test_getGrantedAuthorityList() {

        when(grantedAuthorityRepository.findAll()).thenReturn(testGrantedAuthorityList);

        List<GrantedAuthority> result = grantedAuthorityService.getGrantedAuthorityList();

        assertThat(result).isNotNull();
        assertEquals(testGrantedAuthorityList.get(0).getId(), result.get(0).getId());
        assertEquals(testGrantedAuthorityList.get(0).getAuthority(), result.get(0).getAuthority());
        assertEquals(testGrantedAuthorityList.get(0).getAuthCode(), result.get(0).getAuthCode());
        assertEquals(testGrantedAuthorityList.get(0).getAuthorityId(), result.get(0).getAuthorityId());
        assertEquals(testGrantedAuthorityList.get(0).getInstanceUseId(), result.get(0).getInstanceUseId());
        assertEquals(testGrantedAuthorityList.get(0).getCreated(), result.get(0).getCreated());

    }


    @Test
    public void test_findByInstanceUseId() {
        GrantedAuthority grantedAuthority = getGrantedAuthority(1);
        when(grantedAuthorityRepository.findByInstanceUseId(anyLong())).thenReturn(testGrantedAuthorityList);

        List<GrantedAuthority> result = grantedAuthorityService.findByInstanceUseId(grantedAuthority.getInstanceUseId());

        assertThat(result).isNotNull();
        assertEquals(testGrantedAuthorityList.get(0).getId(), result.get(0).getId());
        assertEquals(testGrantedAuthorityList.get(0).getAuthority(), result.get(0).getAuthority());
        assertEquals(testGrantedAuthorityList.get(0).getAuthCode(), result.get(0).getAuthCode());
        assertEquals(testGrantedAuthorityList.get(0).getAuthorityId(), result.get(0).getAuthorityId());
        assertEquals(testGrantedAuthorityList.get(0).getInstanceUseId(), result.get(0).getInstanceUseId());
        assertEquals(testGrantedAuthorityList.get(0).getCreated(), result.get(0).getCreated());
    }

    @Test
    public void test_findByAuthCode() {
        GrantedAuthority grantedAuthority = getGrantedAuthority(1);
        when(grantedAuthorityRepository.findByAuthCode(anyLong())).thenReturn(testGrantedAuthorityList);

        List<GrantedAuthority> result = grantedAuthorityService.findByAuthCode(grantedAuthority.getAuthCode());

        assertThat(result).isNotNull();
        assertEquals(testGrantedAuthorityList.get(0).getId(), result.get(0).getId());
        assertEquals(testGrantedAuthorityList.get(0).getAuthority(), result.get(0).getAuthority());
        assertEquals(testGrantedAuthorityList.get(0).getAuthCode(), result.get(0).getAuthCode());
        assertEquals(testGrantedAuthorityList.get(0).getAuthorityId(), result.get(0).getAuthorityId());
        assertEquals(testGrantedAuthorityList.get(0).getInstanceUseId(), result.get(0).getInstanceUseId());
        assertEquals(testGrantedAuthorityList.get(0).getCreated(), result.get(0).getCreated());
    }

    @Test
    public void test_reateGrantedAuthority() {
        when(grantedAuthorityRepository.save(grantedauth)).thenReturn(grantedauth);
        GrantedAuthority result = grantedAuthorityService.createGrantedAuthority(grantedauth);
        assertThat(result).isNotNull();
        assertEquals(grantedauth.getId(), result.getId());

    }

    @Test
    public void test_deleteGrantedAuthority() {
        doNothing().when(grantedAuthorityRepository).delete(PARAM);
        String result = grantedAuthorityService.deleteGrantedAuthority(PARAM);
        assertThat(result).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, result);
    }

    @Test
    public void test_deleteGrantedAuthorityRows() {
        doNothing().when(grantedAuthorityRepository).delete(PARAM);
        grantedAuthorityService.deleteGrantedAuthorityRows(testGrantedAuthorityList);
    }

}
