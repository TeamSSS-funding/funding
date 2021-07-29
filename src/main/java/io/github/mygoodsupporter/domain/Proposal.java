package io.github.mygoodsupporter.domain;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Proposal {
    private Long id;
    private Long userId;
    private String title;
    private String description;
    private int targetAmount;
    private ProposalStatus status;

    @Builder
    public Proposal(Long userId, String title, String description, int targetAmount) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.targetAmount = targetAmount;
        this.status = ProposalStatus.PENDING;
    }

    public void approved() {
        setStatus(ProposalStatus.APPROVED);
    }

    public void rejected() {
        setStatus(ProposalStatus.REJECTED);
    }
}
