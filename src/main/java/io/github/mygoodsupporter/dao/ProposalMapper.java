package io.github.mygoodsupporter.dao;

import io.github.mygoodsupporter.domain.Proposal;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProposalMapper {

    List<Proposal> getProposals();
    Proposal getProposalById(Long proposalId);
    Proposal getProposalByMemberId(String memberId);

    void insertProposal(Proposal proposal);
    void updateProposal(Proposal proposal);
    void deleteProposal(Long proposalId);
}
