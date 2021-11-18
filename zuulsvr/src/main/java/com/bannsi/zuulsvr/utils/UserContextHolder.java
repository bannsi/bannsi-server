package com.bannsi.zuulsvr.utils;

import org.springframework.util.Assert;

public class UserContextHolder {
    private static final ThreadLocal<UserContext> userContext = new ThreadLocal<UserContext>();

    public static final UserContext getContext(){
        UserContext context = userContext.get();

        if(context == null){
            context = createEmptyContext();
            userContext.set(context);
        }
        return userContext.get();
    }

    public static final void setContext(UserContext context){
        Assert.notNull(context, "ONLY not-null UserContext instances are primitted");
        userContext.set(context);
    }
    public static final UserContext createEmptyContext(){
        return new UserContext();
    }
}
