package pico.erp.orderacceptance;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pico.erp.shared.data.Menu;
import pico.erp.shared.data.MenuCategory;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum MENU implements Menu {

  ORDER_ACCEPTANCE_MANAGEMENT("/order-acceptance", "receipt", MenuCategory.CUSTOMER);

  String url;

  String icon;

  MenuCategory category;

  public String getId() {
    return name();
  }

}
