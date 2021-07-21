package io.github.mygoodsupporter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateProposalForm {
    private String title;
    private String description;
    private int targetAmount;
}
