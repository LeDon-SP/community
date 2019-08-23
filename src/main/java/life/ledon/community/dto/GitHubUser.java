package life.ledon.community.dto;

import lombok.Data;

@Data
public class GitHubUser {
    private String name;
    private String avatarUrl;
    private Long id;
    private String email;
    private String bio;
}
