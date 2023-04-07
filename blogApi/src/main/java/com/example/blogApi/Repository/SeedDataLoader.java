package com.example.blogApi.Repository;

import com.example.blogApi.Model.*;
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

    private final ReportRepository reportRepository;
    private final Faker faker;

    private final ReactionType[] reactionTypes;

    private static final Random PRNG = new Random();

    public SeedDataLoader(UserRepository userRepository, ReactionRepository reactionRepository, PostRepository postRepository, ReportRepository reportRepository) {
        this.userRepository = userRepository;
        this.reactionRepository = reactionRepository;
        this.postRepository = postRepository;
        this.reportRepository = reportRepository;
        reactionTypes = ReactionType.values();
        this.faker = new Faker();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Seeding data...");
        reportRepository.deleteAll();
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

        List<User> users = userRepository.findAll();
        List<Post> posts = postRepository.findAll();
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            Long userId = (Long) users.get(random.nextInt(users.size())).getId();
            Long postId = (Long) posts.get(random.nextInt(posts.size())).getId();

            if (!reportRepository.existsByUserIdAndPostId(userId, postId)) {
                Report report = new Report(userId, postId, faker.lorem().sentence());
                reportRepository.save(report);
            }
        }

        System.out.println("Seeding data complete!");
    }
}

