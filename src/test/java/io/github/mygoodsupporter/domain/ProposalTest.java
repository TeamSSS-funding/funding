package io.github.mygoodsupporter.domain;



import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProposalTest {

    @Test
    public void submitProposal() {
        //when
        Proposal proposal = Proposal.builder()
                .userId(1L)
                .title("coffee me!")
                .description("coffee me!")
                .targetAmount(3500)
                .build();

        //then
        assertThat(proposal.getUserId()).isEqualTo(1L);
        assertThat(proposal.getTitle()).isEqualTo("coffee me!");
        assertThat(proposal.getDescription()).isEqualTo("coffee me!");
        assertThat(proposal.getTargetAmount()).isEqualTo(3500);
        assertThat(proposal.getStatus()).isEqualTo(ProposalStatus.PENDING);
    }

    private Proposal createProposal() {
        Proposal proposal = Proposal.builder()
                .userId(1L)
                .title("coffee me!")
                .description("coffee me!")
                .targetAmount(3500)
                .build();
        return proposal;
    }

    @Test
    public void approveProposal() {
        //given
        Proposal proposal = createProposal();

        //when
        proposal.approved();

        //then
        assertThat(proposal.getStatus()).isEqualTo(ProposalStatus.APPROVED);
    }

    @Test
    public void rejectProposal() {
        //given
        Proposal proposal = createProposal();

        //when
        proposal.rejected();

        //then
        assertThat(proposal.getStatus()).isEqualTo(ProposalStatus.REJECTED);
    }
}