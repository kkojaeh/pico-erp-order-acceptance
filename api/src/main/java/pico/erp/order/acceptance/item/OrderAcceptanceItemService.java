package pico.erp.order.acceptance.item;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import pico.erp.order.acceptance.OrderAcceptanceId;

public interface OrderAcceptanceItemService {

  OrderAcceptanceItemData create(@Valid @NotNull OrderAcceptanceItemRequests.CreateRequest request);

  void delete(@Valid @NotNull OrderAcceptanceItemRequests.DeleteRequest request);

  boolean exists(@Valid @NotNull OrderAcceptanceItemId id);

  OrderAcceptanceItemData get(@Valid @NotNull OrderAcceptanceItemId id);

  List<OrderAcceptanceItemData> getAll(@Valid @NotNull OrderAcceptanceId orderAcceptanceId);

  void update(@Valid @NotNull OrderAcceptanceItemRequests.UpdateRequest request);

}
