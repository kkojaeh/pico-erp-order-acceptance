package pico.erp.order.acceptance;

import javax.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pico.erp.shared.data.Role;

public final class OrderAcceptanceApi {

  @RequiredArgsConstructor
  public enum Roles implements Role {

    ORDER_ACCEPTANCE_MANAGER,

    ORDER_ACCEPTANCE_ACCESSOR;

    @Id
    @Getter
    private final String id = name();

  }
}
