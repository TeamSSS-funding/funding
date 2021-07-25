package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.dao.MemberDAO;
import io.github.mygoodsupporter.dao.ProposalMapper;
import io.github.mygoodsupporter.domain.Member;
import io.github.mygoodsupporter.domain.Proposal;
import io.github.mygoodsupporter.domain.ProposalStatus;
import io.github.mygoodsupporter.dto.CreateProposalForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
public class ProposalServiceTest {

    @Autowired
    ProposalService proposalService;

    @Autowired
    ProposalMapper proposalMapper;
    @Autowired
    MemberDAO memberDAO;

    @Test
    public void summitProposal() {
        //given
        Member member = createMember("mocha");
        CreateProposalForm form = new CreateProposalForm();
        form.setTitle("coffee me");
        form.setDescription("coffee me");
        form.setTargetAmount(3500);

        //when
        Long proposalId = proposalService.submitProposal(member.getId(), form);

        //then
        Proposal proposal = proposalMapper.getProposalById(proposalId);
        assertThat(proposal.getId()).isEqualTo(proposalId);
        assertThat(proposal.getMemberId()).isEqualTo(member.getId());
        assertThat(proposal.getTitle()).isEqualTo(form.getTitle());
        assertThat(proposal.getDescription()).isEqualTo(form.getDescription());
        assertThat(proposal.getTargetAmount()).isEqualTo(form.getTargetAmount());
        assertThat(proposal.getStatus()).isEqualTo(ProposalStatus.PENDING);
    }

    public void acceptProposal() {
        //given
        Member member = createMember("mocha");
        Proposal proposal = createProposal(member.getId());
        proposalMapper.insertProposal(proposal);

        //when
        proposalService.approveProposal(proposal.getId());

        //then
        Proposal approved = proposalMapper.getProposalById(proposal.getId());
        assertThat(approved.getStatus()).isEqualTo(ProposalStatus.APPROVED);
    }

    public void rejectProposal() {
        //given
        Member member = createMember("mocha");
        Proposal proposal = createProposal(member.getId());
        proposalMapper.insertProposal(proposal);

        //when
        proposalService.approveProposal(proposal.getId());

        //then
        Proposal rejected = proposalMapper.getProposalById(proposal.getId());
        assertThat(rejected.getStatus()).isEqualTo(ProposalStatus.REJECTED);
    }

    public Member createMember(String name) {
        Member member = new Member();

        member.setId(name);
        member.setPassword(name);
        member.setName(name);
        member.setEmail(name + "@mygoodsupporter.github.io");
        member.setPhone("010-1111-1111");

        memberDAO.insertMember(member);
        return member;
    }

    public Proposal createProposal(String memberId) {
        Proposal proposal = Proposal.builder()
                .memberId(memberId)
                .title("coffee me")
                .description("coffee me")
                .targetAmount(3300)
                .build();
        proposalMapper.insertProposal(proposal);
        return proposal;
    }
}