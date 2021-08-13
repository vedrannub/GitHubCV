package mk.ukim.finki.emt.deviceshop.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class FrontPageController {

    @GetMapping("hello")
    public String hello(@RequestParam(value = "param" ,required = false) String param,
                        @SessionAttribute("firstName") String firstName,
                        HttpServletRequest req,
                        HttpServletResponse res,
                        Model model) {
        model.addAttribute("firstName",firstName);
        model.addAttribute("requestParam", param);
        return "hello";
    }

    @GetMapping("hello-path/param/{param}")
    public String helloPath(@PathVariable("param") String pathParam,
                          Model model) {
        model.addAttribute("pathParam",pathParam);
        return "hello";
    }


}
