package com.brasiliwood.cooperfilmes.domain.movie.script;

import com.brasiliwood.cooperfilmes.domain.user.User;
import com.brasiliwood.cooperfilmes.infrastructure.repository.MovieScriptEntity;
import com.brasiliwood.cooperfilmes.infrastructure.repository.MovieScriptRepository;
import com.brasiliwood.cooperfilmes.infrastructure.repository.UserEntity;
import com.brasiliwood.cooperfilmes.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieScriptServiceTest {

    @Mock
    private MovieScriptRepository repository;

    @Mock
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<MovieScriptEntity> entityCaptor;

    @InjectMocks
    private MovieScriptService service;

    @Test
    public void shouldAskRepositoryToSaveMovieScriptWhenSubmittingScript() {
        MovieScript script = MovieScript.builder().text("some text").status(MovieScript.MovieScriptStatus.IN_ANALYSIS).contact(ClientContact.builder().build()).build();
        MovieScriptEntity entity = MovieScriptEntity.of(script);
        when(repository.save(any())).thenReturn(entity);

        service.saveScript(script);

        verify(repository, atLeastOnce()).save(any());
    }

    @Test
    public void shouldBuildMovieScriptEntityBasedOnGivenScriptToSaveItWhenSubmittingScript() {
        ClientContact contact = ClientContact.builder()
                .name("name")
                .phone("phone")
                .email("email").build();
        MovieScript script = MovieScript.builder()
                .text("some text")
                .status(MovieScript.MovieScriptStatus.IN_ANALYSIS)
                .contact(contact).build();
        MovieScriptEntity entity = MovieScriptEntity.of(script);
        when(repository.save(any())).thenReturn(entity);

        service.saveScript(script);

        verify(repository, atLeastOnce()).save(entityCaptor.capture());

        assertThat(entityCaptor.getValue(), allOf(
                instanceOf(MovieScriptEntity.class),
                hasProperty("id", equalTo(script.getId())),
                hasProperty("text", equalTo(script.getText())),
                hasProperty("status", equalTo(entity.getStatus())),
                hasProperty("clientName", equalTo(contact.getName())),
                hasProperty("clientPhone", equalTo(contact.getPhone())),
                hasProperty("clientEmail", equalTo(contact.getEmail()))
        ));
    }

    @Test
    public void shouldReturnMovieScriptBasedOnRetrievedEntityWithIdWhenSavedMovieScriptEntity() {
        ClientContact contact = ClientContact.builder().name("name").phone("phone").email("email").build();
        MovieScript script = MovieScript.builder().id(1).text("some text").status(MovieScript.MovieScriptStatus.IN_ANALYSIS).contact(contact).build();
        MovieScriptEntity entity = MovieScriptEntity.of(script);
        when(repository.save(any())).thenReturn(entity);

        MovieScript result = service.saveScript(script);

        assertThat(result, allOf(
                instanceOf(MovieScript.class),
                hasProperty("id", equalTo(entity.getId())),
                hasProperty("text", equalTo(entity.getText())),
                hasProperty("status", equalTo(entity.getStatus().getDomain())),
                hasProperty("contact", allOf(
                        instanceOf(ClientContact.class),
                        hasProperty("name", equalTo(contact.getName())),
                        hasProperty("phone", equalTo(contact.getPhone())),
                        hasProperty("email", equalTo(contact.getEmail()))
                ))
        ));
    }

    @Test
    public void shouldAskRepositoryToFindMovieScriptByIdWhenFindingScript() {
        Integer scriptId = 1;
        MovieScriptEntity entity = new MovieScriptEntity(
                scriptId, "text","name", "phone", "email",
                MovieScriptEntity.MovieScriptStatus.AGUARDANDO_ANALISE, null);
        when(repository.findById(eq(scriptId))).thenReturn(Optional.of(entity));

        service.findScriptById(scriptId);

        verify(repository, atLeastOnce()).findById(scriptId);
    }

    @Test
    public void shouldReturnMovieScriptBasedOnRetrievedEntityWithIdWhenFindingScript() {
        Integer scriptId = 1;
        Integer userId = 2;
        UserEntity user = new UserEntity(userId, "Me", "email@email.com", "pass", UserEntity.UserPosition.ANALYST);
        MovieScriptEntity entity = new MovieScriptEntity(
                scriptId, "text",
                "name", "phone", "email",
                MovieScriptEntity.MovieScriptStatus.AGUARDANDO_ANALISE, userId);
        when(repository.findById(eq(scriptId))).thenReturn(Optional.of(entity));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        MovieScript result = service.findScriptById(scriptId);

        assertThat(result, allOf(
                instanceOf(MovieScript.class),
                hasProperty("id", equalTo(entity.getId())),
                hasProperty("text", equalTo(entity.getText())),
                hasProperty("status", equalTo(entity.getStatus().getDomain())),
                hasProperty("contact", allOf(
                        instanceOf(ClientContact.class),
                        hasProperty("name", equalTo(entity.getClientName())),
                        hasProperty("phone", equalTo(entity.getClientPhone())),
                        hasProperty("email", equalTo(entity.getClientEmail()))
                )),
                hasProperty("user", allOf(
                        instanceOf(User.class),
                        hasProperty("id", equalTo(user.getId())),
                        hasProperty("name", equalTo(user.getName())),
                        hasProperty("email", equalTo(user.getEmail())),
                        hasProperty("password", equalTo(user.getPassword())),
                        hasProperty("position", equalTo(user.getUserPosition().getDomain()))
                ))
        ));
    }

}
