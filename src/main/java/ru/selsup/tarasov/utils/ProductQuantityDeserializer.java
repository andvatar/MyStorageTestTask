package ru.selsup.tarasov.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.HashMap;

public class ProductQuantityDeserializer extends JsonDeserializer<HashMap<String, Integer>> {
    @Override
    public HashMap<String, Integer> deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException {
        HashMap<String, Integer> ret = new HashMap<>();

        ObjectCodec codec = parser.getCodec();
        TreeNode node = codec.readTree(parser);

        if (node.isArray()){
            for (JsonNode n : (ArrayNode)node){
                JsonNode productName = n.get("name");
                if (productName != null){
                    JsonNode quantity = n.get("quantity");
                    ret.put(productName.asText(), quantity.intValue());
                }
            }
        }
        return ret;
    }
}
