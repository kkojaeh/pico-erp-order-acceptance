package pico.erp.order.acceptance

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import pico.erp.item.ItemId
import pico.erp.order.acceptance.item.OrderAcceptanceItemExceptions
import pico.erp.order.acceptance.item.OrderAcceptanceItemId
import pico.erp.order.acceptance.item.OrderAcceptanceItemRequests
import pico.erp.order.acceptance.item.OrderAcceptanceItemService
import pico.erp.shared.IntegrationConfiguration
import spock.lang.Specification

@SpringBootTest(classes = [IntegrationConfiguration])
@Transactional
@Rollback
@ActiveProfiles("test")
@Configuration
@ComponentScan("pico.erp.config")
class OrderAcceptanceItemServiceSpec extends Specification {

  @Autowired
  OrderAcceptanceItemService orderAcceptanceItemService

  def id = OrderAcceptanceItemId.from("order-acceptance-test-item-1")

  def orderAcceptanceId = OrderAcceptanceId.from("order-acceptance-test")

  def itemId = ItemId.from("item-1")

  def setup() {

  }

  def addItem() {
    orderAcceptanceItemService.create(
      new OrderAcceptanceItemRequests.CreateRequest(
        id: id,
        orderAcceptanceId: orderAcceptanceId,
        itemId: itemId,
        unitPrice: 20000,
        quantity: 100
      )
    )
  }

  def updateItem() {
    orderAcceptanceItemService.update(
      new OrderAcceptanceItemRequests.UpdateRequest(
        id: id,
        unitPrice: 10000,
        quantity: 50
      )
    )
  }

  def addSameItem() {
    orderAcceptanceItemService.create(
      new OrderAcceptanceItemRequests.CreateRequest(
        id: OrderAcceptanceItemId.from("order-acceptance-test-item-2"),
        orderAcceptanceId: orderAcceptanceId,
        itemId: itemId,
        unitPrice: 10000,
        quantity: 99
      )
    )
  }

  def removeItem() {
    orderAcceptanceItemService.delete(
      new OrderAcceptanceItemRequests.DeleteRequest(
        id: id
      )
    )
  }

  def "추가 - 추가"() {
    when:
    addItem()
    def items = orderAcceptanceItemService.getAll(orderAcceptanceId)
    def item = orderAcceptanceItemService.get(id)

    then:
    items.size() == 1
    item.unitPrice == 20000
    item.quantity == 100
    item.itemId == itemId
  }

  def "추가 - 동일 상품 추가"() {
    when:
    addItem()
    addSameItem()
    then:
    thrown(OrderAcceptanceItemExceptions.AlreadyExistsException)
  }

  def "수정 - 수정"() {
    when:
    addItem()
    updateItem()

    def items = orderAcceptanceItemService.getAll(orderAcceptanceId)
    def item = orderAcceptanceItemService.get(id)

    then:
    items.size() == 1
    item.unitPrice == 10000
    item.quantity == 50
    item.itemId == itemId
  }

  def "삭제 - 삭제"() {
    when:
    addItem()
    removeItem()
    def items = orderAcceptanceItemService.getAll(orderAcceptanceId)

    then:
    items.size() == 0
  }

}
