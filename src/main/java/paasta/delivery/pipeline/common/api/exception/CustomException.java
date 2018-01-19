package paasta.delivery.pipeline.common.api.exception;

/**
 * paastaDeliveryPipelineApi
 * paasta.delivery.pipeline.ui.exception
 *
 * @author REX
 * @version 1.0
 * @since 5 /12/2017
 */
public class CustomException extends RuntimeException {

    /**
     * Instantiates a new Custom exception.
     *
     * @param msg the msg
     */
    public CustomException(String msg) {
        super(msg);
    }


    /**
     * Instantiates a new Custom exception.
     *
     * @param msg the msg
     * @param t   the t
     */
    public CustomException(String msg, Throwable t) {
        super(msg, t);
    }

}
