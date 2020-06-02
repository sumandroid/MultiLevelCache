package domain.models;

import java.util.HashMap;

public class Cache {

    private HashMap<String, ListNode> keyStore;
    private DoubleLinkedList doubleLinkedList;
    private int size;
    private Integer level;
    private static final Object lock = new Object();


    public Cache(int size, Integer level) {
        this.size = size;
        this.level = level;
        this.keyStore = new HashMap<>();
        this.doubleLinkedList = new DoubleLinkedList();
    }

    public String write(String key, String value) {
        synchronized (lock){
            if (keyStore.containsKey(key) && keyStore.get(key).value.equals(value)) {
                return null;
            } else if(keyStore.containsKey(key) && !keyStore.get(key).value.equals(value)){
                ListNode node = keyStore.get(key);
                doubleLinkedList.removeNode(node);
                keyStore.remove(key);
            } else {
                if (keyStore.size() >= size) {
                    ListNode deletedNode = doubleLinkedList.removeLeastRecentlyUsed();
                    keyStore.remove(deletedNode.key);
                }
            }
            ListNode listNode = doubleLinkedList.add(key, value);
            keyStore.put(key, listNode);
            return keyStore.get(key).value;
        }
    }

    public String read(String key){
        synchronized (lock){
            if(!keyStore.containsKey(key)){
                return null;
            }else{
                ListNode node = keyStore.get(key);
                doubleLinkedList.moveNodeToFront(node);
                return node.value;
            }
        }
    }


    private static class DoubleLinkedList {
        private ListNode head;
        private ListNode tail;

        public DoubleLinkedList() {
            this.head = new ListNode("@head", "@head");
            this.tail = new ListNode("@tail", "@tail");
            head.next = tail;
            tail.prev = head;
        }

        public ListNode add(String key, String value) {
            ListNode newNode = new ListNode(key, value);
            ListNode headNext = head.next;
            head.next = newNode;
            newNode.prev = head;
            newNode.next = headNext;
            headNext.prev = newNode;
            return newNode;
        }

        public ListNode removeLeastRecentlyUsed() {
            ListNode removableNode = tail.prev;
            removableNode.prev.next = tail;
            tail.prev = removableNode.prev;
            return removableNode;
        }

        public ListNode removeNode(ListNode node) {
            ListNode prev = node.prev;
            ListNode next = node.next;
            prev.next = next;
            next.prev = prev;
            node.prev = null;
            node.next = null;
            return node;
        }

        public void moveNodeToFront(ListNode node) {
            ListNode prev = node.prev;
            ListNode next = node.next;
            prev.next = next;
            next.prev = prev;
            ListNode headNext = head.next;
            head.next = node;
            node.prev = head;
            headNext.prev = node;
            node.next = headNext;
        }
    }


    private static class ListNode {
        private String value;
        private String key;
        private ListNode next;
        private ListNode prev;


        public ListNode(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }

        public String getValue() {
            return value;
        }

        public String getKey() {
            return key;
        }
    }
}
