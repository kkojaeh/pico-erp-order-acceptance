package pico.erp.orderacceptance.impl;

import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import pico.erp.company.CompanyService;
import pico.erp.company.data.CompanyData;
import pico.erp.company.data.CompanyId;
import pico.erp.item.ItemService;
import pico.erp.item.data.ItemData;
import pico.erp.item.data.ItemId;
import pico.erp.orderacceptance.domain.OrderAcceptance;
import pico.erp.orderacceptance.domain.OrderAcceptanceItem;
import pico.erp.orderacceptance.impl.jpa.OrderAcceptanceEntity;
import pico.erp.orderacceptance.impl.jpa.OrderAcceptanceItemEntity;
import pico.erp.project.ProjectService;
import pico.erp.project.data.ProjectData;
import pico.erp.project.data.ProjectId;
import pico.erp.user.UserService;
import pico.erp.user.data.UserData;
import pico.erp.user.data.UserId;

@Mapper
public abstract class OrderAcceptanceJpaMapper {

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

  public OrderAcceptance map(OrderAcceptanceEntity entity) {
    return OrderAcceptance.builder()
      .id(entity.getId())
      .name(entity.getName())
      .orderedDate(entity.getOrderedDate())
      .dueDate(entity.getDueDate())
      .customerData(map(entity.getCustomerId()))
      .managerData(map(entity.getManagerId()))
      .purchaseOrderNumber(entity.getPurchaseOrderNumber())
      .deleted(entity.isDeleted())
      .deletedDate(entity.getDeletedDate())
      .acceptedDate(entity.getAcceptedDate())
      .deliveryAddress(entity.getDeliveryAddress())
      .purchaserData(map(entity.getPurchaserId()))
      .receiverData(map(entity.getReceiverId()))
      .projectData(map(entity.getProjectId()))
      .status(entity.getStatus())
      .build();
  }

  public OrderAcceptanceItem map(OrderAcceptanceItemEntity entity) {
    return OrderAcceptanceItem.builder()
      .id(entity.getId())
      .orderAcceptance(map(entity.getOrderAcceptance()))
      .itemData(map(entity.getItemId()))
      .unitPrice(entity.getUnitPrice())
      .quantity(entity.getQuantity())
      .status(entity.getStatus())
      .build();
  }

  @Mappings({
    @Mapping(target = "customerId", source = "customerData.id"),
    @Mapping(target = "customerName", source = "customerData.name"),
    @Mapping(target = "managerId", source = "managerData.id"),
    @Mapping(target = "managerName", source = "managerData.name"),
    @Mapping(target = "purchaserId", source = "purchaserData.id"),
    @Mapping(target = "purchaserName", source = "purchaserData.name"),
    @Mapping(target = "receiverId", source = "receiverData.id"),
    @Mapping(target = "receiverName", source = "receiverData.name"),
    @Mapping(target = "projectId", source = "projectData.id"),
    @Mapping(target = "projectName", source = "projectData.name"),
    @Mapping(target = "createdBy", ignore = true),
    @Mapping(target = "createdDate", ignore = true),
    @Mapping(target = "lastModifiedBy", ignore = true),
    @Mapping(target = "lastModifiedDate", ignore = true)
  })
  public abstract OrderAcceptanceEntity map(OrderAcceptance data);

  @Mappings({
    @Mapping(target = "itemId", source = "itemData.id"),
    @Mapping(target = "createdBy", ignore = true),
    @Mapping(target = "createdDate", ignore = true),
    @Mapping(target = "lastModifiedBy", ignore = true),
    @Mapping(target = "lastModifiedDate", ignore = true)
  })
  public abstract OrderAcceptanceItemEntity map(OrderAcceptanceItem data);

  public abstract void pass(OrderAcceptanceEntity from, @MappingTarget OrderAcceptanceEntity to);

  public abstract void pass(OrderAcceptanceItemEntity from,
    @MappingTarget OrderAcceptanceItemEntity to);

}
