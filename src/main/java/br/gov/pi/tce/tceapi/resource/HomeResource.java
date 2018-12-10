package br.gov.pi.tce.tceapi.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeResource {

	@GetMapping
	public String Hello() {
		return "Olá, meu povo!";
	}

	@GetMapping("/{name}")
	public String HelloPath(@PathVariable String name) {
		return "Olá, " + name + "!";
	}
}
