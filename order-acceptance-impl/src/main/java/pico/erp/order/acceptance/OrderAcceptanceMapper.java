package pico.erp.order.acceptance;

import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import pico.erp.company.CompanyData;
import pico.erp.company.CompanyId;
import pico.erp.company.CompanyService;
import pico.erp.order.acceptance.OrderAcceptanceRequests.AcceptRequest;
import pico.erp.order.acceptance.OrderAcceptanceRequests.CreateRequest;
import pico.erp.order.acceptance.OrderAcceptanceRequests.DeleteRequest;
import pico.erp.order.acceptance.OrderAcceptanceRequests.UpdateRequest;
import pico.erp.project.ProjectData;
import pico.erp.project.ProjectId;
import pico.erp.project.ProjectService;
import pico.erp.user.UserData;
import pico.erp.user.UserId;
import pico.erp.user.UserService;

@Mapper
public abstract class OrderAcceptanceMapper {

  @Lazy
  @Autowired
  private CompanyService companyService;

  @Lazy
  @Autowired
  private UserService userService;

  @Lazy
  @Autowired
  private OrderAcceptanceRepository orderAcceptanceRepository;

  @Lazy
  @Autowired
  private ProjectService projectService;

  protected UserData map(UserId userId) {
    return Optional.ofNullable(userId)
      .map(userService::get)
      .orElse(null);
  }

  protected CompanyData map(CompanyId companyId) {
    return Optional.ofNullable(companyId)
      .map(companyService::get)
      .orElse(null);
  }



  protected ProjectData map(ProjectId projectId) {
    return Optional.ofNullable(projectId)
      .map(projectService::get)
      .orElse(null);
  }

  public OrderAcceptance map(OrderAcceptanceId orderAcceptanceId) {
    return Optional.ofNullable(orderAcceptanceId)
      .map(id -> orderAcceptanceRepository.findBy(id)
        .orElseThrow(OrderAcceptanceExceptions.NotFoundException::new)
      )
      .orElse(null);
  }

  @Mappings({
    @Mapping(target = "customerId", source = "customer.id"),
    @Mapping(target = "managerId", source = "manager.id"),
    @Mapping(target = "purchaserId", source = "purchaser.id"),
    @Mapping(target = "receiverId", source = "receiver.id"),
    @Mapping(target = "projectId", source = "project.id")
  })
  public abstract OrderAcceptanceData map(OrderAcceptance orderAcceptance);



  @Mappings({
    @Mapping(target = "customer", source = "customerId"),
    @Mapping(target = "manager", source = "managerId"),
    @Mapping(target = "purchaser", source = "purchaserId"),
    @Mapping(target = "receiver", source = "receiverId"),
    @Mapping(target = "project", source = "projectId")
  })
  public abstract OrderAcceptanceMessages.CreateRequest map(CreateRequest request);

  @Mappings({
    @Mapping(target = "customer", source = "customerId"),
    @Mapping(target = "manager", source = "managerId"),
    @Mapping(target = "purchaser", source = "purchaserId"),
    @Mapping(target = "receiver", source = "receiverId"),
    @Mapping(target = "project", source = "projectId")
  })
  public abstract OrderAcceptanceMessages.UpdateRequest map(UpdateRequest request);

  public abstract OrderAcceptanceMessages.DeleteRequest map(DeleteRequest request);

  public abstract OrderAcceptanceMessages.AcceptRequest map(AcceptRequest request);

  @Mappings({
    @Mapping(target = "customerId", source = "customer.id"),
    @Mapping(target = "customerName", source = "customer.name"),
    @Mapping(target = "managerId", source = "manager.id"),
    @Mapping(target = "managerName", source = "manager.name"),
    @Mapping(target = "purchaserId", source = "purchaser.id"),
    @Mapping(target = "purchaserName", source = "purchaser.name"),
    @Mapping(target = "receiverId", source = "receiver.id"),
    @Mapping(target = "receiverName", source = "receiver.name"),
    @Mapping(target = "projectId", source = "project.id"),
    @Mapping(target = "projectName", source = "project.name"),
    @Mapping(target = "createdBy", ignore = true),
    @Mapping(target = "createdDate", ignore = true),
    @Mapping(target = "lastModifiedBy", ignore = true),
    @Mapping(target = "lastModifiedDate", ignore = true)
  })
  public abstract OrderAcceptanceEntity jpa(OrderAcceptance data);

  public OrderAcceptance jpa(OrderAcceptanceEntity entity) {
    return OrderAcceptance.builder()
      .id(entity.getId())
      .name(entity.getName())
      .orderedDate(entity.getOrderedDate())
      .dueDate(entity.getDueDate())
      .customer(map(entity.getCustomerId()))
      .manager(map(entity.getManagerId()))
      .purchaseOrderNumber(entity.getPurchaseOrderNumber())
      .deleted(entity.isDeleted())
      .deletedDate(entity.getDeletedDate())
      .acceptedDate(entity.getAcceptedDate())
      .deliveryAddress(entity.getDeliveryAddress())
      .purchaser(map(entity.getPurchaserId()))
      .receiver(map(entity.getReceiverId()))
      .project(map(entity.getProjectId()))
      .status(entity.getStatus())
      .build();
  }

  public abstract void pass(OrderAcceptanceEntity from, @MappingTarget OrderAcceptanceEntity to);






}


