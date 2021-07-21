package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.dao.ProposalMapper;
import io.github.mygoodsupporter.domain.Proposal;
import io.github.mygoodsupporter.dto.CreateProposalForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProposalService {

    private final ProposalMapper proposalMapper;

    @Transactional
    Long submitProposal(String memberId, CreateProposalForm form) {
        Proposal proposal = Proposal.builder()
                .memberId(memberId)
                .title(form.getTitle())
                .description(form.getDescription())
                .targetAmount(form.getTargetAmount())
                .build();
        proposal.setMemberId(memberId);
        proposalMapper.insertProposal(proposal);

        return proposal.getId();
    }

    @Transactional
    void approveProposal(Long proposalId) {
        Proposal proposal = proposalMapper.getProposalById(proposalId);

        proposal.approved();

        proposalMapper.updateProposal(proposal);
    }

    @Transactional
    void rejectProposal(Long proposalId) {
        Proposal proposal = proposalMapper.getProposalById(proposalId);
        proposal.rejected();
        proposalMapper.updateProposal(proposal);
    }

    Proposal getProposalById(Long proposalId) {
        return proposalMapper.getProposalById(proposalId);
    }
    Proposal getProposalByMemberId(String memberId) {
        return proposalMapper.getProposalByMemberId(memberId);
    }
    List<Proposal> getProposals() {
        return proposalMapper.getProposals();
    }
}
