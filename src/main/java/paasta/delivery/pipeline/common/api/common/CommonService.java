package paasta.delivery.pipeline.common.api.common;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * paastaDeliveryPipelineApi
 * paasta.delivery.pipeline.common.api.common
 *
 * @author REX
 * @version 1.0
 * @since 7 /7/2017
 */
@Service
public class CommonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonService.class);


    /**
     * Sets page info.
     *
     * @param reqPage   the req page
     * @param reqObject the req object
     * @return the page info
     */
    public Object setPageInfo(Page reqPage, Object reqObject) {
        Object resultObject = null;

        try {
            Class<?> aClass;

            if (reqObject instanceof Class) {
                aClass = (Class<?>) reqObject;
                resultObject = ((Class) reqObject).newInstance();
            } else {
                aClass = reqObject.getClass();
                resultObject = reqObject;
            }

            Method methodSetPage = aClass.getMethod("setPage", Integer.TYPE);
            Method methodSetSize = aClass.getMethod("setSize", Integer.TYPE);
            Method methodSetTotalPages = aClass.getMethod("setTotalPages", Integer.TYPE);
            Method methodSetTotalElements = aClass.getMethod("setTotalElements", Long.TYPE);
            Method methodSetLast = aClass.getMethod("setLast", Boolean.TYPE);

            methodSetPage.invoke(resultObject, reqPage.getNumber());
            methodSetSize.invoke(resultObject, reqPage.getSize());
            methodSetTotalPages.invoke(resultObject, reqPage.getTotalPages());
            methodSetTotalElements.invoke(resultObject, reqPage.getTotalElements());
            methodSetLast.invoke(resultObject, reqPage.isLast());

        } catch (NoSuchMethodException e) {
            LOGGER.error("NoSuchMethodException :: {}", e);
        } catch (IllegalAccessException e1) {
            LOGGER.error("IllegalAccessException :: {}", e1);
        } catch (InvocationTargetException e2) {
            LOGGER.error("InvocationTargetException :: {}", e2);
        } catch (InstantiationException e3) {
            LOGGER.error("InstantiationException :: {}", e3);
        }

        return resultObject;
    }


    /**
     * Sets password by aes 256.
     *
     * @param reqProcess the req process
     * @param reqString  the req string
     * @return the password by aes 256
     */
    public String setPasswordByAES256(Enum reqProcess, String reqString) {
        String resultString = "";

        try {
            AES256Util aes256 = new AES256Util();
            URLCodec codec = new URLCodec();
            resultString = reqString;

            // ENCODE
            if (reqProcess.equals(Constants.AES256Type.ENCODE)) {
                resultString = codec.encode(aes256.aesEncode(reqString));
            }

            // DECODE
            if (reqProcess.equals(Constants.AES256Type.DECODE)) {
                resultString = aes256.aesDecode(codec.decode(reqString));
            }

        } catch (EncoderException e) {
            LOGGER.error("Exception :: EncoderException :: {}", e);
        } catch (DecoderException e) {
            LOGGER.error("Exception :: DecoderException :: {}", e);
        }

        return resultString;
    }

}
