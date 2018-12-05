package pico.erp.order.acceptance;

import java.time.OffsetDateTime;
import java.util.LinkedList;
import java.util.List;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import pico.erp.shared.ApplicationInitializer;

//@Transactional
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
@Profile({"!development", "!production"})
public class TestDataInitializer implements ApplicationInitializer {

  @Lazy
  @Autowired
  private OrderAcceptanceService orderAcceptanceService;

  @Autowired
  private DataProperties dataProperties;

  public static void main(String... args) {
    System.out.println(OffsetDateTime.now());
  }

  @Override
  @SneakyThrows
  public void initialize() {
    dataProperties.orderAcceptances.forEach(orderAcceptanceService::create);
  }

  @Data
  @Configuration
  @ConfigurationProperties("data")
  public static class DataProperties {

    List<OrderAcceptanceRequests.CreateRequest> orderAcceptances = new LinkedList<>();

  }

}
