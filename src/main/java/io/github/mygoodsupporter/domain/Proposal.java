package io.github.mygoodsupporter.domain;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Proposal {
    private Long id;
    private String memberId;
    private String title;
    private String description;
    private int targetAmount;
    private ProposalStatus status;

    @Builder
    public Proposal(String memberId, String title, String description, int targetAmount) {
        this.memberId = memberId;
        this.title = title;
        this.description = description;
        this.targetAmount = targetAmount;
        this.status = ProposalStatus.PENDING;
    }
}
