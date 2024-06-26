package com.example.basicproject.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BurgerTest {

    @Test
    public void 자바_객체를_JSON으로_변환() throws JsonProcessingException {
        // 준비
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> ingredients = Arrays.asList("통새우 패티", "고기 패티", "토마토", "소스");
        Burger burger = new Burger("맥도날드", 5500, ingredients);

        // 수행
        String json = objectMapper.writeValueAsString(burger);

        // 예상
        String expected = "{\"name\":\"맥도날드\",\"price\":5500,\"ingredients\":[\"통새우 패티\",\"고기 패티\",\"토마토\",\"소스\"]}";

        // 검증
        assertEquals(expected, json);
        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());
    }

    @Test
    public void JSON을_자바_객체로_변환() throws JsonProcessingException {
        // 준비
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("name", "맥도날드");
        objectNode.put("price", "5500");

        ArrayNode arrayNode = objectMapper.createArrayNode();
        arrayNode.add("통새우 패티");
        arrayNode.add("고기 패티");
        arrayNode.add("토마토");
        arrayNode.add("소스");
        objectNode.set("ingredients", arrayNode);
        String json = objectNode.toString();

        // 수행
        Burger burger = objectMapper.readValue(json, Burger.class);

        // 예상
        List<String> ingredients = Arrays.asList("통새우 패티", "고기 패티", "토마토", "소스");
        Burger expected = new Burger("맥도날드", 5500, ingredients);

        // 검증
        assertEquals(expected.toString(), burger.toString());
        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());
        System.out.println(burger.toString());
    }
}