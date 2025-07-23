package com.brasiliwood.cooperfilmes.infrastructure.api;

import com.brasiliwood.cooperfilmes.domain.movie.script.ClientContact;
import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScript;
import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScriptService;
import com.brasiliwood.cooperfilmes.infrastructure.api.dto.ClientContactDTO;
import com.brasiliwood.cooperfilmes.infrastructure.api.dto.MovieScriptRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MovieScriptControllerAPITest {

    @Mock
    private MovieScriptService service;

    @Captor
    private ArgumentCaptor<MovieScript> scriptCaptor;

    @InjectMocks
    private MovieScriptControllerAPI controller;

    @Test
    public void shouldAskServiceToSubmitMovieScriptWhenSubmittingScript() {
        MovieScriptRequest request = MovieScriptRequest.builder().text("some text")
                .contact(ClientContactDTO.builder().build()).build();

        controller.submitScript(request);

        verify(service, atLeastOnce()).submitScript(any());
    }

    @Test
    public void shouldBuildMovieScriptEntityBasedOnGivenScriptToSaveItWhenSubmittingScript() {
        ClientContactDTO contact = ClientContactDTO.builder().name("name").phone("phone").email("email").build();
        MovieScriptRequest request = MovieScriptRequest.builder().text("some text").contact(contact).build();

        controller.submitScript(request);

        verify(service, atLeastOnce()).submitScript(scriptCaptor.capture());

        assertThat(scriptCaptor.getValue(), allOf(
                instanceOf(MovieScript.class),
                hasProperty("text", equalTo(request.getText())),
                hasProperty("contact", allOf(
                        instanceOf(ClientContact.class),
                        hasProperty("name", equalTo(contact.getName())),
                        hasProperty("phone", equalTo(contact.getPhone())),
                        hasProperty("email", equalTo(contact.getEmail()))
                ))
        ));
    }

}
