package pico.erp.order.acceptance;

import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import pico.erp.audit.AuditApi;
import pico.erp.audit.AuditConfiguration;
import pico.erp.company.CompanyApi;
import pico.erp.item.ItemApi;
import pico.erp.order.acceptance.OrderAcceptanceApi.Roles;
import pico.erp.project.ProjectApi;
import pico.erp.shared.ApplicationId;
import pico.erp.shared.ApplicationStarter;
import pico.erp.shared.Public;
import pico.erp.shared.SpringBootConfigs;
import pico.erp.shared.data.Contact;
import pico.erp.shared.data.Role;
import pico.erp.shared.impl.ApplicationImpl;
import pico.erp.user.UserApi;

@Slf4j
@SpringBootConfigs
public class OrderAcceptanceApplication implements ApplicationStarter {

  public static final String CONFIG_NAME = "order-acceptance/application";

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

  @Bean
  @Public
  public AuditConfiguration auditConfiguration() {
    return AuditConfiguration.builder()
      .packageToScan("pico.erp.order.acceptance")
      .entity(Roles.class)
      .valueObject(Contact.class)
      .build();
  }

  @Override
  public Set<ApplicationId> getDependencies() {
    return Stream.of(
      UserApi.ID,
      CompanyApi.ID,
      ItemApi.ID,
      AuditApi.ID,
      ProjectApi.ID
    ).collect(Collectors.toSet());
  }

  @Override
  public boolean isWeb() {
    return false;
  }

  @Override
  public ApplicationId getId() {
    return OrderAcceptanceApi.ID;
  }

  @Bean
  @Public
  public Role orderAcceptanceAccessorRole() {
    return Roles.ORDER_ACCEPTANCE_ACCESSOR;
  }

  @Bean
  @Public
  public Role orderAcceptanceManagerRole() {
    return Roles.ORDER_ACCEPTANCE_MANAGER;
  }

  @Override
  public pico.erp.shared.Application start(String... args) {
    return new ApplicationImpl(application().run(args));
  }

}
