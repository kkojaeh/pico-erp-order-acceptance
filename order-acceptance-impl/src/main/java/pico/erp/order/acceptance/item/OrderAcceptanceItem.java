package pico.erp.order.acceptance.item;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import pico.erp.audit.annotation.Audit;
import pico.erp.item.data.ItemData;
import pico.erp.order.acceptance.OrderAcceptance;
import pico.erp.order.acceptance.OrderAcceptanceExceptions.CannotModifyException;
import pico.erp.order.acceptance.item.OrderAcceptanceItemEvents.CreatedEvent;
import pico.erp.order.acceptance.item.OrderAcceptanceItemEvents.DeletedEvent;
import pico.erp.order.acceptance.item.OrderAcceptanceItemEvents.UpdatedEvent;
import pico.erp.order.acceptance.item.data.OrderAcceptanceItemId;
import pico.erp.order.acceptance.item.data.OrderAcceptanceItemStatusKind;

/**
 * 주문 접수
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Audit(alias = "order-acceptance-item")
public class OrderAcceptanceItem implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  OrderAcceptanceItemId id;

  OrderAcceptance orderAcceptance;

  ItemData itemData;

  BigDecimal unitPrice;

  BigDecimal quantity;

  OrderAcceptanceItemStatusKind status;

  public OrderAcceptanceItemMessages.CreateResponse apply(
    OrderAcceptanceItemMessages.CreateRequest request) {
    this.id = request.getId();
    this.orderAcceptance = request.getOrderAcceptance();
    this.itemData = request.getItemData();
    this.status = OrderAcceptanceItemStatusKind.CREATED;
    this.unitPrice = request.getUnitPrice();
    this.quantity = request.getQuantity();
    if (!orderAcceptance.canModify()) {
      throw new CannotModifyException();
    }
    return new OrderAcceptanceItemMessages.CreateResponse(
      Arrays.asList(new CreatedEvent(this.id))
    );
  }

  public OrderAcceptanceItemMessages.UpdateResponse apply(
    OrderAcceptanceItemMessages.UpdateRequest request) {
    if (!orderAcceptance.canModify()) {
      throw new OrderAcceptanceItemExceptions.CannotModifyException();
    }
    this.unitPrice = request.getUnitPrice();
    this.quantity = request.getQuantity();
    return new OrderAcceptanceItemMessages.UpdateResponse(
      Arrays.asList(new UpdatedEvent(this.id))
    );
  }

  public OrderAcceptanceItemMessages.DeleteResponse apply(
    OrderAcceptanceItemMessages.DeleteRequest request) {
    return new OrderAcceptanceItemMessages.DeleteResponse(
      Arrays.asList(new DeletedEvent(this.id))
    );
  }

}
