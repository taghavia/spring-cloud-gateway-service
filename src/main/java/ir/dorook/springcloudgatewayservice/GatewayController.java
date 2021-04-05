package ir.dorook.springcloudgatewayservice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GatewayController {

    @RequestMapping("/countriesfallback")
    public String countries(){
        return "Countries API is down";
    }

    @RequestMapping("/jok1fallback")
    public String jok1(){
        return "Jok1 API is down";
    }

    @RequestMapping("/jok2fallback")
    public String jok2(){
        return "Jok2 API is down";
    }
}
