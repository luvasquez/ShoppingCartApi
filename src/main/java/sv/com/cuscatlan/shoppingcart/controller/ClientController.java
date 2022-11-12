package sv.com.cuscatlan.shoppingcart.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sv.com.cuscatlan.shoppingcart.controller.request.ClientRequest;
import sv.com.cuscatlan.shoppingcart.controller.response.ClientResponse;
import sv.com.cuscatlan.shoppingcart.service.ClientService;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor()
public class ClientController {

    @NonNull
    private final ClientService clientService;

    @GetMapping(path ="/{id}")
    @ResponseStatus(OK)
    public ClientResponse getById(@PathVariable Integer id) {
        return clientService.getById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ClientResponse save(@Valid @RequestBody final ClientRequest clientRequest) {
        return  clientService.save(clientRequest);
    }
}
