package pico.erp.order.acceptance;

import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import pico.erp.audit.data.AuditConfiguration;
import pico.erp.shared.ApplicationStarter;
import pico.erp.shared.Public;
import pico.erp.shared.SpringBootConfigs;
import pico.erp.shared.data.Contact;
import pico.erp.shared.data.Role;
import pico.erp.shared.impl.ApplicationImpl;

@Slf4j
@SpringBootConfigs
public class OrderAcceptanceApplication implements ApplicationStarter {

  public static final String CONFIG_NAME = "order-acceptance/application";

  public static final String CONFIG_NAME_PROPERTY = "spring.config.name=order-acceptance/application";

  public static final Properties DEFAULT_PROPERTIES = new Properties();

  static {
    DEFAULT_PROPERTIES.put("spring.config.name", CONFIG_NAME);
  }

  public static SpringApplication application() {
    return new SpringApplicationBuilder(OrderAcceptanceApplication.class)
      .properties(DEFAULT_PROPERTIES)
      .web(false)
      .build();
  }

  public static void main(String[] args) {
    application().run(args);
  }

  @Override
  public boolean isWeb() {
    return false;
  }

  @Bean
  @Public
  public AuditConfiguration auditConfiguration() {
    return AuditConfiguration.builder()
      .packageToScan("pico.erp.orderacceptance")
      .entity(pico.erp.project.ROLE.class)
      .valueObject(Contact.class)
      .build();
  }

  @Bean
  @Public
  public Role orderAcceptanceAccessorRole() {
    return ROLE.ORDER_ACCEPTANCE_ACCESSOR;
  }

  @Bean
  @Public
  public Role orderAcceptanceManagerRole() {
    return ROLE.ORDER_ACCEPTANCE_MANAGER;
  }

  @Override
  public pico.erp.shared.Application start(String... args) {
    return new ApplicationImpl(application().run(args));
  }


}
