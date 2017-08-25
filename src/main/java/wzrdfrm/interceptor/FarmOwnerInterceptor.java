//package wzrdfrm.interceptor;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.HandlerMapping;
//import org.springframework.web.servlet.ModelAndView;
//import wzrdfrm.model.farm.Farm;
//import wzrdfrm.model.user.User;
//import wzrdfrm.repository.FarmRepository;
//import wzrdfrm.repository.UserRepository;
//import wzrdfrm.security.BadRequestException;
//import wzrdfrm.security.ForbiddenAccessException;
//import wzrdfrm.util.AuthUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Map;
//
//@Component
//public class FarmOwnerInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    private FarmRepository farmRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private Logger logger = Logger.getLogger(FarmOwnerInterceptor.class);
//
//    /** Validates that the farm being requested is owned by the logged in user. Only applies if the @FarmOwnerRequired annotation is on the method */
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // check the annotation on the handler method. if @FarmOwnerRequired isn't present, return true
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        FarmOwnerRequired characterSheetOwnerRequired = handlerMethod.getMethod().getAnnotation(FarmOwnerRequired.class);
//        if (characterSheetOwnerRequired == null) {
//            return true;
//        }
//
//        logger.debug("prehandling FarmOwnerInterceptor");
//
//        Map<String, String> pathVariables = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
//
//        Long farmId = Long.valueOf(pathVariables.get("farmId"));
//        if (farmId == null) {
//            throw new BadRequestException("No farmId parameter provided");
//        }
//        Farm farm = farmRepository.findOne(farmId);
//        if (farm == null) {
//            throw new BadRequestException("farmId parameter invalid");
//        }
//
//
//        User user = AuthUtils.getLoggedInUser(request);
//        if (!farm.getOwner().getId().equals(user.getId())) {
//            logger.error("user doesn't match: " + farm.getOwner() + " " + user);
//            throw new ForbiddenAccessException();
//        }
//
//        // store charsheet in request so controllers can access it without looking up again
//        request.setAttribute(AuthUtils.FARM, farm);
//        return true;
//    }
//
////    private String parseCharId(String uri) {
////        if (uri.startsWith("/api/char/")) {
////            String[] parts = uri.split("/");
////            return parts[3];
////        }
////        else {
////            return null;
////        }
////    }
//
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//
//    }
//}
