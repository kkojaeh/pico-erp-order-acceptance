package pico.erp.order.acceptance.item;

import java.math.BigDecimal;
import javax.persistence.Id;
import lombok.Data;
import pico.erp.item.ItemId;
import pico.erp.order.acceptance.OrderAcceptanceId;

@Data
public class OrderAcceptanceItemData {

  @Id
  OrderAcceptanceItemId id;

  OrderAcceptanceId orderAcceptanceId;

  ItemId itemId;

  BigDecimal unitPrice;

  BigDecimal quantity;

  OrderAcceptanceItemStatusKind status;

}
