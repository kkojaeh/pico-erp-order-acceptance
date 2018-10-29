package pico.erp.order.acceptance;

import org.mapstruct.Mapper;

@Mapper
public abstract class OrderAcceptanceJpaMapper {

  /*

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

  public OrderAcceptanceItem map(OrderAcceptanceItemEntity entity) {
    return OrderAcceptanceItem.builder()
      .id(entity.getId())
      .orderAcceptance(map(entity.getOrderAcceptance()))
      .item(map(entity.getItemId()))
      .unitPrice(entity.getUnitPrice())
      .quantity(entity.getQuantity())
      .status(entity.getStatus())
      .build();
  }


  @Mappings({
    @Mapping(target = "itemId", source = "item.id"),
    @Mapping(target = "createdBy", ignore = true),
    @Mapping(target = "createdDate", ignore = true),
    @Mapping(target = "lastModifiedBy", ignore = true),
    @Mapping(target = "lastModifiedDate", ignore = true)
  })
  public abstract OrderAcceptanceItemEntity map(OrderAcceptanceItem data);

  public abstract void pass(OrderAcceptanceEntity from, @MappingTarget OrderAcceptanceEntity to);

  public abstract void pass(OrderAcceptanceItemEntity from,
    @MappingTarget OrderAcceptanceItemEntity to);

    */

}
