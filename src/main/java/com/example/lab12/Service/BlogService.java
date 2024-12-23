package com.example.lab12.Service;

import com.example.lab12.Api.ApiException;
import com.example.lab12.Model.Blog;
import com.example.lab12.Model.MyUser;
import com.example.lab12.Repository.AuthRepository;
import com.example.lab12.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;


    public List<Blog> getAllBlog(){
        return blogRepository.findAll();
    }

    public Blog getById(Integer id){
        return blogRepository.findBlogById(id);
    }
    public Blog getByTitle(String title){
        return blogRepository.findBlogByTitle(title);
    }

    public List<Blog> getMyBlogs(Integer userId){
        MyUser myUser = authRepository.findMyUsersById(userId);
        if(myUser == null)throw new ApiException("Error: user not found");

        return myUser.getBlogs().stream().toList();
    }

    public void addBlog(Integer userId, Blog blog){
        MyUser myUser = authRepository.findMyUsersById(userId);
        if(myUser == null)throw new ApiException("Error: user not found");

        blog.setMyUser(myUser);
        blogRepository.save(blog);
    }

    public void updateBlog(Integer userId, Blog blog ){
        MyUser myUser = authRepository.findMyUsersById(userId);
        if(myUser == null)throw new ApiException("Error: user not found");

        Blog foundBlog = blogRepository.findBlogById(blog.getId());
        if(foundBlog == null)throw new ApiException("Error: blog not found");

        if (!foundBlog.getMyUser().getRole().equalsIgnoreCase("ADMIN") && !foundBlog.getMyUser().getId().equals(userId) ) throw new ApiException("Error: this blog not belongs to you,you can't update it");

        foundBlog.setTitle(blog.getTitle());
        foundBlog.setBody(blog.getBody());
        blogRepository.save(foundBlog);
    }

    public void deleteBlog(Integer userId , Integer blogId ){
        MyUser myUser = authRepository.findMyUsersById(userId);
        if(myUser == null)throw new ApiException("Error: user not found");

        Blog foundBlog = blogRepository.findBlogById(blogId);
        if(foundBlog == null)throw new ApiException("Error: blog not found");

        if (!foundBlog.getMyUser().getRole().equalsIgnoreCase("ADMIN") && !foundBlog.getMyUser().getId().equals(userId) ) throw new ApiException("Error: this blog not belongs to you,you can't delete it");


        blogRepository.deleteById(blogId);
    }
}
