package io.github.mygoodsupporter.mapper;

import io.github.mygoodsupporter.domain.Proposal;
import io.github.mygoodsupporter.domain.ProposalStatus;
import io.github.mygoodsupporter.domain.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ProposalMapperTest {

    @Autowired
    UserMapper userMapper;
    @Autowired
    ProposalMapper proposalMapper;

    @Test
    public void getProposalById() {
        //given
        User user = createMember();
        Proposal proposal = createProposal(user.getId());
        proposalMapper.insertProposal(proposal);

        //when
        Proposal other = proposalMapper.getProposalById(proposal.getId());

        //then
        assertThat(other.getId()).isEqualTo(proposal.getId());
        assertThat(other.getUserId()).isEqualTo(proposal.getUserId());
        assertThat(other.getTitle()).isEqualTo(proposal.getTitle());
        assertThat(other.getDescription()).isEqualTo(proposal.getDescription());
        assertThat(other.getTargetAmount()).isEqualTo(proposal.getTargetAmount());
        assertThat(other.getStatus()).isEqualTo(ProposalStatus.PENDING);
    }
    @Test
    public void getProposalByMemberId() {
        //given
        User user = createMember();
        Proposal proposal = createProposal(user.getId());
        proposalMapper.insertProposal(proposal);

        //when
        Proposal other = proposalMapper.getProposalByMemberId(user.getUsername());

        //then
        assertThat(other.getId()).isEqualTo(proposal.getId());
        assertThat(other.getUserId()).isEqualTo(proposal.getUserId());
    }

    @Test
    public void getProposals() {
        //given
        User user = createMember();
        Proposal proposal = createProposal(user.getId());
        proposalMapper.insertProposal(proposal);

        //when
        List<Proposal> others = proposalMapper.getProposals();

        //then
        assertThat(others).hasSize(1);
        Proposal other = others.get(0);
        assertThat(other.getId()).isEqualTo(proposal.getId());
    }

    @Test
    public void insertProposal() {
        //given
        User user = createMember();
        Proposal proposal = createProposal(user.getId());

        //when
        proposalMapper.insertProposal(proposal);

        //then
        assertThat(proposal.getId()).isNotNull();
    }

    @Test
    public void updateProposal() {

    }

    @Test
    public void deleteProposal() {
        //given
        User user = createMember();
        Proposal proposal = createProposal(user.getId());
        proposalMapper.insertProposal(proposal);

        //when
        proposalMapper.deleteProposal(proposal.getId());

        //then
        Proposal other = proposalMapper.getProposalById(proposal.getId());
        assertThat(other).isNull();
    }

    private User createMember() {
        User user = new User();
        user.setUsername("mocha");
        user.setPassword("mocha");
        user.setName("mocha");
        user.setEmail("mocha@safecornerscoffee.com");
        user.setPhone("010-1111-1111");
        userMapper.insertUser(user);
        return user;
    }

    private Proposal createProposal(Long userId) {
        return Proposal.builder()
                .userId(userId)
                .title("coffee me")
                .description("coffee me")
                .targetAmount(3500)
                .build();
    }
}