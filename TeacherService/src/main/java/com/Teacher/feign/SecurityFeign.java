package com.Teacher.feign;
import com.Teacher.Others.UserDto;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name="QUIZSECURITY")
public interface SecurityFeign {
    @PutMapping("/update-profile-pic/{email}")
    ResponseEntity<String> updateProfilePic(@PathVariable String email,@RequestParam("file") MultipartFile file);
    @PutMapping("/update-profile/{email}")
    ResponseEntity<String> updateUserProfile(@PathVariable String email, @RequestBody UserDto updatedData);
}
