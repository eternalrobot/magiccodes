package Core.data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user_user")
public class user_user {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "username1")
    String username1;
    @Column(name = "username2")
    String username2;

    public user_user(String username1, String username2) {
        this.username1 = username1;
        this.username2 = username2;
    }

    @Override
    public String toString() {
        return "user_user{" +
                "id=" + id +
                ", username1='" + username1 + '\'' +
                ", username2='" + username2 + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public String getUsername2() {
        return username2;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }

    public user_user() {
    }
}
