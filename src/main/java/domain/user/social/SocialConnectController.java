/******************************************************************************
 * Copyright (c) 2012 didauvn.
 ******************************************************************************
 *
 ******************************************************************************
 *              M A I N T E N A N C E     L O G
 ******************************************************************************
 * ISSUE # DATE       PROGRAMMER DESCRIPTION
 * ------- ---------- ---------- ----------------------------------------------
 * 1	   08/08/2012 minhle	 Example
 ******************************************************************************
 */
package domain.user.social;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Overrides ConnectController to demonstrate how to customize the default flow.
 * In this case, there will be no per-provider status page--only a master status
 * page.
 * 
 */
@Controller
public class SocialConnectController extends ConnectController {

	@Inject
	public SocialConnectController(
			ConnectionFactoryLocator connectionFactoryLocator,
			ConnectionRepository connectionRepository) {
		super(connectionFactoryLocator, connectionRepository);
	}

	/**
	 * Override the per-provider status page handler to simply redirect to the
	 * master status page.
	 */
	@Override
	public String connectionStatus(String providerId, NativeWebRequest request,
			Model model) {

		return "redirect:" + SocialConfig.SOCIAL_CONNECT_URL;
	}

	@Override
	protected String connectView() {
		return SocialConfig.SOCIAL_CONNECT_VIEW;
	}

}
