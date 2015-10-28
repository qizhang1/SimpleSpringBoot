@RestController
class SimpleGroovy {

    @RequestMapping("/")
    String home() {
        return "Hello Groovy!"
    }
}