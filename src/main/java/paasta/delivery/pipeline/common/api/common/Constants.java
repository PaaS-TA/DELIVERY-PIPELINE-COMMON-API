package paasta.delivery.pipeline.common.api.common;

/**
 * paastaDeliveryPipelineApi
 * paasta.delivery.pipeline.common.api.common
 *
 * @author REX
 * @version 1.0
 * @since 5 /17/2017
 */
public class Constants {

    public static final String RESULT_STATUS_SUCCESS = "SUCCESS";
    public static final String STRING_DATE_TYPE = "yyyy-MM-dd HH:mm:ss";
    public static final String EMPTY_VALUE = "EMPTY_VALUE";

    /**
     * The constant TARGET_DELIVERY_PIPELINE_API.
     */
    public static final String TARGET_DELIVERY_PIPELINE_API = "deliveryPipelineApi";


    /**
     * The enum Aes 256 type.
     */
    public enum AES256Type {
        /**
         * Encode aes 256 type.
         */
        ENCODE,
        /**
         * Decode aes 256 type.
         */
        DECODE;
    }


    /**
     * The enum Job type.
     */
    public enum JobType {
        /**
         * Build job type.
         */
        BUILD,
        /**
         * Test job type.
         */
        TEST,
        /**
         * Deploy job type.
         */
        DEPLOY;
    }

}
