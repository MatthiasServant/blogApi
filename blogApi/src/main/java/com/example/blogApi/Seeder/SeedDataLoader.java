package com.example.blogApi.Seeder;

import com.example.blogApi.Model.Post;
import com.example.blogApi.Model.Reaction;
import com.example.blogApi.Model.ReactionType;
import com.example.blogApi.Model.User;
import com.example.blogApi.Repository.PostRepository;
import com.example.blogApi.Repository.ReactionRepository;
import com.example.blogApi.Repository.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Component
public class SeedDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ReactionRepository reactionRepository;
    private final PostRepository postRepository;
    private final Faker faker;

    private final ReactionType[] reactionTypes;

    private static final Random PRNG = new Random();

    public SeedDataLoader(UserRepository userRepository, ReactionRepository reactionRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.reactionRepository = reactionRepository;
        this.postRepository = postRepository;
        reactionTypes = ReactionType.values();
        this.faker = new Faker();
    }

    @Override
    public void run(String... args) throws Exception {
        /*List<User> users = IntStream.rangeClosed(1,1500).mapToObj(i -> {
            User user = new User();
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            user.setEmail(faker.internet().emailAddress());
            user.setUsername(faker.name().username());
            for(int j = 0; j < 10; j++) {
                Post post = new Post(faker.lorem().sentence());
                for(int k = 0; k < 10; k++) {
                    Reaction reaction = new Reaction(reactionTypes[PRNG.nextInt(reactionTypes.length)]);
                    post.addReaction(reaction);
                }
                user.addPost(post);
            }
            return user;
        }).toList();

        userRepository.saveAll(users);*/
    }
}

