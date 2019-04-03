package pico.erp.order.acceptance;

import java.time.LocalDateTime;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderAcceptanceRepository {

  OrderAcceptance create(@NotNull OrderAcceptance orderAcceptance);

  void deleteBy(@NotNull OrderAcceptanceId id);

  boolean exists(@NotNull OrderAcceptanceId id);

  long countCreatedBetween(LocalDateTime begin, LocalDateTime end);

  Optional<OrderAcceptance> findBy(@NotNull OrderAcceptanceId id);

  void update(@NotNull OrderAcceptance orderAcceptance);

  boolean exists(@NotNull OrderAcceptanceCode code);

}
