package paasta.delivery.pipeline.common.api.domain.common.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Dojun on 2017-06-13.
 */

@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    /**
     * Instantiates a new File controller.
     *
     * @param fileService the File service
     */
    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }


    /**
     * 파일 업로드
     *
     * @param fileInfo
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public FileInfo createFileInfo(@RequestBody FileInfo fileInfo) {
        return fileService.createFileInfo(fileInfo);
    }


    /**
     * 파일 상세 조회
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    public FileInfo getFileInfo(@PathVariable("id") long id) {

        return fileService.getFileInfo(id);
    }

    /**
     * 파일 목록 조회
     *
     * @return the file list
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<FileInfo> getFileInfoList() {
        // NOT USE
        return fileService.getFileInfoList();
    }


    /**
     * 파일 다운로드
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/download/{id:.+}", method = RequestMethod.POST)
    public FileInfo downloadFile(@PathVariable("id") long id) {
        FileInfo downloadFile = fileService.getFileInfo(id);
        return downloadFile;
    }


    /**
     * 파일 삭제
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    public String deleteFile(@PathVariable("id") long id) {
        return fileService.deleteFile(id);
    }

}
