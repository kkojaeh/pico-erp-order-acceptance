package pico.erp.order.acceptance

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import pico.erp.company.CompanyId
import pico.erp.item.ItemId
import pico.erp.order.acceptance.item.OrderAcceptanceItemExceptions
import pico.erp.order.acceptance.item.OrderAcceptanceItemId
import pico.erp.order.acceptance.item.OrderAcceptanceItemRequests
import pico.erp.order.acceptance.item.OrderAcceptanceItemService
import pico.erp.project.ProjectId
import pico.erp.shared.IntegrationConfiguration
import pico.erp.shared.data.Address
import pico.erp.user.UserId
import spock.lang.Specification

import java.time.OffsetDateTime

@SpringBootTest(classes = [IntegrationConfiguration])
@Transactional
@Rollback
@ActiveProfiles("test")
@Configuration
@ComponentScan("pico.erp.config")
class OrderAcceptanceItemServiceSpec extends Specification {

  @Autowired
  OrderAcceptanceService orderAcceptanceService

  @Autowired
  OrderAcceptanceItemService orderAcceptanceItemService

  def setup() {
    orderAcceptanceService.create(
      new OrderAcceptanceRequests.CreateRequest(
        id: OrderAcceptanceId.from("order-1"),
        name: "테스트 주문 접수",
        orderedDate: OffsetDateTime.now().minusDays(1),
        dueDate: OffsetDateTime.now().plusDays(7),
        customerId: CompanyId.from("CUST1"),
        purchaserId: CompanyId.from("CUST1"),
        receiverId: CompanyId.from("CUST1"),
        projectId: ProjectId.from("sample-project1"),
        purchaseOrderNumber: "PO 번호",
        managerId: UserId.from("ysh"),
        deliveryAddress: new Address(
          postalCode: '13496',
          street: '경기도 성남시 분당구 장미로 42',
          detail: '야탑리더스 410호'
        ),
      )
    )
  }

  def "주문 상품 추가"() {
    when:
    orderAcceptanceItemService.create(
      new OrderAcceptanceItemRequests.CreateRequest(
        id: OrderAcceptanceItemId.from("order-item-1"),
        orderAcceptanceId: OrderAcceptanceId.from("order-1"),
        itemId: ItemId.from("item-1"),
        unitPrice: 20000,
        quantity: 100
      )
    )
    def items = orderAcceptanceItemService.getAll(OrderAcceptanceId.from("order-1"))
    def item = orderAcceptanceItemService.get(OrderAcceptanceItemId.from("order-item-1"))

    then:
    items.size() == 1
    item.unitPrice == 20000
    item.quantity == 100
    item.itemId == ItemId.from("item-1")
  }

  def "동일 주문 상품 추가시 오류"() {
    when:
    orderAcceptanceItemService.create(
      new OrderAcceptanceItemRequests.CreateRequest(
        id: OrderAcceptanceItemId.from("order-item-1"),
        orderAcceptanceId: OrderAcceptanceId.from("order-1"),
        itemId: ItemId.from("item-1"),
        unitPrice: 20000,
        quantity: 100
      )
    )
    orderAcceptanceItemService.create(
      new OrderAcceptanceItemRequests.CreateRequest(
        id: OrderAcceptanceItemId.from("order-item-2"),
        orderAcceptanceId: OrderAcceptanceId.from("order-1"),
        itemId: ItemId.from("item-1"),
        unitPrice: 10000,
        quantity: 50
      )
    )
    then:
    thrown(OrderAcceptanceItemExceptions.AlreadyExistsException)
  }

  def "주문 상품 수정"() {
    when:
    orderAcceptanceItemService.create(
      new OrderAcceptanceItemRequests.CreateRequest(
        id: OrderAcceptanceItemId.from("order-item-1"),
        orderAcceptanceId: OrderAcceptanceId.from("order-1"),
        itemId: ItemId.from("item-1"),
        unitPrice: 20000,
        quantity: 100
      )
    )
    orderAcceptanceItemService.update(
      new OrderAcceptanceItemRequests.UpdateRequest(
        id: OrderAcceptanceItemId.from("order-item-1"),
        unitPrice: 10000,
        quantity: 50
      )
    )
    def items = orderAcceptanceItemService.getAll(OrderAcceptanceId.from("order-1"))
    def item = orderAcceptanceItemService.get(OrderAcceptanceItemId.from("order-item-1"))

    then:
    items.size() == 1
    item.unitPrice == 10000
    item.quantity == 50
    item.itemId == ItemId.from("item-1")
  }

  def "주문 상품 삭제"() {
    when:
    orderAcceptanceItemService.create(
      new OrderAcceptanceItemRequests.CreateRequest(
        id: OrderAcceptanceItemId.from("order-item-1"),
        orderAcceptanceId: OrderAcceptanceId.from("order-1"),
        itemId: ItemId.from("item-1"),
        unitPrice: 20000,
        quantity: 100
      )
    )
    orderAcceptanceItemService.delete(
      new OrderAcceptanceItemRequests.DeleteRequest(
        id: OrderAcceptanceItemId.from("order-item-1")
      )
    )
    def items = orderAcceptanceItemService.getAll(OrderAcceptanceId.from("order-1"))

    then:
    items.size() == 0
  }

}
