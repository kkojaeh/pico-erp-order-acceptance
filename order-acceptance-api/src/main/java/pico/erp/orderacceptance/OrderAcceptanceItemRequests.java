package pico.erp.orderacceptance;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pico.erp.item.data.ItemId;
import pico.erp.orderacceptance.data.OrderAcceptanceId;
import pico.erp.orderacceptance.data.OrderAcceptanceItemId;

public interface OrderAcceptanceItemRequests {

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  class CreateRequest {

    OrderAcceptanceItemId id;

    OrderAcceptanceId orderAcceptanceId;

    ItemId itemId;

    BigDecimal unitPrice;

    BigDecimal quantity;

  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  class UpdateRequest {

    OrderAcceptanceItemId id;

    BigDecimal unitPrice;

    BigDecimal quantity;

  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  class DeleteRequest {

    OrderAcceptanceItemId id;

  }

}
