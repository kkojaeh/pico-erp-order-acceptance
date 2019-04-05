package pico.erp.order.acceptance;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Arrays;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import pico.erp.company.CompanyData;
import pico.erp.order.acceptance.OrderAcceptanceExceptions.CannotAcceptException;
import pico.erp.order.acceptance.OrderAcceptanceExceptions.CannotUpdateException;
import pico.erp.project.ProjectData;
import pico.erp.shared.data.Address;
import pico.erp.user.UserData;

/**
 * 주문 접수
 */
@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderAcceptance implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  OrderAcceptanceId id;

  OrderAcceptanceCode code;

  String name;

  OffsetDateTime orderedDate;

  OffsetDateTime dueDate;

  CompanyData customer;

  UserData manager;

  String purchaseOrderNumber;

  boolean deleted;

  OffsetDateTime deletedDate;

  OffsetDateTime acceptedDate;

  Address deliveryAddress;

  String deliveryTelephoneNumber;

  String deliveryMobilePhoneNumber;

  CompanyData purchaser;

  CompanyData receiver;

  ProjectData project;

  OrderAcceptanceStatusKind status;

  public OrderAcceptance() {
    deleted = false;
  }

  public OrderAcceptanceMessages.CreateResponse apply(
    OrderAcceptanceMessages.CreateRequest request) {
    this.id = request.getId();
    this.name = request.getName();
    this.orderedDate = request.getOrderedDate();
    this.status = OrderAcceptanceStatusKind.CREATED;
    this.dueDate = request.getDueDate();
    this.manager = request.getManager();
    this.customer = request.getCustomer();
    this.deliveryAddress = request.getDeliveryAddress();
    this.deliveryMobilePhoneNumber = request.getDeliveryMobilePhoneNumber();
    this.deliveryTelephoneNumber = request.getDeliveryTelephoneNumber();
    this.purchaseOrderNumber = request.getPurchaseOrderNumber();
    this.project = request.getProject();
    this.receiver = request.getReceiver();
    this.purchaser = request.getPurchaser();
    this.code = request.getCodeGenerator().generate(this);
    return new OrderAcceptanceMessages.CreateResponse(
      Arrays.asList(new OrderAcceptanceEvents.CreatedEvent(this.id))
    );
  }

  public OrderAcceptanceMessages.UpdateResponse apply(
    OrderAcceptanceMessages.UpdateRequest request) {
    if (!isUpdatable()) {
      throw new CannotUpdateException();
    }
    this.name = request.getName();
    this.dueDate = request.getDueDate();
    this.manager = request.getManager();
    this.customer = request.getCustomer();
    this.deliveryAddress = request.getDeliveryAddress();
    this.deliveryMobilePhoneNumber = request.getDeliveryMobilePhoneNumber();
    this.deliveryTelephoneNumber = request.getDeliveryTelephoneNumber();
    this.purchaseOrderNumber = request.getPurchaseOrderNumber();
    this.project = request.getProject();
    this.receiver = request.getReceiver();
    this.purchaser = request.getPurchaser();
    return new OrderAcceptanceMessages.UpdateResponse(
      Arrays.asList(new OrderAcceptanceEvents.UpdatedEvent(this.id))
    );
  }

  public OrderAcceptanceMessages.DeleteResponse apply(
    OrderAcceptanceMessages.DeleteRequest request) {
    deleted = true;
    deletedDate = OffsetDateTime.now();
    return new OrderAcceptanceMessages.DeleteResponse(
      Arrays.asList(new OrderAcceptanceEvents.DeletedEvent(this.id))
    );
  }

  public OrderAcceptanceMessages.AcceptResponse apply(
    OrderAcceptanceMessages.AcceptRequest request) {
    if (status != OrderAcceptanceStatusKind.CREATED) {
      throw new CannotAcceptException();
    }
    status = OrderAcceptanceStatusKind.ACCEPTED;
    acceptedDate = OffsetDateTime.now();
    return new OrderAcceptanceMessages.AcceptResponse(
      Arrays.asList(new OrderAcceptanceEvents.AcceptedEvent(this.id))
    );
  }

  public boolean isUpdatable() {
    return status == OrderAcceptanceStatusKind.CREATED;
  }


}
