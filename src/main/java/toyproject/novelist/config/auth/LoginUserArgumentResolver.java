package toyproject.novelist.config.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import toyproject.novelist.config.auth.dto.SessionUser;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
@Slf4j
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return SessionUser.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        if (httpSession.getAttribute("user") != null) {
            return httpSession.getAttribute("user");
        }
        else {
            Object securityContextObject = httpSession.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
            if (securityContextObject != null) {
                SecurityContext securityContext = (SecurityContext) securityContextObject;
                Authentication authentication = securityContext.getAuthentication();
                return authentication.getPrincipal();
            }
            else {
                return null;
            }
        }
    }
}