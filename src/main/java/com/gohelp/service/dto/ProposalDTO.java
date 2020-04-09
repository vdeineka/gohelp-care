package com.gohelp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.gohelp.domain.Proposal} entity.
 */
public class ProposalDTO implements Serializable {
    
    private Long id;

    private String text;


    private Long userId;

    private String userLogin;

    private Long requestId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProposalDTO proposalDTO = (ProposalDTO) o;
        if (proposalDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proposalDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProposalDTO{" +
            "id=" + getId() +
            ", text='" + getText() + "'" +
            ", userId=" + getUserId() +
            ", userLogin='" + getUserLogin() + "'" +
            ", requestId=" + getRequestId() +
            "}";
    }
}
