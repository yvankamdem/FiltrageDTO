package tech.chillo.sa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.chillo.sa.dto.ClientDTO;
import tech.chillo.sa.entites.Client;
import tech.chillo.sa.service.ClientService;

import java.util.stream.Stream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "client")
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseBody
    public String creer(@RequestBody Client client){
        Client clientCree = this.clientService.creer(client);
        return "Client créé avec succès !!!";
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Stream<ClientDTO> rechercher() {
        return this.clientService.rechercher();
    }

    @GetMapping(path="{id}", produces = APPLICATION_JSON_VALUE)
    public Client lire(@PathVariable int id) {
        return this.clientService.lire(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping(path = "{id}", consumes = APPLICATION_JSON_VALUE, produces = "text/plain")
    @ResponseBody
    public String modifier(@PathVariable int id, @RequestBody Client client) {
        this.clientService.modifier(id, client);
        return "Client modifié avec succès !!!";
    }

}
