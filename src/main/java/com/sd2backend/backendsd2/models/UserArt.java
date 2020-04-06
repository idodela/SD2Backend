package com.sd2backend.backendsd2.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class UserArt {
    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "userId")
    private User user_id;

    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "requestId")
    private Art art_id;

    public UserArt() {
    }

    public UserArt(@NotNull User user_id, @NotNull Art art_id) {
        this.user_id = user_id;
        this.art_id = art_id;
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
