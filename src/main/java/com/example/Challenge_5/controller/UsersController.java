package com.example.Challenge_5.controller;

import com.example.Challenge_5.model.Users;
import com.example.Challenge_5.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping(value = "/api/users")
@RestController
public class UsersController {
    @Autowired
    UsersService usersService;

    @GetMapping(produces = "application/json")
    @Operation(summary = "Api to get all merchant")
    public List<Users> getUsers() {
        return usersService.getAllUsers();
    }

    @PostMapping(value = "add-users")
    public ResponseEntity addUsers(@RequestBody Users users){
        usersService.addUsers(users.builder()
                        .UserId(users.getUserId())
                        .UserName(users.getUserName())
                        .EmailAddress(users.getEmailAddress())
                        .Password(users.getPassword())
                .build());
        return ResponseEntity.ok("Users added successfully");
    }
    @PostMapping(value = "update-users")
    public ResponseEntity updateUsers(@RequestBody Users users) {
        Users updateUsers = usersService.updateUsers(users);
        if (updateUsers != null) {
            return new ResponseEntity<>(updateUsers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Unable to update Users", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "/detail")
    @Operation(summary = "Getting detail of one product by product code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Inputted product code not found")
    })

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{UserId}")
    public String deleteUsers(@PathVariable("UserId") String UserId) {
        usersService.deleteUsers(UserId);
        return "Delete User " + UserId + " success!";
    }

    @GetMapping("/detail/{merchantCode}")
    public ResponseEntity getUsersDetail(@PathVariable String UserId) {
        Users users = usersService.getUsersDetail(UserId);
        if (users != null) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Merchant not found", HttpStatus.NOT_FOUND);
        }
    }

}
