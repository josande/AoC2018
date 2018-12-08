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
            return metadata.stream()
                           .mapToInt(Integer::intValue)
                           .sum();
        }
    }

    private static Node getBaseNode(String input) {
        LinkedList<Integer> inputsRemaining = Arrays.stream(input.split(" "))
                                                    .mapToInt(Integer::valueOf)
                                                    .boxed()
                                                    .collect(Collectors.toCollection(LinkedList::new));
        return getNode(inputsRemaining);
    }

    private static Node getNode(List<Integer> inputsRemaining) {
        Node currentNode = new Node();
        int childNodes = inputsRemaining.get(0);
        inputsRemaining.remove(0);
        int metaDataPosts = inputsRemaining.get(0);
        inputsRemaining.remove(0);

        for (int c=0; c<childNodes; c++) {
            currentNode.addChild(getNode(inputsRemaining));
        }
        for (int m=0;m<metaDataPosts;m++) {
            currentNode.addMetadata(inputsRemaining.get(0));
            inputsRemaining.remove(0);
        }
        return currentNode;
    }
    static int solveA(String input) {
        return getRecursiveMetaDataValue(getBaseNode(input));
    }

    static private int getRecursiveMetaDataValue(Node node) {
        return  node.getSumOfMetadata() +
                node.getChildren().stream()
                                  .mapToInt(Day08::getRecursiveMetaDataValue)
                                  .sum();
    }
    static int solveB(String input) {
        return getRecursiveMetaDataValueUsingMetaReferences(getBaseNode(input));
    }

    static private int getRecursiveMetaDataValueUsingMetaReferences(Node node) {
        if (node.getChildren().isEmpty()) {
            return node.getSumOfMetadata();
        }
        return node.getMetadata().stream()
                                 .filter(m -> m > 0)
                                 .filter(m -> m <= node.getChildren().size())
                                 .mapToInt(m -> getRecursiveMetaDataValueUsingMetaReferences(node.getChildren()
                                                                                                 .get(m-1)))
                                 .sum();
    }

}
