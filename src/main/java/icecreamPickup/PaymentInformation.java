package icecreamPickup;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="PaymentInformation_table")
public class PaymentInformation {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private String paymentStatus;

    @PostUpdate
    public void onPostUpdate(){
        if (this.getPaymentStatus().equals("CANCEL")) {
            PaymentCanceled paymentCanceled = new PaymentCanceled();
            BeanUtils.copyProperties(this, paymentCanceled);
            paymentCanceled.publishAfterCommit();
        }

    }

    @PostPersist
    public void onPostPersist(){
        if (this.getPaymentStatus().equals("APPROVED")) {
            //this.setPaymentStatus("APPROVED");
            PaymentApproved paymentApproved = new PaymentApproved();
            BeanUtils.copyProperties(this, paymentApproved);
            paymentApproved.publishAfterCommit();
        }

    }
    @PrePersist
    public void onPrePersist(){
        if (this.getPaymentStatus().equals("ORDER")) {
            this.setPaymentStatus("APPROVED");

        }

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }




}