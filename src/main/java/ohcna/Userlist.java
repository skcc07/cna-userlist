package ohcna;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Userlist_table")
public class Userlist {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private String userId;
        private String userName;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

}
