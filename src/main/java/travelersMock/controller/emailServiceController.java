package travelersMock.controller;


import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travelersMock.model.CodeTravelers;

import java.util.HashMap;
import java.util.Map;

@RestController
public class emailServiceController {
    public static Map<String, String> emailMap = new HashMap<>();
    private Gson gson = new Gson();

    @PostMapping(value = "/generateToken")
    public ResponseEntity<Object> generateToken(@RequestBody CodeTravelers codeTravelers) {
        emailMap.put(codeTravelers.getEmail(), codeTravelers.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getToken")
    public ResponseEntity<Object> getToken(@RequestBody CodeTravelers codeTravelers) {
        return new ResponseEntity<>(emailMap.get(codeTravelers.getEmail()), HttpStatus.OK);
    }

    @GetMapping(value = "/emailList")
    public ResponseEntity<Object> getEmailList() {
        return new ResponseEntity<>(gson.toJson(emailMap), HttpStatus.OK);
    }
}
