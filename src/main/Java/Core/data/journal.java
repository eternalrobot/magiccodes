package Core.data;

import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "journal")
public class journal {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "time")
    Date time;
    @Basic(fetch = FetchType.LAZY)//long text在java代码中的实现
    @Type(type="text")
    @Column(name="content", nullable=true)
    String content;

    public journal(Date time, String content) {
        this.time = time;
        this.content = content;
    }

    @Override
    public String toString() {
        return "journal{" +
                "id=" + id +
                ", time=" + time +
                ", content='" + content + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public journal() {
    }
}
