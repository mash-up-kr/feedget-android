package kr.mashup.feedget.model.dummy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Creation implements Serializable {

    public int creationId;
    public String description;
    public int feedbackCount;
    public String title;
    public User user;
    public int point;
    public Date createdDate;
    public Date dueDate;
    public ArrayList<String> imageUrlList;
    public ArrayList<Feedback> feedbackList;

    public Creation() {

    }

}
