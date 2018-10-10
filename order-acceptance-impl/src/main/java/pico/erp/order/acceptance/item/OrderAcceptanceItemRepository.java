package pico.erp.order.acceptance.item;

import java.util.Optional;
import java.util.stream.Stream;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Repository;
import pico.erp.item.ItemId;
import pico.erp.order.acceptance.data.OrderAcceptanceId;
import pico.erp.order.acceptance.item.data.OrderAcceptanceItemId;

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
