package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.Project;
import io.github.mygoodsupporter.domain.Proposal;
import io.github.mygoodsupporter.dto.CreateProposalForm;
import io.github.mygoodsupporter.mapper.ProposalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProposalService {

    private final ProposalMapper proposalMapper;
    private final ProjectService projectService;

    @Transactional
    public Long submitProposal(Long userId, CreateProposalForm form) {
        Proposal proposal = Proposal.builder()
                .userId(userId)
                .title(form.getTitle())
                .description(form.getDescription())
                .targetAmount(form.getTargetAmount())
                .build();
        proposal.setUserId(userId);
        proposalMapper.insertProposal(proposal);

        return proposal.getId();
    }

    @Transactional
    public void approveProposal(Long proposalId) {
        Proposal proposal = proposalMapper.getProposalById(proposalId);

        proposal.approved();
        proposalMapper.updateProposal(proposal);

        Project project = Project.builder()
                .name(proposal.getTitle())
                .content(proposal.getDescription())
                .userId(proposal.getUserId())
                .targetAmount(proposal.getTargetAmount())
                .build();

        projectService.openProject(project);
    }

    @Transactional
    public void rejectProposal(Long proposalId) {
        Proposal proposal = proposalMapper.getProposalById(proposalId);
        proposal.rejected();
        proposalMapper.updateProposal(proposal);
    }

    public Proposal getProposalById(Long proposalId) {
        return proposalMapper.getProposalById(proposalId);
    }
    public Proposal getProposalByMemberId(String memberId) {
        return proposalMapper.getProposalByMemberId(memberId);
    }
    public List<Proposal> getProposals() {
        return proposalMapper.getProposals();
    }
}
