package pico.erp.order.acceptance;

import java.time.LocalDateTime;
import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.Value;
import pico.erp.company.CompanyData;
import pico.erp.project.ProjectData;
import pico.erp.shared.TypeDefinitions;
import pico.erp.shared.data.Address;
import pico.erp.shared.event.Event;
import pico.erp.user.UserData;

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
    LocalDateTime orderedDate;

    @Future
    @NotNull
    LocalDateTime dueDate;

    @NotNull
    CompanyData customer;

    @NotNull
    UserData manager;

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
    CompanyData purchaser;

    @NotNull
    CompanyData receiver;

    @NotNull
    ProjectData project;


    @NotNull
    OrderAcceptanceCodeGenerator codeGenerator;

  }

  @Data
  class UpdateRequest {

    @Size(min = 1, max = TypeDefinitions.NAME_LENGTH)
    @NotNull
    String name;

    @Future
    @NotNull
    LocalDateTime dueDate;

    @NotNull
    CompanyData customer;

    @NotNull
    UserData manager;

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
    CompanyData purchaser;

    @NotNull
    CompanyData receiver;

    @NotNull
    ProjectData project;

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
