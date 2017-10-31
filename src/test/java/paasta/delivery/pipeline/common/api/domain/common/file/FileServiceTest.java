package paasta.delivery.pipeline.common.api.domain.common.file;

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
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created by Mingu on 2017-05-31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceTest.class);

    @InjectMocks
    private FileService fileService;

    @Mock
    private FileInfoRepository fileInfoRepository;

    private long PARAM = 12345678;

    private FileInfo testfileInfo;
    private List<FileInfo> testfileInfoList;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        fileService = new FileService(fileInfoRepository);

        testfileInfo = new FileInfo();
        testfileInfo.setId(PARAM);
        testfileInfo.setFileUrl("fileUrl");
        testfileInfo.setOriginalFileName("originalFileName");
        testfileInfo.setStoredFileName("StoredFileName");
        testfileInfo.setCreated(new Date());

        testfileInfoList = getFileInfoList();
    }

    private List<FileInfo> getFileInfoList() {
        List<FileInfo> fileInfos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            FileInfo returnValue = new FileInfo();
            returnValue.setId(PARAM+i);
            returnValue.setFileUrl("fileUrl_"+i);
            returnValue.setOriginalFileName("originalFileName_"+i);
            returnValue.setStoredFileName("StoredFileName_"+i);
            returnValue.setCreated(new Date());
            fileInfos.add(returnValue);
        }
        return fileInfos;
    }


    @Test
    public void test_getFileInfo() {

        when(fileInfoRepository.findById(anyLong())).thenReturn(testfileInfo);

        FileInfo result = fileService.getFileInfo(PARAM);

        assertEquals(testfileInfo.getId(), result.getId());
        assertEquals(testfileInfo.getFileUrl(), result.getFileUrl());
        assertEquals(testfileInfo.getOriginalFileName(), result.getOriginalFileName());
        assertEquals(testfileInfo.getStoredFileName(), result.getStoredFileName());
        assertEquals(testfileInfo.getCreated(), result.getCreated());

    }

    @Test
    public void test_getFileInfoList() {
        when(fileInfoRepository.findAll()).thenReturn(testfileInfoList);

        List<FileInfo> result = fileService.getFileInfoList();

        assertEquals(testfileInfoList.get(0).getId(), result.get(0).getId());
        assertEquals(testfileInfoList.get(0).getFileUrl(), result.get(0).getFileUrl());
        assertEquals(testfileInfoList.get(0).getOriginalFileName(), result.get(0).getOriginalFileName());
        assertEquals(testfileInfoList.get(0).getStoredFileName(), result.get(0).getStoredFileName());
        assertEquals(testfileInfoList.get(0).getCreated(), result.get(0).getCreated());
    }

    @Test
    public void test_createFileInfo() {
        when(fileInfoRepository.save(testfileInfo)).thenReturn(testfileInfo);
        FileInfo result = fileService.createFileInfo(testfileInfo);
        assertThat(result).isNotNull();
        assertEquals(testfileInfo.getId(), result.getId());
    }

    @Test
    public void test_deleteFile() {
        doNothing().when(fileInfoRepository).delete(PARAM);
        String result = fileService.deleteFile(PARAM);
        assertThat(result).isNotNull();
        assertEquals(Constants.RESULT_STATUS_SUCCESS, result);
    }

}
