package day08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class Day08 {

    static class Node {
        private List<Integer> metadata = new ArrayList<>();
        private List<Node> children = new ArrayList<>();

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

    private static List<Node> getAllNodes(String input) {
        LinkedList<Integer> inputRemaining = Arrays.stream(input.split(" "))
                                             .mapToInt(Integer::valueOf)
                                             .boxed()
                                             .collect(Collectors.toCollection(LinkedList::new));
        List<Node> allNodes = new ArrayList<>();
        extendListWithNodes(inputRemaining,allNodes);
        return allNodes;
    }



    private static Node extendListWithNodes(List<Integer> inputRemaining, List<Node> allNodes) {
        Node currentNode = new Node();
        allNodes.add(currentNode);
        int childNodes = inputRemaining.get(0);
        int metaDataPosts = inputRemaining.get(1);
        inputRemaining.remove(0);
        inputRemaining.remove(0);
        for (int c=0; c<childNodes; c++) {
            currentNode.addChild(extendListWithNodes(inputRemaining, allNodes));
        }
        for (int m=0;m<metaDataPosts;m++) {
            currentNode.addMetadata(inputRemaining.get(0));
            inputRemaining.remove(0);
        }
        return currentNode;
    }
    static int solveA(String input) {

        return getAllNodes(input).stream()
                       .mapToInt(Node::getSumOfMetadata)
                       .sum();

    }
    static int solveB(String input) {
        return getRecursiveMetaValue(getAllNodes(input).get(0));
    }

    static private int getRecursiveMetaValue(Node node) {
        if (node.getChildren().isEmpty()) {
            return node.getSumOfMetadata();
        }
        return node.getMetadata().stream()
                                 .filter(m -> m > 0)
                                 .filter(m -> m <= node.getChildren().size())
                                 .mapToInt(m -> getRecursiveMetaValue(node.getChildren().get(m-1)))
                                 .sum();
    }

}
