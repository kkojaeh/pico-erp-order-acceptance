package pico.erp.order.acceptance;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderAcceptanceCodeGeneratorImpl implements OrderAcceptanceCodeGenerator {

  @Autowired
  private OrderAcceptanceRepository orderAcceptanceRepository;

  @Override
  public OrderAcceptanceCode generate(OrderAcceptance orderAcceptance) {
    val now = LocalDateTime.now();
    LocalDateTime begin = now.with(TemporalAdjusters.firstDayOfMonth())
      .with(LocalTime.MIN);
    LocalDateTime end = now.with(TemporalAdjusters.lastDayOfMonth())
      .with(LocalTime.MAX);
    long count = orderAcceptanceRepository.countCreatedBetween(begin, end);
    String value = String
      .format("OA%03d%02d-%04d", now.getYear() % 1000, now.getMonthValue(), count);
    return OrderAcceptanceCode.from(value);
  }

}
