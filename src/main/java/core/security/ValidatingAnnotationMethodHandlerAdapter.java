package core.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.TypeMismatchException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import core.exception.ControllerException;

public class ValidatingAnnotationMethodHandlerAdapter extends
		AnnotationMethodHandlerAdapter {

	@Override
	protected ServletRequestDataBinder createBinder(HttpServletRequest request,
			Object target, String objectName) throws Exception {
		final String url = request.getRequestURL().toString();
		return new ServletRequestDataBinder(target, objectName) {

			@Override
			public <T> T convertIfNecessary(Object value, Class<T> requiredType)
					throws TypeMismatchException {
				try {
					return super.convertIfNecessary(value, requiredType);
				} catch (RuntimeException e) {
					throw new ControllerException(url);
				}
			}

			@Override
			public <T> T convertIfNecessary(Object value,
					Class<T> requiredType, MethodParameter methodParam)
					throws TypeMismatchException {
				try {

					return super.convertIfNecessary(value, requiredType,
							methodParam);
				} catch (RuntimeException e) {
					throw new ControllerException(url);
				}
			}

		};
	}
}
