package pico.erp.order.acceptance.jpa;

import java.util.Optional;
import java.util.stream.Stream;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pico.erp.item.data.ItemId;
import pico.erp.order.acceptance.data.OrderAcceptanceId;
import pico.erp.order.acceptance.item.OrderAcceptanceItem;
import pico.erp.order.acceptance.item.OrderAcceptanceItemRepository;
import pico.erp.order.acceptance.item.data.OrderAcceptanceItemId;

@Repository
interface OrderAcceptanceItemEntityRepository extends
  CrudRepository<OrderAcceptanceItemEntity, OrderAcceptanceItemId> {

  @Query("SELECT CASE WHEN COUNT(oai) > 0 THEN true ELSE false END FROM OrderAcceptanceItem oai JOIN oai.orderAcceptance oa WHERE oa.id = :orderAcceptanceId AND oai.itemId = :itemId")
  boolean exists(@Param("orderAcceptanceId") OrderAcceptanceId orderAcceptanceId,
    @Param("itemId") ItemId itemId);

  @Query("SELECT oai FROM OrderAcceptanceItem oai JOIN oai.orderAcceptance oa  WHERE oa.id = :orderAcceptanceId")
  Stream<OrderAcceptanceItemEntity> findAllBy(
    @Param("orderAcceptanceId") OrderAcceptanceId orderAcceptanceId);

}

@Repository
@Transactional
public class OrderAcceptanceItemRepositoryJpa implements OrderAcceptanceItemRepository {

  @Autowired
  private OrderAcceptanceItemEntityRepository repository;

  @Autowired
  private OrderAcceptanceJpaMapper mapper;

  @Override
  public OrderAcceptanceItem create(OrderAcceptanceItem orderAcceptanceItem) {
    val entity = mapper.map(orderAcceptanceItem);
    val created = repository.save(entity);
    return mapper.map(created);
  }

  @Override
  public void deleteBy(OrderAcceptanceItemId id) {
    repository.delete(id);
  }

  @Override
  public boolean exists(OrderAcceptanceItemId id) {
    return repository.exists(id);
  }

  @Override
  public boolean exists(OrderAcceptanceId orderAcceptanceId, ItemId itemId) {
    return repository.exists(orderAcceptanceId, itemId);
  }

  @Override
  public Stream<OrderAcceptanceItem> findAllBy(OrderAcceptanceId orderAcceptanceId) {
    return repository.findAllBy(orderAcceptanceId)
      .map(mapper::map);
  }

  @Override
  public Optional<OrderAcceptanceItem> findBy(OrderAcceptanceItemId id) {
    return Optional.ofNullable(repository.findOne(id))
      .map(mapper::map);
  }

  @Override
  public void update(OrderAcceptanceItem orderAcceptanceItem) {
    val entity = repository.findOne(orderAcceptanceItem.getId());
    mapper.pass(mapper.map(orderAcceptanceItem), entity);
    repository.save(entity);
  }
}
