package pico.erp.order.acceptance;

import java.time.LocalDateTime;
import lombok.Data;
import pico.erp.company.CompanyId;
import pico.erp.project.ProjectId;
import pico.erp.shared.data.Address;
import pico.erp.user.UserId;

@Data
public class OrderAcceptanceData {

  OrderAcceptanceId id;

  String name;

  LocalDateTime orderedDate;

  LocalDateTime dueDate;

  CompanyId customerId;

  UserId managerId;

  String purchaseOrderNumber;

  boolean deleted;

  LocalDateTime deletedDate;

  LocalDateTime acceptedDate;

  Address deliveryAddress;

  String deliveryTelephoneNumber;

  String deliveryMobilePhoneNumber;

  CompanyId purchaserId;

  CompanyId receiverId;

  ProjectId projectId;

  OrderAcceptanceStatusKind status;

}
