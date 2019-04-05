package pico.erp.order.acceptance;

import java.time.OffsetDateTime;
import lombok.Data;
import pico.erp.company.CompanyId;
import pico.erp.project.ProjectId;
import pico.erp.shared.data.Address;
import pico.erp.user.UserId;

@Data
public class OrderAcceptanceData {

  OrderAcceptanceId id;

  String name;

  OffsetDateTime orderedDate;

  OffsetDateTime dueDate;

  CompanyId customerId;

  UserId managerId;

  String purchaseOrderNumber;

  boolean deleted;

  OffsetDateTime deletedDate;

  OffsetDateTime acceptedDate;

  Address deliveryAddress;

  String deliveryTelephoneNumber;

  String deliveryMobilePhoneNumber;

  CompanyId purchaserId;

  CompanyId receiverId;

  ProjectId projectId;

  OrderAcceptanceStatusKind status;

}
