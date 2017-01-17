package by.tr.op.bean;

import java.io.Serializable;

public class AnswerResult implements Serializable{
    private static final long serialVersionUID = 1L;
    private int count;
    private String answerText;

    public AnswerResult() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.count;
        hash = 61 * hash + (this.answerText != null ? this.answerText.hashCode() : 0);
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
        
        final AnswerResult other = (AnswerResult) obj;
        
        if (this.count != other.count) {
            return false;
        }
        
        if ((this.answerText == null) ? (other.answerText != null) : !this.answerText.equals(other.answerText)) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "AnswerResult{" + "count=" + count + ", answerText=" + answerText + '}';
    }
    
}
