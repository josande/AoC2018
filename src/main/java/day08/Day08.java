package day08;

import java.util.ArrayList;
import java.util.List;

public class Day08 {

    static class Node {
        private List<Integer> metadata = new ArrayList<>();
        private List<Node> children = new ArrayList<>();

        Node() {
            allNodes.add(this);
        }
        private void addChild(Node node) {
            children.add(node);
        }
        private void addMetadata(int metadataPost) {
            metadata.add(metadataPost);
        }
        private List<Node> getChildren() {
            return children;
        }
        private List<Integer> getMetadata() {
            return metadata;
        }
        int getSumOfMetadata() {
            return metadata.stream().mapToInt(Integer::intValue).sum();
        }
    }

    static List<Integer> inputRemaining = new ArrayList<>();
    static List<Node> allNodes = new ArrayList<>();

    static List<Node> getNodes(String input) {
        inputRemaining = new ArrayList<>();
        allNodes = new ArrayList<>();
        //List<Integer> values = Stream.of(input.split(" ")).mapToInt(Integer::valueOf).collect(Collectors.toList());
        for (String v : input.split(" ")) {
            inputRemaining.add(Integer.valueOf(v));
        }

        makeNode();

        return allNodes;
    }



    private static Node makeNode () {
        Node currentNode = new Node();
        int childNodes = inputRemaining.get(0);
        int metaDataPosts = inputRemaining.get(1);
        inputRemaining.remove(0);
        inputRemaining.remove(0);
        for (int c=0; c<childNodes; c++) {
            currentNode.addChild(makeNode());
        }
        for (int m=0;m<metaDataPosts;m++) {
            currentNode.addMetadata(inputRemaining.get(0));
            inputRemaining.remove(0);
        }
        return currentNode;
    }
    static int solveA(String input) {
        getNodes(input);
        return allNodes.stream()
                       .mapToInt(Node::getSumOfMetadata)
                       .sum();

    }
    static int solveB(String input) {
        getNodes(input);
        return getRecursiveMetaValue(allNodes.get(0));

    }

    static private int getRecursiveMetaValue(Node node) {
        if (node.getChildren().isEmpty()) {
            return node.getSumOfMetadata();
        }
        int metaValue=0;
        for (int i=0; i<node.getMetadata().size(); i++) {
            int metaDataValue = node.getMetadata().get(i);
            if (metaDataValue == 0)
                continue;
            if (metaDataValue <= node.getChildren().size()) {
                metaValue+=getRecursiveMetaValue(node
                                                 .getChildren()
                                                 .get(metaDataValue-1));
            }
        }
        return metaValue;
    }

}
