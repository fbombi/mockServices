package travelersMock.controller;


import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class emailServiceController {
    private static Map<String, String> emailMap = new HashMap<>();
    private Gson gson = new Gson();

    @RequestMapping(value = "/generateToken", method = RequestMethod.POST)
    public ResponseEntity<Object> generateToken(@RequestBody String mail) {
        Map<String, String> token = new HashMap<>();
        int randomNumber = (int) (Math.random() * 8999 + 1000);
        token.put("token", String.valueOf(randomNumber));
        Gson gson = new Gson();
        emailMap.put(mail, Integer.toString(randomNumber));
        return new ResponseEntity<>(gson.toJson(token), HttpStatus.OK);
    }

    @RequestMapping(value = "/getToken/{email}", method = RequestMethod.GET)
    public ResponseEntity<Object> getToken(@PathVariable("email") String email) {
        return new ResponseEntity<>(emailMap.get(email), HttpStatus.OK);
    }

    @RequestMapping(value = "/emailList", method = RequestMethod.GET)
    public ResponseEntity<Object> getEmailList() {
        return new ResponseEntity<>(gson.toJson(emailMap), HttpStatus.OK);
    }
}
