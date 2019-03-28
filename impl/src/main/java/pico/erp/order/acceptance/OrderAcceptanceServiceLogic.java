package pico.erp.order.acceptance;

import kkojaeh.spring.boot.component.Give;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pico.erp.order.acceptance.OrderAcceptanceRequests.AcceptRequest;
import pico.erp.order.acceptance.OrderAcceptanceRequests.CreateRequest;
import pico.erp.order.acceptance.OrderAcceptanceRequests.DeleteRequest;
import pico.erp.order.acceptance.OrderAcceptanceRequests.UpdateRequest;
import pico.erp.project.ProjectExceptions.NotFoundException;
import pico.erp.shared.event.EventPublisher;

@SuppressWarnings("Duplicates")
@Service
@Give
@Transactional
@Validated
public class OrderAcceptanceServiceLogic implements OrderAcceptanceService {

  @Autowired
  private OrderAcceptanceRepository orderAcceptanceRepository;

  @Autowired
  private EventPublisher eventPublisher;

  @Autowired
  private OrderAcceptanceMapper mapper;

  @Override
  public void accept(AcceptRequest request) {
    val orderAcceptance = orderAcceptanceRepository.findBy(request.getId())
      .orElseThrow(NotFoundException::new);
    val response = orderAcceptance.apply(mapper.map(request));
    orderAcceptanceRepository.update(orderAcceptance);
    eventPublisher.publishEvents(response.getEvents());
  }

  @Override
  public OrderAcceptanceData create(CreateRequest request) {
    val orderAcceptance = new OrderAcceptance();
    val response = orderAcceptance.apply(mapper.map(request));
    if (orderAcceptanceRepository.exists(orderAcceptance.getId())) {
      throw new OrderAcceptanceExceptions.AlreadyExistsException();
    }
    if (orderAcceptanceRepository.exists(orderAcceptance.getCode())) {
      throw new OrderAcceptanceExceptions.CodeAlreadyExistsException();
    }
    val created = orderAcceptanceRepository.create(orderAcceptance);
    eventPublisher.publishEvents(response.getEvents());
    return mapper.map(created);
  }

  @Override
  public void delete(DeleteRequest request) {
    val orderAcceptance = orderAcceptanceRepository.findBy(request.getId())
      .orElseThrow(NotFoundException::new);
    val response = orderAcceptance.apply(mapper.map(request));
    orderAcceptanceRepository.update(orderAcceptance);
    eventPublisher.publishEvents(response.getEvents());
  }

  @Override
  public boolean exists(OrderAcceptanceId id) {
    return orderAcceptanceRepository.exists(id);
  }

  @Override
  public OrderAcceptanceData get(OrderAcceptanceId id) {
    return orderAcceptanceRepository.findBy(id)
      .map(mapper::map)
      .orElseThrow(OrderAcceptanceExceptions.NotFoundException::new);
  }

  @Override
  public void update(UpdateRequest request) {
    val orderAcceptance = orderAcceptanceRepository.findBy(request.getId())
      .orElseThrow(NotFoundException::new);
    val response = orderAcceptance.apply(mapper.map(request));
    orderAcceptanceRepository.update(orderAcceptance);
    eventPublisher.publishEvents(response.getEvents());
  }
}
