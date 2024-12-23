package com.example.lab12.Controller;

import com.example.lab12.Api.ApiResponse;
import com.example.lab12.Model.Blog;
import com.example.lab12.Model.MyUser;
import com.example.lab12.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/my-blogs")
    public ResponseEntity getMyBlogs(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.ok(blogService.getMyBlogs(myUser.getId()));
    }
    @GetMapping("/get")
    public ResponseEntity getAllBlog(){
        return ResponseEntity.ok(blogService.getAllBlog());
    }
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity getById(@PathVariable Integer id){
        return ResponseEntity.ok(blogService.getById(id));
    }
    @GetMapping("/get-by-title/{title}")
    public ResponseEntity getByTitle(@PathVariable String title){
        return ResponseEntity.ok(blogService.getByTitle(title));
    }


    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid Blog blog){

        blogService.addBlog(myUser.getId(),blog);
        return ResponseEntity.status(201).body(new ApiResponse("Added successfully"));
    }
    @PutMapping("/update")
    public ResponseEntity updateBlog(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid Blog blog){

        blogService.updateBlog(myUser.getId(),blog);
        return ResponseEntity.status(200).body(new ApiResponse("updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal MyUser myUser , @PathVariable Integer id){
        blogService.deleteBlog(myUser.getId(), id);
        return ResponseEntity.status(200).body(new ApiResponse("deleted successfully"));
    }

}
