package paasta.delivery.pipeline.common.api.domain.common.qualityProfile;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import paasta.delivery.pipeline.common.api.common.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.doNothing;

/**
 * Created by kim on 2017-10-31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QualityProfileServiceTest {

    private static final long ID = 1L;
    private static final String LANGUAGE = "test-language-java";
    private static final String LANGUAGE_NAME = "test-language-name-Java";
    private static final String SERVICE_INSTANCES_ID = "test-service-instances-id";
    private static final int QUALITY_PROFILE_ID = 1;
    private static final String QUALITY_PROFILE_NAME = "test-quality-profile-name";
    private static final String QUALITY_PROFILE_KEY = "test-quality-profile-key";
    private static final List<Long> PROJECT_ID_LIST = new ArrayList<>();
    private static final String PROFILE_DEFAULT_YN = "N";
    private static final int ACTIVE_RULE_COUNT = 1;
    private static final int ACTIVE_DEPRECATED_RULE_COUNT = 1;
    private static final Date TEST_CREATED = new Date();
    private static final Date TEST_LAST_MODIFIED = new Date();
    private static final String CREATED_STRING = "test-created-string";
    private static final String LAST_MODIFIED_STRING = "last_modified_string";

    private static QualityProfile testModel = null;
    private static QualityProfile resultModel = null;
    private static List<QualityProfile> testResultList = null;

    @Mock
    private QualityProfileRepository qualityProfileRepository;

    @InjectMocks
    private QualityProfileService qualityProfileService;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        testModel = new QualityProfile();
        resultModel = new QualityProfile();
        testResultList = new ArrayList<>();

        testModel.setId(ID);
        testModel.setServiceInstancesId(SERVICE_INSTANCES_ID);
        testModel.setQualityProfileId(QUALITY_PROFILE_ID);
        testModel.setQualityProfileName(QUALITY_PROFILE_NAME);
        testModel.setQualityProfileKey(QUALITY_PROFILE_KEY);
        testModel.setProfileDefaultYn(PROFILE_DEFAULT_YN);
        testModel.setLanguage(LANGUAGE);
        testModel.setLanguageName(LANGUAGE_NAME);
        testModel.setActiveDeprecatedRuleCount(ACTIVE_DEPRECATED_RULE_COUNT);
        testModel.setActiveRuleCount(ACTIVE_RULE_COUNT);
        testModel.setCreated(TEST_CREATED);
        testModel.setLastModified(TEST_LAST_MODIFIED);
        testModel.setCreatedString(CREATED_STRING);
        testModel.setLastModifiedString(LAST_MODIFIED_STRING);
        testModel.setProjectIdList(PROJECT_ID_LIST);
        testModel.setResultStatus(Constants.RESULT_STATUS_SUCCESS);

        resultModel.setId(testModel.getId());
        resultModel.setServiceInstancesId(testModel.getServiceInstancesId());
        resultModel.setQualityProfileId(testModel.getQualityProfileId());
        resultModel.setQualityProfileName(testModel.getQualityProfileName());
        resultModel.setQualityProfileKey(testModel.getQualityProfileKey());
        resultModel.setProfileDefaultYn(testModel.getProfileDefaultYn());
        resultModel.setLanguage(testModel.getLanguage());
        resultModel.setLanguageName(testModel.getLanguageName());
        resultModel.setActiveDeprecatedRuleCount(testModel.getActiveDeprecatedRuleCount());
        resultModel.setActiveRuleCount(testModel.getActiveRuleCount());
        resultModel.setCreated(testModel.getCreated());
        resultModel.setLastModified(testModel.getLastModified());
        resultModel.setCreatedString(testModel.getCreatedString());
        resultModel.setLastModifiedString(testModel.getLastModifiedString());
        resultModel.setProjectIdList(testModel.getProjectIdList());
        resultModel.setResultStatus(testModel.getResultStatus());

        testResultList.add(resultModel);
    }


    /**
     * Get QualityProfileList  model valid return List.
     *
     * @throws Exception the exception
     */
    @Test
    public void getQualityProfileList_Valid_ReturnList() throws Exception {
        testModel.setProfileDefaultYn("Y");
        when(qualityProfileRepository.findAllByserviceInstancesIdOrProfileDefaultYn(testModel.getServiceInstancesId(), testModel.getProfileDefaultYn())).thenReturn(testResultList);

        List<QualityProfile> resultList = qualityProfileService.getQualityProfileList(SERVICE_INSTANCES_ID);
        assertThat(resultList).isNotNull();
        assertEquals(testResultList, resultList);
        assertEquals(SERVICE_INSTANCES_ID, resultList.get(0).getServiceInstancesId());
    }


    /**
     * Get QualityProfile  model valid return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void getQualityProfile_Valid_Return() throws Exception {
        when(qualityProfileRepository.findOne(ID)).thenReturn(resultModel);

        QualityProfile result = qualityProfileService.getQualityProfile(ID);
        assertThat(result).isNotNull();
        assertEquals(ID, result.getId());
    }


    /**
     * Create QualityProfile  model valid return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void createQualityProfile_Valid_Return() throws Exception {
        when(qualityProfileRepository.save(testModel)).thenReturn(resultModel);

        QualityProfile result = qualityProfileService.createQualityProfile(testModel);
        assertThat(result).isNotNull();
        assertEquals(ID, result.getId());
    }


    /**
     * Copy QualityProfile  model valid return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void qualityProfileCopy_Valid_Return() throws Exception {
        when(qualityProfileRepository.save(testModel)).thenReturn(resultModel);

        QualityProfile result = qualityProfileService.qualityProfileCopy(testModel);
        assertThat(result).isNotNull();
        assertEquals(ID, result.getId());
    }


    /**
     * Update QualityProfile  model valid return model.
     *
     * @throws Exception the exception
     */
    @Test
    public void updateQualityProfile_Valid_Return() throws Exception {
        when(qualityProfileRepository.save(testModel)).thenReturn(resultModel);

        QualityProfile result = qualityProfileService.updateQualityProfile(testModel);
        assertThat(result).isNotNull();
        assertEquals(ID, result.getId());
    }


    /**
     * Delete QualityProfile  model valid return String.
     *
     * @throws Exception the exception
     */
    @Test
    public void deleteQualityProfile_Valid_ReturnString() throws Exception {
        doNothing().when(qualityProfileRepository).delete(testModel.getId());

        String resultString = qualityProfileService.deleteQualityProfile(testModel);
        assertThat(resultString).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, resultString);
    }

}
