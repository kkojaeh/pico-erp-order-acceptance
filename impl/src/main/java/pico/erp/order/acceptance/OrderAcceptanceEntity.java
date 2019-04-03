package pico.erp.order.acceptance;


import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pico.erp.company.CompanyId;
import pico.erp.project.ProjectId;
import pico.erp.shared.TypeDefinitions;
import pico.erp.shared.data.Address;
import pico.erp.shared.data.Auditor;
import pico.erp.user.UserId;

@Entity(name = "OrderAcceptance")
@Table(name = "ODA_ORDER_ACCEPTANCE", indexes = {
  @Index(columnList = "CODE", unique = true),
  @Index(columnList = "createdDate")
})
@Data
@EqualsAndHashCode(of = "id")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderAcceptanceEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @EmbeddedId
  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "ID", length = TypeDefinitions.UUID_BINARY_LENGTH))
  })
  OrderAcceptanceId id;

  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "CODE", length = TypeDefinitions.CODE_LENGTH))
  })
  OrderAcceptanceCode code;

  @Column(length = TypeDefinitions.NAME_LENGTH)
  String name;

  LocalDateTime orderedDate;

  LocalDateTime dueDate;

  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "CUSTOMER_ID", length = TypeDefinitions.ID_LENGTH))
  })
  CompanyId customerId;

  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "MANAGER_ID", length = TypeDefinitions.ID_LENGTH))
  })
  UserId managerId;

  @Column(length = TypeDefinitions.CODE_LENGTH)
  String purchaseOrderNumber;

  @Column
  boolean deleted;

  @Column
  LocalDateTime deletedDate;

  @Column
  LocalDateTime acceptedDate;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "postalCode", column = @Column(name = "DELIVERY_ADDRESS_POSTAL_CODE", length = TypeDefinitions.ADDRESS_POSTAL_LENGTH)),
    @AttributeOverride(name = "street", column = @Column(name = "DELIVERY_ADDRESS_STREET", length = TypeDefinitions.ADDRESS_STREET_LENGTH)),
    @AttributeOverride(name = "detail", column = @Column(name = "DELIVERY_ADDRESS_DETAIL", length = TypeDefinitions.ADDRESS_DETAIL_LENGTH))
  })
  Address deliveryAddress;

  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "PURCHASER_ID", length = TypeDefinitions.ID_LENGTH))
  })
  CompanyId purchaserId;


  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "RECEIVER_ID", length = TypeDefinitions.ID_LENGTH))
  })
  CompanyId receiverId;

  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "PROJECT_ID", length = TypeDefinitions.UUID_BINARY_LENGTH))
  })
  ProjectId projectId;

  @Column(length = TypeDefinitions.ENUM_LENGTH)
  @Enumerated(EnumType.STRING)
  OrderAcceptanceStatusKind status;

  @Column(length = TypeDefinitions.PHONE_NUMBER_LENGTH)
  String deliveryTelephoneNumber;

  @Column(length = TypeDefinitions.PHONE_NUMBER_LENGTH)
  String deliveryMobilePhoneNumber;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "CREATED_BY_ID", updatable = false, length = TypeDefinitions.ID_LENGTH)),
    @AttributeOverride(name = "name", column = @Column(name = "CREATED_BY_NAME", updatable = false, length = TypeDefinitions.NAME_LENGTH))
  })
  @CreatedBy
  Auditor createdBy;

  @CreatedDate
  @Column(updatable = false)
  LocalDateTime createdDate;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "LAST_MODIFIED_BY_ID", length = TypeDefinitions.ID_LENGTH)),
    @AttributeOverride(name = "name", column = @Column(name = "LAST_MODIFIED_BY_NAME", length = TypeDefinitions.NAME_LENGTH))
  })
  @LastModifiedBy
  Auditor lastModifiedBy;

  @LastModifiedDate
  LocalDateTime lastModifiedDate;


  /*
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "PROJECT_ID")
  @OrderBy("createdDate DESC")
  List<ProjectChargeEntity> charges;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "PROJECT_ID")
  @OrderBy("createdDate DESC")
  List<ProjectSaleItemEntity> saleItems;
  */

}
