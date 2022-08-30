package org.msvc.users.app.controllers;

import org.msvc.users.app.model.dtos.UserDTO;
import org.msvc.users.app.model.entities.User;
import org.msvc.users.app.model.mappers.UserMapper;
import org.msvc.users.app.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody @Valid UserDTO userDTO){
        return new ResponseEntity<>(userMapper.toDTO(userService.saveUser(userMapper.toEntity(userDTO))) , HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(@RequestParam(required = false) String name){
        if (name != null){
            return new ResponseEntity<>(userService.findAllByName(name).stream().map(userMapper::toDTO).toList(),HttpStatus.OK);
        }
        return new ResponseEntity<>(userService.findAll().stream().map(userMapper::toDTO).toList(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<User> findByName(@PathVariable String name){
        return ResponseEntity.ok(userService.findByName(name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO){
        return new ResponseEntity<>(userMapper.toDTO(userService
                .updateUser(id, userMapper.toEntity(userDTO))), HttpStatus.CREATED);
    }
}
