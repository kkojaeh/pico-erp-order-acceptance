package pico.erp.order.acceptance;

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
import pico.erp.order.acceptance.OrderAcceptanceRequests.AcceptRequest;
import pico.erp.order.acceptance.OrderAcceptanceRequests.CreateRequest;
import pico.erp.order.acceptance.OrderAcceptanceRequests.DeleteRequest;
import pico.erp.order.acceptance.OrderAcceptanceRequests.UpdateRequest;
import pico.erp.order.acceptance.data.OrderAcceptanceData;
import pico.erp.order.acceptance.data.OrderAcceptanceId;
import pico.erp.order.acceptance.item.OrderAcceptanceItem;
import pico.erp.order.acceptance.item.OrderAcceptanceItemMessages;
import pico.erp.order.acceptance.item.OrderAcceptanceItemRequests;
import pico.erp.order.acceptance.item.data.OrderAcceptanceItemData;
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
  public abstract OrderAcceptanceData map(OrderAcceptance orderAcceptance);

  @Mappings({
    @Mapping(target = "orderAcceptanceId", source = "orderAcceptance.id"),
    @Mapping(target = "itemId", source = "itemData.id")
  })
  public abstract OrderAcceptanceItemData map(OrderAcceptanceItem orderAcceptanceItem);

  @Mappings({
    @Mapping(target = "customerData", source = "customerId"),
    @Mapping(target = "managerData", source = "managerId"),
    @Mapping(target = "purchaserData", source = "purchaserId"),
    @Mapping(target = "receiverData", source = "receiverId"),
    @Mapping(target = "projectData", source = "projectId")
  })
  public abstract OrderAcceptanceMessages.CreateRequest map(CreateRequest request);

  @Mappings({
    @Mapping(target = "customerData", source = "customerId"),
    @Mapping(target = "managerData", source = "managerId"),
    @Mapping(target = "purchaserData", source = "purchaserId"),
    @Mapping(target = "receiverData", source = "receiverId"),
    @Mapping(target = "projectData", source = "projectId")
  })
  public abstract OrderAcceptanceMessages.UpdateRequest map(UpdateRequest request);

  public abstract OrderAcceptanceMessages.DeleteRequest map(DeleteRequest request);

  public abstract OrderAcceptanceMessages.AcceptRequest map(AcceptRequest request);


  @Mappings({
    @Mapping(target = "orderAcceptance", source = "orderAcceptanceId"),
    @Mapping(target = "itemData", source = "itemId")
  })
  public abstract OrderAcceptanceItemMessages.CreateRequest map(
    OrderAcceptanceItemRequests.CreateRequest request);

  @Mappings({
  })
  public abstract OrderAcceptanceItemMessages.UpdateRequest map(
    OrderAcceptanceItemRequests.UpdateRequest request);

  public abstract OrderAcceptanceItemMessages.DeleteRequest map(
    OrderAcceptanceItemRequests.DeleteRequest request);


}


