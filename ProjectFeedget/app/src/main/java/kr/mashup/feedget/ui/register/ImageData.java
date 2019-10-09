package kr.mashup.feedget.ui.register;

import android.net.Uri;

import java.io.Serializable;

import java.util.ArrayList;

/**
 * Created by eastroots92 on 2018. 1. 13..
 */

public class ImageData implements Serializable {
    private String creationId;
    private ArrayList<Uri> files = new ArrayList<>();

    public String getCreationId() {
        return creationId;
    }

    public void setCreationId(String creationId) {
        this.creationId = creationId;
    }

    public ArrayList<Uri> getFiles() { return files;}

    public void setFiles(Uri file) { this.files.add(file); }

    public boolean isImage(){
        if(getFiles().isEmpty()){
            return false;
        }else {
            return true;
        }
    }
}
