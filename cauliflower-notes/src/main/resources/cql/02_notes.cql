CREATE TABLE notes.user_notes (
    user_id uuid,
    note_id timeuuid,
    content text,
    PRIMARY KEY ((user_id), note_id));