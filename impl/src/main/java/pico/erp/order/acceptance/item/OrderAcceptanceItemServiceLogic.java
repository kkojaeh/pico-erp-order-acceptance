package pico.erp.order.acceptance.item;

import java.util.List;
import java.util.stream.Collectors;
import kkojaeh.spring.boot.component.ComponentBean;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pico.erp.order.acceptance.OrderAcceptanceId;
import pico.erp.order.acceptance.item.OrderAcceptanceItemRequests.CreateRequest;
import pico.erp.order.acceptance.item.OrderAcceptanceItemRequests.DeleteRequest;
import pico.erp.order.acceptance.item.OrderAcceptanceItemRequests.UpdateRequest;
import pico.erp.shared.event.EventPublisher;

@SuppressWarnings("Duplicates")
@Service
@ComponentBean
@Transactional
@Validated
public class OrderAcceptanceItemServiceLogic implements OrderAcceptanceItemService {

  @Autowired
  private OrderAcceptanceItemRepository orderAcceptanceItemRepository;

  @Autowired
  private EventPublisher eventPublisher;

  @Autowired
  private OrderAcceptanceItemMapper mapper;

  @Override
  public OrderAcceptanceItemData create(CreateRequest request) {
    if (orderAcceptanceItemRepository.exists(request.getOrderAcceptanceId(), request.getItemId())) {
      throw new OrderAcceptanceItemExceptions.AlreadyExistsException();
    }
    val orderAcceptanceItem = new OrderAcceptanceItem();
    val response = orderAcceptanceItem.apply(mapper.map(request));
    if (orderAcceptanceItemRepository.exists(orderAcceptanceItem.getId())) {
      throw new OrderAcceptanceItemExceptions.AlreadyExistsException();
    }
    val created = orderAcceptanceItemRepository.create(orderAcceptanceItem);
    eventPublisher.publishEvents(response.getEvents());
    return mapper.map(created);
  }

  @Override
  public void delete(DeleteRequest request) {
    val orderAcceptanceItem = orderAcceptanceItemRepository.findBy(request.getId())
      .orElseThrow(OrderAcceptanceItemExceptions.NotFoundException::new);
    val response = orderAcceptanceItem.apply(mapper.map(request));
    orderAcceptanceItemRepository.deleteBy(orderAcceptanceItem.getId());
    eventPublisher.publishEvents(response.getEvents());
  }

  @Override
  public boolean exists(OrderAcceptanceItemId id) {
    return orderAcceptanceItemRepository.exists(id);
  }

  @Override
  public OrderAcceptanceItemData get(OrderAcceptanceItemId id) {
    return orderAcceptanceItemRepository.findBy(id)
      .map(mapper::map)
      .orElseThrow(OrderAcceptanceItemExceptions.NotFoundException::new);
  }

  @Override
  public List<OrderAcceptanceItemData> getAll(OrderAcceptanceId orderAcceptanceId) {
    return orderAcceptanceItemRepository.findAllBy(orderAcceptanceId)
      .map(mapper::map)
      .collect(Collectors.toList());
  }

  @Override
  public void update(UpdateRequest request) {
    val orderAcceptanceItem = orderAcceptanceItemRepository.findBy(request.getId())
      .orElseThrow(OrderAcceptanceItemExceptions.NotFoundException::new);
    val response = orderAcceptanceItem.apply(mapper.map(request));
    orderAcceptanceItemRepository.update(orderAcceptanceItem);
    eventPublisher.publishEvents(response.getEvents());
  }
}
