package pico.erp.order.acceptance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pico.erp.order.acceptance.data.OrderAcceptanceId;
import pico.erp.shared.event.Event;

public interface OrderAcceptanceEvents {

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  class CreatedEvent implements Event {

    public final static String CHANNEL = "event.order-acceptance.created";

    private OrderAcceptanceId orderAcceptanceId;

    public String channel() {
      return CHANNEL;
    }

  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  class DeletedEvent implements Event {

    public final static String CHANNEL = "event.order-acceptance.deleted";

    private OrderAcceptanceId orderAcceptanceId;

    public String channel() {
      return CHANNEL;
    }

  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  class UpdatedEvent implements Event {

    public final static String CHANNEL = "event.order-acceptance.updated";

    private OrderAcceptanceId orderAcceptanceId;

    public String channel() {
      return CHANNEL;
    }

  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  class AcceptedEvent implements Event {

    public final static String CHANNEL = "event.order-acceptance.accepted";

    private OrderAcceptanceId orderAcceptanceId;

    public String channel() {
      return CHANNEL;
    }

  }
}
