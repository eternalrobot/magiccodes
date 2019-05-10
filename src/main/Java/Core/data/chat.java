package Core.data;

import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "chat")
public class chat {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "senduserid")
    int senduserid;
    @Column(name = "receiveuserid")
    int receiveuserid;
    @Column(name = "time")
    Date time;
    @Column(name = "isread")
    boolean isread;
    @Basic(fetch = FetchType.LAZY)//long text在java代码中的实现
    @Type(type="text")
    @Column(name="content", nullable=true)
    String content;

    public chat(int senduserid, int receiveuserid, Date time, boolean isread, String content) {
        this.senduserid = senduserid;
        this.receiveuserid = receiveuserid;
        this.time = time;
        this.isread = isread;
        this.content = content;
    }

    @Override
    public String toString() {
        return "chat{" +
                "id=" + id +
                ", senduserid=" + senduserid +
                ", receiveuserid=" + receiveuserid +
                ", time=" + time +
                ", isread=" + isread +
                ", content='" + content + '\'' +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isIsread() {
        return isread;
    }

    public void setIsread(boolean isread) {
        this.isread = isread;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenduserid() {
        return senduserid;
    }

    public void setSenduserid(int senduserid) {
        this.senduserid = senduserid;
    }

    public int getReceiveuserid() {
        return receiveuserid;
    }

    public void setReceiveuserid(int receiveuserid) {
        this.receiveuserid = receiveuserid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public chat() {
    }
}
