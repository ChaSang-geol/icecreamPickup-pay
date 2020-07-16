package icecreamPickup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class PaymentInformationController {
    @Autowired
    PaymentInformationRepository paymentInformationRepository;


    /*
    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public void init(@RequestBody UserVO userVO) {

    	userVO.getName(); // "kim"
        userVO.getAge(); // 30
    }


     */
    @ResponseBody
    @RequestMapping(value = "/paymentInformations", method = RequestMethod.POST)
    //@RequestMapping(method=RequestMethod.POST, path="/paymentInformations")
    public void paymentCreate(@RequestBody PaymentInformation paymentInformation) {
        PaymentInformation paymentInformationCreate = new PaymentInformation();
        paymentInformationCreate=paymentInformation;
        paymentInformationCreate.setOrderId(paymentInformation.getOrderId());
        //paymentInformationCreate.setPaymentStatus("APPROVED");
       paymentInformationRepository.save(paymentInformationCreate);

    }
    @RequestMapping(method=RequestMethod.GET, path="/paymentInformations")
    public PaymentInformation paymentRead(@RequestBody PaymentInformation paymentInformation) {
        PaymentInformation paymentInformationList = paymentInformationRepository.findByOrderId(paymentInformation.getOrderId());
        return paymentInformationList;

    }
}
