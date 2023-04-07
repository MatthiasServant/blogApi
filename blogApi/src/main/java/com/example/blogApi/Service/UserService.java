package com.example.blogApi.Service;

import com.example.blogApi.Model.User;
import com.example.blogApi.Repository.PostRepository;
import com.example.blogApi.Repository.ReactionRepository;
import com.example.blogApi.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ReactionRepository reactionRepository;

    public UserService(UserRepository userRepository, PostRepository postRepository, ReactionRepository reactionRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.reactionRepository = reactionRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User updateUser(Long id, User user) {
        userRepository.findById(id).ifPresent(u -> {
            u.setUsername(user.getUsername());
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            u.setEmail(user.getEmail());
            userRepository.save(u);
        });
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public void deleteUser(Long id) {
        //delete user reactions and posts before deleting the user
        reactionRepository.deleteAll(reactionRepository.findAllByUserId(id));
        postRepository.deleteAll(postRepository.findAllByAuthorId(id));
        userRepository.deleteById(id);
    }
}
