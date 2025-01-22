package com.franzoso.controllers;

import com.franzoso.entities.Person;
import com.franzoso.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;

@RestController
@RequestMapping(value = "/testes-junit5", produces = {"application/json"})
@Slf4j
@Tag(name = "open-api")
public class PessoaController {

    private final PersonService service;

    public PessoaController(PersonService service) {
        this.service = service;
    }

    @Operation(summary = "Find person by Social Security Number", method = "GET")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search successful"),
            @ApiResponse(responseCode = "422", description = "Invalid request data"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters"),
            @ApiResponse(responseCode = "500", description = "Error searching for data"),
    })
    @GetMapping("/socialSecurityNumber")
    @CrossOrigin(allowedHeaders = "*")
    public ResponseEntity<List<Person>> findPersonBySocialSecurityNumber(@RequestParam("socialSecurityNumber") String socialSecurityNumber) {
        log.info(format("Searching for person data by Social Security Number = %s!", socialSecurityNumber));

        return ResponseEntity.ok(service.findPersonBySocialSecurityNumber(socialSecurityNumber));

    }
}


