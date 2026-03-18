package com.traveljournal.services; // 또는 com.traveljournal.util

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
        // To use JSON
        // 3. JSON 변환 기능을 가진 JacksonFeature 등록 (주석 해제)
        h.add(org.glassfish.jersey.jackson.JacksonFeature.class);

        return h;
    }
}