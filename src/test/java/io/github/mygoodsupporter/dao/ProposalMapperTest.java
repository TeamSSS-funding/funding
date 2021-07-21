package io.github.mygoodsupporter.dao;

import io.github.mygoodsupporter.domain.Member;
import io.github.mygoodsupporter.domain.Proposal;
import io.github.mygoodsupporter.domain.ProposalStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Transactional
public class ProposalMapperTest {

    @Autowired
    MemberDAO memberDAO;
    @Autowired
    ProposalMapper proposalMapper;

    @Test
    public void getProposalById() {
        //given
        Member member = createMember();
        Proposal proposal = createProposal(member.getId());
        proposalMapper.insertProposal(proposal);

        //when
        Proposal other = proposalMapper.getProposalById(proposal.getId());

        //then
        assertThat(other.getId()).isEqualTo(proposal.getId());
        assertThat(other.getMemberId()).isEqualTo(proposal.getMemberId());
        assertThat(other.getTitle()).isEqualTo(proposal.getTitle());
        assertThat(other.getDescription()).isEqualTo(proposal.getDescription());
        assertThat(other.getTargetAmount()).isEqualTo(proposal.getTargetAmount());
        assertThat(other.getStatus()).isEqualTo(ProposalStatus.PENDING);
    }
    @Test
    public void getProposalByMemberId() {
        //given
        Member member = createMember();
        Proposal proposal = createProposal(member.getId());
        proposalMapper.insertProposal(proposal);

        //when
        Proposal other = proposalMapper.getProposalByMemberId(member.getId());

        //then
        assertThat(other.getId()).isEqualTo(proposal.getId());
        assertThat(other.getMemberId()).isEqualTo(proposal.getMemberId());
    }

    @Test
    public void getProposals() {
        //given
        Member member = createMember();
        Proposal proposal = createProposal(member.getId());
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
        Member member = createMember();
        Proposal proposal = createProposal(member.getId());

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
        Member member = createMember();
        Proposal proposal = createProposal(member.getId());
        proposalMapper.insertProposal(proposal);

        //when
        proposalMapper.deleteProposal(proposal.getId());

        //then
        Proposal other = proposalMapper.getProposalById(proposal.getId());
        assertThat(other).isNull();
    }

    private Member createMember() {
        Member member = new Member();
        member.setId("mocha");
        member.setPassword("mocha");
        member.setName("mocha");
        member.setEmail("mocha@safecornerscoffee.com");
        member.setPhone("010-1111-1111");
        memberDAO.insertMember(member);
        return member;
    }

    private Proposal createProposal(String memberId) {
        return Proposal.builder()
                .memberId(memberId)
                .title("coffee me")
                .description("coffee me")
                .targetAmount(3500)
                .build();
    }
}