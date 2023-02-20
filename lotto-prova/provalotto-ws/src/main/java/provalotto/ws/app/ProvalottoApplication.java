package provalotto.ws.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ProvalottoApplication extends SpringBootServletInitializer {

	public static void main(final String[] args) {
		SpringApplication.run(ProvalottoApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
		return builder.sources(ProvalottoAppConfig.class);
	}

}