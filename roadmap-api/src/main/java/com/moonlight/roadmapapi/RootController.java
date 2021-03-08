package com.moonlight.roadmapapi;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@RestController
@CrossOrigin
public class RootController {
    @GetMapping("/ping")
    @ResponseBody
    public String ping() {
        return "OK - " + LocalDateTime.now().toString();
    }
}
