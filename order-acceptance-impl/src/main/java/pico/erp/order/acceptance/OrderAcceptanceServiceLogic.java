package pico.erp.order.acceptance;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pico.erp.audit.AuditService;
import pico.erp.order.acceptance.OrderAcceptanceExceptions.AlreadyExistsException;
import pico.erp.order.acceptance.OrderAcceptanceRequests.AcceptRequest;
import pico.erp.order.acceptance.OrderAcceptanceRequests.CreateRequest;
import pico.erp.order.acceptance.OrderAcceptanceRequests.DeleteRequest;
import pico.erp.order.acceptance.OrderAcceptanceRequests.UpdateRequest;
import pico.erp.project.ProjectExceptions.NotFoundException;
import pico.erp.shared.Public;
import pico.erp.shared.event.EventPublisher;

@SuppressWarnings("Duplicates")
@Service
@Public
@Transactional
@Validated
public class OrderAcceptanceServiceLogic implements OrderAcceptanceService {

  @Autowired
  private OrderAcceptanceRepository orderAcceptanceRepository;

  @Autowired
  private EventPublisher eventPublisher;

  @Autowired
  private OrderAcceptanceMapper mapper;

  @Lazy
  @Autowired
  private AuditService auditService;

  @Override
  public void accept(AcceptRequest request) {
    val orderAcceptance = orderAcceptanceRepository.findBy(request.getId())
      .orElseThrow(NotFoundException::new);
    val response = orderAcceptance.apply(mapper.map(request));
    orderAcceptanceRepository.update(orderAcceptance);
    auditService.commit(orderAcceptance);
    eventPublisher.publishEvents(response.getEvents());
  }

  @Override
  public OrderAcceptanceData create(CreateRequest request) {
    val orderAcceptance = new OrderAcceptance();
    val response = orderAcceptance.apply(mapper.map(request));
    if (orderAcceptanceRepository.exists(orderAcceptance.getId())) {
      throw new AlreadyExistsException();
    }
    val created = orderAcceptanceRepository.create(orderAcceptance);
    auditService.commit(created);
    eventPublisher.publishEvents(response.getEvents());
    return mapper.map(created);
  }

  @Override
  public void delete(DeleteRequest request) {
    val orderAcceptance = orderAcceptanceRepository.findBy(request.getId())
      .orElseThrow(NotFoundException::new);
    val response = orderAcceptance.apply(mapper.map(request));
    orderAcceptanceRepository.update(orderAcceptance);
    auditService.delete(orderAcceptance);
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
    auditService.commit(orderAcceptance);
    eventPublisher.publishEvents(response.getEvents());
  }
}
