package pico.erp.orderacceptance.impl.jpa;


import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import pico.erp.company.data.CompanyId;
import pico.erp.orderacceptance.data.OrderAcceptanceId;
import pico.erp.orderacceptance.data.OrderAcceptanceStatusKind;
import pico.erp.project.data.ProjectId;
import pico.erp.shared.TypeDefinitions;
import pico.erp.shared.data.Address;
import pico.erp.shared.data.Auditor;
import pico.erp.user.data.UserId;

@Entity(name = "OrderAcceptance")
@Table(name = "ODA_ORDER_ACCEPTANCE")
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
    @AttributeOverride(name = "value", column = @Column(name = "ID", length = TypeDefinitions.ID_LENGTH))
  })
  OrderAcceptanceId id;

  @Column(length = TypeDefinitions.NAME_LENGTH)
  String name;

  OffsetDateTime orderedDate;

  OffsetDateTime dueDate;

  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "CUSTOMER_ID", length = TypeDefinitions.ID_LENGTH))
  })
  CompanyId customerId;

  @Column(name = "CUSTOMER_NAME", length = TypeDefinitions.NAME_LENGTH)
  String customerName;

  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "MANAGER_ID", length = TypeDefinitions.ID_LENGTH))
  })
  UserId managerId;

  @Column(name = "MANAGER_NAME", length = TypeDefinitions.NAME_LENGTH)
  String managerName;

  @Column(length = TypeDefinitions.CODE_LENGTH)
  String purchaseOrderNumber;

  @Column
  boolean deleted;

  @Column
  OffsetDateTime deletedDate;

  @Column
  OffsetDateTime acceptedDate;

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

  @Column(name = "PURCHASER_NAME", length = TypeDefinitions.NAME_LENGTH)
  String purchaserName;

  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "RECEIVER_ID", length = TypeDefinitions.ID_LENGTH))
  })
  CompanyId receiverId;

  @Column(name = "RECEIVER_NAME", length = TypeDefinitions.NAME_LENGTH)
  String receiverName;

  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "PROJECT_ID", length = TypeDefinitions.ID_LENGTH))
  })
  ProjectId projectId;

  @Column(length = TypeDefinitions.NAME_LENGTH)
  String projectName;

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
  OffsetDateTime createdDate;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "LAST_MODIFIED_BY_ID", length = TypeDefinitions.ID_LENGTH)),
    @AttributeOverride(name = "name", column = @Column(name = "LAST_MODIFIED_BY_NAME", length = TypeDefinitions.NAME_LENGTH))
  })
  @LastModifiedBy
  Auditor lastModifiedBy;

  @LastModifiedDate
  OffsetDateTime lastModifiedDate;


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
