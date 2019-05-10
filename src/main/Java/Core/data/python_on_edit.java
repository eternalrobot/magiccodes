package Core.data;

import org.hibernate.annotations.Type;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "python_on_edit")
public class python_on_edit {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "userid")
    int userid;
    @Basic(fetch = FetchType.LAZY)//long text在java代码中的实现
    @Type(type="text")
    @Column(name="content", nullable=true)
    String content;

    public python_on_edit(int userid, String content) {
        this.userid = userid;
        this.content = content;
    }

    @Override
    public String toString() {
        return "python_on_edit{" +
                "id=" + id +
                ", userid=" + userid +
                ", content='" + content + '\'' +
                '}';
    }

    public python_on_edit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
