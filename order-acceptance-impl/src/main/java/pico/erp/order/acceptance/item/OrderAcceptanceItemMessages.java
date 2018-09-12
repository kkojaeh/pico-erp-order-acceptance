package pico.erp.order.acceptance.item;

import java.math.BigDecimal;
import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;
import pico.erp.item.data.ItemData;
import pico.erp.order.acceptance.OrderAcceptance;
import pico.erp.order.acceptance.item.data.OrderAcceptanceItemId;
import pico.erp.shared.event.Event;

public interface OrderAcceptanceItemMessages {

  @Data
  class CreateRequest {

    @Valid
    @NotNull
    OrderAcceptanceItemId id;

    @NotNull
    OrderAcceptance orderAcceptance;

    @NotNull
    ItemData itemData;

    @NotNull
    BigDecimal unitPrice;

    @NotNull
    @Min(1)
    BigDecimal quantity;

  }

  @Data
  class UpdateRequest {

    @NotNull
    BigDecimal unitPrice;

    @NotNull
    @Min(1)
    BigDecimal quantity;

  }

  @Data
  class DeleteRequest {

  }

  @Value
  class CreateResponse {

    Collection<Event> events;

  }

  @Value
  class UpdateResponse {

    Collection<Event> events;

  }

  @Value
  class DeleteResponse {

    Collection<Event> events;

  }

  @Data
  class AcceptRequest {

  }

  @Value
  class AcceptResponse {

    Collection<Event> events;

  }
}
