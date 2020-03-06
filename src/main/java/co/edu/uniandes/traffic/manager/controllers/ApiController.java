package co.edu.uniandes.traffic.manager.controllers;

import co.edu.uniandes.traffic.manager.domain.Count;
import co.edu.uniandes.traffic.manager.services.impl.WordCountImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Owner on 2017. 03. 29..
 */
@RequestMapping("api")
@Controller
public class ApiController {

    private final WordCountImpl wordCount;

    public ApiController(WordCountImpl wordCount){
        this.wordCount = wordCount;
    }
    
    @RequestMapping("/wordcount")
    public ResponseEntity<List<Count>> words() {
        return new ResponseEntity<>(wordCount.count(), HttpStatus.OK);
    }

}
