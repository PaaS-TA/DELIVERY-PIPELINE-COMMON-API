package paasta.delivery.pipeline.common.api.domain.common.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * deliveryPipelineApi
 * paasta.delivery.pipeline.common.api.domain.common.code
 *
 * @author REX
 * @version 1.0
 * @since 11 /6/2017
 */
@RestController
public class CodeController {

    private static final String REQ_URL = "/code";
    private final CodeService codeService;


    /**
     * Instantiates a new Code controller.
     *
     * @param codeService the code service
     */
    @Autowired
    public CodeController(CodeService codeService) {this.codeService = codeService;}


    /**
     * Gets code list.
     *
     * @return the code list
     * http://<domain>/code/list?sort=codeOrder,asc&codeName,asc
     */
    @GetMapping(value = REQ_URL + "/list")
    public List<Code> getCodeListAll(Sort sort) {
        return codeService.getCodeListAll(sort);
    }

    /**
     * Gets code list.
     *
     * @param codeGroup the codeGroup
     * @return the code list
     */
    @GetMapping(value = REQ_URL + "/list/{codeGroup:.+}")
    public List<Code> getCodeList(@PathVariable(value = "codeGroup") String codeGroup) {
        return codeService.getCodeList(codeGroup);
    }


    /**
     * Gets code by id.
     *
     * @param id the id
     * @return the code by id
     */
    @GetMapping(value = REQ_URL + "/{id:.+}")
    public Code getCodeById(@PathVariable("id") int id) {
        return codeService.getCodeById(id);
    }


    /**
     * Create code code.
     *
     * @param code the code
     * @return the code
     */
    @PostMapping(value = REQ_URL)
    public Code createCode(@RequestBody Code code) {
        return codeService.createCode(code);
    }


    /**
     * Update code code.
     *
     * @param code the code
     * @return the code
     */
    @PutMapping(value = REQ_URL)
    public Code updateCode(@RequestBody Code code) {
        return codeService.updateCode(code);
    }


    /**
     * Delete cf ino string.
     *
     * @param id the id
     * @return the string
     */
    @DeleteMapping(value = REQ_URL + "/{id:.+}")
    public String deleteCfIno(@PathVariable("id") int id) {
        return codeService.deleteCode(id);
    }

}
