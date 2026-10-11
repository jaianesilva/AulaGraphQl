package com.example.graphql;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Controller;

import graphql.GraphQLError;

@Controller
public class UserController {

    // "Banco de dados" em memória (a chave é o ID)
    private final Map<Long, Human> humans = new LinkedHashMap<>();
    private final Map<Long, Droid> droids = new LinkedHashMap<>();
    private final Map<Long, Starship> starships = new LinkedHashMap<>();
    private final List<Review> reviews = new ArrayList<>();

    public UserController() {
        // Dados iniciais para facilitar os testes no GraphiQL
        Human luke = new Human(1L, "Luke Skywalker", 1.72);
        Human han = new Human(2L, "Han Solo", 1.80);
        Human leia = new Human(3L, "Leia Organa", 1.50);
        Droid r2d2 = new Droid(4L, "R2-D2", "Astromech");
        Droid c3po = new Droid(5L, "C-3PO", "Protocol");

        luke.getFriends().addAll(List.of(han, leia, r2d2, c3po));
        han.getFriends().addAll(List.of(luke, leia));
        leia.getFriends().addAll(List.of(luke, han));
        r2d2.getFriends().add(luke);
        c3po.getFriends().add(luke);

        for (Human h : List.of(luke, han, leia)) {
            humans.put(h.getId(), h);
        }
        for (Droid d : List.of(r2d2, c3po)) {
            droids.put(d.getId(), d);
        }

        starships.put(1L, new Starship(1L, "Millennium Falcon", 34.37));
        starships.put(2L, new Starship(2L, "X-Wing", 12.5));
    }

    // ------------------------------------------------------------------
    // Queries já existentes
    // ------------------------------------------------------------------

    @QueryMapping
    public User me() {
        return new User("John Doe");
    }

    @QueryMapping
    public Hero hero() {
        return new Hero(1L, "Superman");
    }

    @QueryMapping
    public Droid droid(@Argument Long id) {
        return droids.get(id);
    }

    @QueryMapping
    public Review review(@Argument Long id) {
        return reviews.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
    }

    // ------------------------------------------------------------------
    // Novas queries (Exercício 1)
    // ------------------------------------------------------------------

    @QueryMapping
    public List<Human> humans() {
        return new ArrayList<>(humans.values());
    }

    @QueryMapping
    public List<Starship> starships() {
        return new ArrayList<>(starships.values());
    }

    @QueryMapping
    public BaseCharacter character(@Argument Long id) {
        return findCharacter(id);
    }

    // ------------------------------------------------------------------
    // Mutation já existente
    // ------------------------------------------------------------------

    @MutationMapping
    public Review createReview(@Argument ReviewInput review) {
        Review new_review = new Review(1L, review.getStars(), review.getCommentary());
        reviews.add(new_review);
        return new_review;
    }

    // ------------------------------------------------------------------
    // Novas mutations (Exercício 2)
    // ------------------------------------------------------------------

    @MutationMapping
    public Human createHuman(@Argument Long id, @Argument String name, @Argument Double height) {
        ensureCharacterIdIsFree(id);
        Human human = new Human(id, name, height);
        humans.put(id, human);
        return human;
    }

    @MutationMapping
    public Droid createDroid(@Argument Long id, @Argument String name, @Argument String primaryFunction) {
        ensureCharacterIdIsFree(id);
        Droid droid = new Droid(id, name, primaryFunction);
        droids.put(id, droid);
        return droid;
    }

    @MutationMapping
    public Starship createStarship(@Argument Long id, @Argument String name, @Argument Double length) {
        if (starships.containsKey(id)) {
            throw new IllegalArgumentException("Já existe uma nave com o id " + id);
        }
        Starship starship = new Starship(id, name, length);
        starships.put(id, starship);
        return starship;
    }

    @MutationMapping
    public BaseCharacter addFriend(@Argument Long characterId, @Argument Long friendId) {
        BaseCharacter character = findCharacter(characterId);
        if (character == null) {
            throw new IllegalArgumentException("Personagem não encontrado: " + characterId);
        }
        BaseCharacter friend = findCharacter(friendId);
        if (friend == null) {
            throw new IllegalArgumentException("Amigo não encontrado: " + friendId);
        }
        if (character == friend) {
            throw new IllegalArgumentException("Um personagem não pode ser amigo de si mesmo");
        }
        if (!character.getFriends().contains(friend)) {
            character.getFriends().add(friend);
        }
        return character;
    }

    // ------------------------------------------------------------------
    // Auxiliares
    // ------------------------------------------------------------------

    /** Procura em humanos e droids (ambos são Character). */
    private BaseCharacter findCharacter(Long id) {
        Human human = humans.get(id);
        if (human != null) {
            return human;
        }
        return droids.get(id);
    }

    private void ensureCharacterIdIsFree(Long id) {
        if (findCharacter(id) != null) {
            throw new IllegalArgumentException("Já existe um personagem com o id " + id);
        }
    }

    /** Devolve erros de validação como BAD_REQUEST com a mensagem original. */
    @GraphQlExceptionHandler
    public GraphQLError handleIllegalArgument(IllegalArgumentException ex) {
        return GraphQLError.newError()
                .errorType(ErrorType.BAD_REQUEST)
                .message(ex.getMessage())
                .build();
    }
}
