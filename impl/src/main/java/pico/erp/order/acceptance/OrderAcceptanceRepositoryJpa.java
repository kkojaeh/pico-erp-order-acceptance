package pico.erp.order.acceptance;

import java.time.OffsetDateTime;
import java.util.Optional;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
interface OrderAcceptanceEntityRepository extends
  CrudRepository<OrderAcceptanceEntity, OrderAcceptanceId> {

  @Query("SELECT COUNT(a) FROM OrderAcceptance a WHERE a.createdDate >= :begin AND a.createdDate <= :end")
  long countCreatedBetween(@Param("begin") OffsetDateTime begin,
    @Param("end") OffsetDateTime end);

  @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM OrderAcceptance a WHERE a.code = :code")
  boolean exists(@Param("code") OrderAcceptanceCode code);

}

@Repository
@Transactional
public class OrderAcceptanceRepositoryJpa implements OrderAcceptanceRepository {

  @Autowired
  private OrderAcceptanceEntityRepository repository;

  @Autowired
  private OrderAcceptanceMapper mapper;


  @Override
  public OrderAcceptance create(OrderAcceptance orderAcceptance) {
    val entity = mapper.jpa(orderAcceptance);
    val created = repository.save(entity);
    return mapper.jpa(created);
  }

  @Override
  public long countCreatedBetween(OffsetDateTime begin, OffsetDateTime end) {
    return repository.countCreatedBetween(begin, end);
  }

  @Override
  public void deleteBy(OrderAcceptanceId id) {
    repository.deleteById(id);
  }

  @Override
  public boolean exists(OrderAcceptanceId id) {
    return repository.existsById(id);
  }

  @Override
  public Optional<OrderAcceptance> findBy(OrderAcceptanceId id) {
    return repository.findById(id)
      .map(mapper::jpa);
  }

  @Override
  public void update(OrderAcceptance orderAcceptance) {
    val entity = repository.findById(orderAcceptance.getId()).get();
    mapper.pass(mapper.jpa(orderAcceptance), entity);
    repository.save(entity);
  }

  @Override
  public boolean exists(OrderAcceptanceCode code) {
    return repository.exists(code);
  }
}
