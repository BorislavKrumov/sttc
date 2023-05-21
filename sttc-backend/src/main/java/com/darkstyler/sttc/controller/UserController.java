package com.darkstyler.sttc.controller;

import com.darkstyler.sttc.model.dto.UserDto;
import com.darkstyler.sttc.model.dto.UserUpdateDto;
import com.darkstyler.sttc.model.entity.User;
import com.darkstyler.sttc.repository.UserRepository;
import com.darkstyler.sttc.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/manage/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    private ModelMapper modelMapper;

    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<UserDto>> viewAllUsers() {

        List<User> userList = userService.getAllUsers();
        List<UserDto> userDtoList = userList.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());

        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserUpdateDto userUpdateDto) {

        User user = userService.updateUser(modelMapper.map(userUpdateDto, User.class));

        return new ResponseEntity<>(modelMapper.map(user, UserDto.class), HttpStatus.ACCEPTED);
    }

    @PutMapping("/da")
    public ResponseEntity<User> updateUser(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
        User user = userService.updateUserIcon(file, id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    @GetMapping("/da")
//    public ModelAndView getallUsers() {
//        ModelAndView mw = new ModelAndView();
//        List<User> users = userRepository.findAll();
//        mw.setViewName("List");
//        mw.addObject("users",users);
//        return mw;
//    }

    @GetMapping("/me")
    public ResponseEntity<byte[]> getImage(@RequestParam("id") Long id) {
        User user = userRepository.getById(id);
        byte[] image = Base64.getDecoder().decode(user.getImage());
//                user.getImage();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }

    @GetMapping("/icon")
    public ResponseEntity<byte[]> getIcon(@RequestParam("id") Long id) {
        User user = userRepository.getById(id);
        byte[] image = Base64.getDecoder().decode(user.getImage());
//                user.getImage();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }
}
