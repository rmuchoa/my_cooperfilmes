package com.brasiliwood.cooperfilmes.domain.movie.script;

import com.brasiliwood.cooperfilmes.infrastructure.repository.MovieScriptEntity;
import com.brasiliwood.cooperfilmes.infrastructure.repository.MovieScriptRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MovieScriptServiceTest {

    @Mock
    private MovieScriptRepository repository;

    @Captor
    private ArgumentCaptor<MovieScriptEntity> entityCaptor;

    @InjectMocks
    private MovieScriptService service;

    @Test
    public void shouldAskRepositoryToSaveMovieScriptWhenSubmittingScript() {
        MovieScript script = MovieScript.builder().text("some text").contact(ClientContact.builder().build()).build();

        service.submitScript(script);

        verify(repository, atLeastOnce()).save(any());
    }

    @Test
    public void shouldBuildMovieScriptEntityBasedOnGivenScriptToSaveItWhenSubmittingScript() {
        ClientContact contact = ClientContact.builder().name("name").phone("phone").email("email").build();
        MovieScript script = MovieScript.builder().text("some text").contact(contact).build();

        service.submitScript(script);

        verify(repository, atLeastOnce()).save(entityCaptor.capture());

        assertThat(entityCaptor.getValue(), allOf(
                instanceOf(MovieScriptEntity.class),
                hasProperty("id", equalTo(script.getId())),
                hasProperty("text", equalTo(script.getText())),
                hasProperty("clientName", equalTo(contact.getName())),
                hasProperty("clientPhone", equalTo(contact.getPhone())),
                hasProperty("clientEmail", equalTo(contact.getEmail()))
        ));
    }

}
