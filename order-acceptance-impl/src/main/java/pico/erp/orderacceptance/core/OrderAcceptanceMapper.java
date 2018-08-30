package pico.erp.orderacceptance.core;

import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import pico.erp.company.CompanyService;
import pico.erp.company.data.CompanyData;
import pico.erp.company.data.CompanyId;
import pico.erp.item.ItemService;
import pico.erp.item.data.ItemData;
import pico.erp.item.data.ItemId;
import pico.erp.orderacceptance.OrderAcceptanceExceptions;
import pico.erp.orderacceptance.OrderAcceptanceItemRequests;
import pico.erp.orderacceptance.OrderAcceptanceRequests;
import pico.erp.orderacceptance.data.OrderAcceptanceData;
import pico.erp.orderacceptance.data.OrderAcceptanceId;
import pico.erp.orderacceptance.data.OrderAcceptanceItemData;
import pico.erp.orderacceptance.domain.OrderAcceptance;
import pico.erp.orderacceptance.domain.OrderAcceptanceItem;
import pico.erp.orderacceptance.domain.OrderAcceptanceItemMessages;
import pico.erp.orderacceptance.domain.OrderAcceptanceMessages;
import pico.erp.project.ProjectService;
import pico.erp.project.data.ProjectData;
import pico.erp.project.data.ProjectId;
import pico.erp.user.UserService;
import pico.erp.user.data.UserData;
import pico.erp.user.data.UserId;

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
  private ItemService itemService;

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

  protected ItemData map(ItemId itemId) {
    return Optional.ofNullable(itemId)
      .map(itemService::get)
      .orElse(null);
  }

  protected ProjectData map(ProjectId projectId) {
    return Optional.ofNullable(projectId)
      .map(projectService::get)
      .orElse(null);
  }

  protected OrderAcceptance map(OrderAcceptanceId orderAcceptanceId) {
    return Optional.ofNullable(orderAcceptanceId)
      .map(id -> orderAcceptanceRepository.findBy(id)
        .orElseThrow(OrderAcceptanceExceptions.NotFoundException::new)
      )
      .orElse(null);
  }

  @Mappings({
    @Mapping(target = "customerId", source = "customerData.id"),
    @Mapping(target = "managerId", source = "managerData.id"),
    @Mapping(target = "purchaserId", source = "purchaserData.id"),
    @Mapping(target = "receiverId", source = "receiverData.id"),
    @Mapping(target = "projectId", source = "projectData.id")
  })
  abstract OrderAcceptanceData map(OrderAcceptance orderAcceptance);

  @Mappings({
    @Mapping(target = "orderAcceptanceId", source = "orderAcceptance.id"),
    @Mapping(target = "itemId", source = "itemData.id")
  })
  abstract OrderAcceptanceItemData map(OrderAcceptanceItem orderAcceptanceItem);

  @Mappings({
    @Mapping(target = "customerData", source = "customerId"),
    @Mapping(target = "managerData", source = "managerId"),
    @Mapping(target = "purchaserData", source = "purchaserId"),
    @Mapping(target = "receiverData", source = "receiverId"),
    @Mapping(target = "projectData", source = "projectId")
  })
  abstract OrderAcceptanceMessages.CreateRequest map(OrderAcceptanceRequests.CreateRequest request);

  @Mappings({
    @Mapping(target = "customerData", source = "customerId"),
    @Mapping(target = "managerData", source = "managerId"),
    @Mapping(target = "purchaserData", source = "purchaserId"),
    @Mapping(target = "receiverData", source = "receiverId"),
    @Mapping(target = "projectData", source = "projectId")
  })
  abstract OrderAcceptanceMessages.UpdateRequest map(OrderAcceptanceRequests.UpdateRequest request);

  abstract OrderAcceptanceMessages.DeleteRequest map(OrderAcceptanceRequests.DeleteRequest request);

  abstract OrderAcceptanceMessages.AcceptRequest map(OrderAcceptanceRequests.AcceptRequest request);


  @Mappings({
    @Mapping(target = "orderAcceptance", source = "orderAcceptanceId"),
    @Mapping(target = "itemData", source = "itemId")
  })
  abstract OrderAcceptanceItemMessages.CreateRequest map(
    OrderAcceptanceItemRequests.CreateRequest request);

  @Mappings({
  })
  abstract OrderAcceptanceItemMessages.UpdateRequest map(
    OrderAcceptanceItemRequests.UpdateRequest request);

  abstract OrderAcceptanceItemMessages.DeleteRequest map(
    OrderAcceptanceItemRequests.DeleteRequest request);


}


