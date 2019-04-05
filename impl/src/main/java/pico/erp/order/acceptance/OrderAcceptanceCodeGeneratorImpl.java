package pico.erp.order.acceptance;

import java.time.LocalTime;
import java.time.OffsetDateTime;
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
    val now = OffsetDateTime.now();
    OffsetDateTime begin = now.with(TemporalAdjusters.firstDayOfMonth())
      .with(LocalTime.MIN);
    OffsetDateTime end = now.with(TemporalAdjusters.lastDayOfMonth())
      .with(LocalTime.MAX);
    long count = orderAcceptanceRepository.countCreatedBetween(begin, end);
    String value = String
      .format("OA%03d%02d-%04d", now.getYear() % 1000, now.getMonthValue(), count);
    return OrderAcceptanceCode.from(value);
  }

}
