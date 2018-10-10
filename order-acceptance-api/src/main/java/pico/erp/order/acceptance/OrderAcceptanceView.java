package pico.erp.order.acceptance;

import java.time.OffsetDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pico.erp.company.CompanyId;
import pico.erp.item.ItemId;
import pico.erp.project.ProjectId;
import pico.erp.shared.data.Auditor;
import pico.erp.user.UserId;

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
