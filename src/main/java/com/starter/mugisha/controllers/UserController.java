package com.starter.mugisha.controllers;

import com.starter.mugisha.dtos.UserUpdateDto;
import com.starter.mugisha.models.User;
import com.starter.mugisha.payload.ApiResponse;
import com.starter.mugisha.services.UserService;
import com.starter.mugisha.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieves a paginated list of all users")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved users"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid pagination parameters")
    })
    public Page<User> getUsers(
            @Parameter(description = "Page number (0-based)") @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @Parameter(description = "Number of items per page") @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int size,
            @Parameter(description = "Column to sort by") @RequestParam(value = "column") String column) {
        return userService.getAllUsers(page, size, column);
    }

    @DeleteMapping(path = "/{userId}")
    @Operation(summary = "Delete user", description = "Deletes a user by their ID")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User deleted successfully"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<ApiResponse> deleteUser(
            @Parameter(description = "User ID", required = true) @PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(new ApiResponse(true, "user removed successfully", userService.deleteUser(userId)));
    }

    @PutMapping(path = "/{userId}")
    @Operation(summary = "Update user", description = "Updates an existing user's information")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "User updated successfully"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "User not found"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<ApiResponse> updateUser(
            @Parameter(description = "User ID", required = true) @PathVariable("userId") UUID userId,
            @Valid @RequestBody UserUpdateDto userdataRequest) {
        userService.updateUser(userId, userdataRequest);
        User user = userService.getUser(userId);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{email}").buildAndExpand(user.getEmail()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "user updated successfully", user));
    }

    @GetMapping(path = "/{userId}")
    @Operation(summary = "Get user by ID", description = "Retrieves a specific user by their ID")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<ApiResponse> getUser(
            @Parameter(description = "User ID", required = true) @PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(new ApiResponse(true, "user found", this.userService.getUser(userId)));
    }
}
