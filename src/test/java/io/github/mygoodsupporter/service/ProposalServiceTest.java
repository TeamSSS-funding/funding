package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.Proposal;
import io.github.mygoodsupporter.domain.ProposalStatus;
import io.github.mygoodsupporter.domain.user.User;
import io.github.mygoodsupporter.dto.CreateProposalForm;
import io.github.mygoodsupporter.mapper.ProposalMapper;
import io.github.mygoodsupporter.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ProposalServiceTest {

    @Autowired
    ProposalService proposalService;

    @Autowired
    ProposalMapper proposalMapper;
    @Autowired
    UserMapper userMapper;

    @Test
    public void summitProposal() {
        //given
        User user = createMember("mocha");
        CreateProposalForm form = new CreateProposalForm();
        form.setTitle("coffee me");
        form.setDescription("coffee me");
        form.setTargetAmount(3500);

        //when
        Long proposalId = proposalService.submitProposal(user.getId(), form);

        //then
        Proposal proposal = proposalMapper.getProposalById(proposalId);
        assertThat(proposal.getId()).isEqualTo(proposalId);
        assertThat(proposal.getUserId()).isEqualTo(user.getId());
        assertThat(proposal.getTitle()).isEqualTo(form.getTitle());
        assertThat(proposal.getDescription()).isEqualTo(form.getDescription());
        assertThat(proposal.getTargetAmount()).isEqualTo(form.getTargetAmount());
        assertThat(proposal.getStatus()).isEqualTo(ProposalStatus.PENDING);
    }

    public void acceptProposal() {
        //given
        User user = createMember("mocha");
        Proposal proposal = createProposal(user.getId());
        proposalMapper.insertProposal(proposal);

        //when
        proposalService.approveProposal(proposal.getId());

        //then
        Proposal approved = proposalMapper.getProposalById(proposal.getId());
        assertThat(approved.getStatus()).isEqualTo(ProposalStatus.APPROVED);
    }

    public void rejectProposal() {
        //given
        User user = createMember("mocha");
        Proposal proposal = createProposal(user.getId());
        proposalMapper.insertProposal(proposal);

        //when
        proposalService.approveProposal(proposal.getId());

        //then
        Proposal rejected = proposalMapper.getProposalById(proposal.getId());
        assertThat(rejected.getStatus()).isEqualTo(ProposalStatus.REJECTED);
    }

    public User createMember(String name) {
        User user = new User();

        user.setUsername(name);
        user.setPassword(name);
        user.setName(name);
        user.setEmail(name + "@mygoodsupporter.github.io");
        user.setPhone("010-1111-1111");

        userMapper.insertUser(user);
        return user;
    }

    public Proposal createProposal(Long userId) {
        Proposal proposal = Proposal.builder()
                .userId(userId)
                .title("coffee me")
                .description("coffee me")
                .targetAmount(3300)
                .build();
        proposalMapper.insertProposal(proposal);
        return proposal;
    }
}