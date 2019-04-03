package pico.erp.order.acceptance;

import java.time.LocalDateTime;
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

  LocalDateTime orderedDate;

  LocalDateTime dueDate;

  CompanyId customerId;

  UserId managerId;

  String purchaseOrderNumber;

  LocalDateTime acceptedDate;

  CompanyId purchaserId;

  CompanyId receiverId;

  ProjectId projectId;

  OrderAcceptanceStatusKind status;

  Auditor createdBy;

  LocalDateTime createdDate;

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

    LocalDateTime startDueDate;

    LocalDateTime endDueDate;

  }

}
