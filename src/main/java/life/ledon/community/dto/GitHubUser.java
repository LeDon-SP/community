package life.ledon.community.dto;

import lombok.Data;

@Data
public class GitHubUser {
    private String name;
    private String avatar_url;
    private Long id;
    private String email;
    private String bio;
}
