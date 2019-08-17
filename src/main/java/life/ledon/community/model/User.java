package life.ledon.community.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String account_id;
    private String token;
    private String avatar_url;
    private String email;
    private Long gmt_create;
    private Long gmt_modified;
}
