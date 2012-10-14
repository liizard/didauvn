package core.exception.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import core.exception.ControllerException;
import core.exception.DdException;

public class ExceptionHandler implements HandlerExceptionResolver {

	private static final Logger LOGGER = Logger.getLogger("mainLogger");

	private static final Logger DATA_LOGGER = Logger.getLogger("dataLogger");

	private static final Logger VALIDATION_LOGGER = Logger
			.getLogger("validationLogger");

	private static final Logger SECURITY_LOGGER = Logger
			.getLogger("securityLogger");

	private static final Logger RUNTIME_LOGGER = Logger
			.getLogger("runtimeLogger");

	@Autowired
	MappingJacksonJsonView view;

	@Override
	@ResponseBody
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		try {
			ex.printStackTrace();
			ModelAndView mav = new ModelAndView();
			mav.setView(view);
			if (ex instanceof DdException) {
				DdException e = (DdException) ex;
				String message = e.getCode() + " - " + e.getMessage();

				if (e.getType().equals(DdException.DATA_EXCEPTION)) {
					DATA_LOGGER.error(message, e.getException());

				} else if (e.getType().equals(DdException.VALIDATION_EXCEPTION)) {
					VALIDATION_LOGGER.error(message, e.getException());
				}

				LOGGER.error(message);
				mav.addObject("error", ((DdException) ex).getCode());
			} else if (ex instanceof AccessDeniedException) {
				LOGGER.error("err101 - Access Denied Error");
				SECURITY_LOGGER.error("err101 - Access Denied Error", ex);
				mav.addObject("error", "err101");
			} else if (ex instanceof ControllerException) {
				LOGGER.error("err102 - Bad Link Error: " + ex.getMessage());
				SECURITY_LOGGER.error("err102 - Bad Link Error", ex);
				mav.addObject("error", "err102");
			} else {
				LOGGER.error("Runtime Error");
				RUNTIME_LOGGER.error("Runtime Error", ex);
				mav.addObject("error", "err001");
			}

			return mav;
		} catch (Throwable e) {
			LOGGER.error("SERIOUS Error", e);
			RUNTIME_LOGGER.error("SERIOUS Error", ex);
			return null;
		}
	}

}
