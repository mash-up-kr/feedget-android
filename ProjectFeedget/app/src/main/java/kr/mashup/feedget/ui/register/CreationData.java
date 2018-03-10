package kr.mashup.feedget.ui.register;

import java.io.Serializable;

/**
 * Created by eastroots92 on 2018. 1. 13..
 */

public class CreationData implements Serializable {

    private String creationId;
    private String title ;
    private String description;
    private String category;
    private boolean anonymity;
    private float rewardPoint;


    public String getCreationId() {
        return creationId;
    }

    public void setCreationId(String creationId) {
        this.creationId = creationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean getAnonymity() {
        return anonymity;
    }

    public void setAnonymity(boolean anonymity) {
        this.anonymity = anonymity;
    }

    public float getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(float rewardPoint) {
        this.rewardPoint = rewardPoint;
    }


    public boolean isCompleted(){
        if( getTitle() != "" && getDescription() != ""){
            return true;
        }else{
            return false;
        }
    }

    public boolean isCategory(){
        if( getCategory().contains("(필수)")){
            return false;
        }else{
            return true;
        }
    }

    public boolean isSubmit(){
        if( rewardPoint != 0 ){
            return true;
        }else{
            return false;
        }
    }


}
