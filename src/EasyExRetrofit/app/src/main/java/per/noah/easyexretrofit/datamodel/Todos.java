package per.noah.easyexretrofit.datamodel;

import com.google.gson.annotations.SerializedName;

public class Todos {
    @SerializedName("userId")// original key
    private int userid;// rename key
    private int id;
    private String title;
    private boolean completed;
    public void setUserid(int userid) {
        this.userid = userid;
    }
    public int getUserid() {
        return userid;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public boolean getCompleted() {
        return completed;
    }
}
