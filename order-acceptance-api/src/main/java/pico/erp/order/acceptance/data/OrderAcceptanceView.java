package pico.erp.order.acceptance.data;

import java.time.OffsetDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pico.erp.company.data.CompanyId;
import pico.erp.item.data.ItemId;
import pico.erp.project.data.ProjectId;
import pico.erp.shared.data.Auditor;
import pico.erp.user.data.UserId;

@Data
public class OrderAcceptanceView {

  OrderAcceptanceId id;

  String name;

  OffsetDateTime orderedDate;

  OffsetDateTime dueDate;

  CompanyId customerId;

  String customerName;

  UserId managerId;

  String managerName;

  String purchaseOrderNumber;

  OffsetDateTime acceptedDate;

  CompanyId purchaserId;

  String purchaserName;

  CompanyId receiverId;

  String receiverName;

  ProjectId projectId;

  String projectName;

  OrderAcceptanceStatusKind status;

  Auditor createdBy;

  OffsetDateTime createdDate;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Filter {

    String name;

    UserId managerId;

    CompanyId relatedCompanyId;

    ProjectId projectId;

    ItemId itemId;

    Set<OrderAcceptanceStatusKind> statuses;

    OffsetDateTime startDueDate;

    OffsetDateTime endDueDate;

  }

}
