package com.r1.rulesRepoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Rules")
public class RulesRepoController {

    @GetMapping("/{id}")
    public String read(@PathVariable String id) {
        return "hello world";
    }
}
