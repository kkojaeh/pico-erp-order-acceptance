package pico.erp.orderacceptance.data;

import java.math.BigDecimal;
import javax.persistence.Id;
import lombok.Data;
import pico.erp.item.data.ItemId;

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
