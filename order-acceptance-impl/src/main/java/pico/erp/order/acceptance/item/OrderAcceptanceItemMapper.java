package pico.erp.order.acceptance.item;

import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import pico.erp.item.ItemData;
import pico.erp.item.ItemId;
import pico.erp.item.ItemService;
import pico.erp.order.acceptance.OrderAcceptance;
import pico.erp.order.acceptance.OrderAcceptanceId;
import pico.erp.order.acceptance.OrderAcceptanceMapper;

@Mapper
public abstract class OrderAcceptanceItemMapper {

  @Lazy
  @Autowired
  private OrderAcceptanceMapper orderAcceptanceMapper;

  @Lazy
  @Autowired
  private ItemService itemService;

  @Mappings({
    @Mapping(target = "orderAcceptanceId", source = "orderAcceptance.id"),
    @Mapping(target = "itemId", source = "item.id")
  })
  public abstract OrderAcceptanceItemData map(OrderAcceptanceItem orderAcceptanceItem);

  @Mappings({
    @Mapping(target = "orderAcceptance", source = "orderAcceptanceId"),
    @Mapping(target = "item", source = "itemId")
  })
  public abstract OrderAcceptanceItemMessages.CreateRequest map(
    OrderAcceptanceItemRequests.CreateRequest request);

  @Mappings({
  })
  public abstract OrderAcceptanceItemMessages.UpdateRequest map(
    OrderAcceptanceItemRequests.UpdateRequest request);

  public abstract OrderAcceptanceItemMessages.DeleteRequest map(
    OrderAcceptanceItemRequests.DeleteRequest request);

  protected OrderAcceptance map(OrderAcceptanceId orderAcceptanceId) {
    return orderAcceptanceMapper.map(orderAcceptanceId);
  }

  protected ItemData map(ItemId itemId) {
    return Optional.ofNullable(itemId)
      .map(itemService::get)
      .orElse(null);
  }


}
