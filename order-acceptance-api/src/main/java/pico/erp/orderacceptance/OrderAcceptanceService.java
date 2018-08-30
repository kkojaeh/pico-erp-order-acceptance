package pico.erp.orderacceptance;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import pico.erp.orderacceptance.data.OrderAcceptanceData;
import pico.erp.orderacceptance.data.OrderAcceptanceId;

public interface OrderAcceptanceService {


  void accept(@Valid @NotNull OrderAcceptanceRequests.AcceptRequest request);

  OrderAcceptanceData create(@Valid @NotNull OrderAcceptanceRequests.CreateRequest request);

  void delete(@Valid @NotNull OrderAcceptanceRequests.DeleteRequest request);

  boolean exists(@Valid @NotNull OrderAcceptanceId id);

  OrderAcceptanceData get(@Valid @NotNull OrderAcceptanceId id);

  void update(@Valid @NotNull OrderAcceptanceRequests.UpdateRequest request);



}
