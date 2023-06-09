/*
 * Copyright 2013-2021 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.whispersystems.textsecuregcm.websocket;

import io.dropwizard.auth.basic.BasicCredentials;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.eclipse.jetty.websocket.api.UpgradeRequest;
import org.whispersystems.textsecuregcm.auth.AccountAuthenticator;
import org.whispersystems.textsecuregcm.auth.AuthenticatedAccount;
import org.whispersystems.websocket.auth.AuthenticationException;
import org.whispersystems.websocket.auth.WebSocketAuthenticator;


public class WebSocketAccountAuthenticator implements WebSocketAuthenticator<AuthenticatedAccount> {

  private final AccountAuthenticator accountAuthenticator;

  public WebSocketAccountAuthenticator(AccountAuthenticator accountAuthenticator) {
    this.accountAuthenticator = accountAuthenticator;
  }

  @Override
  public AuthenticationResult<AuthenticatedAccount> authenticate(UpgradeRequest request)
      throws AuthenticationException {
    Map<String, List<String>> parameters = request.getParameterMap();
    List<String> usernames = parameters.get("login");
    List<String> passwords = parameters.get("password");

    if (usernames == null || usernames.size() == 0 ||
        passwords == null || passwords.size() == 0) {
      return new AuthenticationResult<>(Optional.empty(), false);
    }

    BasicCredentials credentials = new BasicCredentials(usernames.get(0).replace(" ", "+"),
        passwords.get(0).replace(" ", "+"));

    try {
      return new AuthenticationResult<>(accountAuthenticator.authenticate(credentials), true);
    } catch (final Exception e) {
      // this will be handled and logged upstream
      // the most likely exception is a transient error connecting to account storage
      throw new AuthenticationException(e);
    }
  }

}
