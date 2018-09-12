package pico.erp.order.acceptance.item.data;

import pico.erp.shared.data.LocalizedNameable;

public enum OrderAcceptanceItemStatusKind implements LocalizedNameable {

  /**
   * 주문이 접수가 생성됨을 의미
   */
  CREATED,

  /**
   * 접수 처리 됨
   */
  ACCEPTED,

  /**
   * 주문에 대한 계획이 생성됨
   */
  PLANNED,

  /**
   * 진행중
   */
  ONGOING,

  /**
   * 생산완료
   */
  RELEASE_PREPARED,

  /**
   * 출고됨
   */
  RELEASED,

  /**
   * 결제가 끝나고 종료
   */
  TERMINATED;

}
