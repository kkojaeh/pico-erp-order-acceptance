package pico.erp.order.acceptance.item;

import java.util.Optional;
import java.util.stream.Stream;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pico.erp.item.ItemId;
import pico.erp.order.acceptance.OrderAcceptanceId;

@Repository
interface OrderAcceptanceItemEntityRepository extends
  CrudRepository<OrderAcceptanceItemEntity, OrderAcceptanceItemId> {

  @Query("SELECT CASE WHEN COUNT(oai) > 0 THEN true ELSE false END FROM OrderAcceptanceItem oai WHERE oai.orderAcceptanceId = :orderAcceptanceId AND oai.itemId = :itemId")
  boolean exists(@Param("orderAcceptanceId") OrderAcceptanceId orderAcceptanceId,
    @Param("itemId") ItemId itemId);

  @Query("SELECT oai FROM OrderAcceptanceItem oai WHERE oai.orderAcceptanceId = :orderAcceptanceId")
  Stream<OrderAcceptanceItemEntity> findAllBy(
    @Param("orderAcceptanceId") OrderAcceptanceId orderAcceptanceId);

}

@Repository
@Transactional
public class OrderAcceptanceItemRepositoryJpa implements OrderAcceptanceItemRepository {

  @Autowired
  private OrderAcceptanceItemEntityRepository repository;

  @Autowired
  private OrderAcceptanceItemMapper mapper;

  @Override
  public OrderAcceptanceItem create(OrderAcceptanceItem orderAcceptanceItem) {
    val entity = mapper.jpa(orderAcceptanceItem);
    val created = repository.save(entity);
    return mapper.jpa(created);
  }

  @Override
  public void deleteBy(OrderAcceptanceItemId id) {
    repository.deleteById(id);
  }

  @Override
  public boolean exists(OrderAcceptanceItemId id) {
    return repository.existsById(id);
  }

  @Override
  public boolean exists(OrderAcceptanceId orderAcceptanceId, ItemId itemId) {
    return repository.exists(orderAcceptanceId, itemId);
  }

  @Override
  public Stream<OrderAcceptanceItem> findAllBy(OrderAcceptanceId orderAcceptanceId) {
    return repository.findAllBy(orderAcceptanceId)
      .map(mapper::jpa);
  }

  @Override
  public Optional<OrderAcceptanceItem> findBy(OrderAcceptanceItemId id) {
    return repository.findById(id)
      .map(mapper::jpa);
  }

  @Override
  public void update(OrderAcceptanceItem orderAcceptanceItem) {
    val entity = repository.findById(orderAcceptanceItem.getId()).get();
    mapper.pass(mapper.jpa(orderAcceptanceItem), entity);
    repository.save(entity);
  }
}
