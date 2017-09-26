package paasta.delivery.pipeline.common.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * paastaDeliveryPipelineApi
 * paasta.delivery.pipeline.common.api.config
 *
 * @author REX
 * @version 1.0
 * @since 9/19/2017
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    /**
     * Transaction manager platform transaction manager.
     *
     * @param dataSource the data source
     * @return the platform transaction manager
     */
    @Bean
    PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}


