package ke.co.safaricom.blog.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/books")
public class BookController {
    @GetMapping
    public String index(){
        return "books";
    }
}