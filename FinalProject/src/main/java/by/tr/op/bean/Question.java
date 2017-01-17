package by.tr.op.bean;

import java.io.Serializable;

public class Question implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String questionText;
    private int opinionPollId;
    private int authorId;

    public Question() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getOpinionPollId() {
        return opinionPollId;
    }

    public void setOpinionPollId(int opinionPollId) {
        this.opinionPollId = opinionPollId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.id;
        hash = 13 * hash + (this.questionText != null ? this.questionText.hashCode() : 0);
        hash = 13 * hash + this.opinionPollId;
        hash = 13 * hash + this.authorId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Question other = (Question) obj;
        
        if (this.id != other.id) {
            return false;
        }
        
        if (this.opinionPollId != other.opinionPollId) {
            return false;
        }
        
        if (this.authorId != other.authorId) {
            return false;
        }
        
        if ((this.questionText == null) ? (other.questionText != null) : !this.questionText.equals(other.questionText)) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", questionText=" + questionText + ", opinionPollId=" + opinionPollId + ", authorId=" + authorId + '}';
    }
    
}
