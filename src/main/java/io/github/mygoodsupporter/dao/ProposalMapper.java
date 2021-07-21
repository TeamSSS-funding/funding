package io.github.mygoodsupporter.dao;

import io.github.mygoodsupporter.domain.Proposal;

import java.util.List;

public interface ProposalMapper {

    List<Proposal> getProposals();
    Proposal getProposalById(Long proposalId);
    Proposal getProposalByMemberId(String memberId);

    void insertProposal(Proposal proposal);
    void updateProposal(Proposal proposal);
    void deleteProposal(Proposal proposal);
}
