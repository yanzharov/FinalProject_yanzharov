package by.tr.op.bean;

import java.io.Serializable;

public class Option implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String text;
    private int questionId;

    public Option() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        hash = 37 * hash + (this.text != null ? this.text.hashCode() : 0);
        hash = 37 * hash + this.questionId;
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
        
        final Option other = (Option) obj;
        
        if (this.id != other.id) {
            return false;
        }
        
        if (this.questionId != other.questionId) {
            return false;
        }
        
        if ((this.text == null) ? (other.text != null) : !this.text.equals(other.text)) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "Option{" + "id=" + id + ", text=" + text + ", questionId=" + questionId + '}';
    }
    
}
