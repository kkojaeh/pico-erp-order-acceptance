package pico.erp.order.acceptance;

import static org.springframework.util.StringUtils.isEmpty;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import kkojaeh.spring.boot.component.ComponentBean;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pico.erp.order.acceptance.item.QOrderAcceptanceItemEntity;
import pico.erp.shared.jpa.QueryDslJpaSupport;

@Service
@ComponentBean
@Transactional(readOnly = true)
@Validated
public class OrderAcceptanceQueryJpa implements OrderAcceptanceQuery {

  private final QOrderAcceptanceEntity orderAcceptance = QOrderAcceptanceEntity.orderAcceptanceEntity;

  private final QOrderAcceptanceItemEntity orderAcceptanceItem = QOrderAcceptanceItemEntity.orderAcceptanceItemEntity;

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private QueryDslJpaSupport queryDslJpaSupport;

  @Override
  public Page<OrderAcceptanceView> retrieve(OrderAcceptanceView.Filter filter,
    Pageable pageable) {
    val query = new JPAQuery<OrderAcceptanceView>(entityManager);
    val select = Projections.bean(OrderAcceptanceView.class,
      orderAcceptance.id,
      orderAcceptance.name,
      orderAcceptance.orderedDate,
      orderAcceptance.dueDate,
      orderAcceptance.customerId,
      orderAcceptance.managerId,
      orderAcceptance.purchaseOrderNumber,
      orderAcceptance.acceptedDate,
      orderAcceptance.purchaserId,
      orderAcceptance.receiverId,
      orderAcceptance.projectId,
      orderAcceptance.status,
      orderAcceptance.createdBy,
      orderAcceptance.createdDate
    );
    query.select(select);
    query.from(orderAcceptance);

    val builder = new BooleanBuilder();

    if (!isEmpty(filter.getName())) {
      builder.and(orderAcceptance.name
        .likeIgnoreCase(queryDslJpaSupport.toLikeKeyword("%", filter.getName(), "%")));
    }

    if (filter.getRelatedCompanyId() != null) {
      builder.and(
        orderAcceptance.customerId.eq(filter.getRelatedCompanyId())
          .or(orderAcceptance.purchaserId.eq(filter.getRelatedCompanyId()))
          .or(orderAcceptance.receiverId.eq(filter.getRelatedCompanyId()))
      );
    }

    if (filter.getManagerId() != null) {
      builder.and(orderAcceptance.managerId.eq(filter.getManagerId()));
    }

    if (filter.getProjectId() != null) {
      builder.and(orderAcceptance.projectId.eq(filter.getProjectId()));
    }

    if (filter.getStartDueDate() != null) {
      builder.and(orderAcceptance.dueDate.goe(filter.getStartDueDate()));
    }
    if (filter.getEndDueDate() != null) {
      builder.and(orderAcceptance.dueDate.loe(filter.getEndDueDate()));
    }

    if (filter.getStatuses() != null && !filter.getStatuses().isEmpty()) {
      builder.and(orderAcceptance.status.in(filter.getStatuses()));
    }

    if (filter.getItemId() != null) {
      builder.and(
        orderAcceptance.id.in(
          JPAExpressions.select(orderAcceptanceItem.orderAcceptanceId)
            .from(orderAcceptanceItem)
            .where(orderAcceptanceItem.itemId.eq(filter.getItemId()))
        )
      );
    }

    query.where(builder);
    return queryDslJpaSupport.paging(query, pageable, select);
  }
}
