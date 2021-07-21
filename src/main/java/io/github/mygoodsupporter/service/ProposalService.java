package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.dao.ProposalMapper;
import io.github.mygoodsupporter.domain.Project;
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
    private final ProjectService projectService;

    @Transactional
    public Long submitProposal(String memberId, CreateProposalForm form) {
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
    public void approveProposal(Long proposalId) {
        Proposal proposal = proposalMapper.getProposalById(proposalId);

        proposal.approved();
        proposalMapper.updateProposal(proposal);

        Project project = Project.builder()
                .name(proposal.getTitle())
                .content(proposal.getDescription())
                .memberId(proposal.getMemberId())
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
