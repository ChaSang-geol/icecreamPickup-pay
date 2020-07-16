package icecreamPickup;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PaymentInformationRepository extends PagingAndSortingRepository<PaymentInformation, Long>{
    //List<PaymentInformation> findByOrderId(Long orderId);
    PaymentInformation findByOrderId(Long orderId);

}