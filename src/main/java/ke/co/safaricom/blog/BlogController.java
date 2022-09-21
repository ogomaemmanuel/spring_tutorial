package ke.co.safaricom.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "blog")
public class BlogController {

    @GetMapping
    @ResponseBody
    public Map<String, String> index() {
        Map<String,String> books = new HashMap<String,String>();
        books.put("1", "Bookk1");
        books.put("2", "Book 2");
        books.put("3", "Book3");
        return books;
    }

//    @GetMapping(value = "/archived")
    @ResponseBody
    @RequestMapping(value = "/archived", method = RequestMethod.POST)
    public Map<String, String> archived() {
        Map<String,String> books = new HashMap<String,String>();
        books.put("1", "Book1 ");
        books.put("2", "Book2");
        books.put("3", "Book3 ");
        return books;
    }

    @GetMapping(value = "/archived/{bookID}")
    @ResponseBody
    public String getBookByID(@PathVariable String bookID) {
        Map<String,String> books = new HashMap<String,String>();
        books.put("1", "Book1 ");
        books.put("2", "Book2");
        books.put("3", "Book3 ");
        return books.get(bookID);
    }
}
