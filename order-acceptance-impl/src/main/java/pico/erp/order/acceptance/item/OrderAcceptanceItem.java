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
import pico.erp.item.ItemData;
import pico.erp.order.acceptance.OrderAcceptance;
import pico.erp.order.acceptance.OrderAcceptanceExceptions.CannotModifyException;
import pico.erp.order.acceptance.item.OrderAcceptanceItemEvents.CreatedEvent;
import pico.erp.order.acceptance.item.OrderAcceptanceItemEvents.DeletedEvent;
import pico.erp.order.acceptance.item.OrderAcceptanceItemEvents.UpdatedEvent;

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

  ItemData item;

  BigDecimal unitPrice;

  BigDecimal quantity;

  OrderAcceptanceItemStatusKind status;

  public OrderAcceptanceItemMessages.CreateResponse apply(
    OrderAcceptanceItemMessages.CreateRequest request) {
    this.id = request.getId();
    this.orderAcceptance = request.getOrderAcceptance();
    this.item = request.getItem();
    this.status = OrderAcceptanceItemStatusKind.CREATED;
    this.unitPrice = request.getUnitPrice();
    this.quantity = request.getQuantity();
    if (!orderAcceptance.isUpdatable()) {
      throw new CannotModifyException();
    }
    return new OrderAcceptanceItemMessages.CreateResponse(
      Arrays.asList(new CreatedEvent(this.id))
    );
  }

  public OrderAcceptanceItemMessages.UpdateResponse apply(
    OrderAcceptanceItemMessages.UpdateRequest request) {
    if (!orderAcceptance.isUpdatable()) {
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
