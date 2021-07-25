package io.github.mygoodsupporter.dao;

import io.github.mygoodsupporter.domain.Proposal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProposalMapper {

    List<Proposal> getProposals();
    Proposal getProposalById(Long proposalId);
    Proposal getProposalByMemberId(String memberId);

    void insertProposal(Proposal proposal);
    void updateProposal(Proposal proposal);
    void deleteProposal(Long proposalId);
}
