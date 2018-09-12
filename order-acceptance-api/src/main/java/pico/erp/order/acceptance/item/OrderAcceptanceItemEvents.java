package pico.erp.order.acceptance.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pico.erp.order.acceptance.item.data.OrderAcceptanceItemId;
import pico.erp.shared.event.Event;

public interface OrderAcceptanceItemEvents {

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  class CreatedEvent implements Event {

    public final static String CHANNEL = "event.order-acceptance-item.created";

    private OrderAcceptanceItemId orderAcceptanceItemId;

    public String channel() {
      return CHANNEL;
    }

  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  class DeletedEvent implements Event {

    public final static String CHANNEL = "event.order-acceptance-item.deleted";

    private OrderAcceptanceItemId orderAcceptanceItemId;

    public String channel() {
      return CHANNEL;
    }

  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  class UpdatedEvent implements Event {

    public final static String CHANNEL = "event.order-acceptance-item.updated";

    private OrderAcceptanceItemId orderAcceptanceItemId;

    public String channel() {
      return CHANNEL;
    }

  }

}
