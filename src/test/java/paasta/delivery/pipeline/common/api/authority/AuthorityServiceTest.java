package paasta.delivery.pipeline.common.api.authority;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import paasta.delivery.pipeline.common.api.domain.common.authority.Authority;
import paasta.delivery.pipeline.common.api.domain.common.authority.AuthorityRepository;
import paasta.delivery.pipeline.common.api.domain.common.authority.AuthorityService;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Mingu on 2017-05-31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthorityServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityServiceTest.class);

    private AuthorityService authorityService;

    @MockBean
    private AuthorityRepository authorityRepository;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        authorityService = new AuthorityService(authorityRepository);
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
    public void test_001_createAuthority() throws Exception {

        Authority auth = new Authority();

        auth.setId("test-auth-" + UUID.randomUUID());
        auth.setDisplayName("test-display-name" + UUID.randomUUID());

        when(authorityRepository.save(auth)).thenReturn(auth);
        Authority result = authorityService.createAuthority(auth);

        assertThat(result).isNotNull();
        assertEquals(auth.getId(), result.getId());
    }

//    @Test
//    public void test_002_createAuthority() {
//
//        Authority auth = new Authority();
//
////        auth.setId("test-auth-" + UUID.randomUUID());
//        auth.setDisplayName("test-display-name" + UUID.randomUUID());
//
//        when(authorityRepository.save(auth)).thenThrow(new SQLException());
//
//        try {
//            authorityService.createAuthority(auth);
//        } catch (Exception e) {
//            assertTrue(true);
//        }
//        assertTrue(false);
//    }
}
