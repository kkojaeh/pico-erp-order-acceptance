package pico.erp.order.acceptance;

import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderAcceptanceQuery {

  Page<OrderAcceptanceView> retrieve(@NotNull OrderAcceptanceView.Filter filter,
    @NotNull Pageable pageable);

}
