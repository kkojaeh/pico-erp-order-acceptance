package pico.erp.order.acceptance;

public interface OrderAcceptanceCodeGenerator {

  OrderAcceptanceCode generate(OrderAcceptance orderAcceptance);
}
