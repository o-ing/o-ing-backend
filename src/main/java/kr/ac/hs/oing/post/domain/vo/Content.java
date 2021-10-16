package kr.ac.hs.oing.post.domain.vo;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
@EqualsAndHashCode
public class Content {
    @Lob
    @Column(name = "post_content", nullable = false)
    private String content;

    protected Content() {

    }

    public Content(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
