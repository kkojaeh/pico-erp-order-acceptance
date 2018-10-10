package pico.erp.order.acceptance;

import java.util.Optional;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
interface OrderAcceptanceEntityRepository extends
  CrudRepository<OrderAcceptanceEntity, OrderAcceptanceId> {

}

@Repository
@Transactional
public class OrderAcceptanceRepositoryJpa implements OrderAcceptanceRepository {

  @Autowired
  private OrderAcceptanceEntityRepository repository;

  @Autowired
  private OrderAcceptanceJpaMapper mapper;


  @Override
  public OrderAcceptance create(OrderAcceptance orderAcceptance) {
    val entity = mapper.map(orderAcceptance);
    val created = repository.save(entity);
    return mapper.map(created);
  }

  @Override
  public void deleteBy(OrderAcceptanceId id) {
    repository.delete(id);
  }

  @Override
  public boolean exists(OrderAcceptanceId id) {
    return repository.exists(id);
  }

  @Override
  public Optional<OrderAcceptance> findBy(OrderAcceptanceId id) {
    return Optional.ofNullable(repository.findOne(id))
      .map(mapper::map);
  }

  @Override
  public void update(OrderAcceptance orderAcceptance) {
    val entity = repository.findOne(orderAcceptance.getId());
    mapper.pass(mapper.map(orderAcceptance), entity);
    repository.save(entity);
  }
}
