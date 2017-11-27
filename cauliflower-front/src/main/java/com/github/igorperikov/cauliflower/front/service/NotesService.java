package com.github.igorperikov.cauliflower.front.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.igorperikov.cauliflower.common.dto.notes.ContentWrapperTO;
import com.github.igorperikov.cauliflower.common.dto.notes.NoteTO;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class NotesService {
    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Value("${notes.application.address}")
    private String notesUrl;

    @Value("${notes.application.port}")
    private int notesPort;

    @Autowired
    public NotesService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public List<NoteTO> getNotes(UUID userId) throws IOException {
        Request request = new Request.Builder()
                .url("http://" + notesUrl + ":" + notesPort + "/notes")
                .header("X-User-Id", userId.toString())
                .get()
                .build();
        Response response = httpClient.newCall(request).execute();
        if (response.code() == 200) {
            return objectMapper.readValue(response.body().string(), new TypeReference<List<NoteTO>>(){});
        } else {
            throw new IOException(response.message());
        }
    }

    public void deleteNote(UUID userId, UUID noteId) throws IOException {
        Request request = new Request.Builder()
                .url("http://" + notesUrl + ":" + notesPort + "/notes/" + noteId.toString())
                .header("X-User-Id", userId.toString())
                .delete()
                .build();
        Response response = httpClient.newCall(request).execute();
        if (response.code() != 200) {
            throw new IOException(response.message());
        }
    }

    public void createNote(UUID userId, String content) throws IOException {
        String body = objectMapper.writeValueAsString(new ContentWrapperTO(content));
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), body);
        Request request = new Request.Builder()
                .url("http://" + notesUrl + ":" + notesPort + "/notes")
                .header("X-User-Id", userId.toString())
                .post(requestBody)
                .build();
        Response response = httpClient.newCall(request).execute();
        if (response.code() != 200) {
            throw new IOException(response.message());
        }
    }
}
