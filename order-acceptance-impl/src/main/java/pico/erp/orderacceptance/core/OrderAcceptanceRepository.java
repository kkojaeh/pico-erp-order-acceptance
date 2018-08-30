package pico.erp.orderacceptance.core;

import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Repository;
import pico.erp.orderacceptance.data.OrderAcceptanceId;
import pico.erp.orderacceptance.domain.OrderAcceptance;

@Repository
public interface OrderAcceptanceRepository {

  OrderAcceptance create(@NotNull OrderAcceptance orderAcceptance);

  void deleteBy(@NotNull OrderAcceptanceId id);

  boolean exists(@NotNull OrderAcceptanceId id);

  Optional<OrderAcceptance> findBy(@NotNull OrderAcceptanceId id);

  void update(@NotNull OrderAcceptance orderAcceptance);

}
