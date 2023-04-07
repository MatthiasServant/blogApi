package com.example.blogApi.Repository;

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
        System.out.println("Seeding data...");
        reactionRepository.deleteAll();
        postRepository.deleteAll();
        userRepository.deleteAll();

        IntStream stream = IntStream.rangeClosed(1, 1500);

        stream.forEach(i -> {
            User user = new User();
            user.setUsername(faker.name().username());
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            user.setEmail(faker.internet().emailAddress());
            userRepository.save(user);
            for (int j = 0; j < 10; j++) {
                Post post = new Post(faker.lorem().sentence(), (Long) user.getId());
                postRepository.save(post);
            }
        });

        for (User user : userRepository.findAll()) {
            // Pour chaque utilisateur, récupérez tous ses posts
            List<Post> posts = postRepository.findAllByAuthorId((Long) user.getId());
            // Parcourez tous les posts de l'utilisateur
            for (Post post : posts) {
                // Vérifiez si l'utilisateur n'a pas déjà réagi à ce post
                if (!reactionRepository.existsByUserIdAndPostId((Long) user.getId(), (Long) post.getId())) {
                    // Vérifiez s'il n'y a pas déjà 10 réactions pour ce post
                    if (reactionRepository.countByPostId((Long) post.getId()) < 10) {
                        // Si l'utilisateur n'a pas réagi et qu'il n'y a pas encore 10 réactions pour ce post, créez une nouvelle réaction
                        Reaction reaction = new Reaction();
                        reaction.setUserId((Long) user.getId());
                        reaction.setPostId((Long) post.getId());
                        reaction.setType(reactionTypes[PRNG.nextInt(reactionTypes.length)]);
                        reactionRepository.save(reaction);
                    }
                }
            }
        }
        System.out.println("Seeding data complete!");
    }
}

