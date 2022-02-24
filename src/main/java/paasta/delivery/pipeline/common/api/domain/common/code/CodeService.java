package paasta.delivery.pipeline.common.api.domain.common.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import paasta.delivery.pipeline.common.api.common.Constants;

import java.util.List;

/**
 * deliveryPipelineApi
 * paasta.delivery.pipeline.common.api.domain.common.code
 *
 * @author REX
 * @version 1.0
 * @since 11 /6/2017
 */
@Service
public class CodeService {

    private final CodeRepository codeRepository;


    /**
     * Instantiates a new Code service.
     *
     * @param codeRepository the code repository
     */
    @Autowired
    public CodeService(CodeRepository codeRepository) {this.codeRepository = codeRepository;}


    /**
     * Gets code list All.
     *
     * @return the code list
     */
    List<Code> getCodeListAll(Sort sort) {
        return codeRepository.findAll(sort);
    }

    /**
     * Gets code list.
     *
     * @param codeGroup the codeGroup
     * @return the code list
     */
    List<Code> getCodeList(String codeGroup) {
        return codeRepository.findAllByCodeGroupOrderByCodeOrderAsc(codeGroup);
    }


    /**
     * Gets code by id.
     *
     * @param id the id
     * @return the code by id
     */
    Code getCodeById(int id) {
        return codeRepository.findOne((long) id);
    }


    /**
     * Create code code.
     *
     * @param code the code
     * @return the code
     */
    Code createCode(Code code) {
        return codeRepository.save(code);
    }


    /**
     * Update code code.
     *
     * @param code the code
     * @return the code
     */
    Code updateCode(Code code) {
        return codeRepository.save(code);
    }


    /**
     * Delete cf ino by id string.
     *
     * @param id the id
     * @return the string
     */
    String deleteCode(int id) {
        codeRepository.delete((long) id);
        return Constants.RESULT_STATUS_SUCCESS;
    }

}
