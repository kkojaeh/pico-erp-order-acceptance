package pico.erp.order.acceptance

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import pico.erp.company.CompanyId
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

  def id = OrderAcceptanceId.from("test")
  def unknownId = OrderAcceptanceId.from("unknown")
  def name = "테스트 주문 접수"
  def orderedDate = OffsetDateTime.now().minusDays(1)
  def dueDate = OffsetDateTime.now().plusDays(7)
  def customerId = CompanyId.from("CUST1")
  def purchaserId = CompanyId.from("CUST1")
  def receiverId = CompanyId.from("CUST1")
  def projectId = ProjectId.from("sample-project1")
  def purchaseOrderNumber = "PO 번호"
  def managerId = UserId.from("ysh")
  def deliveryAddress = new Address(
    postalCode: '13496',
    street: '경기도 성남시 분당구 장미로 42',
    detail: '야탑리더스 410호'
  )


  def setup() {
    orderAcceptanceService.create(
      new OrderAcceptanceRequests.CreateRequest(
        id: id,
        name: name,
        orderedDate: orderedDate,
        dueDate: dueDate,
        customerId: customerId,
        purchaserId: purchaserId,
        receiverId: receiverId,
        projectId: projectId,
        purchaseOrderNumber: purchaseOrderNumber,
        managerId: managerId,
        deliveryAddress: deliveryAddress
      )
    )
  }

  def "존재 - 아이디로 확인"() {
    when:
    def exists = orderAcceptanceService.exists(id)

    then:
    exists == true
  }

  def "존재 - 존재하지 않는 아이디로 확인"() {
    when:
    def exists = orderAcceptanceService.exists(unknownId)

    then:
    exists == false
  }

  def "조회 - 아이디로 조회"() {
    when:
    def orderAcceptance = orderAcceptanceService.get(id)

    then:
    orderAcceptance.id == id
    orderAcceptance.name == "테스트 주문 접수"
    orderAcceptance.purchaseOrderNumber == "PO 번호"
    orderAcceptance.orderedDate == orderedDate
    orderAcceptance.dueDate == dueDate
    orderAcceptance.customerId == customerId
    orderAcceptance.purchaserId == purchaserId
    orderAcceptance.receiverId == receiverId
    orderAcceptance.projectId == projectId
    orderAcceptance.purchaseOrderNumber == purchaseOrderNumber
    orderAcceptance.managerId == managerId
    orderAcceptance.deliveryAddress.postalCode == deliveryAddress.postalCode
    orderAcceptance.deliveryAddress.street == deliveryAddress.street
    orderAcceptance.deliveryAddress.detail == deliveryAddress.detail
  }

  def "아이디로 존재하지 않는 주문접수를 조회"() {
    when:
    orderAcceptanceService.get(unknownId)

    then:
    thrown(OrderAcceptanceExceptions.NotFoundException)
  }

}
