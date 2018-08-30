package pico.erp.orderacceptance.domain;

import java.time.OffsetDateTime;
import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.Value;
import pico.erp.company.data.CompanyData;
import pico.erp.orderacceptance.data.OrderAcceptanceId;
import pico.erp.project.data.ProjectData;
import pico.erp.shared.TypeDefinitions;
import pico.erp.shared.data.Address;
import pico.erp.shared.event.Event;
import pico.erp.user.data.UserData;

public interface OrderAcceptanceMessages {

  @Data
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
    CompanyData customerData;

    @NotNull
    UserData managerData;

    @Size(max = TypeDefinitions.CODE_LENGTH)
    String purchaseOrderNumber;

    @Valid
    @NotNull
    Address deliveryAddress;

    @Size(max = TypeDefinitions.PHONE_NUMBER_LENGTH)
    String deliveryTelephoneNumber;

    @Size(max = TypeDefinitions.PHONE_NUMBER_LENGTH)
    String deliveryMobilePhoneNumber;

    @NotNull
    CompanyData purchaserData;

    @NotNull
    CompanyData receiverData;

    @NotNull
    ProjectData projectData;

  }

  @Data
  class UpdateRequest {

    @Size(min = 1, max = TypeDefinitions.NAME_LENGTH)
    @NotNull
    String name;

    @Future
    @NotNull
    OffsetDateTime dueDate;

    @NotNull
    CompanyData customerData;

    @NotNull
    UserData managerData;

    @Size(max = TypeDefinitions.CODE_LENGTH)
    String purchaseOrderNumber;

    @Valid
    @NotNull
    Address deliveryAddress;

    @Size(max = TypeDefinitions.PHONE_NUMBER_LENGTH)
    String deliveryTelephoneNumber;

    @Size(max = TypeDefinitions.PHONE_NUMBER_LENGTH)
    String deliveryMobilePhoneNumber;

    @NotNull
    CompanyData purchaserData;

    @NotNull
    CompanyData receiverData;

    @NotNull
    ProjectData projectData;

  }

  @Data
  class DeleteRequest {

  }

  @Value
  class CreateResponse {

    Collection<Event> events;

  }

  @Value
  class UpdateResponse {

    Collection<Event> events;

  }

  @Value
  class DeleteResponse {

    Collection<Event> events;

  }

  @Data
  class AcceptRequest {

  }

  @Value
  class AcceptResponse {

    Collection<Event> events;

  }
}
