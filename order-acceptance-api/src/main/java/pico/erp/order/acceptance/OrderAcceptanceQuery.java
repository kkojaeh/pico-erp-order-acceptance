package pico.erp.order.acceptance;

import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pico.erp.shared.data.LabeledValuable;

public interface OrderAcceptanceQuery {

  List<? extends LabeledValuable> asLabels(@NotNull String keyword, long limit);

  Page<OrderAcceptanceView> retrieve(@NotNull OrderAcceptanceView.Filter filter,
    @NotNull Pageable pageable);

}
