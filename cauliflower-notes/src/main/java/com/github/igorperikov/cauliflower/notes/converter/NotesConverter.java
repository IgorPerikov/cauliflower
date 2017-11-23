package com.github.igorperikov.cauliflower.notes.converter;

import com.github.igorperikov.cauliflower.common.dto.NoteTO;
import com.github.igorperikov.cauliflower.notes.entity.NoteEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NotesConverter {
    public NoteTO convertFromEntity(NoteEntity entity) {
        return new NoteTO(entity.getUserId(), entity.getNoteId(), entity.getContent());
    }

    public NoteEntity convertFromTO(NoteTO to) {
        return new NoteEntity(to.getUserId(), to.getNoteId(), to.getContent());
    }
}
