package pico.erp.orderacceptance.data;

import java.time.OffsetDateTime;
import lombok.Data;
import pico.erp.company.data.CompanyId;
import pico.erp.project.data.ProjectId;
import pico.erp.shared.data.Address;
import pico.erp.user.data.UserId;

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
