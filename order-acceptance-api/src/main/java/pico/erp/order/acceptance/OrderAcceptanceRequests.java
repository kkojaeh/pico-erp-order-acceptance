package pico.erp.order.acceptance;

import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pico.erp.company.CompanyId;
import pico.erp.project.ProjectId;
import pico.erp.shared.TypeDefinitions;
import pico.erp.shared.data.Address;
import pico.erp.user.UserId;

public interface OrderAcceptanceRequests {

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  class CreateRequest {

    @Valid
    @NotNull
    OrderAcceptanceId id;

    @Size(min = 1, max = TypeDefinitions.NAME_LENGTH)
    @NotNull
    String name;

    @Past
    @NotNull
    OffsetDateTime orderedDate;

    @Future
    @NotNull
    OffsetDateTime dueDate;

    @NotNull
    CompanyId customerId;

    @NotNull
    UserId managerId;

    @Size(max = TypeDefinitions.CODE_LENGTH)
    String purchaseOrderNumber;

    @Valid
    @NotNull
    Address deliveryAddress;

    @NotNull
    CompanyId purchaserId;

    @NotNull
    CompanyId receiverId;

    @NotNull
    ProjectId projectId;

    @Size(max = TypeDefinitions.PHONE_NUMBER_LENGTH)
    String deliveryTelephoneNumber;

    @Size(max = TypeDefinitions.PHONE_NUMBER_LENGTH)
    String deliveryMobilePhoneNumber;

  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  class UpdateRequest {

    @Valid
    @NotNull
    OrderAcceptanceId id;

    @Size(min = 1, max = TypeDefinitions.NAME_LENGTH)
    @NotNull
    String name;

    @Past
    @NotNull
    OffsetDateTime orderedDate;

    @Future
    @NotNull
    OffsetDateTime dueDate;

    @NotNull
    CompanyId customerId;

    @NotNull
    UserId managerId;

    @Size(max = TypeDefinitions.CODE_LENGTH)
    String purchaseOrderNumber;

    @Valid
    @NotNull
    Address deliveryAddress;

    @NotNull
    CompanyId purchaserId;

    @NotNull
    CompanyId receiverId;

    @NotNull
    ProjectId projectId;

    @Size(max = TypeDefinitions.PHONE_NUMBER_LENGTH)
    String deliveryTelephoneNumber;

    @Size(max = TypeDefinitions.PHONE_NUMBER_LENGTH)
    String deliveryMobilePhoneNumber;

  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  class DeleteRequest {

    OrderAcceptanceId id;

  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  class AcceptRequest {

    OrderAcceptanceId id;

  }

}
