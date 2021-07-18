package io.github.javaarchive;

public class VisualMessage implements Comparable<VisualMessage> {
    public String messageText;
    public String id = "";
    public boolean hidden = false;
    public int priority = 0;
    public VisualMessage(String messageText){
        this.messageText = messageText;
    }
    public VisualMessage(String messageText,String id){
        this.messageText = messageText;
        this.id = id;
    }
    public VisualMessage(String messageText,String id,int priority){
        this.messageText = messageText;
        this.id = id;
        this.priority = priority;
    }
    public void setText(String newText){
        this.messageText = newText;
    }
    public void toggleHidden(){
        this.hidden = !this.hidden;
    }
    public void setHidden(boolean newState){
        this.hidden = newState;
    }
    @Override
    public int compareTo(VisualMessage other) {
        // TODO Auto-generated method stub
        if(other.priority == this.priority){
            return this.id.compareTo(other.id);
        }
        return Integer.compare(other.priority, this.priority);
    }

    // Constants
    public static final int HIGHEST = 1000;
    public static final int HIGH = 700;
    public static final int MEDIUM = 500;
    public static final int LOW = 200;
    public static final int LOWEST = 100;
    
    @Override
    public String toString() {
        return "VisualMessage [hidden=" + hidden + ", id=" + id + ", messageText=" + messageText + ", priority="
                + priority + "]";
    }

    
}
