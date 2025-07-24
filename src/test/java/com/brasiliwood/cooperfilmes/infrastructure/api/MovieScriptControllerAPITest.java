package com.brasiliwood.cooperfilmes.infrastructure.api;

import com.brasiliwood.cooperfilmes.domain.movie.script.ClientContact;
import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScript;
import com.brasiliwood.cooperfilmes.domain.movie.script.MovieScriptService;
import com.brasiliwood.cooperfilmes.infrastructure.api.dto.ClientContactDTO;
import com.brasiliwood.cooperfilmes.infrastructure.api.dto.MovieScriptRequest;
import com.brasiliwood.cooperfilmes.infrastructure.api.dto.MovieScriptResponse;
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
import static org.mockito.Mockito.*;

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
        MovieScriptRequest request = MovieScriptRequest.builder().contact(ClientContactDTO.builder().build()).build();
        MovieScript script = MovieScript.builder().status(MovieScript.MovieScriptStatus.IN_ANALYSIS).contact(ClientContact.builder().build()).build();
        when(service.saveScript(any())).thenReturn(script);

        controller.submitScript(request);

        verify(service, atLeastOnce()).saveScript(any());
    }

    @Test
    public void shouldBuildMovieScriptEntityBasedOnGivenScriptToSaveItWhenSubmittingScript() {
        ClientContactDTO contact = ClientContactDTO.builder().name("name").phone("phone").email("email").build();
        MovieScriptRequest request = MovieScriptRequest.builder().text("some text").contact(contact).build();
        MovieScript script = MovieScript.builder().id(1).text(request.getText()).status(MovieScript.MovieScriptStatus.IN_ANALYSIS)
                .contact(ClientContact.builder().name(contact.getName()).phone(contact.getPhone()).email(contact.getEmail()).build()).build();
        when(service.saveScript(any())).thenReturn(script);

        controller.submitScript(request);

        verify(service, atLeastOnce()).saveScript(scriptCaptor.capture());

        assertThat(scriptCaptor.getValue(), allOf(
                instanceOf(MovieScript.class),
                hasProperty("text", equalTo(request.getText())),
                hasProperty("status", equalTo(MovieScript.MovieScriptStatus.WAITING_FOR_ANALYSIS)),
                hasProperty("contact", allOf(
                        instanceOf(ClientContact.class),
                        hasProperty("name", equalTo(contact.getName())),
                        hasProperty("phone", equalTo(contact.getPhone())),
                        hasProperty("email", equalTo(contact.getEmail()))
                ))
        ));
    }

    @Test
    public void shouldReturnMovieScriptResponseBasedOnSavedMovieScriptWhenSubmittingScript() {
        ClientContactDTO contact = ClientContactDTO.builder().name("name").phone("phone").email("email").build();
        MovieScriptRequest request = MovieScriptRequest.builder().text("some text").contact(contact).build();
        MovieScript script = MovieScript.builder().id(1).text(request.getText()).status(MovieScript.MovieScriptStatus.IN_ANALYSIS)
                .contact(ClientContact.builder().name(contact.getName()).phone(contact.getPhone()).email(contact.getEmail()).build()).build();
        when(service.saveScript(any())).thenReturn(script);

        MovieScriptResponse result = controller.submitScript(request);

        assertThat(result, allOf(
                instanceOf(MovieScriptResponse.class),
                hasProperty("text", equalTo(request.getText())),
                hasProperty("status", equalTo(script.getStatus().name())),
                hasProperty("contact", allOf(
                        instanceOf(ClientContactDTO.class),
                        hasProperty("name", equalTo(contact.getName())),
                        hasProperty("phone", equalTo(contact.getPhone())),
                        hasProperty("email", equalTo(contact.getEmail()))
                ))
        ));
    }

    @Test
    public void shouldAskServiceToFindScriptByIdWhenFindingScript() {
        Integer scriptId = 1;
        MovieScript script = MovieScript.builder().id(scriptId).status(MovieScript.MovieScriptStatus.IN_ANALYSIS)
                .contact(ClientContact.builder().build()).build();
        when(service.findScriptById(eq(scriptId))).thenReturn(script);

        controller.checkScriptSubmission(scriptId);

        verify(service, atLeastOnce()).findScriptById(eq(scriptId));
    }

    @Test
    public void shouldReturnMovieScriptResponseBasedOnFoundMovieScriptWhenFindingScript() {
        Integer scriptId = 1;
        ClientContact contact = ClientContact.builder().name("name").phone("phone").email("email").build();
        MovieScript script = MovieScript.builder().id(scriptId).text("some text")
                .status(MovieScript.MovieScriptStatus.IN_ANALYSIS)
                .contact(contact).build();
        when(service.findScriptById(eq(scriptId))).thenReturn(script);

        MovieScriptResponse result = controller.checkScriptSubmission(scriptId);

        assertThat(result, allOf(
                instanceOf(MovieScriptResponse.class),
                hasProperty("text", equalTo(script.getText())),
                hasProperty("status", equalTo(script.getStatus().name())),
                hasProperty("contact", allOf(
                        instanceOf(ClientContactDTO.class),
                        hasProperty("name", equalTo(contact.getName())),
                        hasProperty("phone", equalTo(contact.getPhone())),
                        hasProperty("email", equalTo(contact.getEmail()))
                ))
        ));
    }

}
