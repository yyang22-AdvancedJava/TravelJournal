package com.traveljournal.controller; // 또는 com.traveljournal.util

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/services")
public class JournalApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> h = new HashSet<>();
        // 위에서 만든 서비스 클래스를 등록합니다.
        h.add(JournalService.class);
        return h;
    }
}