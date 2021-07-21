package io.github.mygoodsupporter.controller;

import io.github.mygoodsupporter.domain.Proposal;
import io.github.mygoodsupporter.dto.CreateProposalForm;
import io.github.mygoodsupporter.security.MemberDetails;
import io.github.mygoodsupporter.service.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProposalController {

    private final ProposalService proposalService;

    @GetMapping("/proposals/new")
    public String createProposalForm(Model model) {
        model.addAttribute("createProposalForm", new CreateProposalForm());

        return "proposals/new-proposal";
    }

    @PostMapping("/proposals/new")
    public String submitProposal(@AuthenticationPrincipal MemberDetails memberDetails, CreateProposalForm form) {
        String memberId = memberDetails.getId();

        proposalService.submitProposal(memberId, form);

        return "redirect:/proposals";
    }

    @GetMapping("/proposals")
    public String getProposals(Model model) {
        List<Proposal> proposals = proposalService.getProposals();
        model.addAttribute("proposals", proposals);
        return "proposals/proposals";
    }

    @GetMapping("/proposals/{proposalId}")
    public String getProposal(@PathVariable("proposalId") Long proposalId, Model model) {
        Proposal proposal = proposalService.getProposalById(proposalId);

        model.addAttribute("proposal", proposal);

        return "proposals/proposal";
    }

    @PostMapping("/proposals/{proposalId}/approve")
    public String approveProposal(@PathVariable("proposalId") Long proposalId) {

        proposalService.approveProposal(proposalId);
        return "redirect:/proposals";
    }

    @PostMapping("/proposals/{proposalId}/reject")
    public String rejectProposal(@PathVariable("proposalId") Long proposalId) {
        proposalService.rejectProposal(proposalId);
        return "redirect:/proposals";
    }

}
