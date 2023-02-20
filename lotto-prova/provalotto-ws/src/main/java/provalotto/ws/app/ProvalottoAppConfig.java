package provalotto.ws.app;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableBatchProcessing
@PropertySource(value = { "classpath:application.properties" })
@EntityScan("provalotto.bean")
@EnableJpaRepositories("provalotto.datalayer.dao")
@ComponentScan(basePackages = "provalotto")
public class ProvalottoAppConfig {

}