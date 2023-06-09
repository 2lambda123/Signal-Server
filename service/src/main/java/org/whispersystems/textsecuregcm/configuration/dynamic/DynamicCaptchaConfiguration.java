/*
 * Copyright 2022 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.whispersystems.textsecuregcm.configuration.dynamic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.annotations.VisibleForTesting;
import org.whispersystems.textsecuregcm.captcha.Action;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class DynamicCaptchaConfiguration {

  @JsonProperty
  @DecimalMin("0")
  @DecimalMax("1")
  @NotNull
  private BigDecimal scoreFloor;

  @JsonProperty
  private boolean allowHCaptcha = false;

  @JsonProperty
  private boolean allowRecaptcha = true;

  @JsonProperty
  @NotNull
  private Map<Action, Set<String>> hCaptchaSiteKeys = Collections.emptyMap();

  @JsonProperty
  @NotNull
  private Map<Action, Set<String>> recaptchaSiteKeys = Collections.emptyMap();

  @JsonProperty
  @NotNull
  private Map<Action, BigDecimal> scoreFloorByAction = Collections.emptyMap();

  @JsonProperty
  @NotNull
  private Set<String> signupCountryCodes = Collections.emptySet();

  @JsonProperty
  @NotNull
  private Set<String> signupRegions = Collections.emptySet();

  public BigDecimal getScoreFloor() {
    return scoreFloor;
  }

  public Set<String> getSignupCountryCodes() {
    return signupCountryCodes;
  }

  @VisibleForTesting
  public void setSignupCountryCodes(Set<String> numbers) {
    this.signupCountryCodes = numbers;
  }

  @VisibleForTesting
  public void setSignupRegions(final Set<String> signupRegions) {
    this.signupRegions = signupRegions;
  }

  public Set<String> getSignupRegions() {
    return signupRegions;
  }

  public boolean isAllowHCaptcha() {
    return allowHCaptcha;
  }

  public boolean isAllowRecaptcha() {
    return allowRecaptcha;
  }

  public Map<Action, BigDecimal> getScoreFloorByAction() {
    return scoreFloorByAction;
  }

  @VisibleForTesting
  public void setAllowHCaptcha(final boolean allowHCaptcha) {
    this.allowHCaptcha = allowHCaptcha;
  }

  @VisibleForTesting
  public void setScoreFloor(final BigDecimal scoreFloor) {
    this.scoreFloor = scoreFloor;
  }

  public Map<Action, Set<String>> getHCaptchaSiteKeys() {
    return hCaptchaSiteKeys;
  }

  @VisibleForTesting
  public void setHCaptchaSiteKeys(final Map<Action, Set<String>> hCaptchaSiteKeys) {
    this.hCaptchaSiteKeys = hCaptchaSiteKeys;
  }

  public Map<Action, Set<String>> getRecaptchaSiteKeys() {
    return recaptchaSiteKeys;
  }

  @VisibleForTesting
  public void setRecaptchaSiteKeys(final Map<Action, Set<String>> recaptchaSiteKeys) {
    this.recaptchaSiteKeys = recaptchaSiteKeys;
  }


}
