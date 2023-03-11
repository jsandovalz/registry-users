package com.nisum.registryusers.controls;

import com.nisum.registryusers.models.entity.User;
import com.nisum.registryusers.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
public class UserController {

    @Autowired
    private IUserService _userService;

    @Operation(summary = "Registry Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Add new user creating a JWTToken ",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Some validation error is handled",
                    content = @Content)
    })
    @PostMapping(value = "/users",produces = "application/json")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        User userCreated;
        try{
            userCreated = _userService.save(user);
        }catch (DataIntegrityViolationException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email exists into database");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @Operation(summary = "List all users stored in Db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Details of All Users",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Error in the method",
                    content = @Content)
    })
    @GetMapping("/users")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(_userService.findAll());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put("Message", errorMessage);
        });
        return errors;
    }
}
