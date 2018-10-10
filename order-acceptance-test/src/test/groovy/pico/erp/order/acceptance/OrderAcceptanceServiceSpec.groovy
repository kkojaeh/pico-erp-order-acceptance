package pico.erp.order.acceptance

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import pico.erp.company.CompanyId
import pico.erp.order.acceptance.data.OrderAcceptanceId
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
class OrderAcceptanceServiceSpec extends Specification {

  @Autowired
  OrderAcceptanceService orderAcceptanceService

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

  def "아이디로 존재하는 주문접수 확인"() {
    when:
    def exists = orderAcceptanceService.exists(OrderAcceptanceId.from("order-1"))

    then:
    exists == true
  }

  def "아이디로 존재하지 않는 주문접수 확인"() {
    when:
    def exists = orderAcceptanceService.exists(OrderAcceptanceId.from("!order-1"))

    then:
    exists == false
  }

  def "아이디로 존재하는 주문접수를 조회"() {
    when:
    def orderAcceptance = orderAcceptanceService.get(OrderAcceptanceId.from("order-1"))

    then:
    orderAcceptance.id.value == "order-1"
    orderAcceptance.name == "테스트 주문 접수"
  }

  def "아이디로 존재하지 않는 주문접수를 조회"() {
    when:
    orderAcceptanceService.get(OrderAcceptanceId.from("!order-1"))

    then:
    thrown(OrderAcceptanceExceptions.NotFoundException)
  }

}
