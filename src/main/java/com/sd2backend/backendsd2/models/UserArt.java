package com.sd2backend.backendsd2.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_art")
@IdClass(UserArt.UArtCPK.class)
@NamedQueries({
        @NamedQuery(name = "find_user_art_by_user_id", query = "SELECT a FROM UserArt a WHERE a.user_id = :user"),
})
public class UserArt {
    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")

    private User user_id;

    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "art_id")
    private Art art_id;

    public UserArt() {
    }

    public UserArt(@NotNull User user_id, @NotNull Art artId) {
        this.user_id = user_id;
        this.art_id = artId;
    }

    public static class UArtCPK implements Serializable {


        private String user_id;


        private int art_id;

        public UArtCPK() {
        }

        public UArtCPK(String user_id, int art_id) {
            this.user_id = user_id;
            this.art_id = art_id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UArtCPK that = (UArtCPK) o;
            return user_id.equals(that.user_id) &&
                    Objects.equals(art_id, that.art_id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(user_id, art_id);
        }


    }


    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Art getArt_id() {
        return art_id;
    }

    public void setArt_id(Art art_id) {
        this.art_id = art_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserArt userArt = (UserArt) o;
        return Objects.equals(user_id, userArt.user_id) &&
                Objects.equals(art_id, userArt.art_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, art_id);
    }
}
