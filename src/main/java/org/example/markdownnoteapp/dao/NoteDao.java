package org.example.markdownnoteapp.dao;

import jakarta.persistence.*;

@Entity
@Table(name = "note")
public class NoteDao {

    @Id
    @SequenceGenerator(name = "noteSeqGen", sequenceName = "note_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "noteSeqGen")
    private Long id;

    @Column(unique = true)
    private String title;

    @Lob
    private byte[] content;

    public NoteDao(Long id, String title, byte[] content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public NoteDao() {
    }

    public static NoteDaoBuilder builder() {
        return new NoteDaoBuilder();
    }

    @PrePersist
    public void setDefaults() {
        if (title == null) {
            title = "Note Title - " + id;
        }
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public byte[] getContent() {
        return this.content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public static class NoteDaoBuilder {
        private Long id;
        private String title;
        private byte[] content;

        NoteDaoBuilder() {
        }

        public NoteDaoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public NoteDaoBuilder title(String title) {
            this.title = title;
            return this;
        }

        public NoteDaoBuilder content(byte[] content) {
            this.content = content;
            return this;
        }

        public NoteDao build() {
            return new NoteDao(this.id, this.title, this.content);
        }

        public String toString() {
            return "NoteDao.NoteDaoBuilder(id=" + this.id + ", title=" + this.title + ", content=" + java.util.Arrays.toString(this.content) + ")";
        }
    }
}
