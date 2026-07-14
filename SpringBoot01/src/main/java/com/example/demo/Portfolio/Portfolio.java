package com.example.demo.Portfolio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Portfolio {
    @GetMapping("/me")
    public String me() {
        return """
                   <div style="text-align: center; border: 1px solid black; width: 50%; background-color: #FF00FF; color: black;">
                        <h1> Mansvi Kumar </h1>
                           <p> I am from Patna, Bihar. Currently pursuing B.Tech CSE from Lovely Professional University </p>
                           <h3> Tech Stack <h3>
                           <ol>
                                <li>Java</li>
                                <li>Spring</li>
                           </ol>
                           <ul>
                                <li>Github Link: <a href='https://github.com/igmansvi/Spring' target='_blank'>github.com/igmansvi/Spring</a></li>
                           </ul>
                   </div>
                """;
    }
}
