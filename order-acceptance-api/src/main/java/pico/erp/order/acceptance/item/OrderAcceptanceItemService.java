package pico.erp.order.acceptance.item;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import pico.erp.order.acceptance.data.OrderAcceptanceId;
import pico.erp.order.acceptance.item.data.OrderAcceptanceItemData;
import pico.erp.order.acceptance.item.data.OrderAcceptanceItemId;

public interface OrderAcceptanceItemService {

  OrderAcceptanceItemData create(@Valid @NotNull OrderAcceptanceItemRequests.CreateRequest request);

  void delete(@Valid @NotNull OrderAcceptanceItemRequests.DeleteRequest request);

  boolean exists(@Valid @NotNull OrderAcceptanceItemId id);

  OrderAcceptanceItemData get(@Valid @NotNull OrderAcceptanceItemId id);

  List<OrderAcceptanceItemData> getAll(@Valid @NotNull OrderAcceptanceId orderAcceptanceId);

  void update(@Valid @NotNull OrderAcceptanceItemRequests.UpdateRequest request);

}
