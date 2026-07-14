package com.example.demo.Portfolio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Portfolio {
    @GetMapping("/me")
    public String me() {
        return """
                   <div style="text-align: center; border: 1px solid black; width: 50%; background-color:#D3D3D3; color: black;">
                        <h1> Mansvi Kumar </h1>
                           <p> I am from Patna, Bihar. Currently pursuing B.Tech CSE from Lovely Professional University </p>
                           <ul>
                                <li>Github Link: <a href="https://github.com/igmansvi/Spring" target="_blank">github.com/igmansvi/Spring</a></li>
                           </ul>
                   </div>
                   <div style="text-align: center; border: 1px solid black; width: 50%; background-color:#D3D3D3; color: black;">
                        <p> <a href="/skills">Skills</a> <a href="/education">Education</a> <a href="/projects">Projects</a>  </p>
                   </div>
                """;
    }

    @GetMapping("/skills")
    public String skills() {
        return """
                    <div style="text-align: center; border: 1px solid black; width: 50%; background-color:#D3D3D3; color: black;">
                        <h1> Tech Stack </h1>
                        <ul>
                            <li>Java</li>
                            <li>Spring</li>
                        </ul>
                    </div>
                """;
    }

    @GetMapping("/education")
    public String education() {
        return """
                    <div style="text-align: center; border: 1px solid black; width: 50%; background-color:#D3D3D3; color: black;">
                        <h1> Education </h1>
                        <ol>
                            <ul>
                                <li>B.Tech CSE, Lovely Professional University</li>
                            </ul>
                            <ul>
                                <li>Intermediate, Bihta Public School</li>
                            </ul>
                            <ul>
                                <li>Matriculation, Bihta Public School</li>
                            </ul>
                        </ol>
                    </div>
                """;
    }

    @GetMapping("/projects")
    public String projects() {
        return """
                    <div style="text-align: center; border: 1px solid black; width: 50%; background-color:#D3D3D3; color: black;">
                        <h1> Projects </h1>
                        <ul>
                            <li><a href="https://github.com/igmansvi/sadhn" target="_blank">Sadhn - Skill and Development Network Hub</a></li>
                            <li><a href="https://github.com/igmansvi/docket" target="_blank">Docket - Document Control Application</a></li>
                        </ul>
                    </div>
                """;
    }
}
