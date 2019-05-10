package travelersMock.controller;


import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travelersMock.helpers.Validations;
import travelersMock.model.Email;
import travelersMock.utilities.RandomGenerator;

import java.util.HashMap;
import java.util.Map;

@RestController
public class emailServiceController {
    public static Map<String, String> emailMap = new HashMap<>();
    private Gson gson = new Gson();

    @RequestMapping(value = "/generateToken", method = RequestMethod.POST)
    public ResponseEntity<Object> generateToken(@RequestBody Email mail) {
        Map<String, String> token = new HashMap<>();
        String randomNumber = String.valueOf(RandomGenerator.generateRandomNumber(8000, 1000));
        token.put("token", randomNumber);
        emailMap.put(mail.getEmail(), randomNumber);
        return new ResponseEntity<>(gson.toJson(token), HttpStatus.OK);
    }

    @RequestMapping(value = "/getToken/{email}", method = RequestMethod.GET)
    public ResponseEntity<Object> getToken(@PathVariable("email") Email email) {
        return new ResponseEntity<>(emailMap.get(email.getEmail()), HttpStatus.OK);
    }

    @RequestMapping(value = "/validateToken", method = RequestMethod.POST)
    public ResponseEntity<Object> validateToken(@RequestBody Email email) {
        Map<String, Boolean> validation = new HashMap<>();
        validation.put("validation",Validations.isTheSameToken(email.getEmail(), email.getToken()));
        return new ResponseEntity<>(gson.toJson(validation), HttpStatus.OK);
    }

    @RequestMapping(value = "/emailList", method = RequestMethod.GET)
    public ResponseEntity<Object> getEmailList() {
        System.out.println(emailMap);
        return new ResponseEntity<>(gson.toJson(emailMap), HttpStatus.OK);
    }
}
