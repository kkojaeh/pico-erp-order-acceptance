package pico.erp.order.acceptance;

import kkojaeh.spring.boot.component.Give;
import kkojaeh.spring.boot.component.SpringBootComponent;
import kkojaeh.spring.boot.component.SpringBootComponentBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pico.erp.order.acceptance.OrderAcceptanceApi.Roles;
import pico.erp.shared.SharedConfiguration;
import pico.erp.shared.data.Role;

@Slf4j
@SpringBootComponent("order-acceptance")
@EntityScan
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableJpaRepositories
@EnableJpaAuditing(auditorAwareRef = "auditorAware", dateTimeProviderRef = "dateTimeProvider")
@SpringBootApplication
@Import(value = {
  SharedConfiguration.class
})
public class OrderAcceptanceApplication {

  public static void main(String[] args) {
    new SpringBootComponentBuilder()
      .component(OrderAcceptanceApplication.class)
      .run(args);
  }

  @Bean
  @Give
  public Role orderAcceptanceAccessorRole() {
    return Roles.ORDER_ACCEPTANCE_ACCESSOR;
  }

  @Bean
  @Give
  public Role orderAcceptanceManagerRole() {
    return Roles.ORDER_ACCEPTANCE_MANAGER;
  }

}
