package pico.erp.orderacceptance.core;

import java.util.Optional;
import java.util.stream.Stream;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Repository;
import pico.erp.item.data.ItemId;
import pico.erp.orderacceptance.data.OrderAcceptanceId;
import pico.erp.orderacceptance.data.OrderAcceptanceItemId;
import pico.erp.orderacceptance.domain.OrderAcceptanceItem;

@Repository
public interface OrderAcceptanceItemRepository {

  OrderAcceptanceItem create(@NotNull OrderAcceptanceItem orderAcceptanceItem);

  void deleteBy(@NotNull OrderAcceptanceItemId id);

  boolean exists(@NotNull OrderAcceptanceItemId id);

  boolean exists(@NotNull OrderAcceptanceId orderAcceptanceId, @NotNull ItemId itemId);

  Stream<OrderAcceptanceItem> findAllBy(@NotNull OrderAcceptanceId orderAcceptanceId);

  Optional<OrderAcceptanceItem> findBy(@NotNull OrderAcceptanceItemId id);

  void update(@NotNull OrderAcceptanceItem orderAcceptanceItem);

}
