package com.backbase.stream.compositions.legalentity.core.model;

import com.backbase.stream.legalentity.model.LegalEntity;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LegalEntityResponse {

  private Boolean productChainEnabledFromRequest;
  private final LegalEntity legalEntity;
  private final List<String> membershipAccounts;
}