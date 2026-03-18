package com.traveljournal.services;

import com.traveljournal.persistence.JournalDao; // 본인의 DAO 클래스명 확인
import com.traveljournal.entity.Journal;        // 본인의 Entity 클래스명 확인

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Class to test Your second RESTful Web Service in IntelliJ
 */
@Path("/journals")
public class JournalService {

    // persistence 패키지에 있는 DAO 활용
    JournalDao dao = new JournalDao();

    /**
     * Get all Journals in text
     * URL: /services/journals
     */
    /*
    @GET
    @Produces(MediaType.TEXT_HTML) // 출력을 HTML로 설정
    //@Produces("text/plain")
    public Response getAllEntries() {
        List<Journal> journals = dao.getAll(); // DAO 메서드명이 다르면 수정하세요 (예: findAll)

        StringBuilder html = new StringBuilder();
        html.append("<html><head><title>Travel Journal</title></head><body>");
        html.append("<h1>My Travel Journal Entries</h1>");
        html.append("<table border='1'><tr><th>ID</th><th>Title</th><th>Description</th><th>Location</th></tr>");

        // 데이터를 하나씩 테이블 행으로 추가
        for (Journal j : journals) {
            html.append("<tr>")
                    .append("<td>").append(j.getId()).append("</td>")
                    .append("<td>").append(j.getTitle()).append("</td>")
                    .append("<td>").append(j.getContent()).append("</td>")
                    .append("<td>").append(j.getLocation()).append("</td>")
                    .append("</tr>");
        }

        html.append("</table></body></html>");

        //return Response.status(200).entity(journals).build();
        return Response.status(200).entity(html.toString()).build();
    }
    */

    /**
     * 특정 ID의 일지 반환 (Extra Challenge - PathParam 활용)
     * URL: /services/journals/5
     */
    /* For later use
    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_HTML)
    public Response getEntryHtml(@PathParam("id") int id) {
        Journal j = dao.getById(id);

        if (j == null) {
            return Response.status(404).entity("<html><body><h1>Entry not found!</h1></body></html>").build();
        }

        String html = "<html><body>" +
                      "<h1>Entry Details</h1>" +
                      "<ul>" +
                      "<li>ID: " + j.getId() + "</li>" +
                      "<li>Destination: " + j.getDestination() + "</li>" +
                      "</ul>" +
                      "<a href='../journals'>Back to list</a>" +
                      "</body></html>";

        return Response.status(200).entity(html).build();
    }
    */

    // JSON
    @GET
    @Produces(MediaType.APPLICATION_JSON) // 1. 출력을 JSON으로 설정
    public Response getAllEntries() {
        List<Journal> journals = dao.getAll();

        // 2. HTML 문자열 대신 자바 객체(List)를 그대로 반환
        // Jersey와 Jackson 라이브러리가 협력하여 자동으로 JSON 배열로 변환합니다.
        return Response.status(200)
                .entity(journals)
                .build();
    }
}
