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
import pico.erp.audit.annotation.Audit;
import pico.erp.company.data.CompanyData;
import pico.erp.order.acceptance.OrderAcceptanceEvents.AcceptedEvent;
import pico.erp.order.acceptance.OrderAcceptanceEvents.CreatedEvent;
import pico.erp.order.acceptance.OrderAcceptanceEvents.DeletedEvent;
import pico.erp.order.acceptance.OrderAcceptanceEvents.UpdatedEvent;
import pico.erp.order.acceptance.OrderAcceptanceExceptions.CannotAcceptException;
import pico.erp.order.acceptance.OrderAcceptanceExceptions.CannotModifyException;
import pico.erp.order.acceptance.data.OrderAcceptanceId;
import pico.erp.order.acceptance.data.OrderAcceptanceStatusKind;
import pico.erp.project.data.ProjectData;
import pico.erp.shared.data.Address;
import pico.erp.user.data.UserData;

/**
 * 주문 접수
 */
@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Audit(alias = "order-acceptance")
public class OrderAcceptance implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  OrderAcceptanceId id;

  String name;

  OffsetDateTime orderedDate;

  OffsetDateTime dueDate;

  CompanyData customerData;

  UserData managerData;

  String purchaseOrderNumber;

  boolean deleted;

  OffsetDateTime deletedDate;

  OffsetDateTime acceptedDate;

  Address deliveryAddress;

  String deliveryTelephoneNumber;

  String deliveryMobilePhoneNumber;

  CompanyData purchaserData;

  CompanyData receiverData;

  ProjectData projectData;

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
    this.managerData = request.getManagerData();
    this.customerData = request.getCustomerData();
    this.deliveryAddress = request.getDeliveryAddress();
    this.deliveryMobilePhoneNumber = request.getDeliveryMobilePhoneNumber();
    this.deliveryTelephoneNumber = request.getDeliveryTelephoneNumber();
    this.purchaseOrderNumber = request.getPurchaseOrderNumber();
    this.projectData = request.getProjectData();
    this.receiverData = request.getReceiverData();
    this.purchaserData = request.getPurchaserData();
    return new OrderAcceptanceMessages.CreateResponse(
      Arrays.asList(new CreatedEvent(this.id))
    );
  }

  public OrderAcceptanceMessages.UpdateResponse apply(
    OrderAcceptanceMessages.UpdateRequest request) {
    if (!canModify()) {
      throw new CannotModifyException();
    }
    this.name = request.getName();
    this.dueDate = request.getDueDate();
    this.managerData = request.getManagerData();
    this.customerData = request.getCustomerData();
    this.deliveryAddress = request.getDeliveryAddress();
    this.deliveryMobilePhoneNumber = request.getDeliveryMobilePhoneNumber();
    this.deliveryTelephoneNumber = request.getDeliveryTelephoneNumber();
    this.purchaseOrderNumber = request.getPurchaseOrderNumber();
    this.projectData = request.getProjectData();
    this.receiverData = request.getReceiverData();
    this.purchaserData = request.getPurchaserData();
    return new OrderAcceptanceMessages.UpdateResponse(
      Arrays.asList(new UpdatedEvent(this.id))
    );
  }

  public OrderAcceptanceMessages.DeleteResponse apply(
    OrderAcceptanceMessages.DeleteRequest request) {
    deleted = true;
    deletedDate = OffsetDateTime.now();
    return new OrderAcceptanceMessages.DeleteResponse(
      Arrays.asList(new DeletedEvent(this.id))
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
      Arrays.asList(new AcceptedEvent(this.id))
    );
  }

  public boolean canModify() {
    return status == OrderAcceptanceStatusKind.CREATED;
  }


}
