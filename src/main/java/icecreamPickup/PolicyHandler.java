package icecreamPickup;

import icecreamPickup.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyHandler{
    @Autowired
    private PaymentInformationRepository paymentInformationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverIceCreamOrderCanceled_PamentCancelReq(@Payload IceCreamOrderCanceled iceCreamOrderCanceled){

        if(iceCreamOrderCanceled.isMe()){

            System.out.println("##### listener PamentCancelReq : " + iceCreamOrderCanceled.toJson());
            // 객체 조회
            PaymentInformation paymentInformation = paymentInformationRepository.findByOrderId(iceCreamOrderCanceled.getId());

                // 상태값 set 함
                paymentInformation.setPaymentStatus("CANCEL");
                // 레파지 토리에 save
                paymentInformationRepository.save(paymentInformation);
            }
        }

}
